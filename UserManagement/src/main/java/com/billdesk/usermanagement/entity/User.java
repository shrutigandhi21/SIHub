package com.billdesk.usermanagement.entity;

import java.sql.Timestamp;

import com.billdesk.usermanagement.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users1")
@JsonIgnoreProperties
public class User {
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq1")
	@SequenceGenerator(name="seq1", sequenceName="seq1", initialValue=100001)
	private Integer userId;
	
	private String password;
	
	@Email
	//@Column(unique=true)
	private String email;

	private String fname;
	
	private String lname;
	
	private String status;
	
	@Column(unique=true)
	private Long unumber;
	
	@CreationTimestamp
    private Timestamp createdate;
	
    @UpdateTimestamp
    private Timestamp updatedate;
    
    @CreationTimestamp
    private Timestamp passwordreset;
    
    @OneToOne
    @JoinColumn(name="roleno", referencedColumnName="roleId")
    private Role role;

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", fname=" + fname
				+ ", lname=" + lname + ", status=" + status + ", unumber=" + unumber + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", passwordreset=" + passwordreset + ", role=" + role
				+ ", getStatus()=" + getStatus() + ", getFname()=" + getFname() + ", getLname()=" + getLname()
				+ ", getUserId()=" + getUserId() + ", getEmail()=" + getEmail() + ", getCreatedate()=" + getCreatedate()
				+ ", getUpdatedate()=" + getUpdatedate() + ", getPasswordreset()=" + getPasswordreset()
				+ ", getPassword()=" + getPassword() + ", getUnumber()=" + getUnumber() + ", getRole()=" + getRole()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}

	

	public void setLname(String lname) {
		this.lname = lname;
	}



    

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}






	public Timestamp getCreatedate() {
		return createdate;
	}


	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}


	public Timestamp getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}


	public Timestamp getPasswordreset() {
		return passwordreset;
	}


	public void setPasswordreset(Timestamp passwordreset) {
		this.passwordreset = passwordreset;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean checkSpecialCharacters(String password2) {
		// TODO Auto-generated method stub
		return false;
	}


	public Long getUnumber() {
		return unumber;
	}


	public void setUnumber(Long unumber) {
		this.unumber = unumber;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public User(Integer userId, String password, String email, String fname, String lname, Long unumber,
			Timestamp createdate, Timestamp updatedate, Timestamp passwordreset, Role role) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.unumber = unumber;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.passwordreset = passwordreset;
		this.role = role;
	}

	public User(Integer userId, String password, String email, String fname, String lname, String status, Long unumber,
			Timestamp createdate, Timestamp updatedate, Timestamp passwordreset, Role role) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.status = status;
		this.unumber = unumber;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.passwordreset = passwordreset;
		this.role = role;
	}


}
	
	
	
