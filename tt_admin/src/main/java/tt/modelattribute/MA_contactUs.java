package tt.modelattribute;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope(value="session")
public class MA_contactUs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930619694465138014L;

}
