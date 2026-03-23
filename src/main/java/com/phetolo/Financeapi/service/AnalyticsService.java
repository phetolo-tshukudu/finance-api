package com.phetolo.Financeapi.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.dto.StatisticDTO;
import com.phetolo.Financeapi.enums.TransactionType;
import com.phetolo.Financeapi.exception.TransactionNotFoundException;
import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.repository.TransactionRepository;



@Service
public class AnalyticsService {
	private TransactionRepository Trepo;
	
	public AnalyticsService(TransactionRepository Trepo) {
		this.Trepo=Trepo;
	}
	public StatisticDTO computeStatistics(Long userId) {
		if(!Trepo.existsByUser_Id(userId)) {
			throw new TransactionNotFoundException("Transaction not found for id: "+userId);
		}
		
		List<Transaction> userTransactions = Trepo.findByUser_Id(userId);
		DoubleSummaryStatistics stats = userTransactions.stream().collect(Collectors.summarizingDouble(t->t.getAmount().doubleValue()));
		StatisticDTO summary =new StatisticDTO();
		double variance = userTransactions.stream().mapToDouble(t->t.getAmount().doubleValue()).map(amount->Math.pow(amount-stats.getAverage(), 2)).average().orElse(0);
		double stdev = Math.sqrt(variance);
		double totalSpending = Trepo.findByUser_IdAndType(userId, TransactionType.EXPENSE).stream().mapToDouble(t->t.getAmount().doubleValue()).sum();
		summary.setMaxValue(stats.getMax());
		summary.setMean(stats.getAverage());
		summary.setMinValue(stats.getMin());
		summary.setStdev(stdev);
		summary.setVariance(variance);
		summary.setTotalSpending(totalSpending);
		summary.setTransactionCount(userTransactions.size());
		return summary;
	}
}
