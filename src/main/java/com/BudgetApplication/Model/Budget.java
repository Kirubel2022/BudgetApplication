package com.BudgetApplication.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Budget")
public class Budget {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long budgetId;
	
	
	float balance;
	
	String remark;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(nullable = false,name ="transactionDate")
	private Date budgetDate;
	
	@PrePersist
	private void onCreate() {
		budgetDate = new Date();
	}
	
	
	
	

	public Budget(Long budgetId, float balance, String remark, Date budgetDate) {
		super();
		this.budgetId = budgetId;
		this.balance = balance;
		this.remark = remark;
		this.budgetDate = budgetDate;
	}


	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getBudgetId() {
		return budgetId;
	}


	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}


	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getBudgetDate() {
		return budgetDate;
	}


	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}


	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", balance=" + balance + ", remark=" + remark + ", budgetDate="
				+ budgetDate + ", getBudgetId()=" + getBudgetId() + ", getBalance()=" + getBalance() + ", getRemark()="
				+ getRemark() + ", getBudgetDate()=" + getBudgetDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

	
	
	

	

}
