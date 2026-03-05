package com.phetolo.Financeapi.dto;



public class StatisticDTO {
	private double mean;
	private double variance;
	private double stdev;
	private double median;
	private double totalSpending;
	private int transactionCount;
	private double minValue;
	private double maxValue;
	
	public StatisticDTO(double mean, double variance, double stdev, double median, double totalSpending,
			int transactionCount, double minValue, double maxValue) {
		this.mean = mean;
		this.variance = variance;
		this.stdev = stdev;
		this.median = median;
		this.totalSpending = totalSpending;
		this.transactionCount = transactionCount;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}



	public double getMinValue() {
		return minValue;
	}



	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}



	public double getMaxValue() {
		return maxValue;
	}



	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}



	public StatisticDTO() {
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getStdev() {
		return stdev;
	}

	public void setStdev(double stdev) {
		this.stdev = stdev;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public double getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(double totalSpending) {
		this.totalSpending = totalSpending;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	
}
