package com.ssh.shop.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.shop.model.Account;
import com.ssh.shop.model.Category;
import com.ssh.shop.service.AccountService;
import com.ssh.shop.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)	//支持spring注解测试
@ContextConfiguration(locations="classpath:applicationContext-*.xml")	//添加spring配置文件
public class AccountServiceImplTest {

	@Resource
	private AccountService accountService;
	
	@Test
	public void testGet() {
		System.out.println("get---"+accountService.get(3));
	}

	@Test
	public void testSave() {
		accountService.save(new Account("yzl", "管理员", "111111"));
	}

	@Test
	public void testUpdate() {
		accountService.update(new Account(2,"user","客服A","user"));
	}

	@Test
	public void testDelete() {
		accountService.delete(4);
	}

	@Test
	public void testQuery() {
		List<Account> list = accountService.query();
		for(Account a : list){
			System.out.println(a);
		}
	}

}
