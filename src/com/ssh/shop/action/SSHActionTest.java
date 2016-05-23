package com.ssh.shop.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.shop.model.Category;
import com.ssh.shop.service.CategoryService;

@Controller
public class SSHActionTest extends ActionSupport {
	private String category;
	private String hot;
	
	@Resource
	private CategoryService categoryService;
	
//	public void setCategoryService(CategoryService categoryService) {
//		this.categoryService = categoryService;
//	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	
	public String addCategory(){
		categoryService.save(new Category(category, Integer.parseInt(hot)==1?true:false));
		return "ok";
	}
}
