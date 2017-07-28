package ttadm.util;

import ttadm.modelattribute.IMAmodel;

public class Handler implements Runnable {

	public IMAmodel MAmodel;
	
	public Handler(IMAmodel MAmodel) {
		this.MAmodel = MAmodel;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int i = 0;
			while(i < 10 )
			{
				Thread.currentThread().sleep(1000);
				i++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(MAmodel);
	}

}
