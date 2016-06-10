package com.alignet.configurador.emisor.hibernate.daoMastercard;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.FileBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class MFileDAO  implements FileInterface{

	//SessionFactory sessionFactoryMASTERCARD=new AnnotationConfiguration().configure("hibernateMastercardACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	
	public MFileDAO(){
		
	}

	//@Override
	public FileBean getFilexIdEmisor(Integer idEmisor) throws Exception {
		FileBean fileBean= null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MFileDAO - getFilexIdEmisor");
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.bean.FileBean(fb.intIdFile,fb.intType,fb.intIdReference,fb.intIdIssuer,fb.blFile) " +
					" from FileBean fb where fb.intIdIssuer=:intIdIssuer and fb.intType=:intType");
			query.setParameter("intIdIssuer", idEmisor);
			query.setParameter("intType", Parameters.IN_TYPELOGOEMISOR);
			Utilitario.getLOG_SQL().info("IN_IDISSUER: "+ idEmisor);
			Utilitario.getLOG_SQL().info("IN_TYPE: "+ Parameters.IN_TYPELOGOEMISOR);
			query.setReadOnly(true);
			fileBean = (FileBean)query.uniqueResult();		
			Utilitario.getLOG_SQL().info("FIN: MFileDAO - getFilexIdEmisor");
			
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MFileDAO - getFilexIdEmisor Hubo un problema al momento de obtener la informacion.",e);
		}
		return fileBean;
	}
	
	
	//@Override
	public boolean SaveUpdateLogoEmisor(com.alignet.configurador.emisor.hibernate.bean.FileBean file) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: MFileDAO - SaveUpdateLogoEmisor");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("File: "+file.toString());
			sesion.saveOrUpdate(file);
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: MFileDAO - SaveUpdateLogoEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MFileDAO - SaveUpdateLogoEmisor  ",e);
			manejaExcepcion();
		}
		return isValidoactualizacion;
	}

	

	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionACSMASTERCARD();
	}
	
	private void manejaExcepcion() throws HibernateException{
		try {
			HibernateSessionFactory.getSessionACSMASTERCARD().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR EJECUTAR ROLLBACK ",e);
		}
	}

}
