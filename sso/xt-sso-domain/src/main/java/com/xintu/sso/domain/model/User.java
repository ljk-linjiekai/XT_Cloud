package com.xintu.sso.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_user")
@Data
public class User implements  Serializable {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = -5384516592200628576L;
	
	private Long id;
	private String username;
	
	private String password;
	
	private String phone;
	
	private String email;
	private Date created;
	private Date updated;

	/** 指定主键 */
	/*@Override
	protected Serializable pkVal() {  //一定要指定主键哦
		return this.id;
	}*/
/*
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
*/
}
