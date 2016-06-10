package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;
import com.alignet.configurador.emisor.bean.AcsUserBeanView;
import com.alignet.configurador.emisor.hibernate.bean.AcsUserBean;

public interface AcsUserInterface {
	
	List<AcsUserBean> listaAcsUser() throws Exception;
	List<AcsUserBean> listAcsUserByIdIssuer(String idIssuer) throws Exception;
	AcsUserBean getAcsUserById(Integer idAcsUser) throws Exception; 
	boolean registrarAcsUser(AcsUserBean acsUser) throws Exception;
	boolean actualizarAcsUser(AcsUserBeanView acsUserView) throws Exception;	
	
}
