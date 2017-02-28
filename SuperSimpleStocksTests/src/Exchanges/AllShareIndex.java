package Exchanges;
import java.util.Map;
import java.lang.Math;
import java.util.LinkedList;

import Stocks.Stock;

public class AllShareIndex extends Exchanges implements GBCE{

	public AllShareIndex( LinkedList<Stock> tradingStocks ) {
		super( tradingStocks );
	}

	public double CalculateGeometircMean() {
		// Calculates the root of all trades
		double p = 1; 
		for ( Map.Entry<String, Trade> trade : allTrades.entrySet() ){
			double price = trade.getValue().getTradePrice();
			p = p * price;
		}
		return Math.pow( Math.sqrt(p), allTrades.size() );
	}

}
