package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigSAEBean;
import com.alignet.configurador.emisor.hibernate.dao.DataConfigSAEDAO;
import com.alignet.configurador.emisor.servicio.interfaz.DataConfigSAEInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class DataConfigSAEImplementation implements DataConfigSAEInterface, Serializable{

	private static final long serialVersionUID = 1L;
	DataConfigSAEDAO dataConfigSaeDAO= new DataConfigSAEDAO();

	
	public boolean actualizarDataConfigSAE(ArrayList<DataConfigSAEBean> ListdataConfing) throws Exception {
		boolean isCorrecto=false;
		try {
			isCorrecto=dataConfigSaeDAO.actualizarDataConfig(ListdataConfing);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigSAEImplementation error actualizar", e);
		}
		return isCorrecto;
	}

	public List<DataConfigSAEBean> getListDataConfigSAEForIdEmisor(Integer idEmisor) throws Exception {
		ArrayList<DataConfigSAEBean> listDataCondifSAE= null;
		try {
			listDataCondifSAE = ( ArrayList<DataConfigSAEBean>)dataConfigSaeDAO.getListDataConfigForIdEmisor(idEmisor);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigSAEImplementation error al obtener lista", e);
		}
		return listDataCondifSAE;
	}
	
	
	public String getValueforKeyName(List<DataConfigSAEBean> listDataConfig, String keyName ){
		
		try {
			for (DataConfigSAEBean dataConfigBean : listDataConfig) {
				
				if(dataConfigBean.getId().getSvcKeyname().equals(keyName)){
					return dataConfigBean.getSvcValue();
				}
			}
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. DataConfigSAEImplementation - getValueforKeyName ", e);
			
		}
		return "";
	}
	
	
	

}
