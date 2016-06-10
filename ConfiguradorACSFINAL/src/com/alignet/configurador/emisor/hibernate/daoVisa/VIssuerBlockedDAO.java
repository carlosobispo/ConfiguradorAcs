package com.alignet.configurador.emisor.hibernate.daoVisa;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.IssuerBlockedBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class VIssuerBlockedDAO implements IssuerBlockedInterface{

	//SessionFactory sessionFactoryVISA=new AnnotationConfiguration().configure("hibernateVisaACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	
	//@Override
	public boolean registrarIssuerBlock(IssuerBlockedBean IssuerBlock) {
		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VIssuerBlockedDAO - registrarIssuerBlock");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("Datos a registrar: " + IssuerBlock.toString());
			sesion.save(IssuerBlock);
			//transaction.commit();
			isValidoRegistro=true;			
			Utilitario.getLOG_SQL().info("FIN: VIssuerBlockedDAO - registrarIssuerBlock");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VIssuerBlockedDAO - registrarIssuerBlock Hubo un problema al momento de registrar IssuerBlock ",e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}


	//@Override
	public IssuerBlockedBean getIsssuerBlockedForIdEmisor(Integer idEmisor) {
		IssuerBlockedBean objeto =null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VIssuerBlockedDAO - getIsssuerBlockedForIdEmisor");
			Utilitario.getLOG_SQL().info("ID EMISOR : " +idEmisor);
			iniciarOperacion();
			Query query=sesion.createQuery("select ib from IssuerBlockedBean ib where ib.intIdIssuer=:idEmisor").setMaxResults(1);
			query.setParameter("idEmisor", idEmisor);
			query.setReadOnly(true);
			objeto = (IssuerBlockedBean)query.uniqueResult();			
			Utilitario.getLOG_SQL().info("FIN: VIssuerBlockedDAO - getIsssuerBlockedForIdEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VIssuerBlockedDAO - getIsssuerBlockedForIdEmisor Hubo un problema al momento de obtener IssuerBlock del Emisor: "+idEmisor,e);
		}
		return objeto;
	}	
	
	//@Override
	public boolean actualizarIssuerBlock(IssuerBlockedBean IssuerBlock) {
		boolean isActualizacion = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VIssuerBlockedDAO - actualizarIssuerBlock");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("Datos a actualizar: " + IssuerBlock.toString());
			sesion.saveOrUpdate(IssuerBlock);
			isActualizacion=true;			
			Utilitario.getLOG_SQL().info("FIN: VIssuerBlockedDAO - actualizarIssuerBlock");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VIssuerBlockedDAO - actualizarIssuerBlock Hubo un problema al momento de actualizar IssuerBlock ",e);
			isActualizacion = false;
			manejaExcepcion();
			
		}

		return isActualizacion;
	}
	
	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionACSVISA();
	}
	
	private void manejaExcepcion(){
		try {
			HibernateSessionFactory.getSessionACSVISA().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR al ejecutar ROLLBACK ",e);
		}
		
	}






	
}
