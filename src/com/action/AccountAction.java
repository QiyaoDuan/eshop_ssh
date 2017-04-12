package com.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Account;

/**
 * @author Qiyao
 * 
 * @Date 2017.03.30
 * 
 * @Des Category action class
 * 
 */
@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction<Account> {

	private static final long serialVersionUID = -3714817807922076720L;

	public String query() {
		jsonList = accountService.query();
		return "jsonList";
	}
}
