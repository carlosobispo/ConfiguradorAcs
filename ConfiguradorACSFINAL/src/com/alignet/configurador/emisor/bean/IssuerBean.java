package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class IssuerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String intIdIssuer;
	private String svcName;
	private String svcInitials;
	private String svcLogoMember;
	private String schAuthenticateby;
	private String schEnrolltype;
	private String schCharpaddingpin;
	private String schCharpaddingpam;
	private String intIdstate;
	
	private String sclaseimplementacion;
	private String sflagTiempoDesbloqueo;
	private String shorasDesbloqueo;
	private String sflagMatrixDecision;
	private String sflagAttemps;
	private String sflagPantallaTarjetaNoAfiliada;
	
	private String sflagHabilitarSMS;
	//private String snameImplemntacionWS;
	private String snameCarrier;
	//private String surlWSTelco;
	private String scarrierClassImpl;
	
	
	private String sflagHabilitarEmail;	
	private String snameHost;
	private String spuerto;
	private String susuarioEmail;
	private String sclaveEmail;
	
	private String svcAdcText2;
	private String svcAdcText3;
	private String svcAdcText4;
	
	
	
	public IssuerBean(Integer intIdIssuer, String svcName, String svcInitials,String svcLogoMember,Integer intIdstate){
		this.setIntIdIssuer(intIdIssuer.toString());
		this.svcName=svcName;
		this.svcInitials=svcInitials;
		this.svcLogoMember=svcLogoMember;
		this.setIntIdstate(intIdstate.toString());
	}
	
	public IssuerBean(){
		
	}

	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	public String getSvcInitials() {
		return svcInitials;
	}
	public void setSvcInitials(String svcInitials) {
		this.svcInitials = svcInitials;
	}
	public String getSvcLogoMember() {
		return svcLogoMember;
	}
	public void setSvcLogoMember(String svcLogoMember) {
		this.svcLogoMember = svcLogoMember;
	}

	public String getIntIdIssuer() {
		return intIdIssuer;
	}

	public void setIntIdIssuer(String intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}

	public String getIntIdstate() {
		return intIdstate;
	}

	public void setIntIdstate(String intIdstate) {
		this.intIdstate = intIdstate;
	}

	public String getSchAuthenticateby() {
		return schAuthenticateby;
	}

	public void setSchAuthenticateby(String schAuthenticateby) {
		this.schAuthenticateby = schAuthenticateby;
	}

	public String getSchEnrolltype() {
		return schEnrolltype;
	}

	public void setSchEnrolltype(String schEnrolltype) {
		this.schEnrolltype = schEnrolltype;
	}

	public String getSchCharpaddingpin() {
		return schCharpaddingpin;
	}

	public void setSchCharpaddingpin(String schCharpaddingpin) {
		this.schCharpaddingpin = schCharpaddingpin;
	}



	public String getShorasDesbloqueo() {
		return shorasDesbloqueo;
	}

	public void setShorasDesbloqueo(String shorasDesbloqueo) {
		this.shorasDesbloqueo = shorasDesbloqueo;
	}


	public String getSclaseimplementacion() {
		return sclaseimplementacion;
	}

	public void setSclaseimplementacion(String sclaseimplementacion) {
		this.sclaseimplementacion = sclaseimplementacion;
	}

	public String getSflagTiempoDesbloqueo() {
		return sflagTiempoDesbloqueo;
	}

	public void setSflagTiempoDesbloqueo(String sflagTiempoDesbloqueo) {
		this.sflagTiempoDesbloqueo = sflagTiempoDesbloqueo;
	}

	public String getSflagAttemps() {
		return sflagAttemps;
	}

	public void setSflagAttemps(String sflagAttemps) {
		this.sflagAttemps = sflagAttemps;
	}

	public String getSflagPantallaTarjetaNoAfiliada() {
		return sflagPantallaTarjetaNoAfiliada;
	}

	public void setSflagPantallaTarjetaNoAfiliada(String sflagPantallaTarjetaNoAfiliada) {
		this.sflagPantallaTarjetaNoAfiliada = sflagPantallaTarjetaNoAfiliada;
	}

	public String getSflagHabilitarSMS() {
		return sflagHabilitarSMS;
	}

	public void setSflagHabilitarSMS(String sflagHabilitarSMS) {
		this.sflagHabilitarSMS = sflagHabilitarSMS;
	}

	public String getSnameCarrier() {
		return snameCarrier;
	}

	public void setSnameCarrier(String snameCarrier) {
		this.snameCarrier = snameCarrier;
	}

	public String getSflagHabilitarEmail() {
		return sflagHabilitarEmail;
	}

	public void setSflagHabilitarEmail(String sflagHabilitarEmail) {
		this.sflagHabilitarEmail = sflagHabilitarEmail;
	}

	public String getSnameHost() {
		return snameHost;
	}

	public void setSnameHost(String snameHost) {
		this.snameHost = snameHost;
	}

	public String getSpuerto() {
		return spuerto;
	}

	public void setSpuerto(String spuerto) {
		this.spuerto = spuerto;
	}

	public String getSusuarioEmail() {
		return susuarioEmail;
	}

	public void setSusuarioEmail(String susuarioEmail) {
		this.susuarioEmail = susuarioEmail;
	}

	public String getSclaveEmail() {
		return sclaveEmail;
	}

	public void setSclaveEmail(String sclaveEmail) {
		this.sclaveEmail = sclaveEmail;
	}

	public String getSflagMatrixDecision() {
		return sflagMatrixDecision;
	}

	public void setSflagMatrixDecision(String sflagMatrixDecision) {
		this.sflagMatrixDecision = sflagMatrixDecision;
	}

	public String getSchCharpaddingpam() {
		return schCharpaddingpam;
	}

	public void setSchCharpaddingpam(String schCharpaddingpam) {
		this.schCharpaddingpam = schCharpaddingpam;
	}

	public String getSvcAdcText2() {
		return svcAdcText2;
	}

	public void setSvcAdcText2(String svcAdcText2) {
		this.svcAdcText2 = svcAdcText2;
	}

	public String getSvcAdcText3() {
		return svcAdcText3;
	}

	public void setSvcAdcText3(String svcAdcText3) {
		this.svcAdcText3 = svcAdcText3;
	}

	public String getSvcAdcText4() {
		return svcAdcText4;
	}

	public void setSvcAdcText4(String svcAdcText4) {
		this.svcAdcText4 = svcAdcText4;
	}

	public String getScarrierClassImpl() {
		return scarrierClassImpl;
	}

	public void setScarrierClassImpl(String scarrierClassImpl) {
		this.scarrierClassImpl = scarrierClassImpl;
	}



	
	
	
	



}
