package tt.modelattribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value="session")
public class MA_loadTail implements IMAmodel {

		/**
	 * 
	 */
	private static final long serialVersionUID = 3950229997785369170L;
	
	private int row=1;
	private int col_amountTail = 1; //Кол-во
	private int col_firstPrice = 2;  //Первая цена
	//private int col_codeProvider = 3; //Код поставщика
	private int col_codeNomencl = 4; //Код номенклатуры
	private int col_size=6;

	private int nds=0; //НДС
	private int nadb_opt=0; //Надбавка оптовая
	private int nadb_rozn=0; //Надбавка розничная
	private int rozn_price=0; //Розничная цена
	
	private transient boolean save;
	private transient boolean deleteOldTails;
	private boolean autoload;


	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void setRow(int row) {
		// TODO Auto-generated method stub
		this.row = row;
	}
	
	

	

	public int getCol_amountTail() {
		return col_amountTail;
	}

	public void setCol_amountTail(int col_amountTail) {
		this.col_amountTail = col_amountTail;
	}

	public int getCol_firstPrice() {
		return col_firstPrice;
	}

	public void setCol_firstPrice(int col_firstPrice) {
		this.col_firstPrice = col_firstPrice;
	}

	public boolean isSave() {
		return save;
	}
	
	public int getCol_size() {
		return col_size;
	}

	public void setCol_size(int col_size) {
		this.col_size = col_size;
	}
	

	public void setSave(boolean save) {
		this.save = save;
	}
	
	

	public boolean isDeleteOldTails() {
		return deleteOldTails;
	}

	public void setDeleteOldTails(boolean deleteOldTails) {
		this.deleteOldTails = deleteOldTails;
	}

	public boolean isAutoload() {
		return autoload;
	}

	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}
	
	public int getCol_codeNomencl() {
		return col_codeNomencl;
	}

	public void setCol_codeNomencl(int col_codeNomencl) {
		this.col_codeNomencl = col_codeNomencl;
	}


	public int getNds() {
		return nds;
	}

	public void setNds(int nds) {
		this.nds = nds;
	}

	public int getNadb_opt() {
		return nadb_opt;
	}

	public void setNadb_opt(int nadb_opt) {
		this.nadb_opt = nadb_opt;
	}

	public int getNadb_rozn() {
		return nadb_rozn;
	}

	public void setNadb_rozn(int nadb_rozn) {
		this.nadb_rozn = nadb_rozn;
	}

	public int getRozn_price() {
		return rozn_price;
	}

	public void setRozn_price(int rozn_price) {
		this.rozn_price = rozn_price;
	}

	@Override
	public long getSerialversionuid() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MA_loadTail [row=" + row + ", col_amountTail=" + col_amountTail + ", col_firstPrice=" + col_firstPrice
				+ ", autoload=" + autoload + "]";
	}
	
	
	

}
