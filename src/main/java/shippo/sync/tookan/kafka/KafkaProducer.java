package shippo.sync.tookan.kafka;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;


public class KafkaProducer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);
    private kafka.javaapi.producer.Producer producer;
    private final Properties properties = new Properties();
    private final ArrayBlockingQueue<byte[]> queue = new ArrayBlockingQueue<byte[]>(100);
    private static KafkaProducer instance = null;
    private String topic;

    public KafkaProducer(String topic, String brokerList) {
        this.topic = topic;
        properties.put("metadata.broker.list", brokerList);
        producer = new kafka.javaapi.producer.Producer(new ProducerConfig(properties));
        instance = this;
        startWorking();
    }

    public void pushMsg(byte[] data) {
        try {
            queue.put(data);
        } catch (InterruptedException e) {
            LOG.error("Error during push queue schedule", e);
        }
    }

    public void startWorking() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] data = queue.take();
                        KeyedMessage<String, byte[]> msg = new KeyedMessage<>(topic, data);
                        send(msg);
                    } catch (Exception ex) {
                        LOG.error("Error during send msg to compute topic", ex);
                    }
                }
            }
        }).start();
    }

    public synchronized static KafkaProducer getInstance() {
        if (instance == null)
            throw new RuntimeException("Producer was not initialized");
        return instance;
    }

    public int getQueueSize() {
        return queue.size();

    }

    public void send(KeyedMessage<String, byte[]> msg) {
        int count = 0;
        while (true) {
            try {
                producer.send(msg);
                return;
            } catch (Exception e) {
                LOG.error("Could not send msg now. Kafka exception. Retry: {} time", count++, e);
            }
        }
    }

    public void close() {
        producer.close();
    }

    public String getTopic() {
        return topic;
    }

    public static void main(String[] args) {

        KafkaProducer producer1 = new KafkaProducer("hello", "192.168.10.210:9092");
        producer1.pushMsg("hello".getBytes());
//        KafkaProducer producer2 = new KafkaProducer("test22", "localhost:19092");
//        KafkaProducer producer3 = new KafkaProducer("test33", "localhost:19092");
//        Random randomNumber = new Random();
        List<Integer> listMsg = new ArrayList<>();
//
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
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while ( counter < listMsg.size()){
                    try {
                        Thread.sleep(200l);
                        String message = listMsg.get(counter)+"";
                        producer2.pushMsg(message.getBytes());
                        counter++;
                    }catch (Exception ex){
                        LOG.error("Cant sent message {} ",ex);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while ( counter < listMsg.size()){
                    try {
                        Thread.sleep(200l);
                        String message = listMsg.get(counter)+"";
                        producer3.pushMsg(message.getBytes());
                        counter++;
                    }catch (Exception ex){
                        LOG.error("Cant sent message {} ",ex);
                    }
                }
            }
        }).start();*/

        List<String> topics = Arrays.asList("hello");

        Map<String, Integer> counterMap = new HashMap<>();

        int counter1 = 0;
//        int counter2 = 0;
//        int counter3 = 0;

        counterMap.put("test11", counter1);

//        counterMap.put("test22", counter2);
//
//        counterMap.put("test33", counter3);

        new SingleConsumer("192.168.10.210:9092","test",topics,
                1){
            public void processMsg(ConsumerRecord<byte[], byte[]> record) {
                String msgReceive = new String(record.value());
                LOG.info(msgReceive);
            }

        };

    }

}
