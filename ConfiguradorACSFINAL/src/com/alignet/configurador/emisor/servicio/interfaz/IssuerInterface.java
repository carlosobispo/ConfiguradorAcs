package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.hibernate.bean.IssuerBean;



public interface IssuerInterface {
	

	List<IssuerBean> listaIssuer() throws Exception;
	List<IssuerBean> listIssuerxIdOrName(String idEmisor, String vsName) throws Exception;
	IssuerBean getEmisorforId(Integer idEmisor) throws Exception; 
	boolean existeRegistradoIdEmisor(String idEmisor) throws Exception;
	boolean registrarEmisor(IssuerBean emisor) throws Exception;
	boolean actualizarEmisor(com.alignet.configurador.emisor.bean.IssuerBean emisor) throws Exception;
	boolean registrarActualizarConfigTarjetaNoAfiliada(com.alignet.configurador.emisor.bean.IssuerBean emisor) throws Exception;
	boolean actualizarDocBase(Integer idEmisor,String sDocBaseActualizar) throws Exception;
}
