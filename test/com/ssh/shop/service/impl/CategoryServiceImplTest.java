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
import com.ssh.shop.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)	//支持spring注解测试
@ContextConfiguration(locations="classpath:applicationContext-*.xml")	//添加spring配置文件
public class CategoryServiceImplTest {

	@Resource
	private CategoryService categoryService;  //实现接口，Proxy代理
	
	@Resource
	private CategoryServiceImpl2 categoryService2; // 没有实现接口，CGLIB代理
	
	@Test
	public void testGet() {
		categoryService2.save();
		System.out.println("get---"+categoryService.get(5));
	}

	@Test
	public void testSave() {
		categoryService.save(new Category("测试的", false));
	}

	@Test
	public void testUpdate() {
		categoryService.update(new Category(2, "222", true));
	}

	@Test
	public void testDelete() {
		categoryService.delete(4);
	}

	@Test
	public void testQuery() {
		//默认懒加载，lazy="true"，抛异常：org.hibernate.LazyInitializationException: could not initialize proxy - no Session
		List<Category> list = categoryService.query();
		for(Category c : list){
			System.out.println(c);
		}
	}
	@Test
	public void queryJsonAccount() {
		List<Category> list = categoryService.queryJoinAccount("",2,3);
		for(Category c : list){
			System.out.println(c+"---"+c.getAccount());
		}
	}
	@Test
	public void testDeleteByIds() {
		categoryService.deleteByIds("1,2,3,4,5,6,7");
	}

}
