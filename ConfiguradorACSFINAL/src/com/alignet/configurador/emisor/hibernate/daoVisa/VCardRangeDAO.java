package com.alignet.configurador.emisor.hibernate.daoVisa;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.bean.CardRangeBeanView;
import com.alignet.configurador.emisor.bean.IssuerBean;
import com.alignet.configurador.emisor.hibernate.bean.CardRangeBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.CardRangeInterface;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class VCardRangeDAO implements CardRangeInterface {

	/** Declaracion de Atributos*/
	private Session sesion;

	@Override
	public List<CardRangeBean> listaCardRange() throws Exception {
		
		List<CardRangeBean> listRangeBean = null;
		try {
			Utilitario.getLOG_SQL().info( "INICIO: VCardRangeDAO - listaCardRange()");
			iniciarOperacion();
			Query query = sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.CardRangeBean( cardRange.vc_firstrange ,cardRange.vc_lastrange, cardRange.vc_idState ,cardRange.ch_useADC) " 
										   + " from CardRangeBean cardRange");
			query.setReadOnly(true);
			listRangeBean = query.list();
			Utilitario.getLOG_SQL().info( "FIN: VCardRangeDAO - listaCardRange()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VIssuerDAO - listaCardRange() Hubo un problema al momento de obtener la informacion.",e);
		}

		return listRangeBean;
	}

	@Override
	public List<CardRangeBean> listCardRangeByIdIssuer(String idIssuer) throws Exception {
		List<CardRangeBean> listCardRange =null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VCardRangeDAO - listCardRangeByIdIssuer()");
			iniciarOperacion();
						
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.CardRangeBean(cardRange.in_idCardrange ,cardRange.in_idIssyer, cardRange.vc_firstrange ,cardRange.vc_lastrange, cardRange.vc_idState ,cardRange.ch_useADC) " +
					" from CardRangeBean cardRange  where cardRange.in_idIssyer=:intIdIssuer");

			query.setParameter("intIdIssuer",Integer.parseInt(idIssuer) );			
			query.setReadOnly(true);
			
			listCardRange = query.list();
			Utilitario.getLOG_SQL().info("FIN: VCardRangeDAO - listCardRangeByIdIssuer()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VCardRangeDAO - listCardRangeByIdIssuer() Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listCardRange;
	}

	@Override
	public CardRangeBean getCardRangeById(Integer idCardRange) throws Exception {
		CardRangeBean objCardRange = null;
		try {
			Utilitario.getLOG_SQL().info("START: VCardRangeDAO - getCardRangeforId");
			iniciarOperacion();
			objCardRange=(CardRangeBean)sesion.get(CardRangeBean.class, idCardRange);
			Utilitario.getLOG_SQL().info("FINISH: VCardRangeDAO - getCardRangeforId");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VCardRangeDAO - getCardRangeforId Hubo un problema al momento de obtener CardRange: "+idCardRange,e);
		}
		return objCardRange;
	}

	@Override
	public boolean existeRegistradoIdCardRange(String idEmisor)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registrarCardRange(CardRangeBean cardRange) throws Exception {
	
		boolean isValidoRegistro = false;
		
		try {
			Utilitario.getLOG_SQL().info( "INICIO: VCardRangeDAO - registrarCardRange()");
			iniciarOperacion();
			Utilitario.getLOG_SQL().info( "Datos a registrar : " + cardRange.toString());
			sesion.save(cardRange);
			
			isValidoRegistro = true;
			Utilitario.getLOG_SQL().info("FIN: VCardRangeDAO - registrarCardRange()");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VCardRangeDAO - registrarCardRange Hubo un problema al momento de registrar CardRange ", e);
			manejaExcepcion();
		}

		return isValidoRegistro;
		
	}
	
	@Override
	public boolean actualizarCardRange(CardRangeBeanView cardRangeView) {

		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: VCardRangeDAO - actualizarCardRange");
			iniciarOperacion();
			
			Integer id_cardRange = Integer.parseInt(cardRangeView.getIn_idCardrange());
			
			CardRangeBean cardRangeBean = (CardRangeBean) sesion.get(CardRangeBean.class, id_cardRange);
			
			//cardRangeBean.setIn_idCardrange(in_idCardrange)
			cardRangeBean.setVc_firstrange(cardRangeView.getVc_firstrange());
			cardRangeBean.setVc_lastrange(cardRangeView.getVc_lastrange());
			cardRangeBean.setVc_nameA(cardRangeView.getVc_nameA());
			cardRangeBean.setVc_nameB(cardRangeView.getVc_nameB());
			cardRangeBean.setVc_idState(Integer.parseInt(cardRangeView.getVc_idState()));
			cardRangeBean.setCh_useADC(cardRangeView.getCh_useADC());
			
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
			
			
			sesion.update(cardRangeBean);
			
			//transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FIN: VCardRangeDAO - actualizarCardRange");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VCardRangeDAO - actualizarCardRange Hubo un problema al momento de actualizar CardRange ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}
	
	//Genercios
	public void iniciarOperacion() throws HibernateException {
		sesion = HibernateSessionFactory.getSessionACSVISA();
	}
	
	private void manejaExcepcion() throws HibernateException{
		try {
			HibernateSessionFactory.getSessionACSMASTERCARD().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR EJECUTAR ROLLBACK ",e);
		}
	}

	

	
	

	
}
