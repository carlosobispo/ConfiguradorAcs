package com.alignet.configurador.emisor.factory;

import com.alignet.configurador.emisor.hibernate.daoVisa.VDataConfigDAO;
import com.alignet.configurador.emisor.hibernate.daoVisa.VFileDAO;
import com.alignet.configurador.emisor.hibernate.daoVisa.VIssuerBlockedDAO;
import com.alignet.configurador.emisor.hibernate.daoVisa.VIssuerDAO;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerInterface;

public class VisaDAOFactory extends DAOFactory{


	public IssuerInterface getIssuerServices() {
		return new VIssuerDAO();
	}

	
	public IssuerBlockedInterface getIssuerBlockedServices() {
		return new VIssuerBlockedDAO();
	}
	
	
	public DataConfigInterface getDataConfigServices() {
		return new VDataConfigDAO();
	}

	public FileInterface getFileServices() {
		return new VFileDAO();
	}



}
