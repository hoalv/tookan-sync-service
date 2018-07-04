package shippo.sync.tookan.main;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.sync.tookan.constant.KafkaRef;
import shippo.sync.tookan.kafka.KafkaProducer;
import shippo.sync.tookan.kafka.SingleConsumer;

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
        KafkaProducer producer1 = new KafkaProducer("hello", "192.168.10.210:9092");
        producer1.pushMsg("hello".getBytes());
        List<Integer> listMsg = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            listMsg.add(i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while ( counter < listMsg.size()){
                    try {
                        Thread.sleep(200l);
                        String message = listMsg.get(counter)+"";
                        producer1.pushMsg(message.getBytes());
                        counter++;
                    }catch (Exception ex){
                        LOG.error("Cant sent message {} ",ex);
                    }
                }
            }
        }).start();
        new SingleConsumer(brokerList, kafkaGroup, listTopic, nOfThread) {
            @Override
            public void processMsg(ConsumerRecord<byte[], byte[]> record)
            {
                if(KafkaRef.topicMapper.containsKey(record.topic())) {
                    System.out.println(new String(record.value()));

                }
            }
        };
    }
}