package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class AcsIssuermechanView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**Declaracion de variables*/
	private String   in_idIssuermechan;
	private String   in_idIssuer;
	private String   in_idMechanism;
	private String   in_idState;
	private String   in_idAuthenmechan;
	private String   in_idEnrollmechan;
	private String   dt_dateTime;
		
	/**Metodo Costructor*/
	public AcsIssuermechanView() {
		
	}
	
	public AcsIssuermechanView(String in_idIssuermechan, String in_idIssuer,
			String in_idMechanism, String in_idState, String in_idAuthenmechan,
			String in_idEnrollmechan, String dt_dateTime) {
		super();
		this.in_idIssuermechan = in_idIssuermechan;
		this.in_idIssuer = in_idIssuer;
		this.in_idMechanism = in_idMechanism;
		this.in_idState = in_idState;
		this.in_idAuthenmechan = in_idAuthenmechan;
		this.in_idEnrollmechan = in_idEnrollmechan;
		this.dt_dateTime = dt_dateTime;
	}

	/**Metodos Getter and setter*/

	public String getIn_idIssuermechan() {
		return in_idIssuermechan;
	}

	public void setIn_idIssuermechan(String in_idIssuermechan) {
		this.in_idIssuermechan = in_idIssuermechan;
	}

	
	public String getIn_idIssuer() {
		return in_idIssuer;
	}

	public void setIn_idIssuer(String in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}

	
	public String getIn_idMechanism() {
		return in_idMechanism;
	}

	public void setIn_idMechanism(String in_idMechanism) {
		this.in_idMechanism = in_idMechanism;
	}

	
	public String getIn_idState() {
		return in_idState;
	}

	public void setIn_idState(String in_idState) {
		this.in_idState = in_idState;
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

	
	public String getDt_dateTime() {
		return dt_dateTime;
	}

	public void setDt_dateTime(String dt_dateTime) {
		this.dt_dateTime = dt_dateTime;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("in_idIssuermechan= "+ in_idIssuermechan+", ");
		sb.append("in_idIssuer= "+in_idIssuer +", ");
		sb.append("in_idMechanism= "+in_idMechanism+", ");
		sb.append("in_idState= "+ in_idState+", ");
		sb.append("in_idAuthenmechan= "+ in_idAuthenmechan +", ");
		sb.append("in_idEnrollmechan= "+in_idEnrollmechan +", ");
		sb.append("dt_dateTime= "+dt_dateTime);
		
		return super.toString();
	}
}

