import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import Stocks.*;
import Exchanges.*;

public class ExchangeTest {
	protected static LinkedList<Stock> allStocks;
	// amount of acceptable error
	protected static double epsilon = 0.001;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StockCommon TEA = new StockCommon("TEA", 0.0, 100.0);
		StockCommon POP = new StockCommon("POP", 80.0, 100.0);
		StockCommon ALE = new StockCommon("ALE", 23.0, 60.0);
		StockPreferred GIN = new StockPreferred("GIN", 8.0, 100.0, 0.2);
		StockCommon JOE = new StockCommon("JOE", 13.0, 250.0);
		
		allStocks = new LinkedList<Stock>();
		allStocks.add( TEA );
		allStocks.add( POP );
		allStocks.add( ALE );
		allStocks.add( GIN );
		allStocks.add( JOE );
	}

	@Test
	public void testExchangeReordTrade() {
		AllShareIndex exchange = new AllShareIndex( allStocks );
		String tradeOne = exchange.recordTrade( "TEA", 33.3, 5, "buy" ) ;
		String tradeTwo =  exchange.recordTrade( "POP", 27.88, 2, "sell" );
		String tradeThree =  exchange.recordTrade( "ALE", 15.1, 6, "sx"  );
		String tradeFour = exchange.recordTrade( "JOE", 0, 0, "sx"  );
		
		assertEquals( "trade1", tradeOne );
		assertEquals( "trade2", tradeTwo );
		assertEquals( "noSale", tradeThree );
		assertEquals( "noSale", tradeFour );
	}
	
	// VolumeWeightedStockPrice
	@Test
	public void testVolumeWeightedStockPrice() {
		AllShareIndex exchange = new AllShareIndex( allStocks );
		exchange.recordTrade( "TEA", 33.3, 5, "buy" ) ;
		exchange.recordTrade( "POP", 27.88, 2, "sell" );
		Double tradeVWSP = exchange.getVolumeWeightedStockPrice();
		assertEquals( 24.066153846153846, tradeVWSP, epsilon );
	}
	
	// CalculateGeometircMean
	@Test
	public void testCalculateGeometircMean() {
		AllShareIndex exchange = new AllShareIndex( allStocks );
		Double tradeGBCE = exchange.CalculateGeometircMean();
		assertEquals( 1659857.9594069922, tradeGBCE, epsilon );
	}

}
