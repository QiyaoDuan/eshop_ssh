package com.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.service.IBaseService;

/**
 * @author Qiyao
 * 
 * @Date 2017.03.29
 * 
 * @Des base service class
 * 
 */
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements IBaseService<T> {

	@SuppressWarnings("rawtypes")
	private Class clazz; // clazz中存储了当前操作的类型，即泛型T

	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public BaseServiceImpl() {
		// 拿到泛型的参数类型
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		// 从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
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
		System.out.println(clazz.getSimpleName());
		String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";
		getSession().createQuery(hql) //
				.setInteger("id", id) //
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}
}
