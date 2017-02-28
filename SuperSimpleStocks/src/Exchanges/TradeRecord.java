package Exchanges;

public interface TradeRecord {
	
	public void setTradeTime();
	public double getTradeTime();
	public int getQuantityOfShares();
	public double getTradePrice();
	public void setIndicator( String buyOrSell );
	public boolean indicatorIsSell();

}
