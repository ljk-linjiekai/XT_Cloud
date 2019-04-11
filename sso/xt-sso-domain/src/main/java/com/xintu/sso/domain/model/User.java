package com.xintu.sso.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
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

	/*
	 * 主键
	 */
	private Long id;
	/*
	 * 用户名
	 */
	private String username;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * 手机
	 */
	private String phone;
	/*
	 * 邮箱
	 */
	private String email;
	/*
	 * 创建时间
	 */
	private Date created;
	/*
	 * 更新时间
	 */
	private Date updated;

}
