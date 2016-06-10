package com.alignet.configurador.emisor.servicio.interfaz;

import com.alignet.configurador.emisor.bean.FileBean;

public interface FileInterface {

	FileBean getFilexIdEmisor(Integer idEmisor) throws Exception;
	boolean SaveUpdateLogoEmisor(com.alignet.configurador.emisor.hibernate.bean.FileBean file) throws Exception;
	 
}
