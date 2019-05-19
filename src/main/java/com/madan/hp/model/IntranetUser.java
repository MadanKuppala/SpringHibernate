package com.madan.hp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_user")
public class IntranetUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String passwd;
	private String usertype;
	private String roleid;
	private Integer circleId;
	private String empid;
	private Date dt;
	private String emailid;
	private String phone;
	private String mobileNo;
	private String deptId;
	private String pwdStatus;
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	private short active;
	private Integer pwdType;
	private String encrypedPwd;
	private String range;
	private Integer districtId;
	private Integer divisionId;
	private Integer component;
	private Integer departments;
	
	public void setRange(String range) {
		this.range = range;
	}
	public String getRange() {
		return range;
	}

	// Constructors

	/** default constructor */
	public IntranetUser() {
	}

	/** minimal constructor */
	public IntranetUser(String userid) {
		this.userid = userid;
	}

	/** full constructor */

	// Property accessors
	@Id
	@Column(name = "USERID", unique = true, nullable = false, length = 25)
	public String getUserid() {
		return this.userid;
	}

	
	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "PASSWD", length = 50)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "USERTYPE", length = 1)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "ROLEID", length = 7)
	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Column(name = "CIRCLE_ID", length = 50)
	public Integer getCircleId() {
		return this.circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	@Column(name = "EMPID", length = 70)
	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DT", length = 7)
	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	@Column(name = "EMAILID", length = 50)
	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Column(name = "PHONE", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "MOBILE_NO", length = 15)
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "DEPT_ID", length = 15)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "PWD_STATUS", length = 1)
	public String getPwdStatus() {
		return this.pwdStatus;
	}

	public void setPwdStatus(String pwdStatus) {
		this.pwdStatus = pwdStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE", length = 7)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "CREATED_BY", length = 30)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "UPDATED_BY", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "ACTIVE",nullable = false, precision = 1, scale = 0)
	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}
	
  @Column(name = "PWDTYPE", precision = 1)
	public Integer getPwdType() {
		return pwdType;
	}
  
  public void setPwdType(Integer pwdType) {
		this.pwdType = pwdType;
	}
  
  @Column(name = "ENCRYPTEDPWD", length = 100)
	public String getEncrypedPwd() {
		return encrypedPwd;
	}

  public void setEncrypedPwd(String encrypedPwd) {
		this.encrypedPwd = encrypedPwd;
	}
  	
	@Column(name = "district_id")
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	
	
	@Column(name = "division_id")
	public Integer getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	
	
	@Column(name = "component_id")
	public Integer getComponent() {
		return component;
	}
	public void setComponent(Integer component) {
		this.component = component;
	}
	
	
	@Column(name = "department_id")
	public Integer getDepartments() {
		return departments;
	}
	public void setDepartments(Integer departments) {
		this.departments = departments;
	}
	
	
}
