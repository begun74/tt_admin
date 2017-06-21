package ttadm.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "dir_nomenclature")
@NamedQueries({ 
	//@NamedQuery(name = "DirNomenclature.findByidWithTails", query="select  distinct  dс  from  DirNomenclature  dс  where  dc.id_dir_nomenclature  =  :id") 
}  )
public class DirNomenclature implements  IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4460232450371446581L;

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_dir_nomenclature")
	private Long id;
	
	
	@Column(name="code")
	@NotNull (message = "Please enter code.") 
	private Long code;

	@NotEmpty (message = "Please enter name.") 
	private String name;

	
	@NotEmpty (message = "Please enter article.") 
	private String article;
	
	@NotEmpty (message = "Please enter model.") 
	private String model;
	
	private String composition;//Состав. 
	
	@NotNull (message = "Please enter access_date.") 
	private Date access_date ;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_dir_nomencl_group")
	@NotNull
	private DirNomenclGroup dirNomenclGroup;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_dir_gender")
	@NotNull
	private DirGender dirGender;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_provider")
	@NotNull
	private DirProvider dirProvider;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="dirNomenclature")
	@OrderBy("id DESC")
	private Set<Tail> tails;
	
	private transient double tempPrice;
	private transient double rozn_price;
	private transient double opt_price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public Date getAccess_date() {
		return access_date;
	}

	public void setAccess_date(Date access_date) {
		this.access_date = access_date;
	}

	public DirNomenclGroup getDirNomenclGroup() {
		return dirNomenclGroup;
	}

	public void setDirNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		this.dirNomenclGroup = dirNomenclGroup;
	}
	

	public DirGender getDirGender() {
		return dirGender;
	}

	public void setDirGender(DirGender dirGender) {
		this.dirGender = dirGender;
	}

	
	public DirProvider getDirProvider() {
		return dirProvider;
	}

	public void setDirProvider(DirProvider dirProvider) {
		this.dirProvider = dirProvider;
	}

	
	public Set<Tail> getTails() {
		return tails;
	}

	public void setTails(Set<Tail> tails) {
		this.tails = tails;
	}
	


	public double getTempPrice() {
		return tempPrice;
	}

	public void setTempPrice(double tempPrice) {
		this.tempPrice = tempPrice;
	}

	public double getRozn_price() {
		return rozn_price;
	}

	public void setRozn_price(double rozn_price) {
		this.rozn_price = rozn_price;
	}

	public double getOpt_price() {
		return opt_price;
	}

	public void setOpt_price(double opt_price) {
		this.opt_price = opt_price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	@Override
	public int compareTo(Object o) {
		return ( ((DirNomenclature)o).getCode().compareTo(this.getCode()) );
	}

	@Override
	public String toString() {
		return "DirNomenclature [id=" + id + ", code=" + code + ", name=" + name + ", article=" + article + ", model="
				+ model + ", dirNomenclGroup=" + dirNomenclGroup + ", dirGender=" + dirGender + ", dirProvider=" +dirProvider.getId() + "]";
	}
	
	

}
