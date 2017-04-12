package com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Product;
import com.service.IProductService;

/**
 * @Author Qiyao
 * @Date 2017-04-07
 * @Version 1.0.1
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {

	@SuppressWarnings("unchecked")
	@Override  
    public List<Product> queryJoinCategory(String name, int page, int size) {  
        String hql = "from Product p left join fetch p.category where p.name like :name";  
        return getSession().createQuery(hql)  
                .setString("name", "%" + name + "%")  
                .setFirstResult((page-1) * size) //从第几个开始显示  
                .setMaxResults(size) //显示几个  
                .list();  
    }  
      
    @Override  
    public Long getCount(String name) {  
        String hql = "select count(p) from Product p where p.name like :name";  
        return (Long) getSession().createQuery(hql)  
            .setString("name", "%" + name + "%")  
            .uniqueResult(); //返回一条记录:总记录数  
    }  
    
    @Override  
    public void deleteByIds(String ids) {  
        String hql = "delete from Product p where p.id in (" + ids + ")";  
        getSession().createQuery(hql).executeUpdate();  
    }  
    
    @SuppressWarnings("unchecked")
	@Override  
    public List<Product> querByCategoryId(int cid) {  
        String hql = "from Product p join fetch p.category "  
                + "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";  
        return getSession().createQuery(hql)  
            .setInteger("cid", cid)  
            .setFirstResult(0)  
            .setMaxResults(4)  
            .list();  
    } 
}