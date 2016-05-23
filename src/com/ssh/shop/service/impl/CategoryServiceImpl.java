package com.ssh.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Category;
import com.ssh.shop.service.CategoryService;

@SuppressWarnings("unchecked")
@Service("categoryService")  //这里不用配置parent="baseService"，因为在类里面已经有extends..
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	
	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		//该hql解决N+1问题，left join代表左外连接，fetch代表把查出来的c.account抓取到Category对象中
		String hql = "from Category c left join fetch c.account where c.type like :type";
		//HQL中from后面的是实体类名，不是表名
		return super.getSession().createQuery(hql)
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.setString("type", "%"+type+"%").list();//分页查询
	}

	@Override
	public Long getCount(String type) {
		String hql = "SELECT count(c) from Category c where c.type like :type";
		return (Long) super.getSession().createQuery(hql)
				.setString("type", "%" + type + "%")
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		//这种形式的sql语句是... id in ("1,2,3,4..."),只能删除引号中的第一条数据
		//String hql = "DELETE from Category c where c.id in ids:ids";
		String hql = "DELETE from Category c where c.id in (" + ids + ")";//where id in (1 , 2 , 3 , 4 , 5 , 6 , 7)
		getSession().createQuery(hql)
			.executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql = "from Category c where c.hot like :hot";
		//HQL中from后面的是实体类名，不是表名
		List<Category> list = super.getSession().createQuery(hql)
				.setBoolean("hot", hot).list();
		return list;
	}
	
	

	
}
