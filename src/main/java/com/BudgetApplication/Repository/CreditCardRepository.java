package com.BudgetApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BudgetApplication.Model.CreditCards;



public interface CreditCardRepository extends JpaRepository<CreditCards, Long> {
	
	
	@Query("SELECT sum(e.creditBalance) from CreditCards e")
	float sumAmount();
	
	
	CreditCards findByCardId(Long cardId);
	


}
