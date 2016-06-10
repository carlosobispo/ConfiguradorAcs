package com.alignet.configurador.emisor.hibernate.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T3DS_FILE" , schema="SQMACS")
//@Table(name="T3DS_FILE")
@org.hibernate.annotations.Entity(dynamicInsert=true)
public class FileBean {
	private Integer intIdFile;
	private Integer intType;
	private Integer intIdReference;
	private Integer intIdIssuer;
	private Date dtRegistry;
	private byte[] blFile;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IN_IDFILE" , nullable = false)
	public Integer getIntIdFile() {
		return intIdFile;
	}
	public void setIntIdFile(Integer intIdFile) {
		this.intIdFile = intIdFile;
	}
	
	@Column(name="IN_TYPE")
	public Integer getIntType() {
		return intType;
	}
	public void setIntType(Integer intType) {
		this.intType = intType;
	}
	
	@Column(name="IN_IDREFERENCE")
	public Integer getIntIdReference() {
		return intIdReference;
	}
	public void setIntIdReference(Integer intIdReference) {
		this.intIdReference = intIdReference;
	}
	@Column(name="IN_IDISSUER" , nullable = false)	
	public Integer getIntIdIssuer() {
		return intIdIssuer;
	}
	public void setIntIdIssuer(Integer intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}
	
	@Column(name="DT_REGISTRY")
	@Temporal(TemporalType.TIMESTAMP)	
	public Date getDtRegistry() {
		return dtRegistry;
	}
	public void setDtRegistry(Date dtRegistry) {
		this.dtRegistry = dtRegistry;
	}
	
	@Column( name = "BL_FILE" )
	@Lob()
	public byte[] getBlFile() {
		return blFile;
	}
	public void setBlFile(byte[] blFile) {
		this.blFile = blFile;
	}

	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDFILE="+ intIdFile+",");
		sb.append("IN_TYPE="+ intType+",");
		sb.append("IN_IDREFERENCE="+intIdReference +",");
		sb.append("IN_IDISSUER="+intIdIssuer +",");
		sb.append("DT_REGISTRY="+dtRegistry +",");
		sb.append("BL_FILE="+blFile);
		return sb.toString();
	}
	
	
	
}