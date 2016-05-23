package com.ssh.shop;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.shop.model.Category;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.impl.CategoryServiceImpl;

/**
 * 采用spring注解，仅仅支持spring3.1及其以后的版本
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)	//支持spring注解测试
@ContextConfiguration(locations="classpath:applicationContext-*.xml")	//添加spring配置文件
public class SSHTest {
	@Resource //注入bean对象date，如果没有这个注解，下面的值为null
	private Date mydate;
	@Resource
	private CategoryService categoryService;
	
	@Test	//测试Spring IOC功能
	public void springIOC(){
		System.out.println(mydate);
	}
	
	@Test //测试hibernate功能
	public void hibernate(){
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.save(new Category(1,"冷门产品", false));
	}
	
	@Test
	public void springAndSpring(){
		categoryService.update(new Category(3,"冷门产品", false));
	}
}
