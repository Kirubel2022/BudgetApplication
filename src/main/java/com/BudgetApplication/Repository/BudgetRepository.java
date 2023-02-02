package com.BudgetApplication.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BudgetApplication.Model.Budget;



public interface BudgetRepository extends JpaRepository<Budget, Long> {
	
	@Query("SELECT sum(e.balance) from Budget e")
	float sumAmount();

	
	List<Budget>  findAllByBudgetDateBetween(Date startDate, Date endDate);
}
