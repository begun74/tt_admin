package ttadm.model;

import java.math.BigInteger;

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

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "dir_nomencl_group")
public class DirNomenclGroup implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -389035476310981392L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_dir_nomencl_group")
	private Long id;

	
	@Column(name="code")
	@NotNull (message = "Please enter code.") 
	private Long code;

	@NotEmpty (message = "Please enter name.") 
	private String name;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_dir_nomencl_group_root")
	@NotNull
	private DirNomenclGroupRoot dirNomenclGroupRoot;
	

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
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


	
	public DirNomenclGroupRoot getDirNomenclGroupRoot() {
		return dirNomenclGroupRoot;
	}

	public void setDirNomenclGroupRoot(DirNomenclGroupRoot dirNomenclGroupRoot) {
		this.dirNomenclGroupRoot = dirNomenclGroupRoot;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ( ((DirNomenclGroup)o).getCode().compareTo(this.getCode()) );
	}

}
