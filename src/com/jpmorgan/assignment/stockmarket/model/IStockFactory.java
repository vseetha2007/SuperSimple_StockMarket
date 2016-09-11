package com.jpmorgan.assignment.stockmarket.model;

import com.jpmorgan.assignment.stockmarket.exception.CreateStockException;

/**
 * IStockFactory interface that allows to create new stock
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public interface IStockFactory {
	/**
	 * Method createStock.
	 * 
	 * @param stockSymbol
	 *            StockSymbol
	 * @param stockType
	 *            StockType
	 * @param parValue
	 *            Money
	 * @param lastDividend
	 *            Money
	 * @param fixedDividendPercentage
	 *            double
	 * 
	 * 
	 * @return IStock * @throws CreateStockException
	 */
	public IStock createStock(StockSymbol stockSymbol, StockType stockType, Money parValue, Money lastDividend,
			double fixedDividendPercentage) throws CreateStockException;
}
