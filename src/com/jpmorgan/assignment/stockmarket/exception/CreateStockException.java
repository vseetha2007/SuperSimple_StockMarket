package com.jpmorgan.assignment.stockmarket.exception;

/**
 * CreateStockException exception to be used when creating stock types
 * 
 * @author Seetha.Vaithyanathan
 *
 * @version $Revision: 1.0 $
 */
public final class CreateStockException extends MainException {

	/**
	 * Constructor for CreateStockException.
	 * 
	 * @param errorCode
	 *            IErrorCode
	 */
	public CreateStockException(IErrorCode errorCode) {
		super(errorCode);
	}

}
