package com.alignet.configurador.emisor.actions;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.alignet.configurador.emisor.bean.EnvioClaveDinamicaBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.bean.IssuerBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.IssuerImplementation;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class PantallaEnvioClaveDinamicaAction  extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
	private EnvioClaveDinamicaBean envioClaveBean;
	private BrandBean  brandBean;
	
	private String operacionValidate;
	private Integer brandSelected;	
	
	private SessionMap<String, Object> sessionMapClaveDinamica;
	
	
	public void validate(){
		try {
			Utilitario.getLOG_APP().info("<Inicio> Validate");
			
			if(operacionValidate.equals("validateCambiarConfiguracionMarca")){
				
				Utilitario.getLOG_APP().info("Metodo: validateCambiarConfiguracionMarca ");
				
				validateCambiarMarca(brandBean.getIntIdBrand());
			}
			else if(operacionValidate.equals("validateConfiguracionEnvioClave")){
				
				Utilitario.getLOG_APP().info("Metodo: validateConfiguracionEnvioClave ");
				if(validateCambiarMarca(brandBean.getIntIdBrand()))  
						validateConfiguracionEnvioClave();
			}	
			Utilitario.getLOG_APP().info("<fin> Validate");
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validate PantallaTarjetaBloqueadaAction ",e);
		}		
	}
	
	public boolean validateCambiarMarca(Integer idBrand){
		boolean isValido= false;
		try {
			String regBrand = "[1-2]";
			if( Utilitario.isVacioOrNull(idBrand)){
				Utilitario.getLOG_APP().warn("Marca seleccionada es NULL");
				addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}
			else if ( !idBrand.toString().matches(regBrand) ) {
					Utilitario.getLOG_APP().warn("Marca seleccionada es INVALIDA");
					addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}else{
				String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+idBrand).trim();
				Utilitario.getLOG_APP().info("El usuario selecciono la marca: " + nombreMarca);
				isValido = true;
			}
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR : PantallaTarjetaBloqueadaAction - validateCambiarMarca ",e);
		}
		return isValido;
	}
	
	
	@SkipValidation	
	@Action(value = "/irConfigEnvioClaveDinamica", results = { 
			@Result (location = "t_configEnvioClaveDinamica", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
			})	
	public String irConfigEnvioClaveDinamica(){
		try {
			
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : PANTALLA ENVIO CLAVE DINAMICA");
			setearConfiguracionXMarca(brandBean);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error irConfigEnvioClaveDinamica ",e);
			return INPUT;
		}
		return SUCCESS;
		
	}
	

	public void validateConfiguracionEnvioClave(){
		try {
				if(validateConfigSMS()){
					Utilitario.getLOG_APP().info("La configuracion para SMS es correcto, se procede validar la configuracion para Email");
					validateConfigEmail();	
				}
					
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validateConfiguracionEnvioClave",e);
		}
		
	}
	
	public boolean validateConfigSMS(){
		boolean isvalido=false;
		try {
			
			if(Utilitario.isVacioOrNull(envioClaveBean.getStexttituloSMS())){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Titulo SMS es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Titulo en la configuracion SMS");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStexttituloSMS(), 50)){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Titulo SMS el tamanio supera lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Titulo en la configuracion SMS");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getStextlblComercioSMS() )){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto de 'Comercio' es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del campo Comercio en la configuracion SMS");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblComercioSMS(), 20)){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto de 'Comercio' el tamanio supera lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo TitComercio en la configuracion SMS");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagmostrarintento() ) ){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - el check Mostrar numero de intento es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el check para mostrar Intetno en la configuracion SMS");
			}
			else if(envioClaveBean.getSflagmostrarintento().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextlblNumeroIntento()) ){

				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto de 'Intento' es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del campo intento en la configuracion SMS");
			}
			else if(envioClaveBean.getSflagmostrarintento().equals("true") && !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblNumeroIntento(), 20) ){

				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto de 'Intento' es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del campo intento en la configuracion SMS");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getStextlblClaveDinamica() )){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto Clave dinamica es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Titulo en la configuracion SMS");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblClaveDinamica(), 20)){
				Utilitario.getLOG_APP().warn("Configuracion SMS  - Texto Clave dinamica el tamanio supera lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Clave dinamica en la configuracion SMS");
			}			
			else {
				isvalido=true;
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validateConfigSMS",e);
		}
		return isvalido;
	}
	
	public void validateConfigEmail(){
		
		try {
			String regDosOpciones = "[1-2]";
			String regTresOpciones = "[1-3]";
			
			
			if(Utilitario.isVacioOrNull(envioClaveBean.getStextRemitente())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Remitente es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del texto Remitente en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStextRemitente(), 40) || !Utilitario.isvalidoEmail("no-reply@"+envioClaveBean.getStextRemitente())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Remitente no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el valor del texto Remitente en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getStextAsunto())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Asunto es vacio o nulo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del texto Asunto en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStextAsunto(), 50) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Asunto no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el valor del texto Asunto en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagSaludoInicial()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Saludo Inicial es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Saludo Inicial en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagSaludoInicial())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Saludo Inicial no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la opcion Saludo Inicial en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagSaludoInicial().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextSaludosInicial()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Camnpo Saludo Inicial es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del campo Saludo Inicial en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagSaludoInicial().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextSaludosInicial(), 50)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo saludo Inicial, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Saludo Inicial en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagParrafo1()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Parrafo1 es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la Parrafo Superior en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagParrafo1())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Parrafo1 no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la opcion Parrafo Superior en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagParrafo1().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextParrafo1()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Camnpo Parrafo1 es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Parrafo Superior en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagParrafo1().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextParrafo1(), 250)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Parrafo1, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Parrafo Superior en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflaglblComercioEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Nombre Comercio es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Saludo Inicial en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflaglblComercioEmail())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Nombre Comercio no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la Opcion Mostrar Nombre Comercio en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflaglblComercioEmail().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextlblComercioEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Nombre Comercio es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Campo Nombre Comercio en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflaglblComercioEmail().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblComercioEmail(), 50)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Nombre Comercio, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Nombre Comercio en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagMontoEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Monto es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Mostrar Monto en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagMontoEmail())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Monto no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la Opcion Mostrar Monto en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagMontoEmail().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextlblMontoEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Monto es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Campo Monto en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagMontoEmail().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblMontoEmail(), 40)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Monto, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Monto en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagMontoEmail().equals("true") && !envioClaveBean.getStextFormatoMontoEmail().matches(regTresOpciones)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Formato Monto invalido");
				addFieldError("mensajeERROR", "Favor de verificar del formato Monto en la configuración Correo Electrónico ");
			
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getStextlblClaveDinamicaEmail())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Texto Clave dinamica es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del texto Clave Dinámica en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblClaveDinamicaEmail(), 40)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Texto Clave dinamica, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del Campo Texto Clave dinámica en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagFechaEmail() ) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Fecha es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Mostrar Fecha en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagFechaEmail())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar Fecha no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la Opcion Mostrar Nombre Comercio en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagFechaEmail().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextlblFechaEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Fecha es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Campo Fecha en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagFechaEmail().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblFechaEmail(), 40)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Fecha, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Fecha en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagFechaEmail().equals("true") && !envioClaveBean.getStextFormatoFechaEmail().matches(regDosOpciones)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Formato Fecha invalido");
				addFieldError("mensajeERROR", "Favor de verificar del formato Fecha en la configuración Correo Electrónico ");
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagHoraEmail() ) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar hora es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Mostrar hora en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagHoraEmail())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Opcion Mostrar hora no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la Opcion Mostrar Hora en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagHoraEmail().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextlblHoraEmail()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Hora es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Campo Hora en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagHoraEmail().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextlblHoraEmail(), 40)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Hora, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Hora en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagHoraEmail().equals("true") && !envioClaveBean.getStextFormatoHoraEmail().matches(regDosOpciones)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Formato Hora invalido");
				addFieldError("mensajeERROR", "Favor de verificar del formato Hora en la configuración Correo Electrónico ");
			
			}
			else if(Utilitario.isVacioOrNull(envioClaveBean.getSflagParrafo3()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Parrafo3 es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor de la opción Parrafo Inferior en la configuración Correo Electrónico ");
			}
			else if(!Utilitario.isValidoCheck(envioClaveBean.getSflagParrafo3())){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Saludo Inicial no cumple con el formato");
				addFieldError("mensajeERROR", "Favor de verificar el formato de la opcion Parrafo Inferior en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagParrafo3().equals("true") && Utilitario.isVacioOrNull(envioClaveBean.getStextParrafo3()) ){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Camnpo Parrafo3 es vacio o nullo");
				addFieldError("mensajeERROR", "Favor de verificar el valor del Parrafo Inferior en la configuración Correo Electrónico ");
			}
			else if(envioClaveBean.getSflagParrafo3().equals("true") &&  !Utilitario.isValidoTamnioCampo(envioClaveBean.getStextParrafo3(), 250)){
				Utilitario.getLOG_APP().warn("Configuracion Email  - Campo Parrafo3, longitud excede lo permitido");
				addFieldError("mensajeERROR", "Favor de verificar la longitud del campo Parrafo Inferior en la configuración Correo Electrónico ");
			}
			else {
				Utilitario.getLOG_APP().info("Los datos para la configuracion de Email son correcto");
			}
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validateConfigSMS",e);
		}
	}
	
	
	

	

	@Action(value = "/cambiarConfiguracionMarcaEnvioClave", results = { 
			@Result (location = "t_configEnvioClaveDinamica", name = "success", type="tiles"),
			@Result (location = "t_configEnvioClaveDinamica", name = "input", type="tiles")
	})
	public String cambiarConfiguracionMarcaEnvioClave(){
		
		Utilitario.getLOG_APP().info("<INICIO> Metodo: cambiarConfiguracionMarcaEnvioClave ");
		try {
			
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandBean.getIntIdBrand()).trim();
			Utilitario.getLOG_APP().info("Se obtiene configuracion de la marca : " + nombreMarca);
			setearConfiguracionXMarca(brandBean);

		} catch (Exception e) {
			
			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR :  cambiarConfiguracionMarcaEnvioClave ",e);
			return INPUT;
			
		}
		Utilitario.getLOG_APP().info("<FIN> Metodo: cambiarConfiguracionMarcaEnvioClave ");
		return SUCCESS;
	}
	

	
	public void setearConfiguracionXMarca(BrandBean marca){
		
		try {
			
			UserBean usuario =(UserBean)sessionMapClaveDinamica.get("ObjetoUsuario");
			
			if(marca!=null && !Utilitario.isVacioOrNull(marca.getIntIdBrand()))
				brandSelected=  marca.getIntIdBrand();
			else
				brandSelected = Integer.parseInt(Utilitario.getStringResourceBundle("MARCA_DEFAULT"));
				
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			
			Utilitario.getLOG_APP().info("Se obtiene configuracion de la marca : " + nombreMarca + "["+ brandSelected+ "]");

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			
			envioClaveBean = new EnvioClaveDinamicaBean();
			
			obtenerConfiguracionSMS(datasource, usuario.getIntIdIssuer(), envioClaveBean);
			
			obtenerConfiguracionEmail(datasource, usuario.getIntIdIssuer() , envioClaveBean , brandSelected);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionXMarca ",e);
		}

	}
	
	public void obtenerConfiguracionSMS(Integer datasource, Integer IdUsuario, EnvioClaveDinamicaBean envioClaveBean){
		try {
			
			Utilitario.getLOG_APP().info("** Configuracion SMS : ");

			IssuerImplementation issuerimpl = new IssuerImplementation(datasource);
			IssuerBean IssuerBeanHibernate = issuerimpl.getEmisorforId(IdUsuario);
			
			String vc_docbase = IssuerBeanHibernate.getSvcDocbase()!=null && ! IssuerBeanHibernate.getSvcDocbase().trim().equals("") ? IssuerBeanHibernate.getSvcDocbase() : "";
			
			if(!vc_docbase.equals("") && vc_docbase.contains(",")){
				Utilitario.getLOG_APP().info("Emisor tiene una onfiguracion SMS ya definida." );
				Utilitario.getLOG_APP().info("Configuracion SMS  [ VC_DOCBASE ] : " +vc_docbase);
				envioClaveBean.setSflagTieneConfiguracionSMS("true");
				envioClaveBean = setearCamposxDocBase(vc_docbase, envioClaveBean);
			}
			else{
				Utilitario.getLOG_APP().info("Emisor no tiene configuracion SMS");
				Utilitario.getLOG_APP().info("Se procede a setear los valores por defecto");
				
				envioClaveBean.setSflagTieneConfiguracionSMS("false");
				
				envioClaveBean.setStexttituloSMS("Member Bank - Compras Internet");
				envioClaveBean.setStextlblComercioSMS("Comercio: ");
				envioClaveBean.setStextlblMontoSMS("Monto: ");
				envioClaveBean.setSflagmostrarintento("false");
				envioClaveBean.setStextlblNumeroIntento("Intento: ");
				envioClaveBean.setStextlblClaveDinamica("Clave Dinamica: ");
				
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfigSMSEnvioClave ",e);
		}
	}

	
	public void obtenerConfiguracionEmail(Integer datasource, Integer IdUsuario, EnvioClaveDinamicaBean envioClaveBean, Integer marca_seleccionada){
		try {
			
			Utilitario.getLOG_APP().info("** Configuracion EMAIL : ");

			DataConfigImplementation dataconfigImpl= new DataConfigImplementation(datasource);
			List<DataConfigBean> listDataConfig = dataconfigImpl.getListDataConfigForIdEmisor(IdUsuario);
			
			if(listDataConfig!= null && listDataConfig.size() > 0){
				
					String sHTML_email = dataconfigImpl.getValueforKeyName(listDataConfig, "mail.body");
					String sRemitente_email =  dataconfigImpl.getValueforKeyName(listDataConfig, "mail.from");
					String sAsunto_email= dataconfigImpl.getValueforKeyName(listDataConfig, "mail.subject");

					if( !Utilitario.isVacioOrNull(sHTML_email) && !Utilitario.isVacioOrNull(sRemitente_email)  && !Utilitario.isVacioOrNull(sAsunto_email)){
						Utilitario.getLOG_APP().info("Emisor Tiene  configuracion EMAIL");
						
						envioClaveBean.setSflagTieneConfiguracionEmail("true");
						Utilitario.getLOG_APP().info("mail.from : "+ sRemitente_email );
						
						envioClaveBean.setStextRemitente(sRemitente_email.substring("no-reply@".length(), sRemitente_email.length()) );
						
						envioClaveBean.setStextViewRemitente( sRemitente_email );
						
						Utilitario.getLOG_APP().info("mail.subject : "+ sAsunto_email );
						sAsunto_email= dataconfigImpl.setearTildeCadena(sAsunto_email, true);
						envioClaveBean.setStextAsunto(sAsunto_email);
						envioClaveBean.setStextViewAsunto(sAsunto_email);
						
						Utilitario.getLOG_APP().info("mail.body : "+ sHTML_email );
						String htmlEmailxMarca=  obtenerHtmlEmailxMarcaSeleccionada(sHTML_email, marca_seleccionada);
						
						envioClaveBean.setStextConfiguracionEmail(htmlEmailxMarca);
						
						ConfiguracionDeafultEmail(envioClaveBean);
						
					}else{
						Utilitario.getLOG_APP().info("Emisor NO Tiene  configuracion EMAIL, se muestra la configuracion por DAFAULT <1>");
						
						envioClaveBean.setSflagTieneConfiguracionEmail("false");
						envioClaveBean.setStextRemitente("alignet.com");
						envioClaveBean.setStextAsunto("Clave Dinámica");
						ConfiguracionDeafultEmail(envioClaveBean);
					}
				
			}else{
				Utilitario.getLOG_APP().info("Emisor NO Tiene  configuracion EMAIL, se muestra la configuracion por DAFAULT <2>");
				
				envioClaveBean.setSflagTieneConfiguracionEmail("false");
				envioClaveBean.setStextRemitente("alignet.com");
				envioClaveBean.setStextAsunto("Clave Dinámica");
				
				ConfiguracionDeafultEmail(envioClaveBean);
			}
				
			
		} catch (Exception e) {
			 Utilitario.getLOG_APP().error("ERROR :  setearConfigSMSEnvioClave ",e);
		}
	}
	
	
	public void ConfiguracionDeafultEmail(EnvioClaveDinamicaBean envioClaveBean){
		
		
		envioClaveBean.setSflagSaludoInicial("true");
		envioClaveBean.setStextSaludosInicial("Estimado Cliente:");
		
		envioClaveBean.setSflagParrafo1("true");
		envioClaveBean.setStextParrafo1("Le informamos su clave temporal para comprar en línea por internet con su tarjeta TARJETA_CREDITO ");
		
		envioClaveBean.setSflagParrafo2("false");
		
		envioClaveBean.setSflaglblComercioEmail("false");
		envioClaveBean.setStextlblComercioEmail("Comercio:");
		
		envioClaveBean.setSflagMontoEmail("false");
		envioClaveBean.setStextlblMontoEmail("Monto:");
		
		envioClaveBean.setStextlblClaveDinamicaEmail("Clave dinámica");
		
		envioClaveBean.setSflagFechaEmail("true");
		envioClaveBean.setStextlblFechaEmail("Fecha:");
		
		envioClaveBean.setSflagHoraEmail("true");
		envioClaveBean.setStextlblHoraEmail("Hora: ");
		
		envioClaveBean.setSflagParrafo3("true");
		envioClaveBean.setStextParrafo3("Recuerde que esta clave tiene una duración de TIEMPO_SEGUNDO segundos y es válida solamente para esta transacción.");
		
		envioClaveBean.setSflagParrafo4("false");
		envioClaveBean.setSflagParrafo5("false");
	}
	
	
	
	
	public String obtenerHtmlEmailxMarcaSeleccionada(String sHtmlEmail, Integer marcaSeleccionada){
		
		String HtmlEmail="";
		
		try {
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+marcaSeleccionada).trim();
			String item0 = Utilitario.getStringResourceBundle("ITEM0."+nombreMarca).trim();
			String item1 = Utilitario.getStringResourceBundle("ITEM1."+nombreMarca).trim();
			String item2 = Utilitario.getStringResourceBundle("ITEM2."+nombreMarca).trim();
			String item3 = Utilitario.getStringResourceBundle("ITEM3."+nombreMarca).trim();
			String item4 = Utilitario.getStringResourceBundle("ITEM4."+nombreMarca).trim();
			String item5 = Utilitario.getStringResourceBundle("ITEM5."+nombreMarca).trim();
			String item6 = Utilitario.getStringResourceBundle("ITEM6."+nombreMarca).trim();
			String item7 = Utilitario.getStringResourceBundle("ITEM7."+nombreMarca).trim();
			String item8 = Utilitario.getStringResourceBundle("ITEM8."+nombreMarca).trim();
			String item9 = Utilitario.getStringResourceBundle("ITEM9."+nombreMarca).trim();
			String item10 = Utilitario.getStringResourceBundle("ITEM10."+nombreMarca).trim();
			String item11 = Utilitario.getStringResourceBundle("ITEM11."+nombreMarca).trim();
			String item12 = Utilitario.getStringResourceBundle("ITEM12."+nombreMarca).trim();
			String item13 = Utilitario.getStringResourceBundle("ITEM13."+nombreMarca).trim();
			
			Object[] argument = {item0,item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11,item12,item13};
			
			if( sHtmlEmail.indexOf("<table>") > 0 &&  sHtmlEmail.indexOf("</body>")>0){
				HtmlEmail = sHtmlEmail.substring(  sHtmlEmail.indexOf("<table>") , sHtmlEmail.indexOf("</body>"));
				
				 MessageFormat form = new MessageFormat(HtmlEmail);
				 HtmlEmail=form.format(argument);
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR obtenerHtmlEmailxMarcaSeleccionada ",e);
		}
		return HtmlEmail;
	}


	public EnvioClaveDinamicaBean setearCamposxDocBase(String docbase, EnvioClaveDinamicaBean envioClaveBean ){
		try {
			
			String campoFormat = MessageFormat.format(docbase, "","","","","");
			
			String campo[] = campoFormat.split(",");
			
			String htmlConfiguracion = "";
			
			// Longitud 4: No tiene configurado Intent
			// Longitud 5: Si tiene configurado Intent
			
			if(campo.length==4){
				Utilitario.getLOG_APP().info("NO se mostrara INTENTO para SMS");
				
				envioClaveBean.setSflagmostrarintento("false");
				
				envioClaveBean.setStexttituloSMS(campo[0].trim());
				envioClaveBean.setStextlblComercioSMS(campo[1].trim());
				envioClaveBean.setStextlblMontoSMS(campo[2].trim());
				envioClaveBean.setStextlblClaveDinamica(campo[3].trim());
				
				htmlConfiguracion = "<table><tr><td>"+campo[0].trim()+"</td></tr>" +
						"<tr><td>"+campo[1].trim()+" ACS InLine Exp Test ALG</td></tr>" +
						"<tr><td>"+campo[2].trim()+" USD 25.00</td></tr>" +
						"<tr><td>"+campo[3].trim()+" 859413</td></tr></table>";
			}
			else if(campo.length==5){

				Utilitario.getLOG_APP().info("Se habilita INTENTO para SMS");
				
				envioClaveBean.setSflagmostrarintento("true");
				
				envioClaveBean.setStexttituloSMS(campo[0].trim());
				envioClaveBean.setStextlblComercioSMS(campo[1].trim());
				envioClaveBean.setStextlblMontoSMS(campo[2].trim());
				envioClaveBean.setStextlblNumeroIntento(campo[3].trim());
				envioClaveBean.setStextlblClaveDinamica(campo[4].trim());
				
				htmlConfiguracion = "<table><tr><td>"+campo[0].trim()+"</td></tr>" +
						"<tr><td>"+campo[1].trim()+" ACS InLine Exp Test ALG</td></tr>" +
						"<tr><td>"+campo[2].trim()+" USD 25.00</td></tr>" +
						"<tr><td>"+campo[3].trim()+" 1</td></tr>" +
						"<tr><td>"+campo[4].trim()+" 859413</td></tr></table>";
			}
			envioClaveBean.setStextConfiguracionSMS(htmlConfiguracion);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionXMarca ",e);
		}
		return envioClaveBean;
	}

	
	
	@Action(value = "/vistaResetEnvioClaveDinamica", results = { 
			@Result (location = "t_configEnvioClaveDinamica", name = "success", type="tiles"),
			@Result (location = "t_configEnvioClaveDinamica", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
			})	
	public String ResetEnvioClaveDinamica(){
		
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ ResetEnvioClaveDinamica ----");
			brandSelected = brandBean.getIntIdBrand();
			String brandName = Utilitario.getStringResourceBundle("MARCA."+brandSelected ).trim();

			UserBean usuario =(UserBean)sessionMapClaveDinamica.get("ObjetoUsuario");
			
			Integer ID_Emisor = usuario.getIntIdIssuer();
			
			Utilitario.getLOG_APP().info("Marca seleccionada: "+ brandName);
			
			Utilitario.getLOG_APP().info("Se obtiene la configuracion por default a reanudar: ");
			
			String defaultDocBase   = Utilitario.getStringResourceBundle("DEFAULT.DOCBASE."+brandName).trim();
			String defaultHtmlEmail = Utilitario.getStringResourceBundle("DEFAULT.HTML.EMAIL."+brandName ).trim();
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ) ) ;
			
			IssuerImplementation issuerImpl= new IssuerImplementation(datasource);

			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			//---------
			
			DConfigIDHibernate  = new DataConfigBean.Id(ID_Emisor,"mail.from");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"no-reply@alignet.com" , "Nombre del emisor de envío de email");
			ListDataconfig.add(DataConfigHibernate);
			
			//---------
			
			DConfigIDHibernate  = new DataConfigBean.Id(ID_Emisor,"mail.subject");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"Clave Din&aacute;mica","Subject del email");
			ListDataconfig.add(DataConfigHibernate);
			
			//---------
			
			DConfigIDHibernate  = new DataConfigBean.Id(ID_Emisor,"mail.body");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,defaultHtmlEmail ,"contenido de email");
			ListDataconfig.add(DataConfigHibernate);
			
			//---------
			
			if(issuerImpl.actualizarDocBase(ID_Emisor, defaultDocBase ) && dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				Utilitario.getLOG_APP().info("Se reanudo la configuracion  exitosamente."); 
				
				envioClaveBean.setSflagTieneConfiguracionSMS("true");
				envioClaveBean= setearCamposxDocBase(defaultDocBase, envioClaveBean);
			
				envioClaveBean.setSflagTieneConfiguracionEmail("true");
				envioClaveBean.setStextViewRemitente("no-reply@alignet.com");
				envioClaveBean.setStextRemitente("alignet.com");
				
				envioClaveBean.setStextAsunto("Clave Dinámica");
				envioClaveBean.setStextViewAsunto("Clave Dinámica");
				envioClaveBean.setStextConfiguracionEmail( obtenerHtmlEmailxMarcaSeleccionada(defaultHtmlEmail, brandSelected ));
				
				envioClaveBean.setSflagSaludoInicial("true");
				envioClaveBean.setStextSaludosInicial("Estimado Cliente:");
				
				envioClaveBean.setSflagParrafo1("true");
				envioClaveBean.setStextParrafo1("Le informamos su clave temporal para comprar en línea por internet con su tarjeta  TARJETA_CREDITO");
				envioClaveBean.setSflagParrafo2("false");
				
				envioClaveBean.setSflaglblComercioEmail("false");
				envioClaveBean.setSflagMontoEmail("false");
				
				envioClaveBean.setStextlblClaveDinamicaEmail("Clave dinámica");

				envioClaveBean.setSflagFechaEmail("true");
				envioClaveBean.setStextlblFechaEmail("Fecha:");
				
				envioClaveBean.setSflagHoraEmail("true");
				envioClaveBean.setStextlblHoraEmail("Hora:");
				
				
				envioClaveBean.setSflagParrafo3("true");
				envioClaveBean.setStextParrafo3("Recuerde que esta clave tiene una duración de TIEMPO_SEGUNDO segundos y es válida solamente para esta transacción.");
				envioClaveBean.setSflagParrafo4("false");
				envioClaveBean.setSflagParrafo5("false");
				
				
				addFieldError("mensajeSUCCESS", " Configuracion de envio Clave Dinámica se reanudo correctamente ");
			}else{
				Utilitario.getLOG_APP().error("ERROR : Reanudar configuracion DOC BASE, verificar BD");
				addFieldError("mensajeERROR", " Error al reanudar configuracion, intente nuevamente ");
				return INPUT;
			}
			
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ ResetEnvioClaveDinamica ----");

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  ResetEnvioClaveDinamica ",e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	@Action(value = "/registrarConfiguracionEnvioClave", results = { 
			@Result (location = "t_configEnvioClaveDinamica", name = "success", type="tiles"),
			@Result (location = "t_configEnvioClaveDinamica", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
			})	
	public String registrarConfiguracionEnvioClave(){
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarConfiguracionEnvioClave ----");
			
			brandSelected = brandBean.getIntIdBrand();
			
			Utilitario.getLOG_APP().info("ID MARCA : "+brandSelected);
			
			String sDocBaseGenerado = generarVCDOC_SMS(envioClaveBean, brandSelected);
				
			UserBean usuario =(UserBean)sessionMapClaveDinamica.get("ObjetoUsuario");

			Utilitario.getLOG_APP().info("DOC BASE generado : "+sDocBaseGenerado);

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ) ) ;
			
			IssuerImplementation issuerImpl= new IssuerImplementation(datasource);

			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			DConfigIDHibernate  = new DataConfigBean.Id(usuario.getIntIdIssuer(),"mail.from");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"no-reply@"+envioClaveBean.getStextRemitente(),"Nombre del emisor de envío de email");
			ListDataconfig.add(DataConfigHibernate);
			
			
			DConfigIDHibernate  = new DataConfigBean.Id(usuario.getIntIdIssuer(),"mail.subject");
			
			String sAsunto_email= dataconfigImpl.setearTildeCadena(envioClaveBean.getStextAsunto(), false);
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,sAsunto_email,"Subject del email");
			ListDataconfig.add(DataConfigHibernate);
			
			
			String emailHTML=generarBodyEmail(envioClaveBean, brandBean.getIntIdBrand());
			emailHTML= dataconfigImpl.setearTildeCadena(emailHTML, false);	
			Utilitario.getLOG_APP().info("Email generado "+ emailHTML);
			
			DConfigIDHibernate  = new DataConfigBean.Id(usuario.getIntIdIssuer(),"mail.body");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emailHTML ,"contenido de email");
			ListDataconfig.add(DataConfigHibernate);
			
			
			
			if(issuerImpl.actualizarDocBase(usuario.getIntIdIssuer(), sDocBaseGenerado) && dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				
				envioClaveBean.setSflagTieneConfiguracionSMS("true");
				envioClaveBean= setearCamposxDocBase(sDocBaseGenerado, envioClaveBean);
			
				envioClaveBean.setSflagTieneConfiguracionEmail("true");

				envioClaveBean.setStextViewRemitente("no-reply@"+envioClaveBean.getStextRemitente());
				
				envioClaveBean.setStextViewAsunto(envioClaveBean.getStextAsunto());
				envioClaveBean.setStextConfiguracionEmail( obtenerHtmlEmailxMarcaSeleccionada(emailHTML, brandBean.getIntIdBrand()) );
				
				Utilitario.getLOG_APP().info("Actualizacion exitosa."); 
				
				addFieldError("mensajeSUCCESS", " Configuracion de envio Clave Dinámica correctamente ");
			}else{
				Utilitario.getLOG_APP().error("ERROR : Actualizar DOC BASE, verificar BD");
				addFieldError("mensajeERROR", " Error al registrar configuracion, intente nuevamente ");
				return INPUT;
			}
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarConfiguracionEnvioClave ----");
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  registrarConfiguracionEnvioClave ",e);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	public String generarVCDOC_SMS(EnvioClaveDinamicaBean envioClaveBean, Integer marcaSeleccionada){
		String cadenaGenerada="";
		try {
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+marcaSeleccionada).trim();
			
			Utilitario.getLOG_APP().error("MARCA : "+nombreMarca);

			String sdocBaseComercio = Utilitario.getStringResourceBundle("DOCBASE.COMERCIO."+nombreMarca).trim();
			String sdocBasemonto = Utilitario.getStringResourceBundle("DOCBASE.MONTO."+nombreMarca).trim();
			String sdocBaseIntento = Utilitario.getStringResourceBundle("DOCBASE.INTENTO."+nombreMarca).trim();
			String sdocBaseClaveOTP = Utilitario.getStringResourceBundle("DOCBASE.CLAVEOTP."+nombreMarca).trim();
			
			if(envioClaveBean.getSflagmostrarintento().equals("true")){
				
				cadenaGenerada= envioClaveBean.getStexttituloSMS() +", "+
						envioClaveBean.getStextlblComercioSMS()+" "+sdocBaseComercio+", "+
						envioClaveBean.getStextlblMontoSMS()+" "+sdocBasemonto+", "+
						envioClaveBean.getStextlblNumeroIntento()+" "+sdocBaseIntento+ ", "+
						envioClaveBean.getStextlblClaveDinamica()+" "+sdocBaseClaveOTP;
				
			}else{
				
				cadenaGenerada= envioClaveBean.getStexttituloSMS() +", "+
						envioClaveBean.getStextlblComercioSMS()+" "+sdocBaseComercio+", "+
						envioClaveBean.getStextlblMontoSMS()+" "+sdocBasemonto+", "+
						envioClaveBean.getStextlblClaveDinamica()+" "+sdocBaseClaveOTP;
			}

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  generarVCDOC_SMS ",e);
		}
		return cadenaGenerada;
		
	}
	
	public String generarBodyEmail(EnvioClaveDinamicaBean envioClaveBean, Integer marcaSeleccionada){
		StringBuilder cadenaGenerada = new StringBuilder("<html><body><img src='https://test1.alignetsac.com/ACSVbVExp61/images/bnb_logo.gif'><table>");
		
		try {
		
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+marcaSeleccionada).trim();

			if(envioClaveBean.getSflagSaludoInicial().equals("true"))
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextSaludosInicial()+"</td></tr>");

			
			if(envioClaveBean.getSflagParrafo1().equals("true")){
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextParrafo1()+"</td></tr>");
				if(envioClaveBean.getSflagParrafo2().equals("true"))
					cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextParrafo2()+"</td></tr>");	
			}
			
			if(envioClaveBean.getSflaglblComercioEmail().equals("true")){

				String itemComercio = Utilitario.getStringResourceBundle("BODY.EMAIL.COMERCIO."+nombreMarca).trim();
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextlblComercioEmail()+" "+itemComercio+"</td></tr>");
			}
			
			if(envioClaveBean.getSflagMontoEmail().equals("true")){
				String itemMonto =   Utilitario.getStringResourceBundle("BODY.EMAIL.MONTO"+envioClaveBean.getStextFormatoMontoEmail()+"."+nombreMarca).trim();
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextlblMontoEmail() + " "+itemMonto+"</td></tr>");
			}
			
			String itemClaveOTP= Utilitario.getStringResourceBundle("BODY.EMAIL.CLAVEOTP."+nombreMarca).trim();
			cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextlblClaveDinamicaEmail()+" "+itemClaveOTP+"</td></tr>");
			
			if(envioClaveBean.getSflagFechaEmail().equals("true")){
				String itemFecha =   Utilitario.getStringResourceBundle("BODY.EMAIL.FECHA"+envioClaveBean.getStextFormatoFechaEmail()+"."+nombreMarca).trim();
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextlblFechaEmail() + " "+itemFecha+"</td></tr>");
			}
			
			if(envioClaveBean.getSflagHoraEmail().equals("true")){
				String itemHora =   Utilitario.getStringResourceBundle("BODY.EMAIL.HORA"+envioClaveBean.getStextFormatoHoraEmail()+"."+nombreMarca).trim();
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextlblHoraEmail() + " "+itemHora+"</td></tr>");
			}
			
			
			if(envioClaveBean.getSflagParrafo3().equals("true")){
				cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextParrafo3()+"</td></tr>");
				if(envioClaveBean.getSflagParrafo4().equals("true")){
					cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextParrafo4()+"</td></tr>");	
					if(envioClaveBean.getSflagParrafo5().equals("true"))
						cadenaGenerada.append("<tr><td><br/>"+envioClaveBean.getStextParrafo5()+"</td></tr>");	
				}
			}
			
			cadenaGenerada.append("</table></body></html>");
			
			
			String itemTarjeta= Utilitario.getStringResourceBundle("BODY.EMAIL.TARJETA."+nombreMarca).trim();
			String itemTimeSegundo= Utilitario.getStringResourceBundle("BODY.EMAIL.TIMEOTP_SEGUNDO."+nombreMarca).trim();
			String itemTimeMinuto= Utilitario.getStringResourceBundle("BODY.EMAIL.TIMEOTP_MINUTO."+nombreMarca).trim();
			
			Integer posicionTarjeta =  cadenaGenerada.indexOf("TARJETA_CREDITO");
			if(posicionTarjeta>0)
				cadenaGenerada=cadenaGenerada.replace(posicionTarjeta,posicionTarjeta +"TARJETA_CREDITO".length(), itemTarjeta);
			
			Integer posicionTimeSegundo = cadenaGenerada.indexOf("TIEMPO_SEGUNDO");
			if(posicionTimeSegundo>0)
				cadenaGenerada=cadenaGenerada.replace(posicionTimeSegundo,posicionTimeSegundo+ "TIEMPO_SEGUNDO".length(), itemTimeSegundo);
			
			Integer posicionTimeMinuto = cadenaGenerada.indexOf("TIEMPO_MINUTO");
			if(posicionTimeMinuto>0)
				cadenaGenerada=cadenaGenerada.replace(posicionTimeMinuto,posicionTimeMinuto + "TIEMPO_MINUTO".length(), itemTimeMinuto);
			
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  generarBodyEmail ",e);
	
		}
		return  cadenaGenerada.toString();
	}
	

	
	///@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMapClaveDinamica = (SessionMap<String, Object>)  sessionMap ;
	}
	
	public SessionMap<String, Object> getSessionMap() {
		return sessionMapClaveDinamica;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMapClaveDinamica = sessionMap;
	}


	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapClaveDinamica.get("listBrandHabilitadasEmisor");
		return listBrandHabilitadasEmisor;
	}


	public void setListBrandHabilitadasEmisor(ArrayList<BrandBean> listBrandHabilitadasEmisor) {
		this.listBrandHabilitadasEmisor = listBrandHabilitadasEmisor;
	}


	public BrandBean getBrandBean() {
		return brandBean;
	}


	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

	public Integer getBrandSelected() {
		return brandSelected;
	}

	public void setBrandSelected(Integer brandSelected) {
		this.brandSelected = brandSelected;
	}

	public EnvioClaveDinamicaBean getEnvioClaveBean() {
		return envioClaveBean;
	}

	public void setEnvioClaveBean(EnvioClaveDinamicaBean envioClaveBean) {
		this.envioClaveBean = envioClaveBean;
	}



	public String getOperacionValidate() {
		return operacionValidate;
	}



	public void setOperacionValidate(String operacionValidate) {
		this.operacionValidate = operacionValidate;
	}

}
