package com.alignet.configurador.emisor.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;


public class BrandDAO {
	
	private Session sesion;
	
    public BrandDAO(){
    	
    }

	@SuppressWarnings("unchecked")
	public List<BrandBean> listBrand(){
    		List<BrandBean> marcas=null;
    	try {
    		Utilitario.getLOG_SQL().info("INICIO: BrandDAO - listBrand");
    		iniciarOperacion();
    		marcas=(List<BrandBean>)sesion.createCriteria(BrandBean.class).add(Restrictions.not( Restrictions.in("intIdBrand", new Integer[]{Parameters.SIN_MARCA,Parameters.ID_MARCA_AMEX})  ) ) .list();
    		Utilitario.getLOG_SQL().info("FIN: BrandDAO - listBrand");
		} catch (HibernateException e) {
			Utilitario.getLOG_SQL().error("ERROR: BrandDAO - listBrand Hubo un problema al momento de obtener la informacion.",e);
		}
    	return marcas;
    }
    
	
	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionSAE();
	}
    
}
