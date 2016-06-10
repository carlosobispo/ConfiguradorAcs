<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<head>

	<meta http-equiv=Content-Type content=text/html; charset=UTF-8>  
	
	
	
	<!--  <script src="< %=request.getContextPath()%>/resources/js/jquery.min.js"></script> -->
	
	<!--  <script src="< %=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>  -->
	
	<!--  <script src="< %=request.getContextPath()%>/resources/js/jquery-confirm.js"></script> -->
	
	<link href="<%=request.getContextPath()%>/resources/css/main.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/popup_de_autenticacion.min.css" rel="stylesheet">
	
	<link href="<%=request.getContextPath()%>/resources/css/jquery-confirm.min.css" rel="stylesheet">

	<script type="text/javascript">
			
		$(function(){
			
				
				$( "#form-AutenticacionClaveIncorrectaExpirada" ).validate({
					 rules: {
							"aclaveincorrectaBean.stextTituloOTP": {
		        	   			required: true ,minlength: 1, maxlength: 150 ,customvalidationTituloPopup: true
		                  	 },
		                  	 "aclaveincorrectaBean.stextlblClaveDinamica":{
		                  		required: true ,minlength: 1, maxlength: 20 ,customvalidation: true
		                  	 },
			   				"aclaveincorrectaBean.stextClaveIncorrecta":{
			   					required: true ,minlength: 1, maxlength: 90 ,customvalidation: true
			   				},
			   				"aclaveincorrectaBean.stextClaveExpirada":{
			   					required: true ,minlength: 1, maxlength: 90 ,customvalidation: true
			   				}
					
					 },
					 messages: {
						 
						 	"aclaveincorrectaBean.stextTituloOTP": {
		                         minlength: $.format("Al menos {0} caracteres requeridos!"),
		                         maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                         required: $.format("Favor de ingresar el texto para el Titulo OTP!")  
	                         
			      	   		},
			      	   		"aclaveincorrectaBean.stextlblClaveDinamica": {
					      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
			                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
			                     required: $.format("Favor de ingresar el texto para el La etiqueta Clave Dinámica!") 
		                     },
		                     
			      	   		"aclaveincorrectaBean.stextClaveIncorrecta": {
					      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
			                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
			                     required: $.format("Favor de ingresar el texto para Clave Incorrecta!") 
	                     	},
				      	   	"aclaveincorrectaBean.stextClaveExpirada": {
					      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
			                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
			                     required: $.format("Favor de ingresar el texto para Clave Expirada!") 
		                     }
			      	   		
			      	   		
					 }
				});
				
				
				 $.validator.addMethod("customvalidationTituloPopup",
			              function(value, element) {
			    	 		/* var characterReg = /[`~#$%^&*()\n°¬|+\=?'<>\{\}\[\]\\\/]/gi;*/
			    	 		var characterReg = /[`~#$%^&*()\n°¬|+\=¿?'<>\{\}\[\]\\\/]/gi;
			    	 		
			    	 		
			    	 		if(value == null ||  value =='' )
			   						return true;			
			   					else{
			   						
			   						return !characterReg.test(value);
			   								
			   					}
			              },
			      "No esta permitido caracteres especiales!"
			      ); 
				
			      $.validator.addMethod("customvalidation",
			              function(value, element) {
			    	 		/* var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;*/
			    	 		
			    	  		var characterReg = /[`~#$%^&*()\n°¬|+\=¿?'"<>\{\}\[\]\\\/]/gi;
			    	 		
			    	 		if(value == null ||  value =='' )
			   						return true;			
			   					else{
			   						return !characterReg.test(value);		
			   					}
			              },
			      "No esta permitido caracteres especiales!"
			      ); 
			      
				
				cargarLogoMarcaSeleccionada();
				
				
				
				var sflagMostrarCelular =  document.getElementById('sflagMostrarCelular');
				MostrarOcultarCantidadDigitosEnmascarados(sflagMostrarCelular);
				
				conteoinicial();
				
				
				$('#vistaPreviaClaveIncorrecta').click(function(){
					//$("#MensajeResultado").hide("slow");
					vistaPreviaClaveIncorrecta();
					
		        });
				
				
				$('#vistaPreviaClaveExpirada').click(function(){
					//$("#MensajeResultado").hide("slow");
					vistaPreviaClaveExpirada();
		        });
				
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
			
			
			 function vistaPreviaClaveIncorrecta(){
				 
				 var form = document.forms[0];

				 if(cargarLogoMarcaSeleccionada()){
					 form.indicardorVistaPrevia.value="vistaPreviaClaveIncorrecta";
					 form.action="vistaPrevia";
					 form.submit();	 
				 }
			 } 		
			
			 
			 function vistaPreviaClaveExpirada(){
				 
				 var form = document.forms[0];

				 if(cargarLogoMarcaSeleccionada()){
					 form.indicardorVistaPrevia.value="vistaPreviaClaveExpirada";
					 form.action="vistaPrevia";
					 form.submit();	 
				 }
			 } 				
			
			 
			 function vistaReset(){
				 var form = document.forms[0];
				 
				 if(cargarLogoMarcaSeleccionada()){
					 form.action="vistaResetAutenticacionclaveincorrecta";
					 form.operacionValidate.value="validateCambiarConfiguracionMarca";
					 form.submit();	 
				 }
			 }
			 
			 
			 function MostrarOcultarCantidadDigitosEnmascarados(objeto){
				  
				  var objState = objeto.checked ? true : false;
				  
				  if(objState){
					  $("#cantidadDigitosNoEnmascarados").show("slow");
				  }else{
					  $("#cantidadDigitosNoEnmascarados").hide("slow");
				  }
			  }	
			  	
			
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
				 
				 
					function conteoinicial(){
						
						var stextTituloOTP =   document.getElementById('stextTituloOTP');
						var stextlblClaveDinamica =   document.getElementById('stextlblClaveDinamica');
						var stextClaveIncorrecta =  document.getElementById('stextClaveIncorrecta');
						var stextClaveExpirada =  document.getElementById('stextClaveExpirada');

						var slenTituloOTP =  document.getElementById('frm_stextTituloOTP');
						var slenClaveIncorrecta =  document.getElementById('frm_stextClaveIncorrecta');
						var slenClaveExpirada=  document.getElementById('frm_stextClaveExpirada');
						var slenlblClaveDinamica=  document.getElementById('frm_stextlblClaveDinamica');
						
						textCounter(stextTituloOTP, slenTituloOTP ,105);
						textCounter(stextlblClaveDinamica, slenlblClaveDinamica ,20);
						textCounter(stextClaveIncorrecta, slenClaveIncorrecta ,90);
						textCounter(stextClaveExpirada, slenClaveExpirada ,90);
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
				 
				 function recargarMarca(){
					 
					 var form = document.forms[0];

					 if(cargarLogoMarcaSeleccionada()){
					
						 form.operacionValidate.value="validateCambiarConfiguracionMarca";
						 form.action="cambiarConfiguracionMarcaAutenticacionClaveIncorrecta";
						 form.submit();	
						 
					 }
				 } 
				 
	</script>

</head>

<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Pantalla Autenticaci&oacute;n clave incorrecta - expirada</h4>
			
		
			<s:if test="%{getFieldErrors().get('mensajeERROR')!=null}">
				<div class="alert alert-danger" id="MensajeResultado">
					<s:property value="getFieldErrors().get('mensajeERROR')"/>
				</div>
			</s:if>
			
			<s:if test="%{getFieldErrors().get('mensajeSUCCESS')!=null}">
				<div class="alert alert-success" id="MensajeResultado">
					<s:property value="getFieldErrors().get('mensajeSUCCESS')" />
				</div>
			</s:if>
			
			<s:if test="%{getFieldErrors().get('mensajeALERTA')!=null}">
				<div class="alert alert-warning" id="MensajeResultado">
					<s:property value="getFieldErrors().get('mensajeALERTA')" />
				</div>
			</s:if>		
					
	     	<form  id="form-AutenticacionClaveIncorrectaExpirada" class="form-horizontal" method="post" action="registrarConfiguracionAutenticacionClaveIncorrecta" > 
					<s:hidden  name="aclaveincorrectaBean.stextVistaPreviaTituloOTP"/>
				    <s:hidden  name="operacionValidate" value="validateConfiguracionAutenticacionClaveIncorrecta"/>
				
				
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
												<label for="inputText">T&iacute;tulo en el popup OTP</label>
											</div>
											<div class="col-xs-9">
												<s:textarea cssClass="form-control"  name="aclaveincorrectaBean.stextTituloOTP" id="stextTituloOTP" onkeyup="textCounter(this,frm_stextTituloOTP,105)"/>
												<p class="help-block">
														M&aacute;ximo 105 caracteres    
														<input name="frm_stextTituloOTP" size="4" id="frm_stextTituloOTP"
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
									     				<s:checkbox  name="aclaveincorrectaBean.sflagMostrarCelular"  id="sflagMostrarCelular" onclick="MostrarOcultarCantidadDigitosEnmascarados(this)"/>
									     				 Mostrar N&uacute;mero de Celular
									     			</label>
									     		</div>
											</div>
											<div class="col-xs-9">
												<div id="cantidadDigitosNoEnmascarados">
														<!--   <label for="inputText"></label> -->
														<s:select label="Cantidad de digitos no enmascarados" cssClass="form-control2"
																		list="#{'3':'3', '4':'4', '5':'5', '6':'6'}"  name="aclaveincorrectaBean.stextDigitoCelular"
																		 id="stextDigitoCelular"  value="aclaveincorrectaBean.selectetedDigitoCelular"/>
												</div>	
											</div>							
										</div>					
									</div>
									
									<div class="col-lg-12">
										<div class="form-group">
											<div class="col-xs-3">
												<label for="inputText">Texto de "Clave Din&aacute;mica":</label>
											</div>
											<div class="col-xs-9">
												<s:textfield cssClass="form-control"  name="aclaveincorrectaBean.stextlblClaveDinamica" id="stextlblClaveDinamica" onkeyup="textCounter(this,frm_stextlblClaveDinamica,20)"/>
												<p class="help-block">
														M&aacute;ximo 20 caracteres    
														
														<input name="frm_stextlblClaveDinamica" size="4" id="frm_stextlblClaveDinamica"
															maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
															readonly="readonly" tabindex="-1"> 
												</p>
											</div>							
										</div>					
									</div>		
												
									<div class="col-lg-12">
										<div class="form-group">
											<div class="col-lg-3">
												<label for="inputText">Texto de "Clave Incorrecta":</label>
											</div>
											<div class="col-lg-7">
											
												<s:textarea  cssClass="form-control"  name="aclaveincorrectaBean.stextClaveIncorrecta" id="stextClaveIncorrecta"  onkeyup="textCounter(this,frm_stextClaveIncorrecta,90)"/>
												<p class="help-block">
														M&aacute;ximo 90 caracteres    
														
														<input name="frm_stextClaveIncorrecta" size="4" id="frm_stextClaveIncorrecta"
															maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
															readonly="readonly" tabindex="-1"> 
												</p>
											
											</div>	
											<div class="col-lg-2">
												 <input type="submit" id="vistaPreviaClaveIncorrecta"  style=" display: inline-block; font-weight: 700;margin-bottom: 5px;" class="btn btn-primary" value="Vista Previa"/>		
															
									     	</div>						
										</div>					
									</div>	
														
									<div class="col-lg-12">
										<div class="form-group">
											<div class="col-lg-3">
												<label for="inputText">Texto de "Clave Expirada":</label>
											</div>
											
											<div class="col-lg-7">
												<s:textarea cssClass="form-control"  name="aclaveincorrectaBean.stextClaveExpirada" id="stextClaveExpirada"  onkeyup="textCounter(this,frm_stextClaveExpirada,90)"/>
												<p class="help-block">
														M&aacute;ximo 90 caracteres    
														
														<input name="frm_stextClaveExpirada" size="4" id="frm_stextClaveExpirada"
															maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
															readonly="readonly" tabindex="-1"> 
												</p>
											</div>
											
											<div class="col-lg-2">
													    <input type="submit" id="vistaPreviaClaveExpirada" style=" display: inline-block; font-weight: 700;margin-bottom: 5px;" class="btn btn-primary" value="Vista Previa">
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

      <!--CONTENIDO-->
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
          <div class="Main-content">
            <!--FORMULARIO DE AUTENTICACIÓN-->
            <div class="Main-formulario">
            <!--   <form id="formulario_autenticacion" action="#" method="post" class="FormularioAutenticacion"> -->
                
                <s:if test="%{aclaveincorrectaBean.stextVistaPreviaTituloOTP != '' }">
                	<p class="FormularioAutenticacion-title" align="justify">
                        <!--   <s:label name="aclaveincorrectaBean.stextVistaPreviaTituloOTP"/>  -->
                		${aclaveincorrectaBean.stextVistaPreviaTituloOTP}
                	</p>
                </s:if>				
                <s:else>
                	<p class="FormularioAutenticacion-title">Favor de configurar el t&iacute;tulo del popup OTP en la parte inferior, luego seleccionar el bot&oacute;n Grabar</p>
                </s:else>
                
                
                <ul class="FormularioAutenticacion-listaCampos">
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel">Comercio:</label>
                    <div class="FormularioAutenticacion-campoValue"><span>ACS INLINE EXPRESS EXP</span></div>
                  </li>
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel">Valor Total:</label>
                    <div class="FormularioAutenticacion-campoValue">USD 40.00</div>
                  </li>
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel">Fecha:</label>
                    <div class="FormularioAutenticacion-campoValue"><span>18 / 08 / 14</span></div>
                  </li>
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel">N&uacute;mero de Tarjeta:</label>
                    <div class="FormularioAutenticacion-campoValue"><span>*** *** *** *** 0001</span></div>
                  </li>
                  
                  <s:if test="%{aclaveincorrectaBean.sflagVistaPreviaMostrarCelular == 'true' }">
                  
		                  <li class="FormularioAutenticacion-campoContainer">
		                    <label class="FormularioAutenticacion-campoLabel">N&uacute;mero de Celular:</label>
		                    <div class="FormularioAutenticacion-campoValue"><span> <s:label name="aclaveincorrectaBean.stextVistaPreviaNumCelular"/> </span></div>
		                  </li> 
                                    
                  </s:if>
          
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel">Mensaje Personal:</label>
                    <div class="FormularioAutenticacion-campoValue"><span>Compra Segura</span></div>
                  </li>
                  
                  <li class="FormularioAutenticacion-campoContainer">
                    <label class="FormularioAutenticacion-campoLabel label-message label-message--focus">
		               
		                <s:if test="%{aclaveincorrectaBean.stextVistaPrevialblClaveDinamica != '' }">
		                	<!--  <s:label name="aclaveincorrectaBean.stextVistaPrevialblClaveDinamica"/>  --> 
		                 <span class="u-colorNaranja">${aclaveincorrectaBean.stextVistaPrevialblClaveDinamica}</span>
		                </s:if>	
                    	<s:else>
                    		Texto No Definido
                    	</s:else>
                     </label>
                    
                    <div class="FormularioAutenticacion-campoValue">
                      <span class="FormularioAutenticacion-iconKey"><img src="<%=request.getContextPath()%>/resources/images/icon-key.png" alt="" height="16" width="16"></span>
                      <input type="password" name="ipt_password" id="ipt_password" maxlength="7" autocomplete="off" readonly class="FormularioAutenticacion-input FormularioAutenticacion-input--password"><span class="FormularioAutenticacion-errorMessage">Su contraseña es incorrecta</span>
                    	
                      <span style="display:block" class="FormularioAutenticacion-errorMessage">
                      		 <s:hidden name="aclaveincorrectaBean.stextVistaPreviaClave"/>
			                 <s:hidden name="aclaveincorrectaBean.indicardorVistaPrevia" id="indicardorVistaPrevia"/>
			                  
			                <s:if test="%{aclaveincorrectaBean.stextVistaPreviaClave!= '' }">
			                	<s:label name="aclaveincorrectaBean.stextVistaPreviaClave"/>
			                </s:if>	

                      </span>		
                    </div>
                 
                  </li>
                  
                  <li class="FormularioAutenticacion-campoContainer">
                    <div class="FormularioAutenticacion-tecladoContainer">
                      <div id="number-6" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-6.png" alt=""></div>
                      <div id="number-1" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-1.png" alt=""></div>
                      <div id="number-8" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-8.png" alt=""></div>
                      <div id="number-3" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-3.png" alt=""></div>
                      <div id="number-5" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-5.png" alt=""></div>
                      <div id="number-0" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-0.png" alt=""></div>
                      <div id="number-4" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-4.png" alt=""></div>
                      <div id="number-2" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-2.png" alt=""></div>
                      <div id="number-7" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-7.png" alt=""></div>
                      <div id="number-9" class="FormularioAutenticacion-tecla"><img src="<%=request.getContextPath()%>/resources/images/tecla-9.png" alt=""></div>
                      <div id="tecla-limpiar" class="FormularioAutenticacion-tecla FormularioAutenticacion-tecla--limpiar"><img src="<%=request.getContextPath()%>/resources/images/tecla-limpiar.png" alt=""></div>
                    </div>
                  </li>
                </ul>
                <div class="FormButtons">
                  <div class="FormButtons-enviarContainer">
                    <img class="btnEnviar" src="<%=request.getContextPath()%>/resources/images/btnEnviar.png" alt="" height="28" width="80"> 
                    <div class="FormButtons-iconContainer"><i class="FormButtons-iconAyuda"><a href="#"><img src="<%=request.getContextPath()%>/resources/images/icon-help.png" alt="" height="20" width="20"></a></i></div><span class="FormButtons-textoCancelar"><a id="btn-cancelar" href="#">Cancelar</a></span>
                  </div>
                </div>
	            
             <!--   </form>  -->
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

