package com.phetolo.Financeapi.repository;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phetolo.Financeapi.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
	List<Budget> findByUserIdAndCategory(Long id,String Category);
	Budget findByUserIdAndCategoryAndMonth(Long id,String category,YearMonth mon);
	List<Budget> findByUserId(Long userId);
}
