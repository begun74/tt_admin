package ttadm.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ttadm.model.*;
import ttadm.modelattribute.IMAmodel;
import ttadm.service.TT_AdminServiceImpl;


@Component(value = "appBean")
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7749935089438907692L;
	
	@Resource
    private Environment env;
	
	@Autowired
	private TT_AdminServiceImpl ttadmService;  //Service which will do all data retrieval/manipulation work
	
	private HashMap<Long,Object> mapStore = new HashMap<Long,Object>();
	
	private List<DirProvider> listProviders = new ArrayList<DirProvider>();
	
	private double version = 1.1;
	
	private  String IMAGES_SERVER = null;
	
	
	public double getVersion() {
		return version;
	}


	public void setVersion(double version) {
		this.version = version;
	}


	@PostConstruct
	void init() {
		
		try {
		
				Iterator<Store> iterStore = ttadmService.getStoreList().iterator();
				
				while(iterStore.hasNext())
				{
					Store s = (Store)iterStore.next();
					try {
						mapStore.put(s.getSerialVersionUID(), bytesToObject(s.getBytearray()));
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println(s + "  not found in store!");
					}
				}
		}
		catch(Exception exc) {
			System.out.println("AppBean.init() - ttadmService.getStoreList() is null");
		}
		//listProviders = ttService.getProviderList();
		//System.out.println("mapStore - "+mapStore);
		//System.out.println("AppBean @PostConstruct ");
	}
	
	
	public List<DirProvider> getListProviders() {
		return listProviders;
	}


	public void addToMapStore(IMAmodel IMAmodel) throws Exception 
	{
			Store s = ttadmService.getStoreBySerVerUID(IMAmodel.getSerialversionuid()) == null? new Store() : ttadmService.getStoreBySerVerUID(IMAmodel.getSerialversionuid());
			//Store s = new Store();
			s.setSerialVersionUID(IMAmodel.getSerialversionuid());
			s.setBytearray(objectToBytes(IMAmodel));
			
			//ttadmService.getStore(s)
			ttadmService.addStore(s);
			
			mapStore.put(s.getSerialVersionUID(), bytesToObject(s.getBytearray()));
	}


	public List<IMAmodel> getAutoLoad_IMAmodels()
	{
		List<IMAmodel> listAutoLoads = new ArrayList();
		for(Object MAmodel:mapStore.values())
		{
			try {
				//if( ((IMAmodel)MAmodel).isAutoload() )
				listAutoLoads.add((IMAmodel) MAmodel);
			}
			catch(ClassCastException cce){
				cce.getMessage();
			}
		}
		return listAutoLoads;
	}
	
	public Object findBySerialVerUID(Long serialVersionUID)
	{
		try {
			return mapStore.get(serialVersionUID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	


	public String getIMAGES_SERVER() {
		IMAGES_SERVER = env.getRequiredProperty("IMAGES_SERVER");
		return IMAGES_SERVER;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private Object bytesToObject(byte[] binary) throws IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		Object obj = new Object();

		ByteArrayInputStream byteIn = new ByteArrayInputStream(binary);
		ObjectInputStream objIn = new ObjectInputStream(byteIn);
		obj = objIn.readObject();
		objIn.close();
		byteIn.close();

		return obj;
	}

	private byte[] objectToBytes(IMAmodel IMAmodel) throws IOException{
		// TODO Auto-generated method stub

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		objOut.writeObject(IMAmodel);
		objOut.close();
		byteOut.close();
		byte[] bytes = byteOut.toByteArray();
		

		return bytes;
	}



	@PreDestroy
	void destr() {
		System.out.println("AppBean @PreDestroy ");
	}



}
