package com.alignet.configurador.emisor.hibernate.daoMastercard;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.AcsIssuerIntentADCView;
import com.alignet.configurador.emisor.hibernate.bean.AcsIssuerIntentADCBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.AcsIssuerIntentADCInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class MAcsIssuerIntentADCDAO implements AcsIssuerIntentADCInterface{

	private Session sesion;
	
	@Override
	public List<AcsIssuerIntentADCBean> listaAcsIssuerIntentADCAll() throws Exception {
		
		List<AcsIssuerIntentADCBean> listAcsIssuerIntentADC= null;
		try {
			Utilitario.getLOG_SQL().info("START: MAcsIssuerIntentADCDAO - listaAcsIssuerIntentADCAll");
			
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsIssuerIntentADCBean( issIntent.in_idIssuer, issIntent.in_intent, issIntent.ch_type, issIntent.ch_state, issIntent.in_idAuthenmechan, issIntent.in_idEnrollmechan) " +
					" AcsIssuerIntentADCBean issIntent order by issIntent.in_idIssuer desc" );
			query.setReadOnly(true);
			listAcsIssuerIntentADC = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MAcsIssuerIntentADCDAO - listaAcsIssuerIntentADCAll");
		
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsIssuerIntentADCDAO - listaAcsIssuerIntentADCAll Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listAcsIssuerIntentADC;
	}

	@Override
	public List<AcsIssuerIntentADCBean> listAcsIssuerIntentADCByIdIssuer(String idIssuer) throws Exception {
		
		List<AcsIssuerIntentADCBean> listAcsIssuerIntentADC= null;
		
		try {
			Utilitario.getLOG_SQL().info("START: MAcsIssuerIntentADCDAO - listAcsIssuerIntentADCByIdIssuer()");
			iniciarOperacion();
						
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsIssuerIntentADCBean( issIntent.in_idIssuer, issIntent.in_intent, issIntent.ch_type, issIntent.ch_state, issIntent.in_idAuthenmechan, issIntent.in_idEnrollmechan) " +
					" from AcsIssuerIntentADCBean issIntent  where issIntent.in_idIssuer=:intIdIssuer");
			
			Utilitario.getLOG_SQL().info("query.toString(): " + query.toString());
			Utilitario.getLOG_SQL().info("query.getQueryString(): " + query.getQueryString());
			
			
			query.setParameter("intIdIssuer",Integer.parseInt(idIssuer) );			
			query.setReadOnly(true);
			
			listAcsIssuerIntentADC = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MAcsIssuerIntentADCDAO - listAcsIssuerIntentADCByIdIssuer()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsIssuerIntentADCDAO - listAcsIssuerIntentADCByIdIssuer() Hubo un problema al momento de obtener la informacion.",e);
			Utilitario.getLOG_SQL().error("ERROR: MAcsIssuerIntentADCDAO - listAcsIssuerIntentADCByIdIssuer()", e.getCause());
		}
		
		return listAcsIssuerIntentADC;
	}

	@Override
	public AcsIssuerIntentADCBean getAcsIssuerIntentADCById(Integer idAcsIssuerIntentADC) throws Exception {
		AcsIssuerIntentADCBean objAcsIssuerIntentADC = null;
		
		try {
			Utilitario.getLOG_SQL().info("START: VAcsIssuerIntentADCDAO - getAcsIssuerIntentADCById");
			iniciarOperacion();
			objAcsIssuerIntentADC= (AcsIssuerIntentADCBean) sesion.get(AcsIssuerIntentADCBean.class, idAcsIssuerIntentADC);
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuerIntentADCDAO - getAcsIssuerIntentADCById");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuerIntentADCDAO - getAcsIssuerIntentADCById() Hubo un problema al momento de obtener CardRange: "+idAcsIssuerIntentADC, e);
		}
		
		return objAcsIssuerIntentADC;
	}

	@Override
	public boolean registrarAcsIssuerIntentADC(AcsIssuerIntentADCBean acsIssuerIntentADC) throws Exception {
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info( "START: MAcsIssuerIntentADCDAO - registrarAcsIssuerIntentADC()");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info( "Datos a registrar : " + acsIssuerIntentADC.toString());
			sesion.save(acsIssuerIntentADC);
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FINISH: MAcsIssuerIntentADCDAO - registrarAcsIssuerIntentADC()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsIssuerIntentADCDAO - registrarAcsIssuerIntentADC() Hubo un problema al momento de registrar AcsIssuerIntentADC ", e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	@Override
	public boolean actualizarAcsIssuerIntentADC(
			AcsIssuerIntentADCView acsIssuerIntentADCView) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/** Generales */
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSMASTERCARD();
	}

	private void manejaExcepcion() {
		try {
	
			Utilitario.getLOG_SQL().info("ANTES DE EJECUTAR ROLLBACK");
			HibernateSessionFactory.getSessionACSMASTERCARD().getTransaction().rollback();
			Utilitario.getLOG_SQL().info("DESPUES DE EJECUTAR ROLLBACK");
				
		} catch (Exception e) {
				Utilitario.getLOG_SQL().fatal("ERROR al ejecutar ROLLBACK ", e);
		}
	}
}
