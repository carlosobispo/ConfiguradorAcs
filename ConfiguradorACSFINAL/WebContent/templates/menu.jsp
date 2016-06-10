<%@taglib uri="/struts-tags"  prefix="s"%>

<!-- MENU -->
         <div class="navbar-default sidebar" role="navigation">
             <div class="sidebar-nav navbar-collapse">
                 <ul class="nav" id="side-menu">
                     <li class="sidebar-search">
                         <div class="input-group custom-search-form">
                         	<div>
                         		<h5>Bienvenido(a): </h5>	
                         	</div>
                         	<div>
                         		<h5>${sessionScope.ObjetoUsuario.svcName}</h5>
                         	</div>
                         </div>
                         <!-- /input-group -->
                     </li>
                    
                     
                     <li>
                         <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>Administracion<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                         	 
                         	
							<s:if test="#session.ObjetoUsuario.intProfile == #session.ObjetoAccesoBean.tipo_administrador_Alignet">
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_emisores">
	                         	 		<li>
			                                 <a href="irlistarEmisor">Emisores</a>
			                            </li>
                         	 		</s:if>
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_usuarios">
			                            <li>
			                                 <a href="#">Usuarios</a>
			                            </li>                         	 		
                         	 		</s:if>

                             </s:if>

                             
  							<s:if test="#session.ObjetoUsuario.intProfile == #session.ObjetoAccesoBean.tipo_administrador_Emisor">
  
                          	 		<s:if test="#session.ObjetoAccesoBean.enl_configuracionLogo">
	                         	 		<li>
			                                 <a href="irConfigLogo">Configuraci&oacute;n Logo</a>
			                            </li>
                         	 		</s:if>
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_autenticacionClaveIncorrectaExpirada">
	                         	 		<li>
			                                 <a href="irConfigPantallaAutenticacionClaveIncorrectaEpirada">Pantalla autenticacion clave incorrecta/expirada</a>
			                            </li>
                         	 		</s:if>
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_tarjetaBloqueada">
			                            <li>
			                                 <a href="irConfigPantallaTarjetaBloqueada">Pantalla tarjeta bloqueada</a>
			                            </li>                         	 		
                         	 		</s:if>

                         	 		<s:if test="#session.ObjetoAccesoBean.enl_tarjetaNoAfiliadaEnrolada">
			                            <li>
			                                 <a href="irConfigPantallaTarjetaNoAfiliada">Pantalla tarjeta no afiliada/enrolada</a>
			                            </li>                         	 		
                         	 		</s:if>
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_envioClaveDinamica">
			                            <li>
			                                 <a href="irConfigEnvioClaveDinamica">Pantalla envio clave din&aacute;mica</a>
			                            </li>                         	 		
                         	 		</s:if>
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_notificacionEnrolamiento">
			                            <li>
			                                 <a href="irNotificacionEnrolamiento">Notificaci&oacute;n de Enrolamiento</a>
			                            </li>                         	 		
                         	 		</s:if>
                         	 		
                         	 		<s:if test="#session.ObjetoAccesoBean.enl_descargarManual">
			                            <li>
			                                 <a href="irDescargarManual"  style="font-weight: bold;">Descargar MANUAL </a>
			                            </li>                         	 		
                         	 		</s:if>

                             </s:if>                            
                               
                             
                         </ul>
                         <!-- /.nav-second-level -->
                     </li>

                 </ul>
             </div>
         </div>

         
         
         