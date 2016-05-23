package com.ssh.shop.model;

/**
 * java.io.Serializable：tomcat容器关闭后，session不会销毁，会将对象序列化到硬盘
 * 		容器再次启动时，会通过serialVersionUID从硬盘找到该对象，加载到内存
 * Sorder entity. @author MyEclipse Persistence Tools
 */
public class Sorder implements java.io.Serializable {
	
	/**
	 * 序列号ID
	 * 序列化：将对象从内存存储到硬盘（tomcat的work目录下，生成一个SESSION的文件进行存储）
	 * 反序列化：将对象从硬盘加载到内存（加载后，SESSION的文件会消失）
	 */
	private static final long serialVersionUID = 6274569339070248899L;
	// Fields

	private Integer id;
	private String name;
	private Double price;
	private Integer number;
	private Product product;
	private Forder forder;//暂时不需要


	/** default constructor */
	public Sorder() {
	}

	/** minimal constructor */
	public Sorder(Integer number) {
		this.number = number;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Forder getForder() {
		return forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}
	

}