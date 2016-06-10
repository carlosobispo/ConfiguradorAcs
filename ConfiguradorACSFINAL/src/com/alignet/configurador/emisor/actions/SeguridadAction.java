package com.alignet.configurador.emisor.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.alignet.configurador.emisor.bean.AccesosBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.BrandImplementation;
import com.alignet.configurador.emisor.servicio.implementacion.UserImplementation;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class SeguridadAction extends ActionSupport implements SessionAware {



	private String usuario;
	private String password;
	private String fechaSistema;
	private String mensajeusuario;

	private SessionMap<String, Object> sessionMap;
	
	
	private static final long serialVersionUID = 1L;
	

	@Action(value = "/ingresarSistema", results = { 
			@Result (location = "t_bienvenida", name = "success", type="tiles"),
			@Result (location = "/pages/login.jsp", name = "input")
			})	
	public String ingresarSistema(){

		Utilitario.getLOG_APP().info("****** INGRESO SISTEMA CONFIGURADOR ACS ******");
		String resulttado="input";
		
		try {
				Utilitario.getLOG_APP().info("Usuario a ingresar: "+ usuario);
				int resultado = validaUsuario(usuario, password);
				
				if(resultado==100){
					Utilitario.getLOG_APP().info("El resultado de la validacion es 100, se procede a ingresar al sistema");	
					resulttado="success";
				}
				else if(resultado == 0){
					Utilitario.getLOG_APP().info("Se genero un error de sistema.");	
					mensajeusuario=  Utilitario.getStringMessage("Access.error."+resultado);
				}
				else{
					Utilitario.getLOG_APP().info("Surgio un error al acceder al sistema, "+Utilitario.getStringMessage("Access.error."+resultado));	
					mensajeusuario=  Utilitario.getStringMessage("Access.error."+resultado);
				}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("NO SE PUDO INGRESAR AL SISTEMA "+ e);
		}
		return resulttado;
	}
	
	public int validaUsuario(String usuario,String clave){
		UserImplementation userImpl = null;
		UserBean user = null;
		try {
			
			if( Utilitario.isVacioOrNull(usuario) ||  Utilitario.isVacioOrNull(clave)){
				Utilitario.getLOG_APP().info("Uno de los campos ingresados es vacio o null");
				return 1;
			}
			
			if(usuario.length()>50){
				Utilitario.getLOG_APP().info("El campo Usuario supera el maximo de 50 caracteres");
				return 2;
			}
			userImpl = new UserImplementation();
			user	 = userImpl.selectUserBeanbyUsuario(usuario);
			
			if( user==null ){
				Utilitario.getLOG_APP().info("Usuario NO ENCONTRADO");
				return 5;
			}

			if(user.getIntState().equals(Parameters.REGISTER_NONACTIVE)){
				Utilitario.getLOG_APP().info("El usuario no se encuentra INACTIVO");
				return 6;
			}
			
			if(user.getIntState().equals(Parameters.REGISTER_BLOQUEADO)){
				Utilitario.getLOG_APP().info("El usuario esta temporalmente bloqueado, se procede a realizar el desbloqueo si pasaron las "+Parameters.HORA_DESBLOQUEDO+" horas de bloqueo.");
				
				if(Utilitario.calcularHorasEntreFechas(user.getDtUpdate(), new Date())< Parameters.HORA_DESBLOQUEDO){
					Utilitario.getLOG_APP().info("El usuario esta temporalmente bloqueado, se procede a realizar el desbloqueo si pasaron las "+Parameters.HORA_DESBLOQUEDO+" horas de bloqueo.");
					return 7;
				}else{

					Utilitario.getLOG_APP().info("Se procede a desbloquear al usuario: "+ usuario+". Trancurrio el tiempo de desbloqueo: "+Parameters.HORA_DESBLOQUEDO);
					user.setIntState(Parameters.REGISTER_ACTIVE);
					user.setIntIntent(Integer.valueOf(0));
					userImpl.updateIntentUser(user);
					Utilitario.getLOG_APP().info("El usuario se ha desbloqueado y se reinicio sus intentos.");
					
				}
			}
			
			
			if(!password.equals(user.getSvcPassword())){
				Utilitario.getLOG_APP().warn("La clave ingresada es incorrecta");

				Integer userNumIntent = user.getIntIntent() == null ? 0 : user.getIntIntent();
				Utilitario.getLOG_APP().info("Numero de intento Obtenido: " + userNumIntent);
				Integer totalIntento = userNumIntent +1;
				user.setIntIntent(totalIntento);
				userImpl.updateIntentUser(user);

				Utilitario.getLOG_APP().info("Numero de intento Actual: "+ user.getIntIntent().intValue());

				if(user.getIntIntent() >= Parameters.MAX_INTENTOS){
					Utilitario.getLOG_APP().info("Se Bloquea al usuario ya tiene "+user.getIntIntent()+" intentos.");
					
					user.setIntState(Parameters.REGISTER_BLOQUEADO);
					user.setDtUpdate(new Date());
					userImpl.updateIntentUser(user);
					//commit();
					return 8;
				}
				return 9;
			}
			user.setIntState(Parameters.REGISTER_ACTIVE);
			user.setIntIntent(Integer.valueOf(0));
			userImpl.updateIntentUser(user);
			//commit();
			Utilitario.getLOG_APP().info("El usuario se ha desbloqueado y se reinicio sus intentos.");
			Utilitario.getLOG_APP().info("Se inicia la validacion para configurar los permisos del usuario "+ usuario);
			
			return configurarPermisos(user);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error(" Se genero un error en la logica del validaUsuario. ",e);
			return 0;
		}

	}
	
	public Integer configurarPermisos(UserBean usuario) {
		
		try {
			
			if(Utilitario.isVacioOrNull(usuario.getIntProfile())){
				Utilitario.getLOG_APP().info("El usuario no tiene definido un determinado perfil");
				return 0;
			}
			
			
			if(Utilitario.isVacioOrNull(usuario.getSvcPermissions())){
				Utilitario.getLOG_APP().info("El campo VC_PERMISSIONS es null, es decir no tiene definido accesos");
				return 0;	
			}
			if( !usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADORALIGNET) &&
					!usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADOEMISOR)){
				Utilitario.getLOG_APP().info("IN_PROFILE  "+usuario.getIntProfile() + " No definido, es incorrecto");
				return 0;
			}
			
			String tipo_usuario =  usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADORALIGNET) ? " ADMINISTRADOR ALIGNET " : " ADMINISTRADOR EMISOR";
			Utilitario.getLOG_APP().info("Tipo Usuario: "+tipo_usuario);

			if(usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADOEMISOR) && Utilitario.isVacioOrNull(usuario.getSvcBrands())){
				Utilitario.getLOG_APP().info("No tiene definido Marcas, se deniega el acceso");
				return 0;
			}
			
			if(usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADOEMISOR) && Utilitario.isVacioOrNull(usuario.getIntIdIssuer())){
				Utilitario.getLOG_APP().info("El Usuario no tiene definido un emisor, se deniega el acceso");
				return 0;
			}

			if(usuario.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADOEMISOR)){
				
						Utilitario.getLOG_APP().info("Se obtiene todas las marcas habilitas para el emisor "+ usuario.getIntIdIssuer());
						
						
						BrandImplementation brandInmpl= new BrandImplementation();
						ArrayList<BrandBean>  listBrand = brandInmpl.listBrand();
						
						sessionMap.put("listBrandHabilitadasEmisor",listBrand);
						/*
						ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();
						BrandImplementation brandInmpl= new BrandImplementation();
						ArrayList<BrandBean>  listBrand = brandInmpl.listBrand();
						String[] marcasEmisor= usuario.getSvcBrands().split(",");
						

						for (int i = 0; i < listBrand.size(); i++) {
							BrandBean brand= listBrand.get(i);
							for (int j = 0; j < marcasEmisor.length; j++) {
								Integer idBrand= Integer.parseInt( marcasEmisor[j]);
								
								if(brand.getIntIdBrand().equals(idBrand)){
									
									Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brand.getIntIdBrand())  ) ;
									IssuerImplementation isssuerImpl = new IssuerImplementation( datasource );
									boolean existeRegistradoEmisor =  isssuerImpl.existeRegistradoIdEmisor(usuario.getIntIdIssuer().toString());
									
									if(existeRegistradoEmisor) listBrandHabilitadasEmisor.add(brand);
								}
								
							}
						
						}
						
						
						
						if(listBrandHabilitadasEmisor == null ||  listBrandHabilitadasEmisor.size()==0 ){
							Utilitario.getLOG_APP().warn("El emisor no tiene configurado ninguna marca, se deniega el acceso");
							return 0;
						}
						
						Utilitario.getLOG_APP().info("Total de marcas asignadas al emisor : "+listBrandHabilitadasEmisor.size());
						sessionMap.put("listBrandHabilitadasEmisor",listBrandHabilitadasEmisor);*/
				
			}
			
			AccesosBean accesoUsuario = new AccesosBean();
			accesoUsuario = habilitarAcesos(usuario, accesoUsuario);
			sessionMap.put("ObjetoAccesoBean", accesoUsuario);
			sessionMap.put("ObjetoUsuario",usuario);
			
			return 100;
		
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR, al realizar la configuracion del usuario ",e);
			return 0;
		}
	}
	
	
	public AccesosBean habilitarAcesos(UserBean User,AccesosBean accesoUsuario) throws Exception{
		
		String lista_accesos = User.getSvcPermissions(); 
		if(User.getIntProfile().equals(Parameters.PERFIL_ADMINISTRADOEMISOR)){
			lista_accesos = lista_accesos + ","+ User.getSvcBrands();
		}
		
		String[] acceso = lista_accesos.split(",");
		
		for (int i = 0; i < acceso.length; i++) {
			Integer IdAcceso = Integer.valueOf(acceso[i].trim()) ;
			int keyPage = IdAcceso != null && !IdAcceso.toString().equals("") ? IdAcceso.intValue() : 0;
			
			switch (keyPage) {
				case 1 :
					accesoUsuario.setEnl_configVisa(true);
					break;
				case 2 :
					accesoUsuario.setEnl_configMastercard(true);
					break;

				case 10 :
					accesoUsuario.setEnl_emisores(true);	
					break;
				case 11 :
					accesoUsuario.setEnl_usuarios(true);
					break;	

				
				case 20:
					accesoUsuario.setEnl_configuracionLogo(true);
					break;
				case 21:
					accesoUsuario.setEnl_autenticacionClaveIncorrectaExpirada(true);
					break;
				case 22:
					accesoUsuario.setEnl_tarjetaBloqueada(true);
					break;
				case 23:	
					accesoUsuario.setEnl_tarjetaNoAfiliadaEnrolada(true);
					break;
				case 24:
					accesoUsuario.setEnl_envioClaveDinamica(true);
					break;
				case 25:
					accesoUsuario.setEnl_notificacionEnrolamiento(true);
					break;	
				case 26:
					accesoUsuario.setEnl_descargarManual(true);
					break;
			}
			
		}
		
		return accesoUsuario;
	}
	
	
	@Action(value = "/logout", results = { 
			@Result (location = "/index.jsp", name = "success")
			})	
	public String logout() {
		
		
		try {
			
			Utilitario.getLOG_APP().info("****** INICIO:  - logout ******");	
			
			HttpSession session=ServletActionContext.getRequest().getSession(false); 
			
			if(session!=null){
				
				Enumeration e = session.getAttributeNames();
				while (e.hasMoreElements()) {
					String name = e.nextElement().toString();
					Utilitario.getLOG_APP().info("Se elimina session "+name);	
					session.removeAttribute(name);
				}
				session.invalidate();
				session = null;
				
			}
			Utilitario.getLOG_APP().info("****** FIN:  - logout ******");	
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error Loout ",e);
		}
		

		return SUCCESS;
	}

	
	
	//@Override
	public void setSession(Map<String, Object>  sessionMap) {
		this.sessionMap = (SessionMap<String, Object>)  sessionMap ;
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}


	public String getMensajeusuario() {
		return mensajeusuario;
	}


	public void setMensajeusuario(String mensajeusuario) {
		this.mensajeusuario = mensajeusuario;
	}
	

}
