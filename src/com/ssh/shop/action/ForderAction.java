package com.ssh.shop.action;

import java.util.HashSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Status;
import com.ssh.shop.model.User;

@Controller
@Scope("prototype")
public class ForderAction extends BaseAction<Forder> {
	
	
	
	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}

	/**
	 * 购物车入库，订单入库
	 * 购物车和购物项级联入库
	 */
	public String save(){
		//获取session中的购物项
		model.setUser((User)session.get("user"));
		model.setStatus(new Status(1));//支付中
		forderService.save(model);
		
		//订单入库后，清空session中的购物信息
		session.put("oldForder", session.get("forder"));
		session.put("forder", new Forder());
		System.out.println("new forder--->"+session.get("forder"));
		return "bank";
	}
}
