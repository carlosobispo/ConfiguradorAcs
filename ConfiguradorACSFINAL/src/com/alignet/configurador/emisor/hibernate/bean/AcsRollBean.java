package com.alignet.configurador.emisor.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="T3DS_ROLL" , schema="SQMES")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class AcsRollBean {

	/**Declaracion de atributos*/
	private Integer in_idRoll;
	private String  vc_name;
	
	/**Metodo Constructor*/
	public AcsRollBean() {
		
	}

	public AcsRollBean(Integer in_idRoll, String vc_name) {
		this.in_idRoll = in_idRoll;
		this.vc_name = vc_name;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDROLL" , nullable = false)
	public Integer getIn_idRoll() {
		return in_idRoll;
	}
	public void setIn_idRoll(Integer in_idRoll) {
		this.in_idRoll = in_idRoll;
	}
	
	@Column(name="VC_NAME")
	public String getVc_name() {
		return vc_name;
	}
	public void setVc_name(String vc_name) {
		this.vc_name = vc_name;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("IN_IDROLL= "+ in_idRoll+", ");
		sb.append("VC_NAME= "+ vc_name +", ");
				
		return sb.toString();
	}
	
	
}
