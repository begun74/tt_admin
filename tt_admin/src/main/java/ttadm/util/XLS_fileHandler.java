package ttadm.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tt.modelattribute.IMAmodel;
import tt.modelattribute.MA_loadProvider;
import ttadm.bean.AdminSessionBean;
import ttadm.model.DirProvider;
import ttadm.model.IModel;


@Service

public class XLS_fileHandler  implements Callable<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4561488337396875362L;
	
	private File file;
	
	private File TEMP_FILE_PATH = Constants.tempDirectory;
	private static final String[][] ALLOWED_FILE_TYPES_PICS = {{"image/jpeg","jpeg"}, {"image/jpg","jpg"}, {"image/gif","gif"}};
	private static final String[][] ALLOWED_FILE_TYPES_XLS = {{"application/vnd.ms-excel","xls"},{"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xlsx"}};
	
	
	@Autowired
	private FileUpload fileUpload ;
	
	@Autowired 
	private HttpSession httpSession;
	
	
	private IMAmodel IMAmodel;
	private IModel IModel;
	
	@PostConstruct
	void init() {
		System.out.println("XLS_fileHandler - @PostConstruct");
	}
	
	
	
	
	
	public XLS_fileHandler(MultipartFile file)  
	{
		if (!file.isEmpty())
		{
			String contentType = file.getContentType().toString().toLowerCase();
			String extention ;
			
			if ((extention = isValidContentType(ALLOWED_FILE_TYPES_XLS,contentType)) != null) {
            	File tmpFile = new File(TEMP_FILE_PATH + File.separator+"tmp."+extention );
			
				try {
					file.transferTo(tmpFile);
	
					this.file = tmpFile;
					System.out.println("File - " +file.getName());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	
	public XLS_fileHandler() 
	{

	}

	public void loadXLS(IModel IModel, MultipartFile file, IMAmodel IMAmodel)
	{
		if (!file.isEmpty())
		{
			this.IMAmodel = IMAmodel;
			this.IModel = IModel;
			
			String contentType = file.getContentType().toString().toLowerCase();
			String extention ;
			
			if ((extention = isValidContentType(ALLOWED_FILE_TYPES_XLS,contentType)) != null) {
            	File tmpFile = new File(TEMP_FILE_PATH + File.separator+"tmp."+extention );
			
				try {
					file.transferTo(tmpFile);
	
					this.file = tmpFile;
					System.out.println("File - " +file.getName());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public Long call() throws Exception {
		// TODO Auto-generated method stub
		Collection<?> collection = fileUpload.process(IModel, file, IMAmodel);
		
		//System.out.println(((AdminSessionBean)httpSession.getAttribute("sessionBean")).getHmLog_LoadMA_loadProvider());
		//((AdminSessionBean)httpSession.getAttribute("sessionBean")).addToHmLog_LoadMA_loadProvider(System.currentTimeMillis(), "Добавлено "+collection.size()+" поставщиков");
		System.out.println("File - " + collection.size());
		
		return (long) collection.size();
	}
	
	
	private String isValidContentType(String[][] ALLOWED_FILE_TYPES,String contentType) {
    	//System.out.println("contentType - "+contentType);
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return ext[1]; 
        
        return null;
    }


	
	@PreDestroy
	void destr() {
		System.out.println("XLS_fileHandler - @PreDestroy");
	}
}
