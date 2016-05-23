package com.ssh.shop.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Product;
import com.ssh.shop.service.ProductService;

@SuppressWarnings("unchecked")
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	
	@Override
	public List<Product> queryJoinProduct(String name,int page,int size) {
		//该hql解决N+1问题，left join代表左外连接，fetch代表把查出来的c.account抓取到Category对象中
		String hql = "from Product p left join fetch p.category where p.name like :name";
		//HQL中from后面的是实体类名，不是表名
		return super.getSession().createQuery(hql)
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.setString("name", "%"+name+"%").list();//分页查询
	}

	@Override
	public Long getCount(String name) {
		String hql = "SELECT count(p) from Product p where p.name like :name";
		return (Long) super.getSession().createQuery(hql)
				.setString("name", "%" + name + "%")
				.uniqueResult();
	}

	@Override
	public List<Product> queryByCid(int cid) {
		String hql = "from Product p inner join fetch p.category " +
				"where p.commend=true and p.open=true and p.category.id = :cid order by p.date desc";
		//HQL中from后面的是实体类名，不是表名
		List<Product> list = super.getSession().createQuery(hql)
				.setFirstResult(0)
				.setMaxResults(4)//分页查询前4个
				.setInteger("cid", cid)
				.list();
		
		return list;
	}


}
