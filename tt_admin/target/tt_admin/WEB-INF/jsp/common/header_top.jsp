<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-5 col-xs-5">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> (+375 17) 292-45-90</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> office@trikotag.by</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-2 col-sm-2" >
						<!-- h5>Сайт находится в разработке</h5 -->
					</div>					
					
					<div class="col-md-5 col-sm-5">
								<ul class="nav nav-pills">
									<li><a href="http://www.nbrb.by"><i id="usd_840" class="fa"></i></a></li>
									<li><a href="http://www.nbrb.by"><i id="eur_978" class="fa"></i></a></li>
									<li>
										<!-- Gismeteo Informer (begin) -->
										<div id="GMI_88x31-1_ru" class="gm-info pull-center " style="margin: 3px;">
										    <div style="position:relative;width:88px;height:31px;border:solid 1px;background:#F5F5F5;border-color:#EAEAEA #E4E4E4 #DDDDDD #E6E6E6;border-radius:4px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;">
										        <a style="font:11px/13px Arial,Verdana,sans-serif;text-align:center;text-overflow:ellipsis;text-decoration:none;display:block;overflow:hidden;margin:2px 3px;color:#0678CD;" href="https://gismeteo.by/weather-minsk-4248/">Минск</a>
										        <a style="font:9px/11px Tahoma,Arial,sans-serif;letter-spacing:0.5px;text-align:center;text-decoration:none;position:absolute;bottom:3px;left:0;width:100%;color:#333;" href="https://gismeteo.by"><span style="color:#0099FF;">Gis</span>meteo</a>
										    </div>
										</div>
									</li>
								</ul>
					</div>
					
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">

				<div class="row">
					<div class="col-sm-4">
						<!-- div class="logo pull-left">
							<a href="index.html"><img src="resources/images/home/logo.png" alt="" /></a>
						</div -->
						
						<ul>
							<li class="dropdown"><a class="btn btn-default dropdown-toggle usa" data-toggle="dropdown" >Язык | Lang<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li role="presentation"><a role="menuitem" tabindex="0"
										href="index?locale=ru">RU</a></li>
									<li role="presentation"><a role="menuitem" tabindex="1"
										href="index?locale=en">EN</a></li>
								</ul>
							</li>
						</ul>
						<!-- div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
									DOLLAR
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#">Canadian Dollar</a></li>
									<li><a href="#">Pound</a></li>
								</ul>
							</div>
						</div -->
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
								<ul class="">
									<li class="dropdown"><a href="http://trikotag.by/cart"><spring:message code="cart"/>  <span class="badge"><c:out value="${fn:length(sessionScope.sessBean.orderItems)}"/></span></a>
										<!-- ul class="dropdown-menu">
											<li>
							                        <div class="row">
							                            <div class="col-xs-4 col-sm-4 no-margin text-center">
							                                <div class="thumb">
							                                    <img src="resources/images/products/nopicture.jpg" width="73" height="73"  onerror="this.onerror=null;this.src='resources/assets/images/products/nopicture.jpg';" />
							                                </div>
							                            </div>
								                            <div class="col-xs-8 col-sm-8 no-margin">
								                                <div class="title">Blueberry</div>
								                                <div class="price">$270.00</div>
								                            </div>
							                            
							                        </div>
							                        <a class="close-btn" href="del-from-backet?id=${particleboard.key.id}"></a>
											</li>
							                <li>
							                        <div class="">
							                            <div class="col-xs-12 col-sm-6">
							                                <a href="createOrder" class="le-button"><spring:message code="to.order"/></a>
							                            </div>
							                        </div>
							                </li>
											
										</ul -->
					                </li>
									<!-- li><a href="login"><i class="fa fa-user"></i>Account</a></li -->
									<!-- li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li -->
									<!-- li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li -->
									<li><a href="admin"><i class="fa fa-lock"></i> Login</a>  <c:if test="${fn:length(sessionScope.authUser.username) >0}" ><c:out value="(${sessionScope.authUser.username})"/></c:if></li>
					                
					            </ul>							
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
		</div>
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="mainmenu navbar-header">
							<ul class="nav navbar-nav">
								<li><a href="http://trikotag.by"><spring:message code="goods"/></a></li>
								<li><a href="http://trikotag.by/our_shops" ><spring:message code="our.shops"/></a></li>
								<li><a href="http://trikotag.by/cooperation" ><spring:message code="buy.in.bulk"/></a></li>
								<li><a href="http://trikotag.by/contact-us.html"><spring:message code="contacts"/></a>
								<li><a href="http://trikotag.by/vacancies" ><spring:message code="info.vacancies"/></a></li>
								<li><a href="http://trikotag.by/action" ><spring:message code="action1"/></a></li>
								<li><a href="http://trikotag.by/about_company" ><spring:message code="about.company"/></a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row">
						<div class="col-sm-12">
							<div class="search_box pull-right">
								<input type="text" class="search_text" value="${findText}" placeholder="<spring:message code="search"/>"/>
							</div>
						</div>
				</div>
				
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
