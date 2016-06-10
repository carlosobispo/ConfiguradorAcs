package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.bean.AcsIssuermechanView;
import com.alignet.configurador.emisor.hibernate.bean.AcsIssuermechanBean;


public interface AcsIssuermechanInterface {

	List<AcsIssuermechanBean> listaAcsIssuermechanAll() throws Exception;
	List<AcsIssuermechanBean> listAcsIssuermechanBeanByIdIssuer(String idIssuer) throws Exception;
	AcsIssuermechanBean getAcsIssuermechanBeanById(Integer idAcsIssuermechan) throws Exception; 
	boolean registrarIssuermechan(AcsIssuermechanBean acsIssuermechan) throws Exception;
	boolean actualizarIssuermechan(AcsIssuermechanView acsIssuermechanView) throws Exception;
	
}
