package com.alignet.configurador.emisor.hibernate.bean;
        
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T3DS_CARDRANGE" , schema="SQMACS")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class CardRangeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Declaracion de atributos */
	private Integer in_idCardrange;
	private Integer in_idIssyer;
	private String  vc_firstrange;
	private String  vc_lastrange;
	private String  vc_nameA;
	private String  vc_nameB;
	private Integer vc_idState;
	private String  ch_useADC;
	private Integer in_idPopupADCE;
	private String  vc_bin;
	private Integer in_dataConfig;
	private Date    dt_registry;
	private String  ch_matrixDecision;
	private Integer in_idLanguaje;
	
	/**Decripccion */
	private String descripVc_idState;
	private String descripVh_useADC;
	
	/** Declaracion de constructores*/
	public CardRangeBean() {

	}

	public CardRangeBean(Integer in_idCardrange, Integer in_idIssyer,
			String vc_firstrange, String vc_lastrange, String vc_nameA,
			String vc_nameB, Integer vc_idState, String ch_useADC,
			Integer in_idPopupADCE, String vc_bin, Integer in_dataConfig,
			Date dt_registry, String ch_matrixDecision, Integer in_idLanguaje) {
		super();
		this.in_idCardrange = in_idCardrange;
		this.in_idIssyer = in_idIssyer;
		this.vc_firstrange = vc_firstrange;
		this.vc_lastrange = vc_lastrange;
		this.vc_nameA = vc_nameA;
		this.vc_nameB = vc_nameB;
		this.vc_idState = vc_idState;
		this.ch_useADC = ch_useADC;
		this.in_idPopupADCE = in_idPopupADCE;
		this.vc_bin = vc_bin;
		this.in_dataConfig = in_dataConfig;
		this.dt_registry = dt_registry;
		this.ch_matrixDecision = ch_matrixDecision;
		this.in_idLanguaje = in_idLanguaje;
	}
	
	/**Para listar*/
	public CardRangeBean(String vc_firstrange, String vc_lastrange,	Integer vc_idState, String ch_useADC) {		
		this.vc_firstrange = vc_firstrange;
		this.vc_lastrange = vc_lastrange;
		this.vc_idState = vc_idState;
		this.ch_useADC = ch_useADC;
	}
	
	public CardRangeBean(Integer in_idCardrange, Integer in_idIssyer, String vc_firstrange, String vc_lastrange,	Integer vc_idState, String ch_useADC) {				
		this.in_idCardrange = in_idCardrange;
		this.in_idIssyer = in_idIssyer;
		this.vc_firstrange = vc_firstrange;
		this.vc_lastrange = vc_lastrange;
		this.vc_idState = vc_idState;
		this.ch_useADC = ch_useADC;
	}
	
	
	/** Metodos Getter and Setter*/

	@Id
	@Column(name="IN_IDCARDRANGE" , nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIn_idCardrange() {
		return in_idCardrange;
	}
	public void setIn_idCardrange(Integer in_idCardrange) {
		this.in_idCardrange = in_idCardrange;
	}
	
	@Column(name="IN_IDISSUER")
	public Integer getIn_idIssyer() {
		return in_idIssyer;
	}
	public void setIn_idIssyer(Integer in_idIssyer) {
		this.in_idIssyer = in_idIssyer;
	}
	
	@Column(name="VC_FIRSTRANGE")
	public String getVc_firstrange() {
		return vc_firstrange;
	}
	public void setVc_firstrange(String vc_firstrange) {
		this.vc_firstrange = vc_firstrange;
	}
	
	@Column(name="VC_LASTRANGE")
	public String getVc_lastrange() {
		return vc_lastrange;
	}
	public void setVc_lastrange(String vc_lastrange) {
		this.vc_lastrange = vc_lastrange;
	}

	@Column(name="VC_NAMEA")
	public String getVc_nameA() {
		return vc_nameA;
	}
	public void setVc_nameA(String vc_nameA) {
		this.vc_nameA = vc_nameA;
	}
	
	@Column(name="VC_NAMEB")
	public String getVc_nameB() {
		return vc_nameB;
	}
	public void setVc_nameB(String vc_nameB) {
		this.vc_nameB = vc_nameB;
	}
	
	@Column(name="IN_IDSTATE")
	public Integer getVc_idState() {
		return vc_idState;
	}
	public void setVc_idState(Integer vc_idState) {
		this.vc_idState = vc_idState;
	}
	
	@Column(name="CH_USEADC")
	public String getCh_useADC() {
		return ch_useADC;
	}
	public void setCh_useADC(String ch_useADC) {
		this.ch_useADC = ch_useADC;
	}
	
	@Column(name="IN_IDPOPUPADCE")
	public Integer getIn_idPopupADCE() {
		return in_idPopupADCE;
	}
	public void setIn_idPopupADCE(Integer in_idPopupADCE) {
		this.in_idPopupADCE = in_idPopupADCE;
	}
	
	@Column(name="VC_BIN")
	public String getVc_bin() {
		return vc_bin;
	}
	public void setVc_bin(String vc_bin) {
		this.vc_bin = vc_bin;
	}
	
	@Column(name="IN_DATACONFIG")
	public Integer getIn_dataConfig() {
		return in_dataConfig;
	}
	public void setIn_dataConfig(Integer in_dataConfig) {
		this.in_dataConfig = in_dataConfig;
	}

	@Column(name="DT_REGISTRY")
	public Date getDt_registry() {
		return dt_registry;
	}
	public void setDt_registry(Date dt_registry) {
		this.dt_registry = dt_registry;
	}

	@Column(name="CH_MATRIXDECISION")
	public String getCh_matrixDecision() {
		return ch_matrixDecision;
	}
	public void setCh_matrixDecision(String ch_matrixDecision) {
		this.ch_matrixDecision = ch_matrixDecision;
	}
	
	@Column(name="IN_IDLANGUAGE")
	public Integer getIn_idLanguaje() {
		return in_idLanguaje;
	}
	public void setIn_idLanguaje(Integer in_idLanguaje) {
		this.in_idLanguaje = in_idLanguaje;
	}
	
	/**Metodos de  desccripcion */
	@Transient
	public String getDescripVc_idState() {
		
		switch (vc_idState) {
		case 0:
			descripVc_idState= "Desabilitado";
			break;
		case 1:
			descripVc_idState= "Habilitado";
			break;
		default:
			descripVc_idState= "--";
			break;
		}
		return descripVc_idState;
	}
	public void setDescripVc_idState(String descripVc_idState) {
		this.descripVc_idState = descripVc_idState;
	}

	@Transient
	public String getDescripVh_useADC() {
		int tem_ch_useADC = 9;
		
		if ( (ch_useADC != null) && (!ch_useADC.equals("")) && (!ch_useADC.equals(" ")) ) {
			tem_ch_useADC= Integer.parseInt(ch_useADC);
		}
			
		switch (tem_ch_useADC) {
		case 0:
			descripVh_useADC= "NO";
			break;
			
		case 1:
			descripVh_useADC= "SI";
			break;
			
		default:
			descripVh_useADC= "--";
			break;
		}		
		return descripVh_useADC;
	}
	public void setDescripVh_useADC(String descripVh_useADC) {
		this.descripVh_useADC = descripVh_useADC;
	}
		
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDCARDRANGE= "+ in_idCardrange+", ");
		sb.append("IN_IDISSUER= "+in_idIssyer +", ");
		sb.append("VC_FIRSTRANGE= "+vc_firstrange+", ");
		sb.append("VC_LASTRANGE= "+ vc_lastrange+", ");
		sb.append("VC_NAMEA= "+vc_nameA +", ");
		sb.append("VC_NAMEB= "+vc_nameB +", ");
		sb.append("IN_IDSTATE= "+vc_idState +", ");
		sb.append("CH_USEADC= "+ch_useADC +", ");
		sb.append("IN_IDPOPUPADCE= "+in_idPopupADCE +", ");
		sb.append("VC_BIN= "+vc_bin +", ");
		sb.append("IN_DATACONFIG= "+in_dataConfig +", ");
		sb.append("DT_REGISTRY= "+dt_registry +", ");
		sb.append("CH_MATRIXDECISION= "+ch_matrixDecision +", ");
		sb.append("IN_IDLANGUAGE= "+in_idLanguaje + ", ");
		sb.append("descripVc_idState= "+getDescripVc_idState() + ", ");		
		sb.append("descripVh_useADC= "+getDescripVh_useADC());
		
		return sb.toString();
	}
}

