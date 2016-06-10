package com.alignet.configurador.emisor.hibernate.daoVisa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class VDataConfigDAO implements DataConfigInterface{

	//SessionFactory sessionFactoryVISA=new AnnotationConfiguration().configure("hibernateVisaACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	//@Override
	public boolean registrarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoRegistro = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: VDataConfigDAO - registrarDataConfig");
			iniciarOperacion();
				for (DataConfigBean dataConfigBean : ListdataConfing) {
					Utilitario.getLOG_SQL().info("DataConfigBean Registrar: " + dataConfigBean.toString());
					sesion.save(dataConfigBean);
				}
			//transaction.commit();
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FIN: VDataConfigDAO - registrarDataConfig");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VDataConfigDAO - registrarDataConfig Hubo un problema al momento de registrar DataConfig ",e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	//@Override
	public List<DataConfigBean> getListDataConfigForIdEmisor(Integer idEmisor)
			throws Exception {
		List<DataConfigBean>  list =  null;
		try {
			
			Utilitario.getLOG_SQL().info("INICIO: VDataConfigDAO - getListDataConfigForIdEmisor");
			iniciarOperacion();
			
			Query query= sesion.createQuery("select dtc from DataConfigBean dtc where dtc.id.intIdIssuer=:intIdEmisor" );
			query.setParameter("intIdEmisor", idEmisor);
			query.setReadOnly(true);
			list = query.list();
			Utilitario.getLOG_SQL().info("IN_IDISSUER : "+ idEmisor);
			Utilitario.getLOG_SQL().info("Total de configuraciones obtenidas "+list.size());
			Utilitario.getLOG_SQL().info("FIN: VDataConfigDAO - getListDataConfigForIdEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VDataConfigDAO - getListDataConfigForIdEmisor Hubo un problema al momento de obtener list DataConfig ",e);
		}		
		return list;
	}
	
	//@Override
	public boolean actualizarDataConfig(ArrayList<DataConfigBean> ListdataConfing) throws Exception {
		boolean isValidoactualizacion = false;	
		try {
			Utilitario.getLOG_SQL().info("INICIO: VDataConfigDAO - actualizarDataConfig");
			iniciarOperacion();
				for (DataConfigBean dataConfigBean : ListdataConfing) {
					Utilitario.getLOG_SQL().info("DataConfigBean actualizar: " + dataConfigBean.toString());
					sesion.saveOrUpdate(dataConfigBean);
				}
			//transaction.commit();
			isValidoactualizacion = true;
			Utilitario.getLOG_SQL().info("FIN: VDataConfigDAO - actualizarDataConfig");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VDataConfigDAO - actualizarDataConfig Hubo un problema al momento de actualizar DataConfig ",e);
			manejaExcepcion();
		}
		return isValidoactualizacion;
	}
	
	
	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionACSVISA();
		//transaction=sesion.beginTransaction();
	}
	
	private void manejaExcepcion(){
		try {
			HibernateSessionFactory.getSessionACSVISA().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR EJECUTAR ROLLBACK ",e);
		}
		
	}





	
}
