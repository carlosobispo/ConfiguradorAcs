package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;

import com.alignet.configurador.emisor.bean.IssuerBean;
import com.alignet.configurador.emisor.factory.DAOFactory;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.IssuerBlockedBean;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class IssuerBlockedImplementation implements IssuerBlockedInterface, Serializable{

	private static final long serialVersionUID = 1L;
	DAOFactory factory = null;
	IssuerBlockedInterface daoIssuerBlocked = null;
	
	public IssuerBlockedImplementation (Integer datasource){
		factory = DAOFactory.getFactory(datasource);
		daoIssuerBlocked = factory.getIssuerBlockedServices();
	}
	
	
	//@Override
	public boolean registrarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception {
		boolean isValidoRegistro = false;
		try {
			isValidoRegistro =  daoIssuerBlocked.registrarIssuerBlock(IssuerBlock);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerBlockedImplementation - registrarIssuerBlock error al registrar IssuerBlock ", e);
		}	
		return isValidoRegistro;
	}

	//@Override
	public IssuerBlockedBean getIsssuerBlockedForIdEmisor(Integer idEmisor)
			throws Exception {
		IssuerBlockedBean IssuerBlocked = null;
		try {
			IssuerBlocked=daoIssuerBlocked.getIsssuerBlockedForIdEmisor(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerBlockedImplementation - getIsssuerBlockedForIdEmisor error al obtener IssuerBlock ", e);
		}
		return IssuerBlocked;
	}

	//@Override
	public boolean actualizarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception {
		boolean isValidoActualizacion = false;
		try {
			isValidoActualizacion =  daoIssuerBlocked.actualizarIssuerBlock(IssuerBlock);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerBlockedImplementation - actualziarIssuerBlock error al Actualizar IssuerBlock ", e);
		}	
		return isValidoActualizacion;
	}
	
	
	public void registrarActualizarIssuerBlock(IssuerBean emisor, BrandBean brandBean){
		try {
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandBean.getIntIdBrand())  ) ;
			IssuerBlockedImplementation  IssuerBlockedImpl =  new IssuerBlockedImplementation(datasource);
			
			IssuerBlockedBean IssuerBlockedHibernate = IssuerBlockedImpl.getIsssuerBlockedForIdEmisor( Integer.parseInt(emisor.getIntIdIssuer()));

			 if (IssuerBlockedHibernate == null){
				 
				 IssuerBlockedHibernate = new IssuerBlockedBean();
				 IssuerBlockedHibernate.setIntIdIssuer(Integer.parseInt(emisor.getIntIdIssuer()));
				 
			 }
			 String sflagTiempoDesbloqueo = emisor.getSflagTiempoDesbloqueo()!=null && emisor.getSflagTiempoDesbloqueo().equals("true") ? Parameters.REGISTER_ACTIVE.toString() : Parameters.REGISTER_NONACTIVE.toString();
			 
			 if(sflagTiempoDesbloqueo.equals(Parameters.REGISTER_ACTIVE.toString()))
				 IssuerBlockedHibernate.setIntTimeBlocked( Integer.parseInt(emisor.getShorasDesbloqueo()) );
			 else
				 IssuerBlockedHibernate.setIntTimeBlocked( 24 );
			 
			 IssuerBlockedHibernate.setSchState( sflagTiempoDesbloqueo );
			 IssuerBlockedImpl.actualizarIssuerBlock(IssuerBlockedHibernate); 
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. IssuerBlockedImplementation - registrarActualizarIssuerBlock", e);
		}

	}
	
	

}
