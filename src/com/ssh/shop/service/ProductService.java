package com.ssh.shop.service;


import java.util.List;

import com.ssh.shop.model.Product;

public interface ProductService extends BaseService<Product>{
	
	/**
	 * 关联查询类别
	 * @param type
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Product> queryJoinProduct(String name,int page,int size);
	
	/**
	 * 根据条件查询总记录数
	 */
	public Long getCount(String name);
	
	/**
	 * 根据类别id查询商品，只查下前4个，展示在首页
	 */
	public List<Product> queryByCid(int cid);
}
