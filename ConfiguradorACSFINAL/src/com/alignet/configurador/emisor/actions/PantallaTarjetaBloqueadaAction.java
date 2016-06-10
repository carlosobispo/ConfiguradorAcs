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

import com.alignet.configurador.emisor.bean.TarjetaBloqueadaBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.DataConfigImplementation;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class PantallaTarjetaBloqueadaAction extends ActionSupport implements SessionAware{
	
	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
	
	private BrandBean  brandBean;
	private TarjetaBloqueadaBean tbloqueadaBean;
	private Map<String, Object> sessionMapTarjetaBloqueada;
	
	private String operacionValidate;
	private String brandSelected;
	
	
	private static final long serialVersionUID = 1L;

	
	public void validate(){
		
		try {
			Utilitario.getLOG_APP().info(" Inicio de validacion   validate");
			Utilitario.getLOG_APP().info(" Operacion a realizar: "+ operacionValidate);
			if(operacionValidate.equals("validateCambiarConfiguracionMarca")){
				
				validateCambiarMarca(brandBean.getIntIdBrand());
				
			}else if(operacionValidate.equals("validateConfiguracionTarjetaBloqueada")){
				
					if(validateCambiarMarca(brandBean.getIntIdBrand())){
						
						validateConfiguracionTarjetaBloqueada();
						brandSelected = brandBean.getIntIdBrand().toString();
					}
			
			}

			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validate PantallaTarjetaBloqueadaAction ",e);
		}
		
	}
	
	
	@SkipValidation
	@Action(value = "/irConfigPantallaTarjetaBloqueada", results = { 
			@Result (location = "t_pantallaTarjetaBloqueada", name = "success", type="tiles"),
			@Result (location = "t_errorGeneral", name = "input", type="tiles")
			})	
	public String irConfigPantallaTarjetaBloqueada(){
		try {
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : PANTALLA TARJETA BLOQUEADA");
			
			setearConfiguracionXMarca(brandBean);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  irConfigPantallaTarjetaBloqueada ",e);
			return INPUT;
		}
		
		return SUCCESS;
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
			Utilitario.getLOG_APP().error("ERROR :  validateCambiarMarca ",e);
		}
		return isValido;
	}
	
	
	@Action(value = "/cambiarConfiguracionMarcaTarjetaBloqueada", results = { 
			@Result (location = "t_pantallaTarjetaBloqueada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaBloqueada", name = "input", type="tiles")
	})
	public String cambiarConfiguracionMarca(){
		
		Utilitario.getLOG_APP().info("INICIO PantallaTarjetaBloqueadaAction: cambiarConfiguracionMarca");
		try {
			
			setearConfiguracionXMarca(brandBean);

		} catch (Exception e) {
			
			addFieldError("mensajeERROR", " Ocurrio un error, Intente mas tarde ");
			Utilitario.getLOG_APP().error("ERROR :  cambiarConfiguracionMarca ",e);
			return INPUT;
			
		}
		Utilitario.getLOG_APP().info("FIN PantallaTarjetaBloqueadaAction: cambiarConfiguracionMarca");
		return SUCCESS;
	}
	
	
	public void setearConfiguracionXMarca(BrandBean marca){
		try {
			
			UserBean usuario =(UserBean)sessionMapTarjetaBloqueada.get("ObjetoUsuario");
			listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaBloqueada.get("listBrandHabilitadasEmisor");
			
			Utilitario.getLOG_APP().info("Total de marcas asignadas al emisor [ "+ usuario.getIntIdIssuer()+" ] : "+ listBrandHabilitadasEmisor.size());
			
			if(marca!=null && !Utilitario.isVacioOrNull(marca.getIntIdBrand()))
				brandSelected=  brandBean.getIntIdBrand().toString();
			else
				brandSelected = listBrandHabilitadasEmisor.get(0).getIntIdBrand().toString();
			
				Utilitario.getLOG_APP().info("Marca seleccionada :" + brandSelected);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info("nombreMarca "+nombreMarca);
			
			
			tbloqueadaBean = new TarjetaBloqueadaBean();
			
			DataConfigImplementation dataconfigImpl = new DataConfigImplementation(datasource);
			List<DataConfigBean> listDataConfig = dataconfigImpl.getListDataConfigForIdEmisor(usuario.getIntIdIssuer());
			
			
			if( listDataConfig!=null && listDataConfig.size()>0){

					tbloqueadaBean = setearTarjetaBloqueadaBeanXDataConfig(dataconfigImpl, listDataConfig, nombreMarca);
				
			}else{
				tbloqueadaBean = setearTarjetaBloqueadaBeanXDefault();
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  setearConfiguracionXMarca ",e);
			
		}
	}
	
	
	public TarjetaBloqueadaBean setearTarjetaBloqueadaBeanXDataConfig(DataConfigImplementation dataconfigImpl, List<DataConfigBean> listDataConfig , String nombreMarca ) throws Exception{
		
		Utilitario.getLOG_APP().info("Total de Configuraciones obtenidas : "+ listDataConfig.size());
		TarjetaBloqueadaBean tbloqueadaBean = new  TarjetaBloqueadaBean();
		
		String sflagHAbilitarParrafo1 = dataconfigImpl.getValueforKeyName(listDataConfig, "paut.show.pan.blocked1");
		String sflagHAbilitarParrafo2 = dataconfigImpl.getValueforKeyName(listDataConfig, "paut.show.pan.blocked2");
		
		sflagHAbilitarParrafo1 = sflagHAbilitarParrafo1.equals("true") ? "true" : "false";
		sflagHAbilitarParrafo2 = sflagHAbilitarParrafo2.equals("true") ? "true" : "false";
		
		
		String stextParrafo1 = sflagHAbilitarParrafo1.equals("true") ? dataconfigImpl.getValueforKeyName(listDataConfig, "msg.pan.blocked1"): "";
		String stextParrafo2 = sflagHAbilitarParrafo2.equals("true") ? dataconfigImpl.getValueforKeyName(listDataConfig, "msg.pan.blocked2"): "";

		
		
		if( ( sflagHAbilitarParrafo1.equals("false") &&  sflagHAbilitarParrafo2.equals("false") ) || ( stextParrafo1.equals("") && stextParrafo2.equals("")  ) )
			tbloqueadaBean.setSflagPoseeConfiguracion("false");
		else
			tbloqueadaBean.setSflagPoseeConfiguracion("true");
	
		 
		Utilitario.getLOG_APP().info("Posee alguna configuracion Tarjeta Bloqueada : "+ tbloqueadaBean.getSflagPoseeConfiguracion());
		
				if(tbloqueadaBean.getSflagPoseeConfiguracion().equals("true")){
		
					stextParrafo1 = stextParrafo1.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
					stextParrafo2 = stextParrafo2.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
					
					stextParrafo1 = dataconfigImpl.setearTildeCadena(stextParrafo1,true);
					stextParrafo2 = dataconfigImpl.setearTildeCadena(stextParrafo2,true);
					
					
					tbloqueadaBean.setSflagParrafo1(sflagHAbilitarParrafo1);
					tbloqueadaBean.setSflagParrafo2(sflagHAbilitarParrafo2);
					tbloqueadaBean.setSflagVistaPreviaParrafo1(sflagHAbilitarParrafo1);
					tbloqueadaBean.setSflagVistaPreviaParrafo2(sflagHAbilitarParrafo2);
					
					
					tbloqueadaBean.setStextParrafo1(stextParrafo1);
					tbloqueadaBean.setStextVistaPreviaParrafo1(stextParrafo1);
					tbloqueadaBean.setStextParrafo2(stextParrafo2);
					tbloqueadaBean.setStextVistaPreviaParrafo2(stextParrafo2);
					
				}
		

		
		return tbloqueadaBean;
	}
	
	
	public TarjetaBloqueadaBean setearTarjetaBloqueadaBeanXDefault() throws Exception{
		
		TarjetaBloqueadaBean tbloqueadaBean = new  TarjetaBloqueadaBean();
		
		Utilitario.getLOG_APP().info("Total de Configuraciones obtenidas : 0");
		Utilitario.getLOG_APP().info("El emisor no tiene ninguna configuracion para la marca");
		
		tbloqueadaBean.setSflagParrafo1("false");
		tbloqueadaBean.setSflagParrafo2("false");
		tbloqueadaBean.setStextParrafo1("");
		tbloqueadaBean.setStextVistaPreviaParrafo1("");
		tbloqueadaBean.setStextVistaPreviaParrafo2("");
		tbloqueadaBean.setStextParrafo2("");
		tbloqueadaBean.setSflagPoseeConfiguracion("false");
		
		return tbloqueadaBean;
	}
	
	public void validateConfiguracionTarjetaBloqueada(){
		
		try {
			if(!Utilitario.isValidoCheck(tbloqueadaBean.getSflagParrafo1())){
				Utilitario.getLOG_APP().warn("EL check del parrafo1 es invalido");
				addFieldError("mensajeERROR", "Favor de verificar el check del Parrafo1");
			}
			else if(!Utilitario.isValidoCheck(tbloqueadaBean.getSflagParrafo1())){
				Utilitario.getLOG_APP().warn("EL check del parrafo2 es invalido");
				addFieldError("mensajeERROR", "Favor de verificar el check del Parrafo2");
			}
			else if(Utilitario.isVacioOrNull(tbloqueadaBean.getSflagParrafo1())){
				
				Utilitario.getLOG_APP().warn("tbloqueadaBean.getSflagParrafo1() es NULL o vacio");
				addFieldError("mensajeERROR", "Favor de verificar la configuracion para el Parrafo1");
			}
			else if(Utilitario.isVacioOrNull(tbloqueadaBean.getSflagParrafo2())){
				Utilitario.getLOG_APP().warn("tbloqueadaBean.getSflagParrafo2() es NULL o vacio");
				addFieldError("mensajeERROR", "Favor de verificar la configuracion para el Parrafo2");
			}
			else if(tbloqueadaBean.getSflagParrafo1().equals("false") && tbloqueadaBean.getSflagParrafo2().equals("false") ){
				Utilitario.getLOG_APP().warn("Los dos parrafos estan deshabilitados. " + tbloqueadaBean.getStextVistaPreviaParrafo1());
				Utilitario.getLOG_APP().warn("Los dos parrafos estan deshabilitados. " + tbloqueadaBean.getStextVistaPreviaParrafo2());
				
				addFieldError("mensajeERROR", "Favor de seleccionar al menos un parrafo");
			}
			else if(tbloqueadaBean.getSflagParrafo1().equals("true") && Utilitario.isVacioOrNull( tbloqueadaBean.getStextParrafo1()) ){
				Utilitario.getLOG_APP().warn("Se habilita el parafo1, pero el valor del texto es vacio o  null");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo1");
			}
			else if( tbloqueadaBean.getSflagParrafo1().equals("true") && tbloqueadaBean.getStextParrafo1().length()> 250){
				Utilitario.getLOG_APP().warn("Se habilita el parafo1, pero la longitud del Parrafo1 es mayor a 250");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo1");
			}
			else if(tbloqueadaBean.getSflagParrafo2().equals("true") && Utilitario.isVacioOrNull( tbloqueadaBean.getStextParrafo2()) ){
				Utilitario.getLOG_APP().warn("Se habilita el parafo2, pero el valor del texto es vacio o  null");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo2");
			}
			else if( tbloqueadaBean.getSflagParrafo2().equals("true") && tbloqueadaBean.getStextParrafo2().length()> 160){
				Utilitario.getLOG_APP().warn("Se habilita el parafo2, pero la longitud del Parrafo1 es mayor a 160");
				addFieldError("mensajeERROR", "Favor de verificar el texto del Parrafo2");
			}else{
				String REG_EXP = "\\¿+|\\?+|\\°+|\\¬+|\\|+|\\!+|\\#+|\\$+|\\%+|\\&+|\\+|\\=+|\\’+|\\¡+|\\++|\\*+|\\~+|\\[+|\\]+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+";
				Pattern  pattern = Pattern.compile(REG_EXP);
				
				if(tbloqueadaBean.getSflagParrafo1().equals("true")){
					
					Matcher matcher1 = pattern.matcher(tbloqueadaBean.getStextParrafo1());
					
					if(matcher1.find()){
						Utilitario.getLOG_APP().warn("El formato del parrafo1 es incorrecto : "+ tbloqueadaBean.getStextParrafo1());
						addFieldError("mensajeERROR", "Favor de verificar el formato del parrafo1");
						
					}else {
						Utilitario.getLOG_APP().warn("El formato del parrafo1 es correcto ");
						
					}
				
				}
				
				
				if(tbloqueadaBean.getSflagParrafo2().equals("true")){
					
					Matcher matcher2 = pattern.matcher(tbloqueadaBean.getStextParrafo2());
					
					if( matcher2.find() ){
						Utilitario.getLOG_APP().warn("El formato del parrafo2 es incorrecto : "+ tbloqueadaBean.getStextParrafo1());
						addFieldError("mensajeERROR", "Favor de verificar el formato del parrafo2");
						
						
					}else{
						Utilitario.getLOG_APP().warn("El formato del parrafo2 es correcto ");
						
					} 
				}

				Utilitario.getLOG_APP().warn("Todos los campos son correctos");
				
			}

		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR :  validateConfiguracionTarjetaBloqueada ",e);
		}
	}

	
	
	
	//vistaResetTarjetaBloqueada
	
	@Action(value = "/vistaResetTarjetaBloqueada", results = { 
			@Result (location = "t_pantallaTarjetaBloqueada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaBloqueada", name = "input", type="tiles")
			})	
	public String ResetTarjetaBloqueada(){

		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ ResetTarjetaBloqueada ----");
			
			UserBean usuario =(UserBean)sessionMapTarjetaBloqueada.get("ObjetoUsuario");
			Integer IdEmisor = usuario.getIntIdIssuer();
			
			Utilitario.getLOG_APP().info(" ID emisor: " +IdEmisor);
			
			brandSelected =  brandBean.getIntIdBrand().toString();
			String brandName = Utilitario.getStringResourceBundle("MARCA."+brandSelected ).toString(); 
			
			Utilitario.getLOG_APP().info("Marca Seleccionada: "+brandName + " ID: " +brandSelected );

			Utilitario.getLOG_APP().info("Se obtiene la configuracion a setear por defecto: ");
			
			String defaultParrafo1 =  Utilitario.getStringResourceBundle("DEFAULT.TARJETA.BLOQUEADA.PARRAFO1."+brandName ).trim();
			String defaultParrafo2 =  Utilitario.getStringResourceBundle("DEFAULT.TARJETA.BLOQUEADA.PARRAFO2."+brandName ).trim();
			Utilitario.getLOG_APP().info("msg.pan.blocked1: "+ defaultParrafo1);
			Utilitario.getLOG_APP().info("msg.pan.blocked2: "+ defaultParrafo2);
			
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			
			tbloqueadaBean.setSflagPoseeConfiguracion("true");
			
			
			//--------
			
			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"paut.show.pan.blocked1");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"true","flag para mostrar parrafo 1 del popupBlockeOTP");
			ListDataconfig.add(DataConfigHibernate);
			tbloqueadaBean.setSflagParrafo1("true");
			tbloqueadaBean.setSflagVistaPreviaParrafo1("true");
			
			
			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"msg.pan.blocked1");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaultParrafo1 ,"Parrafo 1 del mensaje informativo para tarjetas bloqueadas");
			ListDataconfig.add(DataConfigHibernate);
			tbloqueadaBean.setStextVistaPreviaParrafo1(defaultParrafo1);
			
			String textoParrafo1 = defaultParrafo1.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
			
			textoParrafo1 = dataconfigImpl.setearTildeCadena(textoParrafo1, true);
			tbloqueadaBean.setStextParrafo1(textoParrafo1);
			
			//------
			
			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"paut.show.pan.blocked2");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"true","flag para mostrar parrafo 2 del popupBlockeOTP");
			ListDataconfig.add(DataConfigHibernate);
			tbloqueadaBean.setSflagParrafo2("true");
			tbloqueadaBean.setSflagVistaPreviaParrafo2("true");
			
			
			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"msg.pan.blocked2");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, defaultParrafo2 ,"Parrafo 2 del mensaje informativo para tarjetas bloqueadas");
			ListDataconfig.add(DataConfigHibernate);
			tbloqueadaBean.setStextVistaPreviaParrafo2(defaultParrafo2);
			String textoParrafo2 = defaultParrafo2.replaceAll(Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() , "").replaceAll(Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim(), "");
			
			textoParrafo2 = dataconfigImpl.setearTildeCadena(textoParrafo2, true);
			tbloqueadaBean.setStextParrafo2(textoParrafo2);
			
			//-----

			if( dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				
				Utilitario.getLOG_APP().info(" Configuracion de Tarjeta Bloqueada reseteada correctamente");
				addFieldError("mensajeSUCCESS", " Configuracion de Tarjeta Bloqueada reseteada correctamente");
				
			}else{
				
				Utilitario.getLOG_APP().error("Error al reanudar configuracion del emisor [ "+IdEmisor+" ], verificar logs de BD");
				addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
				return INPUT;
				
			}

			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ ResetTarjetaBloqueada ----");

		} catch (Exception e) {
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
			Utilitario.getLOG_APP().error("ERROR validate PantallaTarjetaBloqueadaAction ",e);
		}
		
		return SUCCESS;
	}
	
	
	@Action(value = "/registrarConfigTarjetaBloqueada", results = { 
			@Result (location = "t_pantallaTarjetaBloqueada", name = "success", type="tiles"),
			@Result (location = "t_pantallaTarjetaBloqueada", name = "input", type="tiles"),
			@Result (location = "t_errorGeneral", name = "error", type="tiles")
			})	
	public String registrarConfigTarjetaBloqueada(){
		try {
			
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ registrarConfigTarjetaBloqueada ----");
			
			UserBean usuario =(UserBean)sessionMapTarjetaBloqueada.get("ObjetoUsuario");
			
			Integer IdEmisor = usuario.getIntIdIssuer();
			
			//listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaBloqueada.get("listBrandHabilitadasEmisor");
			
			brandSelected =  brandBean.getIntIdBrand().toString();
			
			Utilitario.getLOG_APP().info("Marca Seleccionada "+brandSelected);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected )  ) ;
			
			DataConfigImplementation  dataconfigImpl = new DataConfigImplementation(datasource);
			ArrayList<DataConfigBean> ListDataconfig = new ArrayList<DataConfigBean>();
			DataConfigBean DataConfigHibernate=null;
			DataConfigBean.Id DConfigIDHibernate =null;
			 
			

			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"paut.show.pan.blocked1");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,tbloqueadaBean.getSflagParrafo1(),"flag para mostrar parrafo 1 del popupBlockeOTP");
			ListDataconfig.add(DataConfigHibernate);
			
			if(tbloqueadaBean.getSflagParrafo1().equals("true")){
				Utilitario.getLOG_APP().info("Se habilita el parrafo1: "+ tbloqueadaBean.getSflagParrafo1());

				String textoParrafo1 = dataconfigImpl.setearTildeCadena(tbloqueadaBean.getStextParrafo1(), false);
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"msg.pan.blocked1");
				
				textoParrafo1 = Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() +textoParrafo1 + Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim();
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, textoParrafo1,"Parrafo 1 del mensaje informativo para tarjetas bloqueadas");
				ListDataconfig.add(DataConfigHibernate);

				tbloqueadaBean.setSflagVistaPreviaParrafo1("true");
				
			}
			
			
			
			DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"paut.show.pan.blocked2");
			DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,tbloqueadaBean.getSflagParrafo2(),"flag para mostrar parrafo 2 del popupBlockeOTP");
			ListDataconfig.add(DataConfigHibernate);
			
			if(tbloqueadaBean.getSflagParrafo2().equals("true")){
				
				Utilitario.getLOG_APP().info("Se habilita el parrafo2: "+ tbloqueadaBean.getSflagParrafo2());
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"msg.pan.blocked2");
				
				String textoParrafo2= dataconfigImpl.setearTildeCadena(tbloqueadaBean.getStextParrafo2(), false);
				textoParrafo2 = Utilitario.getStringResourceBundle("CARACTER.INICIO.TEXTO").trim() +tbloqueadaBean.getStextParrafo2().trim() + Utilitario.getStringResourceBundle("CARACTER.FIN.TEXTO").trim();
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate, textoParrafo2,"Párrafo 2 del mensaje informativo para tarjetas bloqueadas");
				ListDataconfig.add(DataConfigHibernate);
				
				tbloqueadaBean.setSflagVistaPreviaParrafo2("true");
			}
			
			
			
			if( dataconfigImpl.actualizarDataConfig(ListDataconfig)){
				Utilitario.getLOG_APP().info("Se actualizo correctamente la configuracion Tarjeta Bloqueada del emisor [ "+IdEmisor+" ]");
				
				tbloqueadaBean.setSflagPoseeConfiguracion("true");
				tbloqueadaBean.setStextVistaPreviaParrafo1(tbloqueadaBean.getStextParrafo1());
				tbloqueadaBean.setStextVistaPreviaParrafo2(tbloqueadaBean.getStextParrafo2());	
				
				addFieldError("mensajeSUCCESS", " Configuracion de Tarjeta Bloqueada correctamente");
				
			}else{
				Utilitario.getLOG_APP().error("Error al registrar/actualizar configuracion del emisor [ "+IdEmisor+" ], verificar logs de BD");
				return ERROR;
			}

			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ registrarConfigTarjetaBloqueada ----");
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR :  registrarConfigTarjetaBloqueada ",e);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
//	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMapTarjetaBloqueada = sessionMap;
	}


	public TarjetaBloqueadaBean getTbloqueadaBean() {
		return tbloqueadaBean;
	}


	public void setTbloqueadaBean(TarjetaBloqueadaBean tbloqueadaBean) {
		this.tbloqueadaBean = tbloqueadaBean;
	}


	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapTarjetaBloqueada.get("listBrandHabilitadasEmisor");
		return listBrandHabilitadasEmisor;
	}


	public void setListBrandHabilitadasEmisor(ArrayList<BrandBean> listBrandHabilitadasEmisor) {
		this.listBrandHabilitadasEmisor = listBrandHabilitadasEmisor;
	}


	public Map<String, Object> getSessionMapTarjetaBloqueada() {
		return sessionMapTarjetaBloqueada;
	}


	public void setSessionMapTarjetaBloqueada(Map<String, Object> sessionMapTarjetaBloqueada) {
		this.sessionMapTarjetaBloqueada = sessionMapTarjetaBloqueada;
	}


	public BrandBean getBrandBean() {
		return brandBean;
	}


	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
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





}
