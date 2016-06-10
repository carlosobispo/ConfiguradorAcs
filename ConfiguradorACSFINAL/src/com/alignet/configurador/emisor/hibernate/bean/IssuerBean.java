package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T3DS_ISSUER" , schema="SQMACS")
//@Table(name="T3DS_ISSUER")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class IssuerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer intIdIssuer;
	private Integer intIdProcessor;
	private String svcName;
	private String svcInitials;
	private String svcLogoMember;
	private String svcLogobrand;
	private String svcUsuario;
	private String svcPassword;
	private Integer intIntentlogin;
	private String schAuthenticateby;
	private String svcURLauthenticate;
	private String schEnrolltype;
	private String svcNamezcmk;
	private String svcNamepvk;
	private Integer intUseblocked;
	private Integer intIdstate;
	private Integer intNumberuser;
	private String schCharpaddingpin;
	private String schCharpaddingpam;
	private String svcTabledecimalize;
	private String svcAdcText1;
	private String svcAdcText2;
	private String svcAdcText3;
	private String svcPamDefault;
	private Integer intMinPwd;
	private Integer intMaxPwd;
	private String svcDocbase;
	private String schUseforgottenpwd;
	private String svcAdcText4;
	private Date dtRegistry;
	private String schMatrixDecision;

	
	public IssuerBean(){
		
	}
	
	
	public IssuerBean(Integer intIdIssuer, String svcName, String svcInitials,String svcLogoMember,Integer intIdstate){
		this.intIdIssuer = intIdIssuer;
		this.svcName =	svcName;
		this.svcInitials = svcInitials;
		this.svcLogoMember = svcLogoMember;
		this.intIdstate = intIdstate;
	}
	
	
	@Id
	@Column(name="IN_IDISSUER" , nullable = false)
	public Integer getIntIdIssuer() {
		return intIdIssuer;
	}
	public void setIntIdIssuer(Integer intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}
	
	@Column(name="IN_IDPROCESSOR")
	public Integer getIntIdProcessor() {
		return intIdProcessor;
	}
	public void setIntIdProcessor(Integer intIdProcessor) {
		this.intIdProcessor = intIdProcessor;
	}
	
	@Column(name="VC_NAME",length=80)
	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	@Column(name="VC_INITIALS",length=10)
	public String getSvcInitials() {
		return svcInitials;
	}
	public void setSvcInitials(String svcInitials) {
		this.svcInitials = svcInitials;
	}
	
	@Column(name="VC_LOGOMEMBER",length=200)
	public String getSvcLogoMember() {
		return svcLogoMember;
	}
	public void setSvcLogoMember(String svcLogoMember) {
		this.svcLogoMember = svcLogoMember;
	}
	
	@Column(name="VC_LOGOBRAND",length=200)
	public String getSvcLogobrand() {
		return svcLogobrand;
	}
	public void setSvcLogobrand(String svcLogobrand) {
		this.svcLogobrand = svcLogobrand;
	}
	
	@Column(name="VC_USUARIO",length=50)
	public String getSvcUsuario() {
		return svcUsuario;
	}
	public void setSvcUsuario(String svcUsuario) {
		this.svcUsuario = svcUsuario;
	}
	
	@Column(name="VC_PASSWORD",length=50)
	public String getSvcPassword() {
		return svcPassword;
	}
	public void setSvcPassword(String svcPassword) {
		this.svcPassword = svcPassword;
	}
	
	@Column(name="IN_INTENTLOGIN")
	public Integer getIntIntentlogin() {
		return intIntentlogin;
	}
	public void setIntIntentlogin(Integer intIntentlogin) {
		this.intIntentlogin = intIntentlogin;
	}
	
	@Column(name="CH_AUTHENTICATEBY",length=1)
	public String getSchAuthenticateby() {
		return schAuthenticateby;
	}
	public void setSchAuthenticateby(String schAuthenticateby) {
		this.schAuthenticateby = schAuthenticateby;
	}
	
	@Column(name="VC_URLAUTHENTICATE",length=300)
	public String getSvcURLauthenticate() {
		return svcURLauthenticate;
	}
	public void setSvcURLauthenticate(String svcURLauthenticate) {
		this.svcURLauthenticate = svcURLauthenticate;
	}
	
	@Column(name="CH_ENROLLTYPE",length=1)
	public String getSchEnrolltype() {
		return schEnrolltype;
	}
	public void setSchEnrolltype(String schEnrolltype) {
		this.schEnrolltype = schEnrolltype;
	}
	
	@Column(name="VC_NAMEZCMK",length=70)
	public String getSvcNamezcmk() {
		return svcNamezcmk;
	}
	public void setSvcNamezcmk(String svcNamezcmk) {
		this.svcNamezcmk = svcNamezcmk;
	}
	
	@Column(name="VC_NAMEPVK",length=70)
	public String getSvcNamepvk() {
		return svcNamepvk;
	}
	public void setSvcNamepvk(String svcNamepvk) {
		this.svcNamepvk = svcNamepvk;
	}
	
	@Column(name="IN_USEBLOCKED")
	public Integer getIntUseblocked() {
		return intUseblocked;
	}
	public void setIntUseblocked(Integer intUseblocked) {
		this.intUseblocked = intUseblocked;
	}
	
	@Column(name="IN_IDSTATE")
	public Integer getIntIdstate() {
		return intIdstate;
	}
	public void setIntIdstate(Integer intIdstate) {
		this.intIdstate = intIdstate;
	}
	
	@Column(name="IN_NUMBERUSER")
	public Integer getIntNumberuser() {
		return intNumberuser;
	}
	public void setIntNumberuser(Integer intNumberuser) {
		this.intNumberuser = intNumberuser;
	}
	
	@Column(name="CH_CHARPADDINGPIN",length=1)
	public String getSchCharpaddingpin() {
		return schCharpaddingpin;
	}
	public void setSchCharpaddingpin(String schCharpaddingpin) {
		this.schCharpaddingpin = schCharpaddingpin;
	}
	
	@Column(name="CH_CHARPADDINGPAM",length=1)
	public String getSchCharpaddingpam() {
		return schCharpaddingpam;
	}
	public void setSchCharpaddingpam(String schCharpaddingpam) {
		this.schCharpaddingpam = schCharpaddingpam;
	}
	
	@Column(name="VC_TABLEDECIMALIZE",length=16)
	public String getSvcTabledecimalize() {
		return svcTabledecimalize;
	}
	public void setSvcTabledecimalize(String svcTabledecimalize) {
		this.svcTabledecimalize = svcTabledecimalize;
	}
	
	@Column(name="VC_ADCTEXT1",length=200)
	public String getSvcAdcText1() {
		return svcAdcText1;
	}
	public void setSvcAdcText1(String svcAdcText1) {
		this.svcAdcText1 = svcAdcText1;
	}
	
	@Column(name="VC_ADCTEXT2",length=400)
	public String getSvcAdcText2() {
		return svcAdcText2;
	}
	public void setSvcAdcText2(String svcAdcText2) {
		this.svcAdcText2 = svcAdcText2;
	}
	
	@Column(name="VC_ADCTEXT3",length=500)
	public String getSvcAdcText3() {
		return svcAdcText3;
	}
	public void setSvcAdcText3(String svcAdcText3) {
		this.svcAdcText3 = svcAdcText3;
	}
	
	@Column(name="VC_PAMDEFAULT",length=100)
	public String getSvcPamDefault() {
		return svcPamDefault;
	}
	public void setSvcPamDefault(String svcPamDefault) {
		this.svcPamDefault = svcPamDefault;
	}
	
	@Column(name="IN_MINPWD")
	public Integer getIntMinPwd() {
		return intMinPwd;
	}
	public void setIntMinPwd(Integer intMinPwd) {
		this.intMinPwd = intMinPwd;
	}
	
	@Column(name="IN_MAXPWD")
	public Integer getIntMaxPwd() {
		return intMaxPwd;
	}
	public void setIntMaxPwd(Integer intMaxPwd) {
		this.intMaxPwd = intMaxPwd;
	}
	
	@Column(name="CH_USEFORGOTTENPWD",length=1)
	public String getSchUseforgottenpwd() {
		return schUseforgottenpwd;
	}
	public void setSchUseforgottenpwd(String schUseforgottenpwd) {
		this.schUseforgottenpwd = schUseforgottenpwd;
	}
	
	
	@Column(name="VC_ADCTEXT4",length=300)
	public String getSvcAdcText4() {
		return svcAdcText4;
	}
	public void setSvcAdcText4(String svcAdcText4) {
		this.svcAdcText4 = svcAdcText4;
	}
	
	
	@Column(name="VC_DOCBASE")
	public String getSvcDocbase() {
		return svcDocbase;
	}
	public void setSvcDocbase(String svcDocbase) {
		this.svcDocbase = svcDocbase;
	}
	
	
	@Column(name="DT_REGISTRY")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtRegistry() {
		return dtRegistry;
	}
	public void setDtRegistry(Date dtRegistry) {
		this.dtRegistry = dtRegistry;
	}
	
	
	@Column(name="CH_MATRIXDECISION",length=1)
	public String getSchMatrixDecision() {
		return schMatrixDecision;
	}
	public void setSchMatrixDecision(String schMatrixDecision) {
		this.schMatrixDecision = schMatrixDecision;
	}
	/*
		@PrePersist
		protected void onCreate() {
			 dtRegistry = new Date();
		    if (dtRegistry == null) { dtRegistry = new Date(); }
		}*/
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDISSUER="+ intIdIssuer+",");
		sb.append("IN_IDPROCESSOR="+intIdProcessor +",");
		sb.append("VC_NAME="+svcName+",");
		sb.append("VC_INITIALS="+ svcInitials+",");
		sb.append("VC_LOGOMEMBER="+svcLogoMember +",");
		sb.append("VC_LOGOBRAND="+svcLogobrand +",");
		sb.append("VC_USUARIO="+svcUsuario +",");
		sb.append("VC_PASSWORD="+svcPassword +",");
		sb.append("IN_INTENTLOGIN="+intIntentlogin +",");
		sb.append("CH_AUTHENTICATEBY="+schAuthenticateby +",");
		sb.append("VC_URLAUTHENTICATE="+svcURLauthenticate +",");
		sb.append("CH_ENROLLTYPE="+schEnrolltype +",");
		sb.append("VC_NAMEZCMK="+svcNamezcmk +",");
		sb.append("VC_NAMEPVK="+svcNamepvk +",");
		sb.append("IN_USEBLOCKED="+intUseblocked +",");
		sb.append("IN_IDSTATE="+intIdstate +",");
		sb.append("IN_NUMBERUSER="+ intNumberuser +",");
		sb.append("CH_CHARPADDINGPIN="+ schCharpaddingpin +",");
		sb.append("CH_CHARPADDINGPAM="+schCharpaddingpam +",");
		sb.append("VC_TABLEDECIMALIZE="+svcTabledecimalize +",");
		sb.append("VC_PAMDEFAULT="+ svcPamDefault+",");
		sb.append("IN_MINPWD="+ intMinPwd+",");
		sb.append("IN_MAXPWD="+intMaxPwd +",");
		sb.append("VC_DOCBASE="+svcDocbase +",");
		sb.append("CH_USEFORGOTTENPWD="+schUseforgottenpwd +",");
		sb.append("CH_MATRIXDECISION="+schMatrixDecision);

		return sb.toString();
	}

}
