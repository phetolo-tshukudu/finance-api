package com.phetolo.Financeapi.dto;

public class InsightResponsedto {
	private double totalSpending;
	private double totalincome;
	private double netBalance;
	private double spendingChange;
	private String message;
	private String warning;
	
	public InsightResponsedto() {
	}

	public InsightResponsedto(double totalSpending, double totalincome, double netBalance,
			double spendingChange, String message, String warning) {
		this.totalSpending = totalSpending;
		this.totalincome = totalincome;
		this.netBalance = netBalance;
		this.spendingChange = spendingChange;
		this.message = message;
		this.warning = warning;
	}

	public double getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(double totalSpending) {
		this.totalSpending = totalSpending;
	}

	public double getTotalincome() {
		return totalincome;
	}

	public void setTotalincome(double totalincome) {
		this.totalincome = totalincome;
	}

	public double getNetBalance() {
		return netBalance;
	}

	public void setNetBalance(double netBalance) {
		this.netBalance = netBalance;
	}


	public double getSpendingChange() {
		return spendingChange;
	}

	public void setSpendingChange(double spendingChange) {
		this.spendingChange = spendingChange;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	
}
