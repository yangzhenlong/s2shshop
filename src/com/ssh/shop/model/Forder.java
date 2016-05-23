package com.ssh.shop.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Forder entity. @author MyEclipse Persistence Tools
 * 购物车
 */

public class Forder implements java.io.Serializable {

	
	/**
	 * 带购物项的购物车
	 * @param sorderSet
	 */
	public Forder(Set<Sorder> sorderSet) {
		super();
		this.sorderSet = sorderSet;
	}

	// Fields

	private Integer id;
	private String name;
	private String phone;
	private String remark;
	private Timestamp date;
	private Double total;
	private String post;
	private String address;
	
	/**
	 * 订单状态
	 */
	private Status status;
	
	/**
	 * 收货用户
	 */
	private User user;
	
	/**
	 * 购物项集合
	 */
	private Set<Sorder> sorderSet = new HashSet<Sorder>();

	public Set<Sorder> getSorderSet() {
		return sorderSet;
	}

	public void setSorderSet(Set<Sorder> sorderSet) {
		this.sorderSet = sorderSet;
	}

	/** default constructor */
	public Forder() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Forder [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", remark=" + remark + ", date=" + date + ", total=" + total
				+ ", post=" + post + ", address=" + address + ", status="
				+ status + ", user=" + user + ", sorderSet=" + sorderSet + "]";
	}

	
}