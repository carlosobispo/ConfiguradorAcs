package com.alignet.configurador.emisor.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.alignet.configurador.emisor.bean.IssuerBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.bean.IssuerBlockedBean;
import com.alignet.configurador.emisor.servicio.implementacion.BrandImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.IssuerBlockedImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.IssuerImplementation;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class IssuerAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<BrandBean>  listBrand = new ArrayList<BrandBean>();
	private List<com.alignet.configurador.emisor.hibernate.bean.IssuerBean> listIssuer = new ArrayList<com.alignet.configurador.emisor.hibernate.bean.IssuerBean>();
	
	private BrandBean  brandBean;
	
	private com.alignet.configurador.emisor.hibernate.bean.IssuerBean IssuerBeanHibernate;
	
	private IssuerImplementation isssuerImpl ;
	private BrandImplementation  brandInmpl;
	
	private Map<String, Object> sessionMapIssuer;

	private IssuerBean emisor;
	private String operacionValidate = "";
	private String brandSelected;
	private Integer emisorSeleccionado;
	

	public Integer getEmisorSeleccionado() {
		return emisorSeleccionado;
	}

	public void setEmisorSeleccionado(Integer emisorSeleccionado) {
		this.emisorSeleccionado = emisorSeleccionado;
	}

	public void validate(){

		try {
			Utilitario.getLOG_APP().info("<Inicio> Validate");
			
			if(operacionValidate.equals("validateBuscarEmisor")){
				
				isValidoCamposBuscarEmisor();
				
			}else if(operacionValidate.equals("verificarIdEmisor")){
				
				if( isValidoMarcaAndIdEmisor(brandBean.getIntIdBrand(), emisor.getIntIdIssuer()) ) VerificarDisponibilidadIdEmisor(true);
					setBrandSelected(brandBean.getIntIdBrand().toString());

			}else if(operacionValidate.equals("registrarNewEmisor")){

				if( ValidarCamposRegistroActualizacionEmisor() ) VerificarDisponibilidadIdEmisor(false);
				setBrandSelected(brandBean.getIntIdBrand().toString());
			
			}else if(operacionValidate.equals("validateIrActualizarEmisor")){
				
				isValidoMarcaAndIdEmisor(brandBean.getIntIdBrand(), emisor.getIntIdIssuer());
			
			}else if(operacionValidate.equals("validateActualizarEmisor")){
				
				ValidarCamposRegistroActualizacionEmisor();
				
			}
			
			brandInmpl= new BrandImplementation();
			listBrand   = brandInmpl.listBrand();
			
			Utilitario.getLOG_APP().info("<Fin> Validate");
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR ",e);
		}
		
	}
	
	@SkipValidation
	@Action(value = "/irlistarEmisor", results = { 
			@Result (location = "t_listaEmisor", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
			})
	public String listarEmisor(){
	
		try {
			
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : EMISORES");
			
			brandInmpl= new BrandImplementation();
			listBrand   = brandInmpl.listBrand();
			
			Utilitario.getLOG_APP().info(" Total marcas obtenidas "+listBrand.size());
			
			String marca_default = Utilitario.getStringResourceBundle("MARCA_DEFAULT").trim();
			Utilitario.getLOG_APP().info(" Lista de emisores de la marca por defecto : " + marca_default);
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+marca_default).trim()  ) ;
			
			isssuerImpl = new IssuerImplementation( datasource );
			listIssuer  = isssuerImpl.listaIssuer();
			
			sessionMapIssuer.put("alistIssuer", listIssuer);

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - listarEmisor ",e);
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	@SkipValidation
	@Action(value = "/seleccionarLink", results = { @Result (location = "t_listaEmisor", name = "success", type="tiles") })
	public String seleccionarPagination(){
		Utilitario.getLOG_APP().info("METODO: ------ seleccionarPagination ----");
		return SUCCESS;
	}
	
	@Action(value = "/buscarEmisor", results = { 
			@Result (location = "t_listaEmisor", name = "success", type="tiles"),
			@Result (location = "t_listaEmisor", name = "input", type="tiles")
			})	
	public String buscarEmisor(){
		
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ buscarEmisor ----");
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			
			isssuerImpl = new IssuerImplementation( datasource );
			listIssuer = isssuerImpl.listIssuerxIdOrName(emisor.getIntIdIssuer(), emisor.getSvcName());
			
			Utilitario.getLOG_APP().info("Busqueda de Emisores segun los siguientes parametros:");
			Utilitario.getLOG_APP().info("MARCA: "+ brandBean.getIntIdBrand());
			Utilitario.getLOG_APP().info("ID EMISOR: " + emisor.getIntIdIssuer());
			Utilitario.getLOG_APP().info("NOMBRE EMISOR: " + emisor.getSvcName());
			Utilitario.getLOG_APP().info("Total de emisores encontrados : " + listIssuer.size() );
			
			sessionMapIssuer.put("alistIssuer", listIssuer);

			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ buscarEmisor ----");
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - buscarEmisor ",e);
			return INPUT;
		}
		return SUCCESS;
	}

	
	public void isValidoCamposBuscarEmisor() throws Exception{

			String regCaracteresPermitidos="^[A-Za-z\\d_ -]+$";
			
			if(!isValidoMarcaAndIdEmisor(brandBean.getIntIdBrand(), emisor.getIntIdIssuer())){
				
				Utilitario.getLOG_APP().warn("La marca ingresa o Id emisor es incorrecto");
				
			}else if(!Utilitario.isVacioOrNull(emisor.getSvcName())  && !emisor.getSvcName().matches(regCaracteresPermitidos)){
						Utilitario.getLOG_APP().warn("NOMBRE EMISOR invalido");
						addFieldError("mensajeERROR", " Ingrese un Nombre Emisor VALIDO! ");	
			}else{
				Utilitario.getLOG_APP().warn("Todos los parametros son correctos, se procede a realizar la busqueda");
			}
	}
	
	
	public boolean isValidoMarcaAndIdEmisor(Integer idBrand,String idEmisor){
		
		boolean isValido=false;
		String regBrand = "[1-2]";
		String RegNumberEmisor = "([1-9]|[1-9][0-9]|[1-9][0-9][0-9])";
		try {

			if( Utilitario.isVacioOrNull(idBrand)){
				
				Utilitario.getLOG_APP().warn("Marca Null");
				addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}
			else if ( !idBrand.toString().matches(regBrand) ) {
					Utilitario.getLOG_APP().warn("Marca Incorrecta");
					addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}
			else if(!Utilitario.isVacioOrNull(idEmisor) && !idEmisor.matches(RegNumberEmisor)){
						
						Utilitario.getLOG_APP().warn("ID EMISOR invalido");
						addFieldError("mensajeERROR", " Ingrese un Id Emisor VALIDO! ");
			}else{
				 isValido=true;
				Utilitario.getLOG_APP().warn("La marca y el Id Emisor son validos");
			}
			
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - isValidoMarcaAndIdEmisor ",e);
		}
		
		return isValido;
	}
	

	public void VerificarDisponibilidadIdEmisor(boolean mostrarMensajeSUCCESS){
		
		try {
			
			Utilitario.getLOG_APP().info("Marca Seleccionada : " + brandBean.getIntIdBrand());
			Utilitario.getLOG_APP().info("Id Emisor ingresado : " + emisor.getIntIdIssuer());
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			
			Utilitario.getLOG_APP().info("Se incia la Validacion del ID emisor Ingresado");
			isssuerImpl = new IssuerImplementation( datasource );
			boolean existeRegistradoEmisor =  isssuerImpl.existeRegistradoIdEmisor(emisor.getIntIdIssuer());
			
			if(!existeRegistradoEmisor){
				Utilitario.getLOG_APP().info("ID Emisor "+ emisor.getIntIdIssuer() + " Disponible");
				if(mostrarMensajeSUCCESS) addFieldError("mensajeSUCCESS", " ID Emisor "+ emisor.getIntIdIssuer() + " Disponible ");
			}
			else{
				Utilitario.getLOG_APP().info("ID Emisor "+ emisor.getIntIdIssuer() + " no  disponible");
				addFieldError("mensajeERROR", " ID Emisor "+ emisor.getIntIdIssuer() + " no  disponible! "); 
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", " ID Emisor "+ emisor.getIntIdIssuer() + " no  disponible!! "); 
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - VerificarDisponibilidadIdEmisor ",e);
		}
	}
	
	
	public boolean ValidarCamposRegistroActualizacionEmisor() throws Exception{
		
		boolean isvalido = false;
		String regTiempoDesbloqueo =  "([1-9]|[1-9][0-9])";
		String regCaracteresPermitidos="^[A-Za-z\\d_ -]+$";
		String regSoloLetras="^[A-Za-z]+$";
		String regNomPaquete="^[A-Za-z.]+$";
		String regSoloNumero="^([0-9])+$";
		
		
		if(!isValidoMarcaAndIdEmisor(brandBean.getIntIdBrand(), emisor.getIntIdIssuer())){
			
			Utilitario.getLOG_APP().warn("La marca ingresa o Id emisor es incorrecto");
			
		}else if( Utilitario.isVacioOrNull(emisor.getSvcName())  || !emisor.getSvcName().trim().matches(regCaracteresPermitidos) || 
				!Utilitario.isValidoTamnioCampo(emisor.getSvcName(),80 ) ){
			
			Utilitario.getLOG_APP().warn("NOMBRE EMISOR invalido");
			addFieldError("mensajeERROR", " Ingrese un Nombre Emisor VALIDO! ");
		}
		else if( Utilitario.isVacioOrNull(emisor.getSvcInitials() )  || !emisor.getSvcInitials().trim().matches(regSoloLetras) || 
				!Utilitario.isValidoTamnioCampo(emisor.getSvcInitials(), 10 ) ){
			
			Utilitario.getLOG_APP().warn("NOMBRE ABREVIADO EMISOR Invalido");
			addFieldError("mensajeERROR", " Ingrese un nombre Abreviado Emisor Valido! ");
		}		
		else if( Utilitario.isVacioOrNull(emisor.getSclaseimplementacion()) || !emisor.getSclaseimplementacion().trim().matches(regNomPaquete) || 
				!Utilitario.isValidoTamnioCampo(emisor.getSclaseimplementacion(), 100 ) ){
			
			Utilitario.getLOG_APP().warn("CLASE IMPLEMENTANCION Invalido");
			addFieldError("mensajeERROR", " Ingrese una Clase Implementacion Valido! ");
		}
		else if( Utilitario.isVacioOrNull(emisor.getSchAuthenticateby()) || !emisor.getSchAuthenticateby().trim().matches(regSoloLetras)  || 
				!Utilitario.existeValorProperties(emisor.getSchAuthenticateby().trim(), "TIPO.AUTENTICACION.POPUP") ||
				!Utilitario.isValidoTamnioCampo(emisor.getSchAuthenticateby(),1 )  ){
			
			Utilitario.getLOG_APP().warn("TIPO AUTENTICACION POR POPUP Invalido");
			addFieldError("mensajeERROR", " Ingrese un Tipo Autenticacion por popup Valido! Puede ser :  "+Utilitario.getStringResourceBundle("TIPO.AUTENTICACION.POPUP")+" ");
		}
		else if( Utilitario.isVacioOrNull(emisor.getSchEnrolltype()) || !emisor.getSchEnrolltype().trim().matches(regSoloLetras)  || 
				!Utilitario.existeValorProperties(emisor.getSchEnrolltype().trim(), "TIPO.ENROLAMIENTO.POPUP") ||
				!Utilitario.isValidoTamnioCampo(emisor.getSchEnrolltype(),1 )  ){
			
			Utilitario.getLOG_APP().warn("TIPO ENROLAMIENTO POR POPUP Invalido");
			addFieldError("mensajeERROR", " Ingrese un Tipo Enrolamiento por popup Valido! Puede ser : "+Utilitario.getStringResourceBundle("TIPO.ENROLAMIENTO.POPUP")+" ");
		}
		else if( Utilitario.isVacioOrNull(emisor.getSchCharpaddingpin()) || !emisor.getSchCharpaddingpin().trim().matches(regSoloLetras)  || 
				!Utilitario.existeValorProperties(emisor.getSchCharpaddingpin().trim(), "CARACTER.GENERACION.PIN") ||
				!Utilitario.isValidoTamnioCampo(emisor.getSchCharpaddingpin(),1 )  ){
			
			Utilitario.getLOG_APP().warn("CARACTER DE RELLENO PARA PIN Invalido");
			addFieldError("mensajeERROR", " Ingrese un Caracter de relleno para generacion de PIN Valido! Puede ser : "+Utilitario.getStringResourceBundle("CARACTER.GENERACION.PIN")+" ");
		}	
		
		else if( Utilitario.isVacioOrNull(emisor.getSchCharpaddingpam()) || !emisor.getSchCharpaddingpam().trim().matches(regSoloLetras)  || 
				!Utilitario.existeValorProperties(emisor.getSchCharpaddingpam().trim(), "CARACTER.GENERACION.PAM") ||
				!Utilitario.isValidoTamnioCampo(emisor.getSchCharpaddingpam(),1 )  ){
			
			Utilitario.getLOG_APP().warn("CARACTER DE RELLENO PARA PAM Invalido");
			addFieldError("mensajeERROR", " Ingrese un Caracter de relleno para generacion de PAM Valido! Puede ser : "+Utilitario.getStringResourceBundle("CARACTER.GENERACION.PAM")+" ");
		}		
		
		else if( emisor.getSflagTiempoDesbloqueo().equals("true") && ( Utilitario.isVacioOrNull(emisor.getShorasDesbloqueo()) || 
				!emisor.getShorasDesbloqueo().trim().matches(regTiempoDesbloqueo ) ) ){
			
			Utilitario.getLOG_APP().warn("Se habilito el tiempo de desbloqueo. Sin embargo, es incocorrecto el valor ingresado");
			addFieldError("mensajeERROR", " Ingrese un Tiempo de desbloqueo Valido! ");
			
		}

		else if( emisor.getSflagHabilitarSMS().equals("true") && ( 
			
				 Utilitario.isVacioOrNull(emisor.getSnameCarrier()) ||
				 Utilitario.isVacioOrNull(emisor.getScarrierClassImpl() )
					) ){
			
			Utilitario.getLOG_APP().warn("Se habilito la configuracion para Envio de SMS,Alguno de los campos de la configuracion es incorrecta");
			addFieldError("mensajeERROR", " Favor de verificar configuracion SMS! ");
		}

		else if( emisor.getSflagHabilitarEmail().equals("true") && ( Utilitario.isVacioOrNull(emisor.getSnameHost()) ||  !Utilitario.isValidoTamnioCampo(emisor.getSnameHost() , 80 ) || 
				Utilitario.isVacioOrNull(emisor.getSpuerto()) ||  !emisor.getSpuerto().trim().matches(regSoloNumero ) || !Utilitario.isValidoTamnioCampo(emisor.getSpuerto() , 80 ) ||
				Utilitario.isVacioOrNull(emisor.getSusuarioEmail()) || !Utilitario.isvalidoEmail(emisor.getSusuarioEmail()) || !Utilitario.isValidoTamnioCampo(emisor.getSusuarioEmail() , 80 ) ||
				Utilitario.isVacioOrNull(emisor.getSclaveEmail())   || !Utilitario.isValidoTamnioCampo(emisor.getSclaveEmail() , 80 )   )  ){
			
			Utilitario.getLOG_APP().warn("Se habilito la configuracion para Envio de Correo Electronico,Alguno de los campos de la configuracion es incorrecta");
			addFieldError("mensajeERROR", " Favor de verificar configuracion Correo Electronico! ");
			
		}
		else{
			
			Utilitario.getLOG_APP().warn("Todos los campos son correctos, se procede a verificar la disponibilidad del ID EMISOR");
			return isvalido = true;

		}
		
		return isvalido;
	}
	
	
	@SkipValidation
	@Action(value = "/irNuevoRegistroEmisor", results = { 
			@Result (location = "t_newRegistroEmisor", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
	})	
	public String irNuevoRegistroEmisor(){
		
		try {

			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ irNuevoRegistroEmisor ----");
			
			if(getBrandSelected()== null){
				brandSelected = Utilitario.getStringResourceBundle("MARCA_DEFAULT").trim();
			}	

			Utilitario.getLOG_APP().info("Marca seleccionada: " +getBrandSelected());
			Utilitario.getLOG_APP().info("Se carga los valores por defecto para el registro del emisor para los campos:");
			Utilitario.getLOG_APP().info("Clase implementacion :"+ Utilitario.getStringResourceBundle("DEFAULT.CLASE.IMPLEMENTACION"));
			Utilitario.getLOG_APP().info("Tipo de autenticacion POPUP: "+ Utilitario.getStringResourceBundle("DEFAULT.TIPO.AUTENTICACION.POPUP"));
			Utilitario.getLOG_APP().info("Tipo de enrolamiento por POPUP: "+Utilitario.getStringResourceBundle("DEFAULT.TIPO.ENROLAMIENTO.POPUP"));
			Utilitario.getLOG_APP().info("Caracter de relleno para la generacion del PIN: "+Utilitario.getStringResourceBundle("DEFAULT.CARACTER.GENERACION.PIN"));

			emisor = new IssuerBean();
			emisor.setSclaseimplementacion(Utilitario.getStringResourceBundle("DEFAULT.CLASE.IMPLEMENTACION").trim());
			emisor.setSchAuthenticateby(Utilitario.getStringResourceBundle("DEFAULT.TIPO.AUTENTICACION.POPUP").trim());
			emisor.setSchEnrolltype(Utilitario.getStringResourceBundle("DEFAULT.TIPO.ENROLAMIENTO.POPUP").trim());
			emisor.setSchCharpaddingpin(Utilitario.getStringResourceBundle("DEFAULT.CARACTER.GENERACION.PIN").trim());  
			emisor.setSchCharpaddingpam(Utilitario.getStringResourceBundle("DEFAULT.CARACTER.GENERACION.PAM").trim());
			
			brandInmpl= new BrandImplementation();
			listBrand   = brandInmpl.listBrand();
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ irNuevoRegistroEmisor ----");
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - irNuevoReigstroEmisor ",e);
			return INPUT;
		}
		return SUCCESS;
	}	
	
	
	@Action(value = "/registrarEmisor", results = { 
			@Result (location = "t_newRegistroEmisor", name = "success", type="tiles"),
			@Result (location = "t_newRegistroEmisor", name = "input", type="tiles")
			})		
	public String registrarEmisor(){
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarEmisor ----");
			
			IssuerBeanHibernate = new com.alignet.configurador.emisor.hibernate.bean.IssuerBean();
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			Utilitario.getLOG_APP().info("Marca seleccionada : " + brandBean.getIntIdBrand() );
			Utilitario.getLOG_APP().info("ID Emisor : " + emisor.getIntIdIssuer() );
			Utilitario.getLOG_APP().info("Nombre Emisor : " + emisor.getSvcName() );
			
			isssuerImpl = new IssuerImplementation( datasource );
			
			IssuerBeanHibernate.setIntIdIssuer( Integer.parseInt(emisor.getIntIdIssuer()));
			IssuerBeanHibernate.setSvcName(emisor.getSvcName().trim());
			IssuerBeanHibernate.setSvcInitials(emisor.getSvcInitials().trim().toUpperCase());
			IssuerBeanHibernate.setSchAuthenticateby(emisor.getSchAuthenticateby().trim().toUpperCase());
			IssuerBeanHibernate.setSchEnrolltype(emisor.getSchEnrolltype().trim().toUpperCase());
			IssuerBeanHibernate.setSchCharpaddingpin(emisor.getSchCharpaddingpin().trim().toUpperCase());
			IssuerBeanHibernate.setSchCharpaddingpam(emisor.getSchCharpaddingpam().trim().toUpperCase());
			IssuerBeanHibernate.setIntIntentlogin(Integer.parseInt(Utilitario.getStringResourceBundle("DEFAULT.INTENTLOGIN").trim()));
			IssuerBeanHibernate.setIntMinPwd(Integer.parseInt(Utilitario.getStringResourceBundle("DEFAULT.MINPWD").trim() ));
			IssuerBeanHibernate.setIntMaxPwd(Integer.parseInt(Utilitario.getStringResourceBundle("DEFAULT.MAXPWD").trim() ));
			IssuerBeanHibernate.setSchUseforgottenpwd(Utilitario.getStringResourceBundle("DEFAULT.USEFORGOTTENPWD").trim());	
			IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_ACTIVE);
			IssuerBeanHibernate.setSvcAdcText1(emisor.getSvcName().trim());
			IssuerBeanHibernate.setSvcPamDefault("Bienvenido a "+emisor.getSvcName());
			IssuerBeanHibernate.setDtRegistry(new Date());
			
			if(emisor.getSflagMatrixDecision().equals("true"))
				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_ACTIVE.toString());
			else
				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_NONACTIVE.toString());
			
			if(emisor.getSflagTiempoDesbloqueo().equals("true"))
				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_ACTIVE);
			else
				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_NONACTIVE);
			
			 boolean isExitoso = isssuerImpl.registrarEmisor(IssuerBeanHibernate); 
			 
			 if(isExitoso){
				
				 if(emisor.getSflagTiempoDesbloqueo().equals("true")){
					 IssuerBlockedImplementation isssuerBlockedImpl =  new IssuerBlockedImplementation(datasource);
					 IssuerBlockedBean IssuerBlockedHibernate = new IssuerBlockedBean();
					 IssuerBlockedHibernate.setIntIdIssuer(Integer.parseInt(emisor.getIntIdIssuer()));
					 IssuerBlockedHibernate.setIntTimeBlocked( Integer.parseInt(emisor.getShorasDesbloqueo()) );
					 IssuerBlockedHibernate.setSchState( Parameters.REGISTER_ACTIVE.toString());
					 isssuerBlockedImpl.registrarIssuerBlock(IssuerBlockedHibernate); 
				 }
				 
				
				 
				 DataConfigImplementation dataConfigImpl = new DataConfigImplementation(datasource);
				 ArrayList<DataConfigBean> listDataConfig = dataConfigImpl.listDataConfigRegistrar(emisor);
				
				 if( listDataConfig!=null && listDataConfig.size() > 0){
					 dataConfigImpl.registrarDataConfig(listDataConfig);
				 }
				
				
				 
				addFieldError("mensajeSUCCESS", " Se registro al emisor "+ emisor.getSvcName() + " correctamente ");
				emisor = new IssuerBean();
				emisor.setSclaseimplementacion(Utilitario.getStringResourceBundle("DEFAULT.CLASE.IMPLEMENTACION").trim());
				emisor.setSchAuthenticateby(Utilitario.getStringResourceBundle("DEFAULT.TIPO.AUTENTICACION.POPUP").trim());
				emisor.setSchEnrolltype(Utilitario.getStringResourceBundle("DEFAULT.TIPO.ENROLAMIENTO.POPUP").trim());
				emisor.setSchCharpaddingpin(Utilitario.getStringResourceBundle("DEFAULT.CARACTER.GENERACION.PIN").trim()); 
				 
				 
			 }else{
				addFieldError("mensajeERROR", " Error al registrar emisor, favor de volver a intentar ");
				 
			 }
			 
			 Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarEmisor ----");
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Error al registrar Emisor, intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - registrarEmisor ",e);
		}
		return SUCCESS;
	}
	

	@Action(value = "/irActualizarEmisor", results = { 
			@Result (location = "t_actualizarEmisor", name = "success", type="tiles"),
			@Result (location = "t_listaEmisor", name = "input", type="tiles")
			})	
	public String irActualizarEmisor(){
		
		Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ irActualizarEmisor ----");

		try {
			Utilitario.getLOG_APP().info("Emisor seleccionado: "+ emisorSeleccionado);
			Utilitario.getLOG_APP().info("Marca seleccionada: "+ brandBean.getIntIdBrand());
			String nomMarca = Utilitario.getStringResourceBundle("MARCA."+brandBean.getIntIdBrand()) ;
			brandBean.setSvcName(nomMarca);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			isssuerImpl = new IssuerImplementation( datasource );
			
			IssuerBeanHibernate = new com.alignet.configurador.emisor.hibernate.bean.IssuerBean();
			emisor = new IssuerBean();
			
			IssuerBeanHibernate = isssuerImpl.getEmisorforId(emisorSeleccionado);
			emisor.setIntIdIssuer(emisorSeleccionado.toString());
			emisor.setSvcName(IssuerBeanHibernate.getSvcName());
			emisor.setSvcInitials(IssuerBeanHibernate.getSvcInitials());
			emisor.setSchAuthenticateby(IssuerBeanHibernate.getSchAuthenticateby());
			emisor.setSchEnrolltype(IssuerBeanHibernate.getSchEnrolltype());
			emisor.setSchCharpaddingpin(IssuerBeanHibernate.getSchCharpaddingpin());
			emisor.setSchCharpaddingpam(IssuerBeanHibernate.getSchCharpaddingpam());
			emisor.setIntIdstate(IssuerBeanHibernate.getIntIdstate().toString());
			
			String schMatrixDecision = IssuerBeanHibernate.getSchMatrixDecision()!= null ? IssuerBeanHibernate.getSchMatrixDecision() : Parameters.REGISTER_NONACTIVE.toString();
			if(schMatrixDecision.equals(Parameters.REGISTER_ACTIVE.toString()))
				emisor.setSflagMatrixDecision("true");
			
			Integer IntUseblocked = IssuerBeanHibernate.getIntUseblocked()!=null ? IssuerBeanHibernate.getIntUseblocked() : Parameters.REGISTER_NONACTIVE;
			if(IntUseblocked.equals(Parameters.REGISTER_ACTIVE)){
				
				IssuerBlockedImplementation isssuerBlockedImpl =  new IssuerBlockedImplementation(datasource);
				IssuerBlockedBean IssuerBlockedHibernate = new IssuerBlockedBean();
				IssuerBlockedHibernate = isssuerBlockedImpl.getIsssuerBlockedForIdEmisor(emisorSeleccionado);
				if(IssuerBlockedHibernate!=null && IssuerBlockedHibernate.getSchState().equals(Parameters.REGISTER_ACTIVE.toString())){
					emisor.setSflagTiempoDesbloqueo("true");
					String sHoraTiempoDesbloqueo = IssuerBlockedHibernate.getIntTimeBlocked()!=null ? IssuerBlockedHibernate.getIntTimeBlocked().toString() : "";
					emisor.setShorasDesbloqueo(sHoraTiempoDesbloqueo);
				}
			}
			
			
			DataConfigImplementation dataconfigImpl = new DataConfigImplementation(datasource);
			List<DataConfigBean> listDataConfig = dataconfigImpl.getListDataConfigForIdEmisor(emisorSeleccionado);
			
			if(listDataConfig!=null && listDataConfig.size()>0){
				Utilitario.getLOG_APP().info("Total de Configuraciones obtenidas : "+ listDataConfig.size());
				
				String clase_implementacion = dataconfigImpl.getValueforKeyName(listDataConfig, "issuer.class.impl");
				emisor.setSclaseimplementacion(clase_implementacion);
				
				String sflagAttemps = dataconfigImpl.getValueforKeyName(listDataConfig, "issuer.attempt");
				if(sflagAttemps.equals("true")) emisor.setSflagAttemps(sflagAttemps);
				
				String sflagPantallaNoEnrolada= dataconfigImpl.getValueforKeyName(listDataConfig, "acs.noenrolcard");
				if(sflagPantallaNoEnrolada.equals("true")) emisor.setSflagPantallaTarjetaNoAfiliada(sflagPantallaNoEnrolada);			
				
				//String sNameImplWs = dataconfigImpl.getValueforKeyName(listDataConfig, "implementation.client.ws"); //NO DEBERIA HABER
				String sNameCarrier =  dataconfigImpl.getValueforKeyName(listDataConfig, "sms.carrier.name");
				String sCarrierClassImpl =  dataconfigImpl.getValueforKeyName(listDataConfig, "carrier.class.impl");  // DEBERIA SER carrier.class.impl
				
				if(!sNameCarrier.equals("") || !sCarrierClassImpl.equals("")){
					emisor.setSflagHabilitarSMS("true");
					emisor.setSnameCarrier(sNameCarrier);
					emisor.setScarrierClassImpl(sCarrierClassImpl);
				}

				String sFlagHabilitarEmail = dataconfigImpl.getValueforKeyName(listDataConfig, "send.email.toAuthenticate");
				if(sFlagHabilitarEmail.equals("true")){
					emisor.setSflagHabilitarEmail(sFlagHabilitarEmail);
					
					String sMailHost = dataconfigImpl.getValueforKeyName(listDataConfig, "mail.host");
					String sMailPort = dataconfigImpl.getValueforKeyName(listDataConfig, "mail.port");
					String sMailUser = dataconfigImpl.getValueforKeyName(listDataConfig, "mail.user");
					String sMailpassword = dataconfigImpl.getValueforKeyName(listDataConfig, "mail.password");
					
					emisor.setSnameHost(sMailHost);
					emisor.setSpuerto(sMailPort);
					emisor.setSusuarioEmail(sMailUser);
					emisor.setSclaveEmail(sMailpassword);
					
				}
				

			}
			
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ irActualizarEmisor ----");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - irActualizarEmisor ",e);
			addFieldError("mensajeERROR", "Error al obtener datos del Emisor, intente mas tarde");
			emisor = new IssuerBean();
			return INPUT;
		}
		return SUCCESS;
	}
	

	@Action(value = "/actualizarEmisor", results = { 
			@Result (location = "t_actualizarEmisor", name = "success", type="tiles"),
			@Result (location = "t_actualizarEmisor", name = "input", type="tiles")
			})	
	public String actualizarEmisor(){
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ actualizarEmisor ----");

			Utilitario.getLOG_APP().info("Marca Seleccionada : " + brandBean.getIntIdBrand());
			Utilitario.getLOG_APP().info("Id Emisor ingresado : " + emisor.getIntIdIssuer());

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			Utilitario.getLOG_APP().info("Se incia la Validacion del ID emisor Ingresado");
			isssuerImpl = new IssuerImplementation( datasource );
			boolean existeReistradoEmisor =  isssuerImpl.existeRegistradoIdEmisor(emisor.getIntIdIssuer());
			
			if(!existeReistradoEmisor) {
				addFieldError("mensajeERROR", " ID Emisor "+ emisor.getIntIdIssuer() + " no  es valido!! "); 
				Utilitario.getLOG_APP().warn("ERROR -  el emisor : "+ emisor.getIntIdIssuer()  +" no existe");
			}
			
			isssuerImpl = new IssuerImplementation( datasource );
			
			 boolean isExitoso = isssuerImpl.actualizarEmisor(emisor);
				
			 if(isExitoso){
				 
				 IssuerBlockedImplementation isssuerBlockedImpl =  new IssuerBlockedImplementation(datasource);
				 isssuerBlockedImpl.registrarActualizarIssuerBlock(emisor, brandBean);

				 DataConfigImplementation dataConfigImpl = new DataConfigImplementation(datasource);
				 ArrayList<DataConfigBean> listDataConfig = dataConfigImpl.listDataConfigRegistrar(emisor);
				
				 if( listDataConfig!=null && listDataConfig.size() > 0){
					 dataConfigImpl.actualizarDataConfig(listDataConfig);
				 }
				 
				 addFieldError("mensajeSUCCESS", " Se actualizo los datos del emisor "+ emisor.getSvcName() + " correctamente ");
				 
			 }else{
				 Utilitario.getLOG_APP().error("ERROR : EmisorAction - actualizarEmisor ");
				 addFieldError("mensajeERROR", "Error al actualizar Emisor, intente mas tarde");
			 }
			
			 Utilitario.getLOG_APP().info("<FIN>     METODO: ------ actualizarEmisor ----");
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Error al actualizar Emisor, intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR : EmisorAction - actualizarEmisor ",e);
		}
		return SUCCESS;
	}
	
	
	
	//@Override
	public void setSession(Map<String, Object> sessionMap) {
			this.sessionMapIssuer = sessionMap;
	}
	
	public BrandBean getBrandBean() {
		return brandBean;
	}
	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}
	public ArrayList<BrandBean> getListBrand() {
		return listBrand;
	}
	public void setListBrand(ArrayList<BrandBean> listBrand) {
		this.listBrand = listBrand;
	}

	public IssuerBean getEmisor() {
		return emisor;
	}
	public void setEmisor(IssuerBean emisor) {
		this.emisor = emisor;
	}

	public String getOperacionValidate() {
		return operacionValidate;
	}

	public void setOperacionValidate(String operacionValidate) {
		this.operacionValidate = operacionValidate;
	}
	
	
	public String getBrandSelected() {
		return brandSelected;
	}

	public void setBrandSelected(String brandSelected) {
		this.brandSelected = brandSelected;
	}

	public com.alignet.configurador.emisor.hibernate.bean.IssuerBean getIssuerBeanHibernate() {
		return IssuerBeanHibernate;
	}

	public void setIssuerBeanHibernate(com.alignet.configurador.emisor.hibernate.bean.IssuerBean issuerBeanHibernate) {
		IssuerBeanHibernate = issuerBeanHibernate;
	}
	public Map<String, Object> getSessionMapIssuer() {
		return sessionMapIssuer;
	}

	public void setSessionMapIssuer(Map<String, Object> sessionMapIssuer) {
		this.sessionMapIssuer = sessionMapIssuer;
	}




}
