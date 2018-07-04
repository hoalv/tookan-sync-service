package shippo.sync.tookan.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/*
 * Listen from many topics
 * */
public abstract class SingleConsumer {
    private Logger LOG = LoggerFactory.getLogger(SingleConsumer.class);
    private ExecutorService executorService;
    private KafkaConsumer<byte[],byte[]> kafkaConsumer;
    private List<String> topics;
    private int nOThreads;
    private AtomicInteger counter = new AtomicInteger(0);

    public SingleConsumer(String brokers, String groupId, List<String> topics, int nOThreads){
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "false");
//        props.put("auto.commit.interval.ms", "1000"); commit every message we get
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        props.put("key.deserializer", Serdes.String().deserializer().getClass().getName());
//        props.put("value.deserializer", serde.deserializer().getClass().getName());
        kafkaConsumer = new KafkaConsumer<byte[], byte[]>(props);
        kafkaConsumer.subscribe(topics);

        LOG.info("Start single consumer for topics {}", topics);
        startConsumeMsg(nOThreads);
    }

    private void startConsumeMsg(int nOThreads){
        try {
            executorService = Executors.newFixedThreadPool(nOThreads);
            while (true) {
                ConsumerRecords<byte[], byte[]> records = kafkaConsumer.poll(Long.MAX_VALUE);
//                executorService.execute(new Runnable() {
//                    @Override
//                    public void run() {
                for (ConsumerRecord<byte[], byte[]> record : records) {
                    processMsg(record);
                    kafkaConsumer.commitSync();
                    LOG.info("Single consumer consume the message {}th", counter.incrementAndGet());
                }
            }
//                });

//            }
        }catch (Exception ex){
            LOG.error("Consumer get message error {}", ex);
        }finally {
            LOG.info("Shutting down consumer ...");
            shutdown();
        }
    }
    /*
     * Process and commit every message
     * */
    public abstract void processMsg(ConsumerRecord<byte[], byte[]> record);

    public void shutdown(){
        if(kafkaConsumer != null){
            kafkaConsumer.close();
        }

        if(executorService != null){
            executorService.shutdown();
            try{
                LOG.info("Wait for terminating the executor ...");
                executorService.awaitTermination(5000, TimeUnit.MICROSECONDS);
            }catch (Exception ex){
                LOG.error("Terminate executor error {}", ex);
            }
        }


    }
}