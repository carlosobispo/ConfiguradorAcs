package com.alignet.configurador.emisor.hibernate.daoVisa;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.AcsRollBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.AcsRollInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class VAcsRollDAO implements AcsRollInterface{

	/** Declaracion de Atributos*/
	private Session sesion;
	
	@Override
	public List<AcsRollBean> listAcsRollAll() {
		
		List<AcsRollBean> listAscRoll = null;
		try {
			Utilitario.getLOG_SQL().info( "STARD: VAcsRollDAO - listAcsRollAll()");
			iniciarOperacion();
			Query query = sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsRollBean( roll.in_idRoll, roll.vc_name)" 
										   + " from AcsRollBean roll");
			query.setReadOnly(true);
			listAscRoll = query.list();
			Utilitario.getLOG_SQL().info( "FINISH: VAcsRollDAO - listAcsRollAll()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsRollDAO - listAcsRollAll() Hubo un problema al momento de obtener la informacion.",e);
		}

		return listAscRoll;
	}
	
	/**Genericos */
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSVISA();
	}
	
	private void manejaExcepcion() throws HibernateException{
		try {
			HibernateSessionFactory.getSessionACSMASTERCARD().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR EJECUTAR ROLLBACK ",e);
		}
	}
}
