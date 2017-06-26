package ttadm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ttadm.bean.AdminSessionBean;

@Controller
@Scope("session")
public class TT_AdminAjaxCtrl {

	
	
	@ResponseBody
	@RequestMapping(value = "/clearErrors", method = RequestMethod.GET)
	public HttpStatus  clearErrors(HttpSession httpSession) 
	{
		if(httpSession.isNew()) 
			return HttpStatus.FORBIDDEN;
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		adminSessionBean.clearError();
		//System.out.println("clearErrors " +sessBean.getErrorList());
		
		return  HttpStatus.OK;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/progress{id}", method = RequestMethod.GET)
	public ResponseEntity<String>  progress(HttpSession httpSession, HttpServletResponse response , @RequestParam(value = "id",   required = false) String id) throws IOException 
	{
		String result = "";
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		
		if(httpSession.isNew() || adminSessionBean == null) 
			return new ResponseEntity<String>("",HttpStatus.FORBIDDEN);
		
		int n = Integer.parseInt(id);
		
		try {
			switch (n) {
				case 0:
				result = adminSessionBean.getHmLog_LoadMA_loadProvider().lastEntry().getValue();
				break;
			}
		}
		catch (NullPointerException exc) {
			result = null;
			response.setStatus(400);
			response.getWriter().write(exc.getMessage());
		}
		
		return new ResponseEntity<String>(result,HttpStatus.OK);

	}
	

}
