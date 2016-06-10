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

import com.alignet.configurador.emisor.bean.NotificacionEnrolamientoBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigSAEBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigSAEImplementation;
import com.alignet.configurador.emisor.util.HtmlFormatter;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value="ConfiguradorACS")
public class NotificacionEnrolamientoAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	
	private NotificacionEnrolamientoBean notificacionEnrolBean;
	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
	
	private BrandBean  brandBean;
	private Integer brandSelected;
	private String operacionValidate;
	
	private SessionMap<String, Object> sessionMapNotificaion;
	
	public void validate() {
		
		try {
			Utilitario.getLOG_APP().info("<Inicio> Validate");
			
				if(operacionValidate.equals("validateCambiarConfiguracionMarca")){
					
					Utilitario.getLOG_APP().info("Metodo: validateCambiarConfiguracionMarca ");
					validateCambiarMarca(brandBean.getIntIdBrand());
						
				}
				else if(operacionValidate.equals("validateNotificacionEnrolamiento")){
					
					Utilitario.getLOG_APP().info("Metodo: validateNotificacionEnrolamiento ");
					 if( validateCambiarMarca(brandBean.getIntIdBrand()) ){
						 validateNotificacionEnrolamiento();
					 }
				}
				
				brandSelected = brandBean.getIntIdBrand();
			
			Utilitario.getLOG_APP().info("<Fin> Validate"); 
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validate  AutenticacionClaveIncorrectaAction ",e);
		}
	}
	
	public boolean validateCambiarMarca(Integer idBrand){
		boolean isValido= false;
		try {
			String regBrand = "[1-2]";
			
			if( Utilitario.isVacioOrNull(idBrand)){
				
				Utilitario.getLOG_APP().warn("Marca Null");
				addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}
			else if ( !idBrand.toString().matches(regBrand) ) {
					Utilitario.getLOG_APP().warn("Marca Incorrecta");
					addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}else{
				
				isValido = true;
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR : PantallaTarjetaBloqueadaAction - validateCambiarMarca ",e);
		}
		return isValido;
	}
	
	public void validateNotificacionEnrolamiento(){
		try {
				if(validateNotificacionSMS()){
					Utilitario.getLOG_APP().info("La configuracion para SMS es correcto, se procede validar la configuracion para Email");
					validatenotificacionEmail();	
				}
					
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validateConfiguracionEnvioClave",e);
		}
		
	}
	
	public boolean validateNotificacionSMS(){
		boolean isvalido = false;
		try {
			
			if(Utilitario.isVacioOrNull(notificacionEnrolBean.getStextConfiguracionSMS()))
			{
				Utilitario.getLOG_APP().warn("Texto de Notificacion SMS es vacio o nulo");
				addFieldError("mensajeERROR", "El campo Mensaje de Enrolamiento SMS es requerido");
			}
			else if( !Utilitario.isValidoTamnioCampo(notificacionEnrolBean.getStextConfiguracionSMS(), 150) )
			{
				Utilitario.getLOG_APP().warn("Longitud del Texto de Notificacion SMS supera el limite permitido < Limite permitido- 150>");
				addFieldError("mensajeERROR", "El campo Mensaje de Enrolamiento SMS supera la longitud permitida");
			}
			else
			{
				String regCaracteresPermitidos="^[A-Za-z\\d_ -,:;.]+$";
				if( !notificacionEnrolBean.getStextConfiguracionSMS().matches(regCaracteresPermitidos)){
					Utilitario.getLOG_APP().warn("Texto de Notificacion SMS Tiene caracteres no permitidos");
					addFieldError("mensajeERROR", "El campo Mensaje de Enrolamiento SMS posee caracteres no permitidos");
				}else
					isvalido = true;
			}
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validateNotificacionSMS ",e);
		}
		return isvalido;
	}
	
	
	public boolean validatenotificacionEmail(){
		boolean isvalido = false;
		try {
			if(Utilitario.isVacioOrNull(notificacionEnrolBean.getStextAsunto() ))
			{
				Utilitario.getLOG_APP().warn("Texto Asunto de Notificacion EMAIL es vacio o nulo");
				addFieldError("mensajeERROR", "El campo Asunto de la Notificación Enrolamiento por Correo Electrónico es requerido");
			}
			else if( !Utilitario.isValidoTamnioCampo(notificacionEnrolBean.getStextAsunto() , 50) )
			{
				Utilitario.getLOG_APP().warn("Longitud del Asunto de Notificacion EMAIL supera el limite permitido < Limite permitido- 50>");
				addFieldError("mensajeERROR", "El campo Asunto de la Notificación Enrolamiento por Correo Electrónico supera la longitud permitida");
			}
			else if( notificacionEnrolBean.getSflagSaludoInicial().equals("true") &&
					Utilitario.isVacioOrNull(notificacionEnrolBean.getStextSaludoInicial()) )
			{
				Utilitario.getLOG_APP().warn("Texto Saludo Inicial de Notificacion EMAIL es vacio o nulo");
				addFieldError("mensajeERROR", "El campo Saludo Inicial de la Notificación Enrolamiento por Correo Electrónico es requerido");
			}	
			else if( notificacionEnrolBean.getSflagSaludoInicial().equals("true") &&
					!Utilitario.isValidoTamnioCampo(notificacionEnrolBean.getStextSaludoInicial() , 50) )
			{
				Utilitario.getLOG_APP().warn("Longitud del Saludo inicial de Notificacion EMAIL supera el limite permitido < Limite permitido- 50>");
				addFieldError("mensajeERROR", "El campo Saludo Inicial de la Notificación Enrolamiento por Correo Electrónico supera la longitud permitida");
			}
			else if( Utilitario.isVacioOrNull( notificacionEnrolBean.getStextParrafo1()) )
			{
				Utilitario.getLOG_APP().warn("Texto Parrafo1 de Notificacion EMAIL es vacio o nulo");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo1 de la Notificación Enrolamiento por Correo Electrónico es requerido");
			}	
			else if( !Utilitario.isValidoTamnioCampo( notificacionEnrolBean.getStextParrafo1() , 250) )
			{
				Utilitario.getLOG_APP().warn("Longitud Configuración texto - Parrafo1 de Notificacion EMAIL supera el limite permitido < Limite permitido- 250>");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo1 de la Notificación Enrolamiento por Correo Electrónico supera la longitud permitida");
			}	
			else if( notificacionEnrolBean.getSflagParrafo2().equals("true") &&
					Utilitario.isVacioOrNull( notificacionEnrolBean.getStextParrafo2()) )
			{
				Utilitario.getLOG_APP().warn("Texto Parrafo2 de Notificacion EMAIL es vacio o nulo");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo2 de la Notificación Enrolamiento por Correo Electrónico es requerido");
			}
			else if( notificacionEnrolBean.getSflagParrafo2().equals("true") &&
					!Utilitario.isValidoTamnioCampo( notificacionEnrolBean.getStextParrafo2() , 250)  )
			{
				Utilitario.getLOG_APP().warn("Longitud Configuración texto - Parrafo2 de Notificacion EMAIL supera el limite permitido < Limite permitido- 250>");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo2 de la Notificación Enrolamiento por Correo Electrónico supera la longitud permitida");
			}
			else if( notificacionEnrolBean.getSflagParrafo3().equals("true") &&
					Utilitario.isVacioOrNull( notificacionEnrolBean.getStextParrafo3()) )
			{
				Utilitario.getLOG_APP().warn("Texto Parrafo3 de Notificacion EMAIL es vacio o nulo");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo3 de la Notificación Enrolamiento por Correo Electrónico es requerido");
			}
			else if( notificacionEnrolBean.getSflagParrafo3().equals("true") &&
					!Utilitario.isValidoTamnioCampo( notificacionEnrolBean.getStextParrafo3() , 250)  )
			{
				Utilitario.getLOG_APP().warn("Longitud Configuración texto - Parrafo3 de Notificacion EMAIL supera el limite permitido < Limite permitido- 250>");
				addFieldError("mensajeERROR", "Configuración texto - Parrafo3 de la Notificación Enrolamiento por Correo Electrónico supera la longitud permitida");
			}
			else{
				isvalido = true;
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validatenotificacionEmail ",e);
		}
		return isvalido;
	}
	
	
	@SkipValidation
	@Action(value = "/irNotificacionEnrolamiento", results = { 
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
	})	
	public String irPantallaNotificacionEnrolamiento(){
		try {
			
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : NOTIFICACION DE ENROLAMIENTO");
			setearConfiguracionXMarca(brandBean);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  irPantallaNotificacionEnrolamiento ",e);
			return INPUT;
		}
		return SUCCESS;
				
	}
	
	public void setearConfiguracionXMarca(BrandBean marcaBean){
		
		try {
			UserBean usuario =(UserBean)sessionMapNotificaion.get("ObjetoUsuario");
			
			if(marcaBean!=null && !Utilitario.isVacioOrNull(marcaBean.getIntIdBrand()))
				brandSelected=  marcaBean.getIntIdBrand();
			else
				brandSelected = Integer.parseInt(Utilitario.getStringResourceBundle("MARCA_DEFAULT"));
							
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			String abreviaturaMarca= Utilitario.getStringResourceBundle("MARCA.ABREVIATURA."+brandSelected).trim();
			
			Utilitario.getLOG_APP().info("Se obtiene configuracion de la marca : " + nombreMarca + "["+ brandSelected+ "]");

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			
			DataConfigImplementation dataconfigImpl = new DataConfigImplementation(datasource);
			List<DataConfigBean> listDataConfig = dataconfigImpl.getListDataConfigForIdEmisor( usuario.getIntIdIssuer() );
			
			
			notificacionEnrolBean = new NotificacionEnrolamientoBean();
			
			
			obtenerConfiguracionNotificacionSMS(dataconfigImpl,listDataConfig, nombreMarca , notificacionEnrolBean );
			obtenerConfiguracionNotificacionEMAIL(dataconfigImpl,listDataConfig , usuario.getIntIdIssuer(),nombreMarca, abreviaturaMarca, notificacionEnrolBean);

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionXMarca ",e);
		}
		
	}

	public void obtenerConfiguracionNotificacionSMS(DataConfigImplementation dataconfigImpl ,List<DataConfigBean> listDataConfig , String BrandName, NotificacionEnrolamientoBean notificacionEnrolBean){
		try {
			Utilitario.getLOG_APP().info("** Configuracion SMS : ");

			
			notificacionEnrolBean.setSflagTieneConfiguracionSMS("false");
			
			if(listDataConfig!=null && listDataConfig.size()>0 ){
				
				String stextNotificaicionSMS = dataconfigImpl.getValueforKeyName( listDataConfig, "sae.sms.messageBody.insert");
				
				if(!stextNotificaicionSMS.equals("")){
					
					Utilitario.getLOG_APP().info("Tiene configurado [ sae.sms.messageBody.insert]");
					Utilitario.getLOG_APP().info("sae.sms.messageBody.insert : " + stextNotificaicionSMS);
					
					String sFormatItem0 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM0."+BrandName); 
					String sFormatItem1 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM1."+BrandName);
					
					String defaultNotificacionSMS= MessageFormat.format(stextNotificaicionSMS, sFormatItem0,sFormatItem1);
					String defaultTextNotificacionSMS =  MessageFormat.format(stextNotificaicionSMS, "TARJETA_CREDITO","ULTIMOS_4_DIGITOS_TARJETA");
					
					Utilitario.getLOG_APP().info("defaultNotificacionSMS : " + defaultNotificacionSMS);
					Utilitario.getLOG_APP().info("defaultTextNotificacionSMS : " + defaultTextNotificacionSMS);
					
				
					notificacionEnrolBean.setSflagTieneConfiguracionSMS("true");
					notificacionEnrolBean.setStextViewConfiguracionSMS(defaultNotificacionSMS);
					notificacionEnrolBean.setStextConfiguracionSMS(defaultTextNotificacionSMS);
				}
			}
			
			if( !notificacionEnrolBean.getSflagTieneConfiguracionSMS().equals("true")){
				
				Utilitario.getLOG_APP().info("EMISOR no tiene ninguna configuracion, se mostrará configuracion [ sae.sms.messageBody.insert] por defecto ");
				String defaultNotificacionSMS = Utilitario.getStringResourceBundle("DEFAULT.MSG.NOTIFICACION.ENROLAMIENTO."+BrandName).trim();
				Utilitario.getLOG_APP().info("sae.sms.messageBody.insert : " + defaultNotificacionSMS);
				String defaultTextNotificacionSMS =  MessageFormat.format(defaultNotificacionSMS,"TARJETA_CREDITO","ULTIMOS_4_DIGITOS_TARJETA");
				
				notificacionEnrolBean.setSflagTieneConfiguracionSMS("false");
				notificacionEnrolBean.setStextConfiguracionSMS(defaultTextNotificacionSMS);
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  obtenerConfiguracionNotificaionSMS ",e);
		}
	}
	
	public void obtenerConfiguracionNotificacionEMAIL(DataConfigImplementation dataconfigImpl ,List<DataConfigBean> listDataConfig , Integer IdEmisor,String brandName ,String BrandAbreviatura, NotificacionEnrolamientoBean notificacionEnrolBean){
		try {
			Utilitario.getLOG_APP().info("** Configuracion EMAIL : ");
			
			String sRemitente_email = "no-reply@alignet.com";
			
			if(listDataConfig!= null && listDataConfig.size() > 0){
				String  sMail_from  =  dataconfigImpl.getValueforKeyName(listDataConfig, "mail.from");
				sRemitente_email = sMail_from.equals("") ? "no-reply@alignet.com" : sMail_from;
			}
			
			notificacionEnrolBean.setStextRemitenteEmail(sRemitente_email);
			
			DataConfigSAEImplementation dataConfigSaeImpl = new DataConfigSAEImplementation();
			List<DataConfigSAEBean> listaDataConfigSae = dataConfigSaeImpl.getListDataConfigSAEForIdEmisor(IdEmisor);
			
			notificacionEnrolBean.setSflagTieneConfiguracionEmail("false");

			if(listaDataConfigSae!=null && listaDataConfigSae.size() > 0 ){
				
				Utilitario.getLOG_APP().info("Total de configuracion de TSM_DATACONFIG "+ listaDataConfigSae.size());
				
				String subjectNotificacion = dataConfigSaeImpl.getValueforKeyName(listaDataConfigSae, "mail.subject.insert."+BrandAbreviatura);
				String HtmlEmailNotificacion = dataConfigSaeImpl.getValueforKeyName(listaDataConfigSae, "mail.body.insert."+BrandAbreviatura);
				
				Utilitario.getLOG_APP().info("mail.subject.insert."+BrandAbreviatura + " : "+subjectNotificacion);
				Utilitario.getLOG_APP().info("mail.body.insert."+BrandAbreviatura+ " : "+HtmlEmailNotificacion);
				
				if( !subjectNotificacion.equals("") && !HtmlEmailNotificacion.equals("")){
					
					notificacionEnrolBean.setStextAsunto(subjectNotificacion);
					notificacionEnrolBean.setStextViewAsunto(subjectNotificacion);
					HtmlFormatter.setearConfiguracionEmail(HtmlEmailNotificacion,brandName, notificacionEnrolBean);
					
				}
			}
			
			if(notificacionEnrolBean.getSflagTieneConfiguracionEmail().equals("false")){
				
				Utilitario.getLOG_APP().info("No tiene ninguna configuracion en TSM_DATACONFIG");
				Utilitario.getLOG_APP().info("Se mostrara la configuracion por defecto");
				
				notificacionEnrolBean.setStextAsunto( Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.ENROLAMIENTO.SUBJECT") );
				
				notificacionEnrolBean.setSflagSaludoInicial("true");
				notificacionEnrolBean.setStextSaludoInicial("Estimado Cliente,");
				
				String defaultNotificacionParrafo1 = Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO1."+brandName);
				defaultNotificacionParrafo1 = Utilitario.setearTildeCadena(defaultNotificacionParrafo1, true);
				notificacionEnrolBean.setStextParrafo1(defaultNotificacionParrafo1 );

				notificacionEnrolBean.setSflagParrafo2("true");
				String defaultNotificacionParrafo2 = Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO2."+brandName);
				defaultNotificacionParrafo2 = Utilitario.setearTildeCadena(defaultNotificacionParrafo2, true);
				notificacionEnrolBean.setStextParrafo2(defaultNotificacionParrafo2);

				notificacionEnrolBean.setSflagParrafo3("true");
				String defaultNotificacionParrafo3 = Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO3."+brandName);
				defaultNotificacionParrafo3 = Utilitario.setearTildeCadena(defaultNotificacionParrafo3, true);
				notificacionEnrolBean.setStextParrafo3( defaultNotificacionParrafo3);
				
			}
			
			
			

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  obtenerConfiguracionNotificacionEMAIL ",e);
		}
	}
	
	
	@Action(value = "/cambiarConfiguracionMarcanotificacionEnrolamiento", results = { 
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
	})	
	public String cambiarConfiguracionMarca(){
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ cambiarConfiguracionMarca ----");
			
			setearConfiguracionXMarca(brandBean);

			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ cambiarConfiguracionMarca ----");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  irPantallaNotificacionEnrolamiento ",e);
			return INPUT;
		}
		return SUCCESS;
				
	}
	
	@Action(value = "/vistaResetNotificacionEnrolamiento", results = { 
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "success", type="tiles"),
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "input", type="tiles")
	})	
	public String ResetNotificacionEnrolamiento(){
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ ResetNotificacionEnrolamiento ----");
			brandSelected =  brandBean.getIntIdBrand();
			String brandName = Utilitario.getStringResourceBundle("MARCA."+brandSelected ).toString(); 
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+brandName + " ID: " +brandSelected );
			String abreviaturaMarca= Utilitario.getStringResourceBundle("MARCA.ABREVIATURA."+brandSelected).trim();
			
			Utilitario.getLOG_APP().info("Se obtiene la configuracion a setear por defecto: ");
			//NOTIFICACION EMAIL
			String defaultNotificacionSMS = Utilitario.getStringResourceBundle("DEFAULT.MSG.NOTIFICACION.ENROLAMIENTO."+brandName);
			Utilitario.getLOG_APP().info("ACS - sae.sms.messageBody.insert: "+defaultNotificacionSMS);
			
			
			//NOTIFICACION X SMS
			String defaultSubjectEMAIL=Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.ENROLAMIENTO.SUBJECT");
			String defaultNotificacionEMAIL = Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.ENROLAMIENTO."+brandName); 
			String defaultParrafo1= Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO1."+brandName); 
			String defaultParrafo2= Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO2."+brandName); 
			String defaultParrafo3= Utilitario.getStringResourceBundle("DEFAULT.EMAIL.NOTIFICACION.PARRAFO3."+brandName); 
			
			Utilitario.getLOG_APP().info("SAE - mail.subject.insert."+abreviaturaMarca+ " : "+defaultSubjectEMAIL);
			Utilitario.getLOG_APP().info("SAE - mail.body.insert."+abreviaturaMarca+ " : "+defaultNotificacionEMAIL);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ) ) ;
			UserBean usuario =(UserBean)sessionMapNotificaion.get("ObjetoUsuario");
			
			//------------------------------
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			DConfigIDHibernate  = new DataConfigBean.Id(usuario.getIntIdIssuer(),"sae.sms.messageBody.insert");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaultNotificacionSMS ,"Mensaje de enrolamiento del SAE MM para el Emisor");
			ListDataconfig.add(DataConfigHibernate);
			
			
			// Registro de la notificacion por EMAIL   -   SAE
			
			DataConfigSAEImplementation dataConfigSaeImpl = new DataConfigSAEImplementation();
			ArrayList<DataConfigSAEBean> ListDataconfigSae = new ArrayList<DataConfigSAEBean>();
			DataConfigSAEBean DataConfigSaeHibernate=null;
			DataConfigSAEBean.Id DConfigSaeIDHibernate =null;
			
			
			DConfigSaeIDHibernate  = new DataConfigSAEBean.Id(usuario.getIntIdIssuer(),"mail.subject.insert."+abreviaturaMarca);
			DataConfigSaeHibernate = new DataConfigSAEBean(DConfigSaeIDHibernate, defaultSubjectEMAIL  ,"Remitente del Mensaje de email que se envia al generar su clave OTP");
			ListDataconfigSae.add(DataConfigSaeHibernate);
			
			DConfigSaeIDHibernate  = new DataConfigSAEBean.Id(usuario.getIntIdIssuer(),"mail.body.insert."+abreviaturaMarca);
			DataConfigSaeHibernate = new DataConfigSAEBean(DConfigSaeIDHibernate, defaultNotificacionEMAIL ,"Mensaje de email que se envia al generar su clave ");
			ListDataconfigSae.add(DataConfigSaeHibernate);
			
			
			//--------------
			
			if(dataconfigImpl.actualizarDataConfig(ListDataconfig) && dataConfigSaeImpl.actualizarDataConfigSAE(ListDataconfigSae)){
				
				notificacionEnrolBean.setSflagTieneConfiguracionSMS("true");

				String sFormatItem0 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM0."+brandName); 
				String sFormatItem1 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM1."+brandName);
				
				String NotificacionSMS_View= MessageFormat.format(defaultNotificacionSMS, sFormatItem0,sFormatItem1);
				String NotificacionSMS_text =  MessageFormat.format(defaultNotificacionSMS, "TARJETA_CREDITO" ,"ULTIMOS_4_DIGITOS_TARJETA");
				
				notificacionEnrolBean.setStextConfiguracionSMS(NotificacionSMS_text);
				notificacionEnrolBean.setStextViewConfiguracionSMS(NotificacionSMS_View);
				
				notificacionEnrolBean.setStextAsunto(defaultSubjectEMAIL);
				notificacionEnrolBean.setStextViewAsunto(defaultSubjectEMAIL);
				
				notificacionEnrolBean.setSflagTieneConfiguracionEmail("true");
				String htmlNotificacionEmail_VIEW= defaultNotificacionEMAIL.substring(defaultNotificacionEMAIL.indexOf("<table>") , defaultNotificacionEMAIL.indexOf("</body>"));
				notificacionEnrolBean.setStextConfiguracionEmail(htmlNotificacionEmail_VIEW);
				notificacionEnrolBean.setStextParrafo1(defaultParrafo1);
				
				notificacionEnrolBean.setSflagParrafo2("true");
				notificacionEnrolBean.setStextParrafo2(defaultParrafo2);
				notificacionEnrolBean.setSflagParrafo3("true");
				notificacionEnrolBean.setStextParrafo3(defaultParrafo3);
				
				
				Utilitario.getLOG_APP().info("Notificacion de Enrolamiento reseteada correctamente"); 
				addFieldError("mensajeSUCCESS", "Notificación de Enrolamiento reseteada correctamente");
				
			}else{
				Utilitario.getLOG_APP().error("ERROR : Configuracion de Notificacion de Enrolamiento ");
				addFieldError("mensajeERROR", " Error al Resetear  Notificacion de Enrolamiento, intente nuevamente ");
				return INPUT;
			}

			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ ResetNotificacionEnrolamiento ----");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR la vista Previa ",e);
			addFieldError("mensajeERROR", " Error al resetear configuracion, intente nuevamente.");
			return INPUT;
		}
		return SUCCESS;
	}
	

	@Action(value = "/registrarNotificacionEnrolamiento", results = { 
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "success", type="tiles"),
			@Result (location = "t_pantallaNotificacionEnrolamiento", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
	})	
	public String registrarNotificacionEnrolamiento(){
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarNotificacionEnrolamiento ----");
			brandSelected = brandBean.getIntIdBrand();
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+nombreMarca+ " [ "+brandSelected+" ]");
			String abreviaturaMarca= Utilitario.getStringResourceBundle("MARCA.ABREVIATURA."+brandSelected).trim();
			
			
			// Registro de la notificacion por SMS   -   ACS
			String textNotificacionSMS = notificacionEnrolBean.getStextConfiguracionSMS();
			
			// {0}  ,  {1}  Respectivamente
			String sFormatTarjeta =Utilitario.getStringResourceBundle("NOTIFICACION.TARJETA."+nombreMarca); 
			String sFormat4DigitoTarjeta =Utilitario.getStringResourceBundle("NOTIFICACION.DIGITO.TARJETA."+nombreMarca); 

			
			/*
			NOTIFICACION.ITEM0.VISA=0869
			NOTIFICACION.ITEM1.VISA=407400 *** *** 046
			 * */
			String sFormatItem0 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM0."+nombreMarca); 
			String sFormatItem1 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM1."+nombreMarca);
			
			
			String textNotificacionSMS_BD = textNotificacionSMS.replaceAll("ULTIMOS_4_DIGITOS_TARJETA", sFormat4DigitoTarjeta).replaceAll("TARJETA_CREDITO", sFormatTarjeta);
			String textNotificaionSMS_VIEW = textNotificacionSMS.replaceAll("ULTIMOS_4_DIGITOS_TARJETA", sFormatItem1 ).replaceAll("TARJETA_CREDITO",sFormatItem0 );

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ) ) ;
			UserBean usuario =(UserBean)sessionMapNotificaion.get("ObjetoUsuario");
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			DConfigIDHibernate  = new DataConfigBean.Id(usuario.getIntIdIssuer(),"sae.sms.messageBody.insert");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, textNotificacionSMS_BD ,"Mensaje de enrolamiento del SAE MM para el Emisor");
			ListDataconfig.add(DataConfigHibernate);
			
			// Registro de la notificacion por EMAIL   -   SAE
			
			DataConfigSAEImplementation dataConfigSaeImpl = new DataConfigSAEImplementation();
			ArrayList<DataConfigSAEBean> ListDataconfigSae = new ArrayList<DataConfigSAEBean>();
			DataConfigSAEBean DataConfigSaeHibernate=null;
			DataConfigSAEBean.Id DConfigSaeIDHibernate =null;
			
			
			DConfigSaeIDHibernate  = new DataConfigSAEBean.Id(usuario.getIntIdIssuer(),"mail.subject.insert."+abreviaturaMarca);
			DataConfigSaeHibernate = new DataConfigSAEBean(DConfigSaeIDHibernate, notificacionEnrolBean.getStextAsunto() ,"Remitente del Mensaje de email que se envia al generar su clave OTP");
			ListDataconfigSae.add(DataConfigSaeHibernate);
			
			String htmlNotificacionEmail = generarHTMLNotificacionEmail(notificacionEnrolBean);
			String textNotificacionEmail_BD = htmlNotificacionEmail.replaceAll("ULTIMOS_4_DIGITOS_TARJETA", sFormat4DigitoTarjeta).replaceAll("TARJETA_CREDITO", sFormatTarjeta);
			
			DConfigSaeIDHibernate  = new DataConfigSAEBean.Id(usuario.getIntIdIssuer(),"mail.body.insert."+abreviaturaMarca);
			DataConfigSaeHibernate = new DataConfigSAEBean(DConfigSaeIDHibernate, Utilitario.setearTildeCadena(textNotificacionEmail_BD, false)   ,"Mensaje de email que se envia al generar su clave ");
			ListDataconfigSae.add(DataConfigSaeHibernate);
			
			if(dataconfigImpl.actualizarDataConfig(ListDataconfig) && dataConfigSaeImpl.actualizarDataConfigSAE(ListDataconfigSae)){
				notificacionEnrolBean.setSflagTieneConfiguracionSMS("true");
				
				notificacionEnrolBean.setStextViewConfiguracionSMS(textNotificaionSMS_VIEW);
				
				notificacionEnrolBean.setStextViewAsunto(notificacionEnrolBean.getStextAsunto());
				
				notificacionEnrolBean.setSflagSaludoInicial("true");
				notificacionEnrolBean.setStextSaludoInicial("Estimado cliente,");
				
				notificacionEnrolBean.setSflagTieneConfiguracionEmail("true");
				
				String htmlNotificacionEmail_VIEW= htmlNotificacionEmail.substring(htmlNotificacionEmail.indexOf("<table id='notificacion_enrolamiento'>") , htmlNotificacionEmail.indexOf("</body>"));
				String textNotificaionEmail_VIEW = htmlNotificacionEmail_VIEW.replaceAll("ULTIMOS_4_DIGITOS_TARJETA", sFormatItem1 ).replaceAll("TARJETA_CREDITO",sFormatItem0 );

				notificacionEnrolBean.setStextConfiguracionEmail(textNotificaionEmail_VIEW);
				
				Utilitario.getLOG_APP().info("Actualizacion/Registro de Notificacion de Enrolamiento exitoso."); 
				addFieldError("mensajeSUCCESS", "Configuracion de Notificacion de Enrolamiento correctemente");
				
			}else{
				Utilitario.getLOG_APP().error("ERROR : Configuracion de Notificacion de Enrolamiento ");
				addFieldError("mensajeERROR", " Error al Configurar  Notificacion de Enrolamiento, intente nuevamente ");
				return INPUT;
			}
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarNotificacionEnrolamiento ----");
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  registrarNotificacionEnrolamiento ",e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String generarHTMLNotificacionEmail(NotificacionEnrolamientoBean notificacionEnrolBean){
		StringBuilder htmlGenerado = new StringBuilder("<html><body><img src='https://test1.alignetsac.com/ACSVbVExp61/images/bnb_logo.gif'><table id='notificacion_enrolamiento'>");
		try {
			if(notificacionEnrolBean.getSflagSaludoInicial().equals("true"))
			{
				htmlGenerado.append("<tr><td><br/><div id='saludo_inicial'>"+notificacionEnrolBean.getStextSaludoInicial()+"</div></td></tr>");
			}
			
			htmlGenerado.append("<tr><td><br/><div id='parrafo1'>"+ notificacionEnrolBean.getStextParrafo1()+"</div></td></tr>");
			
			if(notificacionEnrolBean.getSflagParrafo2().equals("true"))
			{
				htmlGenerado.append("<tr><td><br/><div id='parrafo2'>"+ notificacionEnrolBean.getStextParrafo2()+"</div></td></tr>");
				if(notificacionEnrolBean.getSflagParrafo3().equals("true"))
					htmlGenerado.append("<tr><td><br/><div id='parrafo3'>"+ notificacionEnrolBean.getStextParrafo3()+"</div></td></tr>");
			}
			
			htmlGenerado.append("</table></body></html>");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  generarHTMLNotificacionEmail ",e);
		}
		return htmlGenerado.toString();
	}

	
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMapNotificaion((SessionMap<String, Object>) sessionMap);
	}

	public SessionMap<String, Object> getSessionMapNotificaion() {
		return sessionMapNotificaion;
	}

	public void setSessionMapNotificaion(SessionMap<String, Object> sessionMapNotificaion) {
		this.sessionMapNotificaion = sessionMapNotificaion;
	}


	public NotificacionEnrolamientoBean getNotificacionEnrolBean() {
		return notificacionEnrolBean;
	}
	
	public void setNotificacionEnrolBean(NotificacionEnrolamientoBean notificaicionEnrolBean) {
		this.notificacionEnrolBean = notificaicionEnrolBean;
	}


	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapNotificaion.get("listBrandHabilitadasEmisor");
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


	public String getOperacionValidate() {
		return operacionValidate;
	}


	public void setOperacionValidate(String operacionValidate) {
		this.operacionValidate = operacionValidate;
	}

}
