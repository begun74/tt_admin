package ttadm.model;

import java.io.Serializable;
import java.math.BigInteger;

public interface IModel extends Comparable<Object> , Serializable {

	
	public Long getId();
	public void setId(Long id);
	
	public String getName();

}
