<%@page import="com.alignet.configurador.emisor.hibernate.bean.IssuerBean"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@taglib prefix="display" uri="/WEB-INF/displaytag.tld" %>

<HEAD>
	<link href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap.css" rel="stylesheet">

    <!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
	
	<script type="text/JavaScript">
	
	$( function(){
		
		   $("#displayTagDiv").displayTagAjax();

		   $('#intIdIssuer').keyup(function (){
			   this.value = (this.value + '').replace(/[^0-9]/g, '');
			});
		   
		   
			$( "#form-BuscarEmisor" ).validate({
		           rules: {
		        	   		"emisor.intIdIssuer": {
		                           minlength: 1,
		                           maxlength: 3,
		                           number: true
		                  	 },
			   				"emisor.svcName":{
			   					customvalidation: true
			   				}	
		           },
		           messages: {
		        	   		"emisor.intIdIssuer": {
		                           minlength: $.format("Al menos {0} caracteres requeridos!"),
		                           maxlength: $.format("Maximo {0} caracteres permitidos!"),
		                           number: $.format("Favor de ingresar un n&uacute;mero valido!")
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
		   
	});

	function recargarMarca(){
		var form = document.forms[0];
		form.intIdIssuer.value="";
		form.svcName.value="";
		form.submit();
	}
	
	
    function seleccionarEmisor(idEmisor) 
    { 
    	var form = document.forms[0];
		form.emisorSeleccionado.value=idEmisor;
		form.operacionValidate.value="validateIrActualizarEmisor";
		form.action="irActualizarEmisor";
		form.submit();
    } 
	
	</script>

</HEAD>


<body>

<div class="col-lg-12">
	<h4 class="page-header">ADMINISTRACION</h4>
	
	<s:if test="%{getFieldErrors().get('mensajeERROR')!=null}">
		<div class="alert alert-danger">
			<s:property value="getFieldErrors().get('mensajeERROR')"/>
		</div>
	</s:if>
	

	<div class="panel panel-default">
		<div class="panel-heading">Listado de Emisores</div>
		<div class="panel-body">
			
			
			<div class="row">
		
			<form  id="form-BuscarEmisor" class="form-horizontal" action="buscarEmisor" method="post">
					 	<s:hidden name="operacionValidate" value="validateBuscarEmisor" ></s:hidden>
					 	<s:hidden name="emisorSeleccionado"></s:hidden>
					 	
						<div class="col-lg-10">
								<div class="form-group">
									<div class="col-xs-4">
										<label for="inputText">Marca</label>
										<s:select onchange="recargarMarca()" id="intIdBrand" cssClass="form-control" name="brandBean.intIdBrand" list="listBrand" listKey="intIdBrand" listValue="svcName" />
										
									</div>
									<div class="col-xs-4">
										  <label for="inputText">Id Emisor</label>
										  <s:textfield cssClass="form-control" name="emisor.intIdIssuer" id="intIdIssuer"  placeholder="Id Emisor" />
									</div>
									<div class="col-xs-4">
										 <label for="inputText">Nombre Emisor</label>
										 <s:textfield cssClass="form-control" name="emisor.svcName" id="svcName" placeholder="Nombre Emisor" />
									</div>
									
								</div>
					
						</div>
						<div class="col-lg-2">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="panel-bodyButton">
											<input type="submit" class="btn btn-primary" style="margin-top: 23px;float: right;" value="Buscar">
										</div>
									
									</div>
								</div>		
						</div>
						
						<div class="col-lg-12">
								<div class="form-group">
									<div class="col-xs-12">
											<div class="panel-bodyButton">
													

												    	<s:url id="aNuevoRegistroEmisor" action="irNuevoRegistroEmisor">
															<s:param  name="brandSelected"  value="%{brandBean.intIdBrand}"></s:param>
														</s:url>	
														<s:a href="%{aNuevoRegistroEmisor}" cssClass="btn btn-primary"  style="margin-top: 23px; float: right;">Nuevo</s:a>
											</div>
									</div>
								</div>	
						</div>	
			
						
						<div class="col-lg-12">
									
									
											<div class="form-group">
												
											 			<div class="col-xs-12">
											 				 
											 			
											 			
													 			<div class="panel-body">
																		<div class="dataTable_wrapper">
																			<div id="dataTables-example_wrapper">
																				
																					<div class="row">
																						<div class="col-sm-12">
																							
																								<div id="displayTagDiv">
																								<display:table name="sessionScope.alistIssuer" pagesize="20"  uid="row"
																								class="table table-striped table-bordered table-hover dataTable no-footer" 
																								sort="list" requestURI="seleccionarLink"  >
																								
																								<display:column title="ID" sortable="true" >
																										<a href="#" onclick="seleccionarEmisor('${row.intIdIssuer}');">${row.intIdIssuer}</a>
																								</display:column>
																								<display:column property="svcName" title="Emisor" sortable="true"></display:column>
																								<display:column property="svcInitials" title="Nombre Abreviado" sortable="true"></display:column>
																								<display:column property="svcLogoMember" title="Logo" sortable="true"></display:column>
																								<display:column property="intIdstate" title="Estado" sortable="true" decorator="com.alignet.configurador.emisor.util.DisplaytagStateFormat"></display:column>
																								
																								</display:table>
																										
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
									<div class="col-xs-12">
											<div class="panel-bodyButton">
													

												    	<s:url id="aNuevoRegistroEmisor" action="irNuevoRegistroEmisor">
															<s:param  name="brandSelected"  value="%{brandBean.intIdBrand}"></s:param>
														</s:url>	
														<s:a href="%{aNuevoRegistroEmisor}" cssClass="btn btn-primary"  style="margin-top: 23px; float: right;">Nuevo</s:a>
											</div>
									</div>
								</div>	
						</div>	
						
						
				</form>
			</div>	
				
		</div>
	</div>
	
</div>
 
	 

</body>


