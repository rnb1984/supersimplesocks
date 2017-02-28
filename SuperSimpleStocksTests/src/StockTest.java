import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Stocks.*;

public class StockTest {
	protected static StockCommon TEA;
	protected static StockPreferred GIN;
	// amount of acceptable error
	protected static double epsilon = 0.001;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TEA = new StockCommon("TEA", 0.0, 100.0);
		GIN = new StockPreferred("GIN", 8.0, 100.0, 0.2);
	}
	
	// Common Stock Tests
	@Test
	public void testCommonStockDividendYield() {
		double priceInput = 5.0;
		double testing = TEA.getDvidenedYield( priceInput );
		assertEquals( 20.0, testing, epsilon );
	}
	
	@Test
	public void testCommonStockPERatio() {
		double priceInput = 20.0;
		double testing = TEA.getPE_Ratio( priceInput );
		assertEquals( 0.2, testing, epsilon );
	}
	
	// Preferred Stock Tests
	@Test
	public void testPreferredStockDividendYield() {
		double priceInput = 5.0;
		double testing = GIN.getDvidenedYield( priceInput );
		assertEquals( 0.32, testing, epsilon );
	}
	
	@Test
	public void testPreferredStockPERatio() {
		double priceInput = 20.0;
		double testing = GIN.getPE_Ratio( priceInput );
		assertEquals( 0.2, testing, epsilon );
	}

}