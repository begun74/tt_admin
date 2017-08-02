<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name='robots' content='all, follow' />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <title>Great admin</title>   
    <link href="resources/admin/css/default.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/blue.css" rel="stylesheet" type="text/css" media="screen" /> <!-- color skin: blue / red / green / dark -->
    <link href="resources/admin/css/datePicker.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/wysiwyg.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/fancybox-1.3.1.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="resources/admin/css/visualize.css" rel="stylesheet" type="text/css" media="screen" />

    <script type="text/javascript" src="resources/js/angular.min.js"></script>

	<script type="text/javascript" src="resources/admin/js/jquery-1.4.2.min.js"></script>   
    <script type="text/javascript" src="resources/admin/js/jquery.dimensions.min.js"></script>
    
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
    
    <script type="text/javascript" src="resources/admin/js/advCamp.js"></script>
    <script type="text/javascript" src="resources/admin/js/app.js"></script>
    
    <title><spring:message code="admin.title"/></title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body ng-app="advCamp" ng-controller="advCamp_Ctrl">
<div id="main">
    <!-- #header -->
    	<!-- footer -->
			<%@include file="../admin/header_top.jsp" %>
		<!-- footer -->

    <!-- /header -->
    <!-- #content -->
    <div id="content">
				<!-- breadcrumbs -->
		        <div class="breadcrumbs">
		          <ul>
		            <li class="home"><a href="index">На главную</a></li>
		          </ul>
		        </div>
		        <!-- /breadcrumbs -->

      	<!-- box>
       	<div id="tabs-statistic" class="box">
       	</div>
       	<!-- /box -->

        <!-- box>
        <div id="tabs" class="box">
        </div>
        <!-- /box -->
        
        <!-- /box -->
        <div id="tabs" class="box">
            <ul class="bookmarks">
		           	<li><a href="#tab1">Добавить акцию</a></li>
		           	<li><a href="#tab2">Все акции</a></li>
            </ul>


          	<div class="box-content">    
            	<div id="tab1">
            	
            	<div class="form-cols">
            	 	<div class="col1">
            	   		            <!-- Error form message -->            
   		            <c:if test="${fn:length(adminSessionBean.errorList) gt 0 }">
		            <div class="form-message error"  onclick="clearErrors()">
		              <p>Ошибка :</p>
		              <ul>
		                <li>"${adminSessionBean.errorList}"</li>
		              </ul>
		            </div>
		            </c:if>
            	 		<form id="formAdvCamp" class="formBox" method="post" modelAttribute="AdvertCamp" action="${pageContext.request.contextPath}/content?${_csrf.parameterName}=${_csrf.token}">
				 			<div class="clearfix">
									         <div class="lab">
							                    <label for="name">Наименование акции <span>*</span></label> 
									         </div>
									         <div class="con">
												<input name="name" id="name" type="text" class="input width400px" value="${mA_AdvertCamp.name }">										         
									         </div>
							</div>
				 			<div class="clearfix">
       										<div class="lab"><label for="textarea-two">Текст <span>*</span></label></div>
          									<div class="con"><textarea name="text" id="text" class="textarea wysiwyg texarAction" ></textarea></div>
							</div>
							<fieldset>
					 			<div class="clearfix">
					 							<div class="lab"><label for="fromDate">Дата проведения С <span>*</span></label></div>
												<div class="dateCon">
													<input id="fromDate" type="text" class="input datepicker" name="fromDate"  value="${mA_AdvertCamp.fromDate }" />
												</div>
								</div>
								
																			
					 			<div class="clearfix">
												<div class="lab"><label for="toDate">Дата проведения По <span>*</span></label></div>
												<div class="dateCon">
													<input id="toDate" type="text" class="input datepicker" name="toDate" value="${mA_AdvertCamp.toDate }" />
												</div>
								</div>
					 			<div class="clearfix">
												<div class="lab"><label for="toDate">Действует <input type="checkbox" name="active" checked="checked"/></label></div>
													
								</div>
								
							</fieldset>
							<p/>							
				 			<div class="clearfix">
									<button name="act" value="1" class="button">Добавить</button>
									<button name="act" value="2" class="button">Обновить</button>
							</div>
							
							<input type="hidden" name="id" />
						</form>
					</div>
            	 	
            	 	<div class="col2">
						<div>
            	 			<div><h3>Действующие акции</h3></div>
	            	 			<table class="tab tab-drag">
						            <tr class="top nodrop nodrag">
						              <th class="dragHandle">&nbsp;</th>
						              <!-- th class="checkbox"><input type="checkbox" name="" value="" class="check-all" /></th -->
						              <th>Наименование акции</th>
						              <th>Дата проведения С</th>          
						              <th>Дата проведения По</th>
						              <th class="action">Action</th>
						            </tr>
								</table>
	
            	 				<div align="center"  style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
	            	 				<table class="tab tab-drag">
											<c:forEach items="${advCamps}" var="advCamp" varStatus="loop">
									            <tr id="${advCamp.id}"  title="${advCamp.text}" class="table_row" onClick="toEdit('formAdvCamp','${advCamp.id}','${advCamp.name}','${advCamp.text}', '<fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.fromDate}" />','<fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.toDate}" />', ${advCamp.active});">
									              <td class="dragHandle">&nbsp;</td>
									              <td id="td_name">${advCamp.name}</td>
									              <td id="td_fromDate"><fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.fromDate}" /></td>          
									              <td id="td_toDate"><fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.toDate}" /></td>
									              <td class="action">
									                <a href="content?act=3&id=${advCamp.id}" class="ico ico-delete">Удалить</a>
									              </td>
									            </tr>
											</c:forEach>
									</table>
								</div>
						</div>
					</div>

					</div>

        		</div>

            	<div id="tab2">
						<div>
	            	 			<table class="tab tab-drag">
								</table>
	
            	 				<div align="center"  style="overflow-y:scroll; overflow-x: none; height:300px; width:100%;">
	            	 				<table class="tab tab-drag">
								            <tr class="top nodrop nodrag">
								              <th class="dragHandle">&nbsp;</th>
								              <!-- th class="checkbox"><input type="checkbox" name="" value="" class="check-all" /></th -->
								              <th>Наименование акции</th>
								              <th>Дата проведения С</th>          
								              <th>Дата проведения По</th>
								              <th>Активна</th>
								              <th class="action"></th>
								            </tr>
											<c:forEach items="${allAdvCamps}" var="advCamp" varStatus="loop">
									            <tr  id="${advCamp.id}" class="table_row" >
									              <td class="dragHandle">&nbsp;</td>
									              <td><a href="#">${advCamp.name}</a></td>
									              <td><fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.fromDate}" /></td>          
									              <td><fmt:formatDate pattern="dd/MM/yyyy" value = "${advCamp.toDate}" /></td>
									              <td><input type="checkbox" id="active" <c:if test="${advCamp.active eq true}" >checked</c:if> /></td>
									              <td class="action">
									                <a href="content?act=3&id=${advCamp.id}" class="ico ico-delete"></a>
									              </td>
									            </tr>
											</c:forEach>
									</table>
								</div>
						</div>
        		</div>
        		
        	</div>
        </div>
        <!-- /box -->
    

    </div>
    <!-- /#content -->
	<!-- Sidebar -->
	        <%@include file="../sidebar.jsp" %>
	<!-- /#sidebar-wrapper -->    
	        
	<!-- #footer -->
    <div id="footer">
      <p>© 2010 Great Admin | <a href="#main">Top</a></p>
    </div>
    <!-- /#footer -->
	

	<script>
		$(document).ready(function(){
			
			/*$("#save").attr("checked","checked");*/
			
			/*  Выделяем строку в таблице */
			$( ".table_row" ).click(function(form, id, name, fromDate,toDate) {
				$(this).addClass("selected").siblings().removeClass("selected");
				
				//toEdit('formAdvCamp',$(this).attr("id"), $(this).children('#td_name').text() , $(this).children('#td_fromDate').text(), $(this).children('#td_toDate').text());
				
			});

	    });

	</script>

  <!-- /#main --> 
</body>

</html>