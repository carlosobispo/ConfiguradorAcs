package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TSM_USER" , schema="SQMSM")
//@Table(name="TSM_USER")
//@org.hibernate.annotations.Entity(dynamicInsert=true)
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer intIdUser;
	private Integer intIdProcessor;
	private Integer intIdIssuer;
	private Integer intType;
	private Integer intProfile;
	private String 	svcName;
	private String svcLogin;
	private String svcPassword;
	private Integer intCardmask;
	private String svcXMLaccess;
	private String svcBrands;
	private String svcPermissions;
	private Integer intIntent;
	private Integer intIntentUpdPwd;
	private Integer intMaxDayUpdatePWD;
	private Date dtUpdatePWD;
	private Integer intState;
	private Date dtRegistry;
	private Date dtUpdate;
	private Integer intIdUseraction;
	private Integer intAccessType;
	private Integer intExportData;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IN_IDUSER" , nullable = false)
	public Integer getIntIdUser() {
		return intIdUser;
	}
	public void setIntIdUser(Integer intIdUser) {
		this.intIdUser = intIdUser;
	}
	
	@Column(name="IN_IDPROCESSOR")
	public Integer getIntIdProcessor() {
		return intIdProcessor;
	}
	public void setIntIdProcessor(Integer intIdProcessor) {
		this.intIdProcessor = intIdProcessor;
	}
	
	@Column(name="IN_IDISSUER")
	public Integer getIntIdIssuer() {
		return intIdIssuer;
	}
	public void setIntIdIssuer(Integer intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}
	
	@Column(name="IN_TYPE",nullable=false)
	public Integer getIntType() {
		return intType;
	}
	public void setIntType(Integer intType) {
		this.intType = intType;
	}
	
	@Column(name="IN_PROFILE",nullable=false)
	public Integer getIntProfile() {
		return intProfile;
	}
	public void setIntProfile(Integer intProfile) {
		this.intProfile = intProfile;
	}
	
	@Column(name="VC_NAME",length=50)
	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	@Column(name="VC_LOGIN",length=50)
	public String getSvcLogin() {
		return svcLogin;
	}
	public void setSvcLogin(String svcLogin) {
		this.svcLogin = svcLogin;
	}
	
	@Column(name="VC_PASSWORD",length=50)
	public String getSvcPassword() {
		return svcPassword;
	}
	public void setSvcPassword(String svcPassword) {
		this.svcPassword = svcPassword;
	}
	
	@Column(name="IN_CARDMASK")
	public Integer getIntCardmask() {
		return intCardmask;
	}
	public void setIntCardmask(Integer intCardmask) {
		this.intCardmask = intCardmask;
	}
	
	@Column(name="VC_XMLACCESS",length=9000)
	public String getSvcXMLaccess() {
		return svcXMLaccess;
	}
	public void setSvcXMLaccess(String svcXMLaccess) {
		this.svcXMLaccess = svcXMLaccess;
	}
	
	@Column(name="VC_BRANDS",length=20)
	public String getSvcBrands() {
		return svcBrands;
	}
	public void setSvcBrands(String svcBrands) {
		this.svcBrands = svcBrands;
	}
	
	@Column(name="VC_PERMISSIONS",length=50)
	public String getSvcPermissions() {
		return svcPermissions;
	}
	public void setSvcPermissions(String svcPermissions) {
		this.svcPermissions = svcPermissions;
	}
	
	@Column(name="IN_INTENT")
	public Integer getIntIntent() {
		return intIntent;
	}
	public void setIntIntent(Integer intIntent) {
		this.intIntent = intIntent;
	}
	
	@Column(name="IN_INTENTUPDPWD")
	public Integer getIntIntentUpdPwd() {
		return intIntentUpdPwd;
	}
	public void setIntIntentUpdPwd(Integer intIntentUpdPwd) {
		this.intIntentUpdPwd = intIntentUpdPwd;
	}
	
	@Column(name="IN_MAXDAYUPDATEPWD")
	public Integer getIntMaxDayUpdatePWD() {
		return intMaxDayUpdatePWD;
	}
	public void setIntMaxDayUpdatePWD(Integer intMaxDayUpdatePWD) {
		this.intMaxDayUpdatePWD = intMaxDayUpdatePWD;
	}
	
	@Column(name="DT_UPDATEPWD")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtUpdatePWD() {
		return dtUpdatePWD;
	}
	public void setDtUpdatePWD(Date dtUpdatePWD) {
		this.dtUpdatePWD = dtUpdatePWD;
	}
	
	@Column(name="IN_STATE",nullable=false)
	public Integer getIntState() {
		return intState;
	}
	public void setIntState(Integer intState) {
		this.intState = intState;
	}
	
	@Column(name="DT_REGISTRY",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtRegistry() {
		return dtRegistry;
	}
	public void setDtRegistry(Date dtRegistry) {
		this.dtRegistry = dtRegistry;
	}
	
	@Column(name="DT_UPDATE",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	
	@Column(name="IN_IDUSERACTION")
	public Integer getIntIdUseraction() {
		return intIdUseraction;
	}
	public void setIntIdUseraction(Integer intIdUseraction) {
		this.intIdUseraction = intIdUseraction;
	}
	
	@Column(name="IN_ACCESSTYPE")
	public Integer getIntAccessType() {
		return intAccessType;
	}
	public void setIntAccessType(Integer intAccessType) {
		this.intAccessType = intAccessType;
	}
	
	@Column(name="IN_EXPORTDATA")
	public Integer getIntExportData() {
		return intExportData;
	}
	public void setIntExportData(Integer intExportData) {
		this.intExportData = intExportData;
	}

	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDUSER="+ intIdUser+",");
		sb.append("IN_IDPROCESSOR="+ intIdProcessor+",");
		sb.append("IN_IDISSUER="+intIdIssuer +",");
		sb.append("IN_TYPE="+intType +",");
		sb.append("IN_PROFILE="+intProfile +",");
		sb.append("VC_NAME="+svcName +",");
		sb.append("VC_LOGIN="+ svcLogin +",");
		sb.append("VC_PASSWORD="+svcPassword +",");
		sb.append("IN_CARDMASK="+intCardmask +",");
		sb.append("IN_INTENT="+intIntent +",");
		sb.append("IN_STATE="+ intState);

		return sb.toString();
	}
	
}
