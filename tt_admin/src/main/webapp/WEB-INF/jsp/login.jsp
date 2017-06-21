<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- meta name="viewport" content="width=device-width, initial-scale=1.0" -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/prettyPhoto.css" rel="stylesheet">
    <link href="resources/css/price-range.css" rel="stylesheet">
    <link href="resources/css/animate.css" rel="stylesheet">
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="resources/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<%@include file="common/header_top.jsp" %>
	
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-4 text-center">
					<div class="login-form"><!--login form-->
						<h2><spring:message code="login.to.your.account"/></h2>
						<form id="loginform" method="post"  class="">
			   		            <!-- Error form message -->            
			   		            <c:if test="${not empty error}">
			   		            <div class="form-group alert">
						              <ul>
						                <li><p><h4><spring:message code="login.error"/></h4></p></li>
						              </ul>
					            </div>
					            </c:if>
						
			   		            <div class="form-group">
										<input type="text" name="username"  placeholder="username" />
								</div>
								
								<div class="form-group">
										<input type="password" name="password" placeholder="password" />
								</div>
								<span>
									<input type="checkbox" class="checkbox"> 
									<spring:message code="keep.me.signed.in"/>
								</span>

								<button type="submit" class="btn btn-default">Login</button>
								
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</div><!--/login form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
	
	<!-- footer -->
	<%@include file="common/footer.jsp" %>
	<!-- footer -->

	

  
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/price-range.js"></script>
    <script src="resources/js/jquery.scrollUp.min.js"></script>
    <script src="resources/js/jquery.prettyPhoto.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/main.js"></script>
    

</body>
</html>