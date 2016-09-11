package com.jpmorgan.assignment.stockmarket.exception;

/**
 * StockRegisterException is used to indicate exceptions when registering stocks
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public final class StockRegisterException extends MainException {

	/**
	 * Constructor for StockRegisterException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public StockRegisterException(IErrorCode errorCode) {
		super(errorCode);
	}

}
