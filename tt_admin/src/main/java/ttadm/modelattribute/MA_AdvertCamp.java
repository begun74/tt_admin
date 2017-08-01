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
	
	
	@NotBlank
	private String name;

	@NotBlank
	private String text;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank
	private String fromDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank
	private String toDate;
	
	private int butAdvCamp;

	
	
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

	public int getButAdvCamp() {
		return butAdvCamp;
	}

	public void setButAdvCamp(int butAdvCamp) {
		this.butAdvCamp = butAdvCamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MA_AdvertCamp [name=" + name + ", text=" + text + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", butAdvCamp=" + butAdvCamp + "]";
	}

	
	

}
