package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T3DS_ISSUERINTENTADC" , schema="SQMACS")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class AcsIssuerIntentADCBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Declaracion de atributos */
	private Integer in_idIssuer;
	private Integer in_intent;
	private String  ch_type;
	private String  ch_state;
	private Integer in_idAuthenmechan;
	private Integer in_idEnrollmechan;

	/**Descripcion*/
	private String  descripCh_type;
	private String  descripCh_state;
	
	/**Metodo Constructor*/
	public AcsIssuerIntentADCBean() {

	}
	
	public AcsIssuerIntentADCBean(Integer in_idIssuer, Integer in_intent, String ch_type, String ch_state, Integer in_idAuthenmechan, Integer in_idEnrollmechan) {

		this.in_idIssuer = in_idIssuer;
		this.in_intent = in_intent;
		this.ch_type = ch_type;
		this.ch_state = ch_state;
		this.in_idAuthenmechan = in_idAuthenmechan;
		this.in_idEnrollmechan = in_idEnrollmechan;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDISSUER")
	public Integer getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(Integer in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}
	
	@Column(name="IN_INTENT")
	public Integer getIn_intent() {
		return in_intent;
	}
	public void setIn_intent(Integer in_intent) {
		this.in_intent = in_intent;
	}

	@Column(name="CH_TYPE")
	public String getCh_type() {
		return ch_type;
	}
	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}

	@Column(name="CH_STATE")
	public String getCh_state() {
		return ch_state;
	}
	public void setCh_state(String ch_state) {
		this.ch_state = ch_state;
	}
	
	@Column(name="IN_IDAUTHENMECHAN")
	public Integer getIn_idAuthenmechan() {
		return in_idAuthenmechan;
	}
	public void setIn_idAuthenmechan(Integer in_idAuthenmechan) {
		this.in_idAuthenmechan = in_idAuthenmechan;
	}
	
	@Column(name="IN_IDENROLLMECHAN")
	public Integer getIn_idEnrollmechan() {
		return in_idEnrollmechan;
	}
	public void setIn_idEnrollmechan(Integer in_idEnrollmechan) {
		this.in_idEnrollmechan = in_idEnrollmechan;
	}

	/**Metodos descriptivos*/
	@Transient
	public String getDescripCh_type() {
		
		int int_ch_type = 10;
		descripCh_type = "";
		
		if ( (ch_type != null) && (!ch_type.equals("")) && (!ch_type.equals(" ")) ) {
			int_ch_type = Integer.parseInt(ch_type.trim());
		}
 
		switch (int_ch_type) {
		case 0:
			descripCh_type= "Por autenticación ACS y ACS Expres";
			break;
		case 1:
			descripCh_type= "Por declinación en ADC y ADC Expres";
			break;
		case 2:
			descripCh_type= "Por verificación en ADC";
			break;
		case 3:
			descripCh_type= "Por administrador de clientes para tarjetas afiliadas";
			break;
		case 4:
			descripCh_type= "Por SAT para tarjetas desafiliadas";
			break;	
		case 5:
			descripCh_type= "Por verifcación en ADC Expres";
			break;
		case 6:
			descripCh_type= "Por SAT para olvido de clave y tarjetas afiliadas";
			break;
		case 7:
			descripCh_type= "Por la funcionalidad de olvido de contraseña";
			break;
		case 8:
			descripCh_type= "Por teléfono celular no registrado en autenticación mediante SMS (Short Messages Services)";
			break;
		case 9:
			descripCh_type= "Por validación de mensaje SMS";
			break;	
		default:
			descripCh_type= "--";
			break;
		}
		
		return descripCh_type;
	}
	
	@Transient
	public String getDescripCh_state() {
		descripCh_state = "";		
		switch (Integer.parseInt(ch_state)) {
		case 0:
			descripCh_state= "Desabilitado";
			break;
		case 1:
			descripCh_state= "Habilitado";
			break;
		default:
			descripCh_state= "--";
			break;
		}
		return descripCh_state;
	}

	@Override
	public String toString() {
	
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDISSUER= "+ in_idIssuer+", ");
		sb.append("IN_INTENT= "+in_intent +", ");
		sb.append("CH_TYPE= "+ch_type+", ");
		sb.append("CH_STATE= "+ ch_state+", ");
		sb.append("IN_IDAUTHENMECHAN= "+in_idAuthenmechan+", ");
		sb.append("IN_IDENROLLMECHAN= "+ in_idEnrollmechan+", ");
		sb.append("descripCh_type= "+ getDescripCh_type()+", ");
		sb.append("descripCh_state= "+ getDescripCh_state());
		
		return sb.toString();
	}
}
