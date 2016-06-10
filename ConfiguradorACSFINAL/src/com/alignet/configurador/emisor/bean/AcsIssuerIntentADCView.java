package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class AcsIssuerIntentADCView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Declaracion de atributos */
	private String in_idIssuer;
	private String in_intent;
	private String ch_type;
	private String ch_state;
	private String in_idAuthenmechan;
	private String in_idEnrollmechan;
	
	/**Constructor*/
	public AcsIssuerIntentADCView() {
		
	}

	public AcsIssuerIntentADCView(String in_idIssuer, String in_intent, String ch_type, String ch_state, String in_idAuthenmechan, String in_idEnrollmechan) {
		this.in_idIssuer = in_idIssuer;
		this.in_intent = in_intent;
		this.ch_type = ch_type;
		this.ch_state = ch_state;
		this.in_idAuthenmechan = in_idAuthenmechan;
		this.in_idEnrollmechan = in_idEnrollmechan;
	}
	
	/**Metodos Getter and Setter*/
	public String getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(String in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}

	public String getIn_intent() {
		return in_intent;
	}
	public void setIn_intent(String in_intent) {
		this.in_intent = in_intent;
	}

	public String getCh_type() {
		return ch_type;
	}
	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}

	public String getCh_state() {
		return ch_state;
	}
	public void setCh_state(String ch_state) {
		this.ch_state = ch_state;
	}

	public String getIn_idAuthenmechan() {
		return in_idAuthenmechan;
	}
	public void setIn_idAuthenmechan(String in_idAuthenmechan) {
		this.in_idAuthenmechan = in_idAuthenmechan;
	}

	public String getIn_idEnrollmechan() {
		return in_idEnrollmechan;
	}
	public void setIn_idEnrollmechan(String in_idEnrollmechan) {
		this.in_idEnrollmechan = in_idEnrollmechan;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("in_idIssuer= "+ in_idIssuer+", ");
		sb.append("in_intent= "+in_intent +", ");
		sb.append("ch_type= "+ch_type+", ");
		sb.append("ch_state= "+ ch_state+", ");
		sb.append("in_idAuthenmechan= "+in_idAuthenmechan+", ");
		sb.append("in_idEnrollmechan= "+ in_idEnrollmechan+", ");
						
		return super.toString();
	}
	
}
