package ttadm.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "tails")
public class Tail implements  IModel {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3431951539246492099L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_tails")
	private Long id;
	
	@NotNull
	@Column(name="amounttail")
	private int amountTail; //Кол-во
	
	@NotNull
	@Column(name="firstPrice")
	private double firstPrice;  //Первая цена
	
	@NotNull
	private Timestamp create_date;
	
	private Timestamp destruction_date;

	private String size;

	//@NotNull
	@Column(name="nds")
	private Integer nds; //НДС

	//@NotNull
	@Column(name="nadb_opt")
	private Integer nadb_opt; //Надбавка оптовая

	//@NotNull
	@Column(name="nadb_rozn")
	private Integer nadb_rozn; //Надбавка розничная

	@NotNull
	@Column(name="rozn_price")
	private Double rozn_price;  //Розничная цена
	
	@NotNull
	@Column(name="opt_price")
	private Double opt_price;  //Оптовая цена
	
	@ManyToOne(fetch = FetchType.EAGER)
	//@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "fk_id_nomenclature", nullable=false)
	@NotNull
	private DirNomenclature dirNomenclature;
	
	
	
	private transient int index;
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

/*
	public DirProvider getDirProvider() {
		return dirProvider;
	}

	public void setDirProvider(DirProvider dirProvider) {
		this.dirProvider = dirProvider;
	}
	*/
	
	public DirNomenclature getDirNomenclature() {
		return dirNomenclature;
	}
	public void setDirNomenclature(DirNomenclature dirNomenclature) {
		this.dirNomenclature = dirNomenclature;
	}

	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
	public String toString() {
		return "Tail [id=" + id + ", amountTail=" + amountTail + ", firstPrice=" + firstPrice + ", create_date="
				+ create_date + ", destruction_date=" + destruction_date + ", size=" + size + ", rozn_price="
				+ rozn_price + ", opt_price=" + opt_price + "]";
	}

	@Override
	public int compareTo(Object o) {
		
		int cSize = ((Tail)o).getSize().compareTo(this.getSize());
		int cDN	  = ((Tail)o).getDirNomenclature().compareTo(this.getDirNomenclature());
		
		return cSize+cDN;
	}
	
	
	

}
