package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.ArrayList;
import java.util.List;

import com.alignet.configurador.emisor.hibernate.bean.DataConfigBean;

public interface DataConfigInterface {

	boolean registrarDataConfig( ArrayList<DataConfigBean> ListdataConfing) throws Exception;
	boolean actualizarDataConfig( ArrayList<DataConfigBean> ListdataConfing) throws Exception;
	List<DataConfigBean> getListDataConfigForIdEmisor(Integer idEmisor) throws Exception;
	
}
