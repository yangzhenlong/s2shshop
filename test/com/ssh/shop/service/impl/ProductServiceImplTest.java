package com.ssh.shop.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.shop.model.Category;
import com.ssh.shop.model.Product;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)	//支持spring注解测试
@ContextConfiguration(locations="classpath:applicationContext-*.xml")	//添加spring配置文件
public class ProductServiceImplTest {

	@Resource
	private ProductService productService;  //实现接口，Proxy代理
	
	@Test
	public void queryByCid(){
		System.out.println("111111");
		for(Product p : productService.queryByCid(1)){
			System.out.println("---"+p);
		}
	}
}
