package shippo.sync.tookan.tookanapi;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import shippo.sync.tookan.entity.TookanAgentInfo;
import shippo.sync.tookan.entity.TransportationTask;
import shippo.sync.tookan.global.TookanConfig;
import shippo.sync.tookan.global.Utils;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TaskApi {

    public Response insertAgentWithResponse(TransportationTask transportationTask) {
        Response response = null;

        // Todo

        return response;
    }

    public Response updateAgentWithResponse(TransportationTask transportationTaskInfo) {
        Response response = null;

        // Todo

        return response;
    }

    // Tao thanh cong tra ve fleed_id, loi tra ve null
    public static Integer createTask(TransportationTask transportationTask) throws Exception {
        Integer job_id = null;

        // Todo
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field orderId = new Fields.Field("order_id", transportationTask.getId()+"");
        Fields.Field jobDescription = new Fields.Field("job_description", transportationTask.getDescription() + "," + transportationTask.getNote());
        Fields.Field trackingLink = new Fields.Field("tracking_link", transportationTask.getMetadata()!=null? (String) transportationTask.getMetadata() :"1");
        Fields.Field fleetId = new Fields.Field("fleet_id", transportationTask.getAssignee() + "");
        Fields.Field jobPickupPhone = new Fields.Field("job_pickup_phone", transportationTask.getPickupContactPhone());
        Fields.Field jobPickupName = new Fields.Field("job_pickup_name", transportationTask.getPickupContactName());
        Fields.Field jobPickupAddress = new Fields.Field("job_pickup_address", transportationTask.getPickupFullAddress());
        Fields.Field jobPickupDatetime = new Fields.Field("job_pickup_datetime", transportationTask.getPickupBefore()+"");
        Fields.Field customerUsername = new Fields.Field("customer_username", transportationTask.getDeliverContactName());
        Fields.Field customerPhone = new Fields.Field("customer_phone", transportationTask.getDeliverContactPhone());
        Fields.Field customerAddress = new Fields.Field("customer_address", transportationTask.getDeliverFullAddress());
        Fields.Field jobDeliveryDatetime = new Fields.Field("job_delivery_datetime", transportationTask.getDeliverBefore()+"");
        Fields.Field layoutType = new Fields.Field("layout_type", "0");
        Fields.Field timeZone = new Fields.Field("timezone", "-420");
        Fields.Field hasPickup = new Fields.Field("has_pickup", "0");
        Fields.Field hasDelivery = new Fields.Field("has_delivery", "0");
        if(transportationTask.getType().equals("PICKUP")){
            hasPickup = new Fields.Field("has_pickup", "1");
        }
        else if (transportationTask.getType().equals("DELIVER")){
            hasDelivery = new Fields.Field("has_delivery", "1");
        }

        fields.put(api_key);
        fields.put(orderId);
        fields.put(jobDescription);
        fields.put(trackingLink);
        fields.put(fleetId);
        fields.put(jobPickupPhone);
        fields.put(jobPickupName);
        fields.put(jobPickupAddress);
        fields.put(jobPickupDatetime);
        fields.put(customerUsername);
        fields.put(customerPhone);
        fields.put(customerAddress);
        fields.put(jobDeliveryDatetime);
        fields.put(layoutType);
        fields.put(timeZone);
        fields.put(hasPickup);
        fields.put(hasDelivery);

        ContentResponse response = httpClient.FORM(TookanConfig.TASK_ADD_URL, fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject.getString("message"));
            job_id = ((JSONObject) jsonObject.get("data")).getInt("job_id");
        }else
            System.out.println(jsonObject);
        httpClient.stop();

        return job_id;
    }

    public static boolean updateTask(TransportationTask transportationTask) throws Exception {
        boolean success = false;

        if (transportationTask.getTookanJobId() == null) return false;

        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field jobId = new Fields.Field("job_id", transportationTask.getTookanJobId());

        fields.put(jobId);
        fields.put(api_key);

        if (transportationTask.getDescription() != null && transportationTask.getNote() != null) {
            Fields.Field jobDescription = new Fields.Field("job_description", transportationTask.getDescription()+","+transportationTask.getNote());
            fields.put(jobDescription);
        }
        if (transportationTask.getId() != null) {
            Fields.Field orderId = new Fields.Field("order_id", transportationTask.getId()+"");
            fields.put(orderId);
        }
        if (transportationTask.getTrackingLink() != null) {
            Fields.Field trackingLink = new Fields.Field("tracking_link", (String) transportationTask.getMetadata());
            fields.put(trackingLink);
        }
        if (transportationTask.getAssignee() != null) {
            Fields.Field fleetId = new Fields.Field("fleet_id", transportationTask.getAssignee()+"");
            fields.put(fleetId);
        }
        if (transportationTask.getPickupContactPhone() != null) {
            Fields.Field jobPickupPhone = new Fields.Field("job_pickup_phone", transportationTask.getPickupContactPhone());
            fields.put(jobPickupPhone);
        }
        if (transportationTask.getPickupContactName() != null) {
            Fields.Field jobPickupName = new Fields.Field("job_pickup_name", transportationTask.getPickupContactName());
            fields.put(jobPickupName);
        }
        if (transportationTask.getPickupFullAddress() != null) {
            Fields.Field jobPickupAddress = new Fields.Field("job_pickup_address", transportationTask.getPickupFullAddress());
            fields.put(jobPickupAddress);
        }
        if (transportationTask.getPickupBefore() != null) {
            Fields.Field jobPickupDatetime = new Fields.Field("job_pickup_datetime", transportationTask.getPickupBefore()+"");
            fields.put(jobPickupDatetime);
        }
        if (transportationTask.getDeliverContactName() != null) {
            Fields.Field customerUsername = new Fields.Field("customer_username", transportationTask.getDeliverContactName());
            fields.put(customerUsername);
        }
        if (transportationTask.getDeliverContactPhone() != null) {
            Fields.Field customerPhone = new Fields.Field("customer_phone", transportationTask.getDeliverContactPhone());
            fields.put(customerPhone);
        }
        if (transportationTask.getDeliverFullAddress() != null) {
            Fields.Field customerAddress = new Fields.Field("customer_address", transportationTask.getDeliverFullAddress());
            fields.put(customerAddress);
        }
        if (transportationTask.getDeliverBefore() != null) {
            Fields.Field jobDeliverDatetime = new Fields.Field("job_deliver_datetime", transportationTask.getDeliverBefore()+"");
            fields.put(jobDeliverDatetime);
        }

        ContentResponse response = httpClient.FORM(TookanConfig.TASK_EDIT_URL, fields);
        JSONObject jsonObject = new JSONObject(response.getContentAsString());
        if (jsonObject.getInt("status") == 200) {
            System.out.println(jsonObject.getString("message"));
            success = true;
        }
//        System.out.println(jsonObject);
        httpClient.stop();
        return success;
    }

//    public static TransportationTask getAgentByFleetId(String fleet_id) throws Exception {
//
//
//        SslContextFactory sslContextFactory = new SslContextFactory();
//        HttpClient httpClient = new HttpClient(sslContextFactory);
//        httpClient.setConnectTimeout(10000);
//        httpClient.start();
//
//        Fields fields = new Fields();
//        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
//        Fields.Field fleetId = new Fields.Field("fleet_id", fleet_id);
//
//        fields.put(fleetId);
//        fields.put(api_key);
//
//        ContentResponse response = httpClient.FORM(TookanConfig.AGENT_GET_URL, fields);
//        JSONObject jsonObject = new JSONObject(response.getContentAsString());
//        JSONArray agentArray = jsonObject.getJSONArray("data");
//        JSONObject jsonAgent = agentArray.getJSONObject(0);
//
//        TransportationTask agent = new TransportationTask();
////        agent.setName(jsonAgent.getString("name"));
////        agent.setColor(jsonAgent.getString("fleet_status_color"));
//        agent.setEmail(jsonAgent.getString("email"));
//        agent.setPhone(jsonAgent.getString("phone"));
////        agent.setFirstName(jsonAgent.getString(""));
////        agent.setLastName("viet");
////        agent.setLicense(jsonAgent.getString("license"));
//        agent.setTeamId(jsonAgent.getInt("team_id")+"");
////        agent.setTransportType(jsonAgent.getString("transport_type"));
////        agent.setTransportDesc(jsonAgent.getString("transport_desc"));
//
//        if (jsonObject.getInt("status") == 200) {
//            System.out.println(jsonObject);
//            System.out.println(" Tookan agent get done!");
//        }
////        System.out.println(jsonObject);
//        httpClient.stop();
//        return agent;
//    }


    public static void main(String argv[]){
        String currentPath = "";
        String confPath = "";
        String data = "";
        Gson gson = new Gson();
        try {
            currentPath = new File(".").getCanonicalPath();
            confPath = currentPath + "/msgsample/msg-edittask.txt";
            data = Utils.readFileToString(confPath);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject msg = new JSONObject(data);
        String action = String.valueOf(msg.get("action"));
        JSONObject agent = msg.getJSONObject("data");
        TransportationTask transportationTask = gson.fromJson(String.valueOf(agent), TransportationTask.class);
        try {
            if(action.equals("c")){
                TaskApi.createTask(transportationTask);

            }
            else if(action.equals("u")){
                TaskApi.updateTask(transportationTask);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
