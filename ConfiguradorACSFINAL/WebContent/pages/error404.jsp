<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error en Proceso</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/boostrap.login.css" rel="stylesheet">
</head>
<body>

<div class="container">

	<div class="row">
			 <div class="col-md-12">
			 	 <div class="error-template">
			 	 	<div style="margin-top:60px;" align="center">
			 	 		<img src="<%=request.getContextPath()%>/resources/images/img_pageerror.png" class="img-responsive" alt="" />
			 	 	</div>
			 	 	
			 	 	<div style="margin-top:15px;margin-bottom:15px;color: white;" align="center">
				 	 	<h3>La pagina que intentabas ver no existe.
				 	 		<br></br>
				 	 		Te sugerimos retornar a la página de <a href="https://test1.alignetsac.com/ConfiguradorACS/index.jsp"><strong>inicio</strong></a>
				 	 	</h3>
				 	 	
			 	 	</div> 
			 	 </div>
			 </div>
	</div>

</div>

</body>
</html>