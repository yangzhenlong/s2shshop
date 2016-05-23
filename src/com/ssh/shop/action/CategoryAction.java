package com.ssh.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;



import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Category;

@Controller
public class CategoryAction extends BaseAction<Category>{

	public String queryJoinAccount(){
		System.out.println("type-->" + model.getType());
		pageMap = new HashMap<String,Object>();
		//查询分类
		List<Category> cateGoryList = 
				super.categoryService.queryJoinAccount(model.getType(), super.page, super.rows);
		System.out.println(cateGoryList.size()+":size");
		pageMap.put("rows", cateGoryList);
		//查询总记录数
		Long count = super.categoryService.getCount(model.getType());
		pageMap.put("total", count);
		
		System.out.println("total="+count);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		System.out.println("ids:"+ids);
		//Integer.parseInt("sss");  //检查删除失败的前台功能
		categoryService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";//返回流的格式 给前台
	}
	
	public void save(){
		categoryService.save(model);
	}
	public void update(){
		categoryService.update(model);
	}
	public String query(){
		jsonList = categoryService.query();
		return "jsonList";
	}
}
