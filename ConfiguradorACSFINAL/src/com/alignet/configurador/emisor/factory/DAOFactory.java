package com.alignet.configurador.emisor.factory;

import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerInterface;



public abstract class DAOFactory {

	private static final int  VISA       = 10 ;
	private static final int MASTERCARD  = 20  ;
	
	public abstract DataConfigInterface getDataConfigServices();
	public abstract IssuerInterface getIssuerServices();
	public abstract IssuerBlockedInterface getIssuerBlockedServices();
	public abstract FileInterface getFileServices();
	
	public static DAOFactory getFactory(Integer Datasource){
		
		switch (Datasource) {
			case VISA : 
				return new VisaDAOFactory();
			case MASTERCARD : 
				return new MastercardDAOFactory();
				
		}
		return null;
	}
	
	
	
}
