package com.phetolo.Financeapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	boolean existsById(Long id);
	List<Transaction> findByUser_Id(Long userId);
	List<Transaction> findByUser_IdAndType(Long id,TransactionType type);
	@Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    List<Transaction> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
	@Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND FUNCTION('MONTH', t.date) = :month AND FUNCTION('YEAR', t.date) = :year")
	List<Transaction> findByUser_IdAndMonth(@Param("userId") Long userId, @Param("month") int month, @Param("year") int year);
	boolean existsByUser_Id(Long userId);
	
}
