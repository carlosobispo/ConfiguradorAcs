package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class CardRangeBeanView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** Declaracion de atributos */
	private String in_idCardrange;
	private String in_idIssyer;
	private String vc_firstrange;
	private String vc_lastrange;
	private String vc_nameA;
	private String vc_nameB;
	private String vc_idState;
	private String ch_useADC;
	private String in_idPopupADCE;
	private String vc_bin;
	private String in_dataConfig;
	private String dt_registry;
	private String ch_matrixDecision;
	private String in_idLanguaje;

	/** Metodo Constructor */
	public CardRangeBeanView() {

	}
	
	public CardRangeBeanView(String in_idIssyer, String vc_firstrange,
			String vc_lastrange, String vc_nameA, String vc_nameB,
			String vc_idState, String ch_useADC, String in_idPopupADCE,
			String vc_bin, String in_dataConfig, String dt_registry,
			String ch_matrixDecision, String in_idLanguaje) {
		super();
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

	public String getIn_idCardrange() {
		return in_idCardrange;
	}

	public void setIn_idCardrange(String in_idCardrange) {
		this.in_idCardrange = in_idCardrange;
	}

	public String getIn_idIssyer() {
		return in_idIssyer;
	}

	public void setIn_idIssyer(String in_idIssyer) {
		this.in_idIssyer = in_idIssyer;
	}

	public String getVc_firstrange() {
		return vc_firstrange;
	}

	public void setVc_firstrange(String vc_firstrange) {
		this.vc_firstrange = vc_firstrange;
	}

	public String getVc_lastrange() {
		return vc_lastrange;
	}

	public void setVc_lastrange(String vc_lastrange) {
		this.vc_lastrange = vc_lastrange;
	}

	public String getVc_nameA() {
		return vc_nameA;
	}

	public void setVc_nameA(String vc_nameA) {
		this.vc_nameA = vc_nameA;
	}

	public String getVc_nameB() {
		return vc_nameB;
	}

	public void setVc_nameB(String vc_nameB) {
		this.vc_nameB = vc_nameB;
	}

	public String getVc_idState() {
		return vc_idState;
	}

	public void setVc_idState(String vc_idState) {
		this.vc_idState = vc_idState;
	}

	public String getCh_useADC() {
		return ch_useADC;
	}

	public void setCh_useADC(String ch_useADC) {
		this.ch_useADC = ch_useADC;
	}

	public String getIn_idPopupADCE() {
		return in_idPopupADCE;
	}

	public void setIn_idPopupADCE(String in_idPopupADCE) {
		this.in_idPopupADCE = in_idPopupADCE;
	}

	public String getVc_bin() {
		return vc_bin;
	}

	public void setVc_bin(String vc_bin) {
		this.vc_bin = vc_bin;
	}

	public String getIn_dataConfig() {
		return in_dataConfig;
	}

	public void setIn_dataConfig(String in_dataConfig) {
		this.in_dataConfig = in_dataConfig;
	}

	public String getDt_registry() {
		return dt_registry;
	}

	public void setDt_registry(String dt_registry) {
		this.dt_registry = dt_registry;
	}

	public String getCh_matrixDecision() {
		return ch_matrixDecision;
	}

	public void setCh_matrixDecision(String ch_matrixDecision) {
		this.ch_matrixDecision = ch_matrixDecision;
	}

	public String getIn_idLanguaje() {
		return in_idLanguaje;
	}

	public void setIn_idLanguaje(String in_idLanguaje) {
		this.in_idLanguaje = in_idLanguaje;
	}
	

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("in_idCardrange= "+ in_idCardrange+", ");
		sb.append("in_idIssyer= "+in_idIssyer +", ");
		sb.append("vc_firstrange= "+vc_firstrange+", ");
		sb.append("vc_nameA= "+ vc_nameA+", ");
		sb.append("vc_nameB= "+vc_nameB +", ");
		sb.append("vc_idState= "+vc_idState+", ");		
		sb.append("ch_useADC= "+ ch_useADC+", ");
		sb.append("in_idPopupADCE= "+in_idPopupADCE +", ");
		sb.append("vc_bin= "+vc_bin+", ");
		sb.append("in_dataConfig= "+ in_dataConfig+", ");
		sb.append("dt_registry= "+dt_registry +", ");
		sb.append("ch_matrixDecision= "+ch_matrixDecision+", ");
		sb.append("in_idLanguaje= "+in_idLanguaje);
		return sb.toString();
		
	}
}
