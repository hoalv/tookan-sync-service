package shippo.sync.tookan.global;

import java.util.HashMap;
import java.util.Map;

public class KafkaRef {
    public static Map<String, String> topicMapper = new HashMap();

    final public static String TOOKAN_AGENT_API_TOPIC = "tookan.api.Agent";
//    final public static String RIDER_TOPIC = "rider_service.public.riders";

    static {
//        topicMapper.put(USERS_TOPIC, Constants.USER_PARSER_ACTOR);
//        topicMapper.put(RIDER_TOPIC, Constants.RIDER_PARSER_ACTOR);
    }
}
