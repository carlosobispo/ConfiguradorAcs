package com.alignet.configurador.emisor.hibernate.daoMastercard;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.alignet.configurador.emisor.hibernate.bean.IssuerBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.servicio.interfaz.IssuerInterface;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class MIssuerDAO implements IssuerInterface{
	
	//SessionFactory sessionFactoryMASTERCARD=new AnnotationConfiguration().configure("hibernateMastercardACS.cfg.xml").buildSessionFactory();
	private Session sesion;
	//private Transaction transaction;
	
	public MIssuerDAO(){
		
	}

	
	//@Override
	public List<IssuerBean> listaIssuer() throws Exception {
		List<IssuerBean> listIssuer=null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - listaIssuer");
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.IssuerBean( iss.intIdIssuer ,iss.svcName,iss.svcInitials ,iss.svcLogoMember,iss.intIdstate) " +
					" from IssuerBean iss order by iss.intIdIssuer desc" );
			query.setReadOnly(true);
			listIssuer = query.list();
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - listaIssuer");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - listaIssuer Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listIssuer;
	}
	

	
	//@Override
	public List<IssuerBean> listIssuerxIdOrName(String idEmisor,
			String vsName) throws Exception {
		
		List<IssuerBean> listIssuer=null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - listIssuerxIdOrName");
			iniciarOperacion();
			String cadena= generaCadenaBusquedalistIssuerxIdOrName(idEmisor, vsName);
			
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.hibernate.bean.IssuerBean( iss.intIdIssuer ,iss.svcName,iss.svcInitials ,iss.svcLogoMember,iss.intIdstate) " +
					" from IssuerBean iss "+ cadena +" order by iss.intIdIssuer desc" );
			
			if( !Utilitario.isVacioOrNull(idEmisor) && !Utilitario.isVacioOrNull(vsName) ){
				query.setParameter("intIdIssuer",Integer.parseInt(idEmisor) );
				query.setParameter("svcName", "%"+vsName+"%");
			}
			else if(!Utilitario.isVacioOrNull(idEmisor)){
				query.setParameter("intIdIssuer",Integer.parseInt(idEmisor) );
			}
			else if(!Utilitario.isVacioOrNull(vsName)){
				query.setParameter("svcName", "%"+vsName+"%");
			}			
			
			query.setReadOnly(true);
			listIssuer = query.list();
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - listIssuerxIdOrName");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - listaIssuer Hubo un problema al momento de obtener la informacion.",e);
		}
		
		return listIssuer;
	}

	
	
	public String generaCadenaBusquedalistIssuerxIdOrName(String idEmisor, String vsName){
		String cadenaBusqueda="";
		
		if( !Utilitario.isVacioOrNull(idEmisor) && !Utilitario.isVacioOrNull(vsName) ){
			cadenaBusqueda=" where iss.intIdIssuer=:intIdIssuer and  lower(iss.svcName) LIKE lower(:svcName) ";
		}
		else if(!Utilitario.isVacioOrNull(idEmisor)){
			cadenaBusqueda=" where iss.intIdIssuer=:intIdIssuer ";
		}
		else if(!Utilitario.isVacioOrNull(vsName)){
			cadenaBusqueda=" where lower(iss.svcName) LIKE lower(:svcName) ";
		}
			
		return cadenaBusqueda;
	}
	


	//@Override
	public boolean existeRegistradoIdEmisor(String idEmisor) throws Exception {
		boolean isDisponibleIdEmisor  = false;
		List<IssuerBean> listIssuer=null;
		try {
			
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - isDisponibleIdEmisor");
			iniciarOperacion();
			Query query= sesion.createQuery("select new com.alignet.configurador.emisor.bean.IssuerBean( iss.intIdIssuer ,iss.svcName,iss.svcInitials ,iss.svcLogoMember,iss.intIdstate) " +
					" from IssuerBean iss where iss.intIdIssuer=:intIdIssuer" );
			Integer id_Emisor= Integer.parseInt(idEmisor);
			query.setParameter("intIdIssuer",id_Emisor );
			query.setReadOnly(true);
			listIssuer = query.list();
			Utilitario.getLOG_SQL().info("Numero de registro encontrado: "+ listIssuer.size() +" con el ID_EMISOR: " + idEmisor);
			
			if(listIssuer.size()>0){
				isDisponibleIdEmisor = true;
			}
			
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - isDisponibleIdEmisor");
			
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: VIssuerDAO - isDisponibleIdEmisor Hubo un problema al momento de obtener la informacion.",e);
		}
		return isDisponibleIdEmisor;
	}
	
	//@Override
	public boolean registrarEmisor(com.alignet.configurador.emisor.hibernate.bean.IssuerBean emisor) throws Exception {
		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - registrarEmisor");
			iniciarOperacion();
			emisor.setSvcLogobrand( Utilitario.getStringResourceBundle("DEFAULT.LOGOBRAND.MASTERCARD").trim());
			emisor.setSvcAdcText2(Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO1.MASTERCARD").trim());
			emisor.setSvcAdcText3(Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO2.MASTERCARD").trim());
			emisor.setSvcAdcText4(Utilitario.getStringResourceBundle("DEFAULT.TARJETA.NO.AFILIADA.TEXTO3.MASTERCARD").trim());
			emisor.setSvcDocbase(emisor.getSvcName()+" "+Utilitario.getStringResourceBundle("DEFAULT.DOCBASE.MASTERCARD").trim());
			Utilitario.getLOG_SQL().info("Datos a registrar : "+emisor.toString());
			sesion.save(emisor);
			///transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - registrarEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - registrarEmisor Hubo un problema al momento de registrar Emisor ",e);
			manejaExcepcion();
		}

		return isValidoRegistro;
	}
	
	//@Override
	public IssuerBean getEmisorforId(Integer idEmisor) throws Exception {
		IssuerBean objIssuer=null;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - getEmisorforId");
			iniciarOperacion();
			objIssuer=(IssuerBean)sesion.get(IssuerBean.class, idEmisor);
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - getEmisorforId");
		}catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - getEmisorforId Hubo un problema al momento de obtener emisor: "+idEmisor,e);
		}
		return objIssuer;
	}
	
	//@Override
	public boolean actualizarEmisor(com.alignet.configurador.emisor.bean.IssuerBean emisor) throws Exception {
		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - actualizarEmisor");
			iniciarOperacion();
			Integer Id_Emisor= Integer.parseInt(emisor.getIntIdIssuer());
			IssuerBean IssuerBeanHibernate= (IssuerBean) sesion.get(IssuerBean.class, Id_Emisor);
			
			IssuerBeanHibernate.setIntIdIssuer( Integer.parseInt(emisor.getIntIdIssuer()));
			IssuerBeanHibernate.setSvcName(emisor.getSvcName().trim());
			IssuerBeanHibernate.setSvcInitials(emisor.getSvcInitials().trim().toUpperCase());
			IssuerBeanHibernate.setSchAuthenticateby(emisor.getSchAuthenticateby().toUpperCase());
			IssuerBeanHibernate.setSchEnrolltype(emisor.getSchEnrolltype().toUpperCase());
			IssuerBeanHibernate.setSchCharpaddingpin(emisor.getSchCharpaddingpin().toUpperCase());
			IssuerBeanHibernate.setSchCharpaddingpam(emisor.getSchCharpaddingpam().toUpperCase());
			
			
			if(emisor.getIntIdstate().equals(Parameters.REGISTER_ACTIVE.toString()))
				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_ACTIVE);
			else
				IssuerBeanHibernate.setIntIdstate(Parameters.REGISTER_NONACTIVE);
			
			
			if(emisor.getSflagMatrixDecision().equals("true"))
				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_ACTIVE.toString());
			else
				IssuerBeanHibernate.setSchMatrixDecision(Parameters.REGISTER_NONACTIVE.toString());
			
			if(emisor.getSflagTiempoDesbloqueo().equals("true"))
				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_ACTIVE);
			else
				IssuerBeanHibernate.setIntUseblocked(Parameters.REGISTER_NONACTIVE);
			
			Utilitario.getLOG_SQL().info("Datos a actualizar : "+emisor.toString());
			
			
			sesion.update(IssuerBeanHibernate);
			
			//transaction.commit();
			isValidoRegistro=true;
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - actualizarEmisor");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - actualizarEmisor Hubo un problema al momento de actualizar Emisor ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}



	//@Override
	public boolean registrarActualizarConfigTarjetaNoAfiliada(com.alignet.configurador.emisor.bean.IssuerBean emisor)
			throws Exception {
		boolean isValidoRegistro = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - registrarActualizarConfigTarjetaNoAfiliada");
			iniciarOperacion();
			Integer Id_Emisor= Integer.parseInt(emisor.getIntIdIssuer());
			IssuerBean IssuerBeanHibernate= (IssuerBean) sesion.get(IssuerBean.class, Id_Emisor);
			
			IssuerBeanHibernate.setIntIdIssuer( Integer.parseInt(emisor.getIntIdIssuer()));
			IssuerBeanHibernate.setSvcAdcText2(emisor.getSvcAdcText2());
			IssuerBeanHibernate.setSvcAdcText3(emisor.getSvcAdcText3());
			IssuerBeanHibernate.setSvcAdcText4(emisor.getSvcAdcText4());
			
			Utilitario.getLOG_SQL().info("Datos a actualizar : "+IssuerBeanHibernate.toString());

			sesion.update(IssuerBeanHibernate);
			
			//transaction.commit();
			isValidoRegistro=true;
			
			
			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - registrarActualizarConfigTarjetaNoAfiliada");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - registrarActualizarConfigTarjetaNoAfiliada Hubo un problema al momento de actualizar Emisor ",e);
			manejaExcepcion();
		}
		
		return isValidoRegistro;
	}
	
	//@Override
	public boolean actualizarDocBase(Integer idEmisor, String sDocBaseActualizar) throws Exception {
		boolean isValidoActualizacion = false;
		try {
			Utilitario.getLOG_SQL().info("INICIO: MIssuerDAO - actualizarDocBase");
			iniciarOperacion();
			IssuerBean IssuerBeanHibernate= (IssuerBean) sesion.get(IssuerBean.class, idEmisor);

			IssuerBeanHibernate.setIntIdIssuer(idEmisor);
			IssuerBeanHibernate.setSvcDocbase(sDocBaseActualizar);

			Utilitario.getLOG_SQL().info("Datos a actualizar : "+IssuerBeanHibernate.toString());
			sesion.update(IssuerBeanHibernate);
			
			isValidoActualizacion=true;

			Utilitario.getLOG_SQL().info("FIN: MIssuerDAO - actualizarDocBase");
		} catch (Exception e) {
			Utilitario.getLOG_SQL().error("ERROR: MIssuerDAO - actualizarDocBase Hubo un problema al momento de actualizar Doc Base ",e);
			manejaExcepcion();
		}
		return isValidoActualizacion;
	}

	

	public void iniciarOperacion() throws HibernateException{
		sesion=HibernateSessionFactory.getSessionACSMASTERCARD();
	}
	
	private void manejaExcepcion() throws HibernateException{
		try {
			HibernateSessionFactory.getSessionACSMASTERCARD().getTransaction().rollback();
		} catch (Exception e) {
			Utilitario.getLOG_SQL().fatal("ERROR EJECUTAR ROLLBACK ",e);
		}
	}



	
}
