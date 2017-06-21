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




@Controller
@Scope("session")
@RequestMapping(value = {"/"} , method = RequestMethod.GET)
public class AdminCtrl {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView  loginGet(HttpSession session, @RequestParam(value = "logout",	required = false) String logout,
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
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
	public ModelAndView  loginPOST(HttpSession session, 
				@RequestParam(value = "act",   defaultValue = "0") String act,
				@RequestParam(value = "error",   defaultValue = "") String error) 
	{
		ModelAndView model = new ModelAndView("main");
		
		return model;
	}

}
