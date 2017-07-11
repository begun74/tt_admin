package ttadm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import ttadm.modelattribute.MA_loadNomencl;
import ttadm.modelattribute.MA_loadNomenclGroup;
import ttadm.modelattribute.MA_loadNomenclGroupRoot;
import ttadm.modelattribute.MA_loadProvider;
import ttadm.modelattribute.MA_loadTail;

@Controller
@Scope("session")
public class TT_AdminAjaxCtrl implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6173721887621931115L;

	@ResponseBody
	@RequestMapping(value = "/clearMonitorErrors", method = RequestMethod.GET)
	public HttpStatus  clearMonitorErrors(HttpSession httpSession) 
	{
		if(httpSession.isNew()) 
			return HttpStatus.FORBIDDEN;
		
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		adminSessionBean.clearError();
		
		return  HttpStatus.OK;
	}
	
	@ResponseBody
	@RequestMapping(value = "/clearMonitorProgress{id}", method = RequestMethod.GET)
	public HttpStatus  clearMonitorProgress(HttpSession httpSession, @RequestParam(value = "id",   required = false) String id) 
	{
		if(httpSession.isNew()) 
			return HttpStatus.FORBIDDEN;
		
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		switch (id) {
			case "MA_loadProvider":
				adminSessionBean.clearHmLog_Load(new MA_loadProvider());
			break;
			case "MA_loadNomencl":
				adminSessionBean.clearHmLog_Load(new MA_loadNomencl());
			break;
			case "MA_loadNomenclGroup":
				adminSessionBean.clearHmLog_Load(new MA_loadNomenclGroup());
			break;
			case "MA_loadNomenclGroupRoot":
				adminSessionBean.clearHmLog_Load(new MA_loadNomenclGroupRoot());
			break;
		}
	
		
		return  HttpStatus.OK;
	}
	
	@ResponseBody
	@RequestMapping(value = "/progress{id}", method = RequestMethod.GET)
	public ResponseEntity<List<String>>  progress(HttpSession httpSession, HttpServletResponse response , @RequestParam(value = "id",   required = false) String id) throws IOException 
	{
		List<String> result = new ArrayList<String>();
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		
 		if(httpSession.isNew() || adminSessionBean == null) 
			return new ResponseEntity<List<String>>(result,HttpStatus.FORBIDDEN);
		
		
		try {
			
			switch (id) {
				case "MA_loadProvider":
					result.addAll(adminSessionBean.getHmLog_Load(new MA_loadProvider()).values().stream().collect(Collectors.toList()));
				break;
				case "MA_loadNomencl":
					result.addAll(adminSessionBean.getHmLog_Load(new MA_loadNomencl()).values().stream().collect(Collectors.toList()));
				break;
				case "MA_loadNomenclGroup":
					result.addAll(adminSessionBean.getHmLog_Load(new MA_loadNomenclGroup()).values().stream().collect(Collectors.toList()));
				break;
				case "MA_loadNomenclGroupRoot":
					result.addAll(adminSessionBean.getHmLog_Load(new MA_loadNomenclGroupRoot()).values().stream().collect(Collectors.toList()));
				break;
				case "MA_loadTail":
					result.addAll(adminSessionBean.getHmLog_Load(new MA_loadTail()).values().stream().collect(Collectors.toList()));
				break;
				
			}
		}
		catch (NullPointerException exc) {
			result = null;
			response.setStatus(400);
		}
		
		return new ResponseEntity<List<String>>(result,HttpStatus.OK);

	}
	
	@ResponseBody
	@RequestMapping(value = "/monitorErrors", method = RequestMethod.GET)
	public ResponseEntity<List<String>>  monitorErrors(HttpSession httpSession, HttpServletResponse response) throws IOException 
	{
		List<String> result = new ArrayList<String>();
		AdminSessionBean adminSessionBean = (AdminSessionBean)httpSession.getAttribute("adminSessionBean");
		
 		if(httpSession.isNew() || adminSessionBean == null) 
			return new ResponseEntity<List<String>>(result,HttpStatus.FORBIDDEN);
 		
 		result = adminSessionBean.getErrorList();

		return new ResponseEntity<List<String>>(result,HttpStatus.OK);

	}


}
