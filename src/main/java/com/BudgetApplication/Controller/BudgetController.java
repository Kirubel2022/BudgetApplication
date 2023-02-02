package com.BudgetApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.BudgetApplication.Model.Budget;
import com.BudgetApplication.Model.CreditCards;
import com.BudgetApplication.Model.Expense;
import com.BudgetApplication.Model.Income;
import com.BudgetApplication.Model.Users;
import com.BudgetApplication.Repository.ExpenseRepository;
import com.BudgetApplication.Service.BudgetApplicationServiceImpl;



@RestController
@RequestMapping("/xpay")
public class BudgetController {
	
	@Autowired
	BudgetApplicationServiceImpl service;
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	
	
	//Add User to Budget App
	
	@RequestMapping(value = "/saveUserProfile/{userId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Users> getUser(@PathVariable(value = "userId") Long userId)  {
		
		return ResponseEntity.ok(service.saveUserProfile(userId));

 
	}
	
	
	// Add Income
	@RequestMapping(value = "/AddIncome", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Income> addingIncomes(@RequestBody String  income)  {
	
		return ResponseEntity.ok(service.SaveIncome(income));

 
	}
	
	
	
	
	//Get Balance 
	
	@RequestMapping(value = "/getBalance", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getBalanceAmount()  {
	
		return ResponseEntity.ok(service.getbalance());

 
	}
	
	//Add Expense
	
	@RequestMapping(value = "/AddExpense", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Expense> addExpense(@RequestBody String expense)  {
	
		return ResponseEntity.ok(service.addExpenses(expense));

 
	}
	
	
	//Pay Expense with Credit Balance
	
	@RequestMapping(value = "/PayCreditCard", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Budget> addCreditcards(@RequestBody CreditCards creditcard) {
	
		return ResponseEntity.ok(service.addCreditCards(creditcard));
	
	
	}
	
	
	//Get All Expense
	@RequestMapping(value = "/getExpense", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Expense>> geExpense()  {
	
		return ResponseEntity.ok(expenseRepository.findAll());

 
	}
	
	
	
	// Report Generate 
	 @RequestMapping(value = "/find/date-between", method = RequestMethod.POST, produces = "application/json")
	    public ResponseEntity<List<Budget>> findBudgetByDate(@RequestBody DateSearcherDto dto) {
			


	         return ResponseEntity.ok(service.getBudgetList(dto));
	      
		}
	
	

}
