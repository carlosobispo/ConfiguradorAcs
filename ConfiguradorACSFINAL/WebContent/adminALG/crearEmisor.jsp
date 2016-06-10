<%@taglib uri="/struts-tags"  prefix="s"%>
<head>

    <!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>

	<script type="text/javascript">
	
	$(function(){
		
	
		
		$( "#form-CrearEmisor" ).validate({
	           rules: {
	        	   		"emisor.intIdIssuer": {
	        	   			required: true ,minlength: 1, maxlength: 3,  number: true
	                  	 },
		   				"emisor.svcName":{
		   					minlength: 1, maxlength: 80, customvalidation: true
		   				},
		   				"emisor.svcInitials":{
		   					minlength: 1, maxlength: 10,customvalidationLetra: true
		   				},
		   				"emisor.schAuthenticateby":{
		   					maxlength: 1, customvalidationLetra: true
		   				},
		   				"emisor.schEnrolltype":{
		   					maxlength: 1, customvalidationLetra: true
		   				},
		   				"emisor.schCharpaddingpin":{
		   					maxlength: 1, customvalidationLetra: true
		   				},
		   				"emisor.shorasDesbloqueo":{
		   					minlength: 1, maxlength: 2,number: true
		   				},		   				  				
		   				"emisor.snameCarrier":{
		   					customvalidationLetra: true
		   				},
		   				"emisor.scarrierClassImpl":{
		   					required: true
		   				},
		   				"emisor.spuerto":{
		   					number: true
		   				},
		   				"emisor.susuarioEmail":{
		   					email: true
		   				}
		   				
	           },
	           messages: {
	        	   		"emisor.intIdIssuer": {
	                           minlength: $.format("Al menos {0} caracteres requeridos!"),
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
	                           number: $.format("Favor de ingresar un ID Emisor v&aacute;lido!"),
	                           required: $.format("Favor de ingresar un ID Emisor!")  
	                           
	        	   		},
	        	   		"emisor.svcName": {
	                           minlength: $.format("Al menos {0} caracteres requeridos!"),
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!")
	        	   		},
	        	   		
	        	   		"emisor.svcInitials": {
	                           minlength: $.format("Al menos {0} caracteres requeridos!"),
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!")
	        	   		},
	        	   		"emisor.shorasDesbloqueo": {
	                           minlength: $.format("Al menos {0} caracteres requeridos!"),
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
	                           number: $.format("Favor de ingresar un tiempo de desbloqueo v&aacute;lido!")
	        	   		},
	        	   		"emisor.schAuthenticateby": {
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!")
	        	   		}
	        	   		,
	        	   		"emisor.schEnrolltype": {
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!")
	        	   		}
	        	   		,
	        	   		"emisor.schCharpaddingpin": {
	                           maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!")
	        	   		},
	        	   		"emisor.scarrierClassImpl": {
	        	   			required: $.format("Favor de ingresar un el nombre de la clase para la implementación de la Telco!")  
	        	   		},
	        	   		"emisor.spuerto": {
	        	   			   number: $.format("Favor de ingresar un Puerto v&aacute;lido!")
	        	   		},
	        	   		"emisor.susuarioEmail": {
	        	   				email: $.format("Favor de ingresar un Email v&aacute;lido!")
	        	   		}
	           }
	   	});
		
	      $.validator.addMethod("customvalidation",
	              function(value, element) {
	   					if(value == null ||  value =='' )
	   						return true;			
	   					else
	   					   return /^[A-Za-z\d_ -]+$/.test(value);	
	   				   
	              },
	      "No esta permitido caracteres especiales!"
	      ); 
	      
	      $.validator.addMethod("customvalidationLetra",
	              function(value, element) {
	   					if(value == null ||  value =='' )
	   						return true;			
	   					else
	   					   return /^[A-Za-z]+$/.test(value);	
	   				   
	              },
	      "Solo esta permitido letras!"
	      ); 	      
	      
			var objetoTiempoDesbloqueo = document.getElementById('emisorSflagTiempoDesbloqueo');
			var objetoSMS = document.getElementById('emisorSflagHabilitarSMS');
			var objetoEmail =  document.getElementById('emisorSflagHabilitarEmail');
			
			fncHabilitarTiempoDesbloqueo(objetoTiempoDesbloqueo,'false');
			fncHabilitarSMS(objetoSMS,'false');
			fncHabilitarEmail(objetoEmail,'false');
	      
	      
		
	});
	

	
	function fncHabilitarTiempoDesbloqueo( objeto , limpiarTexto ){

		var form = document.forms[0];
		var objState = objeto.checked ? true : false;
		var limpiarText = limpiarTexto == 'true' ? true : false;

		if(objState){
			form.txtTiempoDesbloqueo.disabled=false
		}else{
			form.txtTiempoDesbloqueo.disabled=true
		}

		if(limpiarText){
			form.txtTiempoDesbloqueo.value="",
			form.txtTiempoDesbloqueo.focus();
		}
	}
	
	
	function fncHabilitarSMS(objeto , limpiarTexto){
		var form = document.forms[0];
		var objState = objeto.checked ? true : false;
		var limpiarText = limpiarTexto == 'true' ? true : false;
		
		if(objState){
			form.txtNombreCarrier.disabled=false,
			form.txtCarrierClassImpl.disabled=false;
			
			if(limpiarText){
				
				form.txtNombreCarrier.value="INFOBIB",
				form.txtCarrierClassImpl.value="com.alignet.iiidsecure.acs.model.CarrierSMSSend";
			}
			
			
		}else{

			form.txtNombreCarrier.disabled=true,
			form.txtCarrierClassImpl.disabled=true;
			
			if(limpiarText){
				form.txtNombreCarrier.value="",
				form.txtCarrierClassImpl.value="";
			}
		}
		
	}	
	
	function fncHabilitarEmail(objeto , limpiarTexto){
		var form = document.forms[0];
		var objState = objeto.checked ? true : false;
		var limpiarText = limpiarTexto == 'true' ? true : false;
		
		if(objState){
			form.txtHost.disabled=false,
			form.txtHost.focus(),
			form.txtPuerto.disabled=false,
			form.txtUsuario.disabled=false,
			form.txtClave.disabled=false;
		
			if(limpiarText){
				form.txtHost.value="smtpcorp.com",
				form.txtPuerto.value="2525",
				form.txtUsuario.value="fidenciano.galvez@alignet.com",
				form.txtClave.value="12345678Aa";
			}
			
		}else{
			form.txtHost.disabled=true,
			form.txtPuerto.disabled=true,
			form.txtUsuario.disabled=true,
			form.txtClave.disabled=true;

			if(limpiarText){
				form.txtHost.value="",
				form.txtPuerto.value="",
				form.txtUsuario.value="",
				form.txtClave.value="";
			}
			
		}
		
	}
	
	function fncSeleccionarVerificar(){
		var form = document.forms[0];
		form.operacionValidate.value="verificarIdEmisor";
	}
	
	</script>
</head>

<body>
<div class="col-lg-12">

	
	<div class="col-lg-12">
				<s:a action="irlistarEmisor" style="float: right;display: inline-block;font-weight: 700;margin-bottom: 5px;max-width: 100%;">Retornar</s:a>
	</div>	
	<h3 class="page-header"> ADMINISTRACION </h3>
	<s:if test="%{getFieldErrors().get('mensajeERROR')!=null}">
		<div class="alert alert-danger">
			<s:property value="getFieldErrors().get('mensajeERROR')"/>
		</div>
	</s:if>
	
	<s:if test="%{getFieldErrors().get('mensajeSUCCESS')!=null}">
		<div class="alert alert-success">
			<s:property value="getFieldErrors().get('mensajeSUCCESS')"/>
		</div>
	</s:if>	
	
	
	<div class="panel panel-default">
		<div class="panel-heading">Registrar Emisor</div>
		<div class="panel-body">
			<div class="row">
				<!--  form-crearEmisor -->
				<form  id="form-CrearEmisor" class="form-horizontal" method="post" action="registrarEmisor">
					<s:hidden  name="operacionValidate" value="registrarNewEmisor"/>
					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
										<label for="inputText">Marca</label>	
							</div>
							<div class="col-xs-6">
								<s:select cssClass="form-control2" list="listBrand" listKey="intIdBrand" listValue="svcName" 
								name="brandBean.intIdBrand" value="brandSelected"/>
							</div>
						</div>
						
					</div>
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Id Emisor</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.intIdIssuer" id="emisor_intIdIssuer" placeholder="ID Emisor"/>
							</div>
							<div class="col-xs-4">
								<input type="submit" class="btn btn-primary" onclick="fncSeleccionarVerificar()" value="Verificar">
							</div>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Nombre Emisor</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.svcName" id="emisor_svcName"  placeholder="Nombre Emisor" maxlength="80"/>
							</div>							
						</div>
					</div>
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Nombre Abreviado Emisor</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.svcInitials"  placeholder="Nombre Abreviado Emisor" cssStyle="text-transform:uppercase;" maxLength="10" />
							</div>							
						</div>
					</div>	
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Clase Implementaci&oacute;n</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.sclaseimplementacion"  placeholder="Clase que implementamentacion logica para cada emisor"/>
							
							</div>							
						</div>
					</div>	
					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Tipo Autenticai&oacute;n por POPUP</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.schAuthenticateby"  placeholder="Tipo de Autenticaion por POPUP" cssStyle="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" maxlength="1"/>
							</div>							
						</div>
					</div>		
								
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Tipo Enrolamiento por POPUP</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.schEnrolltype"  placeholder="Tipo Enrolamiento por POPUP" cssStyle="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" maxlength="1"/>
							</div>							
						</div>
					</div>						
					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Car&aacute;cter de relleno para la generaci&oacute;n del PIN</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.schCharpaddingpin"  placeholder="Caracter de relleno para la generacion del PIN"  cssStyle="text-transform:uppercase;"  onkeyup="javascript:this.value=this.value.toUpperCase();" maxlength="1"/>
							</div>							
						</div>
					</div>					
	
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<label for="inputText">Car&aacute;cter de relleno para la generaci&oacute;n del PAM</label>
							</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.schCharpaddingpam"  placeholder="Caracter de relleno para la generacion del PAM"  cssStyle="text-transform:uppercase;"  onkeyup="javascript:this.value=this.value.toUpperCase();" maxlength="1"/>
							</div>							
						</div>
					</div>		

					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-2">
								<div class="checkbox">
					     			<label>
					     				<s:checkbox  name="emisor.sflagTiempoDesbloqueo" onclick="fncHabilitarTiempoDesbloqueo(this,'true')" id="emisorSflagTiempoDesbloqueo"/>
					     				 Habilitar Tiempo de Desbloqueo 
					     			</label>
					     		</div>
				     		</div>
							<div class="col-xs-6">
								<s:textfield cssClass="form-control" name="emisor.shorasDesbloqueo"  placeholder="Tiempo desbloqueo tarjeta en horas"  disabled="true" id="txtTiempoDesbloqueo"/>
							</div>					     		
						</div>
					</div>

					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-8">
								<div class="checkbox">
					     			<label>
					     			
					     				<s:checkbox  name="emisor.sflagMatrixDecision" />
					     				  Habilitar AFS 
					     				  
					     			</label>
					     		</div>
				     		</div>						
						</div>
					</div>	

					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-8">
								<div class="checkbox">
					     			<label>
					     			
					     				<s:checkbox  name="emisor.sflagAttemps" />
					     				  Habilitar Attemps 
					     				  
					     			</label>
					     		</div>
				     		</div>						
						</div>
					</div>
					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-8">
								<div class="checkbox">
					     			<label>
					     				
					     				<s:checkbox  name="emisor.sflagPantallaTarjetaNoAfiliada" />
					     				 Habilitar pantalla informativa Tarjeta no Afiliada
					     			</label>
					     		</div>
				     		</div>						
						</div>
					</div>
													
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading2">Configuracion canal para envio OTP</div>
								<div class="panel-body">
									<ul class="nav nav-tabs">
										    <li class="active"><a data-toggle="tab" href="#sms">SMS</a></li>
   											<li><a data-toggle="tab" href="#email">Correo Electr&oacute;nico</a></li>
									</ul>
									 <div class="tab-content">
									 		    <div id="sms" class="tab-pane fade in active">
									 		    	<div class="form-group">
									 		    		<div class="col-xs-12">
									 		    		.
									 		    		</div>
									 		    	</div>
									 		    
												     <div class="form-group">
												     	<div class="col-xs-12">
												     		<div class="checkbox">
												     			<label>			
												     				 <s:checkbox  name="emisor.sflagHabilitarSMS" onclick="fncHabilitarSMS(this,'true')" id="emisorSflagHabilitarSMS"/>
					     											 Habilitar SMS
												     			</label>
												     		</div>
												     	</div>
												     </div>
												     
													<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Nombre Carrier</label>
														</div>
														<div class="col-xs-6">
															<s:textfield cssClass="form-control" disabled="true"  name="emisor.snameCarrier"  placeholder="Nombre Carrier" id="txtNombreCarrier"  cssStyle="text-transform:uppercase;"  onkeyup="javascript:this.value=this.value.toUpperCase();"/>
														</div>							
													</div>													     
													<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Clase Implementaci&oacute;n con la Telco</label>
														</div>
														<div class="col-xs-8">
															<s:textfield cssClass="form-control" disabled="true" name="emisor.scarrierClassImpl"   placeholder="URL WS para comunicacion con Telco" id="txtCarrierClassImpl"/>
														</div>							
													</div>													     
												</div>
									 
											    <div id="email" class="tab-pane fade">
									 		    	<div class="form-group">
									 		    		<div class="col-xs-12">
									 		    		.
									 		    		</div>
									 		    	</div>
									 		    	
												     <div class="form-group">
												     	<div class="col-xs-12">
												     		<div class="checkbox">
												     			<label>
												     				 <s:checkbox  name="emisor.sflagHabilitarEmail"   onclick="fncHabilitarEmail(this,'true')" id="emisorSflagHabilitarEmail"/>
					     											 Habilitar Correo Electr&oacute;nico
												     				 
												     			</label>
												     		</div>
												     	</div>
												     </div>									 		    	
									 		    		
													<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Host</label>
														</div>
														<div class="col-xs-6">
															<s:textfield cssClass="form-control"  name="emisor.snameHost" id="txtHost" placeholder="Host" disabled="true"/>
														</div>							
													</div>										 		    		
									 		    	<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Puerto</label>
														</div>
														<div class="col-xs-6">
															<s:textfield cssClass="form-control" name="emisor.spuerto" id="txtPuerto" disabled="true" placeholder="Puerto" />
														</div>							
													</div>
													<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Usuario</label>
														</div>
														<div class="col-xs-6">
															<s:textfield cssClass="form-control" name="emisor.susuarioEmail" id="txtUsuario" disabled="true" placeholder="Usuario" />				
														</div>							
													</div>														
													<div class="form-group">
														<div class="col-xs-2">
															<label for="inputText">Clave</label>
														</div>
														<div class="col-xs-6">
															<s:textfield cssClass="form-control" name="emisor.sclaveEmail" id="txtClave" disabled="true" placeholder="Clave" />
															
														</div>							
													</div>																									    
											    </div>									 
									 </div>
							
									</div>
								</div>
								</div>
						
						</div>
					
					</div>			
				

					
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-xs-11">
									<input type="submit" style=" float: right;display: inline-block; font-weight: 700;margin-bottom: 5px; max-width: 100%;" class="btn btn-primary" value="Registrar">		
				     		</div>						
						</div>
					</div>					
					
					
				</form>
			</div>
		</div>
	</div>
</div>
</body>
