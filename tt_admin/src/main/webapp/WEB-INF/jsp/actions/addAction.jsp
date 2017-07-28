<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>

    
</head>

<body>
	<div class="form-cols">
		<div class="col1">
			<form>
				 			<div class="clearfix">
									         <div class="lab">
							                    <label>Наименование акции</label> 
									         </div>
									         <div class="con">
												<input name="name" id="name" type="text" class="input width400px" >										         
									         </div>
							</div>
				 			<div class="clearfix  texarAction">
       										<div class="lab"><label for="textarea-two">Техт</label></div>
          									<div class="con"><textarea cols="" rows="" class="textarea wysiwyg" id="textarea-two"></textarea></div>
							</div>
											
			</form>
		</div>
	</div>
</body>

</html>

