package shippo.sync.tookan.global;

import java.util.Properties;

public class TookanConfig {
    public static String API_KEY;

    public static String AGENT_ADD_URL;
    public static String AGENT_EDIT_URL;
    public  static String AGENT_GET_URL;

    static {
        try{
            Properties tempProp = new Properties();
            tempProp.load(TookanConfig.class.getResourceAsStream("/tookan_config.properties"));
            API_KEY = tempProp.getProperty("api_tookan_key");
            AGENT_ADD_URL = tempProp.getProperty("agent_add_url");
            AGENT_EDIT_URL = tempProp.getProperty("agent_edit_url");
            AGENT_GET_URL = tempProp.getProperty("agent_get_url");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
