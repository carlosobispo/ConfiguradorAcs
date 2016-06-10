package com.alignet.configurador.emisor.bean;

import java.io.File;
import java.util.Date;

public class FileBean {
	
	private Integer intIdFile;
	private Integer intType;
	private Integer intIdReference;
	private Integer intIdIssuer;
	private Date dtRegistry;
	
	private File fileLogoIssuer;
	private String fileLogoIssuerFileName;
	private String fileLogoIssuerContentType;	
	
	private byte[] blFile;
	
	
	public FileBean(){
		
	}
	
	public FileBean(Integer intIdFile,Integer intType,Integer intIdReference,Integer intIdIssuer,byte[] blFile){
		this.intIdFile=intIdFile;
		this.intType=intType;
		this.intIdReference=intIdReference;
		this.intIdIssuer=intIdIssuer;
		this.blFile=blFile;
	}
	
	
	
	public Integer getIntIdFile() {
		return intIdFile;
	}

	public void setIntIdFile(Integer intIdFile) {
		this.intIdFile = intIdFile;
	}

	public Integer getIntType() {
		return intType;
	}

	public void setIntType(Integer intType) {
		this.intType = intType;
	}

	public Integer getIntIdReference() {
		return intIdReference;
	}

	public void setIntIdReference(Integer intIdReference) {
		this.intIdReference = intIdReference;
	}

	public Integer getIntIdIssuer() {
		return intIdIssuer;
	}

	public void setIntIdIssuer(Integer intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}

	public Date getDtRegistry() {
		return dtRegistry;
	}

	public void setDtRegistry(Date dtRegistry) {
		this.dtRegistry = dtRegistry;
	}

	public File getFileLogoIssuer() {
		return fileLogoIssuer;
	}

	public void setFileLogoIssuer(File fileLogoIssuer) {
		this.fileLogoIssuer = fileLogoIssuer;
	}

	public String getFileLogoIssuerFileName() {
		return fileLogoIssuerFileName;
	}

	public void setFileLogoIssuerFileName(String fileLogoIssuerFileName) {
		this.fileLogoIssuerFileName = fileLogoIssuerFileName;
	}

	public String getFileLogoIssuerContentType() {
		return fileLogoIssuerContentType;
	}

	public void setFileLogoIssuerContentType(String fileLogoIssuerContentType) {
		this.fileLogoIssuerContentType = fileLogoIssuerContentType;
	}

	public byte[] getBlFile() {
		return blFile;
	}

	public void setBlFile(byte[] blFile) {
		this.blFile = blFile;
	}
	
}
