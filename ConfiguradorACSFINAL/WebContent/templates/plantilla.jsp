<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

	 
<!-- 

< %@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 

 --> 
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <!--  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv=Content-Type content=text/html; charset=UTF-8> 
<title>CONFIGURADOR SAC</title>

    <!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <!-- <link href="< %=request.getContextPath()%>/resources/css/metisMenu.min.css" rel="stylesheet"> --> 

    <!-- Timeline CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    
    <script src="<%=request.getContextPath()%>/resources/js/jquery-confirm.js"></script>

    
</head>
<body>


 
 <div id="wrapper">
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

			<div>
		
				<tiles:insertAttribute   name="header"/>
			</div>
			
            <div>
				<tiles:insertAttribute   name="menu"/>
			</div>
	</nav>
	
	 	
	
					<div id="page-wrapper">
					
						<div class="row">
							<div class="col-lg-12">
									<div align="right">
											<h5 class="page-header">Fecha : ${sessionScope.ObjetoAccesoBean.fechaSistema} </h5> 
									</div>
									 
							</div>
							
							
							<div class="col-lg-12">
								<tiles:insertAttribute   name="central"/>
							</div>
							
						</div>
						
					</div>
	
 	</div>

    <!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	
    <!-- Bootstrap Core JavaScript  -->
   <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript  -->
   <!--  <script src="< %=request.getContextPath()%>/resources/js/metisMenu.min.js"></script> -->

    <!-- Custom Theme JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.js"></script>

	<script src="<%=request.getContextPath()%>/resources/js/jquery.displaytag-ajax-1.2.js"></script>
		
	<script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-confirm.js"></script>
	
</body>
</html>