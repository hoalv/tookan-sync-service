package shippo.sync.tookan.tookanapi;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import shippo.sync.tookan.entity.RiderTookanAgent;
import shippo.sync.tookan.entity.TookanAgentInfo;
import shippo.sync.tookan.entitymanager.RiderTookanAgentManager;
import shippo.sync.tookan.entitymanager.TeamManager;
import shippo.sync.tookan.kafka.SingleConsumer;

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
        String data = new String(record.value());
        System.out.println(data);
        try {
            TookanAgentInfo tookanAgentInfo = mapKafkaMsgToTookanAgentInfo(data);
//            System.out.println(data);
            JSONObject msg = new JSONObject(data);
            String action = String.valueOf(msg.get("action"));
            JSONObject agent = msg.getJSONObject("data");
            String rider_id = String.valueOf(agent.get("id"));

            switch (action) {
                case CREATE: {
                    Integer fleet_id = null;
                    try {
                        fleet_id = AgentApi.insertAgent(tookanAgentInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(fleet_id == null)return;

                    RiderTookanAgentManager manager = new RiderTookanAgentManager();
                    RiderTookanAgent riderTookanAgent = new RiderTookanAgent();
                    riderTookanAgent.setAgent(tookanAgentInfo.getUserName());
                    riderTookanAgent.setAgentId(fleet_id);
                    riderTookanAgent.setRiderId(Integer.parseInt(rider_id));
                    riderTookanAgent.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    riderTookanAgent.setVersion(0);

                    //add an agent on Tookan
                    manager.setup();
                    Long tookan_agent_id = manager.addRiderTookanAgent(riderTookanAgent);
                    System.out.println("insert ok!");
                    manager.exit();
                    break;
                }
                case UPDATE: {
                    RiderTookanAgentManager manager1 = new RiderTookanAgentManager();
                    //read fleet_id
                    manager1.setup();
                    tookanAgentInfo.setFleetId(manager1.readByAgent(tookanAgentInfo.getUserName()).getAgentId());
                    manager1.exit();

                    //update agent on Tookan
                    if (tookanAgentInfo.getFleetId() != null) {
                        Boolean checkUpdate = false;
                        try {
                            checkUpdate = AgentApi.updateAgent(tookanAgentInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
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
        String rider_id = String.valueOf(agent.get("id"));
        TookanAgentInfo tookanAgentInfo = gson.fromJson(String.valueOf(agent), TookanAgentInfo.class);

        tookanAgentInfo.setPhone(agent.getString("mobile"));
        tookanAgentInfo.setTimezone("-430");
        tookanAgentInfo.setTransportType("2");
        tookanAgentInfo.setTransportDesc("");
        tookanAgentInfo.setLicense("");
        tookanAgentInfo.setColor("");
        tookanAgentInfo.setName(tookanAgentInfo.getUserName());
        tookanAgentInfo.setFirstName(tookanAgentInfo.getFirstName() != null
                ? tookanAgentInfo.getFirstName() : "");
        tookanAgentInfo.setLastName(tookanAgentInfo.getLastName() != null
                ? tookanAgentInfo.getLastName() : "");

        //lay tookan_id tu bang team
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
