package shippo.sync.tookan.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the transportation_tasks database table.
 *
 */
@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name="transportation_tasks")
@NamedQuery(name="TransportationTask.findAll", query="SELECT t FROM TransportationTask t")
public class TransportationTask implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SerializedName("id")
    private Long id;

    @Column(name="accepted_at")
    @SerializedName("acceptedAt")
    private Timestamp acceptedAt;

    @Column(name="assigned_at")
    @SerializedName("assignedAt")
    private Timestamp assignedAt;

    @Column(name = "assignee")
    @SerializedName("assignee")
    private Integer assignee;

    @Column(name="assignee_type")
    @SerializedName("assigneeType")
    private String assigneeType;

    @Column(name="cancelled_at")
    @SerializedName("cancelledAt")
    private Timestamp cancelledAt;

    @Column(name = "cod")
    @SerializedName("cod")
    private Double cod;

    @Column(name="created_at")
    @SerializedName("createdAt")
    private Timestamp createdAt;

    @Column(name="created_by")
    @SerializedName("createdBy")
    private Integer createdBy;

    @Column(name="decline_at")
    @SerializedName("declineAt")
    private Timestamp declineAt;

    @Column(name="deleted_at")
    @SerializedName("deletedAt")
    private Timestamp deletedAt;

    @Column(name="deliver_before")
    @SerializedName("deliverBefore")
    private Timestamp deliverBefore;

    @Column(name="deliver_contact_name")
    @SerializedName("deliverContactName")
    private String deliverContactName;

    @Column(name="deliver_contact_phone")
    @SerializedName("deliverContactPhone")
    private String deliverContactPhone;

    @Column(name="deliver_detail")
    @SerializedName("deliverDetail")
    private String deliverDetail;

    @Column(name="deliver_full_address")
    @SerializedName("deliverFullAddress")
    private String deliverFullAddress;

    @Column(name="deliver_latitude")
    @SerializedName("deliverLatitude")
    private String deliverLatitude;

    @Column(name="deliver_location_ids_path")
    @SerializedName("deliverLocationIdsPath")
    private String deliverLocationIdsPath;

    @Column(name="deliver_longitude")
    @SerializedName("deliverLongitude")
    private String deliverLongitude;

    @Column(name = "description")
    @SerializedName("description")
    private String description;

    @Column(name="fail_reason")
    @SerializedName("failReason")
    private String failReason;

    @Column(name="failed_at")
    @SerializedName("failedAt")
    private Timestamp failedAt;

    @Column(name="has_delivery")
    @SerializedName("hasDelivery")
    private Boolean hasDelivery;

    @Column(name="has_pickup")
    @SerializedName("hasPickup")
    private Boolean hasPickup;

    @Column(name="in_progress_at")
    @SerializedName("inProgressAt")
    private Timestamp inProgressAt;

    @Column(name="internal_reason_fail")
    @SerializedName("internalReasonFail")
    private String internalReasonFail;

    @Column(name="is_last_mile_delivery")
    @SerializedName("isLastMileDelivery")
    private Boolean isLastMileDelivery;

    @Column(name="last_sync_at")
    @SerializedName("lastSyncAt")
    private Timestamp lastSyncAt;

    @Column(name="merchant_id")
    @SerializedName("merchantId")
    private Integer merchantId;

    @Column(name = "metadata")
    @SerializedName("metadata")
    private Object metadata;

    @Column(name = "note")
    @SerializedName("note")
    private String note;

    @Column(name="object_code")
    @SerializedName("objectCode")
    private String objectCode;

    @Column(name="object_id")
    @SerializedName("objectId")
    private Integer objectId;

    @Column(name="object_type")
    @SerializedName("objectType")
    private String objectType;

    @Column(name="pick_location_ids_path")
    @SerializedName("pickLocationIdsPath")
    private String pickLocationIdsPath;

    @Column(name="pickup_before")
    @SerializedName("pickupBefore")
    private Timestamp pickupBefore;

    @Column(name="pickup_contact_name")
    @SerializedName("pickupContactName")
    private String pickupContactName;

    @Column(name="pickup_contact_phone")
    @SerializedName("pickupContactPhone")
    private String pickupContactPhone;

    @Column(name="pickup_detail")
    @SerializedName("pickupDetail")
    private String pickupDetail;

    @Column(name="pickup_full_address")
    @SerializedName("pickupFullAddress")
    private String pickupFullAddress;

    @Column(name="pickup_latitude")
    @SerializedName("pickupLatitude")
    private String pickupLatitude;

    @Column(name="pickup_longitude")
    @SerializedName("pickupLongitude")
    private String pickupLongitude;

    @Column(name="real_cod")
    @SerializedName("realCod")
    private Double realCod;

    @Column(name="reason_code")
    @SerializedName("reasonCode")
    private Object reasonCode;

    @Column(name="recipient_pay_courier_fee")
    @SerializedName("recipientPayCourierFee")
    private String recipientPayCourierFee;

    @Column(name="request_id")
    @SerializedName("requestId")
    private Integer requestId;

    @Column(name="rider_shift_id")
    @SerializedName("riderShiftId")
    private Integer riderShiftId;

    @Column(name="started_at")
    @SerializedName("startedAt")
    private Timestamp startedAt;

    @Column(name = "state")
    @SerializedName("state")
    private String state;

    @Column(name="success_at")
    @SerializedName("successAt")
    private Timestamp successAt;

    @Column(name="task_deadline")
    @SerializedName("taskDeadline")
    private Timestamp taskDeadline;

    @Column(name="tookan_job_id")
    @SerializedName("tookanJobId")
    private String tookanJobId;

    @Column(name="tracking_link")
    @SerializedName("trackingLink")
    private Boolean trackingLink;

    @Column(name = "type")
    @SerializedName("type")
    private String type;

    @Column(name="updated_at")
    @SerializedName("updatedAt")
    private Timestamp updatedAt;

    @Column(name = "version")
    @SerializedName("version")
    private Integer version;

    public TransportationTask() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getAcceptedAt() {
        return this.acceptedAt;
    }

    public void setAcceptedAt(Timestamp acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Timestamp getAssignedAt() {
        return this.assignedAt;
    }

    public void setAssignedAt(Timestamp assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Integer getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeType() {
        return this.assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public Timestamp getCancelledAt() {
        return this.cancelledAt;
    }

    public void setCancelledAt(Timestamp cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public Double getCod() {
        return this.cod;
    }

    public void setCod(Double cod) {
        this.cod = cod;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getDeclineAt() {
        return this.declineAt;
    }

    public void setDeclineAt(Timestamp declineAt) {
        this.declineAt = declineAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Timestamp getDeliverBefore() {
        return this.deliverBefore;
    }

    public void setDeliverBefore(Timestamp deliverBefore) {
        this.deliverBefore = deliverBefore;
    }

    public String getDeliverContactName() {
        return this.deliverContactName;
    }

    public void setDeliverContactName(String deliverContactName) {
        this.deliverContactName = deliverContactName;
    }

    public String getDeliverContactPhone() {
        return this.deliverContactPhone;
    }

    public void setDeliverContactPhone(String deliverContactPhone) {
        this.deliverContactPhone = deliverContactPhone;
    }

    public String getDeliverDetail() {
        return this.deliverDetail;
    }

    public void setDeliverDetail(String deliverDetail) {
        this.deliverDetail = deliverDetail;
    }

    public String getDeliverFullAddress() {
        return this.deliverFullAddress;
    }

    public void setDeliverFullAddress(String deliverFullAddress) {
        this.deliverFullAddress = deliverFullAddress;
    }

    public String getDeliverLatitude() {
        return this.deliverLatitude;
    }

    public void setDeliverLatitude(String deliverLatitude) {
        this.deliverLatitude = deliverLatitude;
    }

    public String getDeliverLocationIdsPath() {
        return this.deliverLocationIdsPath;
    }

    public void setDeliverLocationIdsPath(String deliverLocationIdsPath) {
        this.deliverLocationIdsPath = deliverLocationIdsPath;
    }

    public String getDeliverLongitude() {
        return this.deliverLongitude;
    }

    public void setDeliverLongitude(String deliverLongitude) {
        this.deliverLongitude = deliverLongitude;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFailReason() {
        return this.failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Timestamp getFailedAt() {
        return this.failedAt;
    }

    public void setFailedAt(Timestamp failedAt) {
        this.failedAt = failedAt;
    }

    public Boolean getHasDelivery() {
        return this.hasDelivery;
    }

    public void setHasDelivery(Boolean hasDelivery) {
        this.hasDelivery = hasDelivery;
    }

    public Boolean getHasPickup() {
        return this.hasPickup;
    }

    public void setHasPickup(Boolean hasPickup) {
        this.hasPickup = hasPickup;
    }

    public Timestamp getInProgressAt() {
        return this.inProgressAt;
    }

    public void setInProgressAt(Timestamp inProgressAt) {
        this.inProgressAt = inProgressAt;
    }

    public String getInternalReasonFail() {
        return this.internalReasonFail;
    }

    public void setInternalReasonFail(String internalReasonFail) {
        this.internalReasonFail = internalReasonFail;
    }

    public Boolean getIsLastMileDelivery() {
        return this.isLastMileDelivery;
    }

    public void setIsLastMileDelivery(Boolean isLastMileDelivery) {
        this.isLastMileDelivery = isLastMileDelivery;
    }

    public Timestamp getLastSyncAt() {
        return this.lastSyncAt;
    }

    public void setLastSyncAt(Timestamp lastSyncAt) {
        this.lastSyncAt = lastSyncAt;
    }

    public Integer getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Object getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getObjectCode() {
        return this.objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public Integer getObjectId() {
        return this.objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getPickLocationIdsPath() {
        return this.pickLocationIdsPath;
    }

    public void setPickLocationIdsPath(String pickLocationIdsPath) {
        this.pickLocationIdsPath = pickLocationIdsPath;
    }

    public Timestamp getPickupBefore() {
        return this.pickupBefore;
    }

    public void setPickupBefore(Timestamp pickupBefore) {
        this.pickupBefore = pickupBefore;
    }

    public String getPickupContactName() {
        return this.pickupContactName;
    }

    public void setPickupContactName(String pickupContactName) {
        this.pickupContactName = pickupContactName;
    }

    public String getPickupContactPhone() {
        return this.pickupContactPhone;
    }

    public void setPickupContactPhone(String pickupContactPhone) {
        this.pickupContactPhone = pickupContactPhone;
    }

    public String getPickupDetail() {
        return this.pickupDetail;
    }

    public void setPickupDetail(String pickupDetail) {
        this.pickupDetail = pickupDetail;
    }

    public String getPickupFullAddress() {
        return this.pickupFullAddress;
    }

    public void setPickupFullAddress(String pickupFullAddress) {
        this.pickupFullAddress = pickupFullAddress;
    }

    public String getPickupLatitude() {
        return this.pickupLatitude;
    }

    public void setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public String getPickupLongitude() {
        return this.pickupLongitude;
    }

    public void setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public Double getRealCod() {
        return this.realCod;
    }

    public void setRealCod(Double realCod) {
        this.realCod = realCod;
    }

    public Object getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(Object reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getRecipientPayCourierFee() {
        return this.recipientPayCourierFee;
    }

    public void setRecipientPayCourierFee(String recipientPayCourierFee) {
        this.recipientPayCourierFee = recipientPayCourierFee;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getRiderShiftId() {
        return this.riderShiftId;
    }

    public void setRiderShiftId(Integer riderShiftId) {
        this.riderShiftId = riderShiftId;
    }

    public Timestamp getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getSuccessAt() {
        return this.successAt;
    }

    public void setSuccessAt(Timestamp successAt) {
        this.successAt = successAt;
    }

    public Timestamp getTaskDeadline() {
        return this.taskDeadline;
    }

    public void setTaskDeadline(Timestamp taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTookanJobId() {
        return this.tookanJobId;
    }

    public void setTookanJobId(String tookanJobId) {
        this.tookanJobId = tookanJobId;
    }

    public Boolean getTrackingLink() {
        return this.trackingLink;
    }

    public void setTrackingLink(Boolean trackingLink) {
        this.trackingLink = trackingLink;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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