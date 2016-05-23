package com.ssh.shop.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SendAction extends ActionSupport{
	public SendAction(){
		System.out.println("----sendAction init-----------");
	}
	public String execute(){
		System.out.println("-------> send execute---");
		return "send";
	}
}
