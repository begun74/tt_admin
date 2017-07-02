package ttadm.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;


@Entity
@Table(name = "orders")
@Scope("request")
public class Order implements IModel {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8125750113397192529L;
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_orders", nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private Timestamp creation_date; 
	
	
	//@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
	//@Pattern(regexp="\\+\\d{3}\\s\\d{2,4}\\s\\d{3}-\\d{2}-\\d{2}")
	//@NotEmpty (message = "Please enter phone.")
	//@Pattern(regexp="\\d")
	private String phone;
		
	private String email;
	
	@Size(min = 2, max = 20, message = "Please enter person_name.")
	private String person_name;

	@Size(min = 0, max = 100)
	private String comment;

	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="order")
	@Fetch(FetchMode.SELECT)
	private List<OrderItems> orderItems ;
	
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


	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((Order)o).getId().compareTo(getId());
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", creation_date=" + creation_date + "]";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Order "+id;
	}
	
	

}
