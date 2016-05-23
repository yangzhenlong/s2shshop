package com.ssh.shop.service;



import com.ssh.shop.model.Forder;

public interface ForderService extends BaseService<Forder>{
	
	public double culTotal(Forder forder);
	
}
