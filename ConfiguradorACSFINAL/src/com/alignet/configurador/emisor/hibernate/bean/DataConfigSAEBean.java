package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TSM_DATACONFIG" , schema="SQMSM")
//@Table(name="TSM_DATACONFIG")
public class DataConfigSAEBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public static class Id implements Serializable{

		private static final long serialVersionUID = 1L;
		private Integer intIdIssuer;
		private String svcKeyname;
		
		public Id(){
		}
		
		public Id( Integer intIdIssuer,String svcKeyname){
			this.intIdIssuer=intIdIssuer;
			this.svcKeyname=svcKeyname;
		}
		
		@Column(name="IN_IDISSUER",nullable = false)	
		public Integer getIntIdIssuer() {
			return intIdIssuer;
		}
		public void setIntIdIssuer(Integer intIdIssuer) {
			this.intIdIssuer = intIdIssuer;
		}
		
		@Column(name="VC_KEYNAME",length=100)	
		public String getSvcKeyname() {
			return svcKeyname;
		}
		public void setSvcKeyname(String svcKeyname) {
			this.svcKeyname = svcKeyname;
		}
		
	}
	
	

	private Id id;
	private String svcValue;
	private String svcDescription;
	
	public DataConfigSAEBean() {
		
	}
	
	public DataConfigSAEBean(Id id,String svcValue,String svcDescription){
		this.id = id;
		this.svcValue = svcValue;
		this.svcDescription = svcDescription;
	}
	
	@EmbeddedId	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	
	@Column(name="VC_VALUE",length=1200)	
	public String getSvcValue() {
		return svcValue;
	}
	public void setSvcValue(String svcValue) {
		this.svcValue = svcValue;
	}
	
	@Column(name="VC_DESCRIPTION",length=300)	
	public String getSvcDescription() {
		return svcDescription;
	}
	public void setSvcDescription(String svcDescription) {
		this.svcDescription = svcDescription;
	}
	
	public String toString(){
		StringBuffer sb =  new StringBuffer();
		sb.append("IN_IDISSUER="+id.getIntIdIssuer()+",");
		sb.append("VC_KEYNAME="+id.getSvcKeyname()+",");
		sb.append("VC_VALUE="+getSvcValue()+",");
		sb.append("VC_DESCRIPTION="+getSvcDescription());
		return sb.toString();
	}
	
}
