package com.alignet.configurador.emisor.hibernate.daoMastercard;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.IssuerBlockedBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerBlockedInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class MIssuerBlockedDAO implements IssuerBlockedInterface{

	///SessionFactory sessionFactoryMASTERCARD=new AnnotationConfiguration().configure("hibernateMastercardACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	//@Override
	public boolean registrarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception {
		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerBlockedDAO - registrarIssuerBlock");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("IssuerBlock : " + IssuerBlock.toString());
			sesion.save(IssuerBlock);
		//	transaction.commit();
			isValidoRegistro=true;			
			Utilitario.getLOG_SQL().info("INICIO: MIssuerBlockedDAO - registrarIssuerBlock");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerBlockedDAO - registrarIssuerBlock Hubo un problema al momento de registrar IssuerBlock ",e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	//@Override
	public boolean actualizarIssuerBlock(IssuerBlockedBean IssuerBlock) throws Exception {
		boolean isActualizacion = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerBlockedDAO - actualizarIssuerBlock");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info("IssuerBlock : " + IssuerBlock.toString());
			sesion.saveOrUpdate(IssuerBlock);
			//transaction.commit();
			isActualizacion=true;			
			Utilitario.getLOG_SQL().info("FIN: MIssuerBlockedDAO - actualizarIssuerBlock");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerBlockedDAO - actualizarIssuerBlock Hubo un problema al momento de actualizar IssuerBlock ",e);
			manejaExcepcion();
		}

		return isActualizacion;
	}
	
	
	//@Override
	public IssuerBlockedBean getIsssuerBlockedForIdEmisor(Integer idEmisor)
			throws Exception {
		IssuerBlockedBean objeto =null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerBlockedDAO - getIsssuerBlockedForIdEmisor");
			Utilitario.getLOG_SQL().info("ID EMISOR : " +idEmisor);
			iniciarOperacion();
			Query query=sesion.createQuery("select ib from IssuerBlockedBean ib where ib.intIdIssuer=:idEmisor").setMaxResults(1);
			query.setParameter("idEmisor", idEmisor);
			query.setReadOnly(true);
			objeto = (IssuerBlockedBean)query.uniqueResult();			
			Utilitario.getLOG_SQL().info("FIN: MIssuerBlockedDAO - getIsssuerBlockedForIdEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerBlockedDAO - getIsssuerBlockedForIdEmisor Hubo un problema al momento de obtener IssuerBlock del Emisor: "+idEmisor,e);
		}
		return objeto;
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
