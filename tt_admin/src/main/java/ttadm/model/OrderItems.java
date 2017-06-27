package ttadm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "order_items")
public class OrderItems implements IModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4911618125897962158L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_order_items")
	private long id;
	
	@NotNull
	private int amount;

	@NotNull
	private Timestamp create_date;
	
	private Timestamp destruction_date;
	
	@NotNull
	@Column(name="price")
	private double price;  //цена
	
/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_dir_nomenclature", nullable=false)
	@NotNull
	private DirNomenclature dirNomenclature;
*/
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_tails", nullable=false)
	@NotNull
	private Tail tail;

	@ManyToOne(fetch = FetchType.LAZY)
	//@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name = "fk_id_orders", nullable=false)
	private Order order;
	
	
	private transient int npp;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}


	public void setId(long id) {
		this.id = id;
	}


	/*
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
*/
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Tail getTail() {
		return tail;
	}

	public void setTail(Tail tail) {
		this.tail = tail;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public int getNpp() {
		return npp;
	}

	public void setNpp(int npp) {
		this.npp = npp;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((OrderItems)o).getId().compareTo(getId());
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", amount=" + amount + ", create_date=" + create_date + ", destruction_date="
				+ destruction_date + ", price=" + price + ", order=" + order + "]";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "OrderItem "+id;
	}


	
}
