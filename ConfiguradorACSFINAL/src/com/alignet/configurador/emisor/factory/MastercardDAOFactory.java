package com.alignet.configurador.emisor.factory;

import com.alignet.configurador.emisor.hibernate.daoMastercard.MDataConfigDAO;
import com.alignet.configurador.emisor.hibernate.daoMastercard.MFileDAO;
import com.alignet.configurador.emisor.hibernate.daoMastercard.MIssuerBlockedDAO;
import com.alignet.configurador.emisor.hibernate.daoMastercard.MIssuerDAO;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerInterface;

public class MastercardDAOFactory extends DAOFactory{

	//@Override
	public IssuerInterface getIssuerServices() {
		return new MIssuerDAO();
	}

	//@Override
	public IssuerBlockedInterface getIssuerBlockedServices() {
		return new MIssuerBlockedDAO();
	}

	//@Override
	public DataConfigInterface getDataConfigServices(){
		return new MDataConfigDAO();
	}

	//@Override
	public FileInterface getFileServices() {
		return new MFileDAO();
	}

	

}
