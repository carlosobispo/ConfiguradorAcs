package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alignet.configurador.emisor.bean.IssuerBean;
import com.alignet.configurador.emisor.factory.DAOFactory;
import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class DataConfigImplementation implements DataConfigInterface,Serializable{

	private static final long serialVersionUID = 1L;
	DAOFactory factory =null;
	DataConfigInterface daoDataConfig = null;
	
	public DataConfigImplementation(Integer datasource){
		factory = DAOFactory.getFactory(datasource);
		daoDataConfig =factory.getDataConfigServices();
	}
	
	
	//@Override
	public boolean registrarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoRegistro = false;
		try {
			isValidoRegistro= daoDataConfig.registrarDataConfig(ListdataConfing);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigImplementation - registrarDataConfig error al registrar DataConfig ", e);
		}
		
		return isValidoRegistro;
	}

	//@Override
	public List<DataConfigBean> getListDataConfigForIdEmisor(Integer idEmisor)
			throws Exception {
		List<DataConfigBean> list= null;
		try {
			list = daoDataConfig.getListDataConfigForIdEmisor(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigImplementation - getListDataConfigForIdEmisor error al obtener list DataConfig para el emisor " + idEmisor, e);
		}
		
		return list;
	}


	//@Override
	public boolean actualizarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoActualizacion = false;
		try {
			isValidoActualizacion= daoDataConfig.actualizarDataConfig(ListdataConfing);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigImplementation - actualizarDataConfig error al actualizar DataConfig ", e);
			}
		return isValidoActualizacion;
	}
	
	
	public ArrayList<DataConfigBean> listDataConfigRegistrar(IssuerBean emisor){
		ArrayList<DataConfigBean> ListDataconfig = null;
		DataConfigBean DataConfigHibernate=null;
		DataConfigBean.Id DConfigIDHibernate =null;
		
		try {
				Integer IdEmisor = Integer.parseInt(emisor.getIntIdIssuer()) ;
				ListDataconfig =  new ArrayList<DataConfigBean>();
				
				//--
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"issuer.class.impl");
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSclaseimplementacion().trim(),"Clase que implementa lógica para cada emisor");
				ListDataconfig.add(DataConfigHibernate);
				
				//---
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"issuer.attempt");
				String FlagAttend = emisor.getSflagAttemps()!=null && emisor.getSflagAttemps().equals("true") ? "true" : "false"; 
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,FlagAttend,"Indicador para Emisor que utiliza intentos de compra");
				ListDataconfig.add(DataConfigHibernate);				
				
				//-- 
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"acs.noenrolcard");
				String FlagPantallaTarjetaNoAfiliada = emisor.getSflagPantallaTarjetaNoAfiliada()!=null && emisor.getSflagPantallaTarjetaNoAfiliada().equals("true") ? "true" : "false"; 
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,FlagPantallaTarjetaNoAfiliada,"Ventana informativa para pan no enrolada");
				ListDataconfig.add(DataConfigHibernate);	
				
				//--
				String FlagHabilitarSMS = emisor.getSflagHabilitarSMS()!=null && emisor.getSflagHabilitarSMS().equals("true") ? "true" : "false"; 
				if(FlagHabilitarSMS.equals("true")){

					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"sms.carrier.name");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSnameCarrier().trim(),"Nombre del carrier que usa el emisor");
					ListDataconfig.add(DataConfigHibernate);
						
					
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"carrier.class.impl");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getScarrierClassImpl().trim(),"Clase que implementa la conexión con el SMSSend");
					ListDataconfig.add(DataConfigHibernate);	
				}
				
				String FlagHabilitarEmail = emisor.getSflagHabilitarEmail()!=null && emisor.getSflagHabilitarEmail().equals("true") ? "true" : "false"; 
				DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"send.email.toAuthenticate");
				DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,FlagHabilitarEmail,"Parámetro que indica si el emisor envía SecureKey al email del TH");
				ListDataconfig.add(DataConfigHibernate);				
				
				if(FlagHabilitarEmail.equals("true")){
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"mail.host");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSnameHost().trim(),"Host del proveedor de email");
					ListDataconfig.add(DataConfigHibernate);
					
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"mail.port");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSpuerto().trim(),"Puerto habilitado para envío de email");
					ListDataconfig.add(DataConfigHibernate);
					
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"mail.user");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSusuarioEmail().trim(),"Usuario para envío de email");
					ListDataconfig.add(DataConfigHibernate);
					
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"mail.password");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,emisor.getSclaveEmail().trim(),"Contraseña del usuario para envío de email");
					ListDataconfig.add(DataConfigHibernate);
					
				}	
				
				if(FlagHabilitarSMS.equals("true") || FlagHabilitarEmail.equals("true")){
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"issuer.skey");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"true","Indicador de que el Emisor utiliza SecureKey");
					ListDataconfig.add(DataConfigHibernate);
				}
				else{
					DConfigIDHibernate  = new DataConfigBean.Id(IdEmisor,"issuer.skey");
					DataConfigHibernate = new DataConfigBean(DConfigIDHibernate,"false","Indicador de que el Emisor utiliza SecureKey");
					ListDataconfig.add(DataConfigHibernate);
				}
				
				
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR : listDataConfigRegistrar ",e);
		}
		return ListDataconfig;
	}
	
	
	
	public String getValueforKeyName(List<DataConfigBean> listDataConfig, String keyName ){
		
		try {
			
			
			for (DataConfigBean dataConfigBean : listDataConfig) {
				
				if(dataConfigBean.getId().getSvcKeyname().equals(keyName)){
					return dataConfigBean.getSvcValue();
				}
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigImplementation - getValueforKeyName ", e);
			
		}

		return "";
	}
	
	
	public  String setearTildeCadena(String cadena, boolean mostrarTildeEnCajaTexto){
		try {
			 String seteoTildes[][]={  {"&aacute;","á"}, {"&eacute;","é"},{"&iacute;","í"},{"&oacute;","ó"}, {"&uacute;","ú"} ,
					 				   {"&Aacute;","Á"}, {"&Eacute;","É"},{"&Iacute;","Í"},{"&Oacute;","Ó"}, {"&Uacute;","Ú"} ,{"&ntilde;","ñ"},{"&Ntilde;","Ñ"}};
			
			for (int i = 0; i < seteoTildes.length; i++) {
				
				if(mostrarTildeEnCajaTexto)
					cadena = cadena.replaceAll(seteoTildes[i][0], seteoTildes[i][1]);
				else 
					cadena = cadena.replaceAll(seteoTildes[i][1], seteoTildes[i][0]);
			}
			return cadena;
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigImplementation - getValueforKeyName ", e);
		}
		return "";
	}
	

	public  String CelularDigitoNoEnmascarados(String numDigitoNoEnmascarados){
		String DigitoCelularNoEnmascarados="";
		try {
			String defaultCelular=Utilitario.getStringResourceBundle("DEFAULT.CELULAR").trim();
			Integer digitosNoEnmascarados= Integer.parseInt(numDigitoNoEnmascarados);
			
			 DigitoCelularNoEnmascarados= defaultCelular.substring(defaultCelular.length()-digitosNoEnmascarados ,defaultCelular.length() );
			
			while (DigitoCelularNoEnmascarados.length() <defaultCelular.length()) {
				DigitoCelularNoEnmascarados="*"+DigitoCelularNoEnmascarados;
			}
			
		} catch (Exception e) {
			System.out.println("ERROR");
			Utilitario.getLOG_APP().error("ERROR : PantallaAutenticacionClaveIncorrecta - DigitoNoEnmascarados ",e);
		}
		return DigitoCelularNoEnmascarados;
	}
	
	
	/*
	 private static final String ORIGINAL
        = "ÁáÉéÍíÓóÚúÑñÜü";
private static final String REPLACEMENT
        = "AaEeIiOoUuNnUu";
public static String stripAccents(String str) {
    if (str == null) {
        return null;
    }
    char[] array = str.toCharArray();
    for (int index = 0; index < array.length; index++) {
        int pos = ORIGINAL.indexOf(array[index]);
        if (pos > -1) {
            array[index] = REPLACEMENT.charAt(pos);
        }
    }
    return new String(array);
} 
	 * */
	
	
	
}
