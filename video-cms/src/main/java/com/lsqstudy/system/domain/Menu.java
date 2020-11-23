package com.lsqstudy.system.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 节点id
	private Integer pid; // 父节点
	private String title; // 菜单标题
	private String href; // 菜单连接
	private Integer spread; // 是否展开
	private String target; // 打开方式
	private String icon; // 图标
	private Integer available; // 0不可用，1 可用
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;

	@ManyToMany(targetEntity = Role.class)
	@JoinTable(
			//代表中间表名称
			name = "sys_role_menu",
			//中间表的外键对应到当前表的主键名称
			joinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
			//中间表的外键对应到对方表的主键名称
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<Role> roles = new HashSet<>(0);

	public Menu() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getSpread() {
		return spread;
	}

	public void setSpread(Integer spread) {
		this.spread = spread;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
		return "Menu{" +
				"id=" + id +
				", pid=" + pid +
				", title='" + title + '\'' +
				", href='" + href + '\'' +
				", spread=" + spread +
				", target='" + target + '\'' +
				", icon='" + icon + '\'' +
				", available=" + available +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}