package com.ssh.shop.action;




import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Account;

@Controller
public class AccountAction extends BaseAction<Account>{

	public String query(){
		System.out.println("query account:"+model);
		jsonList = accountService.query();
		return "jsonList";
	}
	
}
