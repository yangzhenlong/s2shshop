package com.ssh.shop.listenter;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 项目启动的时候，配置该监听器到web.xml，获取需要的数据，展示到首页
 * 
 * 监听器不能注入到spring中，所以通过WebApplicationContextUtils获取context对象
 * @author Administrator
 *
 */
public class InitDataListenter_old implements ServletContextListener {

	private ProductService productService = null;
	private CategoryService categoryService = null;
	ApplicationContext context = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 1.获取业务逻辑类productService查询商品信息
		//解决方案(1):不可取，会加载spring配置文件2次
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-*.xml");
//		productService = (ProductService) context.getBean("productService");
		
		//解决方案(2)：项目启动时，把spring配置文件通过监听器加载，存储在ServletContext中，通过WebApplicationContextUtils工具类可以获取
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
//		System.out.println("......"+context.getBeanDefinitionNames());
//		for(int i=0;i<context.getBeanDefinitionNames().length;i++){
//			System.out.println("..."+context.getBeanDefinitionNames()[i].toString());
//		}
		productService = (ProductService) context.getBean("productService");
		categoryService = (CategoryService) context.getBean("categoryService");
		
		//获取首页商品数据
		List<List<Product>> allProductList = new ArrayList<List<Product>>();
		List<Category> categoryList = categoryService.queryByHot(true);
		for (Category c : categoryList) {
			System.out.println("cid="+c.getId());
			List<Product> productList = productService.queryByCid(c.getId());
			allProductList.add(productList);
		}
		
		//把数据设置到内置对象中
		event.getServletContext().setAttribute("allProductList", allProductList);
	}

}
