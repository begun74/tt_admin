package tt.modelattribute;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



public interface IMAmodel extends Serializable {
	
	public int getRow() ;
	
	public void setRow(int row);
	
	public long  getSerialversionuid();
	
	public boolean isAutoload();
	
}
