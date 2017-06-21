package ttadm.model;

import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class JSON_OrderItems implements IModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -598648911693334644L;


	private Long id;
	
	//----- DirNomenclature
	private Long code;

	private String name;

	private String article;
	
	private String model;
	//----- DirNomenclature

	//----- OrderItems
	private String size;
	
	private int amount;

	private Timestamp create_date;
	
	private Timestamp destruction_date;
	//----- OrderItems
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	
	
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getDestruction_date() {
		return destruction_date;
	}

	public void setDestruction_date(Timestamp destruction_date) {
		this.destruction_date = destruction_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((JSON_OrderItems)o).getId().compareTo(getId());
	}
	
}
