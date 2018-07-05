package shippo.sync.tookan.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the teams database table.
 *
 */
@Entity
@Table(name="teams")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="created_at")
    @SerializedName("create_at")
    private Timestamp createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name="parent_path")
    @SerializedName("parent_path")
    private String parentPath;

    @Column(name="updated_at")
    @SerializedName("update_at")
    private Timestamp updatedAt;

    @Column(name="tookan_id")
    @SerializedName("tookan_id")
    private Integer tookanId;

    @Column(name="hub_id")
    @SerializedName("hub_id")
    private Integer hubId;


    public Team() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public Integer getTookanId() {
        return tookanId;
    }

    public void setTookanId(Integer tookanId) {
        this.tookanId = tookanId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Team)) return false;
        Team other = (Team) obj;
        return (other.tookanId.equals(tookanId)
                || other.name.equals(name));
    }

    @Override
    public String toString() {
        return id + "_" + tookanId + "_" + name;
    }
}
