package shippo.sync.tookan.tookanapi;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import shippo.sync.tookan.entity.v0.Rider;
import shippo.sync.tookan.entity.v0.RiderTookanAgent;
import shippo.sync.tookan.entity.tookan.TookanAgentInfo;
import shippo.sync.tookan.entitymanager.RiderManager;
import shippo.sync.tookan.entitymanager.RiderTookanAgentManager;
import shippo.sync.tookan.entitymanager.TeamManager;
import shippo.sync.tookan.kafka.SingleConsumer;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;

public class TookanAgentParser extends SingleConsumer {

    static final String CREATE = "c";
    static final String UPDATE = "u";

    public TookanAgentParser(String brokers, String groupId, List<String> topics, int nOThreads) {
        super(brokers, groupId, topics, nOThreads);
    }

    @Override
    public void processMsg(ConsumerRecord<byte[], byte[]> record) {
        String data = new String(record.value(), Charset.forName("UTF-8"));
        System.out.println(data);
        try {
            TookanAgentInfo tookanAgentInfo = mapKafkaMsgToTookanAgentInfo(data);
            JSONObject msg = new JSONObject(data);
            String action = String.valueOf(msg.get("action"));
            JSONObject agent = msg.getJSONObject("data");
            String rider_id_in_tookan_agents = null;
            if(agent.has("userId")){
                rider_id_in_tookan_agents = agent.getInt("userId") + "";

            }

            String rider_id = null;
            RiderManager riderManager = new RiderManager();
            riderManager.setup();
            Rider rider = new Rider();
            if (agent.has("id"))
                rider_id = String.valueOf(agent.get("id"));
            else {
                rider_id = riderManager.getRiderByUserId(Long.parseLong(rider_id_in_tookan_agents)).getId() + "";
            }
            riderManager.exit();

            switch (action) {
                case CREATE: {
                    Integer fleet_id = null;
                    try {
                        fleet_id = AgentApi.insertAgent(tookanAgentInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (fleet_id == null) return;

                    RiderTookanAgentManager manager = new RiderTookanAgentManager();
                    RiderTookanAgent riderTookanAgent = new RiderTookanAgent();
                    riderTookanAgent.setAgent(tookanAgentInfo.getUserName());
                    riderTookanAgent.setAgentId(fleet_id);
                    riderTookanAgent.setRiderId(Integer.parseInt(rider_id_in_tookan_agents));
                    riderTookanAgent.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    riderTookanAgent.setVersion(0);
//                    add an agent on Tookan
                    manager.setup();
                    Long tookan_agent_id = manager.addRiderTookanAgent(riderTookanAgent);
                    if (tookan_agent_id != null){
                        riderManager.setup();
                        riderManager.updateIsSyncTookan(Long.parseLong(rider_id), true);
                        riderManager.exit();
                        System.out.println("Insert tookan_agents ok!");
                    }
                    manager.exit();
                    break;
                }
                case UPDATE: {
                    RiderTookanAgentManager manager1 = new RiderTookanAgentManager();
                    Integer fleet_id = null;
                    manager1.setup();

//                    read fleet_id from table tookan_agents (= agent_id)
                    if (manager1.readByAgent(tookanAgentInfo.getUserName()) != null) {
                        fleet_id = manager1.readByAgent(tookanAgentInfo.getUserName()).getAgentId();
                    }

                    manager1.exit();
                    if (agent.has("state")) {
//                  update status block/unblock agent on tookan
                        Integer block_status = -1;
                        String state = (String) agent.get("state");
                        if (state.equals("ACTIVE")) {
                            block_status = 1;
                        } else if (state.equals("INACTIVE")) {
                            block_status = 0;
                        }
                        //read fleet_id
                        Boolean checkBlock = false;
                        if (fleet_id != null) {
                            checkBlock = AgentApi.updateBlockStatusOfAgent(fleet_id, block_status);
                        }
                        // rider already update on tookan
                        if(checkBlock){
                            riderManager.setup();
                            riderManager.updateIsSyncTookan(Long.parseLong(rider_id), true);
                            riderManager.exit();
                        }
                    } else {
//                        update agent on Tookan
                        tookanAgentInfo.setFleetId(fleet_id);
                        if (tookanAgentInfo.getFleetId() != null) {
                            Boolean checkUpdate = false;
                            try {
                                checkUpdate = AgentApi.updateAgent(tookanAgentInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // rider already update on tookan
                            if(checkUpdate){
                                riderManager.setup();
                                riderManager.updateIsSyncTookan(Long.parseLong(rider_id), true);
                                riderManager.exit();
                            }
                        }
                    }

                    break;
                }
                default: {
                    System.out.println("Action not support!");
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TookanAgentInfo mapKafkaMsgToTookanAgentInfo(String data) {
        Gson gson = new Gson();
        System.out.println(data);
        JSONObject msg = new JSONObject(data);
        JSONObject agent = msg.getJSONObject("data");
        TookanAgentInfo tookanAgentInfo = gson.fromJson(String.valueOf(agent), TookanAgentInfo.class);

        if (agent.has("mobile"))
            tookanAgentInfo.setPhone(agent.getString("mobile"));
        tookanAgentInfo.setTimezone("-420");
        tookanAgentInfo.setTransportType("2");
        tookanAgentInfo.setTransportDesc("");
        tookanAgentInfo.setLicense("");
        tookanAgentInfo.setColor("");
        tookanAgentInfo.setName(tookanAgentInfo.getUserName());
        tookanAgentInfo.setFirstName(tookanAgentInfo.getFirstName() != null
                ? tookanAgentInfo.getFirstName() : "");
        tookanAgentInfo.setLastName(tookanAgentInfo.getLastName() != null
                ? tookanAgentInfo.getLastName() : "");

//        lay tookan_id tu bang team
        if (tookanAgentInfo.getTeamId() != null) {
            int shippo_team = Integer.parseInt(tookanAgentInfo.getTeamId());

            TeamManager teamManager = new TeamManager();
            teamManager.setup();
            int tookan_team = teamManager.getTeamById(shippo_team).getTookanId();
            tookanAgentInfo.setTeamId(tookan_team + "");
            teamManager.exit();
        }
        return tookanAgentInfo;
    }
}
