package com.jpmorgan.assignment.stockmarket.service;

import java.math.BigDecimal;

import com.jpmorgan.assignment.stockmarket.exception.ErrorCodes;
import com.jpmorgan.assignment.stockmarket.exception.StockRegisterException;
import com.jpmorgan.assignment.stockmarket.exception.StockServiceException;
import com.jpmorgan.assignment.stockmarket.model.Indicator;
import com.jpmorgan.assignment.stockmarket.model.StockSymbol;
import com.jpmorgan.assignment.stockmarket.model.StockType;

/**
 * StockService
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public interface IStockService {

	/**
	 * Registers a stock with the stock register
	 * 
	 * <p>
	 * Once stock is registered, supported operations can be invoked
	 * {@link #getDividendYield(String, BigDecimal)}
	 * {@link #getPERatio(String, BigDecimal) </p>
	 * 
	 * @param stockSymbol
	 * @param stockType
	 * @param parValue
	 * @param lastDividend
	 * @param fixedDividendPercentage
	 * 
	 * @throws StockServiceException contains Error Code 
	 *         {@link ErrorCodes#INVALID_PARVALUE_AMOUNT}
	 *         {@link ErrorCodes#INVALID_DIVIDEND_PERCENTAGE}
	 *         {@link ErrorCodes#INVALID_DIVIDEND_PERCENTAGE}
	 *         {@link ErrorCodes#DUPLICATE_STOCK_REGISTRATION}
	 */
	public void registerStock(String stockSymbol, StockType stockType, BigDecimal parValue, BigDecimal lastDividend,
			double fixedDividendPercentage) throws StockServiceException;

	/**
	 * Calculates dividend yield based on the stock type
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate dividend
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Stock Symbol is a stockSymbol that is used while registering the stock
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Stock Price is in GBP for e.g 100Pennies is 1GBP, 60Pennies is 0.60GBP
	 * <p>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param stockPrice
	 * 
	 * 
	 * 
	 * 
	 * @return BigDecimal 
	 * @throws StockServiceException
	 *         {@link ErrorCodes#STOCK_NOT_REGISTERED}
	 *         {@link ErrorCodes#INVALID_STOCK_PRICE}
	 */
	public BigDecimal getDividendYield(String stockSymbol, BigDecimal stockPrice) throws StockServiceException;

	/**
	 * Calculates PE ratio
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate pe ratio
	 * {@link #registerStock()}
	 * <p>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param stockPrice
	 * 
	 * 
	 * 
	 * @return BigDecimal 
	 * @throws StockServiceException
	 *         {@link ErrorCodes#STOCK_NOT_REGISTERED}
	 *         {@link ErrorCodes#INVALID_STOCK_PRICE}
	 */
	public BigDecimal getPERatio(String stockSymbol, BigDecimal stockPrice) throws StockServiceException;

	/**
	 * setTrade Method to record a trade for a particular stock
	 * 
	 * <p>
	 * Stock needs to be registered before calling calculate pe ratio
	 * {@link #registerStock()}
	 * <p>
	 * <p>
	 * Timestamp is generated on the server
	 * </P>
	 * 
	 * @param stockSymbol
	 *            - stockSymbol used while registering stock
	 * @param noOfshares
	 * @param indicator
	 * @param price
	 * 
	 * 
	 * @return boolean 
	 * @throws StockServiceException
	 *         {@link ErrorCodes#STOCK_NOT_REGISTERED}
	 *         {@link ErrorCodes#INVALID_TRADE_QUANTITY}
	 *         {@link ErrorCodes#INVALID_STOCK_PRICE}
	 */
	public boolean setTrade(String stockSymbol, int noOfshares, Indicator indicator, BigDecimal price)
			throws StockServiceException;

	/**
	 * getVolumneWeightedStockPrice Method to provide VolumneWeightedStockPrice
	 * 
	 * @param stockSymbol
	 *            String
	 * @param minutes
	 *            int
	 * 
	 * 
	 * @return BigDecimal 
	 * @throws StockServiceException
   	 *         {@link ErrorCodes#STOCK_NOT_REGISTERED}
	 */
	public BigDecimal getVolumeWeightedStockPrice(String stockSymbol, int minutes) throws StockServiceException;

	/**
	 * getAllShareIndex Method to provide getAllShareIndex using geometric mean
	 * 
	 * 
	 * @return Double 
	 * @throws StockServiceException if there is a calculation error 
	 */
	public Double getAllShareIndex() throws StockServiceException;
	
	/**
	 * Checks whether the user entered stock exists.
	 * @param stockSymbol
	 * @throws StockRegisterException
	 */
	public void checkStockRegistration(String stockSymbol) throws StockRegisterException;


}
