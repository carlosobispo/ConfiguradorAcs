package com.alignet.configurador.emisor.servicio.interfaz;

import com.alignet.configurador.emisor.hibernate.bean.IssuerBlockedBean;

public interface IssuerBlockedInterface {
	
	boolean registrarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception;
	boolean actualizarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception;
	IssuerBlockedBean getIsssuerBlockedForIdEmisor(Integer idEmisor) throws Exception;
	
}
