package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;
import com.alignet.configurador.emisor.bean.UrlBeanView;
import com.alignet.configurador.emisor.hibernate.bean.UrlBean;

public interface UrlInterface {

	List<UrlBean> listaUrl() throws Exception;
	List<UrlBean> listUrlByIdIssuer(String idIssuer) throws Exception;
	UrlBean getUrlById(Integer idUrl) throws Exception; 
	boolean registrarUrl(UrlBean url) throws Exception;
	boolean actualizarUrl(UrlBeanView urlView) throws Exception;	
	
	boolean existeRegistradoIdEmisor(String idEmisor) throws Exception;
	boolean actualizarDocBase(Integer idEmisor,String sDocBaseActualizar) throws Exception;
	
}
