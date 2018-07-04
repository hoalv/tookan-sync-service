package shippo.sync.tookan.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("JpaQlInspection")
@Entity
@Table(name = "tookan_agents")
@NamedQuery(name="RiderTookanAgent.findAll", query="SELECT t FROM RiderTookanAgent t")
public class RiderTookanAgent implements Serializable {

    @Id
    private Integer id;

    @Column(name = "rider_id")
    private Integer riderId;

    @Column(name = "agent")
    private String agent;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "version")
    private Integer version;

    @Column(name="updated_at")
    @SerializedName("updated_at")
    private Timestamp updatedAt;

    public RiderTookanAgent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
