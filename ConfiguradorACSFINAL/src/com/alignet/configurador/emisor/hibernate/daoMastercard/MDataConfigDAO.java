package com.alignet.configurador.emisor.hibernate.daoMastercard;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class MDataConfigDAO implements DataConfigInterface{
	
	//SessionFactory sessionFactoryMASTERCARD=new AnnotationConfiguration().configure("hibernateMastercardACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	public MDataConfigDAO(){
		
	}
	
	
	//@Override
	public boolean registrarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: MDataConfigDAO - actualizarDataConfig");
			iniciarOperacion();
				for (DataConfigBean dataConfigBean : ListdataConfing) {
					Utilitario.getLOG_SQL().info("DataConfigBean actualizar: " + dataConfigBean.toString());
					sesion.saveOrUpdate(dataConfigBean);
				}
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: MDataConfigDAO - actualizarDataConfig");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MDataConfigDAO - actualizarDataConfig Hubo un problema al momento de actualizar DataConfig ",e);
			manejaExcepcion();
		}
		return isValidoactualizacion;
	}

	//@Override
	public List<DataConfigBean> getListDataConfigForIdEmisor(Integer idEmisor)
			throws Exception {
		List<DataConfigBean>  list =  null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MDataConfigDAO - getListDataConfigForIdEmisor");
			Utilitario.getLOG_SQL().info("ID EMISOR : "+ idEmisor);
			iniciarOperacion();
			Query query= sesion.createQuery("select dtc from DataConfigBean dtc where dtc.id.intIdIssuer=:intIdEmisor" );
			query.setParameter("intIdEmisor", idEmisor);
			query.setReadOnly(true);
			list = query.list();
			Utilitario.getLOG_SQL().info("Total de configuraciones obtenidas "+list.size());
			Utilitario.getLOG_SQL().info("FIN: MDataConfigDAO - getListDataConfigForIdEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MDataConfigDAO - getListDataConfigForIdEmisor Hubo un problema al momento de obtener list DataConfig ",e);
		}		
		return list;
	}

	//@Override
	public boolean actualizarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: MDataConfigDAO - actualizarDataConfig");
			iniciarOperacion();
				for (DataConfigBean dataConfigBean : ListdataConfing) {
					Utilitario.getLOG_SQL().info("DataConfigBean actualizar: " + dataConfigBean.toString());
					sesion.saveOrUpdate(dataConfigBean);
				}
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: MDataConfigDAO - actualizarDataConfig");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MDataConfigDAO - actualizarDataConfig Hubo un problema al momento de actualizar DataConfig ",e);
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
