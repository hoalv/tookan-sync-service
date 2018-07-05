package shippo.sync.tookan.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the tookan_agents database table.
 *
 */
@Entity
@Table(name="tookan_agents")
//@NamedQuery(name="RiderTookanAgent.findAll", query="SELECT t FROM RiderTookanAgent t")
public class RiderTookanAgent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agent")
    private String agent;

    @Column(name="agent_id")
    private Integer agentId;

    @Column(name="rider_id")
    private Integer riderId;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column(name="version")
    private Integer version;

    public RiderTookanAgent() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getRiderId() {
        return this.riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
