package com.alignet.configurador.emisor.hibernate.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.alignet.configurador.emisor.util.Utilitario;

public class HibernateSessionFactory {

	private static final String CONFIGURATIONSAE="/hibernate.cfg.xml";
	private static final String CONFIGURATIONACSVISA="/hibernateVisaACS.cfg.xml";
	private static final String CONFIGURATIONACSMASTERCARD="/hibernateMastercardACS.cfg.xml";
	
	private static Configuration configurationSAE= new AnnotationConfiguration();
	private static Configuration configurationACSVISA= new AnnotationConfiguration();
	private static Configuration configurationACSMASTERCARD= new AnnotationConfiguration();
	
	private static SessionFactory sessionFactorySAE;
	private static SessionFactory sessionFactoryACSVISA;
	private static SessionFactory sessionFactoryACSMASTERCARD;
	
	static{
		try {
			configurationSAE.configure(CONFIGURATIONSAE);
			configurationACSVISA.configure(CONFIGURATIONACSVISA);
			configurationACSMASTERCARD.configure(CONFIGURATIONACSMASTERCARD);
			
			sessionFactorySAE = configurationSAE.buildSessionFactory();
			sessionFactoryACSVISA = configurationACSVISA.buildSessionFactory();
			sessionFactoryACSMASTERCARD = configurationACSMASTERCARD.buildSessionFactory();
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().fatal("[HibernateSessionFactory]  %%%% Error Creating SessionFactory %%%%: ", e);
		}
		
	}
	
	private HibernateSessionFactory(){
		
	}
	
	public static UserTransaction getUserTransaction() throws NamingException {		
		return (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
	}
	
	
	public static Session getSessionSAE(){
		final String logSession = "[ HibernateSessionFactory.getSessionSAE ] ";
		try {
			int status = getUserTransaction().getStatus();
			
			//Utilitario.getLOG_APP().info(logSession + " status " + status);
			if (status == Status.STATUS_NO_TRANSACTION || status == Status.STATUS_ROLLEDBACK || status == Status.STATUS_COMMITTED) {
				//Utilitario.getLOG_APP().info(logSession + " ENTRO AL IF " + status);
				getUserTransaction().begin();
			}
			SessionFactory sf = (SessionFactory) new InitialContext().lookup(configurationSAE.getProperty("hibernate.session_factory_name"));
			
			if (sf != null) {
				//Utilitario.getLOG_APP().info(logSession + " SessionFactory NO ES NULL ");
				return sf.getCurrentSession();
			}
			
		} catch (NamingException e) {
			Utilitario.getLOG_APP().error(logSession + "No pudo obtener la fábrica de sesiones", null);
			Utilitario.getLOG_APP().error(logSession + "NamingException: ", e);
		} catch (NotSupportedException e) {
			Utilitario.getLOG_APP().error(logSession + "Error al iniciar la transaccion: ", null);
			Utilitario.getLOG_APP().error(logSession + "NotSupportedException: ", e);
		} catch (SystemException e) {
			Utilitario.getLOG_APP().error(logSession + "Error al obtener estado de transaccion: ", null);
			Utilitario.getLOG_APP().error(logSession + "SystemException: ", e);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error(logSession + "Exception: ", e);
		}
		return null;
	}
	

	public static Session getSessionACSVISA(){
		final String logSession = "[ HibernateSessionFactory.getSessionACSVISA ] ";
		try {
			int status = getUserTransaction().getStatus();
			//Utilitario.getLOG_APP().info(logSession + " status " + status);
			
			if (status == Status.STATUS_NO_TRANSACTION || status == Status.STATUS_ROLLEDBACK || status == Status.STATUS_COMMITTED) {
			//	Utilitario.getLOG_APP().info(logSession + " ENTRO AL IF " + status);
				getUserTransaction().begin();
			}
			SessionFactory sf = (SessionFactory) new InitialContext().lookup(configurationACSVISA.getProperty("hibernate.session_factory_name"));
			
			if (sf != null) {
				//Utilitario.getLOG_APP().info(logSession + " SessionFactory NO ES NULL ");
				return sf.getCurrentSession();
			}
			
		} catch (NamingException e) {
			Utilitario.getLOG_APP().fatal(logSession + "No pudo obtener la fábrica de sesiones", null);
			Utilitario.getLOG_APP().fatal(logSession + "NamingException: ", e);
		} catch (NotSupportedException e) {
			Utilitario.getLOG_APP().fatal(logSession + "Error al iniciar la transaccion: ", null);
			Utilitario.getLOG_APP().fatal(logSession + "NotSupportedException: ", e);
		} catch (SystemException e) {
			Utilitario.getLOG_APP().fatal(logSession + "Error al obtener estado de transaccion: ", null);
			Utilitario.getLOG_APP().fatal(logSession + "SystemException: ", e);
		} catch (Exception e) {
			Utilitario.getLOG_APP().fatal(logSession + "Exception: ", e);
		}
		return null;
	}
	

	public static Session getSessionACSMASTERCARD(){
		final String logSession = "[ HibernateSessionFactory.getSessionACSMASTERCARD ] ";
		try {
			int status = getUserTransaction().getStatus();
			
			if (status == Status.STATUS_NO_TRANSACTION || status == Status.STATUS_ROLLEDBACK || status == Status.STATUS_COMMITTED) {
				getUserTransaction().begin();
			}
			SessionFactory sf = (SessionFactory) new InitialContext().lookup(configurationACSMASTERCARD.getProperty("hibernate.session_factory_name"));
			
			if (sf != null) {
				return sf.getCurrentSession();
			}
			
		} catch (NamingException e) {
			Utilitario.getLOG_APP().fatal(logSession + "No pudo obtener la fábrica de sesiones", null);
			Utilitario.getLOG_APP().fatal(logSession + "NamingException: ", e);
		} catch (NotSupportedException e) {
			Utilitario.getLOG_APP().fatal(logSession + "Error al iniciar la transaccion: ", null);
			Utilitario.getLOG_APP().fatal(logSession + "NotSupportedException: ", e);
		} catch (SystemException e) {
			Utilitario.getLOG_APP().fatal(logSession + "Error al obtener estado de transaccion: ", null);
			Utilitario.getLOG_APP().fatal(logSession + "SystemException: ", e);
		} catch (Exception e) {
			Utilitario.getLOG_APP().fatal(logSession + "Exception: ", e);
		}
		return null;
	}	

	
	public static Configuration getConfiguration() {
		return configurationSAE;
	}
	
	public static void closeSessionFactory() {
		sessionFactorySAE.close();
	}
	
	
}
