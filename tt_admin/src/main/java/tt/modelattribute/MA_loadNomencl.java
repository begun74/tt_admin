package tt.modelattribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value="session")
public class MA_loadNomencl  implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -765852209444376374L;


	
	private int row=1;
	private int col_name=1;
	private int col_code=2;
	private int col_article=3;
	private int col_codeNomenclGroup=4;
	private int col_gender=5;
	private int col_pathToImage=6;
	private int col_model=7;
	private int col_codeProvider = 8; //Код поставщика
	private int col_composition = 0; //Состав
	
	private transient  boolean save;
	private boolean autoload;
	
	
	
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void setRow(int row) {
		this.row=row;
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

	public int getCol_article() {
		return col_article;
	}

	public void setCol_article(int col_article) {
		this.col_article = col_article;
	}
	
	
	public int getCol_model() {
		return col_model;
	}

	public void setCol_model(int col_model) {
		this.col_model = col_model;
	}

	public int getCol_codeNomenclGroup() {
		return col_codeNomenclGroup;
	}

	public void setCol_codeNomenclGroup(int col_codeNomenclGroup) {
		this.col_codeNomenclGroup = col_codeNomenclGroup;
	}

	
	public int getCol_gender() {
		return col_gender;
	}

	public void setCol_gender(int col_gender) {
		this.col_gender = col_gender;
	}
	

	public int getCol_pathToImage() {
		return col_pathToImage;
	}

	public void setCol_pathToImage(int col_pathToImage) {
		this.col_pathToImage = col_pathToImage;
	}

	

	public int getCol_codeProvider() {
		return col_codeProvider;
	}

	public void setCol_codeProvider(int col_codeProvider) {
		this.col_codeProvider = col_codeProvider;
	}

	public int getCol_composition() {
		return col_composition;
	}

	public void setCol_composition(int col_composition) {
		this.col_composition = col_composition;
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

	public long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	@Override
	public String toString() {
		return "MA_loadNomencl [row=" + row + ", col_name=" + col_name + ", col_code=" + col_code + ", col_article="
				+ col_article + ", save=" + save + ", autoload=" + autoload + "]";
	}
	
	

}
