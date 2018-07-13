package shippo.sync.tookan.tookanapi;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import shippo.sync.tookan.global.TookanConfig;
import shippo.sync.tookan.entity.TookanAgentInfo;

import javax.ws.rs.core.Response;

public class AgentApi {

    public Response insertAgentWithResponse(TookanAgentInfo tookanAgent) {
        Response response = null;

        // Todo

        return response;
    }

    public Response updateAgentWithResponse(TookanAgentInfo tookanAgentInfo) {
        Response response = null;

        // Todo

        return response;
    }

    // Tao thanh cong tra ve fleed_id, loi tra ve null
    public static Integer insertAgent(TookanAgentInfo tookanAgent) throws Exception {
        Integer fleed_id = null;

        // Todo
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field name = new Fields.Field("name", tookanAgent.getName());
        Fields.Field email = new Fields.Field("email", tookanAgent.getEmail());
        Fields.Field userName = new Fields.Field("username", tookanAgent.getUserName());
        Fields.Field phone = new Fields.Field("phone", tookanAgent.getPhone());
        Fields.Field transportType = new Fields.Field("transport_type", tookanAgent.getTransportType()); //value = 2
        Fields.Field transportDesc = new Fields.Field("transport_desc", tookanAgent.getTransportDesc());
        Fields.Field license = new Fields.Field("license", tookanAgent.getLicense());
        Fields.Field color = new Fields.Field("color", tookanAgent.getColor());
        Fields.Field timezone = new Fields.Field("timezone", tookanAgent.getTimezone()); //value = -430
        Fields.Field teamId = new Fields.Field("team_id", tookanAgent.getTeamId());
        Fields.Field password = new Fields.Field("password", tookanAgent.getPassword());
        Fields.Field firstName = new Fields.Field("first_name", tookanAgent.getFirstName());
        Fields.Field lastName = new Fields.Field("last_name", tookanAgent.getLastName());

        fields.put(api_key);
        fields.put(name);
        fields.put(email);
        fields.put(userName);
        fields.put(phone);
        fields.put(transportType);
        fields.put(transportDesc);
        fields.put(license);
        fields.put(color);
        fields.put(timezone);
        fields.put(teamId);
        fields.put(password);
        fields.put(firstName);
        fields.put(lastName);

        ContentResponse response = httpClient.FORM(TookanConfig.AGENT_ADD_URL, fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject.getString("message"));
            fleed_id = ((JSONObject) jsonObject.get("data")).getInt("fleet_id");
        }else
            System.out.println(jsonObject);
        httpClient.stop();

        return fleed_id;
    }

    public static boolean updateAgent(TookanAgentInfo tookanAgent) throws Exception {
        boolean success = false;

        if (tookanAgent.getFleetId() == null) return false;

        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field fleetId = new Fields.Field("fleet_id", tookanAgent.getFleetId().toString());

        fields.put(fleetId);
        fields.put(api_key);

        if (tookanAgent.getName() != null) {
            Fields.Field name = new Fields.Field("name", tookanAgent.getName());
            fields.put(name);
        }
        if (tookanAgent.getEmail() != null) {
            Fields.Field email = new Fields.Field("email", tookanAgent.getEmail());
            fields.put(email);
        }
        if (tookanAgent.getUserName() != null) {
            Fields.Field userName = new Fields.Field("username", tookanAgent.getUserName());
            fields.put(userName);
        }
        if (tookanAgent.getPhone() != null) {
            Fields.Field phone = new Fields.Field("phone", tookanAgent.getPhone());
            fields.put(phone);
        }
        if (tookanAgent.getTransportType() != null) {
            Fields.Field transportType = new Fields.Field("transport_type", tookanAgent.getTransportType());
            fields.put(transportType);
        }
        if (tookanAgent.getTransportDesc() != null) {
            Fields.Field transportDesc = new Fields.Field("transport_desc", tookanAgent.getTransportDesc());
            fields.put(transportDesc);
        }
        if (tookanAgent.getLicense() != null) {
            Fields.Field license = new Fields.Field("license", tookanAgent.getLicense());
            fields.put(license);
        }
        if (tookanAgent.getColor() != null) {
            Fields.Field color = new Fields.Field("color", tookanAgent.getColor());
            fields.put(color);
        }
        if (tookanAgent.getTimezone() != null) {
            Fields.Field timezone = new Fields.Field("timezone", tookanAgent.getTimezone());
            fields.put(timezone);
        }
        if (tookanAgent.getTeamId() != null) {
            Fields.Field teamId = new Fields.Field("team_id", tookanAgent.getTeamId());
            fields.put(teamId);
        }
        if (tookanAgent.getPassword() != null) {
            Fields.Field password = new Fields.Field("password", tookanAgent.getPassword());
            fields.put(password);
        }
        if (tookanAgent.getFirstName() != null) {
            Fields.Field firstName = new Fields.Field("first_name", tookanAgent.getFirstName());
            fields.put(firstName);
        }
        if (tookanAgent.getLastName() != null) {
            Fields.Field lastName = new Fields.Field("last_name", tookanAgent.getLastName());
            fields.put(lastName);
        }
        ContentResponse response = httpClient.FORM(TookanConfig.AGENT_EDIT_URL, fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject.getString("message"));
            success = true;
        }
//        System.out.println(jsonObject);
        httpClient.stop();
        return success;
    }

    public static TookanAgentInfo getAgentByFleetId(String fleet_id) throws Exception {


        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field fleetId = new Fields.Field("fleet_id", fleet_id);

        fields.put(fleetId);
        fields.put(api_key);

        ContentResponse response = httpClient.FORM(TookanConfig.AGENT_GET_URL, fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        JSONArray agentArray = jsonObject.getJSONArray("data");
        JSONObject jsonAgent = agentArray.getJSONObject(0);

        TookanAgentInfo agent = new TookanAgentInfo();
//        agent.setName(jsonAgent.getString("name"));
//        agent.setColor(jsonAgent.getString("fleet_status_color"));
        agent.setEmail(jsonAgent.getString("email"));
        agent.setPhone(jsonAgent.getString("phone"));
//        agent.setFirstName(jsonAgent.getString(""));
//        agent.setLastName("viet");
//        agent.setLicense(jsonAgent.getString("license"));
        agent.setTeamId(jsonAgent.getInt("team_id")+"");
//        agent.setTransportType(jsonAgent.getString("transport_type"));
//        agent.setTransportDesc(jsonAgent.getString("transport_desc"));

        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject);
            System.out.println(" Tookan agent get done!");
        }
//        System.out.println(jsonObject);
        httpClient.stop();
        return agent;
    }

    public static boolean updateBlockStatusOfAgent(int feet_id, int block_status) throws Exception {
        boolean success = false;

        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field fleetId = new Fields.Field("fleet_id", String.valueOf(feet_id));
        Fields.Field blockStatus = new Fields.Field("block_status", String.valueOf(block_status));

        fields.put(fleetId);
        fields.put(api_key);
        fields.put(blockStatus);


        ContentResponse response = httpClient.FORM(TookanConfig.AGENT_BLOCK_URL , fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        System.out.println(jsonObject);
        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject.getString("message"));
//            System.out.println(jsonObject);
            success = true;
        }
//        System.out.println(jsonObject);
        httpClient.stop();
        return success;
    }
    public static void main(String argv[]){
//        TookanAgentInfo agent = new TookanAgentInfo();
//        agent.setName("Patil");
//        agent.setColor("blue");
//        agent.setEmail("leviethoa@shippo.vn");
//        agent.setPhone("0123456789");
//        agent.setFirstName("hoa");
//        agent.setLastName("viet");
//        agent.setLicense("demo");
//        agent.setTeamId("10566");
//        agent.setTimezone("-330");
//        agent.setTransportType("1");
//        agent.setTransportDesc("auto");
//        agent.setUserName("viethoa");
//        agent.setPassword("123456");
//
//        try {
//            Integer t = AgentApi.insertAgentWithResponse(agent);
//            System.out.println(t);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
//            AgentApi.getAgentByFleetId("139388");
            AgentApi.updateBlockStatusOfAgent(18894, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
