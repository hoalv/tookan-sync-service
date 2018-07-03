import jdk.jfr.Description;
import org.junit.Test;

public class TestInsertNew {
    // Input message kafka
    // Expect all element of tookan map all element in message kafka

    @Description("Expect username,email,team_id,mobile(rider)" +
            "= user_name,email, team_id,phone (agent tookan)")
    @Test
    public void testSyncTookan(){

    }

    // Test Insert agent tookan

    @Description("Expect return id agent tookan")
    @Test
    public void testInsertAgent(){

    }

    @Description("Expect have a record with rider_id, agent_id,agent in tookan_agents table")
    @Test
    public void testMapping(){

    }
}
