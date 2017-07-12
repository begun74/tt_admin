<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ru">

<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name='robots' content='all, follow' />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    
    <title>Great admin</title>   
    <link href="resources/admin/css/default.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/blue.css" rel="stylesheet" type="text/css" media="screen" /> <!-- color skin: blue / red / green / dark -->
    <link href="resources/admin/css/datePicker.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/wysiwyg.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/fancybox-1.3.1.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/visualize.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/css/simplePagination.css" rel="stylesheet" type="text/css">

    
    <script type="text/javascript" src="resources/js/angular.min.js"></script>

	<script type="text/javascript" src="resources/admin/js/jquery-1.4.2.min.js"></script>   
    <script type="text/javascript" src="resources/admin/js/jquery.dimensions.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.simplePagination.js"></script>
    
    <!-- // Tabs // -->
    <script type="text/javascript" src="resources/admin/js/ui.core.js"></script>
    <script type="text/javascript" src="resources/admin/js/jquery.ui.tabs.min.js"></script>

    <!-- // Table drag and drop rows // -->
    <script type="text/javascript" src="resources/admin/js/tablednd.js"></script>

    <!-- // Date Picker // -->
    <script type="text/javascript" src="resources/admin/js/date.js"></script>
    <!--[if IE]><script type="text/javascript" src="admin/js/jquery.bgiframe.js"></script><![endif]-->
    <script type="text/javascript" src="resources/admin/js/jquery.datePicker.js"></script>

    <!-- // Wysiwyg // -->
    <script type="text/javascript" src="resources/admin/js/jquery.wysiwyg.js"></script>

    <!-- // Graphs // -->
    <script type="text/javascript" src="resources/admin/js/excanvas.js"></script>
    <script type="text/javascript" src="resources/admin/js/jquery.visualize.js"></script>

    <!-- // Fancybox // -->
  	<script type="text/javascript" src="resources/admin/js/jquery.fancybox-1.3.1.js"></script>

    <!-- // File upload // --> 
    <script type="text/javascript" src="resources/admin/js/jquery.filestyle.js"></script>
    
    <script type="text/javascript" src="resources/admin/js/init.js"></script>
    
    <script type="text/javascript" src="resources/admin/js/app.js"></script>
    
    <title><spring:message code="admin.title"/></title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body ng-app="myApp" ng-controller="myCtrl">
<div id="main">
    <!-- #header -->
    	<!-- footer -->
			<%@include file="admin/header_top.jsp" %>
		<!-- footer -->

    <!-- /header -->
    <!-- #content -->
    <div id="content">
				<!-- breadcrumbs -->
		        <div class="breadcrumbs">
		          <ul>
		            <li class="home"><a href="index">Homepage</a></li>
		          </ul>
		        </div>
		        <!-- /breadcrumbs -->

      	<!-- box>
       	<div id="tabs-statistic" class="box">
       	</div>
       	<!-- /box -->



		<div id="tabs" class="box">
            <ul class="bookmarks">
		           	<li><a href="#tabs-1"><spring:message code="admin.load.info.from.file" /></a></li>
		            <li><a href="#tabs-2"><spring:message code="tails" /></a></li>
            </ul>
          	<div class="box-content">    
          

            <div id="tabs-1">
   		               		            <!-- Monitoring -->            
   		            <div class="form-message error ">
   		            	<div class="monitors monitorErrors" >
   		            	</div>
   		            </div>
					<div class="form-message correct">				
   		            	<div  id="MA_loadTail" class="monitors monitorProgress" >
						</div>
					</div>

            		
					<div class="formBox">

							<form:form id="addTempFileTail" class="formBox" role="form"  
								  			enctype="multipart/form-data" 
								  			action="${pageContext.request.contextPath}/admin/addTempFileTail?${_csrf.parameterName}=${_csrf.token}#tabs-1" 
								  			method="POST">
		   									<div class="cols4">            
												<div class="col1">
											  			<div class="clearfix">
														         <div class="lab">
												                    <label>Количество</label> 
														         </div>
														         <div  style="width: 25px" class="conleft" >
																	<input name="col_amountTail" id="col_amountTail" type="text" class="input" value="${sessionBean.mA_loadTail.col_amountTail}">										         
														         </div>
														 </div>
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Первая Цена</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_firstPrice" id="col_firstPrice" type="text" class="input" value="${sessionBean.mA_loadTail.col_firstPrice}"> 
														         </div>
														</div>
												</div>
												<div class="col2">
												
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Код Номенкл.</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_codeNomencl" id="col_codeNomencl" type="text" class="input" value="${sessionBean.mA_loadTail.col_codeNomencl}"> 
														         </div>
														</div>
											  			<div class="clearfix">
														         <div class="lab">
										                   			<label>Размер</label>
														         </div>
														         <div style="width: 25px" class="conleft">
										                   			<input name="col_size" id="col_size" type="text" class="input" value="${sessionBean.mA_loadTail.col_size}"> 
														         </div>
														</div>
												</div>
												<div class="col3">
														<div class="clearfix">									        
																        <div class="lab">
																        	<label>Нач. строка</label>
																        </div>
																        <div style="width: 25px" class="conleft">
																        	<input name="row" id="row" type="text" class="input" value="${sessionBean.mA_loadTail.row}" >
																        </div>
														</div>
														<div class="clearfix">									        
																        <div class="lab">
																        	<label>НДС</label>
																        </div>
																        <div style="width: 25px" class="conleft">
																        	<input name="nds" id="nds" type="text" class="input" value="${sessionBean.mA_loadTail.nds}" >
																        </div>
														</div>
												</div>
												<div class="col4">
														<div class="clearfix">									        
																        <div class="lab">
																        	<label>Надбавка опт.</label>
																        </div>
																        <div style="width: 25px" class="conleft">
																        	<input name="nadb_opt" id="nadb_opt" type="text" class="input" value="${sessionBean.mA_loadTail.nadb_opt}" >
																        </div>
														</div>
														<div class="clearfix">									        
																        <div class="lab">
																        	<label>Надбавка розница</label>
																        </div>
																        <div style="width: 25px" class="conleft">
																        	<input name="nadb_rozn" id="nadb_rozn" type="text" class="input" value="${sessionBean.mA_loadTail.nadb_rozn}" >
																        </div>
														</div>
												</div>

												
														<div class="clearfix file">
											              <div class="lab"><label for="file"><spring:message code="load"/> </label></div>
											              <div class="con"><input type="file" name="file" class="upload-file" id="file" /></div>
											            </div>
	
														<div class="clearfix">									        
															<div class="lab">
															   	<label><input name="save" id="save" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadTail.save == true}">checked="checked" </c:if>  >Сохранить настройки</label>
															</div>
														</div>
	
														<div class="clearfix">									        
															<div class="lab">
															   	<label><input name="autoload" id="autoload" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadTail.autoload == true}">checked="checked"</c:if>  >Автозагрузка</label>
															</div>
														</div>
														
								        				<div class="clearfix">
									        				<div class="lab">	
									        					<button type="submit" style="width:130px;" onclick="if(file.value.length == 0) {alert('Выберите файл!'); return false};" ><spring:message code="admin.load.info.from.file"/></button>
									        				</div>
									        				<div class="lab">
									        					<img alt="" src="resources/images/down.png"/>
									        				</div>
								        				</div>

											</div>
								        				
								         <input type="hidden" name ="act" id ="act" value="3"/>
							</form:form>

		   				<div class="col1">
		   						<form id="addTempFileTail" class="formBox" role="form"  
									  			enctype="multipart/form-data" 
									  			action="${pageContext.request.contextPath}/admin/addFileTail?${_csrf.parameterName}=${_csrf.token}#tabs-1" 
									  			method="POST">
									  	<fieldset>
									  	<!-- 
						 				    			<table class="tab" border="0">
										    				<tr align="center">
											    				<th width="5%">№п/п</th>
											    				<th width="35%">Наименование</th>
											    				<th width="5%">Кол-во</th>
											    				<th width="5%">Первая цена</th>
											    				<th width="37%">Размер</th>
											    				<th width="22%">Дата загрузки</th>
											    				<th width="4%" class="checkbox"><input type="checkbox" name="" id="tailIndx" class="check-all" onclick='checkboxAny($(this).attr("checked"),"tailIndex");'/></th>
											    				<th width="2%"><label for="tailIndx" style="cursor: pointer;">Выделить всё</label></th>
										    				</tr>
										    			</table>
						
															<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
																<table class="tab tab-drag">
																	<c:forEach items="${tempTails}" var="tail" varStatus="loop">
																		<tr>
																			<td >${loop.count}</td>
																			<td style="cursor:pointer;">${tail.dirNomenclature.name}</td>
																			<td style="cursor:pointer;">${tail.amountTail}</td>
																			<td style="cursor:pointer;">${tail.firstPrice}</td>
																			<td style="cursor:pointer;">${tail.size}</td>
																			<td style="cursor:pointer;">${tail.create_date}</td>
																			<td class="checkbox"><input name="tailIndex" class="tailIndex" value="${tail.index}" type="checkbox"  style="cursor:pointer;" class="checkbox"/></td>
													         				<td >
													         					<a href="" class="ico ico-delete" onclick=""></a>
													         				</td>
													         				
																		</tr>
																	</c:forEach>
																</table>
															</div>
											-->
											<div >
							
												<button class="button_updTailsTable" type="button" ng-click="updTailsTable(); $event.stopPropagation();">Обновить</button>
												<br>
								
													<div>
										 				    			<table class="tab" border="0">
															    				<tr align="center">
																	    				<th width="5%">№п/п</th>
																	    				<th width="35%">Наименование</th>
																	    				<th width="5%">Кол-во</th>
																	    				<th width="5%">Первая цена</th>
																	    				<th width="37%">Размер</th>
																	    				<th width="22%">Дата загрузки</th>
																	    				<th width="4%" class="checkbox">
																	    					<label for="tailIndx" style="cursor: pointer;">Выделить всё
																	    						<input type="checkbox" name="" id="tailIndx" class="check-all" onclick='checkboxAny($(this).attr("checked"),"tailIndex");'/>
																	    					</label>
																	    				</th>
															    				</tr>
														    			</table>
									
																		<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
																			<table class="tab tab-drag">
																				<tr ng-repeat="t in tails ">
																					<td>{{$index+1}}</td>
																					<td style="cursor:pointer;" >{{t.name}}</td>
																					<td style="cursor:pointer;" >{{t.amountTail}}</td>
																					<td style="cursor:pointer;" >{{t.firstPrice}}</td>
																					<td style="cursor:pointer;" >{{t.size}}</td>
																					<td style="cursor:pointer;">{{ t.create_date | date:'yyyy-MM-dd HH:mm'}}</td>
																					<td class="checkbox"><input name="tailIndex" class="tailIndex" value="${tail.index}" type="checkbox"  style="cursor:pointer;" class="checkbox"/></td>													
																				</tr>
																			</table>
																		</div>		
													</div>		
							
											</div>            
												
									        				<div class="clearfix">
											        					<button type="submit" class="" onclick="" >Загрузить</button>
											        					<label><input name="deleteOldTails" id="deleteOldTails" type="checkbox" class="checkbox" <c:if test="${sessionBean.mA_loadTail.deleteOldTails == true}">checked="checked" </c:if> />удалить старые остатки</label>
									        				</div>
									      </fieldset>
									      <input type="hidden" name ="act" id ="act" value="3"/>
									      
		   				       </form>
						</div>

				</div>


					
            </div>

            <div id="tabs-2">  
				<div ng-app="myApp" ng-controller="myCtrl">

				<button ng-click="updTailsTable()">Обновить</button>
				<br>

					<div>
		 				    			<table class="tab" border="0">
							    				<tr align="center">
									    				<th width="5%">№п/п</th>
									    				<th width="35%">Наименование</th>
									    				<th width="5%">Кол-во</th>
									    				<th width="5%">Первая цена</th>
									    				<th width="37%">Размер</th>
									    				<th width="22%">Дата загрузки</th>
									    				<th width="4%" class="checkbox"><input type="checkbox" name="" id="tailIndx" class="check-all" onclick='checkboxAny($(this).attr("checked"),"tailIndex");'/></th>
									    				<th width="2%"><label for="tailIndx" style="cursor: pointer;">Выделить всё</label></th>
							    				</tr>
						    			</table>
	
										<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
											<table class="tab tab-drag">
												<tr ng-repeat="t in tails | orderBy : 'name'">
													<td>{{$index+1}}</td>
													<td style="cursor:pointer;" >{{t.name}}</td>
													<td style="cursor:pointer;" >{{t.amountTail}}</td>
													<td style="cursor:pointer;" >{{t.firstPrice}}</td>
													<td style="cursor:pointer;" >{{t.size}}</td>
													<td style="cursor:pointer;">{{ t.create_date | date:'yyyy-MM-dd HH:mm'}}</td>
													<td class="checkbox"><input name="tailIndex" class="tailIndex" value="${tail.index}" type="checkbox"  style="cursor:pointer;" class="checkbox"/></td>													
												</tr>
											</table>
										</div>		
					</div>		

				</div>            

            </div>
            
          </div><!-- /box-content -->  
        </div>        
        <!-- /box -->
        
        <!-- /box -->
        <div class="box">

								<c:set var="p_p" value="${param.p_p}"/>
								<c:if test="${empty param.p_p}" >
									<c:set var="p_p" value="100"/>
								</c:if>
	
	
								<c:set var="p" value="${param.p}"/>
								<c:if test="${empty param.p}" >
									<c:set var="p" value="1"/>
								</c:if>
        
        				<h4>Текущие остатки</h4>
	 				    			<table class="tab" border="0">
						    				<tr align="center">
								    				<th width="5%">№п/п</th>
								    				<th width="35%">Наименование</th>
								    				<th width="5%">Кол-во</th>
								    				<th width="5%">Первая цена</th>
								    				<th width="37%">Размер</th>
								    				<th width="22%">Дата загрузки</th>
								    				<th width="4%">Удалить</th>
						    				</tr>
					    			</table>

									<div align="center" style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
										<table class="tab tab-drag">
											<c:forEach items="${tails}" var="tail" varStatus="loop">
												<tr>
													<td >${loop.count}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.dirNomenclature.name}</td>
													<td style="cursor:pointer;" onclick="$('#amountTail').val('${tail.amountTail}'); $('#firstPrice').val('${tail.firstPrice}');">${tail.amountTail}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.firstPrice}</td>
													<td style="cursor:pointer;" onclick="$('#firstPrice').val('${tail.firstPrice}'); $('#amountTail').val('${tail.amountTail}');">${tail.size}</td>
													<td style="cursor:pointer;">${tail.create_date}</td>
							         				<td>
							         					<!-- a href="javascript:editBrand(${dirProvider.id});" class="ico ico-edit" onclick=""></a -->
							         					<a href="javascript:delObject('Tail',${tail.id},'3');" class="ico ico-delete" onclick=""></a>
							         				</td>
												</tr>
											</c:forEach>
										</table>
									</div>
									
									
									<div style="margin: 10px">
										<div id="light-pagination" class="" style="display: table; margin: 0 auto;"></div>
										
										<div style="display: table;  margin: 0 auto;">
											<label for="selectperp">
													<span>Показывать</span>
											</label>
											<select id="selectperp" class="" name="p_p">
		        										<option value="20" <c:if test="${p_p eq 20 }">selected</c:if> >20</option>
		        										<option value="50" <c:if test="${p_p eq 50 }">selected</c:if> >50</option>
		        										<option value="100" <c:if test="${p_p eq 100 }">selected</c:if> >100</option>
		        										<option value="200" <c:if test="${p_p eq 200 }">selected</c:if> >200</option>
		        										<!-- option value="-1" <c:if test="${p_p le 0 }">selected</c:if> >Все</option -->
											</select>
										</div>
									</div>
										
									<input type="hidden" id="sortby" name="sortby" value="${sortby}">
									
        </div>
        <!-- /box -->
    

    </div>
    <!-- /#content -->
	<!-- Sidebar -->
	        <%@include file="sidebar.jsp" %>
	<!-- /#sidebar-wrapper -->    
	        
	<!-- #footer -->
    <div id="footer">
      <p>© 2010 Great Admin | <a href="#main">Top</a></p>
    </div>
    <!-- /#footer -->
	

  <!-- /#main --> 
  <script>
  		$(document).ready(function(){
				
  			
			/*$("#save").attr("checked","checked");*/
			
		    $("#selectperp").change(function() {
				var p_p = $(this).val();
				window.location = '?act=3&p_p='+p_p+'&p='+${p}+'&sortby='+$("#sortby").val();
		    });
			
			
			$('#light-pagination').pagination({
	            items: ${allItems},
	            itemsOnPage: ${p_p},
	            cssStyle: 'light-theme',
	            prevText:"<<",
	            nextText:">>",
	            hrefTextPrefix: "?act=3&p_p="+${p_p}+"&sortby="+$("#sortby").val()+"&p=",
	            currentPage: ${p}
	        });

	    });
	    
    </script>
</body>

</html>