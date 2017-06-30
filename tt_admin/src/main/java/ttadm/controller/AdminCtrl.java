package ttadm.controller;


import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
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

import ttadm.modelattribute.MA_loadNomenclGroupRoot;
import ttadm.bean.AdminSessionBean;
import ttadm.bean.AppBean;
import ttadm.model.DirNomenclGroup;
import ttadm.model.DirNomenclGroupRoot;
import ttadm.model.DirNomenclature;
import ttadm.model.DirProvider;
import ttadm.model.Tail;
import ttadm.modelattribute.MA_loadNomencl;
import ttadm.modelattribute.MA_loadNomenclGroup;
import ttadm.modelattribute.MA_loadProvider;
import ttadm.modelattribute.MA_loadTail;
import ttadm.service.TT_AdminServiceImpl;
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
				@RequestParam(value = "error",   defaultValue = "") String error) 
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
				model = new ModelAndView("addNomencl");
				model.addObject("dirNomencls", ttadmService.getNomenclatureList());
			break;

			case "3":
				model = new ModelAndView("addTails");
				model.addObject("tempTails", adminSessBean.getTempListTails());
				model.addObject("tails", ttadmService.getTailsList());
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

	@PostConstruct
	void init(){
		//System.out.println(this + " INIT() ");
	}

	@PreDestroy
	void destr() {
		//System.out.println(this  +" DESTROY() ");
	}

}
