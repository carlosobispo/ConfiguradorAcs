package com.alignet.configurador.emisor.hibernate.daoMastercard;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.AcsUserBeanView;
import com.alignet.configurador.emisor.hibernate.bean.AcsUserBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.AcsUserInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class MAcsUserDAO implements AcsUserInterface{
	
	private Session sesion;
	
	@Override
	public List<AcsUserBean> listaAcsUser() throws Exception {
		List<AcsUserBean> listUrl= null;
		try {
			Utilitario.getLOG_SQL().info("START: MAcsUserDAO - listaAcsUser");
			
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsUserBean( user.in_idUser, user.in_idIssuer, user.in_idRoll, user.vc_name, user.vc_login, user.vc_password, user.in_idState) " +
					" AcsUserBean user order by user.in_idUser desc" );
			query.setReadOnly(true);
			listUrl = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MAcsUserDAO - listaAcsUser");
		
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - listaAcsUser Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listUrl;
	}

	@Override
	public List<AcsUserBean> listAcsUserByIdIssuer(String idIssuer) throws Exception {
		List<AcsUserBean> listUrl= null;
		try {
			Utilitario.getLOG_SQL().info("START: MAcsUserDAO - listAcsUserByIdIssuer()");
			iniciarOperacion();
						
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.AcsUserBean( user.in_idUser, user.in_idIssuer, user.in_idRoll, user.vc_name, user.vc_login, user.vc_password, user.in_idState) " +
					" from AcsUserBean user  where user.in_idIssuer=:intIdIssuer");
			
			Utilitario.getLOG_SQL().info("query.toString(): " + query.toString());
			Utilitario.getLOG_SQL().info("query.getQueryString(): " + query.getQueryString());
			
			
			query.setParameter("intIdIssuer",Integer.parseInt(idIssuer) );			
			query.setReadOnly(true);
			
			listUrl = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MAcsUserDAO - listAcsUserByIdIssuer()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - listAcsUserByIdIssuer() Hubo un problema al momento de obtener la informacion.",e);
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - listAcsUserByIdIssuer()", e.getCause());
		}
		
		return listUrl;
	}

	@Override
	public AcsUserBean getAcsUserById(Integer idAcsUser) throws Exception {
		AcsUserBean objAcsUser = null;
		
		try {
			Utilitario.getLOG_SQL().info("START: MAcsUserDAO - getAcsUserById");
			iniciarOperacion();
			objAcsUser= (AcsUserBean) sesion.get(AcsUserBean.class, idAcsUser);	
					
			Utilitario.getLOG_SQL().info("FINISH: MAcsUserDAO - getAcsUserById");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - getAcsUserById() Hubo un problema al momento de obtener CardRange: "+idAcsUser, e);
		}
		
		return objAcsUser;
	}

	@Override
	public boolean registrarAcsUser(AcsUserBean acsUser) throws Exception {
		
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info( "START: MAcsUserDAO - registrarAcsUser()");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info( "Datos a registrar : " + acsUser.toString());
			sesion.save(acsUser);
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FINISH: MAcsUserDAO - registrarAcsUser()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - registrarAcsUser() Hubo un problema al momento de registrar url ", e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	@Override
	public boolean actualizarAcsUser(AcsUserBeanView acsUserView) throws Exception {
		
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info("STARD: MAcsUserDAO - actualizarAcsUser()");
			iniciarOperacion();
					
			Integer id_acsUser  = Integer.parseInt(acsUserView.getIn_idUser());
			
			
			AcsUserBean acsUserBeanHibernate = (AcsUserBean) sesion.get(AcsUserBean.class, id_acsUser);
			acsUserBeanHibernate.setIn_idRoll(Integer.parseInt(acsUserView.getIn_idRoll()));
			acsUserBeanHibernate.setVc_name(acsUserView.getVc_name());
			acsUserBeanHibernate.setVc_login(acsUserView.getVc_login());
			acsUserBeanHibernate.setVc_password(acsUserView.getVc_password());
			acsUserBeanHibernate.setIn_idState(Integer.parseInt(acsUserView.getIn_idState()));
			
						
					
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
			
			
			sesion.update(acsUserBeanHibernate);
			
			//transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FINISH: MAcsUserDAO - actualizarAcsUser()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MAcsUserDAO - actualizarAcsUser() Hubo un problema al momento de actualizar AcsUser ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}
	
	/** Generales */
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSMASTERCARD();
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
