package com.ssh.shop.service;



import java.util.List;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder>{
	
	/**
	 * 商品信息转化为购物项：product--->sorder
	 */
	public Sorder productToSorder(Product product);
	
	/**
	 * 判断购物项是否重复
	 * 购物项添加到购物车
	 * 
	 * forder: 购物车
	 * newSorder: 要添加到购物车的新购物项
	 */
	public void addSorderToForder(Forder forder, Product product);
	
	/**
	 * 更新总价格
	 * 购物车数量变化，使得总价格也变化
	 * 
	 * forder：购物车
	 * newSorder：修改过价格的购物项
	 */
	public void updateTotalPrice(Forder forder, Sorder newSorder);
	
	/**
	 * 查询销售量
	 * number:多少个商品的销售量信息
	 */
	public List<Object> querySale(int number);
}
