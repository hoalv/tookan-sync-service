package shippo.sync.tookan.main;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.sync.tookan.entity.TookanAgentInfo;
import shippo.sync.tookan.global.Utils;
import shippo.sync.tookan.kafka.KafkaProducer;
import shippo.sync.tookan.kafka.SingleConsumer;
import shippo.sync.tookan.tookanapi.AgentApi;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

        //tao producer test
        String topic = properties.getProperty("kafka.tookanagent.topic");
        KafkaProducer producer1 = new KafkaProducer(topic, "192.168.10.210:9092");

        String data = "";
        Gson gson = new Gson();
        try {
            currentPath = new File(".").getCanonicalPath();
            confPath = currentPath + "/msgsample/msg-updateagent.txt";
            data = Utils.readFileToString(confPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        producer1.pushMsg(data.getBytes());


        new SingleConsumer(brokerList, kafkaGroup, listTopic, nOfThread) {
            @Override
            public void processMsg(ConsumerRecord<byte[], byte[]> record)
            {
//                System.out.println(new String(record.value()));
//                String msgReceive = new String(record.value());
//                LOG.info(msgReceive);
                Gson gson = new Gson();

                String data = new String(record.value());
                System.out.println(data);
                JSONObject msg = new JSONObject(data);
                String action = String.valueOf(msg.get("action"));
                JSONObject agent = msg.getJSONObject("agent");
                TookanAgentInfo tookanAgentInfo = gson.fromJson(String.valueOf(agent), TookanAgentInfo.class);

                switch(action){
                    case "c" :
                        Integer fleet_id = null;
                        try {
                            fleet_id = AgentApi.insertAgent(tookanAgentInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "u" :
                        if(tookanAgentInfo.getFleetId() != null){
                            Boolean checkUpdate = false;
                            try {
                                checkUpdate = AgentApi.updateAgent(tookanAgentInfo);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default :
                        System.out.println("Action not support!");

                }
            }
        };
    }
}