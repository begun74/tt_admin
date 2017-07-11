package ttadm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ttadm.model.Tail;

public class CalculatePrice_Opt_Rozn_forTails {
	
	
	public static Tail calculate(Tail tail) {
		//System.out.println("processTail - "+tail);

		double fpice = tail.getFirstPrice();

		double nadb_opt = (tail.getNadb_opt()+100.0) / 100.0;
		double nadb_rozn = (tail.getNadb_rozn()+100.0) / 100.0;
		double nds = (tail.getNds()+100.0) / 100.0;
		
		double rozn_price = fpice * nadb_rozn * nds;
		double opt_price = fpice * nadb_opt * nds;
		//System.out.println(rozn_price +"  "+opt_price);
		
		rozn_price = new BigDecimal(rozn_price).setScale(2, RoundingMode.HALF_UP).doubleValue();
		opt_price = new BigDecimal(opt_price).setScale(2, RoundingMode.HALF_UP).doubleValue();

		//System.out.println(res +"");

		tail.setRozn_price(rozn_price);
		tail.setOpt_price(opt_price);
		
		//System.out.println("processTail - "+tail);		
		
		return tail;
	}

}
