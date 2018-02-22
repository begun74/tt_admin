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
@Table(name = "diff_of_tails")
public class NewTails implements Comparable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_diff_of_tails")
	
	
	public Long id;
	
	@NotNull
	@Column(name="max_create_date")
	private Timestamp max_create_date;
	
	@Column(name="fk_dir_nomenclature")
	@NotNull
	private Long fk_dir_nomenclature;

	public Long getId() {
		return id;
	}

	
	public NewTails(Timestamp max_create_date, Long fk_dir_nomenclature)
	{
		this.max_create_date = max_create_date;
		this.fk_dir_nomenclature = fk_dir_nomenclature;
	}


	public Long getFk_dir_nomenclature() 
	{
		return fk_dir_nomenclature;
	}


	public void setFk_dir_nomenclature(Long fk_dir_nomenclature) 
	{
		this.fk_dir_nomenclature = fk_dir_nomenclature;
	}


	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		return fk_dir_nomenclature.compareTo(((NewTails)obj).getFk_dir_nomenclature());
	}


}
