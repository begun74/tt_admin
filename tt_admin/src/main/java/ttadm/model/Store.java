package ttadm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "store")
public class Store {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_store")
	private long id;
	
	
	private Long serialVersionUID;
	
	//@Lob 
	private byte[] bytearray;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(Long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}

	public byte[] getBytearray() {
		return bytearray;
	}

	public void setBytearray(byte[] bytearray) {
		this.bytearray = bytearray;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", serialVersionUID = " + serialVersionUID + "]";
	}

	
	

}
