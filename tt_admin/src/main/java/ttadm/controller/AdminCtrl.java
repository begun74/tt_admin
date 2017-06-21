package ttadm.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ttadm.bean.AdminSessionBean;
import ttadm.bean.AppBean;
import ttadm.service.TT_AdminServiceImpl;




@Controller
@Scope("session")
@RequestMapping(value = {"/"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	@Autowired
	private AppBean appBean;

	@Autowired
	private AdminSessionBean adminSessBean;
	
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
			model = new ModelAndView("index");
			return model;
		}
		
		
		switch (act)
		{
			case "1":
				model = new ModelAndView("addProvider");
				model.addObject("dirProviders",ttadmService.getProviderList());
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
		
		model.addObject("error",adminSessBean.getErrorList().toString().length() > 512 ?adminSessBean.getErrorList().toString().substring(0, 512)+" ...":adminSessBean.getErrorList());
		model.addObject("sessionBean",adminSessBean);
		model.addObject("providers", ttadmService.getProviderList());
		
		
		return model;
	}

}
