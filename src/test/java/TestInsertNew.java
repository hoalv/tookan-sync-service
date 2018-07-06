import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import shippo.sync.tookan.entity.RiderTookanAgent;
import shippo.sync.tookan.entity.TookanAgentInfo;
import shippo.sync.tookan.entitymanager.RiderTookanAgentManager;
import shippo.sync.tookan.entitymanager.TeamManager;
import shippo.sync.tookan.global.Utils;
import shippo.sync.tookan.kafka.KafkaProducer;
import shippo.sync.tookan.tookanapi.AgentApi;
import shippo.sync.tookan.tookanapi.TookanAgentParser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestInsertNew {
    // Input message kafka
    // Expect all element of tookan map all element in message kafka

    @Description("Expect username,email,team_id,mobile(rider)" +
            "= user_name,email, team_id,phone (agent tookan)")

    @Before
    public void initTest() {
        String topic = "shippo.rider_service.topic.test.rider";
        KafkaProducer producer1 = new KafkaProducer(topic, "192.168.10.210:9092");

        String data = "";
        String currentPath = "";
        String confPath = "";
        try {
            currentPath = new File(".").getCanonicalPath();

//            //test insert
//            confPath = currentPath + "/msgsample/msg-updateagent.txt";

            //test update
            confPath = currentPath + "/msgsample/msg-addagent.txt";

            data = Utils.readFileToString(confPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        producer1.pushMsg(data.getBytes());

        new Thread(new Runnable() {
            @Override
            public void run() {
                new TookanAgentParser("192.168.10.210:9092", "tookan_api.group", Arrays.asList(topic), 1);
            }
        }).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSyncTookan() {
        RiderTookanAgentManager manager1 = new RiderTookanAgentManager();
        manager1.setup();
        RiderTookanAgent agent1 = manager1.readByAgent("ijOK1YSefQ8hCDJ7Beu");
        manager1.exit();

        String fleet_id = String.valueOf(agent1.getAgentId());


        TeamManager teamManager = new TeamManager();
        teamManager.setup();
        int tookan_team = teamManager.getTeamById(90).getTookanId();
        teamManager.exit();

        TookanAgentInfo agent2 = null;
        try {
            agent2 = AgentApi.getAgentByFleetId(fleet_id);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        if (agent2 != null) {
            if (agent2.getEmail().equals("AiHaneda.qQ1HVaF47Gtksgi@test.com")
                    && agent2.getPhone().equals("0910469705")
                    && agent2.getTeamId().equals(String.valueOf(tookan_team))) {
                Assert.assertTrue(true);
            }else {
                Assert.assertTrue(false);
            }
        }else {
            Assert.assertTrue(false);
        }


    }

//    // Test Insert agent tookan
//
//    @Description("Expect return id agent tookan")
//    @Test
//    public void testInsertAgent() {
//    }
//
//    @Description("Expect have a record with rider_id, agent_id,agent in tookan_agents table")
//    @Test
//    public void testMapping() {
//
//    }
}
