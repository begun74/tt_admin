package ttadm.modelattribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value="session")
public class MA_loadNomenclGroupRoot implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2711186834366245506L;
	/**
	 * 
	 */
	
	
	private int row=1;
	private int col_name=1;
	private int col_code=2;

	private transient  boolean save;
	private boolean autoload;
	private final int autoloadPriority = 1;
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	
	public int getCol_name() {
		return col_name;
	}
	
	public void setCol_name(int col_name) {
		this.col_name = col_name;
	}
	
	public int getCol_code() {
		return col_code;
	}
	
	public void setCol_code(int col_code) {
		this.col_code = col_code;
	}
	
	public boolean isSave() {
		return save;
	}
	
	public void setSave(boolean save) {
		this.save = save;
	}
	
	public boolean isAutoload() {
		return autoload;
	}
	
	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}
	
	
	public int getAutoloadPriority() {
		return autoloadPriority;
	}
	
	
	@Override
	public long getSerialversionuid() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MA_loadNomenclGroupRoot [row=" + row + ", col_name=" + col_name + ", col_code=" + col_code
				+ ", autoload=" + autoload + "]";
	}

	
	
	

}
