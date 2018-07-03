package shippo.sync.tookan.tookanapi;

import shippo.sync.tookan.entity.TookanAgentInfo;

import javax.ws.rs.core.Response;

public class AgentApi {

    public Response insertAgentWithResponse(TookanAgentInfo tookanAgent){
        Response response = null;

        // Todo

        return response;
    }

    public Response updateAgentWithResponse(TookanAgentInfo tookanAgentInfo){
        Response response = null;

        // Todo

        return response;
    }

    // Tao thanh cong tra ve fleed_id, loi tra ve null
    public Integer insertAgent(TookanAgentInfo tookanAgent){
        Integer fleed_id = null;

        // Todo

        return fleed_id;
    }

    public boolean updateAgent(TookanAgentInfo tookanAgentInfo){
        boolean success = false;

        return success;
    }
}
