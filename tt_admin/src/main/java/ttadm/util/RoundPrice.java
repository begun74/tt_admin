package ttadm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundPrice {
	
	public static double round_HALF_UP(double price) {
		
		return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
