package ttadm.model;

import java.util.LinkedList;

public class JSON_RESULT implements IJSON {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4453133844151856298L;
	
	private long id=0;
	
	LinkedList<IModel> ok = new LinkedList<IModel>();


	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public LinkedList<IModel> getOk() {
		return ok;
	}

	public void setOk(LinkedList<IModel> ok) {
		this.ok = ok;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "JSON_OK";
	}
	
	

}
