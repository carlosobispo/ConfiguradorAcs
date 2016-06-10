package com.alignet.configurador.emisor.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.alignet.configurador.emisor.bean.AutenticacionClaveIncorrectaBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigImplementation;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class AutenticacionClaveIncorrectaAction extends ActionSupport implements SessionAware{

	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
	
	private AutenticacionClaveIncorrectaBean aclaveincorrectaBean;
	private Map<String, Object> sessionMapTarjetaNoAfiliada;
	private BrandBean  brandBean;
	private String operacionValidate;
	private String brandSelected;
	
	private static final long serialVersionUID = 1L;

	
	public void validate() {
		try {
			Utilitario.getLOG_APP().info("<Inicio> Validate");
			
			if(operacionValidate.equals("validateCambiarConfiguracionMarca")){
				Utilitario.getLOG_APP().info("Metodo: validateCambiarConfiguracionMarca ");
				validateCambiarMarca(brandBean.getIntIdBrand());	
			}
			
			else if(operacionValidate.equals("validateConfiguracionAutenticacionClaveIncorrecta")){
				Utilitario.getLOG_APP().info("Metodo: validateConfiguracionAutenticacionClaveIncorrecta ");

				if(validateCambiarMarca(brandBean.getIntIdBrand())){
					validateConfiguracionAutenticacionClaveIncorrecta();
				}
			}
			
			brandSelected =	brandBean.getIntIdBrand().toString(); 
			 
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
	
	public void validateConfiguracionAutenticacionClaveIncorrecta(){
		try {
			if( Utilitario.isVacioOrNull(aclaveincorrectaBean.getStextTituloOTP()) ){
				Utilitario.getLOG_APP().warn("El valor del texto del Titulo en el popup OTP es vacio o null");
				addFieldError("mensajeERROR", "  Favor de verificar el texto ingresado para el Titulo en el popup OTP  ");
			}
			else if(aclaveincorrectaBean.getStextTituloOTP().length()>150){
				Utilitario.getLOG_APP().warn("La longitud de la cadena para el titulo en el popup OTP supera los 150 caracteres");
				addFieldError("mensajeERROR", "  Favor de verificar la longitud del texto para el Titulo en el popup OTP  ");
			}
			else if( !Utilitario.isValidoCheck(aclaveincorrectaBean.getSflagMostrarCelular())){
				Utilitario.getLOG_APP().warn("EL check para mostrar el numero de celular es invalido");
				addFieldError("mensajeERROR", "Favor de verificar el check Mostrar Número de Celular");
			}
			else if(Utilitario.isVacioOrNull(aclaveincorrectaBean.getStextlblClaveDinamica()) ){
				Utilitario.getLOG_APP().warn("El valor del texto de Clave dinamica es vacio o null");
				addFieldError("mensajeERROR", "  Favor de verificar el texto para la etiqueta Clave Dinámica");
			}
			else if(Utilitario.isVacioOrNull(aclaveincorrectaBean.getStextClaveIncorrecta()) ){
				Utilitario.getLOG_APP().warn("El valor del texto de Clave Incorrecta es vacio o null");
				addFieldError("mensajeERROR", "  Favor de verificar el texto para la etiqueta Clave Incorrecta");
			}
			else if(Utilitario.isVacioOrNull(aclaveincorrectaBean.getStextClaveExpirada()) ){
				Utilitario.getLOG_APP().warn("El valor del texto de Clave Expirada es vacio o null");
				addFieldError("mensajeERROR", "  Favor de verificar el texto para la etiqueta Clave Expirada");
			}
			else{
				
				
				String REG_titulo_popup_otp = "\\¿+|\\?+|\\°+|\\¬+|\\|+|\\#+|\\$+|\\%+|\\&+|\\+|\\=+|\\’+|\\++|\\*+|\\~+|\\[+|\\]+|\\{+|\\}+|\\^+|\\<+|\\>+";
				Pattern  pattern = Pattern.compile(REG_titulo_popup_otp);
	
				Matcher matcherTituloOTP = pattern.matcher(aclaveincorrectaBean.getStextTituloOTP());
				if(matcherTituloOTP.find()){
					Utilitario.getLOG_APP().warn("El formato del Titulo para el popup OTP es incorrecto : "+ aclaveincorrectaBean.getStextTituloOTP());
					addFieldError("mensajeERROR", "Favor de verificar el texto ingresado para el Titulo en el popup OTP");
				}
				
				//---------
				
				if(aclaveincorrectaBean.getSflagMostrarCelular().equals("true")){
					String regDigitosmostrarCelular = "[3-6]";
					if(!aclaveincorrectaBean.getStextDigitoCelular().matches(regDigitosmostrarCelular)){
						Utilitario.getLOG_APP().warn("Digitos mostrar CElular incorrecto : "+ aclaveincorrectaBean.getStextDigitoCelular());
						addFieldError("mensajeERROR", "Favor de verificar la cantidad de digitos no enmascarados");
					}
				}
				
				//--------
				String REG_EXP_GENERAL = "\\¿+|\\?+|\\°+|\\¬+|\\|+|\\!+|\\#+|\\$+|\\%+|\\&+|\\+|\\=+|\\’+|\\¡+|\\++|\\*+|\\~+|\\[+|\\]+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+";
				Pattern  patternGeneral = Pattern.compile(REG_EXP_GENERAL);
				
				Matcher matcherlblClaveDinamica = patternGeneral.matcher(aclaveincorrectaBean.getStextlblClaveDinamica());
				
				if(matcherlblClaveDinamica.find()){
					Utilitario.getLOG_APP().warn("El formato del texto Clave dinamica es incorrecto : "+ aclaveincorrectaBean.getStextlblClaveDinamica());
					addFieldError("mensajeERROR", "Favor de verificar el texto ingresado para la etiqueta Clave Dinámica");
				}
				
				
				Matcher matcherClaveIncorrecta = patternGeneral.matcher(aclaveincorrectaBean.getStextClaveIncorrecta());
				
				if(matcherClaveIncorrecta.find()){
					Utilitario.getLOG_APP().warn("ERROr en el formato del texto Clave incorrecta : "+ aclaveincorrectaBean.getStextClaveIncorrecta());
					addFieldError("mensajeERROR", "Favor de verificar el texto ingresado para Clave Incorrecta");
				}
				
				
				Matcher matcherClaveExpirada = patternGeneral.matcher(aclaveincorrectaBean.getStextClaveExpirada());
				
				if(matcherClaveExpirada.find()){
					Utilitario.getLOG_APP().warn("ERROr en el formato del texto Clave Expirada : "+ aclaveincorrectaBean.getStextClaveExpirada());
					addFieldError("mensajeERROR", "Favor de verificar el texto ingresado para Clave Expirada");
				}
			}
			
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validate  validateConfiguracionAutenticacionClaveIncorrecta ",e);
		}
		
	}

	
	@SkipValidation
	@Action(value = "/irConfigPantallaAutenticacionClaveIncorrectaEpirada", results = { 
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
	})	
	public String irConfigPantallaAutenticacionClaveIncorrectaEpirada(){
		try {
			
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : PANTALLA AUTENTICACION CLAVE INCORRECTA - EXPIRADA");
			
			setearConfiguracionxMarca(brandBean);

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  irConfigPantallaAutenticacionClaveIncorrectaEpirada ",e);
			return INPUT;
		}
		
		return SUCCESS;
				
	}
	
	
	@Action(value = "/cambiarConfiguracionMarcaAutenticacionClaveIncorrecta", results = { 
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "success", type="tiles"),
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "input", type="tiles")
	})
	public String cambiarConfiguracionMarca(){
		
		Utilitario.getLOG_APP().info("INICIO AutenticacionClaveIncorrectaAction: cambiarConfiguracionMarca");
		try {
			
			setearConfiguracionxMarca(brandBean);

		} catch (Exception e) {
			
			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR : AutenticacionClaveIncorrectaAction - cambiarConfiguracionMarca ",e);
			return INPUT;
			
		}
		Utilitario.getLOG_APP().info("FIN AutenticacionClaveIncorrectaAction: cambiarConfiguracionMarca");
		return SUCCESS;
	}	
	
	
	
	@Action(value = "/vistaPrevia", results = { 
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "success", type="tiles"),
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "input", type="tiles")
	})	
	public String vistaPrevia(){
		try {
			listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
			Utilitario.getLOG_APP().info("Marca Seleccionada "+brandBean.getIntIdBrand());
			brandSelected =  brandBean.getIntIdBrand().toString();
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			
			aclaveincorrectaBean.setStextVistaPreviaTituloOTP(aclaveincorrectaBean.getStextTituloOTP());
			
			if(aclaveincorrectaBean.getSflagMostrarCelular().equals("true")){
				
				aclaveincorrectaBean.setSflagVistaPreviaMostrarCelular("true");
				aclaveincorrectaBean.setStextVistaPreviaNumCelular( dataconfigImpl.CelularDigitoNoEnmascarados(aclaveincorrectaBean.getStextDigitoCelular()));
				aclaveincorrectaBean.setSelectetedDigitoCelular(aclaveincorrectaBean.getStextDigitoCelular());
			}
			
			if(aclaveincorrectaBean.getIndicardorVistaPrevia().equals("vistaPreviaClaveIncorrecta")){
				aclaveincorrectaBean.setStextVistaPreviaClave(aclaveincorrectaBean.getStextClaveIncorrecta());
			}
			else if( aclaveincorrectaBean.getIndicardorVistaPrevia().equals("vistaPreviaClaveExpirada") ){
				aclaveincorrectaBean.setStextVistaPreviaClave(aclaveincorrectaBean.getStextClaveExpirada());	
			}
			
			aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica(aclaveincorrectaBean.getStextlblClaveDinamica());
			
			addFieldError("mensajeALERTA", " Para guardar los cambios realizados, seleccionar el boton Grabar ");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR la vista Previa ",e);
		}
		return SUCCESS;
	}
	
	
	@SkipValidation
	@Action(value = "/vistaResetAutenticacionclaveincorrecta", results = { 
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "success", type="tiles"),
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "input", type="tiles")
	})	
	public String ResetAutenticacionclaveincorrecta(){
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ ResetAutenticacionclaveincorrecta ----");
			brandSelected =  brandBean.getIntIdBrand().toString();
			String brandName = Utilitario.getStringResourceBundle("MARCA."+brandSelected ).toString(); 
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+brandName + " ID: " +brandSelected );

			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ).trim() ) ;
			
			Utilitario.getLOG_APP().info("Se obtiene la configuracion a setear por defecto: ");
			String defaulTitleOTP =  Utilitario.getStringResourceBundle("DEFAULT.TITLE.OTP."+brandName ).trim();
			String defaulLblSecureKey =  Utilitario.getStringResourceBundle("DEFAULT.LBL.SECUREKEY."+brandName ).trim();
			String defaulDigitoNoEnmascaradoCelular =  Utilitario.getStringResourceBundle("DEFAULT.DIGITO.NO.ENMASCARADO.CELULAR."+brandName ).trim();
			String defaulMsgSkIncorrect =  Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.INCORRECT."+brandName ).trim();
			String defaulMsgSkEXpired =  Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.EXPIRED."+brandName ).trim();
			
			Utilitario.getLOG_APP().info("paut.title: "+defaulTitleOTP);
			Utilitario.getLOG_APP().info("paut.lbl.securekey: "+defaulLblSecureKey);
			Utilitario.getLOG_APP().info("issuer.cellnumnomask: "+defaulDigitoNoEnmascaradoCelular);
			Utilitario.getLOG_APP().info("paut.msg.sk.incorrect: "+defaulMsgSkIncorrect);
			Utilitario.getLOG_APP().info("paut.msg.sk.expired: "+defaulMsgSkEXpired);

			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			Integer IdEmisor = usuario.getIntIdIssuer();
			
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			//------
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.title");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaulTitleOTP,"Personalizacion del titulo en popup OTP");
			ListDataconfig.add(DataConfigHibernate);
			
			String textoTituloOTP=dataconfigImpl.setearTildeCadena(defaulTitleOTP, true);
			aclaveincorrectaBean.setStextTituloOTP(textoTituloOTP);
			aclaveincorrectaBean.setStextVistaPreviaTituloOTP(defaulTitleOTP);
			
			//------
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.lbl.securekey");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaulLblSecureKey,"Personalizacion de la etiqueta Clave Dinámica en popup OTP");
			ListDataconfig.add(DataConfigHibernate);
			
			String lblClaveDinamica = dataconfigImpl.setearTildeCadena(defaulLblSecureKey, true);
			aclaveincorrectaBean.setStextlblClaveDinamica(lblClaveDinamica);
			aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica(defaulLblSecureKey);
			
			//------
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.show.cellphone");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, "true" ,"Mostrar en pantalla OTP numero de celular");
			ListDataconfig.add(DataConfigHibernate);
			
			
			aclaveincorrectaBean.setSflagVistaPreviaMostrarCelular("true");
			aclaveincorrectaBean.setSflagMostrarCelular("true");
			aclaveincorrectaBean.setStextVistaPreviaNumCelular( dataconfigImpl.CelularDigitoNoEnmascarados(defaulDigitoNoEnmascaradoCelular ));
			aclaveincorrectaBean.setSelectetedDigitoCelular( defaulDigitoNoEnmascaradoCelular );
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "issuer.cellnumnomask");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaulDigitoNoEnmascaradoCelular,"Cantidad de digitos no enmascarados del celular");
			ListDataconfig.add(DataConfigHibernate);
			
			//------
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.msg.sk.incorrect");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaulMsgSkIncorrect ,"Personalizacion del mensaje clave incorrecta en popup estatico");
			ListDataconfig.add(DataConfigHibernate);
			String stextClaveIncorrecta= dataconfigImpl.setearTildeCadena( defaulMsgSkIncorrect , true );
			aclaveincorrectaBean.setStextClaveIncorrecta(stextClaveIncorrecta);
			
			//------
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.msg.sk.expired");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaulMsgSkEXpired ,"Personalizacion del mensaje clave expirada en popup estatico");
			ListDataconfig.add(DataConfigHibernate);
			String stextClaveExpirada= dataconfigImpl.setearTildeCadena( defaulMsgSkEXpired , true );
			aclaveincorrectaBean.setStextClaveExpirada(stextClaveExpirada);
			
			//-----
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "issuer.paut.customized");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, "true" ,"Indicador para Emisor que utiliza pantalla de autenticación personalizada");
			ListDataconfig.add(DataConfigHibernate);
			
			
			if(dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				Utilitario.getLOG_APP().info("Reset de configuracion Clave Incorrecta/Expirada correctamente ");
				
				
				
				
				addFieldError("mensajeSUCCESS", " Configuracion de Autenticación Clave Incorrecta y/o Expirada reseteada correctamente ");
				
			}else{
				Utilitario.getLOG_APP().error("ERROR al resetear de configuracion Clave Incorrecta/Expirada , revisar log de BD");
				addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			}
			
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ ResetAutenticacionclaveincorrecta ----");

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR la vista Previa ",e);
			addFieldError("mensajeERROR", " Error al resetear configuracion, intente nuevamente.");
		}
		return SUCCESS;
	}	
	
	
	
	
	@Action(value = "/registrarConfiguracionAutenticacionClaveIncorrecta", results = { 
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "success", type="tiles"),
			@Result (location = "t_pantallaAutenticacionClaveIncorrecta", name = "input", type="tiles")
	})
	public String registrarConfiguracionAutenticacionClaveIncorrecta(){
		
		Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarConfiguracionAutenticacionClaveIncorrecta ----");
		try {
			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			Integer IdEmisor = usuario.getIntIdIssuer();
			
			//listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
			
			brandSelected =  brandBean.getIntIdBrand().toString();
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+brandSelected);
			Utilitario.getLOG_APP().info("ID Emisor: "+IdEmisor);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.title");
			String textoTituloOTP=dataconfigImpl.setearTildeCadena(aclaveincorrectaBean.getStextTituloOTP(), false);
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, textoTituloOTP,"Personalización del titulo en popup OTP");
			ListDataconfig.add(DataConfigHibernate);
			aclaveincorrectaBean.setStextVistaPreviaTituloOTP(aclaveincorrectaBean.getStextTituloOTP());

			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.show.cellphone");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, aclaveincorrectaBean.getSflagMostrarCelular(),"Mostrar en pantalla OTP numero de celular");
			ListDataconfig.add(DataConfigHibernate);
			
			if(aclaveincorrectaBean.getSflagMostrarCelular().equals("true")){
				
				aclaveincorrectaBean.setSflagVistaPreviaMostrarCelular("true");
				aclaveincorrectaBean.setStextVistaPreviaNumCelular( dataconfigImpl.CelularDigitoNoEnmascarados(aclaveincorrectaBean.getStextDigitoCelular()));
				aclaveincorrectaBean.setSelectetedDigitoCelular(aclaveincorrectaBean.getStextDigitoCelular());
				DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "issuer.cellnumnomask");
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, aclaveincorrectaBean.getStextDigitoCelular(),"Cantidad de digitos no enmascarados del celular");
				ListDataconfig.add(DataConfigHibernate);
			}
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.lbl.securekey");
			String lblClaveDinamica= dataconfigImpl.setearTildeCadena(aclaveincorrectaBean.getStextlblClaveDinamica(), false);
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, lblClaveDinamica ,"Personalizacion de la etiqueta Clave Dinámica en popup OTP");
			aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica(aclaveincorrectaBean.getStextlblClaveDinamica());
			ListDataconfig.add(DataConfigHibernate);
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.msg.sk.incorrect");
			//String stextClaveIncorrecta= dataconfigImpl.setearTildeCadena(aclaveincorrectaBean.getStextClaveIncorrecta(), false);
			String stextClaveIncorrecta= aclaveincorrectaBean.getStextClaveIncorrecta();
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, stextClaveIncorrecta ,"Personalizacion del mensaje clave incorrecta en popup estatico");
			ListDataconfig.add(DataConfigHibernate);
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "paut.msg.sk.expired");
			//String stextClaveExpirada= dataconfigImpl.setearTildeCadena(aclaveincorrectaBean.getStextClaveExpirada(), false);
			String stextClaveExpirada=aclaveincorrectaBean.getStextClaveExpirada();
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, stextClaveExpirada ,"Personalizacion del mensaje clave expirada en popup estatico");
			ListDataconfig.add(DataConfigHibernate);
			
			DConfigIDHibernate = new DataConfigBean.Id(IdEmisor, "issuer.paut.customized");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, "true" ,"Indicador para Emisor que utiliza pantalla de autenticación personalizada");
			ListDataconfig.add(DataConfigHibernate);
			
			aclaveincorrectaBean.setIndicardorVistaPrevia("");
			aclaveincorrectaBean.setStextVistaPreviaClave("");
			
			if(dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				Utilitario.getLOG_APP().info(" Actualizacion de Configuracion  Autenticacion Clave Incorrecta y/o Expirada  correctamente ");
				addFieldError("mensajeSUCCESS", " Configuracion de Autenticación Clave Incorrecta y/o Expirada  correctamente  ");
				
			}else{
				Utilitario.getLOG_APP().error("ERROR Al actualizar configuracion Autenticacion Clave Incorrecta y/o Expirada, revisar log de BD");
				addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR : AutenticacionClaveIncorrectaAction - ConfiguracionAutenticaionClaveIncorrecta ",e);
			return INPUT;
		}

		Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarConfiguracionAutenticacionClaveIncorrecta ----");
		
		return SUCCESS;
	}
	
	
	

	public void setearConfiguracionxMarca(BrandBean marca){
		try {
			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
			Utilitario.getLOG_APP().info("Total de marcas asignadas al emisor [ "+ usuario.getIntIdIssuer()+" ] : "+ listBrandHabilitadasEmisor.size());
			
			if( marca!=null && !Utilitario.isVacioOrNull(marca.getIntIdBrand()))
				 brandSelected =	marca.getIntIdBrand().toString(); 
			else
				brandSelected = listBrandHabilitadasEmisor.get(0).getIntIdBrand().toString();

			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info(" Se obtiene la configuracion Autenticacion Clave Incorrecta de la marca [ "+nombreMarca+" ] : " + brandSelected);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			
			aclaveincorrectaBean =  new AutenticacionClaveIncorrectaBean();
			
			DataConfigImplementation dataconfigImpl =  new DataConfigImplementation(datasource);
			List<DataConfigBean> listDataConfig = dataconfigImpl.getListDataConfigForIdEmisor(usuario.getIntIdIssuer());
			
			if(listDataConfig!=null && listDataConfig.size()>0){
				aclaveincorrectaBean = setearAutenticaiconClaveIncorrectaXDataConfig(dataconfigImpl, listDataConfig, nombreMarca);
			}else{
				aclaveincorrectaBean= setearAutenticaiconClaveIncorrectaXDefault(dataconfigImpl, nombreMarca);
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionxMarca ",e);
		}
	}
	
	public AutenticacionClaveIncorrectaBean setearAutenticaiconClaveIncorrectaXDataConfig(DataConfigImplementation dataconfigImpl, List<DataConfigBean> listDataConfig , String nombreMarca) throws Exception{
		
		Utilitario.getLOG_APP().info("Total de Configuraciones obtenidas : "+ listDataConfig.size());
		AutenticacionClaveIncorrectaBean aclaveincorrectaBean = new  AutenticacionClaveIncorrectaBean();
		///-----
		
		String paut_title = dataconfigImpl.getValueforKeyName(listDataConfig, "paut.title");
		
		String stituloPopupOTP = paut_title.equals("") ?  Utilitario.getStringResourceBundle("DEFAULT.TITLE.OTP."+nombreMarca).trim() : paut_title;
		
		stituloPopupOTP =  dataconfigImpl.setearTildeCadena(stituloPopupOTP, true);
		aclaveincorrectaBean.setStextTituloOTP(stituloPopupOTP);	
		
		if( paut_title.equals("" ))  aclaveincorrectaBean.setStextVistaPreviaTituloOTP("");
		else  aclaveincorrectaBean.setStextVistaPreviaTituloOTP(stituloPopupOTP);

		///-----
		
		String smostrarCelular= dataconfigImpl.getValueforKeyName(listDataConfig, "paut.show.cellphone");
		
		smostrarCelular = smostrarCelular.equals("") ? "false" : smostrarCelular;
		
		String sdigitoNoEnmascaradosCelular = dataconfigImpl.getValueforKeyName(listDataConfig, "issuer.cellnumnomask");
		
		sdigitoNoEnmascaradosCelular = sdigitoNoEnmascaradosCelular.equals("") ?
				Utilitario.getStringResourceBundle("DEFAULT.DIGITO.NO.ENMASCARADO.CELULAR."+nombreMarca).trim() : sdigitoNoEnmascaradosCelular ;
				
		if(smostrarCelular.equals("true")){
			aclaveincorrectaBean.setSflagVistaPreviaMostrarCelular("true");
			aclaveincorrectaBean.setStextVistaPreviaNumCelular( dataconfigImpl.CelularDigitoNoEnmascarados(sdigitoNoEnmascaradosCelular));
		}  
	
		aclaveincorrectaBean.setSflagMostrarCelular(smostrarCelular);
		aclaveincorrectaBean.setSflagVistaPreviaMostrarCelular(smostrarCelular);
		aclaveincorrectaBean.setSelectetedDigitoCelular(sdigitoNoEnmascaradosCelular);
		
		///-----

		String paut_lbl_securekey= dataconfigImpl.getValueforKeyName(listDataConfig, "paut.lbl.securekey");
		
		String lblClaveDinamica = paut_lbl_securekey.equals("") ? Utilitario.getStringResourceBundle("DEFAULT.LBL.SECUREKEY."+nombreMarca).trim() : paut_lbl_securekey ;
		lblClaveDinamica = dataconfigImpl.setearTildeCadena(lblClaveDinamica, true);
		aclaveincorrectaBean.setStextlblClaveDinamica(lblClaveDinamica);
		
		if(paut_lbl_securekey.equals("")) aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica("");
		else aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica(lblClaveDinamica);
		
		///-----
		
		String paut_msg_sk_incorrect =   dataconfigImpl.getValueforKeyName(listDataConfig, "paut.msg.sk.incorrect");
		String stextClaveIncorrecta =  paut_msg_sk_incorrect.equals("") ? Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.INCORRECT."+nombreMarca).trim() : paut_msg_sk_incorrect;
		stextClaveIncorrecta = dataconfigImpl.setearTildeCadena(stextClaveIncorrecta, true);

		aclaveincorrectaBean.setStextClaveIncorrecta(stextClaveIncorrecta);

		
		///-----

		String paut_msg_sk_expired =   dataconfigImpl.getValueforKeyName(listDataConfig, "paut.msg.sk.expired");
		String stextClaveExpirada =  paut_msg_sk_incorrect.equals("") ? Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.EXPIRED."+nombreMarca).trim() : paut_msg_sk_expired;
		stextClaveExpirada = dataconfigImpl.setearTildeCadena(stextClaveExpirada, true);
		
		aclaveincorrectaBean.setStextClaveExpirada(stextClaveExpirada);

		return aclaveincorrectaBean;
	}
	
	public AutenticacionClaveIncorrectaBean setearAutenticaiconClaveIncorrectaXDefault( DataConfigImplementation dataconfigImpl, String nombreMarca) throws Exception{
		
		AutenticacionClaveIncorrectaBean  aclaveincorrectaBean =  new  AutenticacionClaveIncorrectaBean();
		
		Utilitario.getLOG_APP().info("Total de Configuraciones obtenidas : 0");
		Utilitario.getLOG_APP().info("El emisor no tiene ninguna configuracion para la marca");
		
		String stituloPopupOTP = Utilitario.getStringResourceBundle("DEFAULT.TITLE.OTP."+nombreMarca) ;
		stituloPopupOTP =  dataconfigImpl.setearTildeCadena(stituloPopupOTP, true);
		
		aclaveincorrectaBean.setStextVistaPreviaTituloOTP("");
		aclaveincorrectaBean.setStextTituloOTP(stituloPopupOTP);
		
		//---
		
		String lblClaveDinamica = Utilitario.getStringResourceBundle("DEFAULT.LBL.SECUREKEY."+nombreMarca).trim() ;
		lblClaveDinamica = dataconfigImpl.setearTildeCadena(lblClaveDinamica, true);
		aclaveincorrectaBean.setStextlblClaveDinamica(lblClaveDinamica);
		aclaveincorrectaBean.setStextVistaPrevialblClaveDinamica("");
		
		
		//---
		aclaveincorrectaBean.setSflagMostrarCelular("false");
		String sdigitoNoEnmascaradosCelular  = Utilitario.getStringResourceBundle("DEFAULT.DIGITO.NO.ENMASCARADO.CELULAR."+nombreMarca).trim();
		aclaveincorrectaBean.setSelectetedDigitoCelular(sdigitoNoEnmascaradosCelular);
		
		//---
		
		String stextClaveIncorrecta =   Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.INCORRECT."+nombreMarca).trim();
		stextClaveIncorrecta = dataconfigImpl.setearTildeCadena(stextClaveIncorrecta, true);

		aclaveincorrectaBean.setStextClaveIncorrecta(stextClaveIncorrecta);
		
		//---

		String stextClaveExpirada = Utilitario.getStringResourceBundle("DEFAULT.MSG.SK.EXPIRED."+nombreMarca).trim();
		stextClaveExpirada = dataconfigImpl.setearTildeCadena(stextClaveExpirada, true);
		
		aclaveincorrectaBean.setStextClaveExpirada(stextClaveExpirada);
		
		return aclaveincorrectaBean;
	}
	
	
	
	//@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMapTarjetaNoAfiliada= sessionMap;
	}

	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
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


	public AutenticacionClaveIncorrectaBean getAclaveincorrectaBean() {
		return aclaveincorrectaBean;
	}


	public void setAclaveincorrectaBean(AutenticacionClaveIncorrectaBean aclaveincorrectaBean) {
		this.aclaveincorrectaBean = aclaveincorrectaBean;
	}


}
