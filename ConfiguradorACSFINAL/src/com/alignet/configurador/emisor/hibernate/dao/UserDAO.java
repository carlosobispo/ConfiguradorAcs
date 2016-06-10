package com.alignet.configurador.emisor.hibernate.dao;

import javax.servlet.ServletException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class UserDAO {

	private Session sesion;
	//private Transaction tx;
	
	public UserDAO(){
	}
	
	
	
	/*
		
	public UserBean selectUserBeanbyUsuario(String usuario){
		UserBean user = null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: UserDAO - selectUserBeanbyUsuario");
			iniciarOperacion();
			
			user=(UserBean) sesion.createCriteria(UserBean.class).add(Restrictions.eq("svcLogin", usuario)).add(Restrictions.ne("intState",Parameters.REGISTER_ELIMINADO )).add(Restrictions.eq("intType", Parameters.REGISTER_USER_TYPE)).uniqueResult();
			sesion.setReadOnly(user, true);
			Utilitario.getLOG_SQL().info("FIN: UserDAO - selectUserBeanbyUsuario");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: UserDAO - selectUserBeanbyUsuario Hubo un problema al momento de obtener la informacion.",e);
		}
		return user;
	}
	*/
	
	
	public UserBean selectUserBeanbyUsuario(String usuario){
		UserBean user = null;
		try {
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("INICIO: UserDAO - selectUserBeanbyUsuario");

			//user=(UserBean) sesion.createCriteria(UserBean.class).add(Restrictions.eq("svcLogin", usuario)).add(Restrictions.ne("intState",Parameters.REGISTER_ELIMINADO )).add(Restrictions.eq("intType", Parameters.REGISTER_USER_TYPE)).uniqueResult();
			user=(UserBean) sesion.createCriteria(UserBean.class).add(Restrictions.eq("svcLogin", usuario)).uniqueResult();
			sesion.setReadOnly(user, true);
			Utilitario.getLOG_SQL().info("VC_LOGIN : "+usuario);			
			Utilitario.getLOG_SQL().info("FIN: UserDAO - selectUserBeanbyUsuario");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: UserDAO - selectUserBeanbyUsuario Hubo un problema al momento de obtener la informacion."+ e.getMessage());
		}
		return user;
	}
	
	/*
 	public UserBean selectUserBeanbyUsuario(String usuario){
	UserBean user = null;
	try {
		Utilitario.getLOG_SQL().info("INICIO: UserDAO - selectUserBeanbyUsuario");
		iniciarOperacion();
		
		//sesion.createSQLQuery(arg0)
		
		SQLQuery query=  sesion.createSQLQuery("SELECT  IN_IDUSER,IN_IDPROCESSOR,IN_IDISSUER,IN_TYPE,IN_PROFILE,VC_NAME,VC_LOGIN,VC_PASSWORD,IN_CARDMASK, VC_XMLACCESS,VC_BRANDS,VC_PERMISSIONS," +
				"IN_INTENT,IN_INTENTUPDPWD,IN_MAXDAYUPDATEPWD,DT_UPDATEPWD,IN_STATE,DT_REGISTRY,DT_UPDATE,IN_IDUSERACTION, IN_ACCESSTYPE, IN_EXPORTDATA  " +
				"FROM SQMSM.TSM_USER WHERE VC_LOGIN = 'adminPrueba'  FOR READ ONLY");
		query.addEntity(UserBean.class);
		user= (UserBean) query.uniqueResult();
		Utilitario.getLOG_SQL().info("FIN: UserDAO - selectUserBeanbyUsuario");
	} catch (Exception e) {
		Utilitario.getLOG_SQL().error("ERROR: UserDAO - selectUserBeanbyUsuario Hubo un problema al momento de obtener la informacion.",e);
	}
	return user;
 	}*/
	

	 
	public boolean UpdateIntentUser(UserBean userBean){
		boolean operacionExitosa=false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: UserDAO - UpdateIntentUser");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("ANTES  " +userBean.toString());
			sesion.update(userBean);
			//Utilitario.getLOG_SQL().info("DESPUES1 " +userBean.toString());
			//tx.commit();
			
			//HIBERNATE
			try {
				UserTransaction transaction = HibernateSessionFactory.getUserTransaction();
				int status = transaction.getStatus();
				//Utilitario.getLOG_SQL().info(" Status.STATUS_ACTIVE "+Status.STATUS_ACTIVE);
				//Utilitario.getLOG_SQL().info(" status  "+status);
				
				if(status == Status.STATUS_ACTIVE){
					//Utilitario.getLOG_SQL().info("ANTES DE EJECUTAR COMMIT ");
					transaction.commit();
					//Utilitario.getLOG_SQL().info("DESPUES DE EJECUTAR COMMIT ");
				}
			} catch (Exception e) {
				Utilitario.getLOG_SQL().info(" ERROR EJECUTAR COMMIT  ", e);
				throw new ServletException("Error al obtener transacción (Hibernate)", e);
			}
			
			//Utilitario.getLOG_SQL().info("DESPUES2 " +userBean.toString());

			operacionExitosa=true;
			Utilitario.getLOG_SQL().info("FIN: UserDAO - UpdateIntentUser");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: UserDAO - UpdateIntentUser Hubo un problema al momento de actualizar intent informacion .",e);
			manejaExcepcion();
		}
		
		return operacionExitosa;
	}

	public void iniciarOperacion() throws HibernateException{
		sesion = HibernateSessionFactory.getSessionSAE();
	}
	
	private void manejaExcepcion(){
		try {
			Utilitario.getLOG_SQL().info("ANTES DE ROLLBACK");
			HibernateSessionFactory.getSessionSAE().getTransaction().rollback();
			Utilitario.getLOG_SQL().info("DESPUES DE ROLLBACK");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR al ejecutar ROLLBACK ",e);
		}
	}
	
}
