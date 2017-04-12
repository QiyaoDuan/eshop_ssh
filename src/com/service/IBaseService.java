package com.service;

import java.util.List;

/**
 * @Author Qiyao
 * @Date 2017-03-28
 * @Version 1.0.1
 */
public interface IBaseService<T> {
	public void save(T t);

	public void update(T t);

	public void delete(int id);

	public T get(int id);

	public List<T> query();
}
