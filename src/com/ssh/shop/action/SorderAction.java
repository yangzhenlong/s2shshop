package com.ssh.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;

@Controller
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder> {
	
	/**
	 * 添加购物项到购物车
	 */
	public String addSorder(){
		System.out.println("---addSorder-----");
		//1.判断session中是否有购物车，如果没有，就创建一个
		if(session.get("forder") == null){
			session.put("forder", new Forder(new HashSet<Sorder>()));
		}
		Forder forder = (Forder) session.get("forder");
		//2.根据前台传过来的product.id，获取商品信息
		Product product = productService.get(model.getProduct().getId());
		//3.商品信息转化为购物项：product--->sorder ,判断购物项是否重复
		//4.购物项添加到购物车
		sorderService.addSorderToForder(forder, product);
		//5.计算购物项总价格，更新到购物车
		forder.setTotal(forderService.culTotal(forder));
		//6.购物车保存到session
		session.put("forder", forder);
		
		return "showCar";
	}
	
	/**
	 * 更新购物车的总价格
	 */
	public String update(){
		Forder forder = (Forder) session.get("forder");
		//更新购物车中数量
		sorderService.updateTotalPrice(forder, model);
		//计算更新后的总价格
		forder.setTotal(forderService.culTotal(forder));
		//保存新购物车
		session.put("forder", forder);
		//返回给ajax请求结果：流(新的总价格)
		inputStream = new ByteArrayInputStream(forder.getTotal().toString().getBytes());
		return "stream";
	}
	
	/**
	 * 查询销售量
	 */
	public String querySale(){
		//获取json列表：[["圣得西服",4],["罗蒙西服",1],["衫衫西服",102],["韩版女装",4],["雪地靴",1]]
		List<Object> jsonList = sorderService.querySale(model.getNumber());
		//将json列表放入值栈
		ActionContext.getContext().getValueStack().push(jsonList);
		return "jsonList";
	}
}
