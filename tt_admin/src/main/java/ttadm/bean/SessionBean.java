package ttadm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import ttadm.model.*;
import ttadm.util.RoundPrice;



@Service("sessBean")
@Scope( proxyMode=ScopedProxyMode.TARGET_CLASS,value=WebApplicationContext.SCOPE_SESSION)


public class SessionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3864475801596719257L;

	private LinkedHashMap<String,Object> errorMap = new LinkedHashMap<String,Object>();
	
	private AtomicInteger npp = new AtomicInteger(0);
	
	private List<OrderItems> orderItems = new ArrayList<OrderItems>();
	
	private HashMap mapProductFilter = new HashMap();

	private User authUser;
	
	private double totalPriceOrderItems = 0;
	
	private int totalAmountOrderItems = 0;
	{
		mapProductFilter.put("category", 11);
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public int getNpp() {
		return npp.incrementAndGet();
	}

	public HashMap<String, Object> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(LinkedHashMap<String, Object> errorMap) {
		this.errorMap = errorMap;
	}

	public User getAuthUser() {
		return authUser;
	}

	public void setAuthUser(User authUser) {
		this.authUser = authUser;
	}

	public HashMap getMapProductFilter() {
		return mapProductFilter;
	}

	public void setMapProductFilter(HashMap mapProductFilter) {
		this.mapProductFilter = mapProductFilter;
	}

	public double getTotalPriceOrderItems() {
		totalPriceOrderItems = 0;
		
		for(OrderItems oi: orderItems)
			totalPriceOrderItems += oi.getPrice()*oi.getAmount();
		
		totalPriceOrderItems = RoundPrice.round_HALF_UP(totalPriceOrderItems);
		
		return totalPriceOrderItems;
	}

	public int getTotalAmountOrderItems() {
		totalAmountOrderItems = 0;
		
		for(OrderItems oi: orderItems)
			totalAmountOrderItems += oi.getAmount();
		
		return totalAmountOrderItems;
	}



	
}
