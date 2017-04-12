package com.action;


import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Qiyao
 * 
 * @Date 2017.04.02
 * 
 * @Des Controller("sendAction")
 * 
 */
public class SendAction extends ActionSupport {

	private static final long serialVersionUID = 4924565814219341946L;

	public String execute(){
		return "send";
	}
}
