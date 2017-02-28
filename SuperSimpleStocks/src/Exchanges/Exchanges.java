package Exchanges;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Date;

import Stocks.Stock;


public abstract class Exchanges implements	VolumeWeightedStockPrice {
	// time has a default set to 15 minutes which is 900000 milliseconds
	protected long TimePeriod = 900000;
	protected Map<String,Stock> allStocks;
	protected Map<String,Trade> allTrades;

	public Exchanges( LinkedList<Stock> tradingStocks ) {
		this.allStocks = new HashMap<String,Stock>();
		for ( Stock stock : tradingStocks ){
			allStocks.put( stock.getName(), stock );
		}
		this.allTrades = new HashMap<String,Trade>();
	}
	
	public String recordTrade( String stockSymbolIn, double tradePriceIn, int quantityOfSharesIn, String buyOrSellIn){
		
		// If trade is good, give it an unique id and store with other trades
		if(  allStocks.containsKey(stockSymbolIn) && tradePriceIn > 0.0 && quantityOfSharesIn > 0 && isBuySell (buyOrSellIn ) ){

			Trade newTrade = new Trade( stockSymbolIn, tradePriceIn, quantityOfSharesIn, buyOrSellIn );

			int newId = 1;
			while( allTrades.containsKey("trade"+ Integer.toString( newId )) == true ){
				newId++;
			}
			String id = "trade"+ Integer.toString( newId );
			allTrades.put( id, newTrade);
			return id;
		}
		else{
		
			if ( allStocks.containsKey(stockSymbolIn) == false ){
				System.out.println("No Stock Symobl Exists");
			}
			
			if( tradePriceIn <= 0 ){
				System.out.println("No Sale, trade price is less than 0");
			}
			
			if( quantityOfSharesIn <= 0 ){
				System.out.println("No Sale, quantity Of shares is less than 0");
			}
			return "noSale";
		}

	}
	
	protected boolean isBuySell(String buyOrSell){
		// Check the buyOrSellIn is formatted correctly
		if ( buyOrSell.toLowerCase() == "sell" ){
			return true;
		}else if ( buyOrSell.toLowerCase() == "buy" ){
			return true;
				
		}else {
				System.out.println("Buy or Sell not set for Trade " + buyOrSell);
				return false;
			}
	}

	@Override
	public void setTimePeriod( long timePeriodIn ) {
		// This is set in milliseconds
		this.TimePeriod = timePeriodIn;
	}

	@Override
	public double getVolumeWeightedStockPrice() {
		// Find all trades within the last 15 minutes then Calculate
		Date time = new Date();
		long start = time.getTime() - this.TimePeriod;
		
		double quantitiesAndTradePrices = 0;
		double quantities = 0;
		
		for ( Map.Entry<String, Trade> trade : allTrades.entrySet() ){
			if ( trade.getValue().getTradeTime() > start ){
				quantitiesAndTradePrices += trade.getValue().getTradePrice() * trade.getValue().getQuantityOfShares();
				quantities += trade.getValue().getQuantityOfShares();
			}
		}
		
		return quantitiesAndTradePrices/quantities;
	}

}
