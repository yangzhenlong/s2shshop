package com.ssh.shop.service.impl;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Category;


/**
 * 代理模式为 CGLIB代理
 * 测试CGLIB代理
 * @author Administrator
 *
 */
@Service("categoryService2")
public class CategoryServiceImpl2{
	
	public void save() {
		System.out.println("---CGLIB----");
	}
	
}
