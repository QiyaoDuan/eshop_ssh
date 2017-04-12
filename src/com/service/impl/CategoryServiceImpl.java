package com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Category;
import com.service.ICategoryService;

/**
 * @author Qiyao
 * 
 * @Date 2017.03.30
 * 
 * @Des Category service implement class
 * 
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements
		ICategoryService {

	@SuppressWarnings("unchecked")
	@Override  
    public List<Category> queryJoinAccount(String type, int page, int size) {  
        String hql = "from Category c left join fetch c.account where c.type like :type";  
        return  getSession().createQuery(hql)  
                .setString("type", "%" + type + "%")  
                .setFirstResult((page-1) * size) //从第几个开始显示  
                .setMaxResults(size) //显示几个  
                .list();  
    }    
	
	@Override  
    public Long getCount(String type) {  
        String hql = "select count(c) from Category c where c.type like :type";  
        return (Long) getSession().createQuery(hql)  
            .setString("type", "%" + type + "%")  
            .uniqueResult(); //返回一条记录:总记录数  
    }  
	
	//删除操作
	@Override  
    public void deleteByIds(String ids) {  
        String hql = "delete from Category c where c.id in (" + ids + ")";  
        getSession().createQuery(hql).executeUpdate();  
    } 
	
	@SuppressWarnings("unchecked")
	@Override  
    public List<Category> queryByHot(boolean hot) {  
        String hql = "from Category c where c.hot=:hot";  
        return getSession().createQuery(hql)  
            .setBoolean("hot", hot)  
            .list();  
    } 
	
}