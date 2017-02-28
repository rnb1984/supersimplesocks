package Exchanges;
import java.util.Date;

public class Trade implements TradeRecord {
	private String stockSymbol;
	private double tradePrice;
	private long tradeTime;
	private int quantityOfShares;
	private boolean indicator;

	public Trade( String stockSymbolIn, double tradePriceIn, int quantityOfSharesIn, String buyOrSell) {
		
		setTradeTime();
		this.stockSymbol = stockSymbolIn;
		this.tradePrice = tradePriceIn;
		this.quantityOfShares = quantityOfSharesIn;
		setIndicator( buyOrSell );
		
	}
	
	public String getStockSymbol(){
		return this.stockSymbol;
	}

	@Override
	public void setTradeTime() {
		// Sets the timestamp of the trade
		Date time = new Date();
		this.tradeTime = time.getTime();

	}

	@Override
	public double getTradeTime() {
		return this.tradeTime;
	}

	@Override
	public int getQuantityOfShares() {
		return this.quantityOfShares;
	}

	@Override
	public double getTradePrice() {
		return this.tradePrice;
	}

	@Override
	public void setIndicator( String buyOrSell ) {
		// indicator is true for a sell and false for a buy
		if (buyOrSell.toLowerCase() == "sell"){
			this.indicator = true;
		}else if (buyOrSell.toLowerCase() == "buy"){
			this.indicator = false;
		}

	}

	@Override
	public boolean indicatorIsSell() {
		// True if a sell
		return this.indicator;
	}

}
