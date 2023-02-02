package com.BudgetApplication.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BudgetApplication.Controller.DateSearcherDto;
import com.BudgetApplication.Model.Budget;
import com.BudgetApplication.Model.CreditCards;
import com.BudgetApplication.Model.Expense;
import com.BudgetApplication.Model.Income;
import com.BudgetApplication.Model.Users;
import com.BudgetApplication.Repository.BudgetRepository;
import com.BudgetApplication.Repository.CreditCardRepository;
import com.BudgetApplication.Repository.ExpenseRepository;
import com.BudgetApplication.Repository.IncomeRepository;
import com.BudgetApplication.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

@Service
public class BudgetApplicationServiceImpl {

	@Autowired
	BudgetRepository budgetRepository;

	@Autowired
	ExpenseRepository expenseRepository;

	@Autowired
	IncomeRepository incomeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	public Users saveUserProfile(Long userId) {

		Users user = null;
		ObjectMapper objectMapper = new ObjectMapper();

		OkHttpClient client = new OkHttpClient();

		Request getRequest = new Request.Builder().url("http://localhost:8085/xpay/getUser/" + userId).get().build();

		ResponseBody response;

		try {
			response = client.newCall(getRequest).execute().body();

			user = objectMapper.readValue(response.string(), Users.class);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userRepository.save(user);

		return user;

	}

	public Income SaveIncome(String income) {

		Income incomes = null;

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			incomes = objectMapper.readValue(income, Income.class);
			incomeRepository.save(incomes);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return incomes;
	}

	public String getbalance() {

		float expense = 0;

		float IncomeBalance = 0;

		float finalbalance = 0;

		IncomeBalance = incomeRepository.sumAmount();

		expense = expenseRepository.sumAmount();

		finalbalance = (IncomeBalance - expense);

		Budget budget = new Budget();

		budget.setRemark("Total Balance");

		budget.setBalance(finalbalance);
		budgetRepository.save(budget);

		return "Balance = " + finalbalance;

	}

	public Expense addExpenses(String expense) {

		Expense expenses = new Expense();

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			expenses = objectMapper.readValue(expense, Expense.class);
			expenseRepository.save(expenses);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return expenses;
	}

	public Budget addCreditCards(CreditCards creditcard) {

		Expense expense = new Expense();
		float creditCardBalance = 0;
		float expenseBalance = 0;
		float finalBalance = 0;

		CreditCards creditcards = creditCardRepository.findByCardId(creditcard.getCardId());

		creditCardBalance = creditcards.getCreditBalance();
		expenseBalance = expenseRepository.sumAmount();

		finalBalance = (creditCardBalance - expenseBalance);

		expenseRepository.deleteAll();

		expense.setRemark("Expense Cleared");

		expenseRepository.save(expense);

		creditcards.setCreditBalance(finalBalance);

		creditCardRepository.save(creditcards);

		OkHttpClient client = new OkHttpClient();

		Request getRequest = new Request.Builder().url("http://localhost:8085/xpay/updatecreditcardbalance?cardId="
				+ creditcard.getCardId() + "&amount=" + finalBalance).get().build();

		ResponseBody response;

		try {
			response = client.newCall(getRequest).execute().body();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Budget budget = new Budget();

		budget.setBalance(expenseBalance);
		budget.setRemark("CreditCard Payement");

		budgetRepository.save(budget);

		return budget;
	}
	
	
	
	
	
	
	
	
	
	
	public List<Budget> getBudgetList(DateSearcherDto dto) {
		

		List<Budget> BudgetMasterList = budgetRepository.findAllByBudgetDateBetween(dto.getStartDate(),
				dto.getEndDate());

		return BudgetMasterList;
		
	}

}
