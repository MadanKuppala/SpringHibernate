package com.madan.hp.model;

import java.io.Serializable;


public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String usertype;
	private Integer circle_id;
	private Integer subdiv_id;
	private Integer section_id;
	private String emailId;
	private String phone;
	private String mobile;
	private String password;
	private String role_id;
	private String dept_id;
	private String empId;
	private String encodedPwd;
	private Integer pwdType;
	private String range;
	private Integer districts;
	private Integer mandals;
    private Integer divisionId;
    private Integer componentId;
    private Integer departmentId;
    private Integer districtId;
	
	public Integer getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Integer long1) {
		this.divisionId = long1;
	}
	public Integer getDistricts() {
		return districts;
	}
	public void setDistricts(Integer districts) {
		this.districts = districts;
	}
	public Integer getMandals() {
		return mandals;
	}
	public void setMandals(Integer mandals) {
		this.mandals = mandals;
	}
	public User() {
    }
    public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
    
    public enum UserRole {

		PROJECTS("PROJECTS"),
		CONSTRUCTION("CONSTRUCTION"),
		VENDOR("VENDOR"),
		TPQC("TPQC");
		
	    private String role;
	    
	    private UserRole(String role) {
	            this.role = role;
	    }
	    public String getRole() {
	            return this.role;
	    }
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getSubdiv_id() {
		return subdiv_id;
	}

	public void setSubdiv_id(Integer subdiv_id) {
		this.subdiv_id = subdiv_id;
	}

	public Integer getSection_id() {
		return section_id;
	}

	public void setSection_id(Integer section_id) {
		this.section_id = section_id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getCircle_id() {
		return circle_id;
	}

	public void setCircle_id(Integer circle_id) {
		this.circle_id = circle_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}	

	public Integer getPwdType() {
		return pwdType;
	}
	public void setPwdType(Integer pwdType) {
		this.pwdType = pwdType;
	}
	public static  User toUser(IntranetUser inUser){
		User user=new User();
		user.setUserId(inUser.getUserid());
		if(inUser.getPwdType().equals(new Integer(1))){
			user.setPassword(inUser.getEncrypedPwd());
		}else{
			user.setPassword(inUser.getPasswd());
		}
		user.setRange(inUser.getRange());
		user.setRole_id(inUser.getRoleid());
		user.setCircle_id(inUser.getCircleId());
		user.setEncodedPwd(inUser.getEncrypedPwd());
		user.setPwdType(inUser.getPwdType());
		user.setMobile(inUser.getMobileNo());
		user.setEmailId(inUser.getEmailid());
		user.setUsertype(inUser.getUsertype());
		user.setDept_id(inUser.getDeptId());
		user.setDistricts(inUser.getDistrictId());
		user.setDistrictId(inUser.getDistrictId());
		if(inUser.getDepartments() != null)
			user.setDepartmentId(inUser.getDepartments());
		if(inUser.getDivisionId() != null)
			user.setDivisionId(inUser.getDivisionId());
		if(inUser.getComponent() != null)
			user.setComponentId(inUser.getComponent());
		
		return user;
		
	}
	public String getEncodedPwd() {
		return encodedPwd;
	}
	public void setEncodedPwd(String encodedPwd) {
		this.encodedPwd = encodedPwd;
	}
	
	public Integer getComponentId() {
		return componentId;
	}
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	
}
