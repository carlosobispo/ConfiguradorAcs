package com.alignet.configurador.emisor.actions;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.alignet.configurador.emisor.bean.TarjetaNoAfiliadaBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.IssuerBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.IssuerImplementation;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class PantallaTarjetaNoAfiliadaAction extends ActionSupport implements SessionAware {
	
	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
	
	private BrandBean  brandBean;
	private TarjetaNoAfiliadaBean tnoafiliadaBean;
	private Map<String, Object> sessionMapTarjetaNoAfiliada;
	
	private String operacionValidate;
	private String brandSelected;
	
	private static final long serialVersionUID = 1L;


	public void validate(){
		
		try {
			Utilitario.getLOG_APP().info(" Inicio de validacion   validate");
			Utilitario.getLOG_APP().info(" Operacion a realizar: "+ operacionValidate);
			
			if(operacionValidate.equals("validateCambiarConfiguracionMarca")){
				validateCambiarMarca(brandBean.getIntIdBrand());
			}
			else if(operacionValidate.equals("validateConfiguracionTarjetaNoAfiliada")){
				if( validateCambiarMarca(brandBean.getIntIdBrand()) ){
					validateConfiguracionTarjetaNoAfiliada();
				}
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR  validate ",e);
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
	
	
	public void validateConfiguracionTarjetaNoAfiliada(){
		try {
			if(Utilitario.isVacioOrNull( tnoafiliadaBean.getStextParrafo1())){
				Utilitario.getLOG_APP().warn("tnoafiliadaBean.getStextParrafo1() es NULL o vacio");
				addFieldError("mensajeERROR", "Favor de verificar la configuracion para el Parrafo1");
			}
			else if(tnoafiliadaBean.getStextParrafo1().length()>300){
				Utilitario.getLOG_APP().warn("Se habilita el parafo2, pero la longitud del Parrafo1 es mayor a 300");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo2");
			}
			else if(Utilitario.isVacioOrNull( tnoafiliadaBean.getStextParrafo2())){
				Utilitario.getLOG_APP().warn("tnoafiliadaBean.getStextParrafo2() es NULL o vacio");
				addFieldError("mensajeERROR", "Favor de verificar la configuracion para el Parrafo2");
			}
			else if(tnoafiliadaBean.getStextParrafo2().length()>400){
				Utilitario.getLOG_APP().warn("Se habilita el parafo3, pero la longitud del Parrafo1 es mayor a 400");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo2");
			}
			else if(Utilitario.isVacioOrNull( tnoafiliadaBean.getStextParrafo3())){
				Utilitario.getLOG_APP().warn("tnoafiliadaBean.getStextParrafo3() es NULL o vacio");
				addFieldError("mensajeERROR", "Favor de verificar la configuracion para el Parrafo3");
			}
			else if(tnoafiliadaBean.getStextParrafo3().length()>200){
				Utilitario.getLOG_APP().warn("Se habilita el parafo3, pero la longitud del Parrafo2 es mayor a 200");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo3");
			}
			else{
				String REG_EXP = "\\¿+|\\?+|\\°+|\\¬+|\\|+|\\!+|\\#+|\\$+|\\%+|\\&+|\\+|\\=+|\\’+|\\¡+|\\*+|\\~+|\\[+|\\]+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+";
				Pattern  pattern = Pattern.compile(REG_EXP);
			
				Matcher matcher1 = pattern.matcher( tnoafiliadaBean.getStextParrafo1() );
				
				if(matcher1.find()){
					Utilitario.getLOG_APP().warn("El formato del parrafo1 es incorrecto : "+ tnoafiliadaBean.getStextParrafo1());
					addFieldError("mensajeERROR", "Favor de verificar el formato del parrafo1");
					
				}
				
				Matcher matcher2 = pattern.matcher( tnoafiliadaBean.getStextParrafo2() );
				
				if(matcher2.find()){
					Utilitario.getLOG_APP().warn("El formato del parrafo1 es incorrecto : "+ tnoafiliadaBean.getStextParrafo2());
					addFieldError("mensajeERROR", "Favor de verificar el formato del parrafo2");
					
				}
				
				Matcher matcher3 = pattern.matcher( tnoafiliadaBean.getStextParrafo3() );
				
				if(matcher3.find()){
					Utilitario.getLOG_APP().warn("El formato del parrafo1 es incorrecto : "+ tnoafiliadaBean.getStextParrafo3());
					addFieldError("mensajeERROR", "Favor de verificar el formato del parrafo3");
					
				}				
			}

		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR :  validateConfiguracionTarjetaNoAfiliada ",e);
		
		}
	}
	
	@SkipValidation
	@Action(value = "/irConfigPantallaTarjetaNoAfiliada", results = { 
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
			})	
	public String irConfigPantallaTarjetaNoAfiliada(){
		
		try {
			
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : PANTALLA TARJETA NO AFILIADA");
			
			setearConfiguracionxMarca(brandBean);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  irConfigPantallaTarjetaNoAfiliada ",e);
			return INPUT;
			
		}
		return SUCCESS;
	}
	

	public void setearConfiguracionxMarca(BrandBean marca){
		try {
			
			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			//listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
			//Utilitario.getLOG_APP().info("Total de marcas asignadas al emisor [ "+ usuario.getIntIdIssuer()+" ] : "+ listBrandHabilitadasEmisor.size());
			
			if( marca!=null && !Utilitario.isVacioOrNull(marca.getIntIdBrand()))
				brandSelected =	marca.getIntIdBrand().toString(); 
			else
				brandSelected = Utilitario.getStringResourceBundle("MARCA_DEFAULT");
			//	brandSelected = listBrandHabilitadasEmisor.get(0).getIntIdBrand().toString();
				
			
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info(" Se obtiene la configuracion tarjeta No Afilida de la marca [ "+nombreMarca+" ] : " + brandSelected);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			
			tnoafiliadaBean = new TarjetaNoAfiliadaBean();
			IssuerImplementation issuerimpl = new IssuerImplementation(datasource);
			IssuerBean IssuerBeanHibernate = issuerimpl.getEmisorforId(usuario.getIntIdIssuer());
			
			String stextParrafo1TarjetaNoAfiliada = IssuerBeanHibernate.getSvcAdcText2()!= null && !IssuerBeanHibernate.getSvcAdcText2().trim().equals("")  ? IssuerBeanHibernate.getSvcAdcText2() : "";
			String stextParrafo2TarjetaNoAfiliada = IssuerBeanHibernate.getSvcAdcText3()!= null && !IssuerBeanHibernate.getSvcAdcText3().trim().equals("")  ? IssuerBeanHibernate.getSvcAdcText3() : "";
			String stextParrafo3TarjetaNoAfiliada = IssuerBeanHibernate.getSvcAdcText4()!= null && !IssuerBeanHibernate.getSvcAdcText4().trim().equals("")  ? IssuerBeanHibernate.getSvcAdcText4() : "";
			
			if( Utilitario.isVacioOrNull(stextParrafo1TarjetaNoAfiliada) && Utilitario.isVacioOrNull(stextParrafo2TarjetaNoAfiliada)  && Utilitario.isVacioOrNull(stextParrafo3TarjetaNoAfiliada))
				tnoafiliadaBean.setSflagPoseeConfiguracion("false");
			else 
				tnoafiliadaBean.setSflagPoseeConfiguracion("true");
			
			
			if(tnoafiliadaBean.getSflagPoseeConfiguracion().equals("false")){
			
				Utilitario.getLOG_APP().info("El emisor "+IssuerBeanHibernate.getSvcName() + " NO posee una ninguna configuracion registrada, semuestra las configuracion por defecto" );
				stextParrafo1TarjetaNoAfiliada = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO1."+nombreMarca).trim();
				stextParrafo2TarjetaNoAfiliada = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO2."+nombreMarca).trim();
				stextParrafo3TarjetaNoAfiliada = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO3."+nombreMarca).trim();

			}

			stextParrafo1TarjetaNoAfiliada = stextParrafo1TarjetaNoAfiliada.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
			stextParrafo2TarjetaNoAfiliada = stextParrafo2TarjetaNoAfiliada.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
			stextParrafo3TarjetaNoAfiliada = stextParrafo3TarjetaNoAfiliada.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
			
			stextParrafo1TarjetaNoAfiliada = issuerimpl.setearTildeCadena(stextParrafo1TarjetaNoAfiliada,true);
			stextParrafo2TarjetaNoAfiliada = issuerimpl.setearTildeCadena(stextParrafo2TarjetaNoAfiliada,true);
			stextParrafo3TarjetaNoAfiliada = issuerimpl.setearTildeCadena(stextParrafo3TarjetaNoAfiliada,true);

			
			tnoafiliadaBean.setStextParrafo1(stextParrafo1TarjetaNoAfiliada);
			tnoafiliadaBean.setStextVistaPreviaParrafo1(stextParrafo1TarjetaNoAfiliada);
			
			tnoafiliadaBean.setStextParrafo2(stextParrafo2TarjetaNoAfiliada);
			tnoafiliadaBean.setStextVistaPreviaParrafo2(stextParrafo2TarjetaNoAfiliada);
			
			
			tnoafiliadaBean.setStextParrafo3(stextParrafo3TarjetaNoAfiliada);
			tnoafiliadaBean.setStextVistaPreviaParrafo3(stextParrafo3TarjetaNoAfiliada);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionxMarca ",e);
		}
		
	}
	
	
	@Action(value = "/cambiarConfiguracionMarcaTarjetaNoAfiliada", results = { 
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "input", type="tiles")
	})
	public String cambiarConfiguracionMarca(){
		try {

			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ cambiarConfiguracionMarca ----");
			
			setearConfiguracionxMarca(brandBean);
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ cambiarConfiguracionMarca ----");
			
		} catch (Exception e) {

			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR :  cambiarConfiguracionMarca ",e);
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	

	
	
	@Action(value = "/vistaResetTarjetaNoAfiliada", results = { 
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
			})	
	public String ResetTarjetaNoAfiliada(){
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ ResetTarjetaNoAfiliada ----");
			
			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			Integer IdEmisor = usuario.getIntIdIssuer();
			Utilitario.getLOG_APP().info("ID EMISOR: "+ IdEmisor);
			
			brandSelected =  brandBean.getIntIdBrand().toString();
			String brandName = Utilitario.getStringResourceBundle("MARCA."+brandSelected ).trim();
			
			Utilitario.getLOG_APP().info("Marca Seleccionada: " + brandName +" - "+ brandSelected );
			
			String defaultTarjetaBloqueadaParrafo1 = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO1."+brandName).trim();
			String defaultTarjetaBloqueadaParrafo2 = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO2."+brandName).trim();
			String defaultTarjetaBloqueadaParrafo3 = Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO3."+brandName).trim();

			Utilitario.getLOG_APP().info("Configuracion a reanudar :");
			Utilitario.getLOG_APP().info("defaultTarjetaBloqueadaParrafo1: "+defaultTarjetaBloqueadaParrafo1);
			Utilitario.getLOG_APP().info("defaultTarjetaBloqueadaParrafo2: "+defaultTarjetaBloqueadaParrafo2);
			Utilitario.getLOG_APP().info("defaultTarjetaBloqueadaParrafo3: "+defaultTarjetaBloqueadaParrafo3);
			
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			IssuerImplementation IssuerImpl= new IssuerImplementation(datasource);
			
			com.alignet.configurador.emisor.bean.IssuerBean emisor= new com.alignet.configurador.emisor.bean.IssuerBean();

			emisor.setIntIdIssuer(IdEmisor.toString());
			
			//----------
			
				tnoafiliadaBean.setSflagPoseeConfiguracion("true");
				
				tnoafiliadaBean.setStextVistaPreviaParrafo1(defaultTarjetaBloqueadaParrafo1);
				
				emisor.setSvcAdcText2(defaultTarjetaBloqueadaParrafo1);
				
				String textoParrafo1 = defaultTarjetaBloqueadaParrafo1.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").
																	   replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
				 textoParrafo1= IssuerImpl.setearTildeCadena(textoParrafo1, true);
				 tnoafiliadaBean.setStextParrafo1(textoParrafo1);
			
			 //----------
				
				tnoafiliadaBean.setStextVistaPreviaParrafo2(defaultTarjetaBloqueadaParrafo2);
				
				emisor.setSvcAdcText3(defaultTarjetaBloqueadaParrafo2);
				
				String textoParrafo2 = defaultTarjetaBloqueadaParrafo2.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").
																	   replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
				textoParrafo2= IssuerImpl.setearTildeCadena(textoParrafo2, true);
				tnoafiliadaBean.setStextParrafo2(textoParrafo2);
			 
			//----------
				
				tnoafiliadaBean.setStextVistaPreviaParrafo3(defaultTarjetaBloqueadaParrafo3);
				
				emisor.setSvcAdcText4(defaultTarjetaBloqueadaParrafo3);
				
				String textoParrafo3 = defaultTarjetaBloqueadaParrafo3.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").
																	   replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
				textoParrafo3= IssuerImpl.setearTildeCadena(textoParrafo3, true);
				tnoafiliadaBean.setStextParrafo3(textoParrafo3);
			 
			//----------
				
			if(IssuerImpl.registrarActualizarConfigTarjetaNoAfiliada(emisor)){
				
				Utilitario.getLOG_APP().info("Se actualizo correctamente la configuracion Tarjeta Bloqueada del emisor [ "+IdEmisor+" ]");

				addFieldError("mensajeSUCCESS", "Configuracion de Tarjeta No Afiliada reseteada correctamente");
				
			}else{
				Utilitario.getLOG_APP().error("Error al reanudar configuracion del emisor, verificar logs de BD");
				addFieldError("mensajeERROR", " Error al reanudar configuracion, intente nuevamente");
				return INPUT;
			}
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ ResetTarjetaNoAfiliada ----");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  registrarConfigTarjetaNoAfiliada ",e);
			addFieldError("mensajeERROR", " Error al reanudar configuración, intente nuevamente");
			return ERROR;	
		}
		return SUCCESS;
	}
	
	
	
	@Action(value = "/registrarConfigTarjetaNoAfiliada", results = { 
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaNoAfiliada", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
			})	
	public String registrarConfigTarjetaNoAfiliada(){
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarConfigTarjetaNoAfiliada ----");
			
			UserBean usuario =(UserBean)sessionMapTarjetaNoAfiliada.get("ObjetoUsuario");
			Integer IdEmisor = usuario.getIntIdIssuer();
			Utilitario.getLOG_APP().info("ID Emisor: "+ IdEmisor);
			
			brandSelected =  brandBean.getIntIdBrand().toString();
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+ brandSelected + " "+Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected ) );
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			
			IssuerImplementation IssuerImpl= new IssuerImplementation(datasource);
			
			com.alignet.configurador.emisor.bean.IssuerBean emisor= new com.alignet.configurador.emisor.bean.IssuerBean();

			emisor.setIntIdIssuer(IdEmisor.toString());
			
			
			String textoParrafo1= IssuerImpl.setearTildeCadena(tnoafiliadaBean.getStextParrafo1(), false);
			textoParrafo1 = Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() +tnoafiliadaBean.getStextParrafo1().trim() + Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim();
			emisor.setSvcAdcText2(textoParrafo1);
			
			
			String textoParrafo2= IssuerImpl.setearTildeCadena(tnoafiliadaBean.getStextParrafo2(), false);
			textoParrafo2 = Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() +tnoafiliadaBean.getStextParrafo2().trim() + Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim();
			emisor.setSvcAdcText3(textoParrafo2);
	
			
			String textoParrafo3 =  IssuerImpl.setearTildeCadena(tnoafiliadaBean.getStextParrafo3(), false);
			textoParrafo3 = Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() +tnoafiliadaBean.getStextParrafo3().trim() + Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim();
			emisor.setSvcAdcText4(textoParrafo3);
			
			if(IssuerImpl.registrarActualizarConfigTarjetaNoAfiliada(emisor)){
				
				Utilitario.getLOG_APP().info("Se actualizo correctamente la configuracion Tarjeta Bloqueada del emisor [ "+IdEmisor+" ]");
				
				tnoafiliadaBean.setSflagPoseeConfiguracion("true");
				tnoafiliadaBean.setStextVistaPreviaParrafo1(tnoafiliadaBean.getStextParrafo1());
				tnoafiliadaBean.setStextVistaPreviaParrafo2(tnoafiliadaBean.getStextParrafo2());
				tnoafiliadaBean.setStextVistaPreviaParrafo3(tnoafiliadaBean.getStextParrafo3());
	
				addFieldError("mensajeSUCCESS", " Configuracion de Tarjeta No Afiliada correctamente");
				
			}else{
				Utilitario.getLOG_APP().error("Error al registrar/actualizar configuracion del emisor [ "+IdEmisor+" ], verificar logs de BD");
				addFieldError("mensajeERROR", " Error al guardas configuracion, intente nuevamente");
				return INPUT;
			}
			
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarConfigTarjetaNoAfiliada ----");

		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  registrarConfigTarjetaNoAfiliada ",e);
			return ERROR;	
		}
		return SUCCESS;
	}
	
//	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMapTarjetaNoAfiliada = sessionMap;
	}

	
	
	public Map<String, Object> getSessionMapTarjetaNoAfiliada() {
		return sessionMapTarjetaNoAfiliada;
	}

	public void setSessionMapTarjetaNoAfiliada(Map<String, Object> sessionMapTarjetaNoAfiliada) {
		this.sessionMapTarjetaNoAfiliada = sessionMapTarjetaNoAfiliada;
	}



	public TarjetaNoAfiliadaBean getTnoafiliadaBean() {
		return tnoafiliadaBean;
	}



	public void setTnoafiliadaBean(TarjetaNoAfiliadaBean tnoafiliadaBean) {
		this.tnoafiliadaBean = tnoafiliadaBean;
	}



	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaNoAfiliada.get("listBrandHabilitadasEmisor");
		return listBrandHabilitadasEmisor;
	}



	public void setListBrandHabilitadasEmisor(ArrayList<BrandBean> listBrandHabilitadasEmisor) {
		this.listBrandHabilitadasEmisor = listBrandHabilitadasEmisor;
	}



	public String getBrandSelected() {
		return brandSelected;
	}



	public void setBrandSelected(String brandSelected) {
		this.brandSelected = brandSelected;
	}


	public String getOperacionValidate() {
		return operacionValidate;
	}


	public void setOperacionValidate(String operacionValidate) {
		this.operacionValidate = operacionValidate;
	}


	public BrandBean getBrandBean() {
		return brandBean;
	}


	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

}
