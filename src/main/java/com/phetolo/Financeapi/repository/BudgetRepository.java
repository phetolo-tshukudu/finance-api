package com.phetolo.Financeapi.repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phetolo.Financeapi.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
	List<Budget> findByUser_IdAndCategory(Long id,String Category);
	Budget findByUser_IdAndCategoryAndMonth(Long id,String category,YearMonth mon);
	List<Budget> findAllByUser_Id(Long userId);
	Optional<Budget> findByUser_Id(Long userId);
}
