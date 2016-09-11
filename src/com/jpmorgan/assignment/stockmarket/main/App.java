package com.jpmorgan.assignment.stockmarket.main;

import java.math.BigDecimal;
import java.util.Scanner;

import com.jpmorgan.assignment.stockmarket.exception.StockRegisterException;
import com.jpmorgan.assignment.stockmarket.exception.StockServiceException;
import com.jpmorgan.assignment.stockmarket.model.Indicator;
import com.jpmorgan.assignment.stockmarket.model.StockType;
import com.jpmorgan.assignment.stockmarket.service.IStockService;
import com.jpmorgan.assignment.stockmarket.service.StockServiceImpl;

/**
 * JP Morgan - Super Simple Stock Market
 * 
 * @author Seetha.Vaithyanathan
 */
public class App {

	private static IStockService stockService = StockServiceImpl.getInstance();
	private static Scanner scanner;

	public static void main(String[] args) {
		initStocks();
		printMenu();

		scanner = new Scanner(System.in);
		String choice = null;
		while (true) {
			choice = scanner.nextLine();
			if ("q".equalsIgnoreCase(choice)) {
				System.out.println("Application is stopped executing");
				scanner.close();
				System.exit(0);
			} else {
				try {
					int option = Integer.parseInt(choice);
					String stockFromUser;
					BigDecimal priceFromUser;

					switch (option) {
					case 1:
						stockFromUser = getStockFromUser();
						priceFromUser = getStockPriceFromUser();
						System.out.println("stockFromUser"+stockFromUser);
						System.out.println("priceFromUser"+priceFromUser);

						calculateDividendYield(stockFromUser, priceFromUser);
						break;
					case 2:
						stockFromUser = getStockFromUser();
						priceFromUser = getStockPriceFromUser();
						calculatePERatio(stockFromUser, priceFromUser);
						break;
					case 3:
						stockFromUser = getStockFromUser();
						int quantityFromUser = getQuantityFromUser();
						Indicator tradeTypeFromUser = getTradeType();
						priceFromUser = getStockPriceFromUser();
						recordTrade(stockFromUser, quantityFromUser, tradeTypeFromUser, priceFromUser);
						break;
					case 4:
						stockFromUser = getStockFromUser();
						calculateVolumeWeightedStockPrice(stockFromUser);
						break;
					case 5:
						calculateGBCE();
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					printResult("Invalid Option");
				} catch (StockServiceException e1) {
					if(e1.getErrorCode()!=null)
					printResult(e1.getErrorCode().getErrorCode());
					else
					printResult(e1.getMessage());				
				}catch (StockRegisterException e1) {
					if(e1.getErrorCode()!=null)
					printResult(e1.getErrorCode().getErrorCode());

					printResult(e1.getMessage());				
				}catch (Exception e1) {
					printResult(e1.getMessage());
				}
				System.out.println("-------------------");
				printMenu();
			}
		}
	}

	private static String getStockFromUser() throws StockRegisterException {
		System.out.println("Please input stock symbol");
		boolean error=false;
		String stockSymbol = scanner.nextLine();

		stockService.checkStockRegistration(stockSymbol);
			return stockSymbol;

		
		//return null;
	}

	private static BigDecimal getStockPriceFromUser() {
		System.out.println("Please input stock price");
		String stockPrice = scanner.nextLine();
		try {
			BigDecimal result = new BigDecimal(stockPrice);
			if (BigDecimal.ZERO.compareTo(result) >= 0) {
				throw new RuntimeException("Invalid quantity: Must be greated than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid price: Not a number");
		}
	}

	private static Indicator getTradeType()  {
		System.out.println("Please input trade type (BUY/SELL)");
		String type = scanner.nextLine();
		try {
			return Indicator.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid trade type: Must be BUY or SELL");
		}
	}

	private static int getQuantityFromUser() {
		System.out.println("Please input quantity");
		String quantity = scanner.nextLine();
		try {
			int result = Integer.parseInt(quantity);
			if (result <= 0) {

				throw new RuntimeException("Invalid quantity: Must be greated than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid quantity: Not a number");
		}
	}

	private static void printMenu() {
		System.out.println("JPMorgan - Super simple stock market");
		System.out.println("1: Calculate dividend yield for stock");
		System.out.println("2: Calculate P/E ratio for stock");
		System.out.println("3: Record a trade for stock");
		System.out.println("4: Calculate Volume Weighted Stock Price for stock");
		System.out.println("5: Calculate GBCE All Share Index");
		System.out.println("q: Quit");
	}

	private static void calculateDividendYield(String stockSymbol, BigDecimal stockPrice) throws StockServiceException {
		BigDecimal result = stockService.getDividendYield(stockSymbol, stockPrice);
		printResult("Dividend Yield: " + result);

	}

	private static void calculatePERatio(String stockSymbol, BigDecimal stockPrice) throws StockServiceException {
		BigDecimal result = stockService.getPERatio(stockSymbol, stockPrice);
		printResult("PE Ratio: " + result);

	}

	private static void calculateVolumeWeightedStockPrice(String stockSymbol) throws StockServiceException {
		BigDecimal result = stockService.getVolumeWeightedStockPrice(stockSymbol, 15);
		printResult("Volume Weighted Stock Price: " + result);

		/*
		 * List<Trade> trades = tradeService.getTrades(stock, 15); if (trades ==
		 * null || trades.isEmpty()) { printResult(
		 * "Volume Weighted Stock Price: No trades"); } else { BigDecimal result
		 * = stockService.calculateVolumeWeightedStockPrice(trades);
		 * printResult("Volume Weighted Stock Price: " + result); }
		 */
	}

	private static void recordTrade(String stockSymbol, int quantity, Indicator type, BigDecimal price) throws StockServiceException {
		boolean result = stockService.setTrade(stockSymbol, quantity, type, price);
		if(result){
			printResult("Trade recorded");
		}else{
			printResult("Trade recording failed");
		}
	}

	private static void calculateGBCE() throws StockServiceException {
		Double result = stockService.getAllShareIndex();
		printResult("Unable to calculate GBCE: No trades");
		
	}

	private static void initStocks()  {

		try {
			stockService.registerStock("TEA", StockType.COMMON, new BigDecimal(100), new BigDecimal(0), 0);
			stockService.registerStock("POP", StockType.COMMON, new BigDecimal(100), new BigDecimal(8), 0);
			stockService.registerStock("ALE", StockType.COMMON, new BigDecimal(60), new BigDecimal(23), 0 );
			stockService.registerStock("GIN", StockType.PREFERRED,new BigDecimal(100), new BigDecimal(8), 2 );
			stockService.registerStock("JOE", StockType.PREFERRED, new BigDecimal(250),new BigDecimal(13), 0 );
		} catch (StockServiceException e) {
			printResult("Unable to initialize stocks...");

		}

	}

	private static void printResult(String result) {
		System.out.println("-------------------------------------");
		System.out.println(result);
		System.out.println("-------------------------------------");
	}
	private static void printResult(int result) {
		System.out.println("-------------------------------------");
		System.out.println(result);
		System.out.println("-------------------------------------");
	}
}
