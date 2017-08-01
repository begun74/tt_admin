package ttadm.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import ttadm.modelattribute.IMAmodel;


@Service
public class Handler implements Serializable, Runnable  {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4026351348050804698L;
	
	
	public IMAmodel MAmodel;
	private Thread currThread = null;
	
	
	public Handler() {
		currThread = new Thread(this,"Handler");
	}
	
	public Handler(IMAmodel MAmodel) {
		this.MAmodel = MAmodel;
		currThread = new Thread(this,"Handler");
	}

	@PostConstruct
	void init() {
		System.out.println("Handler - @PostConstruct");
	}
	
	
	@PreDestroy
	void destr() {
		//if(currThread != null)
		
			currThread.interrupt();

		System.out.println("Handler - @PreDestroy");
		
	}
		
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			int i = 0;
			while(i < 5 )
			{
				currThread.sleep(1000);
				i++;
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(MAmodel);
	}



}
