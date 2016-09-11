package com.jpmorgan.assignment.stockmarket.exception;

/**
 * StockServiceException is used to wrap exception at the service layer
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public final class StockServiceException extends MainException {

	/**
	 * Constructor for StockServiceException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public StockServiceException(IErrorCode errorCode) {
		super(errorCode);
	}

}
