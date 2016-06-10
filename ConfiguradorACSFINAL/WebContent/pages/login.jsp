<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags"  prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configurado ACS</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/boostrap.login.css" rel="stylesheet">

</head>

<body>

			<div class="container">
			  
					<div class="row" id="pwd-container">
					<div class="col-md-4"></div>

					<div class="col-md-4">
					  <section class="login-form">
					  
						<form method="post" action="ingresarSistema" role="login">
						  <img src="<%=request.getContextPath()%>/resources/images/logo_alignet.png" class="img-responsive" alt="" />
						  
						  <input type="text" name="usuario" id="usuario" placeholder="Usuario" class="form-control input-lg"  required/>
						  
						  <input type="password"  name="password" id="password" class="form-control input-lg" placeholder="Password" required />
						  
						  
						  <div class="pwstrength_viewport_progress"></div>

						  <input type="submit"  name="btnIngresar" class="btn btn-lg btn-primary btn-block" value="Ingresar">
						  
						  	<div class="form-group has-error">
								<s:property value="mensajeusuario"/>
							</div>
						</form>
						

					  </section>  
					  </div>
					  
					  <div class="col-md-4"></div>
					  

					</div>

			</div>	

</body>
</html>