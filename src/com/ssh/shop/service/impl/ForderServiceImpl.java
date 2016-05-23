package com.ssh.shop.service.impl;


import org.springframework.stereotype.Service;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Sorder;
import com.ssh.shop.service.ForderService;

@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements
		ForderService {


	@Override
	public double culTotal(Forder forder) {
		double tatol = 0.0;
		for(Sorder sorder : forder.getSorderSet()){
			tatol += sorder.getNumber() * sorder.getPrice();
		}
		return tatol;
	}


}
