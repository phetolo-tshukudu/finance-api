package com.phetolo.Financeapi.mapper;

import com.phetolo.Financeapi.dto.TransactionDTO;
import com.phetolo.Financeapi.model.Transaction;

public class TransactionMapper {
	public static TransactionDTO mapToDto(Transaction transaction) {
		return new TransactionDTO(transaction.getId(),transaction.getAmount(),transaction.getType(),transaction.getCategory(),transaction.getDescription(),transaction.getDate(),transaction.getStatus());
	}
	
	public static Transaction mapToEntity(TransactionDTO transactiondto) {
		return new Transaction(transactiondto.getId(),transactiondto.getAmount(),transactiondto.getType(),transactiondto.getCategory(),transactiondto.getDescription(),transactiondto.getDate(),transactiondto.getStatus(),null);
	}
}
