package ttadm.util;

import tt.modelattribute.IMAmodel;

public class Handler implements Runnable {

	public IMAmodel MAmodel;
	
	public Handler(IMAmodel MAmodel) {
		this.MAmodel = MAmodel;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(MAmodel);
	}

}
