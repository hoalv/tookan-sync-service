package shippo.sync.tookan.tookanapi;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.json.JSONObject;
import shippo.sync.tookan.entity.tookan.TookanTask;
import shippo.sync.tookan.global.TookanConfig;
import shippo.sync.tookan.global.Utils;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

public class TaskApi {

    public Response insertAgentWithResponse(TookanTask tookanTask) {
        Response response = null;

        // Todo

        return response;
    }

    public Response updateAgentWithResponse(TookanTask tookanTaskInfo) {
        Response response = null;

        // Todo

        return response;
    }

    // Tao thanh cong tra ve job_id, loi tra ve null
    public static Integer createTask(TookanTask tookanTask) throws Exception {
        Integer job_id = null;

        // Todo
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field orderId = new Fields.Field("order_id", tookanTask.getOrder_id()+"");
        Fields.Field jobDescription = new Fields.Field("job_description", tookanTask.getJob_description());
        Fields.Field trackingLink = new Fields.Field("tracking_link", tookanTask.getTracking_link()+"");
        Fields.Field fleetId = new Fields.Field("fleet_id", tookanTask.getFleet_id()+"");
        Fields.Field jobPickupPhone = new Fields.Field("job_pickup_phone", tookanTask.getJob_pickup_phone());
        Fields.Field jobPickupName = new Fields.Field("job_pickup_name", tookanTask.getJob_pickup_name());
        Fields.Field jobPickupAddress = new Fields.Field("job_pickup_address", tookanTask.getJob_pickup_address());
        Fields.Field jobPickupDatetime = new Fields.Field("job_pickup_datetime", tookanTask.getJob_pickup_datetime());
        Fields.Field customerUsername = new Fields.Field("customer_username", tookanTask.getCustomer_username());
        Fields.Field customerPhone = new Fields.Field("customer_phone", tookanTask.getCustomer_phone());
        Fields.Field customerAddress = new Fields.Field("customer_address", tookanTask.getCustomer_address());
        Fields.Field customerEmail = new Fields.Field("customer_email", tookanTask.getCustomer_email());
        Fields.Field jobDeliveryDatetime = new Fields.Field("job_delivery_datetime", tookanTask.getJob_delivery_datetime());
        Fields.Field layoutType = new Fields.Field("layout_type", "0");
        Fields.Field timeZone = new Fields.Field("timezone", "-420");
        Fields.Field hasPickup = new Fields.Field("has_pickup", tookanTask.getHas_pickup()+"");
        Fields.Field hasDelivery = new Fields.Field("has_delivery", tookanTask.getHas_delivery()+"");
        Fields.Field autoAssignment = new Fields.Field("auto_assignment", tookanTask.getAuto_assignment()+"");
        Fields.Field pickupCustomFieldTemplate = new Fields.Field("pickup_custom_field_template", tookanTask.getPickup_custom_field_template());
        Fields.Field customFieldTemplate = new Fields.Field("custom_field_template", tookanTask.getCustom_field_template());
        Fields.Field notify = new Fields.Field("notify", tookanTask.getNotify()+"");
        Fields.Field geofence = new Fields.Field("geofence", tookanTask.getGeofence()+"");
        Fields.Field job_pickup_latitude = new Fields.Field("job_pickup_latitude", tookanTask.getJob_pickup_latitude());
        Fields.Field job_pickup_longitude = new Fields.Field("job_pickup_longitude", tookanTask.getJob_pickup_longitude());
        Fields.Field latitude = new Fields.Field("latitude", tookanTask.getLatitude());
        Fields.Field longitude = new Fields.Field("longitude", tookanTask.getLongitude());
        Fields.Field pickupMetaData = new Fields.Field("pickup_meta_data", String.valueOf(tookanTask.getPickup_meta_data()));
        Fields.Field metaData = new Fields.Field("meta_data", String.valueOf(tookanTask.getMeta_data()));

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
        fields.put(customerEmail);
        fields.put(jobDeliveryDatetime);
        fields.put(layoutType);
        fields.put(timeZone);
        fields.put(hasPickup);
        fields.put(hasDelivery);
        fields.put(autoAssignment);
        fields.put(pickupCustomFieldTemplate);
        fields.put(customFieldTemplate);
        fields.put(notify);
        fields.put(geofence);
        fields.put(job_pickup_latitude);
        fields.put(job_pickup_longitude);
        fields.put(latitude);
        fields.put(longitude);
        fields.put(pickupMetaData);
        fields.put(metaData);

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

    public static boolean updateTask(TookanTask tookanTask) throws Exception {
        boolean success = false;

        if (tookanTask.getJob_id() == null) return false;

        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(sslContextFactory);
        httpClient.setConnectTimeout(10000);
        httpClient.start();

        Fields fields = new Fields();
        Fields.Field api_key = new Fields.Field("api_key", TookanConfig.API_KEY);
        Fields.Field jobId = new Fields.Field("job_id", tookanTask.getJob_id()+"");

        fields.put(jobId);
        fields.put(api_key);

        if (tookanTask.getJob_description() != null ) {
            Fields.Field jobDescription = new Fields.Field("job_description", tookanTask.getJob_description());
            fields.put(jobDescription);
        }
        if (tookanTask.getOrder_id() != null) {
            Fields.Field orderId = new Fields.Field("order_id", tookanTask.getOrder_id()+"");
            fields.put(orderId);
        }
        if (tookanTask.getTracking_link() != null) {
            Fields.Field trackingLink = new Fields.Field("tracking_link", tookanTask.getTracking_link()+"");
            fields.put(trackingLink);
        }
        if (tookanTask.getFleet_id() != null) {
            Fields.Field fleetId = new Fields.Field("fleet_id", tookanTask.getFleet_id()+"");
            fields.put(fleetId);
        }
        if (tookanTask.getJob_pickup_phone() != null) {
            Fields.Field jobPickupPhone = new Fields.Field("job_pickup_phone", tookanTask.getJob_pickup_phone());
            fields.put(jobPickupPhone);
        }
        if (tookanTask.getJob_pickup_name() != null) {
            Fields.Field jobPickupName = new Fields.Field("job_pickup_name", tookanTask.getJob_pickup_name());
            fields.put(jobPickupName);
        }
        if (tookanTask.getJob_pickup_address() != null) {
            Fields.Field jobPickupAddress = new Fields.Field("job_pickup_address", tookanTask.getJob_pickup_address());
            fields.put(jobPickupAddress);
        }
        if (tookanTask.getJob_pickup_datetime() != null) {
            Fields.Field jobPickupDatetime = new Fields.Field("job_pickup_datetime", tookanTask.getJob_pickup_datetime()+"");
            fields.put(jobPickupDatetime);
        }
        if (tookanTask.getCustomer_username() != null) {
            Fields.Field customerUsername = new Fields.Field("customer_username", tookanTask.getCustomer_username());
            fields.put(customerUsername);
        }
        if (tookanTask.getCustomer_phone() != null) {
            Fields.Field customerPhone = new Fields.Field("customer_phone", tookanTask.getCustomer_phone());
            fields.put(customerPhone);
        }
        if (tookanTask.getCustomer_address() != null) {
            Fields.Field customerAddress = new Fields.Field("customer_address", tookanTask.getCustomer_address());
            fields.put(customerAddress);
        }
        if (tookanTask.getCustomer_email() != null) {
            Fields.Field customerAddress = new Fields.Field("customer_email", tookanTask.getCustomer_email());
            fields.put(customerAddress);
        }
        if (tookanTask.getJob_delivery_datetime() != null) {
            Fields.Field jobDeliverDatetime = new Fields.Field("job_deliver_datetime", tookanTask.getJob_delivery_datetime()+"");
            fields.put(jobDeliverDatetime);
        }
        if (tookanTask.getHas_pickup() != null) {
            Fields.Field hasPickup = new Fields.Field("has_pickup", tookanTask.getHas_pickup()+"");
            fields.put(hasPickup);
        }
        if (tookanTask.getHas_delivery() != null) {
            Fields.Field hasDelivery = new Fields.Field("has_delivery", tookanTask.getHas_delivery()+"");
            fields.put(hasDelivery);
        }
        if (tookanTask.getAuto_assignment() != null) {
            Fields.Field autoAssignment = new Fields.Field("auto_assignment", tookanTask.getAuto_assignment()+"");
            fields.put(autoAssignment);
        }
        if (tookanTask.getPickup_custom_field_template() != null) {
            Fields.Field pickupCustomFieldTemplate = new Fields.Field("pickup_custom_field_template", tookanTask.getPickup_custom_field_template()+"");
            fields.put(pickupCustomFieldTemplate);
        }
        if (tookanTask.getCustom_field_template() != null) {
            Fields.Field customFieldTemplate = new Fields.Field("custom_field_template", tookanTask.getCustom_field_template()+"");
            fields.put(customFieldTemplate);
        }
        if (tookanTask.getNotify() != null) {
            Fields.Field notify = new Fields.Field("notify", tookanTask.getNotify()+"");
            fields.put(notify);
        }if (tookanTask.getGeofence() != null) {
            Fields.Field geofence = new Fields.Field("geofence", tookanTask.getGeofence()+"");
            fields.put(geofence);
        }
        if (tookanTask.getJob_pickup_latitude() != null) {
            Fields.Field jobPickupLatitude = new Fields.Field("job_pickup_latitude", tookanTask.getJob_delivery_datetime()+"");
            fields.put(jobPickupLatitude);
        }
        if (tookanTask.getJob_pickup_longitude() != null) {
            Fields.Field jobPickupLongitude = new Fields.Field("job_pickup_longitude", tookanTask.getJob_pickup_longitude()+"");
            fields.put(jobPickupLongitude);
        }
        if (tookanTask.getLatitude() != null) {
            Fields.Field latitude = new Fields.Field("latitude", tookanTask.getLatitude()+"");
            fields.put(latitude);
        }
        if (tookanTask.getLongitude() != null) {
            Fields.Field longitude = new Fields.Field("longitude", tookanTask.getLongitude()+"");
            fields.put(longitude);
        }
        if (tookanTask.getPickup_meta_data() != null) {
            Fields.Field pickupMetaData = new Fields.Field("pickup_meta_data", tookanTask.getPickup_meta_data()+"");
            fields.put(pickupMetaData);
        }
        if (tookanTask.getMeta_data() != null) {
            Fields.Field metaData = new Fields.Field("meta_data", tookanTask.getMeta_data()+"");
            fields.put(metaData);
        }
        if (tookanTask.getLayout_type() != null) {
            Fields.Field layoutType = new Fields.Field("layout_type", tookanTask.getLayout_type()+"");
            fields.put(layoutType);
        }
        if (tookanTask.getTimezone() != null) {
            Fields.Field timezone = new Fields.Field("timezone", tookanTask.getTimezone()+"");
            fields.put(timezone);
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
        TookanTask tookanTask = gson.fromJson(String.valueOf(agent), TookanTask.class);
        try {
            if(action.equals("c")){
                TaskApi.createTask(tookanTask);

            }
            else if(action.equals("u")){
                TaskApi.updateTask(tookanTask);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
