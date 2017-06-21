<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--  Required accCount -->
<!-- c:set var="allCount" value="10"/ -->

<!--  Required act -->
<!-- c:set var="act" value="1"/ -->

<div class="form-cols">
	   		<div class="col1">            

				<form:form id="addFileForm" class="formBox" role="form"  
					  			enctype="multipart/form-data" 
					  			action="${pageContext.request.contextPath}/admin/addFile?${_csrf.parameterName}=${_csrf.token}" 
					  			method="POST">
				
				  			<div class="clearfix checkbox">
									        <div class="lab">
									        	<label>Колонки</label>
									        </div>
									        <div class="con">
									        	<!-- select name="cols" id="cols" multiple="multiple">
											        <c:forEach begin="1" end="10" var="colN">
														<option>${colN}</option>		
													</c:forEach>
												</select -->
													<label><input type="checkbox" id="allCols" />Все&nbsp;&nbsp;&nbsp;</label>
											        <c:forEach begin="1" end="${allCount}" var="colN">
														<label><input type="checkbox" name="cols" id="col${colN}" value="${colN-1}"/>${colN}</label>		
													</c:forEach>
																		                   		  
									        </div>
							</div>
							<div class="clearfix radio">									        
									        <div class="lab">
									        	<label>Начальная строка</label>
									        </div>
									        <div class="con">
											        <c:forEach begin="1" end="${allCount}" var="rowN">
														<label><input type="radio" name="row" id="row" value="${rowN}"/>${rowN}</label>		
													</c:forEach>
									        </div>
							 </div>
							 
							
					         <div style="margin-bottom: 15px" class="clearfix file">
							         	<p><spring:message code="load"/> <input type="file" name="file"></p>
							 </div>
				
					         <button type="submit" class="" onclick="if(file.value.length == 0) {alert('Выберите файл!'); return false};" ><spring:message code="load"/></button>
					         <input type="hidden" name ="act" id ="act" value="${act}"/>
				</form:form>
			</div>
</div>

