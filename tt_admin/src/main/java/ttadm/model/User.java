package ttadm.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tt_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 565L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_user")
	private Long id;
	
	@NotEmpty (message = "Please enter name.") 
	private String name;
	
	@NotEmpty (message = "Please enter password.") 
	private String password;
	
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_with_role",
            joinColumns = @JoinColumn(name = "tt_user_id"),
            inverseJoinColumns = @JoinColumn(name = "tt_user_role_id")
    )
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	
	
	public User()	{}
	
	public User(String name,String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String password,
			boolean enabled, Set<UserRole> userRole) {
			this.name = name;
			this.password = password;
			this.enabled = enabled;
			this.userRole = userRole;
	}
	
	public User(String name, String password, boolean enabled) {
		this.name = name;
		this.password = password;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name = " + name + "]";
	}

	
}
