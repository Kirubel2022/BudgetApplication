package com.BudgetApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BudgetApplication.Model.Income;




public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	
//	@Query(value = "SELECT * amount FROM Income")
//	public List<Income> findAllamount();

	
//	@Query("select p from Income p where p.amount <=1")
//	public List<Income> findAllAmount();
	
	@Query("select u from Income u where u.incomeId = ?1")
	List<Income>  findByIncomeId(Long incomeId);
	
	
	@Query("SELECT sum(e.amount) from Income e")
	float sumAmount();
	
	
	
}
