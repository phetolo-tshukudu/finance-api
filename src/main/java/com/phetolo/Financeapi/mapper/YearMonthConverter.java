package com.phetolo.Financeapi.mapper;

import java.time.YearMonth;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class YearMonthConverter implements AttributeConverter<YearMonth, String> {

	@Override
	public String convertToDatabaseColumn(YearMonth attribute) {
		// TODO Auto-generated method stub
		return attribute!=null? attribute.toString():null;
	}

	@Override
	public YearMonth convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return dbData!=null? YearMonth.parse(dbData):null;
	}

}
