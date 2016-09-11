package com.jpmorgan.assignment.stockmarket.model;

import com.jpmorgan.assignment.stockmarket.exception.CalculationException;

/**
 * IStock allows abstract representation of the Stock class {@See AbstractStock}
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public interface IStock {

	/**
	 * Method getStockSymbol.
	 * 
	 * @return StockSymbol
	 */
	public StockSymbol getStockSymbol();

	/**
	 * Method getStockType.
	 * 
	 * @return StockType
	 */
	public StockType getStockType();

	/**
	 * Method getParValue.
	 * 
	 * @return Money
	 */
	public Money getParValue();

	/**
	 * Method getFixedDividendPercentage.
	 * 
	 * @return double
	 */
	public double getFixedDividendPercentage();

	/**
	 * Method getDividendYield.
	 * 
	 * @param stockPrice
	 *            Money
	 * 
	 * 
	 * @return Money * @throws CalculationException
	 */
	public Money getDividendYield(Money stockPrice) throws CalculationException;

	/**
	 * Method getLastDividend.
	 * 
	 * @return Money
	 */
	public Money getLastDividend();

	/**
	 * Method getPERatio.
	 * 
	 * @param stockPrice
	 *            Money
	 * 
	 * 
	 * @return Money * @throws CalculationException
	 */
	public Money getPERatio(Money stockPrice) throws CalculationException;

}
