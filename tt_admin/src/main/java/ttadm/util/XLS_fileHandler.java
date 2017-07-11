package ttadm.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
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

import ttadm.bean.AdminSessionBean;
import ttadm.model.DirProvider;
import ttadm.model.IModel;
import ttadm.model.Tail;
import ttadm.modelattribute.IMAmodel;
import ttadm.modelattribute.MA_loadProvider;
import ttadm.service.TT_AdminServiceImpl;


@Service
public class XLS_fileHandler  implements Runnable, AutoCloseable {

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
	private TT_AdminServiceImpl ttadmService;  //Service which will do all data retrieval/manipulation work

	
	private AdminSessionBean adminSessBean;
	
	
	private IMAmodel IMAmodel;
	private IModel IModel;
	
	private final Thread currThread;
	
	@PostConstruct
	void init() {
		System.out.println("XLS_fileHandler - @PostConstruct");
	}
	
	
	@PreDestroy
	void destr() {
		System.out.println("XLS_fileHandler - @PreDestroy");
	}
	
	
	
	public XLS_fileHandler(MultipartFile file)  
	{
		currThread = new Thread(this,"XLS_fileHandler(MultipartFile file)");
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
		currThread = new Thread(this,"XLS_fileHandler");
	}

	public void injectAdminSessBean(AdminSessionBean adminSessBean)
	{
		
		this.adminSessBean = adminSessBean;
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
					//System.out.println("File - " +file.getName());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public  void run() {
		// TODO Auto-generated method stub
		adminSessBean.getHmLog_Load(IMAmodel).clear();
		
		
		adminSessBean.addToHmLog_Load(IMAmodel,System.currentTimeMillis(), "Парсим файл "+file.getName() +".");
		
		
		int rec = 0;
		try {
			TreeSet<IModel> IModelSet = new TreeSet<IModel>();

			if(IModel instanceof Tail)
			{
				adminSessBean.setTempListTails((List<Tail>) fileUpload.process(IModel, file, IMAmodel));
				System.out.println("adminSessBean.getTempListTails() - "+adminSessBean.getTempListTails().size() );
				adminSessBean.addToHmLog_Load(IMAmodel, System.currentTimeMillis(), "Обработано "+adminSessBean.getTempListTails().size()+" записей.");
			}
			else
			{
			
					IModelSet.addAll((List<IModel>) fileUpload.process(IModel, file, IMAmodel));
					System.out.println("HmPollPaths - "+fileUpload.getHmPollPaths().size() );
		
					Thread.currentThread().sleep(2000);
					
					
					for(IModel imodel: IModelSet) 
					{
						try {
							ttadmService.saveIModel(imodel);
							rec++;
							adminSessBean.addToHmLog_Load(IMAmodel,System.currentTimeMillis(), "Обработана "+rec+" запись ...");
							
							//Thread.currentThread().sleep(100);
		
						}
						catch(org.springframework.dao.DataIntegrityViolationException dve) {
							//dve.printStackTrace(); 
							adminSessBean.getErrorList().add(imodel.getName()+" уже существует! ");
						}
						
					}
					
					adminSessBean.addToHmLog_Load(IMAmodel, System.currentTimeMillis(), rec+" успешно обработано!");
					Thread.currentThread().sleep(2000);
					
					
					if(fileUpload.getHmPollPaths().keySet().size() >0) 
					{
						rec=1;
						adminSessBean.addToHmLog_Load(IMAmodel,System.currentTimeMillis(), "Грузим картинки ...");
						Thread.currentThread().sleep(2000);
			
						for(Long code: fileUpload.getHmPollPaths().keySet())
						{
							try {
								fileUpload.downloadPhoto(code, fileUpload.getHmPollPaths().get(code));
								adminSessBean.addToHmLog_Load(IMAmodel,System.currentTimeMillis(), "Загружена "+fileUpload.getHmPollPaths().get(code)+"  ...");
							}
							catch(Exception exc) {
								adminSessBean.addError(exc.getMessage());
							}
						}
						adminSessBean.addToHmLog_Load(IMAmodel,System.currentTimeMillis(), "Загрузка картинок закончена!");
					}
			
			}//else
		}
		catch (java.lang.NumberFormatException nfe) {
			nfe.printStackTrace();
			adminSessBean.addError(nfe.getMessage());
			
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					adminSessBean.addError("Ошибка загрузки файла!");
		}

		
		
		//return rec;
	}
	
	
	private String isValidContentType(String[][] ALLOWED_FILE_TYPES,String contentType) {
    	//System.out.println("contentType - "+contentType);
    	List<String[]> lExt= Arrays.asList(ALLOWED_FILE_TYPES);
    	
    	for(String[] ext: lExt) 
    		if(ext[0].equals(contentType))
    			return ext[1]; 
        
        return null;
    }


	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub 
		System.out.println(currThread +" - close()");
		currThread.interrupt();
	}


	
}
