package ttadm.controller;



import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ttadm.modelattribute.MA_loadTempTail;
import ttadm.modelattribute.MA_loadNomenclGroupRoot;
import ttadm.bean.AdminSessionBean;
import ttadm.bean.AppBean;
import ttadm.model.AdvertisingCampaign;
import ttadm.model.DirNomenclGroup;
import ttadm.model.DirNomenclGroupRoot;
import ttadm.model.DirNomenclature;
import ttadm.model.DirProvider;
import ttadm.model.Tail;
import ttadm.modelattribute.IMAmodel;
import ttadm.modelattribute.MA_AdvertCamp;
import ttadm.modelattribute.MA_loadNomencl;
import ttadm.modelattribute.MA_loadNomenclGroup;
import ttadm.modelattribute.MA_loadProvider;
import ttadm.modelattribute.MA_loadTail;
import ttadm.service.TT_AdminServiceImpl;
import ttadm.util.AutoLoadService;
import ttadm.util.Handler;
import ttadm.util.ProcessingFiles;




@Controller
@Scope("request")
@SessionAttributes("adminCtrl")
@RequestMapping(value = {"/","/admin"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	@Autowired
	private AppBean appBean;

	@Autowired
	private AdminSessionBean adminSessBean;
	
	@Autowired
	private ProcessingFiles processingFiles;
	
	@Autowired
	AutoLoadService AutoLoadService;
	
	@Autowired
	private TT_AdminServiceImpl ttadmService;  //Service which will do all data retrieval/manipulation work


	
	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public ModelAndView  indexGet(HttpSession session, 
				@RequestParam(value = "logout",	required = false) String logout) 
	{
		ModelAndView model = new ModelAndView("index");
		
		if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			
			return model;
		}
		
		return model;
	}

	
	@RequestMapping(value = {"/admin"},method = RequestMethod.GET)
	public ModelAndView  loginPOST(HttpSession session, @RequestParam(value = "logout",	required = false) String logout,
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error,
				@RequestParam(value = "p", defaultValue = "1") int p,
				@RequestParam(value = "p_p", defaultValue = "20") int perPage,
				@RequestParam(value = "sortby",  required=false) String sortby) 
	{
		ModelAndView model = new ModelAndView("main");

		if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			model = new ModelAndView("redirect:/admin");
			return model;
		}
		
		
		switch (act)
		{
			case "1":
				model = new ModelAndView("addProvider");
			break;

			case "2":
				Object[] nomencList = ttadmService.getNomenclatureList(p,perPage, sortby);
				model = new ModelAndView("addNomencl");
				model.addObject("dirNomencls", nomencList[1]);
				model.addObject("allItems", nomencList[0]);
				model.addObject("sortby", sortby);
			break;

			case "3":
				Object[] tailsList = ttadmService.getTailsList(p,perPage, sortby);
				model = new ModelAndView("addTails");
				model.addObject("tempTails", adminSessBean.getTempListTails());
				model.addObject("tails", tailsList[1]);
				model.addObject("allItems", tailsList[0]);
				model.addObject("sortby", sortby);
			break;

			case "4":
				model = new ModelAndView("addNomenclGroup");
				model.addObject("dirNomenclGroups", ttadmService.getNomenclGroupList());
			break;

			case "5":
				model = new ModelAndView("autoLoad");
				model.addObject("autoLoadIMAmodels", appBean.getAutoLoad_IMAmodels());
			break;

			case "6":
				model = new ModelAndView("addNomenclGroupRoot");
				model.addObject("dirNomenclGroupRoots", ttadmService.getNomenclGroupRootList());
			break;
		}
		
		//model.addObject("error",adminSessBean.getErrorList().toString().length() > 512 ?adminSessBean.getErrorList().toString().substring(0, 512)+" ...":adminSessBean.getErrorList());
		model.addObject("sessionBean",adminSessBean);
		model.addObject("providers", ttadmService.getProviderList());
		
		
		return model;
	}

	
	@RequestMapping(value = {"addFileProvider"} , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileProvidere( @ModelAttribute  MultipartFile file,
										@Valid MA_loadProvider mA_loadProvider ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);

		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		
		if(mA_loadProvider.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadProvider);
				adminSessBean.setmA_loadProvider(mA_loadProvider);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		
		adminSessBean.setmA_loadProvider(mA_loadProvider);
		
		
		processingFiles.loadFile(new DirProvider(), file, adminSessBean.getmA_loadProvider());
		
		return model;
	}
	
	
	@RequestMapping(value = {"addFileNomencl"} , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileNomencl( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomencl mA_loadNomencl ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomencl.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomencl);
				adminSessBean.setmA_loadNomencl(mA_loadNomencl);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		
		
		adminSessBean.setmA_loadNomencl(mA_loadNomencl);
		processingFiles.loadFile(new DirNomenclature(), file, adminSessBean.getmA_loadNomencl());
		
		return model;
	}	
	
	
	@RequestMapping(value = {"addFileNomenclGroup"} , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileNomenclGroup( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomenclGroup mA_loadNomenclGroup ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomenclGroup.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomenclGroup);
				adminSessBean.setmA_loadNomenclGroup(mA_loadNomenclGroup);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}
		
		
		adminSessBean.setmA_loadNomenclGroup(mA_loadNomenclGroup);
		processingFiles.loadFile(new DirNomenclGroup(), file, adminSessBean.getmA_loadNomenclGroup());
		
		return model;
	}	
	
	

		
	@RequestMapping(value = "addFileNomenclGroupRoot" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileNomenclGroupRoot( @ModelAttribute  MultipartFile file,
										@Valid MA_loadNomenclGroupRoot mA_loadNomenclGroupRoot ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadNomenclGroupRoot.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadNomenclGroupRoot);
				adminSessBean.setmA_loadNomenclGroupRoot(mA_loadNomenclGroupRoot);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}

		adminSessBean.setmA_loadNomenclGroupRoot(mA_loadNomenclGroupRoot);
		processingFiles.loadFile(new DirNomenclGroupRoot(), file, adminSessBean.getmA_loadNomenclGroupRoot());
		
		return model;
	}	


	@RequestMapping(value = "addTempFileTail" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processTempFileTail( @ModelAttribute  MultipartFile file,
										@Valid MA_loadTail mA_loadTail ,
										BindingResult result,
										@RequestParam(value = "act",   defaultValue = "-1", required=true) int act) 
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		if(result.hasErrors())
		{
			adminSessBean.addError("Правильно введите данные!");
			return model;
		}
		
		
		if(mA_loadTail.isSave())
		{
			try {
				appBean.addToMapStore(mA_loadTail);
				adminSessBean.setmA_loadTail(mA_loadTail);
			}
			catch(org.springframework.dao.DataIntegrityViolationException dve) 
			{
				dve.printStackTrace();
				adminSessBean.getErrorList().add("Настройки уже существуют! ");
			}
			catch(Exception ioe)
			{
				ioe.printStackTrace();
				adminSessBean.getErrorList().add("Параметры не записаны! ");
			}
		}

		
		adminSessBean.setmA_loadTail(mA_loadTail);
		processingFiles.loadFile(new Tail(), file, adminSessBean.getmA_loadTail());
		
		return model;
	}	
	
	
	@RequestMapping(value = "addFileTail" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView   processFileTail(@ModelAttribute MA_loadTempTail mA_loadTempTail, 
														@RequestParam(value = "act",   defaultValue = "-1", required=true) int act,
														@RequestParam(value = "deleteOldTails",   required=false) boolean deleteOldTails)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		long time = System.currentTimeMillis();
		try {
				//Сохраняем "галку" deleteOldTails
				adminSessBean.getmA_loadTail().setDeleteOldTails(deleteOldTails);
				appBean.addToMapStore(adminSessBean.getmA_loadTail());

				if(deleteOldTails)
					ttadmService.updateTails();

				
					synchronized(this) {
						
						ttadmService.addTails(adminSessBean.getTempListTails());

						adminSessBean.getTempListTails().clear();
						
						System.gc();
						
					}
					
						
	
		}
		catch (javax.validation.ConstraintViolationException cve)
		{
			adminSessBean.addError(cve.getLocalizedMessage());
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					adminSessBean.addError("Ошибка загрузки остатков!");
		}
		
		time = (System.currentTimeMillis() - time)/1000;
		System.out.println("time addFileTail - " + time+" sec.");
		return model;
	}
	
	
	@RequestMapping(value = "autoLoad" , method = RequestMethod.POST )
	public ModelAndView   processAutoLoad(@RequestParam(value = "act",   defaultValue = "-1", required=true) int act,
											@RequestParam(value = "status",   required=false) int status)
	{
		ModelAndView model = new ModelAndView("redirect:/admin?act="+act);
		
		List<Handler> listHandler = new LinkedList();

		for(IMAmodel mam: appBean.getAutoLoad_IMAmodels())
			listHandler.add(new Handler(mam));
		
		
		switch (status) {
			
			case 0:
				appBean.setAutoLoadFile(processingFiles.stopAutoLoad());
				break;
			
			case 1:
				appBean.setAutoLoadFile(processingFiles.startAutoLoad(listHandler));
				break;
		}

		//model.addObject("autoLoadFile", appBean.isAutoLoadFile());

		return model;
	}	

	
	@RequestMapping(value = "/content" , method = RequestMethod.POST )
	public ModelAndView   content(HttpSession session
									, @Valid MA_AdvertCamp mA_AdvertCamp 
									, BindingResult result
									, @RequestParam(value = "act",	 defaultValue = "0") int act
									, @RequestParam(value = "logout",	required = false) String logout
									, @RequestParam(value = "id",	required = false) Long id)
	{
		ModelAndView model = new ModelAndView("redirect:/content?act="+act);
		
		if (logout != null) {
			SecurityContextHolder.clearContext();
			session.invalidate();
			
			return new ModelAndView("redirect:/");
		}

		if(result.hasErrors() && (act == 1 || act == 2))
		{
			adminSessBean.addError("Правильно введите ВСЕ данные!");
			
			model = new ModelAndView("actions/main");
			model.addObject("advCamps", ttadmService.getAdvCampList(true));
			
			return model;
		}		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		
		switch (act) {
		
			case 0:
				model = new ModelAndView("actions/main");
			break;

			case 1://add
				model = new ModelAndView("redirect:/content?act=0");
				AdvertisingCampaign advCamp = new AdvertisingCampaign();
				try {
					advCamp.setFromDate(new Timestamp(dateFormat.parse(mA_AdvertCamp.getFromDate()).getTime()));
					advCamp.setToDate(new Timestamp(dateFormat.parse(mA_AdvertCamp.getToDate()).getTime()));
					advCamp.setName(mA_AdvertCamp.getName().replaceAll("\"", " "));
					advCamp.setText(mA_AdvertCamp.getText().replaceAll("\"", " "));
					advCamp.setActive(new Boolean(mA_AdvertCamp.getActive()));
					ttadmService.saveIModel(advCamp);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				catch (DataIntegrityViolationException e) {
					adminSessBean.addError("С таким названием уже существует!");
					e.printStackTrace();
				}
			break;

			case 2: //update
				model = new ModelAndView("redirect:/content?act=0");
				advCamp = (AdvertisingCampaign)ttadmService.getObject(AdvertisingCampaign.class, id);
					if(advCamp !=null)
						try {
							advCamp.setFromDate(new Timestamp(dateFormat.parse(mA_AdvertCamp.getFromDate()).getTime()));
							advCamp.setToDate(new Timestamp(dateFormat.parse(mA_AdvertCamp.getToDate()).getTime()));
							advCamp.setName(mA_AdvertCamp.getName().replaceAll("\"", " "));
							advCamp.setText(mA_AdvertCamp.getText().replaceAll("\"", " "));
							advCamp.setActive(new Boolean(mA_AdvertCamp.getActive()));
							ttadmService.saveIModel(advCamp);
							
						} catch (ParseException e) {
							e.printStackTrace();
						}
						catch (DataIntegrityViolationException e) {
							adminSessBean.addError("С таким названием уже существует!");
							e.printStackTrace();
						}
			break;

			case 3: //delete
				if(id > 0)
				{
					advCamp = (AdvertisingCampaign) ttadmService.getObject(AdvertisingCampaign.class, id);
					ttadmService.delObject(advCamp);
				}
					
				model = new ModelAndView("redirect:/content?act=0");
			break;

		}
		
		model.addObject("advCamps", ttadmService.getAdvCampList());
		model.addObject("allAdvCamps", ttadmService.getAdvCampList());

		return model;
		
	}
	

	@PostConstruct
	void init(){
		//System.out.println(this + " INIT() ");
	}

	@PreDestroy
	void destr() {
		//System.out.println(this  +" DESTROY() ");
	}

	
}
