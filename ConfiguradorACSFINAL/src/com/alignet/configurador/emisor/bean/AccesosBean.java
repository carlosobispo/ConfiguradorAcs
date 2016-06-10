package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;

public class AccesosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer tipo_administrador_Alignet;
	private Integer tipo_administrador_Emisor;
	
	private String  fechaSistema;
	
	/*CONFIGURACION DEL MENU*/

	private boolean enl_configVisa;
	private boolean enl_configMastercard;
	
	private boolean enl_emisores;
	private boolean enl_usuarios;

	private boolean enl_configuracionLogo;
	private boolean enl_autenticacionClaveIncorrectaExpirada;
	private boolean enl_tarjetaBloqueada;
	private boolean enl_tarjetaNoAfiliadaEnrolada;
	private boolean enl_envioClaveDinamica;
	private boolean enl_descargarManual;

	private boolean enl_notificacionEnrolamiento;
	
	
	/*FALTA CONFIGURACION POR OPCIONES*/
	
	
	
	public AccesosBean(){
		this.fechaSistema =  Utilitario.getFechaSistema();
		
		this.tipo_administrador_Alignet = Parameters.PERFIL_ADMINISTRADORALIGNET;
		this.tipo_administrador_Emisor  = Parameters.PERFIL_ADMINISTRADOEMISOR;

		this.enl_emisores=false;
		this.enl_usuarios=false;
		this.enl_configVisa=false;
		this.enl_configMastercard=false;
		this.enl_autenticacionClaveIncorrectaExpirada=false;
		this.enl_tarjetaBloqueada=false;
		this.enl_tarjetaNoAfiliadaEnrolada=false;
		this.enl_descargarManual=false;
		this.enl_notificacionEnrolamiento=false;
	}

	public String getFechaSistema() {
		return fechaSistema;
	}

	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}

	public boolean isEnl_emisores() {
		return enl_emisores;
	}

	public void setEnl_emisores(boolean enl_emisores) {
		this.enl_emisores = enl_emisores;
	}

	public boolean isEnl_usuarios() {
		return enl_usuarios;
	}

	public void setEnl_usuarios(boolean enl_usuarios) {
		this.enl_usuarios = enl_usuarios;
	}

	public boolean isEnl_configVisa() {
		return enl_configVisa;
	}

	public void setEnl_configVisa(boolean enl_configVisa) {
		this.enl_configVisa = enl_configVisa;
	}

	public boolean isEnl_configMastercard() {
		return enl_configMastercard;
	}

	public void setEnl_configMastercard(boolean enl_configMastercard) {
		this.enl_configMastercard = enl_configMastercard;
	}

	public Integer getTipo_administrador_Alignet() {
		return tipo_administrador_Alignet;
	}

	public void setTipo_administrador_Alignet(Integer tipo_administrador_Alignet) {
		this.tipo_administrador_Alignet = tipo_administrador_Alignet;
	}

	public Integer getTipo_administrador_Emisor() {
		return tipo_administrador_Emisor;
	}

	public void setTipo_administrador_Emisor(Integer tipo_administrador_Emisor) {
		this.tipo_administrador_Emisor = tipo_administrador_Emisor;
	}

	public boolean isEnl_autenticacionClaveIncorrectaExpirada() {
		return enl_autenticacionClaveIncorrectaExpirada;
	}

	public void setEnl_autenticacionClaveIncorrectaExpirada(boolean enl_autenticacionClaveIncorrectaExpirada) {
		this.enl_autenticacionClaveIncorrectaExpirada = enl_autenticacionClaveIncorrectaExpirada;
	}

	public boolean isEnl_tarjetaBloqueada() {
		return enl_tarjetaBloqueada;
	}

	public void setEnl_tarjetaBloqueada(boolean enl_tarjetaBloqueada) {
		this.enl_tarjetaBloqueada = enl_tarjetaBloqueada;
	}

	public boolean isEnl_tarjetaNoAfiliadaEnrolada() {
		return enl_tarjetaNoAfiliadaEnrolada;
	}

	public void setEnl_tarjetaNoAfiliadaEnrolada(boolean enl_tarjetaNoAfiliadaEnrolada) {
		this.enl_tarjetaNoAfiliadaEnrolada = enl_tarjetaNoAfiliadaEnrolada;
	}

	public boolean isEnl_configuracionLogo() {
		return enl_configuracionLogo;
	}

	public void setEnl_configuracionLogo(boolean enl_configuracionLogo) {
		this.enl_configuracionLogo = enl_configuracionLogo;
	}

	public boolean isEnl_envioClaveDinamica() {
		return enl_envioClaveDinamica;
	}

	public void setEnl_envioClaveDinamica(boolean enl_envioClaveDinamica) {
		this.enl_envioClaveDinamica = enl_envioClaveDinamica;
	}

	public boolean isEnl_descargarManual() {
		return enl_descargarManual;
	}

	public void setEnl_descargarManual(boolean enl_descargarManual) {
		this.enl_descargarManual = enl_descargarManual;
	}

	public boolean isEnl_notificacionEnrolamiento() {
		return enl_notificacionEnrolamiento;
	}

	public void setEnl_notificacionEnrolamiento(boolean enl_notificacionEnrolamiento) {
		this.enl_notificacionEnrolamiento = enl_notificacionEnrolamiento;
	}
}
