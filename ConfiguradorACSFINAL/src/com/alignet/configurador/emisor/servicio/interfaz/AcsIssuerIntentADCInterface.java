package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.bean.AcsIssuerIntentADCView;
import com.alignet.configurador.emisor.hibernate.bean.AcsIssuerIntentADCBean;



public interface AcsIssuerIntentADCInterface {

	
	List<AcsIssuerIntentADCBean> listaAcsIssuerIntentADCAll() throws Exception;
	List<AcsIssuerIntentADCBean> listAcsIssuerIntentADCByIdIssuer(String idIssuer) throws Exception;
	AcsIssuerIntentADCBean getAcsIssuerIntentADCById(Integer idAcsIssuerIntentADC) throws Exception; 
	boolean registrarAcsIssuerIntentADC(AcsIssuerIntentADCBean acsIssuerIntentADC) throws Exception;
	boolean actualizarAcsIssuerIntentADC(AcsIssuerIntentADCView acsIssuerIntentADCView) throws Exception;
	
}
