package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;
import java.util.List;

import com.alignet.configurador.emisor.factory.DAOFactory;
import com.alignet.configurador.emisor.hibernate.bean.IssuerBean;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class IssuerImplementation implements IssuerInterface,Serializable{

	private static final long serialVersionUID = 1L;
	DAOFactory factory = null;
	IssuerInterface daoIssuer = null;
	
	public IssuerImplementation(Integer datasource){
		factory   = DAOFactory.getFactory(datasource);
		daoIssuer = factory.getIssuerServices();
	}
	

	
	//@Override
	public List<IssuerBean> listaIssuer() throws Exception {
		List<IssuerBean> listIssuer = null;
		
		try {
			listIssuer = (List<IssuerBean>) daoIssuer.listaIssuer();
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - listaIssuer2 error al obtener lista marcas ", e);
		}
		return listIssuer;
	}

	//@Override
	public List<IssuerBean> listIssuerxIdOrName(String idEmisor,
			String vsName) throws Exception {
		List<IssuerBean> listIssuer = null;
		try {
			listIssuer = (List<IssuerBean>) daoIssuer.listIssuerxIdOrName( idEmisor,  vsName);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - listIssuerxIdOrName error al obtener lista marcas ", e);
		}
		
		return listIssuer;
	}

	//@Override
	public boolean existeRegistradoIdEmisor(String idEmisor) throws Exception {
		boolean isDisponibleIdEmisor = false;
		try {
			isDisponibleIdEmisor = daoIssuer.existeRegistradoIdEmisor(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - isDisponibleIdEmisor error al obtener Disponibilidad Emisor ", e);
		}
		return isDisponibleIdEmisor;
	}


	//@Override
	public boolean registrarEmisor(IssuerBean emisor) throws Exception {
		boolean isValidoRegistro = false;
		try {
			isValidoRegistro = daoIssuer.registrarEmisor(emisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - registrarEmisor error al registrar Emisor ", e);
		}
		
		return isValidoRegistro;
	}



	//@Override
	public IssuerBean getEmisorforId(Integer idEmisor) throws Exception {
		IssuerBean beanIssuer=null;
		try {
			beanIssuer = daoIssuer.getEmisorforId(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - getEmisorforId error al obtener Emisor "+idEmisor , e);
		}
		return beanIssuer;
	}



	//@Override
	public boolean actualizarEmisor(com.alignet.configurador.emisor.bean.IssuerBean emisor) throws Exception {
		boolean isValidoActualizacion = false;
		try {
			isValidoActualizacion = daoIssuer.actualizarEmisor(emisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - actualizarEmisor error al Actualizar Emisor ", e);
		}
		return isValidoActualizacion;
	}


	//@Override
	public boolean registrarActualizarConfigTarjetaNoAfiliada(com.alignet.configurador.emisor.bean.IssuerBean emisor)
			throws Exception {
		boolean isValidoActualizacion = false;
		try {
			isValidoActualizacion = daoIssuer.registrarActualizarConfigTarjetaNoAfiliada(emisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - actualizarEmisor error al Actualizar Emisor ", e);
		}
		return isValidoActualizacion;
	}
	
	
	//@Override
	public boolean actualizarDocBase(Integer idEmisor, String sDocBaseActualizar) throws Exception {
		boolean isValidoActualizacion = false;
		try {
			isValidoActualizacion = daoIssuer.actualizarDocBase(idEmisor, sDocBaseActualizar);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerImplementation - actualizarDocBase error al Actualizar Doc Base ", e);
			
		}
		return isValidoActualizacion;
	}

	
	
	public  String setearTildeCadena(String cadena, boolean mostrarTildeEnCajaTexto){
		try {
			 String seteoTildes[][]={  {"&aacute;","á"}, {"&eacute;","é"},{"&iacute;","í"},{"&oacute;","ó"}, {"&uacute;","ú"} ,
					 				   {"&Aacute;","Á"}, {"&Eacute;","É"},{"&Iacute;","Í"},{"&Oacute;","Ó"}, {"&Uacute;","Ú"} ,{"&ntilde;","ñ"},{"&Ntilde;","Ñ"}};
			System.out.println(seteoTildes.length);
			
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








}
