package com.alignet.configurador.emisor.hibernate.daoMastercard;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.UrlBeanView;
import com.alignet.configurador.emisor.hibernate.bean.UrlBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.UrlInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class MUrlDAO implements UrlInterface{
	
	private Session sesion;
	
	@Override
	public List<UrlBean> listaUrl() throws Exception {
		
		List<UrlBean> listUrl= null;
		try {
			Utilitario.getLOG_SQL().info("START: MUrlDAO - listaUrl");
			
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.UrlBean( url.in_idurl, url.in_idissuer, url.svcInitials, url.in_devicecategory, url.vc_url, url.in_type, url.in_idauthenmechan, url.in_idenrollmechan, url.in_idstate) " +
					" from UrlBean url order by url.in_idurl desc" );
			query.setReadOnly(true);
			listUrl = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MUrlDAO - listaUrl");
		
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - listaUrl Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listUrl;
	}

	@Override
	public List<UrlBean> listUrlByIdIssuer(String idIssuer) throws Exception {
		List<UrlBean> listUrl= null;
		try {
			Utilitario.getLOG_SQL().info("START: MUrlDAO - listUrlByIdIssuer()");
			iniciarOperacion();
						
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.UrlBean( url.in_idurl, url.in_idissuer, url.in_devicecategory, url.vc_url, url.in_type, url.in_idauthenmechan, url.in_idenrollmechan, url.in_idstate) " +
					" from UrlBean url  where url.in_idissuer=:intIdIssuer");
			
			Utilitario.getLOG_SQL().info("query.toString(): " + query.toString());
			Utilitario.getLOG_SQL().info("query.getQueryString(): " + query.getQueryString());
			
			
			query.setParameter("intIdIssuer",Integer.parseInt(idIssuer) );			
			query.setReadOnly(true);
			
			listUrl = query.list();
			Utilitario.getLOG_SQL().info("FINISH: MUrlDAO - listUrlByIdIssuer()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - listUrlByIdIssuer() Hubo un problema al momento de obtener la informacion.",e);
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - listUrlByIdIssuer()", e.getCause());
		}
		
		return listUrl;
	}

	@Override
	public UrlBean getUrlById(Integer idUrl) throws Exception {
		UrlBean objUrl = null;
		try {
			Utilitario.getLOG_SQL().info("START: MUrlDAO - getUrlforId");
			iniciarOperacion();
			objUrl=(UrlBean)sesion.get(UrlBean.class, idUrl);
			Utilitario.getLOG_SQL().info("FINISH: MUrlDAO - getUrlforId");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - getUrlforId() Hubo un problema al momento de obtener CardRange: "+idUrl, e);
		}
		return objUrl;
	}

	@Override
	public boolean registrarUrl(UrlBean url) throws Exception {
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info( "START: MUrlDAO - registrarUrl()");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info( "Datos a registrar : " + url.toString());
			sesion.save(url);
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FINISH: MUrlDAO - registrarUrl()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - registrarUrl() Hubo un problema al momento de registrar url ", e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}

	@Override
	public boolean actualizarUrl(UrlBeanView urlView) throws Exception {
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info("STARD: MUrlDAO - actualizarUrl()");
			iniciarOperacion();
			
			
			Integer id_url  = Integer.parseInt(urlView.getIn_idurl());
			
			UrlBean	urlBeanHibernate = (UrlBean) sesion.get(UrlBean.class, id_url);
			urlBeanHibernate.setIn_idurl(Integer.parseInt(urlView.getIn_idurl()));
			urlBeanHibernate.setIn_idissuer(Integer.parseInt(urlView.getIn_idissuer()));
			urlBeanHibernate.setIn_devicecategory(Integer.parseInt(urlView.getIn_devicecategory()));
			urlBeanHibernate.setVc_url(urlView.getVc_url());
			urlBeanHibernate.setIn_type(Integer.parseInt(urlView.getIn_type()));
			urlBeanHibernate.setIn_idauthenmechan(Integer.parseInt(urlView.getIn_idauthenmechan()));
			urlBeanHibernate.setIn_idenrollmechan(Integer.parseInt(urlView.getIn_idenrollmechan()));
			urlBeanHibernate.setIn_idstate(Integer.parseInt(urlView.getIn_idstate()));
			
					
//			if(emisor.getIntIdstate().equals(Parameters.REGISTER_ACTIVE.toString()))
//				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_ACTIVE);
//			else
//				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_NONACTIVE);
//			
//			
//			if(emisor.getSflagMatrixDecision().equals("true"))
//				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_ACTIVE.toString());
//			else
//				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_NONACTIVE.toString());
//			
//			if(emisor.getSflagTiempoDesbloqueo().equals("true"))
//				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_ACTIVE);
//			else
//				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_NONACTIVE);
//			
//			Utilitario.getLOG_SQL().info("Datos a actualizar : "+emisor.toString());
			
			
			sesion.update(urlBeanHibernate);
			
			//transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FINISH: MUrlDAO - actualizarUrl()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MUrlDAO - actualizarUrl() Hubo un problema al momento de actualizar CardRange ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}

	@Override
	public boolean existeRegistradoIdEmisor(String idEmisor) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizarDocBase(Integer idEmisor, String sDocBaseActualizar)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	// Generales
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSMASTERCARD();
	}

	private void manejaExcepcion() {
			try {
				
				Utilitario.getLOG_SQL().info("ANTES DE EJECUTAR ROLLBACK");
				HibernateSessionFactory.getSessionACSVISA().getTransaction().rollback();
				Utilitario.getLOG_SQL().info("DESPUES DE EJECUTAR ROLLBACK");
				
			} catch (Exception e) {
				Utilitario.getLOG_SQL().fatal("ERROR al ejecutar ROLLBACK ", e);
			}
		}
}
