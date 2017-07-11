package ttadm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import ttadm.model.JSON_Tail;
import ttadm.model.Tail;
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
	
	@Autowired
	private AdminSessionBean adminSessBean;

	

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

	
	@RequestMapping(value = "/getTempTails", method = RequestMethod.GET)
	public ResponseEntity<List<JSON_Tail>> getTempTails(HttpSession httpSession)
	{
		
		
		//List<OrderItems> orderItems = ttService.getOrderItems(id);
		
		List<JSON_Tail> json_tails = new LinkedList<JSON_Tail>();

 		if(httpSession.isNew()) 
			return new ResponseEntity<List<JSON_Tail>>(new ArrayList<JSON_Tail>(),HttpStatus.FORBIDDEN);
 		
 		List<Tail> tempTails = adminSessBean.getTempListTails();
 		
 		for(Tail tail: tempTails)
 		{
 			JSON_Tail json_tail = new JSON_Tail();
 			
 			//json_tail.setId(tail.getId());
 			json_tail.setAmountTail(tail.getAmountTail());
 			json_tail.setCreate_date(tail.getCreate_date());
 			json_tail.setFirstPrice(tail.getFirstPrice());
 			//json_tail.setNadb_opt(tail.getNadb_opt());
 			//json_tail.setNadb_rozn(tail.getNadb_rozn());
 			json_tail.setName(tail.getDirNomenclature().getName());
 			json_tail.setIndex(tail.getIndex());
 			//json_tail.setDirNomenclature(tail.getDirNomenclature());
 			//json_tail.setNds(tail.getNds());
 			//json_tail.setOpt_price(tail.getOpt_price());
 			//json_tail.setRozn_price(tail.getRozn_price());
 			json_tail.setSize(tail.getSize());
 			
 			json_tails.add(json_tail);
 		}
		
		//System.out.println("orderItems.size() - " +orderItems.size());
		/*
		for(OrderItems orderItem: orderItems) {
			//System.out.println(orderItem.getId()+":  "+orderItem.getSize()+"  "+ orderItem.getAmount());
			//System.out.println(orderItem.getOrder()+"  ");
			//System.out.println(orderItem.getDirNomenclature());
			JSON_OrderItems json_oitem = new JSON_OrderItems();
			json_oitem.setId(orderItem.getId());
			json_oitem.setAmount(orderItem.getAmount());
			json_oitem.setArticle(orderItem.getTail().getDirNomenclature().getArticle());
			json_oitem.setCode(orderItem.getTail().getDirNomenclature().getCode());
			json_oitem.setCreate_date(orderItem.getCreate_date());
			json_oitem.setModel(orderItem.getTail().getDirNomenclature().getModel());
			json_oitem.setName(orderItem.getTail().getDirNomenclature().getName());
			json_oitem.setSize(orderItem.getTail().getSize());
			json_oitem.setDestruction_date(orderItem.getDestruction_date());
			
			json_oitems.add(json_oitem);
		}
		*/
		//System.out.println(" json_oitems - " + json_oitems);
		
		return  new ResponseEntity<List<JSON_Tail>>(json_tails,HttpStatus.OK);
	}	
	

}
