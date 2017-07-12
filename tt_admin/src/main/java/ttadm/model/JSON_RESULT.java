package ttadm.model;

import java.util.LinkedList;

public class JSON_RESULT implements IJSON {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4453133844151856298L;
	
	
	LinkedList<IModel> ok = new LinkedList<IModel>();
	LinkedList<IModel> temp = new LinkedList<IModel>();


	public LinkedList<IModel> getOk() {
		return ok;
	}

	public void setOk(LinkedList<IModel> ok) {
		this.ok = ok;
	}
	
	

	public LinkedList<IModel> getTemp() {
		return temp;
	}


	public void setTemp(LinkedList<IModel> temp) {
		this.temp = temp;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "JSON_RESULT";
	}
	
	

}
