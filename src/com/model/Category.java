package com.model;

import javax.persistence.*;

/**
 * @Author Qiyao
 * @Date 2017-03-28
 * @Version 1.0.1
 */
@Entity
//@Table(name = "category", catalog = "shop")
public class Category implements java.io.Serializable {


	private static final long serialVersionUID = -2257887565826000039L;
	// Fields
	private Integer id;
	private Account account;
	private String type;
	private Boolean hot;

	// Constructors

	/** default constructor */
	public Category() {
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", account=" + account + ", type=" + type
				+ ", hot=" + hot + "]";
	}

	/** full constructor */
	public Category(Integer id, Account account, String type, Boolean hot) {
		super();
		this.id = id;
		this.account = account;
		this.type = type;
		this.hot = hot;
	}

	public Category(Integer id, String type, Boolean hot) {
		super();
		this.id = id;
		this.type = type;
		this.hot = hot;
	}

	public Category(String type, Boolean hot) {
		super();
		this.type = type;
		this.hot = hot;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "hot")
	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

}