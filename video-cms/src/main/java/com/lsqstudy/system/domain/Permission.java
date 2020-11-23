package com.lsqstudy.system.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "per_name")
	private String perName;
	@Column(name = "per_code")
	private String perCode;
	private Integer available;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;


	@ManyToMany(targetEntity = Role.class)
	@JoinTable(
			//代表中间表名称
			name = "sys_role_permission",
			//中间表的外键对应到当前表的主键名称
			joinColumns = {@JoinColumn(name = "per_id", referencedColumnName = "id")},
			//中间表的外键对应到对方表的主键名称
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<Role> roles = new HashSet<>(0);

	public Permission() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerCode() {
		return perCode;
	}

	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Permission{" +
				"id=" + id +
				", perName='" + perName + '\'' +
				", perCode='" + perCode + '\'' +
				", available=" + available +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
