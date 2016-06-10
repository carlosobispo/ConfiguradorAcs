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
@Table(name="T3DS_URL" , schema="SQMACS")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class UrlBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**Declaracion de Atributos*/
	private Integer   in_idurl;        
	private Integer   in_idissuer;
	private Integer   in_devicecategory;
	private String    vc_url;
	private Integer   in_type;
	private Integer   in_idauthenmechan;
	private Integer   in_idenrollmechan;
	private Integer   in_idstate;
	
	/**Decripccion */
	private String descripIn_devicecategory;
	private String descripIn_type;
	private String descripIn_idstate;
	
	
	/**Metodo constructor*/
	public UrlBean() {
		
	}
	
	public UrlBean(Integer in_idurl, Integer in_idissuer, Integer in_devicecategory, String vc_url, Integer in_type, Integer in_idauthenmechan, Integer in_idenrollmechan, Integer in_idstate) {

		this.in_idurl = in_idurl;
		this.in_idissuer = in_idissuer;
		this.in_devicecategory = in_devicecategory;
		this.vc_url = vc_url;
		this.in_type = in_type;
		this.in_idauthenmechan = in_idauthenmechan;
		this.in_idenrollmechan = in_idenrollmechan;
		this.in_idstate = in_idstate;
	}

	/**Metodos Getter and Setter */
	@Id
	@Column(name="IN_IDURL" , nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIn_idurl() {
		return in_idurl;
	}
	public void setIn_idurl(Integer in_idurl) {
		this.in_idurl = in_idurl;
	}

	@Column(name="IN_IDISSUER")
	public Integer getIn_idissuer() {
		return in_idissuer;
	}
	public void setIn_idissuer(Integer in_idissuer) {
		this.in_idissuer = in_idissuer;
	}
	
	@Column(name="IN_DEVICECATEGORY")
	public Integer getIn_devicecategory() {
		return in_devicecategory;
	}
	public void setIn_devicecategory(Integer in_devicecategory) {
		this.in_devicecategory = in_devicecategory;
	}
	
	@Column(name="VC_URL")
	public String getVc_url() {
		return vc_url;
	}
	public void setVc_url(String vc_url) {
		this.vc_url = vc_url;
	}

	@Column(name="IN_TYPE")
	public Integer getIn_type() {
		return in_type;
	}
	public void setIn_type(Integer in_type) {
		this.in_type = in_type;
	}

	@Column(name="IN_IDAUTHENMECHAN")
	public Integer getIn_idauthenmechan() {
		return in_idauthenmechan;
	}
	public void setIn_idauthenmechan(Integer in_idauthenmechan) {
		this.in_idauthenmechan = in_idauthenmechan;
	}

	@Column(name="IN_IDENROLLMECHAN")
	public Integer getIn_idenrollmechan() {
		return in_idenrollmechan;
	}
	public void setIn_idenrollmechan(Integer in_idenrollmechan) {
		this.in_idenrollmechan = in_idenrollmechan;
	}

	@Column(name="IN_IDSTATE")
	public Integer getIn_idstate() {
		return in_idstate;
	}
	public void setIn_idstate(Integer in_idstate) {
		this.in_idstate = in_idstate;
	}

	/**Metodos de  desccripcion */
	@Transient
	public String getDescripIn_devicecategory(){
		descripIn_devicecategory = "";		
		
		switch (in_devicecategory) {
		case 0:
			descripIn_devicecategory= "WEB";
			break;
		case 1:
			descripIn_devicecategory= "WAP";
			break;
		case 2:
			descripIn_devicecategory= "SMS";
			break;
		case 3:
			descripIn_devicecategory= "Extension for Voice and Messaging Channels";
			break;			
		default:
			descripIn_devicecategory= "--";
			break;
		}
		return descripIn_devicecategory;
	}
	@Transient
	public String getDescripIn_type() {
		descripIn_type = "";	
		
		switch (in_type) {
		case 0:
			descripIn_type= "PAReq ADC (URL Alignet)";
			break;
		case 1:
			descripIn_type= "Inicio de autenticación (PAReq) (URL Alignet)";
			break;
		case 2:
			descripIn_type= "Fin de la autenticación (PARes) (URL emisor o Alignet)";
			break;
		case 3:
			descripIn_type= "Validación de datos solo para ADC (URL emisor)";
			break;
		case 4:
			descripIn_type= "Validación de datos solo para SAT (URL emisor)";
			break;	
		case 5:
			descripIn_type= "URL a donde se envía la contraseña para su validación";
			break;
		case 6:
			descripIn_type= "URL de enrolamiento por MQ (BBVA) ";
			break;
		case 7:
			descripIn_type= "URL de alignet donde el emisor da respuesta después de la solicitud  de requerimiento de la PAM para el BCP. Solo es válido para el flujo PCB(contraseña creado en el emisor)";
			break;
		case 8:
			descripIn_type= "URL de alignet donde el emisor dara respuesta a la verificación de los datos del tarjetahabiente en el proceso de afiliación del BCP. Solo es válido para el flujo PCB";
			break;
		case 9:
			descripIn_type= "URL del emisor para el envio de los mensajes S1 (Autenticación mediante SMS - EGlobal)";
			break;	
		default:
			descripIn_type= "--";
			break;
		}
		
		return descripIn_type;
	}
	@Transient
	public String getDescripIn_idstate() {
		descripIn_idstate = "";		
		switch (in_idstate) {
		case 0:
			descripIn_idstate= "Desabilitado";
			break;
		case 1:
			descripIn_idstate= "Habilitado";
			break;
		default:
			descripIn_idstate= "--";
			break;
		}
		return descripIn_idstate;
	}
		
	@Override
	public String toString() {
				          		   		            			      
		StringBuffer sb = new StringBuffer();
		sb.append("IN_IDURL= "+ in_idurl+", ");
		sb.append("IN_IDISSUER= "+in_idissuer +", ");
		sb.append("IN_DEVICECATEGORY= "+in_devicecategory+", ");
		sb.append("VC_URL= "+ vc_url+", ");
		sb.append("IN_TYPE= "+in_type +", ");
		sb.append("IN_IDAUTHENMECHAN= "+in_idauthenmechan +", ");
		sb.append("IN_IDENROLLMECHAN= "+in_idenrollmechan +", ");
		sb.append("IN_IDSTATE= "+in_idstate +", ");
		
		return sb.toString();
	}
       
	
}
