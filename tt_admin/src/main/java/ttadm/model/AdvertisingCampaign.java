package ttadm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "advert_campaign")
public class AdvertisingCampaign implements  IModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6513655766251803087L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_advert_camp")
	public Long id;

	@Column(name="name")
	@NotNull
	private String name;

	@Column(name="text")
	@NotNull
	private String text;
	
	@NotNull
	private Timestamp create_date;
	
	@NotNull
	private Timestamp fromDate;

	@NotNull
	private Timestamp toDate;

	
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "advert_campaign";
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
