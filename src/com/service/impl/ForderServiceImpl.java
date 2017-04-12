package com.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.model.Forder;
import com.model.Sorder;
import com.service.IForderService;

@Service("forderService")  
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements IForderService {  
  
    @Override  
    public BigDecimal cluTotal(Forder forder) {  
  
    	BigDecimal total = new BigDecimal(0.00);
		for(Sorder sorder : forder.getSorders()) {
			total = total.add(sorder.getPrice().multiply(new BigDecimal(sorder.getNumber())));
		}
		return total;
    }  
    
    @Override
	public void updateStatusById(int id, int sid) {
		String hql = "update Forder f set f.status.id=:sid where f.id=:id";
		getSession().createQuery(hql)
			.setInteger("sid", sid)
			.setInteger("id", id)
			.executeUpdate();
	}
}