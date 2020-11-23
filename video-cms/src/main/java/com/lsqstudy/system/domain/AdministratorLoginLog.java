package com.lsqstudy.system.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;

@Entity
@Table(name = "sys_administrator_login_log")
public class AdministratorLoginLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "login_name")
	private String loginName;
	@Column(name = "real_name")
	private String realName;
	@Column(name = "login_ip")
	private String loginIp;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 设置显示在页面的json时间格式
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "login_time")
	private Date loginTime;

	public AdministratorLoginLog() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "AdministratorLoginLog{" +
				"id=" + id +
				", loginName='" + loginName + '\'' +
				", realName='" + realName + '\'' +
				", loginIp='" + loginIp + '\'' +
				", loginTime=" + loginTime +
				'}';
	}
}