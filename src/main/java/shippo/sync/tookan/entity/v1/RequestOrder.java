package shippo.sync.tookan.entity.v1;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "RequestOrder" database table.
 * 
 */
@Entity
@Table(name="\"RequestOrder\"")
@NamedQuery(name="RequestOrder.findAll", query="SELECT r FROM RequestOrder r")
public class RequestOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Column(name="\"orderId\"")
	private Integer orderId;

	@Column(name="\"overtimeDeliver\"")
	private Integer overtimeDeliver;

	@Column(name="\"requestId\"")
	private Integer requestId;

	private Integer version;

	public RequestOrder() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOvertimeDeliver() {
		return this.overtimeDeliver;
	}

	public void setOvertimeDeliver(Integer overtimeDeliver) {
		this.overtimeDeliver = overtimeDeliver;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}