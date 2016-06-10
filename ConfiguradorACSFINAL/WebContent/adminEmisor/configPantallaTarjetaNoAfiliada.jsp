<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<head>


	<meta http-equiv=Content-Type content=text/html; charset=UTF-8>  
	<!-- 
	<script src="< %=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="< %=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
	 -->


    <link href="<%=request.getContextPath()%>/resources/css/main.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/jquery-confirm.min.css" rel="stylesheet">
  
    <link href="<%=request.getContextPath()%>/resources/css/popup_de_bloqueo_autenticacion.min.css" rel="stylesheet">
	
	
	
	<script type="text/javascript">
		
		$(function(){
			
			$( "#form-TarjetaNoAfiliada" ).validate({
				 rules: {
						"tnoafiliadaBean.stextParrafo1": {
	        	   			required: true ,minlength: 1, maxlength: 300 ,customvalidation: true
	                  	 },
		   				"tnoafiliadaBean.stextParrafo2":{
		   					required: true ,minlength: 1, maxlength: 400 ,customvalidation: true
		   				},
		   				"tnoafiliadaBean.stextParrafo3":{
		   					required: true ,minlength: 1, maxlength: 200 ,customvalidation: true
		   				}
				
				 },
				 messages: {
					 
					 "tnoafiliadaBean.stextParrafo1": {
                         minlength: $.format("Al menos {0} caracteres requeridos!"),
                         maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
                         required: $.format("Favor de ingresar el texto para el parrafo1!")  
                         
		      	   		},
		      	   	"tnoafiliadaBean.stextParrafo2": {
			      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
	                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
	                     required: $.format("Favor de ingresar el texto para el parrafo2!") 
                     },
 		      	   	"tnoafiliadaBean.stextParrafo3": {
			      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
	                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
	                     required: $.format("Favor de ingresar el texto para el parrafo3!") 
                    }
				 }
			});
			
			
			
		      $.validator.addMethod("customvalidation",
		              function(value, element) {
		    	 		
		    	  		/*var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;*/
		    	 		var characterReg = /[`~#$%^&*\n°¬|\=?'"<>\{\}\[\]\\\/]/gi;
		   				
		   					if(value == null ||  value =='' )
		   						return true;			
		   					else{
		   						
		   						return !characterReg.test(value);
		   								
		   					}
		              },
		      "No esta permitido caracteres especiales!"
		      ); 
		      
		    cargarLogoMarcaSeleccionada();
			conteoinicial();
			
			$('#vistaReset').click(function(){
				
				$.confirm({
					title: 'confirmación',
					content: '¿Está seguro que desea reanudar la configuración?',
					confirm: function () {
						vistaReset();
					}
				});
		
		    });
			
			
		});
		
		
		


		  function cargarLogoMarcaSeleccionada(){
			  
	   			var form = document.forms[0];

	   			var indice = form.listaBrand.selectedIndex ;
			   	var valorMarcaSeleccionada = form.listaBrand.options[indice].value; 
			   	var boxImagenMarca = $('.Header-logoVerifyVisaContainer img');
			   	var pathArray = window.location.pathname.split( '/' );
			   	
			   	if(valorMarcaSeleccionada!=1 && valorMarcaSeleccionada!=2){
					 alert('Seleccione una Marca Valida');
					 return false;
				 }  
			   	
			   	if(valorMarcaSeleccionada==1){
			   		boxImagenMarca.attr('src', '/' + pathArray[1] + '/resources/images/vpas_logo.gif?ln=img');
			   	}else if(valorMarcaSeleccionada==2){
			   		boxImagenMarca.attr('src', '/' + pathArray[1] + '/resources/images/sc_logo.gif?ln=img');
			   	}
			   	return true;
	   			
			 };
		 

		 function vistaReset(){
				 var form = document.forms[0];
				 
				 if(cargarLogoMarcaSeleccionada()){
					 form.action="vistaResetTarjetaNoAfiliada";
					 form.operacionValidate.value="validateCambiarConfiguracionMarca";
					 form.submit();	 
				 }
		}		 
			 
		
		function conteoinicial(){
			var stextParrafo1 =  document.getElementById('stextParrafo1');
			var stextParrafo2 =  document.getElementById('stextParrafo2');
			var stextParrafo3 =  document.getElementById('stextParrafo3');
			

			var slenParrafo1 =  document.getElementById('frm_stextParrafo1');
			var slenParrafo2 =  document.getElementById('frm_stextParrafo2');
			var slenParrafo3 =  document.getElementById('frm_stextParrafo3');
			
			
			textCounter(stextParrafo1, slenParrafo1 ,300);
			textCounter(stextParrafo2, slenParrafo2 ,400);
			textCounter(stextParrafo3, slenParrafo3 ,200);
			
		}
		
		 function recargarMarca(){
			 
			 var form = document.forms[0];

			 if(cargarLogoMarcaSeleccionada()){
				 
				 form.operacionValidate.value="validateCambiarConfiguracionMarca";
				 form.action="cambiarConfiguracionMarcaTarjetaNoAfiliada";
				 form.submit();
				 
			 }
		 }
		
		function textCounter(param1,param2, sizeLimit){

			var textValue = param1 != null ? param1.value : "";
			var len=   textValue.length;

				if(len > sizeLimit){
							textValue = textValue.substring(0,sizeLimit);
							param1.value=textValue;
				}else{
							
							param2.value=len;
								
				}
		}

	</script>
	
</head>

<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Pantalla Tarjeta No Afiliada</h4>
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
		<form  id="form-TarjetaNoAfiliada" class="form-horizontal" method="post" action="registrarConfigTarjetaNoAfiliada" enctype="multipart/form-data" >
				<s:hidden  name="operacionValidate" value="validateConfiguracionTarjetaNoAfiliada"/>
				<s:hidden  name="tnoafiliadaBean.sflagPoseeConfiguracion"/>
				
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
														value="brandSelected" id="listaBrand" onchange="recargarMarca()"/>
												</div>
											</div>
								
										</div>							
							</div>
						</div>
					</div>


		<div class="panel panel-default">
		
			<div class="panel-heading">Configuraci&oacute;n</div>
			<div class="panel-body">
				<div class="row">
						
						<div class="col-lg-6">
						
							<div style="margin-top: 5%;margin-bottom: 10%;"> 
							


						<div class="col-lg-12">
							<div class="form-group">
								<div class="col-xs-3">
															<label for="inputText">Parrafo1</label>
								</div>
								<div class="col-xs-9">
									<s:textarea cssClass="form-control1" name="tnoafiliadaBean.stextParrafo1"  id="stextParrafo1" onkeyup="textCounter(this,frm_stextParrafo1,300)"/>
									<p class="help-block">
										M&aacute;ximo 300 caracteres    
										
										<input name="frm_stextParrafo1" size="4" id="frm_stextParrafo1"
											maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
											readonly="readonly" tabindex="-1"> 
									</p>
								</div>					     		
							</div>
						</div>

						<div class="col-lg-12">
							<div class="form-group">
								<div class="col-xs-3">
															<label for="inputText">Parrafo2</label>
								</div>
								<div class="col-xs-9">
									<s:textarea cssClass="form-control1" name="tnoafiliadaBean.stextParrafo2" id="stextParrafo2" onkeyup="textCounter(this,frm_stextParrafo2 ,400)"/>
									
									<p class="help-block">
										M&aacute;ximo 400 caracteres    
										
										<input name="frm_stextParrafo2" size="4" id="frm_stextParrafo2"
											maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
											readonly="readonly" tabindex="-1"> 
									</p>
									
								</div>					     		
							</div>
						</div>										
					


						<div class="col-lg-12">
							<div class="form-group">
								<div class="col-xs-3">
															<label for="inputText">Parrafo3</label>
								</div>
								<div class="col-xs-9">
									<s:textarea cssClass="form-control1" name="tnoafiliadaBean.stextParrafo3" id="stextParrafo3" onkeyup="textCounter(this,frm_stextParrafo3,200)"/>
									
									<p class="help-block">
										M&aacute;ximo 200 caracteres    
										
										<input name="frm_stextParrafo3" size="4" id="frm_stextParrafo3"
											maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
											readonly="readonly" tabindex="-1"> 
									</p>
									
								</div>					     		
							</div>
						</div>	
						
						<div class="col-lg-12">
							<div class="form-group">
								<div class="col-xs-6" align="right">
									   <div id="vistaReset" class="btn btn-primary" >Reanudar Configuraci&oacute;n</div>
								</div>
								<div class="col-xs-6" align="center">
										<input type="submit" class="btn btn-primary" value="Grabar">		
					     		</div>	
					     							
							</div>
						</div>	
					
							
							
							</div>
						
						
						
						</div>
						
						<div class="col-lg-6">

					<div class="Site-outerWrapper">
						<div class="Site-wrapper">
							<!--HEADER-->
							<div class="Header">
								<div class="Header-logoVerifyVisaContainer">
									<img src="<%=request.getContextPath()%>/resources/images/vpas_logo.gif" alt="Verify by Visa">
								</div>
								<div style="float: right;">
									<img alt="Logo"	src="logoIssuer?brandSelected=<s:property value="brandBean.intIdBrand"/>" width="140" height="47" />
								</div>
							</div>
							<!--MAIN-->
							<div class="Main">
								<h1 class="Main-title u-colorNaranja">Tarjeta No Afiliada</h1>
								<div class="Main-content">
										<div class="Main-descripcion">
											<s:if test="%{tnoafiliadaBean.sflagPoseeConfiguracion == 'true' }">
													
														
														<p> 
														
															<!--  <s:label name="tnoafiliadaBean.stextVistaPreviaParrafo1"/> --> 
															${tnoafiliadaBean.stextVistaPreviaParrafo1}
															<s:hidden name="tnoafiliadaBean.stextVistaPreviaParrafo1"/>
														</p>
																											
														<p>
														
															<!--  <s:label name="tnoafiliadaBean.stextVistaPreviaParrafo2"/>  --> 
															${tnoafiliadaBean.stextVistaPreviaParrafo2}
															<s:hidden name="tnoafiliadaBean.stextVistaPreviaParrafo2"/>
														</p>
																							
														<p>
														
															<!--  <s:label name="tnoafiliadaBean.stextVistaPreviaParrafo3"/>  --> 
															${tnoafiliadaBean.stextVistaPreviaParrafo3}
															<s:hidden name="tnoafiliadaBean.stextVistaPreviaParrafo3"/>
														</p>
											</s:if>
											
											<s:if test="%{tnoafiliadaBean.sflagPoseeConfiguracion == 'false' }">
												<p>No tiene registrado ninguna configuraci&oacute;n para esta marca, Favor de verificar los valores en la parte inferior y luego seleccionar el bot&oacute;n Grabar</p>
											</s:if>		

											
										</div>
										<div class="FormButtons">
											<img src="<%=request.getContextPath()%>/resources/images/btnContinuar.png" alt="" height="29" width="101">	
										</div>
								</div>
							</div>
						</div>
					</div>
						
						</div>
						
	
			


				</div>
			</div>
		</div>
		
	
		</form>
	</div>

</body>

