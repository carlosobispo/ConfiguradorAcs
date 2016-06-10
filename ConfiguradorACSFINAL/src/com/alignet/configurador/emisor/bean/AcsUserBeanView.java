package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class AcsUserBeanView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Declaracion de variables */
	private String in_idUser;
	private String in_idIssuer;
	private String in_idRoll;
	private String vc_name;
	private String vc_login;
	private String vc_password;
	private String in_idState;
	
	/**Metodo Constructor*/
	public AcsUserBeanView() {
	
	}

	public AcsUserBeanView(String in_idUser, String in_idIssuer, String in_idRoll, String vc_name, String vc_login, String vc_password, String in_idState) {
		
		this.in_idUser = in_idUser;
		this.in_idIssuer = in_idIssuer;
		this.in_idRoll = in_idRoll;
		this.vc_name = vc_name;
		this.vc_login = vc_login;
		this.vc_password = vc_password;
		this.in_idState = in_idState;
	}
	
	/**Metodos Getter and Setter*/
	public String getIn_idUser() {
		return in_idUser;
	}
	public void setIn_idUser(String in_idUser) {
		this.in_idUser = in_idUser;
	}

	public String getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(String in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}

	public String getIn_idRoll() {
		return in_idRoll;
	}
	public void setIn_idRoll(String in_idRoll) {
		this.in_idRoll = in_idRoll;
	}

	public String getVc_name() {
		return vc_name;
	}
	public void setVc_name(String vc_name) {
		this.vc_name = vc_name;
	}

	public String getVc_login() {
		return vc_login;
	}
	public void setVc_login(String vc_login) {
		this.vc_login = vc_login;
	}

	public String getVc_password() {
		return vc_password;
	}
	public void setVc_password(String vc_password) {
		this.vc_password = vc_password;
	}

	public String getIn_idState() {
		return in_idState;
	}
	public void setIn_idState(String in_idState) {
		this.in_idState = in_idState;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("in_idUser= "+ in_idUser+", ");
		sb.append("in_idIssuer= "+in_idIssuer +", ");
		sb.append("in_idRoll= "+in_idRoll+", ");
		sb.append("vc_name= "+ vc_name+", ");
		sb.append("vc_login= "+vc_login +", ");
		sb.append("vc_password= "+vc_password +", ");
		sb.append("in_idState= "+in_idState +", ");
		
		return super.toString();
	} 
}
