package com.ssh.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ssh.shop.model.Category;
import com.ssh.shop.service.BaseService;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.util.HibernateSessionFactory;

@SuppressWarnings("unchecked")
@Service("baseService") //注解，相当于 bean id="baseService" class=...
@Lazy(true)  //相当于lazy-init="true"
public class BaseServiceImpl<T> implements BaseService<T> {
	
	/**
	 * 这里定义一个class对象，是为了设置给公共类T
	 */
	private Class clazz;
	//这个clazz是null，怎么得到具体是哪个类呢？找个测试类extends当前类，然后写个main方法然后new该测试类就懂了
	
	/**
	 * 构造方法
	 * 获取具体的泛型类
	 * 当该类被调用后，可以获取是哪个具体类调用的
	 */
	
	public BaseServiceImpl(){
		System.out.println("this:当前调用该构造的对象："+ this);
		System.out.println("当前this对象的父类："+ this.getClass().getSuperclass());
		System.out.println("当前this对象的父类<泛型信息>："+ this.getClass().getGenericSuperclass());
		
		//获取this父类<泛型信息>中泛型类参数
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取某个具体的泛型类T
		clazz = (Class) paramType.getActualTypeArguments()[0];
		
	}
	
	@Resource  //没有指定名称，默认为属性名称 相当于<property name="sessionFactory" ref="sessionFactory"/>
	protected SessionFactory sessionFactory;
	
	/**   //使用Resource注解后，下面的set方法可以注释了。使用的原理是反射
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	
	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		//2中方法，推荐第二种
		//one: if obj!=null,delete it
//		Object obj = getSession().get(Category.class, id);
//		if(obj!=null){
//			getSession().delete(obj);
//		}
		//two: execute hql
		String hql = "delete "+clazz.getSimpleName()+" where id=:id";
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}
	
	/**
	 * 查询所有
	 */
	@Override
	public List<T> query() {
		String hql = "FROM "+ clazz.getSimpleName();
		return getSession().createQuery(hql).list();
		
	}
	
	
	
	
//	@Override
//	public void save(Category category) {
//		//通过工具类获取session
//		Session session = HibernateSessionFactory.getSession();
//		try{
//			//手动事务开始		这里的手动是和后面整合后用spring管理事务的对比
//			session.getTransaction().begin();
//			session.save(category);
//			//事务结束，手动提交
//			session.getTransaction().commit();
//		}catch(Exception e){
//			//发生异常，回滚事务
//			session.getTransaction().rollback();
//			throw new RuntimeException(e);
//		}finally{
//			//使用工具类关闭session
//			HibernateSessionFactory.closeSession();
//		}
//	}
//	
//	/**
//	 * 不用事务，因为使用了spring声明书事务管理
//	 * @param category
//	 */
//	@Override
//	public void update(Category category) {
//		getSession().update(category);
//	}

}
