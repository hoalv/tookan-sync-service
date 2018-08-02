package shippo.sync.tookan.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.sync.tookan.tookanapi.TookanAgentParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
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

        new TookanAgentParser(brokerList, kafkaGroup, listTopic, nOfThread);
    }
}