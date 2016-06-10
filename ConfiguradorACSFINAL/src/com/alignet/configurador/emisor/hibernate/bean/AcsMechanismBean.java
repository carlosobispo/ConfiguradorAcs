package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="T3DS_MECHANISM" , schema="SQMES")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AcsMechanismBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**Decleracion de Variables*/
	private Integer   in_idMechanism;   
	private String    vc_description;  
	private String    vc_description1;
	
	/**Relacines ORM*/
	@OneToOne
	private AcsIssuermechanBean acsIssuermechanBean;
	
	/**Metodos Constructores*/
	public AcsMechanismBean() {
		
	}

	public AcsMechanismBean(Integer in_idMechanism) {
		this.in_idMechanism = in_idMechanism;
	}

	public AcsMechanismBean(Integer in_idMechanism, String vc_description, String vc_description1) {
		this.in_idMechanism = in_idMechanism;
		this.vc_description = vc_description;
		this.vc_description1 = vc_description1;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDMECHANISM" , nullable = false)
	public Integer getIn_idMechanism() {
		return in_idMechanism;
	}
	public void setIn_idMechanism(Integer in_idMechanism) {
		this.in_idMechanism = in_idMechanism;
	}

	@Column(name="VC_DESCRIPTION")
	public String getVc_description() {
		return vc_description;
	}
	public void setVc_description(String vc_description) {
		this.vc_description = vc_description;
	}
	
	@Column(name="VC_DESCRIPTION1")
	public String getVc_description1() {
		return vc_description1;
	}
	public void setVc_description1(String vc_description1) {
		this.vc_description1 = vc_description1;
	}
	
	public AcsIssuermechanBean getAcsIssuermechanBean() {
		return acsIssuermechanBean;
	}
	public void setAcsIssuermechanBean(AcsIssuermechanBean acsIssuermechanBean) {
		this.acsIssuermechanBean = acsIssuermechanBean;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDMECHANISM= "+ in_idMechanism+", ");
		sb.append("VC_DESCRIPTION= "+vc_description +", ");
		sb.append("VC_DESCRIPTION1= "+vc_description1+", ");			
		return sb.toString();
	}
 
}
