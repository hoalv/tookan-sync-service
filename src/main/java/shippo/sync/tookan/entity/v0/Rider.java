package shippo.sync.tookan.entity.v0;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the riders database table.
 *
 */
@Entity
@Table(name="riders")
@NamedQuery(name="Rider.findAll", query="SELECT r FROM Rider r")
public class Rider implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String avatar;

    @Column(name="created_at")
    private Timestamp createdAt;

    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String mobile;

    @Column(name="nick_name")
    private String nickName;

    private String state;

    @Column(name="tag_line")
    private String tagLine;

    @Column(name="team_id")
    private Integer teamId;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column(name="user_id")
    private Integer userId;

    private String username;

    @Column(name = "is_synced_tookan")
    private Boolean isSyncedTookan;

    private Integer version;

    public Rider() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTagLine() {
        return this.tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public Integer getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIsSyncedTookan() {
        return isSyncedTookan;
    }

    public void setIsSyncedTookan(Boolean syncedTookan) {
        isSyncedTookan = syncedTookan;
    }

}