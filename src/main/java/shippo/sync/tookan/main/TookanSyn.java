package shippo.sync.tookan.main;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.sync.tookan.entity.RiderTookanAgent;
import shippo.sync.tookan.entity.TookanAgentInfo;
import shippo.sync.tookan.entitymanager.RiderTookanAgentManager;
import shippo.sync.tookan.entitymanager.TeamManager;
import shippo.sync.tookan.global.Utils;
import shippo.sync.tookan.kafka.KafkaProducer;
import shippo.sync.tookan.kafka.SingleConsumer;
import shippo.sync.tookan.tookanapi.AgentApi;
import shippo.sync.tookan.tookanapi.TookanAgentParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TookanSyn {
    static Logger LOG = LoggerFactory.getLogger(TookanSyn.class);

    public TookanSyn() {
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        String currentPath = "";
        String confPath = "";
        try {
            currentPath = new File(".").getCanonicalPath();
            LOG.info("Current path: {}",currentPath);
            confPath = currentPath+"/conf/tookan-sync.properties";
            properties.load(new FileReader(confPath));
        } catch (IOException e) {
            LOG.error("Load properties file error ",e);

        }
        String brokerList = properties.getProperty("kafka.brokerlist");
        List<String> listTopic = Arrays.asList(properties.getProperty("kafka.tookanagent.topic"));
        String kafkaGroup = properties.getProperty("kafka.tookanagent.group");
        int nOfThread = Integer.parseInt(properties.getProperty("rider.nthread"));

        LOG.info("Loaded oauth configuration: brokerList - {},topics - {}, kafkaGroup - {}, nOThread - {} "
                ,brokerList, listTopic, kafkaGroup, nOfThread);
//
//        //tao producer test
//        String topic = properties.getProperty("kafka.tookanagent.topic");
//        KafkaProducer producer1 = new KafkaProducer(topic, "192.168.10.210:9092");
//
//        String data = "";
//        Gson gson = new Gson();
//        try {
//            currentPath = new File(".").getCanonicalPath();
//            confPath = currentPath + "/msgsample/msg-addagent.txt";
//            data = Utils.readFileToString(confPath);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        producer1.pushMsg(data.getBytes());


//        new SingleConsumer(brokerList, kafkaGroup, listTopic, nOfThread) {
//            @Override
//            public void processMsg(ConsumerRecord<byte[], byte[]> record)
//            {
//                Gson gson = new Gson();
//                String data = new String(record.value());
//                System.out.println(data);
//                try {
//
//                    System.out.println(data);
//                    JSONObject msg = new JSONObject(data);
//                    String action = String.valueOf(msg.get("action"));
//                    JSONObject agent = msg.getJSONObject("data");
//                    String rider_id = String.valueOf(agent.get("id")) ;
//                    TookanAgentInfo tookanAgentInfo = gson.fromJson(String.valueOf(agent), TookanAgentInfo.class);
//
//                    tookanAgentInfo.setTimezone("-430");
//                    tookanAgentInfo.setTransportType("2");
//                    tookanAgentInfo.setTransportDesc("");
//                    tookanAgentInfo.setLicense("");
//
//                    //lay tookan_id tu bang team
//                    if(tookanAgentInfo.getTeamId() != null){
//                        int shippo_team = Integer.parseInt(tookanAgentInfo.getTeamId());
//
//                        TeamManager teamManager = new TeamManager();
//                        teamManager.setup();
//                        int tookan_team = teamManager.getTeamById(shippo_team).getTookanId();
//                        tookanAgentInfo.setTeamId(tookan_team + "");
//                        teamManager.exit();
//                    }
//
//                    switch(action){
//                        case "c" :
//                            Integer fleet_id = null;
//                            try {
//                                fleet_id = AgentApi.insertAgent(tookanAgentInfo);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                            RiderTookanAgentManager manager = new RiderTookanAgentManager();
//                            RiderTookanAgent riderTookanAgent = new RiderTookanAgent();
//                            riderTookanAgent.setAgent(tookanAgentInfo.getUserName());
//                            riderTookanAgent.setAgentId(fleet_id);
//                            riderTookanAgent.setRiderId(Integer.parseInt(rider_id));
//                            riderTookanAgent.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//                            riderTookanAgent.setVersion(0);
//
//                            //add an agent on Tookan
//                            manager.setup();
//                            Long tookan_agent_id = manager.addRiderTookanAgent(riderTookanAgent);
//                            System.out.println("insert ok!");
//                            manager.exit();
//                            break;
//                        case "u" :
//                            RiderTookanAgentManager manager1 = new RiderTookanAgentManager();
//                            //read fleet_id
//                            manager1.setup();
//                            tookanAgentInfo.setFleetId(manager1.readByAgent(tookanAgentInfo.getUserName()).getAgentId());
//                            manager1.exit();
//
//                            //update agent on Tookan
//                            if(tookanAgentInfo.getFleetId() != null){
//                                Boolean checkUpdate = false;
//                                try {
//                                    checkUpdate = AgentApi.updateAgent(tookanAgentInfo);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            break;
//                        default :
//                            System.out.println("Action not support!");
//                            break;
//
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        };
        new TookanAgentParser(brokerList, kafkaGroup, listTopic, nOfThread);
    }
}