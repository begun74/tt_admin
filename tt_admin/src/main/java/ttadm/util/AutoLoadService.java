package ttadm.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoLoadService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084790110880766478L;

	@Autowired
	private static ProcessingFiles processingFiles;
	
	
	@PostConstruct
	void init() {
		
		System.out.println("======== AutoLoadService @PostConstruct ");
	}


	@PreDestroy
	void destr() {
		
		System.out.println("======== AutoLoadService @PreDestroy ");
	}


	public static void stopAutoLoad() {
		// TODO Auto-generated method stub
		processingFiles.stopAutoLoad();
	}


	public static void startAutoLoad(List<Handler> listHandler) {
		// TODO Auto-generated method stub
		processingFiles.startAutoLoad(listHandler);
	}

}
