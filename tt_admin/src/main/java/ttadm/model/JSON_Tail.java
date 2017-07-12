package ttadm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class JSON_Tail implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2513917853165464466L;
	
	
	private Long id;
	private Long code;
	
	private String name;

	private int amountTail; //Кол-во
	
	private double firstPrice;  //Первая цена
	
	private Timestamp create_date;
	
	private Timestamp destruction_date;

	private String size;

	private Integer nds; //НДС

	private Integer nadb_opt; //Надбавка оптовая

	private Integer nadb_rozn; //Надбавка розничная

	private Double rozn_price;  //Розничная цена
	
	private Double opt_price;  //Оптовая цена

	private int index;
	
	//private DirNomenclature dirNomenclature;

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountTail() {
		return amountTail;
	}

	public void setAmountTail(int amountTail) {
		this.amountTail = amountTail;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getNds() {
		return nds;
	}

	public void setNds(Integer nds) {
		this.nds = nds;
	}

	public Integer getNadb_opt() {
		return nadb_opt;
	}

	public void setNadb_opt(Integer nadb_opt) {
		this.nadb_opt = nadb_opt;
	}

	public Integer getNadb_rozn() {
		return nadb_rozn;
	}

	public void setNadb_rozn(Integer nadb_rozn) {
		this.nadb_rozn = nadb_rozn;
	}

	public Double getRozn_price() {
		return rozn_price;
	}

	public void setRozn_price(Double rozn_price) {
		this.rozn_price = rozn_price;
	}

	public Double getOpt_price() {
		return opt_price;
	}

	public void setOpt_price(Double opt_price) {
		this.opt_price = opt_price;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "JSON_Tail [id=" + id + ", name=" + name + ", amountTail=" + amountTail + ", firstPrice=" + firstPrice
				+ ", create_date=" + create_date + ", destruction_date=" + destruction_date + ", size=" + size
				+ ", nds=" + nds + ", nadb_opt=" + nadb_opt + ", nadb_rozn=" + nadb_rozn + ", rozn_price=" + rozn_price
				+ ", opt_price=" + opt_price + "]";
	}

	
	
}
