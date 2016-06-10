<%@taglib uri="/struts-tags"  prefix="s"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<head>


	<meta http-equiv=Content-Type content=text/html; charset=UTF-8>  
	
	
	
	<link href="<%=request.getContextPath()%>/resources/css/jquery-confirm.min.css" rel="stylesheet">

	<link href="<%=request.getContextPath()%>/resources/css/estiloSMS.css" rel="stylesheet">
	 
	<script type="text/javascript">
	
		$(function(){
			
			
			cargarLogoMarcaSeleccionada();
			
			//---- Configuracion para EMAIL
			var sflagSaludoinicial =  document.getElementById('sflagSaludoInicial');
			MostrarOcultarDivForChecked(sflagSaludoinicial,'divstextSaludosInicial');
			MostrarOcultarParrafoXFlag();

			MostrarOcultarParrafo();	
			conteoinicial();
			
		});
		
		
		
		function MostrarOcultarDivForChecked(objetoCheked, divMostrarOcultar){
			  
			  var objState = objetoCheked.checked ? true : false;
			  
			  if(objState){
				  $("#"+divMostrarOcultar).show("slow");
			  }else{
				  $("#"+divMostrarOcultar).hide("slow");
			  }
		}
		
		function conteoinicial(){
			
			//SMS
			var stextConfiguracionSMS    =   document.getElementById('stextConfiguracionSMS');
			var slentextConfiguracionSMS =  document.getElementById('frm_stextConfiguracionSMS');
			
			textCounter(stextConfiguracionSMS, slentextConfiguracionSMS ,150);
			
			//EMAIL
			var stextAsuntoEMAIL    =   document.getElementById('stextAsunto');
			var slentextAsuntoEMAIL =  document.getElementById('frm_stextAsunto');
			
			var stextSaludoInicial    =   document.getElementById('stextSaludoInicial');
			var slentextSaludoInicial =  document.getElementById('frm_stextSaludoInicial');
			
			var stextParrafo1    =   document.getElementById('stextParrafo1');
			var slentextParrafo1 =  document.getElementById('frm_stextParrafo1');
			
			var stextParrafo2    =   document.getElementById('stextParrafo2');
			var slentextParrafo2 =  document.getElementById('frm_stextParrafo2');
			
			var stextParrafo3    =   document.getElementById('stextParrafo3');
			var slentextParrafo3 =  document.getElementById('frm_stextParrafo3');
			
			
			textCounter(stextAsuntoEMAIL, slentextAsuntoEMAIL ,50);
			textCounter(stextSaludoInicial, slentextSaludoInicial ,50);
			textCounter(stextParrafo1, slentextParrafo1 ,250);
			textCounter(stextParrafo2, slentextParrafo2 ,250);
			textCounter(stextParrafo3, slentextParrafo3 ,250);

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
		
		
		function MostrarOcultarParrafoXFlag(){
			var form = document.forms[0];
			
			var flagParrafo2 = form.sflagParrafo2.value;
			var flagParrafo3 = form.sflagParrafo3.value;
			
			
			 if(flagParrafo2=='true'){
				  $("#divAddParrafo2").hide("slow");
				  $("#divstextParrafo2").show("slow"); 
					 
				  if(flagParrafo3=='true'){
						 $("#divRemoveParrafo2").hide("slow");
						 $("#divAddParrafo3").hide("slow");
						 
						 $("#divstextParrafo3").show("slow"); 
						 $("#divRemoveParrafo3").show("slow");
					  
				  }else{
					  $("#divRemoveParrafo2").show("slow");
					  $("#divAddParrafo3").show("slow");
					  $("#divstextParrafo3").hide("slow"); 
				  }
				  
			  }else{

				  $("#divAddParrafo2").show("slow");
				  $("#divstextParrafo2").hide("slow");
				  $("#divstextParrafo3").hide("slow");  
			  }
			
			 
		}
		
		
		function MostrarOcultarParrafo(){
			var form = document.forms[0];
			 
			var option = $('#form-NotificacionEnrolamiento .option');

			option.on('click', function(){ 
				var self = $(this);
				
					if(self.find('img').attr('id') == 'addParrafo2'){
						
						 form.sflagParrafo2.value="true";
						 $("#divAddParrafo2").hide("slow");   //OCULTA   $("#").hide("slow");   
						 $("#divstextParrafo2").show("slow"); //MUESTRA   $("#").show("slow");

					}
					else if(self.find('img').attr('id') == 'removeParrafo2'){
						
						 form.sflagParrafo2.value="false";
						 $("#divstextParrafo2").hide("slow"); 
						 $("#divAddParrafo2").show("slow");
						
					}
					else if(self.find('img').attr('id') == 'addParrafo3'){
						
						 form.sflagParrafo3.value="true";
						 
						 $("#divAddParrafo3").hide("slow");
						 $("#divRemoveParrafo2").hide("slow");
						 
						 $("#divstextParrafo3").show("slow");
					}
					
					else if(self.find('img').attr('id') == 'removeParrafo3'){
						
						 form.sflagParrafo3.value="false";
						 
						 $("#divstextParrafo3").hide("slow");
						 $("#divstextParrafo2").show("slow");
						 
						 
						 $("#divAddParrafo3").show("slow");
						 $("#divRemoveParrafo2").show("slow");
					}
					
					
					
			});
		}
		
		
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
			
				 form.operacionValidate.value="validateCambiarConfiguracionMarca";
				 form.action="cambiarConfiguracionMarcanotificacionEnrolamiento";
				 form.submit();	
				 
			 }
		 } 
		 
	</script>
	 
    
</head>

<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Notificaci&oacute;n de Enrolamiento</h4>
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

		<form  id="form-NotificacionEnrolamiento" class="form-horizontal" method="post" action="registrarNotificacionEnrolamiento" enctype="multipart/form-data">
					<s:hidden  name="operacionValidate" value="validateNotificacionEnrolamiento"/>
					
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
				<div class="panel-body">
					<ul class="nav nav-tabs">
										    <li class="active"><a data-toggle="tab" href="#sms">SMS</a></li>
   											<li><a data-toggle="tab" href="#email">Correo Electr&oacute;nico</a></li>
					</ul>
					
					<div class="tab-content">
						
						<!-- CONFIGURACION SMS -->
						
						<div id="sms" class="tab-pane fade in active">
								<div class="form-group" style="margin-bottom: 35px;">
								 	<div class="col-xs-12"></div>
								</div>
								<div class="panel panel-default">
									
									<div class="panel-heading">Configuraci&oacute;n</div>
									
									<div class="panel-body">
										
										<div class="row">
												<div class="col-lg-6">
														<div style="margin-top: 5%;margin-bottom: 10%;"> 
															<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-3">
																						<label for="inputText">Mensaje de Enrolamiento</label>
																					</div>
																					<div class="col-lg-9">
																						<s:textarea cssClass="form-control1" name="notificacionEnrolBean.stextConfiguracionSMS" id="stextConfiguracionSMS" onkeyup="textCounter(this,frm_stextConfiguracionSMS ,150)"/>
																						<p class="help-block">
																							M&aacute;ximo 150 caracteres    
																							
																							<input name="frm_stextConfiguracionSMS" size="4" id="frm_stextConfiguracionSMS"
																								maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																								readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																				</div>
																				
															 </div>	
															 
															 <div class="col-lg-12">
																			<div class="well">
						
																				<h4>Consideraciones:</h4>
																				<p>Se indica alguna palabras RESERVADAS que se podria incluir en el p&aacute;rrafo superior</p>
																				<p>
																					<b>ULTIMOS_4_DIGITOS_TARJETA</b> : Esta palabra ser&aacute; reemplazada por los ultimos 4 d&iacute;gitos de la Tarjeta del usuario
																				</p>
																				<p>
																					<b>TARJETA_CREDITO</b> : Esta palabra ser&aacute; reemplazada por la Tarjeta del usuario
																				</p>
																				
																			</div>

															</div>
																
														</div>			
												</div>
												<div class="col-lg-6">
														<div  class="contentSMS">
																<div class="timeline-comment-wrapper js-comment-container">
																	<a href="#">
																		<img alt="" class="timeline-comment-avatar" src="<%=request.getContextPath()%>/resources/images/usuarioSMS.png" height="48" width="48">
																	</a>
																	
																	<div class="contentTextSMS timeline-comment">
																		<div class="comment-content">
																			<div class="edit-comment-hide">
																			
																			
																				<div class="comment-body markdown-body markdown-format js-comment-body"> 
																					<s:hidden name="notificacionEnrolBean.sflagTieneConfiguracionSMS"></s:hidden>
																					
																					<s:if test="%{notificacionEnrolBean.sflagTieneConfiguracionSMS == 'true' }">
																							<s:hidden name="notificacionEnrolBean.stextViewConfiguracionSMS"></s:hidden>
																							${notificacionEnrolBean.stextViewConfiguracionSMS}
																					</s:if>
																					<s:else>
																						<s:label>No tiene ninguna configuraci&oacute;n para el envio de SMS</s:label>
																					</s:else>
																				</div>
																				
																			</div>
																		</div>
																	 </div>
																	
																</div>	
														</div>
												
												
												</div>
												
										</div>
									
										
									</div>
								</div>
						
						</div>
						
						<!-- CONFIGURACION EMAIL  notificacionEnrolBean -->
								
						<div id="email" class="tab-pane fade">
								<div class="form-group" style="margin-bottom: 35px;">
								 	<div class="col-xs-12"></div>
								</div>
								
								<div class="panel panel-default">
									<s:hidden name="notificacionEnrolBean.sflagTieneConfiguracionEmail"></s:hidden>
									<s:if test="%{notificacionEnrolBean.sflagTieneConfiguracionEmail == 'true' }">
											<div class="panel-heading">
													<div id="imageSMS">
													 	 <img alt="" src="<%=request.getContextPath()%>/resources/images/usuarioEmail.png" height="80" width="80">
													</div>
													<div id="asunto">
														<div class="fechaEmail">jueves 26/11/2015 03:15 p.m</div>
														<div class="remitente"><s:label name="notificacionEnrolBean.stextRemitenteEmail"></s:label> </div>
														<div><s:label name="notificacionEnrolBean.stextViewAsunto"></s:label> </div>
														 
														 <s:hidden name="notificacionEnrolBean.stextRemitenteEmail"></s:hidden>	
														 <s:hidden name="notificacionEnrolBean.stextViewAsunto"></s:hidden>	
														 
													</div>
																
											</div>
											
											<div class="panel-body" style="padding-bottom: 80px;">
													<img alt="Logo"	src="logoIssuer?brandSelected=<s:property value="brandBean.intIdBrand"/>" width="140" height="47" />
													
													${notificacionEnrolBean.stextConfiguracionEmail}
													<s:hidden name="notificacionEnrolBean.stextConfiguracionEmail"></s:hidden>	
											</div>
											
											
									</s:if>
									<s:else>
			 		    					<div class="comment-body markdown-body markdown-format js-comment-body" style="text-align: center; padding: 50px;">
			 		    						<s:label>No tiene ninguna configuraci&oacute;n para el envio de CORREO ELECTR&Oacute;NICO</s:label>
			 		    					</div>
																
									</s:else>
									
								</div>
								
								<div class="panel panel-default">
										<div class="panel-heading2">Configuraci&oacute;n</div>
										<div class="panel-body">
											<div class="row">
												 	<div class="col-lg-8">
												 	
												 		<div class="col-lg-12">
															<div class="form-group">
																<div class="col-lg-2">
																	<label for="inputText">Asunto</label>
																</div>
																<div class="col-lg-10">
																	<s:textfield cssClass="form-control" name="notificacionEnrolBean.stextAsunto" id="stextAsunto" onkeyup="textCounter(this,frm_stextAsunto,50)" />
																	<p class="help-block">
																		M&aacute;ximo 50 caracteres <input name="frm_stextAsunto" size="4" id="frm_stextAsunto" maxlength="3"
																			style="border: 0px; color: #737373; display: block; margin-bottom: 10px; float: right;"
																			readonly="readonly" tabindex="-1">
																	</p>
					
																</div>
															</div>
														</div>
												
														<div class="col-lg-12">
															<div class="form-group">
																<div class="col-lg-2">
																	<label> <s:checkbox name="notificacionEnrolBean.sflagSaludoInicial" id="sflagSaludoInicial" onclick="MostrarOcultarDivForChecked(this,'divstextSaludosInicial')" />
																		Mostrar Saludo inicial
																	</label>
																</div>
																<div class="col-lg-10" id="divstextSaludosInicial">
																	<s:textfield cssClass="form-control" name="notificacionEnrolBean.stextSaludoInicial" id="stextSaludoInicial" onkeyup="textCounter(this,frm_stextSaludoInicial,50)" />
																	<p class="help-block">
																		M&aacute;ximo 50 caracteres <input name="frm_stextSaludoInicial" size="4" id="frm_stextSaludoInicial" maxlength="3"
																			style="border: 0px; color: #737373; display: block; margin-bottom: 10px; float: right;" readonly="readonly" tabindex="-1">
																	</p>
																</div>
															</div>
														</div>
											
														<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label for="inputText">Configuraci&oacute;n Texto</label>
																					</div>
																					<div class="col-lg-9">
																						
																						<s:textarea cssClass="form-control1" name="notificacionEnrolBean.stextParrafo1" id="stextParrafo1" onkeyup="textCounter(this,frm_stextParrafo1,250)"/>
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo1" size="4" id="frm_stextParrafo1"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>				
																					</div>
																					<div class="col-lg-1">
																						<div class="option" id="divAddParrafo2" style="width: 0px;">
																								  <img alt="Agregar parrafo" id="addParrafo2" src="<%=request.getContextPath()%>/resources/images/add.jpg" width="28px" height="28px">
																						</div>
																					</div>
																						
																				</div>
														</div>
	
														<div class="col-lg-12"  id="divstextParrafo2">
																<div class="form-group">
																	<div class="col-lg-2"></div>
																	<div class="col-lg-9">
																		<s:hidden name="notificacionEnrolBean.sflagParrafo2" id="sflagParrafo2"></s:hidden>
																		<s:textarea cssClass="form-control1" name="notificacionEnrolBean.stextParrafo2" id="stextParrafo2" onkeyup="textCounter(this,frm_stextParrafo2 ,250)"/>
																		<p class="help-block">
																				M&aacute;ximo 250 caracteres    
																				
																				<input name="frm_stextParrafo2" size="4" id="frm_stextParrafo2"
																					maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																					readonly="readonly" tabindex="-1"> 
																		</p>				
																	</div>
																	
																	<div class="col-lg-1">
																		<div class="option" id="divAddParrafo3" style="margin-bottom: 10px;">
																				  <img alt="Agregar parrafo" id="addParrafo3" src="<%=request.getContextPath()%>/resources/images/add.jpg" width="28px" height="28px">
																		</div>
																		<div class="option" id="divRemoveParrafo2" style="margin-bottom: 10px;">
																				  <img alt="Agregar parrafo" id="removeParrafo2" src="<%=request.getContextPath()%>/resources/images/remove.jpg" width="28px" height="28px">
																		</div>
																	</div>	
																		
																	
																</div>
														</div>
														
														<div class="col-lg-12"  id="divstextParrafo3">
																<div class="form-group">
																	<div class="col-lg-2"></div>
																	<div class="col-lg-9">
																		<s:hidden name="notificacionEnrolBean.sflagParrafo3" id="sflagParrafo3"></s:hidden>
																		<s:textarea cssClass="form-control1" name="notificacionEnrolBean.stextParrafo3" id="stextParrafo3" onkeyup="textCounter(this,frm_stextParrafo3,250)"/>	
																		<p class="help-block">
																				M&aacute;ximo 250 caracteres    
																				
																				<input name="frm_stextParrafo3" size="4" id="frm_stextParrafo3"
																					maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																					readonly="readonly" tabindex="-1"> 
																		</p>			
																	</div>
																	<div class="col-lg-1">
																		<div class="option" id="divRemoveParrafo3" >
																			  <img alt="Agregar parrafo" id="removeParrafo3" src="<%=request.getContextPath()%>/resources/images/remove.jpg" width="28px" height="28px">
																		</div>	
																	</div>
																	
																	
																	
																</div>
														</div> 
														
														
												 	</div>

													<div class="col-lg-4">
																		<ul class="timeline">
																			<li class="timeline-inverted">
																					<div class="timeline-panel">
													                                        <div class="timeline-heading">
													                                          <h4 class="timeline-title">CONSIDERACIONES </h4> 
													                                        </div>
													                                        <div class="timeline-body">
													                                            <p>Se indica alguna palabras RESERVADAS que se podria incluir en el p&aacute;rrafo superior</p>
																								<p><b>ULTIMOS_4_DIGITOS_TARJETA</b> : Esta palabra ser&aacute; reemplazada por los ultimos 4 d&iacute;gitos de la Tarjeta del usuario</p>
																								<p><b>TARJETA_CREDITO</b> : Esta palabra ser&aacute; reemplazada por la Tarjeta del usuario</p>
													                                        </div>
											                                    	</div>
																			 </li>
																		</ul>
	 
													</div>	

											</div>
										</div>
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
	
		</form>
	</div>

</body>