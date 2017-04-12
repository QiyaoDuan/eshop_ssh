package com.service;

import java.util.List;

import com.model.Product;

/**
 * @Author Qiyao
 * @Date 2017-04-07
 * @Version 1.0.1
 */
public interface IProductService extends IBaseService<Product> {

	//使用商品的名称查询商品信息，级联类别  
    public List<Product> queryJoinCategory(String type, int page, int size);   
    
    //根据关键字查询总记录数  
    public Long getCount(String type);
    
    //根据ids删除多条记录  
    public void deleteByIds(String ids);  
    
    //根据热点类别查询推荐商品（仅仅查询前4个）  
    public List<Product> querByCategoryId(int cid);
}
