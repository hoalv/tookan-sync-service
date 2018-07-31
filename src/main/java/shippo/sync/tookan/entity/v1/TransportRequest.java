package shippo.sync.tookan.entity.v1;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "TransportRequest" database table.
 * 
 */
@Entity
@Table(name="\"TransportRequest\"")
@NamedQuery(name="TransportRequest.findAll", query="SELECT t FROM TransportRequest t")
public class TransportRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"addressContactName\"")
	private String addressContactName;

	@Column(name="\"addressContactPhone\"")
	private String addressContactPhone;

	@Column(name="\"addressContacts\"")
	private Object addressContacts;

	@Column(name="\"addressDetail\"")
	private String addressDetail;

	@Column(name="\"addressDistrictId\"")
	private Integer addressDistrictId;

	@Column(name="\"addressFull\"")
	private String addressFull;

	@Column(name="\"addressId\"")
	private Integer addressId;

	@Column(name="\"addressPlaceId\"")
	private Integer addressPlaceId;

	@Column(name="\"addressProvinceId\"")
	private Integer addressProvinceId;

	@Column(name="\"addressStreetId\"")
	private Integer addressStreetId;

	@Column(name="\"addressWardId\"")
	private Integer addressWardId;

	@Column(name="\"createdBy\"")
	private Integer createdBy;

	@Column(name="\"createdTime\"")
	private Timestamp createdTime;

	@Column(name="\"customerId\"")
	private Integer customerId;

	private Integer id;

	@Column(name="\"isDeleted\"")
	private Integer isDeleted;

	@Column(name="\"lastStatusTime\"")
	private Timestamp lastStatusTime;

	@Column(name="\"modifiedBy\"")
	private Integer modifiedBy;

	@Column(name="\"modifiedTime\"")
	private Timestamp modifiedTime;

	@Column(name="\"numberOfOrder\"")
	private Integer numberOfOrder;

	@Column(name="\"numberOfSuccessOrder\"")
	private Integer numberOfSuccessOrder;

	@Column(name="\"orderIds\"")
	private Object orderIds;

	@Column(name="\"overtimeDeliver\"")
	private Integer overtimeDeliver;

	private String status;

	@Column(name="\"taskIds\"")
	private Object taskIds;

	@Column(name="\"totalWeight\"")
	private Integer totalWeight;

	private String type;

	private Integer version;

	@Column(name="\"warehouseId\"")
	private Integer warehouseId;

	public TransportRequest() {
	}

	public String getAddressContactName() {
		return this.addressContactName;
	}

	public void setAddressContactName(String addressContactName) {
		this.addressContactName = addressContactName;
	}

	public String getAddressContactPhone() {
		return this.addressContactPhone;
	}

	public void setAddressContactPhone(String addressContactPhone) {
		this.addressContactPhone = addressContactPhone;
	}

	public Object getAddressContacts() {
		return this.addressContacts;
	}

	public void setAddressContacts(Object addressContacts) {
		this.addressContacts = addressContacts;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Integer getAddressDistrictId() {
		return this.addressDistrictId;
	}

	public void setAddressDistrictId(Integer addressDistrictId) {
		this.addressDistrictId = addressDistrictId;
	}

	public String getAddressFull() {
		return this.addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getAddressPlaceId() {
		return this.addressPlaceId;
	}

	public void setAddressPlaceId(Integer addressPlaceId) {
		this.addressPlaceId = addressPlaceId;
	}

	public Integer getAddressProvinceId() {
		return this.addressProvinceId;
	}

	public void setAddressProvinceId(Integer addressProvinceId) {
		this.addressProvinceId = addressProvinceId;
	}

	public Integer getAddressStreetId() {
		return this.addressStreetId;
	}

	public void setAddressStreetId(Integer addressStreetId) {
		this.addressStreetId = addressStreetId;
	}

	public Integer getAddressWardId() {
		return this.addressWardId;
	}

	public void setAddressWardId(Integer addressWardId) {
		this.addressWardId = addressWardId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getLastStatusTime() {
		return this.lastStatusTime;
	}

	public void setLastStatusTime(Timestamp lastStatusTime) {
		this.lastStatusTime = lastStatusTime;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getNumberOfOrder() {
		return this.numberOfOrder;
	}

	public void setNumberOfOrder(Integer numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	public Integer getNumberOfSuccessOrder() {
		return this.numberOfSuccessOrder;
	}

	public void setNumberOfSuccessOrder(Integer numberOfSuccessOrder) {
		this.numberOfSuccessOrder = numberOfSuccessOrder;
	}

	public Object getOrderIds() {
		return this.orderIds;
	}

	public void setOrderIds(Object orderIds) {
		this.orderIds = orderIds;
	}

	public Integer getOvertimeDeliver() {
		return this.overtimeDeliver;
	}

	public void setOvertimeDeliver(Integer overtimeDeliver) {
		this.overtimeDeliver = overtimeDeliver;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getTaskIds() {
		return this.taskIds;
	}

	public void setTaskIds(Object taskIds) {
		this.taskIds = taskIds;
	}

	public Integer getTotalWeight() {
		return this.totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

}