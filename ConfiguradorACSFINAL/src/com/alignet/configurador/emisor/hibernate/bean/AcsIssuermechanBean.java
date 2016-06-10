package com.alignet.configurador.emisor.hibernate.bean;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T3DS_ISSUERMECHAN" , schema="SQMES")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class AcsIssuermechanBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**Declaracion de atributos*/
	private Integer   in_idIssuermechan;
	private Integer   in_idIssuer;
	private Integer   in_idMechanism;
	private Integer   in_idState;
	private Integer   in_idAuthenmechan;
	private Integer   in_idEnrollmechan;
	private Date      dt_dateTime;
	
	/**Descripcion*/
	private String   descripIn_idState;
	
	/**Relaciones ORM*/
	@OneToOne
	@JoinColumn(name="in_idMechanism")
	private AcsMechanismBean acsMechanismBean;
	
	/**Medoto constructor*/
	public AcsIssuermechanBean() {
		
	}

	public AcsIssuermechanBean(Integer in_idIssuermechan, Integer in_idIssuer, Integer in_idMechanism, Integer in_idState, Integer in_idAuthenmechan, Integer in_idEnrollmechan, Date dt_dateTime) {

		this.in_idIssuermechan = in_idIssuermechan;
		this.in_idIssuer = in_idIssuer;
		this.in_idMechanism = in_idMechanism;
		this.in_idState = in_idState;
		this.in_idAuthenmechan = in_idAuthenmechan;
		this.in_idEnrollmechan = in_idEnrollmechan;
		this.dt_dateTime = dt_dateTime;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDISSUERMECHAN" , nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIn_idIssuermechan() {
		return in_idIssuermechan;
	}
	public void setIn_idIssuermechan(Integer in_idIssuermechan) {
		this.in_idIssuermechan = in_idIssuermechan;
	}
	
	@Column(name="IN_IDISSUER")
	public Integer getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(Integer in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}
	
	public AcsMechanismBean getAcsMechanismBean() {
		return acsMechanismBean;
	}
	public void setAcsMechanismBean(AcsMechanismBean acsMechanismBean) {
		this.acsMechanismBean = acsMechanismBean;
	}
		
	@Column(name="IN_IDMECHANISM")
	public Integer getIn_idMechanism() {
		return in_idMechanism;
	}
	public void setIn_idMechanism(Integer in_idMechanism) {
		this.in_idMechanism = in_idMechanism;
	}
	
	@Column(name="IN_IDSTATE")
	public Integer getIn_idState() {
		return in_idState;
	}
	public void setIn_idState(Integer in_idState) {
		this.in_idState = in_idState;
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
	
	@Column(name="DT_DATETIME")
	public Date getDt_dateTime() {
		return dt_dateTime;
	}
	public void setDt_dateTime(Date dt_dateTime) {
		this.dt_dateTime = dt_dateTime;
	}
	
	
	/**Metodos de descripcion*/
	@Transient
	public String getDescripIn_idState() {
		descripIn_idState = "";		
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
		return descripIn_idState;

	}

	
	@Override
	public String toString() {
				
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDISSUERMECHAN= "+ in_idIssuermechan+", ");
		sb.append("IN_IDISSUER= "+in_idIssuer +", ");
		sb.append("IN_IDMECHANISM= "+in_idMechanism+", ");
		sb.append("IN_IDSTATE= "+ in_idState+", ");
		sb.append("IN_IDAUTHENMECHAN= "+ in_idAuthenmechan +", ");
		sb.append("IN_IDENROLLMECHAN= "+in_idEnrollmechan +", ");
		sb.append("DT_DATETIME= "+dt_dateTime);		
		
		return sb.toString();
	}
	
}
