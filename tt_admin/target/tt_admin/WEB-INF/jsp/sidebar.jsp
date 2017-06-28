<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        
    <!-- #sidebar -->
    <div id="sidebar">

        <!-- mainmenu -->
        <ul id="floatMenu" class="mainmenu">
          <li><a href="#">Автозагрузка</a>
            <ul class="submenu">
              <li><a href="${pageContext.request.contextPath}/admin?act=5">Автозагрузка (старт/стоп)</a></li>          
            </ul>
          </li>
          <li class="first"><a href="#"><spring:message code="dictionary"/></a>
            <ul class="submenu">
              <li><a href="${pageContext.request.contextPath}/admin?act=1" >Поставщик</li>          
              <li><a href="${pageContext.request.contextPath}/admin?act=2" >Номенклатура</li>          
			  <li><a href="${pageContext.request.contextPath}/admin?act=4" >Номенклатурная группа</li>
			  <li><a href="${pageContext.request.contextPath}/admin?act=6" >НоменклГруппаРодитель</li>
            </ul>
          </li>
          <li><a href="#"><spring:message code="admin.load.info.from.file"/></a>
            <ul class="submenu">
              <li><a href="${pageContext.request.contextPath}/admin?act=3" ><spring:message code="load.tails"/></a></li>          
            </ul>
          </li>
          <li><a href="#" ><spring:message code="admin.view.orders"/></a>
          	<ul class="submenu">
          		<li><a href="${pageContext.request.contextPath}/admin?act=6" ><spring:message code="all.orders"/></a></li>
          	</ul>
          </li>
          <li><a href="#" >Контент сайта</a>
          	<ul class="submenu">
          		<li><a href="${pageContext.request.contextPath}/admin/content?act=1" >Реклама/Акции</a></li>
          	</ul>
          </li>

		<!-- li><button onclick="window.location.href = '${pageContext.request.contextPath}/admin?logout' ">Выход</button></li -->
		<li><a href="" onclick="window.location.href = '${pageContext.request.contextPath}/admin?logout'">Выход</a></li>
        </ul>
        <!-- /.mainmenu -->

    </div>
    <!-- /#sidebar -->
