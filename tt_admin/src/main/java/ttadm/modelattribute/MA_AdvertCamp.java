package ttadm.modelattribute;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;


@Component
@Scope("session")
@SessionAttributes("mA_AdvertCamp")
public class MA_AdvertCamp  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1649313390929297781L;
	
	
	
	private Long id = null;
	
	@NotBlank
	private String name;

	@NotBlank
	private String text;

	private String text_to_slider;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank
	private String fromDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank
	private String toDate;
	
	private Boolean active = Boolean.FALSE;

	
	
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	public String getText_to_slider() {
		return text_to_slider;
	}

	public void setText_to_slider(String text_to_slider) {
		if(text_to_slider.trim().length() == 0)
			text_to_slider = null;
		this.text_to_slider = text_to_slider;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MA_AdvertCamp [name=" + name + ", text=" + text + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", butAdvCamp=" + active + "]";
	}

	
	

}
