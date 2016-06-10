package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

public class UrlBeanView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Declaacion de atributos */
	private String   in_idurl;        
	private String   in_idissuer;
	private String   in_devicecategory;
	private String   vc_url;
	private String   in_type;
	private String   in_idauthenmechan;
	private String   in_idenrollmechan;
	private String   in_idstate;

	/** Metodo Constructor */
	public UrlBeanView() {
		
	}

	public UrlBeanView(String in_idurl, String in_idissuer, String in_devicecategory, String vc_url, String in_type, String in_idauthenmechan, String in_idenrollmechan, String in_idstate) {
		
		this.in_idurl = in_idurl;
		this.in_idissuer = in_idissuer;
		this.in_devicecategory = in_devicecategory;
		this.vc_url = vc_url;
		this.in_type = in_type;
		this.in_idauthenmechan = in_idauthenmechan;
		this.in_idenrollmechan = in_idenrollmechan;
		this.in_idstate = in_idstate;
	
	}

	/**Getter and Setter */
	public String getIn_idurl() {
		return in_idurl;
	}
	public void setIn_idurl(String in_idurl) {
		this.in_idurl = in_idurl;
	}

	public String getIn_idissuer() {
		return in_idissuer;
	}
	public void setIn_idissuer(String in_idissuer) {
		this.in_idissuer = in_idissuer;
	}

	public String getIn_devicecategory() {
		return in_devicecategory;
	}
	public void setIn_devicecategory(String in_devicecategory) {
		this.in_devicecategory = in_devicecategory;
	}

	public String getVc_url() {
		return vc_url;
	}
	public void setVc_url(String vc_url) {
		this.vc_url = vc_url;
	}

	public String getIn_type() {
		return in_type;
	}
	public void setIn_type(String in_type) {
		this.in_type = in_type;
	}

	public String getIn_idauthenmechan() {
		return in_idauthenmechan;
	}
	public void setIn_idauthenmechan(String in_idauthenmechan) {
		this.in_idauthenmechan = in_idauthenmechan;
	}

	public String getIn_idenrollmechan() {
		return in_idenrollmechan;
	}
	public void setIn_idenrollmechan(String in_idenrollmechan) {
		this.in_idenrollmechan = in_idenrollmechan;
	}

	public String getIn_idstate() {
		return in_idstate;
	}
	public void setIn_idstate(String in_idstate) {
		this.in_idstate = in_idstate;
	}

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("in_idurl= "+ in_idurl+", ");
		sb.append("in_idissuer= "+in_idissuer +", ");
		sb.append("in_devicecategory= "+in_devicecategory+", ");
		sb.append("vc_url= "+ vc_url+", ");
		sb.append("in_type= "+in_type +", ");
		sb.append("in_idauthenmechan= "+in_idauthenmechan +", ");
		sb.append("in_idenrollmechan= "+in_idenrollmechan +", ");
		sb.append("in_idstate= "+in_idstate +", ");
		
		return sb.toString();
	}


}
