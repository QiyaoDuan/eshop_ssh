package com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.model.Forder;
import com.model.Product;
import com.model.Sorder;
import com.service.ISorderService;

@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements
		ISorderService {

    @Override  
    public Forder addSorder(Forder forder, Product product) {  
        boolean isHave = false; //用来标记有没有重复购物项  
        //拿到当前的购物项  
        Sorder sorder = productToSorder(product);  
        //判断当前购物项是否重复，如果重复，则添加数量即可  
        for(Sorder old : forder.getSorders()) {  
            if(old.getProduct().getId().equals(sorder.getProduct().getId())) {  
                //购物项有重复，添加数量即可  
                old.setNumber(old.getNumber() + sorder.getNumber());  
                isHave = true;  
                break;  
            }  
        }  
        //当前购物项在购物车中不存在，新添加即可  
        if(!isHave) { 
        	sorder.setForder(forder);  
            forder.getSorders().add(sorder);  
        }  
        return forder;  
    }  
  
    @Override  
    public Sorder productToSorder(Product product) {  
        Sorder sorder = new Sorder();  
        sorder.setName(product.getName());  
        sorder.setNumber(1);  
        sorder.setPrice(product.getPrice());  
        sorder.setProduct(product);  
        return sorder;  
    }  
    
    //修改订单里商品数量的时候更新其它数据
    @Override
    public Forder updateSorder(Sorder sorder, Forder forder) {
        for(Sorder temp : forder.getSorders()) {
            if(temp.getProduct().getId().equals(sorder.getProduct().getId())) {
                temp.setNumber(sorder.getNumber());
            }
        }
        return forder;
    }
    
    //查询销售数据，
    @Override
    public List<Object> querySale(int number) {
        //不用fecth查出来的就是两项
        String hql = "select s.name, sum(s.number) from Sorder s join s.product group by s.product.id";
        return getSession().createQuery(hql) //
            .setFirstResult(0) //
            .setMaxResults(number) //
            .list();
    }
}
