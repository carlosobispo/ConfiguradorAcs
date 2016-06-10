package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TSM_BRAND",schema="SQMSM")
//@Table(name="TSM_BRAND")
public class BrandBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer intIdBrand;
	private String svcName;
	
	public BrandBean(){
		super();
	}
	
	
	@Id
	@Column(name = "IN_IDBRAND", nullable = false)	
	public Integer getIntIdBrand() {
		return intIdBrand;
	}
	public void setIntIdBrand(Integer intIdBrand) {
		this.intIdBrand = intIdBrand;
	}

	@Column(name = "VC_NAME", nullable = true, length = 30)	
	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	
	
	
	
}
