package com.ssh.shop.model;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;/* 类别编号,自动增长 */
	private String type;/* 类别名称 */
	private Boolean hot;/* 类别是否为热点类别,热点类别才有可能显示在首页 */

	private Account account;

	/** default constructor */
	public Category() {
	}
	
	public Category(Integer id, String type, Boolean hot) {
		super();
		this.id = id;
		this.type = type;
		this.hot = hot;
	}

	/** full constructor */
	public Category(String type, Boolean hot) {
		this.type = type;
		this.hot = hot;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot + "]";
	}
}