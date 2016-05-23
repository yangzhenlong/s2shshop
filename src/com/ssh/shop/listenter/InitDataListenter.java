package com.ssh.shop.listenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssh.shop.model.Category;
import com.ssh.shop.model.Product;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ProductService;
import com.ssh.shop.util.ProductTimerTask;

/**
 * 项目启动的时候，配置该监听器到web.xml，获取需要的数据，展示到首页
 * 
 * 监听器不能注入到spring中，所以通过WebApplicationContextUtils获取context对象
 * @author Administrator
 *
 */
public class InitDataListenter implements ServletContextListener {

	ApplicationContext context = null;
	ProductTimerTask productTimerTask = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		productTimerTask = (ProductTimerTask) context.getBean("productTimerTask");
		//设置上下文
		productTimerTask.setApplication(event);
		//设置守护线程，每隔一小时刷新一次数据
		new Timer(true).schedule(productTimerTask, 0, 60*60*1000);
	}

}
