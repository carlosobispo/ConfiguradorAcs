package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.bean.PopupADCEBeanView;
import com.alignet.configurador.emisor.hibernate.bean.PopupADCEBean;

public interface PopupADCEInterface {

	List<PopupADCEBean> listaPopupADCE() throws Exception;

	List<PopupADCEBean> listPopupADCEByIdUrl(Integer idUrl) throws Exception;

	List<PopupADCEBean> listPopupADCEByIdIssuer(Integer idUrl) throws Exception;
	
	PopupADCEBean getPopupADCEforId(Integer idPopupADCE) throws Exception;

	boolean registrarPopupADCE(PopupADCEBean popupADCE) throws Exception;

	boolean actualizarPopupADCE(PopupADCEBeanView popupADCE) throws Exception;
	
	boolean existeRegistradoIdPopupADCE(String idPopupADCE) throws Exception;

	

	//boolean registrarActualizarConfigTarjetaNoAfiliada( com.alignet.configurador.emisor.bean.PopupADCEBean popupADCE) throws Exception;

	//boolean actualizarDocBase(Integer idEmisor, String sDocBaseActualizar) throws Exception;
	
}
