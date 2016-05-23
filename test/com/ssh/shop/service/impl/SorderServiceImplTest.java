package com.ssh.shop.service.impl;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.shop.service.SorderService;


@RunWith(SpringJUnit4ClassRunner.class)	//支持spring注解测试
@ContextConfiguration(locations="classpath:applicationContext-*.xml")	//添加spring配置文件
public class SorderServiceImplTest {
	
	@Resource
	public SorderService sorderService;
	
	@Test
	public void querySale(){
		System.out.println(JSONSerializer.toJSON(sorderService.querySale(5)));
	}
}
