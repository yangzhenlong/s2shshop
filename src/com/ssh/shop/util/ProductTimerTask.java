package com.ssh.shop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;

import org.springframework.stereotype.Component;

import com.ssh.shop.model.Category;
import com.ssh.shop.model.Product;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ProductService;

/**
 * 定时加载首页商品数据
 * 当添加了新商品时，定时刷新servletContext中的allProductList对象
 * @author Administrator
 *
 */
@Component  //组件
public class ProductTimerTask extends TimerTask {
	@Resource
	private ProductService productService = null;
	@Resource
	private CategoryService categoryService = null;
	
	private ServletContextEvent application = null;//项目上下文
	/**
	 * 设置上下文：监听器中调用此方法
	 * @param application
	 */
	public void setApplication(ServletContextEvent application) {
		this.application = application;
	}
	
	@Override
	public void run() {
		System.out.println("get index data start...");
		//获取首页商品数据
		List<List<Product>> allProductList = new ArrayList<List<Product>>();
		List<Category> categoryList = categoryService.queryByHot(true);
		for (Category c : categoryList) {
			List<Product> productList = productService.queryByCid(c.getId());
			allProductList.add(productList);
		}
		
		application.getServletContext().setAttribute("allProductList", allProductList);
	}
	
	/**
	 * 测试守护线程
	 * 
	 * new Timer(true):守护线程，例如timer为tomcat的守护线程，当tomcat关闭，该线程也关闭；
	 * @param args
	 */
	public static void main(String[] args) {
		new Timer(false).schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("---run---");
			}
		}, 0, 2000);
	}
}
