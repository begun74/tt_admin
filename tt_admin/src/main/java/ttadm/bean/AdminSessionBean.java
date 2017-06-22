package ttadm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tt.modelattribute.MA_loadNomencl;
import tt.modelattribute.MA_loadNomenclGroup;
import tt.modelattribute.MA_loadNomenclGroupRoot;
import tt.modelattribute.MA_loadProvider;
import tt.modelattribute.MA_loadTail;
import ttadm.model.Tail;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AdminSessionBean  implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 3935902451114251503L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017739667981879557L;
	

	@Autowired
	private AppBean appBean;
	
	@Autowired
	@Qualifier("mA_loadProvider")
	private MA_loadProvider mA_loadProvider;
	
	@Autowired
	private MA_loadNomencl mA_loadNomencl;
	
	@Autowired
	private MA_loadNomenclGroup mA_loadNomenclGroup;
	
	@Autowired
	private MA_loadNomenclGroupRoot mA_loadNomenclGroupRoot;

	@Autowired
	private MA_loadTail mA_loadTail;
	
	private List<String> errorList = new ArrayList<String>();
	private List<String> successList = new ArrayList<String>();
	private List<Tail> tempListTails = new ArrayList<Tail>();
	



	public List<Tail> getTempListTails() {
		return tempListTails;
	}

	public void setTempListTails(List<Tail> tempListTails) {
		this.tempListTails = tempListTails;
	}
	
	

	public MA_loadNomencl getmA_loadNomencl() {
		return mA_loadNomencl;
	}

	public void setmA_loadNomencl(MA_loadNomencl mA_loadNomencl) {
		this.mA_loadNomencl = mA_loadNomencl;
	}



	public MA_loadProvider getmA_loadProvider() {
		return mA_loadProvider;
	}

	public void setmA_loadProvider(MA_loadProvider mA_loadProvider) {
		this.mA_loadProvider = mA_loadProvider;
	}

	
	
	public MA_loadTail getmA_loadTail() {
		return mA_loadTail;
	}

	public void setmA_loadTail(MA_loadTail mA_loadTail) {
		this.mA_loadTail = mA_loadTail;
	}

	
	
	public MA_loadNomenclGroup getmA_loadNomenclGroup() {
		return mA_loadNomenclGroup;
	}

	public void setmA_loadNomenclGroup(MA_loadNomenclGroup mA_loadNomenclGroup) {
		this.mA_loadNomenclGroup = mA_loadNomenclGroup;
	}

	
	public MA_loadNomenclGroupRoot getmA_loadNomenclGroupRoot() {
		return mA_loadNomenclGroupRoot;
	}

	public void setmA_loadNomenclGroupRoot(MA_loadNomenclGroupRoot mA_loadNomenclGroupRoot) {
		this.mA_loadNomenclGroupRoot = mA_loadNomenclGroupRoot;
	}


	public List<String> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<String> successList) {
		this.successList = successList;
	}




	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	
	public void addError(String error) {
		getErrorList().clear();
		getErrorList().add(error);
	}


	public void clearError() {
		getErrorList().clear();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@PostConstruct
	void init(){
		System.out.println("AdminSessionBean @PostConstruct ");
		
		if(appBean.findBySerialVerUID(mA_loadNomencl.getSerialversionuid()) == null)
			setmA_loadNomencl(new MA_loadNomencl());
		else
			this.setmA_loadNomencl((MA_loadNomencl) appBean.findBySerialVerUID(mA_loadNomencl.getSerialversionuid()) );

		if(appBean.findBySerialVerUID(mA_loadProvider.getSerialversionuid()) == null)
			setmA_loadProvider(new MA_loadProvider());
		else
			this.setmA_loadProvider((MA_loadProvider) appBean.findBySerialVerUID(mA_loadProvider.getSerialversionuid()) );

		if(appBean.findBySerialVerUID(mA_loadTail.getSerialversionuid()) == null)
			setmA_loadTail(new MA_loadTail());
		else
			this.setmA_loadTail((MA_loadTail) appBean.findBySerialVerUID(mA_loadTail.getSerialversionuid()) );
		
		setmA_loadNomenclGroup( (appBean.findBySerialVerUID(mA_loadNomenclGroup.getSerialversionuid()) == null?new MA_loadNomenclGroup(): (MA_loadNomenclGroup) appBean.findBySerialVerUID(mA_loadNomenclGroup.getSerialversionuid())));
		
		setmA_loadNomenclGroupRoot( (appBean.findBySerialVerUID(mA_loadNomenclGroupRoot.getSerialversionuid()) == null?new MA_loadNomenclGroupRoot(): (MA_loadNomenclGroupRoot) appBean.findBySerialVerUID(mA_loadNomenclGroupRoot.getSerialversionuid())));

	}
	
	
	@PreDestroy
	void destr() {
		System.out.println("AdminSessionBean @PreDestroy ");
	}


	

}
