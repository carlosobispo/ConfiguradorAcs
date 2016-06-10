package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T3DS_USER" , schema="SQMES")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class AcsUserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Declaracion de variables */
	private Integer in_idUser;
	private Integer in_idIssuer;
	private Integer in_idRoll;
	private String vc_name;
	private String vc_login;
	private String vc_password;
	private Integer in_idState;

	/**Descripcion */
	private String descripIn_idState;
	
	/**Metodo Constructor*/
	public AcsUserBean() {
		
	}

	public AcsUserBean(Integer in_idUser, Integer in_idIssuer, Integer in_idRoll, String vc_name, String vc_login, String vc_password, Integer in_idState) {
		this.in_idUser = in_idUser;
		this.in_idIssuer = in_idIssuer;
		this.in_idRoll = in_idRoll;
		this.vc_name = vc_name;
		this.vc_login = vc_login;
		this.vc_password = vc_password;
		this.in_idState = in_idState;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDUSER" , nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIn_idUser() {
		return in_idUser;
	}
	public void setIn_idUser(Integer in_idUser) {
		this.in_idUser = in_idUser;
	}
	
	@Column(name="IN_IDISSUER")
	public Integer getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(Integer in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}
	
	@Column(name="IN_IDROLL")
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
	
	@Column(name="VC_LOGIN")
	public String getVc_login() {
		return vc_login;
	}
	public void setVc_login(String vc_login) {
		this.vc_login = vc_login;
	}
	
	@Column(name="VC_PASSWORD")
	public String getVc_password() {
		return vc_password;
	}
	public void setVc_password(String vc_password) {
		this.vc_password = vc_password;
	}
	
	@Column(name="IN_IDSTATE")
	public Integer getIn_idState() {
		return in_idState;
	}
	public void setIn_idState(Integer in_idState) {
		this.in_idState = in_idState;
	}
	
	/**Metodos de descripcion*/
	@Transient
	public String getDescripIn_idState() {
		descripIn_idState = "--";
		
		if ( (in_idState != null) && (!in_idState.equals("")) && (!in_idState.equals(" ")) ) {
			switch (in_idState) {
			case 0:
				descripIn_idState= "Desabilitado";
				break;
			case 1:
				descripIn_idState= "Habilitado";
				break;
			default:
				descripIn_idState= "--";
				break;
			}
		}
		
		return descripIn_idState;

	}

	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("IN_IDUSER= "+ in_idUser+", ");
		sb.append("IN_IDISSUER= "+in_idIssuer +", ");
		sb.append("IN_IDROLL= "+in_idRoll+", ");
		sb.append("VC_NAME= "+ vc_name+", ");
		sb.append("VC_LOGIN= "+ vc_login +", ");
		sb.append("VC_PASSWORD= "+vc_password +", ");
		sb.append("IN_IDSTATE= "+in_idState);
		
		return super.toString();
	}
	
}
