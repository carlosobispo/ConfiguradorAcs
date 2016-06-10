<%@taglib uri="/struts-tags"  prefix="s"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<head>


	<meta http-equiv=Content-Type content=text/html; charset=UTF-8>  
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>

    <link href="<%=request.getContextPath()%>/resources/css/main.min.css" rel="stylesheet">

	<script type="text/javascript">

	  function cargarLogoMarcaSeleccionada(){
		  
 			var form = document.forms[0];

 			var indice = form.listaBrand.selectedIndex ;
		   	var valorMarcaSeleccionada = form.listaBrand.options[indice].value; 

		   	if(valorMarcaSeleccionada!=1 && valorMarcaSeleccionada!=2){
				 alert('Seleccione una Marca Valida');
				 return false;
			 }  

		   	return true;
 			
		 };
	 
	
		 function recargarMarca(){
			 
			 var form = document.forms[0];
	
			 if(cargarLogoMarcaSeleccionada()){
				 form.action="irConfigLogo";
				 form.submit();
				 
			 }
		 }
	
	</script>
	
</head>

<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Logo</h4>
			<s:if test="%{getFieldErrors().get('mensajeERROR')!=null}">
				<div class="alert alert-danger">
					<s:property value="getFieldErrors().get('mensajeERROR')"/>
				</div>
			</s:if>
			<s:if test="%{getFieldErrors().get('mensajeSUCCESS')!=null}">
				<div class="alert alert-success">
					<s:property value="getFieldErrors().get('mensajeSUCCESS')" />
				</div>
			</s:if>

		<form  id="form-Logo" class="form-horizontal" method="post" action="cargarLogoIssuer" enctype="multipart/form-data">
					<s:hidden  name="operacionValidate" value="validateCargaLogo"/>
					
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
							
										<div class="col-lg-12">
											<div class="form-group">
												<div class="col-xs-2">
													<label for="inputText">Marca</label>
												</div>
												<div class="col-xs-6">
													<s:select cssClass="form-control2"
														list="listBrandHabilitadasEmisor" listKey="intIdBrand"
														listValue="svcName" name="brandBean.intIdBrand"
														value="brandSelected" id="listaBrand"  onchange="recargarMarca()"/>
												</div>
											</div>
								
										</div>							
							</div>
						</div>
					</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">Configuraci&oacute;n Logo</div>
			<div class="panel-body">
				<div class="row">

					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-12">
									<div align="center">
										<h4 class="page-header">Logo Emisor</h4>
									</div>	
									<div align="center">
										<h6>Las dimensiones del logo debe ser 140x47 (.gif) </h6>
									</div>	
				     		</div>						
						</div>
					</div>	
															
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-12">
									<div align="center" style="margin-bottom: 5px;">
										<img alt="Logo"	src="logoIssuer?brandSelected=<s:property value="brandBean.intIdBrand"/>" width="140" height="47" />
									</div>		
				     		</div>						
						</div>
					</div>
					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-12">
									<div align="center" style="margin-bottom: 15px;">
										<s:file name="fileBean.fileLogoIssuer" accept="image/gif"></s:file>
									</div>		
				     		</div>						
						</div>
					</div>
																				
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-12">
									<div align="center">
										<input type="submit" style="font-weight: 700;" class="btn btn-primary" value="Cargar">
									</div>		
				     		</div>						
						</div>
					</div>										
										<!-- https://books.google.com.pe/books?id=96HHRq6g5x8C&pg=PA294&lpg=PA294&dq=struts2++cargar+imagen&source=bl&ots=fyCd2sLoZ9&sig=ax5SGYnosESvkz5HZt-oX6pFFOA&hl=es&sa=X&ved=0ahUKEwi2zsLk2_rLAhXFsh4KHWJ1ByY4ChDoAQgiMAE#v=onepage&q=struts2%20%20cargar%20imagen&f=false -->

										
				</div>
			</div>
				
		
		
		</div>
	
		</form>
	</div>

</body>

