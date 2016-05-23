package com.ssh.shop.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;

import com.ssh.shop.model.Product;

public class ProductAction extends BaseAction<Product>{
	
	public String queryJoinCategory(){
		pageMap = new HashMap<String,Object>();
		//查询分类
		List<Product> productList = 
				super.productService.queryJoinProduct(model.getName(), super.page, super.rows);
		pageMap.put("rows", productList);
		//查询总记录数
		Long count = super.productService.getCount(model.getName());
		pageMap.put("total", count);
		
		return "jsonMap";
	}
	
	public void save() throws IOException{
		//文件上传
		String pic = uploadUtil.uploadFile(fileUpload);
		model.setPic(pic);
		//执行更新
		productService.save(model);
	}
	public void update(){
		productService.update(model);
	}
	
	public String get(){
		Product product = productService.get(model.getId());
		request.put("product", product);
		return "detail";
	}
}
