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
			
			$( "#form-TarjetaBloqueada" ).validate({
				 rules: {
						"tbloqueadaBean.stextParrafo1": {
	        	   			required: true ,minlength: 1, maxlength: 250 ,customvalidation: true
	                  	 },
		   				"tbloqueadaBean.stextParrafo2":{
		   					required: true ,minlength: 1, maxlength: 160 ,customvalidation: true
		   				}
				
				 },
				 messages: {
					 
					 "tbloqueadaBean.stextParrafo1": {
                         minlength: $.format("Al menos {0} caracteres requeridos!"),
                         maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
                         required: $.format("Favor de ingresar el texto para el parrafo1!")  
                         
		      	   		},
		      	   	"tbloqueadaBean.stextParrafo2": {
			      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
	                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
	                     required: $.format("Favor de ingresar el texto para el parrafo2!") 
                     }
				 }
			});
			
			
			
		      $.validator.addMethod("customvalidation",
		              function(value, element) {
		    	 		var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		
		    	 		if(value == null ||  value =='' )
		   						return true;			
		   					else{
		   						
		   						return !characterReg.test(value);
		   								
		   					}
		              },
		      "No esta permitido caracteres especiales!"
		      ); 
		      
			cargarLogoMarcaSeleccionada();
			
			var sflagParrafo1 =  document.getElementById('sflagParrafo1');
			var sflagParrafo2 =  document.getElementById('sflagParrafo2');

			fncHabilitarParrafo1(sflagParrafo1,'false');
			fncHabilitarParrafo2(sflagParrafo2,'false');

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
		 
		 function recargarMarca(){
			 
			 var form = document.forms[0];

			 if(cargarLogoMarcaSeleccionada()){
				 
				 form.operacionValidate.value="validateCambiarConfiguracionMarca";
				 form.action="cambiarConfiguracionMarcaTarjetaBloqueada";
				 form.submit();
				 
			 }
		 }
		 
		 
		 
		 
		 function vistaReset(){
			 var form = document.forms[0];
			 
			 if(cargarLogoMarcaSeleccionada()){
				 form.action="vistaResetTarjetaBloqueada";
				 form.operacionValidate.value="validateCambiarConfiguracionMarca";
				 form.submit();	 
			 }
		 }
		 
		
		function fncHabilitarParrafo1( objeto , limpiarTexto ){

			var form = document.forms[0];
			var objState = objeto.checked ? true : false;
			var limpiarText = limpiarTexto == 'true' ? true : false;
   			var indice = form.listaBrand.selectedIndex ;
		   	var valorMarcaSeleccionada = form.listaBrand.options[indice].value; 
			
			if(objState){
				form.stextParrafo1.disabled=false;
				form.stextParrafo1.focus();
				
				if(limpiarText){
					
				   	if(valorMarcaSeleccionada==1){
				   		form.stextParrafo1.value="Para proteger su tarjeta Visa contra el uso no autorizado, por el momento, esta no puede ser usada para compras por Internet en ninguna de las tiendas afiliadas a Verified by Visa. Para mayor información, comuníquese con su Banco.";
				   	}else if(valorMarcaSeleccionada==2){
				   		form.stextParrafo1.value="Para proteger su tarjeta MasterCard contra el uso no autorizado, por el momento, esta no puede ser usada para compras por Internet en ninguna de las tiendas afiliadas a SecureCode. Para mayor información, comuníquese con su Banco.";
				   	}
				}
				
			}else{
			
				form.stextParrafo1.disabled=true;
				if(limpiarText){
					form.stextParrafo1.value="";			
				}
			}
			
			var stextParrafo1 =  document.getElementById('stextParrafo1');
			var slenParrafo1 =  document.getElementById('frm_stextParrafo1');
			
			textCounter(stextParrafo1, slenParrafo1 ,250);
			
		}
		
		function fncHabilitarParrafo2( objeto , limpiarTexto ){

			var form = document.forms[0];
			var objState = objeto.checked ? true : false;
			var limpiarText = limpiarTexto == 'true' ? true : false;

			if(objState){
				form.stextParrafo2.disabled=false;
				
				if(limpiarText){
					
					form.stextParrafo2.value="A continuación el proceso de compra se reestablecerá automáticamente en unos momentos, de lo contrario, presione el botón para continuar el proceso.";
				}
				
			}else{
				form.stextParrafo2.disabled=true;
				
				if(limpiarText){
					form.stextParrafo2.value="",
					form.stextParrafo2.focus();
				}
			}

			var stextParrafo2 =  document.getElementById('stextParrafo2');
			var slenParrafo2 =  document.getElementById('frm_stextParrafo2');
			textCounter(stextParrafo2, slenParrafo2 ,160);

		}	
		
		function conteoinicial(){
			var stextParrafo1 =  document.getElementById('stextParrafo1');
			var stextParrafo2 =  document.getElementById('stextParrafo2');

			var slenParrafo1 =  document.getElementById('frm_stextParrafo1');
			var slenParrafo2 =  document.getElementById('frm_stextParrafo2');
			
			textCounter(stextParrafo1, slenParrafo1 ,250);
			textCounter(stextParrafo2, slenParrafo2 ,160);
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
		
		/*
		function textCounter(param1,param2, sizeLimit){

			var textValue = param1 != null ? param1.value : "";
			
			var text = textValue.replace(/[áéíóúÁÉÍÓÚñÑ]/g, '&Xacute;');
			var lenSinReplace=   textValue.length;
			var lenConReplace =  text.length;

				if(lenSinReplace > sizeLimit){
							textValue = textValue.substring(0,sizeLimit);
							param1.value=textValue;
				}else{
							if(param2 != null){
								
										if(lenConReplace>sizeLimit){
											textValue = textValue.substring(0,textValue.length-1);
											param1.value=textValue;
										}else{
											param2.value=lenConReplace;
										}
							}		
				}
		}
*/
		
		
	</script>
	


</head>

<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Pantalla Tarjeta Bloqueada</h4>
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
		<form  id="form-TarjetaBloqueada" class="form-horizontal" method="post" action="registrarConfigTarjetaBloqueada" enctype="multipart/form-data" >
				<s:hidden  name="operacionValidate" value="validateConfiguracionTarjetaBloqueada"/>
				<s:hidden  name="tbloqueadaBean.sflagPoseeConfiguracion"/>
				
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
		
			<div class="panel-heading">Configuraci&oacute;n</div>
			<div class="panel-body">
				<div class="row">
				
					
					<div class="col-lg-6">
						<div style="margin-top: 5%;margin-bottom: 10%;"> 

						<div class="col-lg-12">
							<div class="form-group">
								<div class="col-xs-3">
									<div class="checkbox">
						     			<label>
						     				<s:checkbox  name="tbloqueadaBean.sflagParrafo1" onclick="fncHabilitarParrafo1(this,'true')" id="sflagParrafo1" />
						     				 Habilitar Parrafo1
						     			</label>
						     		</div>
					     		</div>
								<div class="col-xs-9">
									<s:textarea cssClass="form-control1" name="tbloqueadaBean.stextParrafo1"   disabled="true" id="stextParrafo1" onkeyup="textCounter(this,frm_stextParrafo1,250)"/>
									<p class="help-block">
										M&aacute;ximo 250 caracteres    
										
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
									<div class="checkbox">
						     			<label>
						     				<s:checkbox  name="tbloqueadaBean.sflagParrafo2"  onclick="fncHabilitarParrafo2(this,'true')"  id="sflagParrafo2"/>
						     				 Habilitar Parrafo2
						     			</label>
						     		</div>
					     		</div>
								<div class="col-xs-9">
									<s:textarea cssClass="form-control1" name="tbloqueadaBean.stextParrafo2" disabled="true" id="stextParrafo2" onkeyup="textCounter(this,frm_stextParrafo2,160)"/>
									
									<p class="help-block">
										M&aacute;ximo 160 caracteres    
										
										<input name="frm_stextParrafo2" size="4" id="frm_stextParrafo2"
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
								<h1 class="Main-title u-colorNaranja">Autenticaci&oacute;n Fallida</h1>
								<div class="Main-content">
										<div class="Main-descripcion">
											<s:if test="%{tbloqueadaBean.sflagPoseeConfiguracion == 'true' }">
													<s:hidden name="tbloqueadaBean.sflagVistaPreviaParrafo1"/>
													<s:if test="%{tbloqueadaBean.sflagVistaPreviaParrafo1 == 'true' }">
														
														<p align="justify"> 
															
															<!--  <s:label name="tbloqueadaBean.stextVistaPreviaParrafo1"/>  --> 
															${tbloqueadaBean.stextVistaPreviaParrafo1}
															<s:hidden name="tbloqueadaBean.stextVistaPreviaParrafo1"/>
														</p>
														
													</s:if>
													
													<s:hidden name="tbloqueadaBean.sflagVistaPreviaParrafo2"/>
													<s:if test="%{tbloqueadaBean.sflagVistaPreviaParrafo2 == 'true' }">
														
														<p align="justify">
															
															<!--  <s:label name="tbloqueadaBean.stextVistaPreviaParrafo2"/> --> 
															${tbloqueadaBean.stextVistaPreviaParrafo2}
															<s:hidden name="tbloqueadaBean.stextVistaPreviaParrafo2"/>
														</p>
													</s:if>											
											
											</s:if>
											
											<s:if test="%{tbloqueadaBean.sflagPoseeConfiguracion == 'false' }">
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

