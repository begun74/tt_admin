package ttadm.model;

import java.io.Serializable;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "tt_user_role")
public class UserRole  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5132585896071532457L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_user_role")
	private Long id;
	
	@Column(name="name")
	@NotEmpty (message = "Please enter role name.") 
	private String role;
	
//	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_user_role", nullable=false)
	//private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
