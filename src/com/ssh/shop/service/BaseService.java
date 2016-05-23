package com.ssh.shop.service;

import java.util.List;

/**
 * 抽取类
 * @author Administrator
 *
 * @param <T> 公共类
 */
public interface BaseService<T> {

	public T get(int id);
	public void save(T t);
	public void update(T t);
	public void delete(int id);
	public List<T> query();
}
