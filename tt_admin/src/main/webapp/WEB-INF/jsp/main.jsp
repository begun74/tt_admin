<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name='robots' content='all, follow' />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <title>Great admin</title>   
    <link href="${pageContext.request.contextPath}/resources/admin/css/default.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="${pageContext.request.contextPath}/resources/admin/css/blue.css" rel="stylesheet" type="text/css" media="screen" /> <!-- color skin: blue / red / green / dark -->
    <link href="${pageContext.request.contextPath}/resources/admin/css/datePicker.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="${pageContext.request.contextPath}/resources/admin/css/wysiwyg.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="${pageContext.request.contextPath}/resources/admin/css/fancybox-1.3.1.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="${pageContext.request.contextPath}/resources/admin/css/visualize.css" rel="stylesheet" type="text/css" media="screen" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery-1.4.2.min.js"></script>   
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.dimensions.min.js"></script>
    
    <!-- // Tabs // -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/ui.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.ui.tabs.min.js"></script>

    <!-- // Table drag and drop rows // -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/tablednd.js"></script>

    <!-- // Date Picker // -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/date.js"></script>
    <!--[if IE]><script type="text/javascript" src="admin/js/jquery.bgiframe.js"></script><![endif]-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.datePicker.js"></script>

    <!-- // Wysiwyg // -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.wysiwyg.js"></script>

    <!-- // Graphs // -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/excanvas.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.visualize.js"></script>

    <!-- // Fancybox // -->
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.fancybox-1.3.1.js"></script>

    <!-- // File upload // --> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/jquery.filestyle.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/admin/js/init.js"></script>
    
    <title><spring:message code="admin.title"/></title>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
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
        <div class="box">
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
</body>

</html>