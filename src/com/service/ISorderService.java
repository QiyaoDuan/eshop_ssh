package com.service;

import java.util.List;

import com.model.Forder;
import com.model.Product;
import com.model.Sorder;

public interface ISorderService extends IBaseService<Sorder> {  
    //添加购物项，返回新的购物车  
    public Forder addSorder(Forder forder, Product product);  
    //把商品数据转化为购物项  
    public Sorder productToSorder(Product product);
    
  //根据商品id和数量更新商品数量
    public Forder updateSorder(Sorder sorder, Forder forder);
    
    //查询热点商品的销售量
    public List<Object> querySale(int number);
} 