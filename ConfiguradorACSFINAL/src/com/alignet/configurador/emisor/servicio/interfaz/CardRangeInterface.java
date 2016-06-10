package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.bean.CardRangeBeanView;
import com.alignet.configurador.emisor.hibernate.bean.CardRangeBean;


public interface CardRangeInterface {

	List<CardRangeBean> listaCardRange() throws Exception;
	
	List<CardRangeBean> listCardRangeByIdIssuer(String idIssuer) throws Exception;
	
	CardRangeBean getCardRangeById(Integer idCardRange) throws Exception;
	
	boolean existeRegistradoIdCardRange(String idEmisor) throws Exception;
	
	boolean registrarCardRange(CardRangeBean cardRange) throws Exception;
	
	boolean actualizarCardRange(CardRangeBeanView cardRangeView);
	

//	boolean registrarActualizarConfigTarjetaNoAfiliada( com.alignet.configurador.emisor.bean.IssuerBean emisor) throws Exception;
//	boolean actualizarDocBase(Integer idEmisor, String sDocBaseActualizar) throws Exception;

}
