package shippo.sync.tookan.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TookanAgentInfo implements Serializable {

    @SerializedName("fleet_id")
    private Integer fleetId;

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("team_id")
    private String teamId;

    @SerializedName("transport_type")
    private String transportType;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("transport_desc")
    private String transportDesc;

    @SerializedName("phone")
    private String phone;

    @SerializedName("license")
    private String license;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("color")
    private String color;




    public Integer getFleetId() {
        return fleetId;
    }

    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTransportDesc() {
        return transportDesc;
    }

    public void setTransportDesc(String transportDesc) {
        this.transportDesc = transportDesc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
