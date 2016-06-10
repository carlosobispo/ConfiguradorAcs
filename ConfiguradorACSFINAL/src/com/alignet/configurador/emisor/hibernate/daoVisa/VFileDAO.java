package com.alignet.configurador.emisor.hibernate.daoVisa;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.FileBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.FileInterface;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class VFileDAO implements FileInterface{

	//SessionFactory sessionFactoryVISA=new AnnotationConfiguration().configure("hibernateVisaACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	public VFileDAO(){
		
	}

	//@Override
	public FileBean getFilexIdEmisor(Integer idEmisor) throws Exception {
		FileBean fileBean= null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VFileDAO - getFilexIdEmisor");
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.bean.FileBean(fb.intIdFile,fb.intType,fb.intIdReference,fb.intIdIssuer,fb.blFile) " +
					" from FileBean fb where fb.intIdIssuer=:intIdIssuer and fb.intType=:intType");
			query.setParameter("intIdIssuer", idEmisor);
			query.setParameter("intType", Parameters.IN_TYPELOGOEMISOR);
			Utilitario.getLOG_SQL().info("IN_IDISSUER: "+ idEmisor);
			Utilitario.getLOG_SQL().info("IN_TYPE: "+ Parameters.IN_TYPELOGOEMISOR);
			query.setReadOnly(true);
			fileBean = (FileBean)query.uniqueResult();		
			Utilitario.getLOG_SQL().info("FIN: VFileDAO - getFilexIdEmisor");
			
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VFileDAO - getFilexIdEmisor Hubo un problema al momento de obtener la informacion.",e);
		}
		return fileBean;
	}
	
	
	//@Override
	public boolean SaveUpdateLogoEmisor(com.alignet.configurador.emisor.hibernate.bean.FileBean file) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: VFileDAO - SaveUpdateLogoEmisor");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("File: "+file.toString());
			sesion.saveOrUpdate(file);
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: VFileDAO - SaveUpdateLogoEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VFileDAO - SaveUpdateLogoEmisor  ",e);
			manejaExcepcion();
		}
		return isValidoactualizacion;
	}
	

	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionACSVISA();
	}
	
	private void manejaExcepcion() throws HibernateException{
		try {
			HibernateSessionFactory.getSessionACSVISA().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR al ejecutar ROLLBACK ",e);
		}
	}


	
}
