package com.ssh.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.shop.model.fileupload.FileUpload;
import com.ssh.shop.service.AccountService;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ForderService;
import com.ssh.shop.service.ProductService;
import com.ssh.shop.service.SorderService;
import com.ssh.shop.service.UserService;
import com.ssh.shop.util.UploadUtil;

/**
 * 设置jsp中使用到的session、request、application
 * 其他action继承该类，可以使用这些对象，而不用再重复地自己创建
 * 
 * 注意：在项目启动的时候，struts过滤器会自动把相应的内置对象，
 * 	和内置对象对应的sessionMap、requestMap、applicateMap，
 * 	存储到ActionContext和值栈中，
 * 	实现的拦截器为servletConfig(org.apache.struts2.interceptor.ServletConfigInterceptor)
 * 
 * Struts执行流程：创建action ---> 调用拦截器  ---> 拦截器访问成功后调用action的方法
 * 
 * ServletConfigInterceptor源代码中有下面代码：（判断实现什么接口，就注入什么对象）
 * 	if (action instanceof ApplicationAware) //如果action实现了ApplicationAware接口
			((ApplicationAware) action).setApplication(context.getApplication());
	if (action instanceof SessionAware)
		((SessionAware) action).setSession(context.getSession());
	if (action instanceof RequestAware)
		((RequestAware) action).setRequest((Map) context.get("request"));
 * 
 * 加入的ModelDriven泛型的使用方法
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Controller
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements SessionAware,RequestAware,ApplicationAware,ModelDriven<T>{

	//批量删除的记录的id数组
	protected String ids;
	
	protected T model;
	
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected Map<String, Object> applicate;
	
	/**
	 * 获取EasyUI中的参数page、rows 固定写法，不能修改。 可以用火狐debug中看到。
	 */
	protected Integer page;
	protected Integer rows;
	
	/**
	 * 存储分页用到的数据，包括记录和总记录数
	 */
	protected Map<String, Object> pageMap = null;
	/**
	 * 流
	 */
	protected InputStream inputStream;
	/**
	 * List
	 */
	List<T> jsonList = null;
	

	@Resource
	protected CategoryService categoryService;
	@Resource
	protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource
	protected UploadUtil uploadUtil;
	@Resource
	protected ForderService forderService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected UserService userService;
	
	//用户文件上传(使用方法见product/save.jsp中图片上传)
	protected FileUpload fileUpload;
	/**
	 * 获取T的具体类型
	 * 实现ModelDriven接口，必须要写此方法：获取实例
	 */
	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
//		System.out.println("model:"+model);
		return model;
	}
	
	
	@Override
	public void setApplication(Map<String, Object> applicate) {
		this.applicate = applicate;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 只写get方法，用于将获取的数据传输给前台
	 * @return
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * 只写get方法，用于将获取的数据传输给前台
	 * @return
	 */
	public List<T> getJsonList() {
		return jsonList;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	
}
