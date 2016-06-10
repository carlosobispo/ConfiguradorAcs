package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T3DS_ISSUERBLOCKED",schema="SQMACS")
//@Table(name="T3DS_ISSUERBLOCKED")
public class IssuerBlockedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer intIdIssuer;
	private Integer intTimeBlocked;
	private String schState;
	
	@Id
	@Column(name = "IN_IDISSUER", nullable = false)	
	public Integer getIntIdIssuer() {
		return intIdIssuer;
	}
	public void setIntIdIssuer(Integer intIdIssuer) {
		this.intIdIssuer = intIdIssuer;
	}
	
	@Column(name = "IN_TIMEBLOCKED")	
	public Integer getIntTimeBlocked() {
		return intTimeBlocked;
	}
	public void setIntTimeBlocked(Integer intTimeBlocked) {
		this.intTimeBlocked = intTimeBlocked;
	}
	
	@Column(name = "CH_STATE",length=1)
	public String getSchState() {
		return schState;
	}
	public void setSchState(String schState) {
		this.schState = schState;
	}
	

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDISSUER="+ intIdIssuer+",");
		sb.append("IN_TIMEBLOCKED="+intTimeBlocked +",");
		sb.append("CH_STATE="+schState+"");
		return sb.toString();
	}
	
}
