package com.alignet.configurador.emisor.hibernate.daoVisa;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.AcsIssuermechanView;
import com.alignet.configurador.emisor.hibernate.bean.AcsIssuermechanBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.AcsIssuermechanInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class VAcsIssuermechanDAO implements AcsIssuermechanInterface{

	private Session sesion;
	
	@Override
	public List<AcsIssuermechanBean> listaAcsIssuermechanAll()throws Exception {
		List<AcsIssuermechanBean> listAcsIssuermechan= null;
		try {
			Utilitario.getLOG_SQL().info("START: VAcsIssuermechanDAO - listaAcsIssuermechanBeanAll");
			
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsIssuermechanBean( issMecha.in_idIssuermechan, issMecha.in_idIssuer, issMecha.in_idMechanism, issMecha.in_idState, issMecha.in_idAuthenmechan, issMecha.in_idEnrollmechan, issMecha.dt_dateTime) " +
					" AcsIssuermechanBean issMecha order by issMecha.in_idIssuermechan desc" );
			query.setReadOnly(true);
			listAcsIssuermechan = query.list();
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuermechanDAO - listaAcsIssuermechanBeanAll");
		
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - listaAcsIssuermechanBeanAll Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listAcsIssuermechan;
	}

	@Override
	public List<AcsIssuermechanBean> listAcsIssuermechanBeanByIdIssuer(String idIssuer) throws Exception {
		List<AcsIssuermechanBean> listAcsIssuermechan= null;
		try {
			Utilitario.getLOG_SQL().info("START: VAcsIssuermechanDAO - listAcsUserByIdIssuer()");
			iniciarOperacion();
						
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsIssuermechanBean(issMecha.in_idIssuermechan, issMecha.in_idIssuer, issMecha.in_idMechanism, issMecha.in_idState, issMecha.in_idAuthenmechan, issMecha.in_idEnrollmechan, issMecha.dt_dateTime) " +
					" from AcsIssuermechanBean issMecha  where issMecha.in_idIssuer=:intIdIssuer");
			
			Utilitario.getLOG_SQL().info("query.toString(): " + query.toString());
			Utilitario.getLOG_SQL().info("query.getQueryString(): " + query.getQueryString());
			
			
			query.setParameter("intIdIssuer",Integer.parseInt(idIssuer) );			
			query.setReadOnly(true);
			
			listAcsIssuermechan = query.list();
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuermechanDAO - listAcsUserByIdIssuer()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - listAcsUserByIdIssuer() Hubo un problema al momento de obtener la informacion.",e);
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - listAcsUserByIdIssuer()", e.getCause());
		}
		
		return listAcsIssuermechan;
	}

	@Override
	public AcsIssuermechanBean getAcsIssuermechanBeanById(Integer idAcsIssuermechan) throws Exception {
		
		AcsIssuermechanBean objAcsIssuermechan = null;
		
		try {
			Utilitario.getLOG_SQL().info("START: VAcsIssuermechanDAO - getAcsIssuermechanBeanById()");
			iniciarOperacion();
			objAcsIssuermechan= (AcsIssuermechanBean) sesion.get(AcsIssuermechanBean.class, idAcsIssuermechan);	
					
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuermechanDAO - getAcsIssuermechanBeanById()");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - getAcsIssuermechanBeanById() Hubo un problema al momento de obtener CardRange: "+idAcsIssuermechan, e);
		}
		
		return objAcsIssuermechan;
	}

	@Override
	public boolean registrarIssuermechan(AcsIssuermechanBean acsIssuermechan) throws Exception {
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info( "START: VAcsIssuermechanDAO - registrarIssuermechan()");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info( "Datos a registrar : " + acsIssuermechan.toString());
			sesion.save(acsIssuermechan);
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuermechanDAO - registrarIssuermechan()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - registrarIssuermechan() Hubo un problema al momento de registrar registrarIssuermechan ", e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	@Override
	public boolean actualizarIssuermechan(AcsIssuermechanView acsIssuermechanView) throws Exception {
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info("STARD: VAcsIssuermechanDAO - actualizarIssuermechan()");
			iniciarOperacion();
					
			Integer id_issuermechan  = Integer.parseInt(acsIssuermechanView.getIn_idAuthenmechan());
			
			AcsIssuermechanBean acsIssuermechanHiernate = (AcsIssuermechanBean) sesion.get(AcsIssuermechanBean.class, id_issuermechan);  
			
			acsIssuermechanHiernate.setIn_idIssuermechan(Integer.parseInt(acsIssuermechanView.getIn_idIssuermechan()));
			acsIssuermechanHiernate.setIn_idIssuer(Integer.parseInt(acsIssuermechanView.getIn_idIssuer()));
			acsIssuermechanHiernate.setIn_idMechanism(Integer.parseInt(acsIssuermechanView.getIn_idMechanism()));
			acsIssuermechanHiernate.setIn_idState(Integer.parseInt(acsIssuermechanView.getIn_idState()));
			acsIssuermechanHiernate.setIn_idAuthenmechan(Integer.parseInt(acsIssuermechanView.getIn_idAuthenmechan()));
			acsIssuermechanHiernate.setIn_idEnrollmechan(Integer.parseInt(acsIssuermechanView.getIn_idEnrollmechan()));
			//acsIssuermechanHiernate.setDt_dateTime(new  acsIssuermechanView.getDt_dateTime());
			
					
//			if(emisor.getIntIdstate().equals(Parameters.REGISTER_ACTIVE.toString()))
//				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_ACTIVE);
//			else
//				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_NONACTIVE);
//			
//			
//			if(emisor.getSflagMatrixDecision().equals("true"))
//				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_ACTIVE.toString());
//			else
//				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_NONACTIVE.toString());
//			
//			if(emisor.getSflagTiempoDesbloqueo().equals("true"))
//				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_ACTIVE);
//			else
//				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_NONACTIVE);
//			
//			Utilitario.getLOG_SQL().info("Datos a actualizar : "+emisor.toString());
			
			
			sesion.update(acsIssuermechanHiernate);
			
			//transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FINISH: VAcsIssuermechanDAO - actualizarIssuermechan()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VAcsIssuermechanDAO - actualizarIssuermechan() Hubo un problema al momento de actualizar actualizarIssuermechan ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}
	
	/** Generales */
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSVISA();
	}

	private void manejaExcepcion() {
		try {
	
			Utilitario.getLOG_SQL().info("ANTES DE EJECUTAR ROLLBACK");
			HibernateSessionFactory.getSessionACSVISA().getTransaction().rollback();
			Utilitario.getLOG_SQL().info("DESPUES DE EJECUTAR ROLLBACK");
				
		} catch (Exception e) {
				Utilitario.getLOG_SQL().fatal("ERROR al ejecutar ROLLBACK ", e);
		}
	}

	

}
