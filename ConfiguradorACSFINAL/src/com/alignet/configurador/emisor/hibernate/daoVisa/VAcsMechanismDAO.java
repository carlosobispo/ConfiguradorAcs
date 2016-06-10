package com.alignet.configurador.emisor.hibernate.daoVisa;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.AcsMechanismBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.AcsMechanismInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class VAcsMechanismDAO implements AcsMechanismInterface{

	/** Declaracion de Atributos*/
	private Session sesion;

	
	@Override
	public List<AcsMechanismBean> listAcsMechanismAll() {
		List<AcsMechanismBean> listAcsMechanism = null;
		try {
			Utilitario.getLOG_SQL().info( "STARD: VAcsMechanismDAO - listAcsMechanismAll()");
			iniciarOperacion();
			Query query = sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsMechanismBean( mecha.in_idMechanism, mecha.vc_description, mecha.vc_description1) " 
										   + " from AcsMechanismBean mecha");
			query.setReadOnly(true);
			listAcsMechanism = query.list();
			Utilitario.getLOG_SQL().info( "FINISH: VAcsMechanismDAO - listAcsMechanismAll()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsMechanismDAO - listAcsMechanismAll() Hubo un problema al momento de obtener la informacion.",e);
		}

		return listAcsMechanism;
	}
	
	/**Genericos */
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSVISA();
	}
}
