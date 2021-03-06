package shippo.sync.tookan.global;

import java.util.Properties;

public class TookanConfig {
    public static String API_KEY;

    public static String AGENT_ADD_URL;
    public static String AGENT_EDIT_URL;
    public static String AGENT_GET_URL;
    public static String AGENT_BLOCK_URL;

    public static String TASK_ADD_URL;
    public static String TASK_EDIT_URL;

    static {
        try {
            Properties tempProp = new Properties();
            tempProp.load(TookanConfig.class.getResourceAsStream("/tookan_config.properties"));
            API_KEY = tempProp.getProperty("api_tookan_key");
            //for agent
            AGENT_ADD_URL = tempProp.getProperty("agent_add_url");
            AGENT_EDIT_URL = tempProp.getProperty("agent_edit_url");
            AGENT_GET_URL = tempProp.getProperty("agent_get_url");
            AGENT_BLOCK_URL = tempProp.getProperty("agent_block_url");
            //for task
            TASK_ADD_URL = tempProp.getProperty("task_add_url");
            TASK_EDIT_URL = tempProp.getProperty("task_edit_url");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
