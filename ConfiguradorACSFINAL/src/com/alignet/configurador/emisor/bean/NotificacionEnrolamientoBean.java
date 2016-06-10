package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class NotificacionEnrolamientoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	// CONFIGURACION SMS
	
	private String sflagTieneConfiguracionSMS;
	private String stextViewConfiguracionSMS;
	private String stextConfiguracionSMS;
	
	// CONFIGURACION EMAIL
	
	private String sflagTieneConfiguracionEmail;
	private String stextRemitenteEmail;
	private String stextViewAsunto;
	private String stextAsunto;
	private String stextConfiguracionEmail; 
	private String sflagSaludoInicial;
	private String stextSaludoInicial;
	
	private String stextParrafo1;
	private String sflagParrafo2;
	private String stextParrafo2;
	private String sflagParrafo3;
	private String stextParrafo3;
	
	
	public String getStextAsunto() {
		return stextAsunto;
	}
	public void setStextAsunto(String stextAsunto) {
		this.stextAsunto = stextAsunto;
	}
	public String getStextConfiguracionEmail() {
		return stextConfiguracionEmail;
	}
	public void setStextConfiguracionEmail(String stextConfiguracionEmail) {
		this.stextConfiguracionEmail = stextConfiguracionEmail;
	}
	public String getSflagSaludoInicial() {
		return sflagSaludoInicial;
	}
	public void setSflagSaludoInicial(String sflagSaludoInicial) {
		this.sflagSaludoInicial = sflagSaludoInicial;
	}
	public String getStextParrafo1() {
		return stextParrafo1;
	}
	public void setStextParrafo1(String stextParrafo1) {
		this.stextParrafo1 = stextParrafo1;
	}
	public String getSflagParrafo2() {
		return sflagParrafo2;
	}
	public void setSflagParrafo2(String sflagParrafo2) {
		this.sflagParrafo2 = sflagParrafo2;
	}
	public String getStextParrafo2() {
		return stextParrafo2;
	}
	public void setStextParrafo2(String stextParrafo2) {
		this.stextParrafo2 = stextParrafo2;
	}
	public String getSflagParrafo3() {
		return sflagParrafo3;
	}
	public void setSflagParrafo3(String sflagParrafo3) {
		this.sflagParrafo3 = sflagParrafo3;
	}
	public String getStextParrafo3() {
		return stextParrafo3;
	}
	public void setStextParrafo3(String stextParrafo3) {
		this.stextParrafo3 = stextParrafo3;
	}

	
	
	public String getSflagTieneConfiguracionSMS() {
		return sflagTieneConfiguracionSMS;
	}
	public void setSflagTieneConfiguracionSMS(String sflagTieneConfiguracionSMS) {
		this.sflagTieneConfiguracionSMS = sflagTieneConfiguracionSMS;
	}
	public String getStextViewConfiguracionSMS() {
		return stextViewConfiguracionSMS;
	}
	public void setStextViewConfiguracionSMS(String stextViewConfiguracionSMS) {
		this.stextViewConfiguracionSMS = stextViewConfiguracionSMS;
	}
	public String getStextConfiguracionSMS() {
		return stextConfiguracionSMS;
	}
	public void setStextConfiguracionSMS(String stextConfiguracionSMS) {
		this.stextConfiguracionSMS = stextConfiguracionSMS;
	}
	public String getSflagTieneConfiguracionEmail() {
		return sflagTieneConfiguracionEmail;
	}
	public void setSflagTieneConfiguracionEmail(String sflagTieneConfiguracionEmail) {
		this.sflagTieneConfiguracionEmail = sflagTieneConfiguracionEmail;
	}
	public String getStextRemitenteEmail() {
		return stextRemitenteEmail;
	}
	public void setStextRemitenteEmail(String stextRemitenteEmail) {
		this.stextRemitenteEmail = stextRemitenteEmail;
	}
	public String getStextViewAsunto() {
		return stextViewAsunto;
	}
	public void setStextViewAsunto(String stextViewAsunto) {
		this.stextViewAsunto = stextViewAsunto;
	}
	public String getStextSaludoInicial() {
		return stextSaludoInicial;
	}
	public void setStextSaludoInicial(String stextSaludoInicial) {
		this.stextSaludoInicial = stextSaludoInicial;
	}
	
	
	
	
}
