package Stocks;

public abstract class Stock implements PERatio, DividenedYield {
	protected String stockSymbol;
	protected double parValue;
	protected double dividend;
	
	public Stock( String stockSymbolIn, double parValueIn, double lastDividendIn){
		this.stockSymbol = stockSymbolIn;
		this.parValue = parValueIn;
		this.dividend = lastDividendIn;
		}

	@Override
	public double getDvidenedYield( double price ) {
		// Check lastDividend has value
		if (price <= 0){
		return this.dividend/price;
		}
		else{
			System.out.println("Price is less than zero");
			return 0;
			}
	}


	@Override
	public double getPE_Ratio( double price ) {
		// Assume dividend means last dividend in assignment as both common and preferred have last dividend
		if (price <= 0){
		return price/this.dividend;
		}
		else{
			System.out.println("Price is less than zero");
			return 0;
			}
	}
	
	public String getName(){
		return this.stockSymbol;
	}

}
