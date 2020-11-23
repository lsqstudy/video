package com.lsqstudy.system.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_administrator")
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "administrator_name")
	private String administratorName;
	@Column(name = "real_name")
	private String realName;
	private String password;
	private Integer sex;// 0女、1男

	// 设置时区为上海时区，时间格式自己据需求定。
	// 查询出来的时间的数据库字段对应的实体类的属性上添加@JsonFormat
	// 接收前台数据的对象的属性上加@DateTimeFormat
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	// @JSONField(format="yyyy-MM-dd")
	private Date birthday;
	private String phone;
	private String weixin;
	private String qq;
	private String email;
	private String address;
	private Integer type; // 管理员类型
	private String interest;// 兴趣
	private String skill; // 个人技能
	private String position;// 担任什么角色：如CEO、经理
	@Column(name = "avatar_url")
	private String avatarUrl;// 头像的url
	private Integer available;// 0不可用，1可用
	private String salt;//密码加密使用的盐,这里使用的是UUID
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;


	/**
	 * @JoinColumn
	 *      作用：用于定义主键字段和外键字段的对应关系。
	 *      属性：
	 *     	name：指定外键字段的名称
	 *     	referencedColumnName：指定引用主表的主键字段名称
	 *     	unique：是否唯一。默认值不唯一
	 *     	nullable：是否允许为空。默认值允许。
	 *     	insertable：是否允许插入。默认值允许。
	 *     	updatable：是否允许更新。默认值允许。
	 *     	columnDefinition：列的定义信息。
	 */

	@ManyToMany(targetEntity = Role.class)
	@JoinTable(
			//代表中间表名称
			name = "sys_role_administrator",
			//中间表的外键对应到当前表的主键名称
			joinColumns = {@JoinColumn(name = "administrator_id", referencedColumnName = "id")},
			//中间表的外键对应到对方表的主键名称
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<Role> roles = new HashSet<>(0);


	public Administrator() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Administrator{" +
				"id=" + id +
				", administratorName='" + administratorName + '\'' +
				", realName='" + realName + '\'' +
				", password='" + password + '\'' +
				", sex=" + sex +
				", birthday=" + birthday +
				", phone='" + phone + '\'' +
				", weixin='" + weixin + '\'' +
				", qq='" + qq + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", type=" + type +
				", interest='" + interest + '\'' +
				", skill='" + skill + '\'' +
				", position='" + position + '\'' +
				", avatarUrl='" + avatarUrl + '\'' +
				", available=" + available +
				", salt='" + salt + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
