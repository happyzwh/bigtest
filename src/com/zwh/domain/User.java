package com.zwh.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zwh.annotation.DataBase;

@Entity
@DataBase(name="DB2")
@Table(name="t_user")
public class User implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983668061682635240L;
	
	private Integer uid;

	private String username;
	
	private String password;
	
	private String other;
	
	private Date ctime;

	public User(){
		
	}
	
    @Id @GeneratedValue
    @Column(name="uid", unique=true, nullable=false)
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="other")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	 @Column(name="ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	

}
