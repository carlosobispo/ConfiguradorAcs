<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<head>
	<meta http-equiv=Content-Type content=text/html; charset=UTF-8>  
	
	<!-- 
	<script src="< %=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="< %=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
	 -->
	
	<link href="<%=request.getContextPath()%>/resources/css/jquery-confirm.min.css" rel="stylesheet">
	
	
	<link href="<%=request.getContextPath()%>/resources/css/estiloSMS.css" rel="stylesheet">
	 
	
 
	<script type="text/javascript">
		
		$(function(){
			
			$( "#form-EnvioClaveDinmica" ).validate({
				 rules: {
						"envioClaveBean.stexttituloSMS": {
	        	   			required: true ,minlength: 1, maxlength: 50 ,customvalidation: true
	                  	 },
		   				"envioClaveBean.stextlblComercioSMS":{
		   					required: true ,minlength: 1, maxlength: 20 ,customvalidation: true
		   				},
		   				"envioClaveBean.stextlblMontoSMS":{
		   					required: true ,minlength: 1, maxlength: 20 ,customvalidation: true
		   				},
		   				"envioClaveBean.stextlblNumeroIntento":{
		   					required: true ,minlength: 1, maxlength: 20 ,customvalidation: true
		   				},
		   				"envioClaveBean.stextlblClaveDinamica":{
		   					required: true ,minlength: 1, maxlength: 20 ,customvalidation: true
		   				}
		   				
		   				
		   				,
		   				"envioClaveBean.stextRemitente":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidation: true
		   				},
		   				"envioClaveBean.stextAsunto":{
		   					required: true ,minlength: 1, maxlength: 50 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextSaludosInicial":{
		   					required: true ,minlength: 1, maxlength: 50 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextParrafo1":{
		   					required: true ,minlength: 1, maxlength: 250 ,customvalidationParrafo: true
		   				},
		   				"envioClaveBean.stextParrafo2":{
		   					required: true ,minlength: 1, maxlength: 250 ,customvalidationParrafo: true
		   				},
		   				"envioClaveBean.stextlblComercioEmail":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextlblMontoEmail":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextlblClaveDinamicaEmail":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextlblFechaEmail":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextlblHoraEmail":{
		   					required: true ,minlength: 1, maxlength: 40 ,customvalidationConfigEmail: true
		   				},
		   				"envioClaveBean.stextParrafo3":{
		   					required: true ,minlength: 1, maxlength: 250 ,customvalidationParrafo: true
		   				},
		   				"envioClaveBean.stextParrafo4":{
		   					required: true ,minlength: 1, maxlength: 250 ,customvalidationParrafo: true
		   				},
		   				"envioClaveBean.stextParrafo5":{
		   					required: true ,minlength: 1, maxlength: 250 ,customvalidationParrafo: true
		   				}
				
				 },
				 messages: {
					 
					 	"envioClaveBean.stexttituloSMS": {
							minlength: $.format("Al menos {0} caracteres requeridos!"),
							maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
							required: $.format("Favor de ingresar el texto para el Titulo del SMS!")  
                        
		      	   		},
			      	   	"envioClaveBean.stextlblComercioSMS": {
							minlength: $.format("Al menos {0} caracteres requeridos!"),
							maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
							required: $.format("Favor de ingresar el texto para el  Comercio!") 
	                    },
			      	   	"envioClaveBean.stextlblMontoSMS": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el  Monto!") 
	                   },
			      	   	"envioClaveBean.stextlblNumeroIntento": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el  Numero de intento!") 
	                  },
			      	   	"envioClaveBean.stextlblComercioEmail": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Comercio!") 
	                  },
			      	   	"envioClaveBean.stextlblMontoEmail": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Monto!") 
	                  },
			      	   	"envioClaveBean.stextlblClaveDinamica": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para la Clave Dinamica!") 
	                  },
			      	   	"envioClaveBean.stextRemitente": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Remitente") 
	                  },
			      	   	"envioClaveBean.stextAsunto": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Asunto") 
	                  },
			      	   	"envioClaveBean.stextSaludosInicial": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Saludos Inicial") 
	                  },
			      	   	"envioClaveBean.stextParrafo1": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Parrafo Superior") 
	                  },
			      	   	"envioClaveBean.stextParrafo2": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto el parrafo") 
	                  },
			      	   	"envioClaveBean.stextlblClaveDinamicaEmail": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para Clave Dinamica") 
	                  },
			      	   	"envioClaveBean.stextlblFechaEmail": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para la fecha") 
	                  },
			      	   	"envioClaveBean.stextlblHoraEmail": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para la Hora") 
	                  },
			      	   	"envioClaveBean.stextParrafo3": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el Parrafo Inferior") 
	                  },
			      	   	"envioClaveBean.stextParrafo4": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el parrafo") 
	                  },
			      	   	"envioClaveBean.stextParrafo5": {
				      	   	 minlength: $.format("Al menos {0} caracteres requeridos!"),
		                     maxlength: $.format("M&aacute;ximo {0} caracteres permitidos!"),
		                     required: $.format("Favor de ingresar el texto para el parrafo") 
	                  }
				 }
			});
			
			
		      $.validator.addMethod("customvalidation",
		              function(value, element) {
		    	 		//var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		var characterReg = /[áéíóúÁÉÍÓÚÑñ`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		
		    	 		if(value == null ||  value =='' )
		   						return true;			
		   					else{
		   						
		   						return !characterReg.test(value);
		   								
		   					}
		              },
		      "No esta permitido caracteres especiales!"
		      ); 
		      
		      $.validator.addMethod("customvalidationConfigEmail",
		              function(value, element) {
		    	 		//var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		
		    	 		if(value == null ||  value =='' )
		   						return true;			
		   					else{
		   						
		   						return !characterReg.test(value);
		   								
		   					}
		              },
		      "No esta permitido caracteres especiales!"
		      ); 
		      
		      $.validator.addMethod("customvalidationParrafo",
		              function(value, element) {
		    	 		//var characterReg = /[`~#$%^&*()\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		var characterReg = /[`~#$%^&*\n°¬|+\=?'"<>\{\}\[\]\\\/]/gi;
		    	 		
		    	 		if(value == null ||  value =='' )
		   						return true;			
		   					else{
		   						
		   						return !characterReg.test(value);
		   								
		   					}
		              },
		      "No esta permitido caracteres especiales!"
		      );
			
		  	//Configuracion SMS    
			var sflagMostrarIntent =  document.getElementById('sflagmostrarintento');
			 MostrarOcultarDivForChecked(sflagMostrarIntent,'divlblNumeroIntento');
			
			//---- Configuracion para EMAIL
			
			var sflagSaludoinicial =  document.getElementById('sflagSaludoInicial');
			MostrarOcultarDivForChecked(sflagSaludoinicial,'divstextSaludosInicial');
			
			var sflagParrafoSuperior =  document.getElementById('sflagParrafo1');
			MostrarOcultarParrafoSuperior(sflagParrafoSuperior); 
			
			
			var sflagComercioEmail =  document.getElementById('sflaglblComercioEmail');
			MostrarOcultarDivForChecked(sflagComercioEmail,'divstextlblComercioEmail');
			
			var sflagMontoEmail =  document.getElementById('sflagMontoEmail');
			MostrarOcultarDivForChecked(sflagMontoEmail,'divstextlblMontoEmail');
			
			
			var sflagFechaEmail =  document.getElementById('sflagFechaEmail');
			MostrarOcultarDivForChecked(sflagFechaEmail,'divstextlblFechaEmail');
			
			
			
			var sflagHoraEmail =  document.getElementById('sflagHoraEmail');
			MostrarOcultarDivForChecked(sflagHoraEmail,'divstextlblHoraEmail');
			
			 
			var sflagParrafoinferior =  document.getElementById('sflagParrafo3');
			 MostrarOcultarParrafoInferior(sflagParrafoinferior); 
			
			conteoinicial();
			MostrarOcultarParrafo();
			
			
			
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
		
		
		function conteoinicial(){
			
			
			//SMS
			var stextTituloSMS=   document.getElementById('stexttituloSMS');
			var slenTituloSMS =  document.getElementById('frm_stexttituloSMS');
			
			var stextComercio=   document.getElementById('stextlblComercioSMS');
			var slenlblComercio =  document.getElementById('frm_stextlblComercioSMS');
			
			var stextmonto=   document.getElementById('stextlblMontoSMS');
			var slenlblmonto =  document.getElementById('frm_stextlblMontoSMS');
			
			var stextIntento=   document.getElementById('stextlblNumeroIntento');
			var slenlblintento =  document.getElementById('frm_stextlblNumeroIntento');
			

			var stextClaveDinamica=   document.getElementById('stextlblClaveDinamica');
			var slenlblClaveDinamica =  document.getElementById('frm_stextlblClaveDinamica');
			
			
			textCounter(stextTituloSMS, slenTituloSMS ,50);
			textCounter(stextComercio, slenlblComercio ,20);
			textCounter(stextmonto, slenlblmonto ,20);
			textCounter(stextIntento, slenlblintento ,20);
			textCounter(stextClaveDinamica, slenlblClaveDinamica ,20);
			
			
			//EMAIL
			
			var stextRemitenteEmail= document.getElementById('stextRemitente');
			var slenRemitenteEmail= document.getElementById('frm_stextRemitente');
			
			var stextAsuntoEmail= document.getElementById('stextAsunto');
			var slenAsuntoEmail= document.getElementById('frm_stextAsunto');
			
			var stextSaludoInicialEmail= document.getElementById('stextSaludosInicial');
			var slenSaludoInicialEmail= document.getElementById('frm_stextSaludosInicial');
			
			var stextParrafo1Email= document.getElementById('stextParrafo1');
			var slenParrafo1Email= document.getElementById('frm_stextParrafo1');
			
			var stextParrafo2Email= document.getElementById('stextParrafo2');
			var slenParrafo2Email= document.getElementById('frm_stextParrafo2');
			
			var stextlblComercioEmail= document.getElementById('stextlblComercioEmail');
			var slenlblComercioEmail= document.getElementById('frm_stextlblComercioEmail');
			
			var stextlblMontoEmail= document.getElementById('stextlblMontoEmail');
			var slenlblMontoEmail= document.getElementById('frm_stextlblMontoEmail');
			
			var stextlblClaveDinamicaEmail= document.getElementById('stextlblClaveDinamicaEmail');
			var slenlblClaveDinamicaEmail= document.getElementById('frm_stextlblClaveDinamicaEmail');
			
			var stextlblFechaEmail= document.getElementById('stextlblFechaEmail');
			var slenlblFechaEmail= document.getElementById('frm_stextlblFechaEmail');
			
			var stextlblHoraEmail= document.getElementById('stextlblHoraEmail');
			var slenlblHoraEmail= document.getElementById('frm_stextlblHoraEmail');
			
			var stextParrafo3Email= document.getElementById('stextParrafo3');
			var slenParrafo3Email= document.getElementById('frm_stextParrafo3');
			
			var stextParrafo4Email= document.getElementById('stextParrafo4');
			var slenParrafo4Email= document.getElementById('frm_stextParrafo4');
			
			var stextParrafo5Email= document.getElementById('stextParrafo5');
			var slenParrafo5Email= document.getElementById('frm_stextParrafo5');
			
			
			textCounter(stextRemitenteEmail, slenRemitenteEmail ,40);
			textCounter(stextAsuntoEmail, slenAsuntoEmail ,50);
			textCounter(stextSaludoInicialEmail, slenSaludoInicialEmail ,50);
			textCounter(stextParrafo1Email, slenParrafo1Email ,250);
			textCounter(stextParrafo2Email, slenParrafo2Email ,250);
			textCounter(stextlblComercioEmail, slenlblComercioEmail ,50);
			textCounter(stextlblMontoEmail, slenlblMontoEmail ,40);
			textCounter(stextlblClaveDinamicaEmail, slenlblClaveDinamicaEmail ,40);
			textCounter(stextlblFechaEmail, slenlblFechaEmail ,40);
			textCounter(stextlblHoraEmail, slenlblHoraEmail ,40);
			textCounter(stextParrafo3Email, slenParrafo3Email ,250);
			textCounter(stextParrafo4Email, slenParrafo4Email ,250);
			textCounter(stextParrafo5Email, slenParrafo5Email ,250);
			
			
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
		
	
		
		
		function MostrarOcultarDivForChecked(objetoCheked, divMostrarOcultar){
			  
			  var objState = objetoCheked.checked ? true : false;
			  
			  if(objState){
				  $("#"+divMostrarOcultar).show("slow");
			  }else{
				  $("#"+divMostrarOcultar).hide("slow");
			  }
		}
		
		function MostrarOcultarParrafoSuperior(objetoCheked){
			var form = document.forms[0];
			var flagParrafo2 = form.sflagParrafo2.value;
			
			var objState = objetoCheked.checked ? true : false;
			
			
			if(objState){
				  	
				  $("#divstextParrafo1").show("slow");
				  
				  if(flagParrafo2=='true'){
					  $("#divAddParrafo2").hide("slow");
					  $("#divstextParrafo2").show("slow");
				  }
				  else{
					  $("#divAddParrafo2").show("slow");
					  $("#divstextParrafo2").hide("slow");  
				  }
				  
			  }else{
				  $("#divstextParrafo1").hide("slow");
				  $("#divAddParrafo2").hide("slow");
				  $("#divstextParrafo2").hide("slow");
				  
			  }
		}
		
		
		function MostrarOcultarParrafoInferior(objetoCheked){
			var form = document.forms[0];
			var flagParrafo4 = form.sflagParrafo4.value;
			var flagParrafo5 = form.sflagParrafo5.value;
			var objState = objetoCheked.checked ? true : false;
			
			if(objState){
				  $("#divstextParrafo3").show("slow");
				  
				  if(flagParrafo4=='true'){
					  $("#divAddParrafo4").hide("slow");
					  $("#divstextParrafo4").show("slow"); 
						 
					  if(flagParrafo5=='true'){
							 $("#divRemoveParrafo4").hide("slow");
							 $("#divAddParrafo5").hide("slow");
							 
							 $("#divstextParrafo5").show("slow"); 
							 $("#divRemoveParrafo5").show("slow");
						  
					  }else{
						  $("#divRemoveParrafo4").show("slow");
						  $("#divAddParrafo5").show("slow");
						  $("#divstextParrafo5").hide("slow"); 
					  }
					  
				  }else{

					  $("#divAddParrafo4").show("slow");
					  $("#divstextParrafo4").hide("slow");
					  $("#divstextParrafo5").hide("slow");  
				  }
				  
				  
			  }else{
				  $("#divstextParrafo3").hide("slow");
				  $("#divAddParrafo4").hide("slow");
				  $("#divstextParrafo4").hide("slow");
				  $("#divstextParrafo5").hide("slow");
			  }
				
		//	form.sflagParrafo4.value="false";
		//	form.sflagParrafo5.value="false";
			
		}
		
		function MostrarOcultarParrafo(){
			
			var form = document.forms[0];
			 
			var option = $('#form-EnvioClaveDinmica .option');
			 
			 option.on('click', function(){ 
				 var self = $(this);
				 if(self.find('img').attr('id') == 'addParrafo2'){
					
					 form.sflagParrafo2.value="true";
					 
					 $("#divAddParrafo2").hide("slow");
					 $("#divstextParrafo2").show("slow");
				 } 
				 else if(self.find('img').attr('id') == 'removeParrafo2'){
					 
					 form.sflagParrafo2.value="false";
					 
					 $("#divAddParrafo2").show("slow");
					 $("#divstextParrafo2").hide("slow");
				 }
				 else  if(self.find('img').attr('id') == 'addParrafo4'){

					 form.sflagParrafo4.value="true";
					 
					 $("#divAddParrafo4").hide("slow");
					 $("#divstextParrafo4").show("slow"); 
					 
					 $("#divRemoveParrafo4").show("slow");
					 $("#divAddParrafo5").show("slow");
					 
				 }
				 else if(self.find('img').attr('id') == 'removeParrafo4'){
					 
					 form.sflagParrafo4.value="false";
					 
					 $("#divAddParrafo4").show("slow");
					 $("#divstextParrafo4").hide("slow");
					 
					 //$("#divRemoveParrafo4").show("slow");
					 //$("#divAddParrafo5").show("slow");
				 }
				 else  if(self.find('img').attr('id') == 'addParrafo5'){
					 
					 form.sflagParrafo5.value="true";
					 
					 
					 $("#divRemoveParrafo4").hide("slow");
					 $("#divAddParrafo5").hide("slow");
					 
					 $("#divstextParrafo5").show("slow"); 
					 $("#divRemoveParrafo5").show("slow");
				 }
				 else if(self.find('img').attr('id') == 'removeParrafo5'){
					 
					 form.sflagParrafo5.value="false";
					 
					 $("#divRemoveParrafo4").show("slow");
					 $("#divAddParrafo5").show("slow");
					 
					 $("#divstextParrafo5").hide("slow"); 
					 $("#divRemoveParrafo5").hide("slow");
				 }
				 
		   	 });
		}
		
		  
		function recargarMarca(){
				 
				 var form = document.forms[0];

				 if(cargarLogoMarcaSeleccionada()){
				
					 form.operacionValidate.value="validateCambiarConfiguracionMarca";
					 form.action="cambiarConfiguracionMarcaEnvioClave";
					 form.submit();	
					 
				 }
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
		 
		 
		 function vistaReset(){
			 var form = document.forms[0];
			 
			 if(cargarLogoMarcaSeleccionada()){
				 form.action="vistaResetEnvioClaveDinamica";
				 form.operacionValidate.value="validateCambiarConfiguracionMarca";
				 form.submit();	 
			 }
		 }
		 
		 
	
	</script>
	

		
</head>


<body>
	
	<div class="col-lg-12">
		<h4 class="page-header">  Configuraci&oacute;n Envio Clave Din&aacute;mica</h4>
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

			<form  id="form-EnvioClaveDinmica" class="form-horizontal" method="post" action="registrarConfiguracionEnvioClave">
					<s:hidden  name="operacionValidate" value="validateConfiguracionEnvioClave"/>
		
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
						<div class="panel-body">
							<ul class="nav nav-tabs">
										    <li class="active"><a data-toggle="tab" href="#sms">SMS</a></li>
   											<li><a data-toggle="tab" href="#email">Correo Electr&oacute;nico</a></li>
							</ul>
							<div class="tab-content">
							
								<!-- CONFIGURACION SMS -->
							
								<div id="sms" class="tab-pane fade in active">
								 		    	<div class="form-group" style="margin-bottom: 35px;">
								 		    		<div class="col-xs-12">
								 		    		</div>
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
																						<label for="inputText">Titulo SMS</label>
																					</div>
																					<div class="col-lg-9">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stexttituloSMS" id="stexttituloSMS" onkeyup="textCounter(this,frm_stexttituloSMS,50)"/>
																						<p class="help-block">
																								M&aacute;ximo 50 caracteres    
																								
																								<input name="frm_stexttituloSMS" size="4" id="frm_stexttituloSMS"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																						
																					</div>	
																				</div>
																				
																			</div>	
																			
																			<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-3">
																						<label for="inputText">Texto de "Comercio"</label>
																					</div>
																					<div class="col-lg-9">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblComercioSMS" id="stextlblComercioSMS" onkeyup="textCounter(this,frm_stextlblComercioSMS,20)"/>
																						<p class="help-block">
																								M&aacute;ximo 20 caracteres    
																								
																								<input name="frm_stextlblComercioSMS" size="4" id="frm_stextlblComercioSMS"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																				</div>
																				
																			</div>	
																				
																			<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-3">
																						<label for="inputText">Texto de "Monto"</label>
																					</div>
																					<div class="col-lg-9">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblMontoSMS" id="stextlblMontoSMS" onkeyup="textCounter(this,frm_stextlblMontoSMS,20)"/>
																						<p class="help-block">
																								M&aacute;ximo 20 caracteres    
																								
																								<input name="frm_stextlblMontoSMS" size="4" id="frm_stextlblMontoSMS"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																						
																					
																				</div>
																				
																			</div>
																			
																			<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-3">
																						<div class="checkbox">
																			     			<label>
																			     				<s:checkbox  name="envioClaveBean.sflagmostrarintento"   id="sflagmostrarintento"  onclick="MostrarOcultarDivForChecked(this,'divlblNumeroIntento');"/>
																			     				 Mostrar N&uacute;mero de intentos
																			     			</label>
																			     		</div>
																					
																					</div>
																					<div class="col-lg-9" id="divlblNumeroIntento">
																						<label>Texto de "Intento"</label>	
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblNumeroIntento" id="stextlblNumeroIntento"  onkeyup="textCounter(this,frm_stextlblNumeroIntento,20)"/>
																						<p class="help-block">
																								M&aacute;ximo 20 caracteres    
																								
																								<input name="frm_stextlblNumeroIntento" size="4" id="frm_stextlblNumeroIntento"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>				
																					</div>	
																				</div>
																			</div> 
																																			
																			<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-3">
																						<label for="inputText">Texto de "Clave Din&aacute;mica"</label>
																					</div>
																					<div class="col-lg-9">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblClaveDinamica" id="stextlblClaveDinamica"  onkeyup="textCounter(this,frm_stextlblClaveDinamica,20)"/>
																						<p class="help-block">
																								M&aacute;ximo 20 caracteres    
																								
																								<input name="frm_stextlblClaveDinamica" size="4" id="frm_stextlblClaveDinamica"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
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
																													<s:hidden name="envioClaveBean.sflagTieneConfiguracionSMS"></s:hidden>
																													
																													<s:if test="%{envioClaveBean.sflagTieneConfiguracionSMS == 'true' }">
																															<s:hidden name="envioClaveBean.stextConfiguracionSMS"></s:hidden>
																															${envioClaveBean.stextConfiguracionSMS}
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
							
								
								
								
								
								<!-- CONFIGURACION EMAIL -->
								
								 <div id="email" class="tab-pane fade">
									 		<div class="form-group" style="margin-bottom: 35px;">
								 		    		<div class="col-xs-12">
								 		    		</div>
								 		    </div>
								 		    <div class="panel panel-default">
											 		    <s:hidden name="envioClaveBean.sflagTieneConfiguracionEmail"></s:hidden>	
								 		    			<s:if test="%{envioClaveBean.sflagTieneConfiguracionEmail == 'true' }">
								 		    			
																<div class="panel-heading">
																	<div id="imageSMS">
																	 	 <img alt="" src="<%=request.getContextPath()%>/resources/images/usuarioEmail.png" height="80" width="80">
																	</div>
																	<div id="asunto">
																		<div class="fechaEmail">jueves 26/11/2015 03:15 p.m</div>
																		<div class="remitente"><s:label name="envioClaveBean.stextViewRemitente"></s:label> </div>
																		<div><s:label name="envioClaveBean.stextViewAsunto"></s:label> </div>
																		
																		 <s:hidden name="envioClaveBean.stextViewRemitente"></s:hidden>	
																		 <s:hidden name="envioClaveBean.stextViewAsunto"></s:hidden>	
																		 
																	</div>
																
																</div>		
																									
																<div class="panel-body" style="padding-bottom: 80px;">
																	<img alt="Logo"	src="logoIssuer?brandSelected=<s:property value="brandBean.intIdBrand"/>" width="140" height="47" />
																	
																	${envioClaveBean.stextConfiguracionEmail}
																	<s:hidden name="envioClaveBean.stextConfiguracionEmail"></s:hidden>	
																</div>		
																
								 		    			</s:if>
								 		    			<s:else>
								 		    					<div class="comment-body markdown-body markdown-format js-comment-body" style="text-align: center; padding: 50px;">
								 		    						<s:label>No tiene ninguna configuraci&oacute;n para el envio de SMS</s:label>
								 		    					</div>
																
														</s:else>
				
																								
											</div>
								 		    	
								 		    
								 		    
								 		    	
								 		    	
								 		   <div class="panel panel-default">
														<div class="panel-heading2">Configuraci&oacute;n</div>												
														<div class="panel-body">
															<div class="row">
																<div class="col-lg-8">	

																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label for="inputText">Remitente</label>
																					</div>
																					<div class="col-lg-10">
																						<label>no-reply@</label> 
																						<s:textfield cssClass="form-control3" name="envioClaveBean.stextRemitente" id="stextRemitente" onkeyup="textCounter(this,frm_stextRemitente,40)"/>
																						<p class="help-block">
																								M&aacute;ximo 40 caracteres    
																								
																								<input name="frm_stextRemitente" size="4" id="frm_stextRemitente"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																						
																					</div>	
																				</div>
																		
																				
																			</div> 	
																			
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label for="inputText">Asunto</label>
																					</div>
																					<div class="col-lg-10">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextAsunto" id="stextAsunto" onkeyup="textCounter(this,frm_stextAsunto,50)"/>
																						<p class="help-block">
																								M&aacute;ximo 50 caracteres    
																								
																								<input name="frm_stextAsunto" size="4" id="frm_stextAsunto"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>														
																					
																					</div>	
																				</div>
																			</div>
																			 
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label>
																						<s:checkbox  name="envioClaveBean.sflagSaludoInicial"  id="sflagSaludoInicial"   onclick="MostrarOcultarDivForChecked(this,'divstextSaludosInicial')"/>
							     				 												Mostrar Saludo inicial
																						</label>
																					</div>
																					<div class="col-lg-10" id="divstextSaludosInicial">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextSaludosInicial" id="stextSaludosInicial" onkeyup="textCounter(this,frm_stextSaludosInicial,50)"/>
																						<p class="help-block">
																								M&aacute;ximo 50 caracteres    
																								
																								<input name="frm_stextSaludosInicial" size="4" id="frm_stextSaludosInicial"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																				</div>
																		</div> 
																			
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<div class="checkbox">
																			     			<label>
																			     				<s:checkbox  name="envioClaveBean.sflagParrafo1"   id="sflagParrafo1" onclick="MostrarOcultarParrafoSuperior(this)"/>
																			     				 Mostrar Parrafo superior
																			     			</label>
																			     		</div>
																					
																					</div>
																					
																					<div class="col-lg-9" id="divstextParrafo1">
																						<s:textarea cssClass="form-control1" name="envioClaveBean.stextParrafo1" id="stextParrafo1" onkeyup="textCounter(this,frm_stextParrafo1,250)"/>	
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo1" size="4" id="frm_stextParrafo1"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>			
																					</div>
																					<div class="col-lg-1">
																						<div class="option" id="divAddParrafo2">
																								  <img alt="Agregar parrafo" id="addParrafo2" src="<%=request.getContextPath()%>/resources/images/add.jpg" width="28px" height="28px">
																						</div>
																						
																					</div>
																					
																				</div>
																		 </div> 
																			
																		<div class="col-lg-12"  id="divstextParrafo2">
																				<div class="form-group">
																					<div class="col-lg-2">
																					</div>
																					<div class="col-lg-9">
																						
																						<s:hidden name="envioClaveBean.sflagParrafo2" id="sflagParrafo2"></s:hidden>
																						<s:textarea cssClass="form-control1" name="envioClaveBean.stextParrafo2" id="stextParrafo2" onkeyup="textCounter(this,frm_stextParrafo2 ,250)"/>
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo2" size="4" id="frm_stextParrafo2"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>				
																					</div>	
																					<div class="col-lg-1">
																							<div class="option" id="divRemoveParrafo2" style="width: 0px;">
																									  <img alt="Quitar parrafo" id="removeParrafo2" src="<%=request.getContextPath()%>/resources/images/remove.jpg" width="28px" height="28px">
																							</div>
																					</div>
																					
																					
																				</div>
																		</div>
																			
																		
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label>
																						<s:checkbox  name="envioClaveBean.sflaglblComercioEmail"  id="sflaglblComercioEmail"   onclick="MostrarOcultarDivForChecked(this,'divstextlblComercioEmail')"/>
							     				 												Mostrar Nombre Comercio
																						</label>
																					</div>
																					<div class="col-lg-10" id="divstextlblComercioEmail">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblComercioEmail" id="stextlblComercioEmail" onkeyup="textCounter(this,frm_stextlblComercioEmail,50)"/>
																						<p class="help-block">
																								M&aacute;ximo 50 caracteres    
																								
																								<input name="frm_stextlblComercioEmail" size="4" id="frm_stextlblComercioEmail"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																				</div>
																		</div> 
																		
																		
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label>
																						<s:checkbox  name="envioClaveBean.sflagMontoEmail"  id="sflagMontoEmail" onclick="MostrarOcultarDivForChecked(this,'divstextlblMontoEmail')"/>
							     				 											Mostrar Monto
																						</label>
																					</div>
																					<div class="col-lg-10" id="divstextlblMontoEmail">
																						<div class="col-lg-6">
																							<label>Texto de "Monto"</label>
																							<s:textfield cssClass="form-control" name="envioClaveBean.stextlblMontoEmail" id="stextlblMontoEmail"  onkeyup="textCounter(this,frm_stextlblMontoEmail ,40)"/>
																							<p class="help-block">
																								M&aacute;ximo 40 caracteres    
																								
																								<input name="frm_stextlblMontoEmail" size="4" id="frm_stextlblMontoEmail"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																							</p>
																						</div>
																						<div class="col-lg-6">
																							<s:select label="Formato" cssClass="form-control"
																								list="#{'1':'USD 25.00', '2':'25.00 USD', '3':'25.00'}"  name="envioClaveBean.stextFormatoMontoEmail"
																								id="stextFormatoMontoEmail" />
																						</div>
																						
																					</div>	
																				</div>
																		</div> 
																		
																			 																																	
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label for="inputText">Texto "Clave Din&aacute;mica"</label>
																					</div>
																					<div class="col-lg-10">
																						<s:textfield cssClass="form-control" name="envioClaveBean.stextlblClaveDinamicaEmail" id="stextlblClaveDinamicaEmail" onkeyup="textCounter(this,frm_stextlblClaveDinamicaEmail ,40)"/>
																						<p class="help-block">
																								M&aacute;ximo 40 caracteres    
																								
																								<input name="frm_stextlblClaveDinamicaEmail" size="4" id="frm_stextlblClaveDinamicaEmail"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>
																					</div>	
																				</div>
																		</div>
																		
																																			
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label>
																						<s:checkbox  name="envioClaveBean.sflagFechaEmail"  id="sflagFechaEmail" onclick="MostrarOcultarDivForChecked(this,'divstextlblFechaEmail')"/>
							     				 											Mostrar Fecha
																						</label>
																					</div>
																					<div class="col-lg-10" id="divstextlblFechaEmail">
																						<div class="col-lg-6">
																							<label>Texto de "Fecha"</label>
																							<s:textfield cssClass="form-control" name="envioClaveBean.stextlblFechaEmail" id="stextlblFechaEmail"  onkeyup="textCounter(this,frm_stextlblFechaEmail ,40)"/>
																							<p class="help-block">
																								M&aacute;ximo 40 caracteres    
																								
																								<input name="frm_stextlblFechaEmail" size="4" id="frm_stextlblFechaEmail"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																							</p>
																						</div>
																						<div class="col-lg-6">
																							<s:select label="Formato" cssClass="form-control"
																								list="#{'1':'12/04/2016', '2':'12-ABR-2016'}"  name="envioClaveBean.stextFormatoFechaEmail"
																								id="stextFormatoFechaEmail" />
																						</div>
																						
																					</div>	
																				</div>
																		</div> 
																			
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<label>
																						<s:checkbox  name="envioClaveBean.sflagHoraEmail"  id="sflagHoraEmail" onclick="MostrarOcultarDivForChecked(this,'divstextlblHoraEmail')"/>
							     				 											Mostrar Hora
																						</label>
																					</div>
																					<div class="col-lg-10" id="divstextlblHoraEmail">
																						<div class="col-lg-6">
																							<label>Texto de "Hora"</label>
																							<s:textfield cssClass="form-control" name="envioClaveBean.stextlblHoraEmail" id="stextlblHoraEmail" onkeyup="textCounter(this,frm_stextlblHoraEmail,40)"/>
																							<p class="help-block">
																								M&aacute;ximo 40 caracteres    
																								
																								<input name="frm_stextlblHoraEmail" size="4" id="frm_stextlblHoraEmail"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																							</p>
																							
																						</div>
																						<div class="col-lg-6">
																							<s:select label="Formato" cssClass="form-control"
																								list="#{'1':'10:21:15', '2':'10:21'}"  name="envioClaveBean.stextFormatoHoraEmail"
																								id="stextFormato" />
																						</div>
																						
																					</div>	
																				</div>
																		</div> 
		
																		<div class="col-lg-12" >
																				<div class="form-group">
																					<div class="col-lg-2">
																						<div class="checkbox">
																			     			<label>
																			     				<s:checkbox  name="envioClaveBean.sflagParrafo3"   id="sflagParrafo3" onclick="MostrarOcultarParrafoInferior(this)"/>
																			     				 Mostrar Parrafo Inferior
																			     			</label>
																			     		</div>
																					
																					</div>
																					<div class="col-lg-9"  id="divstextParrafo3">
																						
																						<s:textarea cssClass="form-control1" name="envioClaveBean.stextParrafo3" id="stextParrafo3" onkeyup="textCounter(this,frm_stextParrafo3 ,250)"/>
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo3" size="4" id="frm_stextParrafo3"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>				
																					</div>
																					<div class="col-lg-1">
																						<div class="option" id="divAddParrafo4" style="width: 0px;">
																								  <img alt="Agregar parrafo" id="addParrafo4" src="<%=request.getContextPath()%>/resources/images/add.jpg" width="28px" height="28px">
																						</div>
																					</div>
																						
																				</div>
																		</div>
																			 
																		<div class="col-lg-12"  id="divstextParrafo4">
																				<div class="form-group">
																					<div class="col-lg-2">
																						
																					</div>
																					<div class="col-lg-9">
																						<s:hidden name="envioClaveBean.sflagParrafo4" id="sflagParrafo4"></s:hidden>
																						<s:textarea cssClass="form-control1" name="envioClaveBean.stextParrafo4" id="stextParrafo4" onkeyup="textCounter(this,frm_stextParrafo4 ,250)"/>
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo4" size="4" id="frm_stextParrafo4"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>				
																					</div>
																					
																					<div class="col-lg-1">
																						<div class="option" id="divAddParrafo5" style="margin-bottom: 10px;">
																								  <img alt="Agregar parrafo" id="addParrafo5" src="<%=request.getContextPath()%>/resources/images/add.jpg" width="28px" height="28px">
																						</div>
																						<div class="option" id="divRemoveParrafo4" style="margin-bottom: 10px;">
																								  <img alt="Agregar parrafo" id="removeParrafo4" src="<%=request.getContextPath()%>/resources/images/remove.jpg" width="28px" height="28px">
																						</div>
																					</div>	
																						
																					
																				</div>
																		</div> 
																		
																		<div class="col-lg-12"  id="divstextParrafo5">
																				<div class="form-group">
																					<div class="col-lg-2">
																						
																					</div>
																					<div class="col-lg-9">
																						<s:hidden name="envioClaveBean.sflagParrafo5" id="sflagParrafo5"></s:hidden>
																						<s:textarea cssClass="form-control1" name="envioClaveBean.stextParrafo5" id="stextParrafo5" onkeyup="textCounter(this,frm_stextParrafo5,250)"/>	
																						<p class="help-block">
																								M&aacute;ximo 250 caracteres    
																								
																								<input name="frm_stextParrafo5" size="4" id="frm_stextParrafo5"
																									maxlength="3" style="border: 0px;color: #737373;display: block; margin-bottom: 10px;float: right;"  
																									readonly="readonly" tabindex="-1"> 
																						</p>			
																					</div>
																					<div class="col-lg-1">
																						<div class="option" id="divRemoveParrafo5" >
																							  <img alt="Agregar parrafo" id="removeParrafo5" src="<%=request.getContextPath()%>/resources/images/remove.jpg" width="28px" height="28px">
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
													                                            <p>Se indica alguna palabras RESERVADAS que deber&aacute;n estar en cualquier p&aacute;rrafo</p>
													                                            <p> <b>TARJETA_CREDITO</b> : Esta palabra ser&aacute; reemplazada por la Tarjeta del usuario a realizar la compra.</p>
													                                            <p> <b>TIEMPO_MINUTO</b> : Esta palabra ser&aacute; reemplazada por el tiempo de expiraci&oacute;n de la clave OTP representado en Minutos</p>
													                                            <p> <b>TIEMPO_SEGUNDO</b> : Esta palabra ser&aacute; reemplazada por el tiempo de expiraci&oacute;n de la clave OTP representado en Segundos</p>
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