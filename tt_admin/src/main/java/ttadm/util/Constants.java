package ttadm.util;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextAwareProcessor;


@Component
public class Constants {

	@Autowired
    ServletContext context; 
	
	@Autowired
	HttpSession session;
	
	@Resource
    private Environment env;
	
    public static File tempDirectory = null;
    public static String IMAGES_SERVER = null;
    public static File UPLOAD_FILE_PATH = null;
    
    public Constants()
    {
    	
    }
    
    
	
	@PostConstruct
	void init(){
		
		IMAGES_SERVER = env.getRequiredProperty("IMAGES_SERVER");
		tempDirectory = (File) context.getAttribute("javax.servlet.context.tempdir");
		UPLOAD_FILE_PATH = new File(env.getRequiredProperty("UPLOAD_FILE_PATH"));
		//UPLOAD_FILE_PATH = new File(context.getRealPath("/pics/products/"));
		//System.out.println("getParent - " +new File(context.getRealPath("/")).getParent());
		System.out.println("Temp Directory " +tempDirectory);
		System.out.println("IMAGES_SERVER - " +IMAGES_SERVER);
		System.out.println("UPLOAD_FILE_PATH - " +UPLOAD_FILE_PATH);
		
	}
	
	@PreDestroy
	void destr() {
		//System.out.println("BacketBean @PreDestroy ");
	}

}
