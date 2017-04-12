package com.service;

import java.math.BigDecimal;

import com.model.Forder;

public interface IForderService extends IBaseService<Forder> {  
	//计算购物总价格
	public BigDecimal cluTotal(Forder forder);
	//根据订单编号，更新订单状态
	public void updateStatusById(int id, int sid);
}