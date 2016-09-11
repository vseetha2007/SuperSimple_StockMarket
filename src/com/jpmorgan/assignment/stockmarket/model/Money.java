package com.jpmorgan.assignment.stockmarket.model;

import java.math.BigDecimal;

import com.jpmorgan.assignment.stockmarket.exception.CreateStockException;

/**
 * Money that represents price/amount/value in the application
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public final class Money {
	private final BigDecimal value;
	private final Currency currency;

	/**
	 * By default the amount is in pennies (GBP)
	 * 
	 * @param amount
	 * 
	 * @throws CreateStockException
	 */
	private Money(BigDecimal amount) {
		this.value = amount;
		this.currency = Currency.GBP;
	}

	/**
	 * Method valueOf.
	 * 
	 * @param amount
	 *            BigDecimal
	 * 
	 * @return Money
	 */
	public static final Money valueOf(BigDecimal amount) {
		return new Money(amount);
	}

	/**
	 * Method getValue.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * Method isNegative.
	 * 
	 * @return boolean
	 */
	public boolean isNegative() {
		if (value.compareTo(BigDecimal.valueOf(0)) < 0)
			return true;
		return false;
	}

}
