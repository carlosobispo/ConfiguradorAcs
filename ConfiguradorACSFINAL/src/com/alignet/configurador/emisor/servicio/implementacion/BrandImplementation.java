package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;
import java.util.ArrayList;

import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.dao.BrandDAO;
import com.alignet.configurador.emisor.servicio.interfaz.BrandInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class BrandImplementation implements BrandInterface,Serializable{

	private static final long serialVersionUID = 1L;
	BrandDAO brandDao = new BrandDAO();
	
	public ArrayList<BrandBean> listBrand() throws Exception {
		ArrayList<BrandBean> listBrand=null;
		try {
			listBrand=(ArrayList<BrandBean>)brandDao.listBrand();
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. BrandImplementation error al obtener lista marcas ", e);
		}
		return listBrand;
	}

	
}
