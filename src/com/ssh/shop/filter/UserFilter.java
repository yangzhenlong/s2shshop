package com.ssh.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssh.shop.model.User;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//转换servlet对象为httpServlet对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		System.out.println("filter user:"+(User)req.getSession().getAttribute("user"));
		//判断当前session是否有用户信息
		if(req.getSession().getAttribute("user") == null){
			//1.保存用户要去的地址，为了登陆后跳转到该页面
			String goUrl = req.getServletPath();// /user/confirm.jsp
			String reqParam = req.getQueryString(); //请求参数 name=zhangsan&pwd=0000
			if(reqParam !=null && !reqParam.equals("")){
				goUrl = goUrl + "?" + reqParam;
			}
			req.getSession().setAttribute("goUrl", goUrl);
			
			//2.非法请求，跳转到登录页面
			req.getSession().setAttribute("error", "非法请求，请先登录");
			res.sendRedirect(req.getContextPath() + "/ulogin.jsp");
		}else{
			//如果有下一个过滤器则跳转，否则直接跳转到目标页面
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
