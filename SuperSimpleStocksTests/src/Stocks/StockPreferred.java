package Stocks;

public class StockPreferred extends Stock {
	private double fixedDividend;
	
	public StockPreferred( String stockSymbolIn, double parValueIn, double dividendIn, double fixedDividendIn ) {
		super (stockSymbolIn, parValueIn, dividendIn);
		this.fixedDividend = fixedDividendIn;
	}
	
	
	@Override
	public double getDvidenedYield( double price ) {
		// Check lastDividend has value
		if (fixedDividend != 0){
		return ( fixedDividend * parValue )/price;
		}
		else{
			System.out.println("fixedDividend has not been set, possibly should be Common not Preferred");
			return 0;
			}
	}

}
