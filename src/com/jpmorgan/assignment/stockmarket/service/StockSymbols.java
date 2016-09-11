package com.jpmorgan.assignment.stockmarket.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jpmorgan.assignment.stockmarket.model.StockSymbol;

/**
 * StockSymbols allows to cache reuse value objects for Stock symbols 
 * @author Seetha.Vaithyanathan
 * @version $Revision: 1.0 $
 */
public class StockSymbols {

	private Map<String, StockSymbol> stockSymbols = new ConcurrentHashMap<String, StockSymbol>();

	/**
	 * Method lookup.
	 * 
	 * @param stockSymbol
	 *            String
	 * 
	 * @return StockSymbol
	 */
	public StockSymbol lookup(String stockSymbol) {
		if (!stockSymbols.containsKey(stockSymbol))
			stockSymbols.put(stockSymbol, new StockSymbol(stockSymbol));
		return stockSymbols.get(stockSymbol);
	}

}
