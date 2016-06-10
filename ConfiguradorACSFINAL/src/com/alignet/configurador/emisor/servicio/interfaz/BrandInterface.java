package com.alignet.configurador.emisor.servicio.interfaz;

import java.util.List;

import com.alignet.configurador.emisor.hibernate.bean.BrandBean;

public interface BrandInterface {
	
	 List<BrandBean> listBrand() throws Exception;
	 
}
