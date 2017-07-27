package ttadm.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javafx.concurrent.Task;
import ttadm.bean.AdminSessionBean;
import ttadm.bean.AppBean;
import ttadm.model.IModel;
import ttadm.modelattribute.IMAmodel;

@Service
public class ProcessingFiles implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3365768209076569422L;

	@Autowired
	private  XLS_fileHandler xls_fileHandler ;
	
	
	private static ExecutorService photoFileService ;

	@Autowired 
	private HttpSession httpSession;
	
	private static int pool_size = 1;

	private static ScheduledExecutorService service ;
	
	private static boolean flag;
	



	@PostConstruct
	void init() {
		
		photoFileService = Executors.newFixedThreadPool(pool_size);
		
		System.out.println("ProcessFiles @PostConstruct ");
	}


	public  void loadFile(IModel IModel,MultipartFile file, IMAmodel IMAmodel) 
	{
		
		//System.out.println("adminCtrl - "+httpSession.getAttribute("adminCtrl"));
		//System.out.println("adminSessionBean - " +httpSession.getAttribute("adminSessionBean"));
		//XLS_fileHandler xls_fileHandler = xls_fileHandler();
		xls_fileHandler.loadXLS(IModel,file, IMAmodel);
		xls_fileHandler.injectAdminSessBean((AdminSessionBean)httpSession.getAttribute("adminSessionBean"));
		photoFileService.submit(xls_fileHandler);
	}	
	
	
	

	
	public static boolean startAutoLoad(List<Handler> pool)
	{
		pool_size = pool.size() == 0?pool_size:pool.size();
		
		
		if(!flag)
		{
			service = Executors.newScheduledThreadPool(pool_size);
			
			for(Handler handler: pool)
			{
				service.scheduleWithFixedDelay(handler, 1, 5, TimeUnit.SECONDS);
				flag = true;
			}
		
		}
		
		return flag;
	}
	
	
	public static boolean stopAutoLoad()
	{
		service.shutdown();
		flag = false;
		return flag;
	}
	
	
	public static void startPhotoFileService(HashMap<Long,String> hmPhotoFile) 
	{
		//photoFileService = Executors.newSingleThreadExecutor();
		
		for(Long code : hmPhotoFile.keySet())
			photoFileService.submit(new FileHandler(code, hmPhotoFile.get(code)));
	}

	public static void startPhotoFileService2(HashMap<Long,List<String>> hmPhotoFile) 
	{	
		//Загрузка фото
		//photoFileService = Executors.newFixedThreadPool(3);
		Future<Long> future = null;
		
		for(Long code : hmPhotoFile.keySet())
		{
			future = photoFileService.submit(new FileHandler(code, hmPhotoFile.get(code)));
		}
		
		if(future.isDone())
		{
			stopPhotoFileService();
			//System.out.println("startPhotoFileService2.isDown() - " + future.isDone());
		}
	}

	
	public static void stopPhotoFileService() 
	{
		try {
			//System.out.println("=========== stopPhotoFileService =========");
			photoFileService.awaitTermination(3, TimeUnit.SECONDS);
			System.out.println("=========== photoFileService.awaitTermination ! =========");
			
			service.awaitTermination(3, TimeUnit.SECONDS);
			System.out.println("=========== service.awaitTermination ! =========");
			
		} catch (Exception e) {
			
			photoFileService.shutdownNow();
			System.out.println("===========photoFileService.shutdownNow ! =========");

			service.shutdownNow();
			System.out.println("===========service.shutdownNow ! =========");
		}
	}
	
	
	
	
	
	@PreDestroy
	void destr() {
		
		stopPhotoFileService();
		
		System.out.println("ProcessFiles @PreDestroy ");
	}

}
