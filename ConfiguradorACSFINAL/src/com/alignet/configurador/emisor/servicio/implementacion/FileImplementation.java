package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;

import com.alignet.configurador.emisor.bean.FileBean;
import com.alignet.configurador.emisor.factory.DAOFactory;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class FileImplementation implements FileInterface,Serializable{

	private static final long serialVersionUID = 1L;
	
	DAOFactory factory =null;
	FileInterface daointerfaz= null;
	
	public FileImplementation(Integer datasource){
		factory= DAOFactory.getFactory(datasource);
		daointerfaz = factory.getFileServices();
	}
	
	//@Override
	public FileBean getFilexIdEmisor(Integer idEmisor) throws Exception {
		FileBean fileBean= null;
		try {
			fileBean=daointerfaz.getFilexIdEmisor(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. FileImplementation - getFilexIdEmisor ", e);
			
		}
		return fileBean;
	}

	//@Override
	public boolean SaveUpdateLogoEmisor(com.alignet.configurador.emisor.hibernate.bean.FileBean file) throws Exception {
		boolean iscorrecto=false;
		try {
			iscorrecto= daointerfaz.SaveUpdateLogoEmisor(file);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. FileImplementation - SaveUpdateLogoEmisor ", e);
		}
		return iscorrecto;
	}

}
