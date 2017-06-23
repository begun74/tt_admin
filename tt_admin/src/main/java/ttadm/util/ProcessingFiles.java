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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tt.modelattribute.IMAmodel;

import ttadm.bean.AdminSessionBean;
import ttadm.model.IModel;

@Service
public class ProcessingFiles implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3365768209076569422L;

	@Autowired
	private  XLS_fileHandler xls_fileHandler ;
	
	//@Autowired
	//private AdminSessionBean adminSessBean;
	
	//@Autowired
	//private FileUpload fileUpload ;

	private static ScheduledExecutorService service ;
	private static ExecutorService photoFileService ;

	@Autowired 
	private HttpSession httpSession;
	
	private static int pool_size = 1;


	@PostConstruct
	void init() {
		
		photoFileService = Executors.newFixedThreadPool(pool_size);
		
		System.out.println("ProcessFiles @PostConstruct ");
	}

	
	
	
	public  void loadFile(IModel IModel,MultipartFile file, IMAmodel IMAmodel) 
	{
		
		System.out.println(httpSession.getAttribute("adminCtrl"));
		System.out.println(httpSession.getAttribute("sessionBean"));
		xls_fileHandler.loadXLS(IModel,file, IMAmodel);
		photoFileService.submit(xls_fileHandler);
	}	
	
	
	

	
	public static void startAutoLoad(List<Handler> pool)
	{
		pool_size = pool.size() == 0?pool_size:pool.size();
		
		service = Executors.newScheduledThreadPool(pool_size);
		for(Handler handler: pool)
			service.scheduleWithFixedDelay(handler, 5, 5, TimeUnit.SECONDS);
		
		
	}
	
	public static void stopAutoLoad()
	{
		service.shutdown();
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
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		} catch (InterruptedException e) {
			photoFileService.shutdownNow();
			System.out.println("===========photoFileService.shutdownNow ! =========");
		}
	}
	
	
	
	
	
	@PreDestroy
	void destr() {
		
		stopPhotoFileService();
		
		System.out.println("ProcessFiles @PreDestroy ");
	}

}
