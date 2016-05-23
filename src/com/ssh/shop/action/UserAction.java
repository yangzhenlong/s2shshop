package com.ssh.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.User;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	public String login(){
		model = userService.login(model);
		if(model == null){
			session.put("error", "登录失败");
			return "ulogin";
		}else{
			//保存user
			session.put("user", model);
			System.out.println((User)session.get("user"));
			//根据session中是否有goUrl，进行跳转
			System.out.println("goUrl--->"+session.get("goUrl") + ",user="+session.get("user"));
			if(session.get("goUrl")!=null){
				return "goUrl";
			}else{
				return "index";
			}
		}
	}
}
