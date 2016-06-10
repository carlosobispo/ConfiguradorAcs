package com.alignet.configurador.emisor.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigSAEBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.util.Utilitario;

public class DataConfigSAEDAO {

	private Session sesion;
//	private Transaction transaction;
	
	
	
	public boolean actualizarDataConfig(ArrayList<DataConfigSAEBean> ListdataConfing) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: DataConfigSAEDAO - actualizarDataConfig");
			iniciarOperacion();
				for (DataConfigSAEBean dataConfigBean : ListdataConfing) {
					Utilitario.getLOG_SQL().info("DataConfigBean actualizar: " + dataConfigBean.toString());
					sesion.saveOrUpdate(dataConfigBean);
				}
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: DataConfigSAEDAO - actualizarDataConfig");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: DataConfigSAEDAO - actualizarDataConfig Hubo un problema al momento de actualizar DataConfig ",e);
			manejaExcepcion();
		}
		return isValidoactualizacion;
	}
	
	public List<DataConfigSAEBean> getListDataConfigForIdEmisor(Integer idEmisor)
			throws Exception {
		List<DataConfigSAEBean>  list =  null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: DataConfigSAEDAO - getListDataConfigForIdEmisor");
			Utilitario.getLOG_SQL().info("IN_IDISSUER : "+ idEmisor);
			iniciarOperacion();
			Query query= sesion.createQuery("select dtc from DataConfigSAEBean dtc where dtc.id.intIdIssuer=:intIdEmisor" );
			query.setParameter("intIdEmisor", idEmisor);
			query.setReadOnly(true);
			list = query.list();
			Utilitario.getLOG_SQL().info("Total de configuraciones obtenidas "+list.size());
			Utilitario.getLOG_SQL().info("FIN: DataConfigSAEDAO - getListDataConfigForIdEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: DataConfigSAEDAO - getListDataConfigForIdEmisor Hubo un problema al momento de obtener list DataConfig ",e);
		}		
		return list;
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
