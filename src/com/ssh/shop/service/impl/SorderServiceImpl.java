package com.ssh.shop.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;
import com.ssh.shop.service.ForderService;
import com.ssh.shop.service.SorderService;

@SuppressWarnings("unchecked")
@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements
		SorderService {

	@Override
	public Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}

	@Override
	public void addSorderToForder(Forder forder, Product product) {
		Sorder newSorder = productToSorder(product);
		boolean isRepeat = false;
		//判断购物车中的购物项是否有重复
		for(Sorder oldSorder : forder.getSorderSet()){
			//判断新购物项中的商品id是否等于旧购物项商品id  相等：数量相加
			if(oldSorder.getProduct().getId().equals(newSorder.getProduct().getId())){
				oldSorder.setNumber(oldSorder.getNumber() + newSorder.getNumber());
				//设置重复标识为true
				isRepeat = true;
				break;
			}
		}
		
		//当isRepeat=false时，说明没有重复项
		if(!isRepeat){
			//级联入库，购物项中添加外键forder.id，此时forder.id=null,但是入库时先入库购物车，再入库购物项，会自动添加外键forder.id
			newSorder.setForder(forder);
			forder.getSorderSet().add(newSorder);
		}
		
	}

	@Override
	public void updateTotalPrice(Forder forder, Sorder newSorder) {
		
		for(Sorder oldSorder : forder.getSorderSet()){
			//判断新购物项中的商品id是否等于旧购物项商品id  相等：更新购物车数量
			if(oldSorder.getProduct().getId().equals(newSorder.getProduct().getId())){
				//更新集合中的商品number
				oldSorder.setNumber(newSorder.getNumber());
			}
		}
	}

	@Override
	public List<Object> querySale(int number) {
		String hql = "SELECT s.name, sum(s.number) FROM Sorder s JOIN s.product GROUP BY s.product.id";//返回一个Object[Sorder,Product]
		return getSession().createQuery(hql)
			.setFirstResult(0)
			.setMaxResults(number)
			.list();
	}


}
