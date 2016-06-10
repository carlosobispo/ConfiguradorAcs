package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.ArrayList;
import java.util.List;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigSAEBean;



public interface DataConfigSAEInterface {

	boolean actualizarDataConfigSAE( ArrayList<DataConfigSAEBean> ListdataConfing) throws Exception;
	List<DataConfigSAEBean> getListDataConfigSAEForIdEmisor(Integer idEmisor) throws Exception;
	
}
