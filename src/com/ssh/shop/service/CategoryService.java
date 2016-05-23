package com.ssh.shop.service;

import java.util.List;

import com.ssh.shop.model.Category;

public interface CategoryService extends BaseService<Category>{
	
	/**
	 * 关联查询管理员
	 * @param type
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Category> queryJoinAccount(String type,int page,int size);
	
	/**
	 * 根据条件查询总记录数
	 */
	public Long getCount(String type);
	/**
	 * 根据Ids删除category记录：批量删除
	 */
	public void deleteByIds(String ids);
	
	/**
	 * 根据热点查询分类
	 */
	public List<Category> queryByHot(boolean hot);
}
