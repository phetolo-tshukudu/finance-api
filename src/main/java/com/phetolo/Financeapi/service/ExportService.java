package com.phetolo.Financeapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phetolo.Financeapi.model.Transaction;
import com.phetolo.Financeapi.repository.TransactionRepository;

@Service
public class ExportService {
	private TransactionRepository Trepo;
	
	
	public ExportService(TransactionRepository Trepo) {
		this.Trepo=Trepo;
	}
	
	public String exportTransactionsToCsv(Long userId) {
		List<Transaction> transactions = Trepo.findByUserId(userId);
		StringBuilder csv = new StringBuilder();
		csv.append("Id,Amount,Status,Category,Description,Date,Type\n");
		
		for(Transaction t : transactions) {
			csv.append(t.getId()).append(",");
			csv.append(t.getAmount().doubleValue()).append(",");
			csv.append(t.getStatus()).append(",");
			csv.append(t.getCategory()).append(",");
			csv.append(t.getDescription()).append(",");
			csv.append(t.getDate()).append(",");
			csv.append(t.getType()).append("\n");
		}
		return csv.toString();
	}
}
