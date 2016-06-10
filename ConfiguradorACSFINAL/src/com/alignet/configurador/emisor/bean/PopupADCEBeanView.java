package com.alignet.configurador.emisor.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T3DS_POPUPADCE" , schema="SQMACS")
@org.hibernate.annotations.Entity(
        dynamicInsert = true,
        dynamicUpdate = true
		)
public class PopupADCEBeanView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** Declaracion de atributos*/
	private Integer in_idPopupADCE;
	private Integer in_idIssuer;
	private Integer in_cashierPassword;
	private Integer in_cardPassword;
	private Integer in_bankingPassword;
	private Integer in_cvv2;
	private Integer in_identityCard;
	private Integer in_dateExpiry;
	private Integer in_dateBirth;
	private Integer in_phoneNumber;
	private Integer in_idCardRange;
	private Integer in_value1;
	private Integer in_value2;
	private Integer in_value3;
	private Integer in_value4;
	private String  vc_valueName1;
	private String  vc_valueName2;
	private String  vc_valueName3;
	private String  vc_valueName4;
	private Integer in_userRandon;
	private Integer in_postalCode;
	private Integer in_customerNumber;
	private Integer in_accountOpenDate;
	private Integer in_paymentLimitDate;
	private Integer in_minimunPayment;
	private Integer in_previousBalance;
	private Integer in_creditLimit;
	private Integer in_cutDate;
	private Integer in_fullName;
	
	/** Metodo Constructor*/
	public PopupADCEBeanView() {

	}

	public PopupADCEBeanView(Integer in_idPopupADCE, Integer in_idIssuer, Integer in_cashierPassword, Integer in_cardPassword, Integer in_bankingPassword, Integer in_cvv2,
							 Integer in_identityCard, Integer in_dateExpiry, Integer in_dateBirth, Integer in_phoneNumber, Integer in_idCardRange, Integer in_value1, Integer in_value2,
							 Integer in_value3, Integer in_value4, String vc_valueName1, String vc_valueName2, String vc_valueName3, String vc_valueName4, Integer in_userRandon, Integer in_postalCode,
							 Integer in_customerNumber, Integer in_accountOpenDate, Integer in_paymentLimitDate, Integer in_minimunPayment, Integer in_previousBalance, Integer in_creditLimit,
							 Integer in_cutDate, Integer in_fullName) {		
		this.in_idPopupADCE = in_idPopupADCE;
		this.in_idIssuer = in_idIssuer;
		this.in_cashierPassword = in_cashierPassword;
		this.in_cardPassword = in_cardPassword;
		this.in_bankingPassword = in_bankingPassword;
		this.in_cvv2 = in_cvv2;
		this.in_identityCard = in_identityCard;
		this.in_dateExpiry = in_dateExpiry;
		this.in_dateBirth = in_dateBirth;
		this.in_phoneNumber = in_phoneNumber;
		this.in_idCardRange = in_idCardRange;
		this.in_value1 = in_value1;
		this.in_value2 = in_value2;
		this.in_value3 = in_value3;
		this.in_value4 = in_value4;
		this.vc_valueName1 = vc_valueName1;
		this.vc_valueName2 = vc_valueName2;
		this.vc_valueName3 = vc_valueName3;
		this.vc_valueName4 = vc_valueName4;
		this.in_userRandon = in_userRandon;
		this.in_postalCode = in_postalCode;
		this.in_customerNumber = in_customerNumber;
		this.in_accountOpenDate = in_accountOpenDate;
		this.in_paymentLimitDate = in_paymentLimitDate;
		this.in_minimunPayment = in_minimunPayment;
		this.in_previousBalance = in_previousBalance;
		this.in_creditLimit = in_creditLimit;
		this.in_cutDate = in_cutDate;
		this.in_fullName = in_fullName;
	}

	/**Metodos Getter and Setter*/
	@Id
	@Column(name="IN_IDPOPUPADCE" , nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIn_idPopupADCE() {
		return in_idPopupADCE;
	}
	public void setIn_idPopupADCE(Integer in_idPopupADCE) {
		this.in_idPopupADCE = in_idPopupADCE;
	}
	
	@Column(name="IN_IDISSUER")
	public Integer getIn_idIssuer() {
		return in_idIssuer;
	}
	public void setIn_idIssuer(Integer in_idIssuer) {
		this.in_idIssuer = in_idIssuer;
	}

	@Column(name="IN_CASHIERPASSWORD")
	public Integer getIn_cashierPassword() {
		return in_cashierPassword;
	}
	public void setIn_cashierPassword(Integer in_cashierPassword) {
		this.in_cashierPassword = in_cashierPassword;
	}
	
	@Column(name="IN_CARDPASSWORD")
	public Integer getIn_cardPassword() {
		return in_cardPassword;
	}
	public void setIn_cardPassword(Integer in_cardPassword) {
		this.in_cardPassword = in_cardPassword;
	}
	
	@Column(name="IN_BANKINGPASSWORD")
	public Integer getIn_bankingPassword() {
		return in_bankingPassword;
	}
	public void setIn_bankingPassword(Integer in_bankingPassword) {
		this.in_bankingPassword = in_bankingPassword;
	}
	
	@Column(name="IN_CVV2")
	public Integer getIn_cvv2() {
		return in_cvv2;
	}
	public void setIn_cvv2(Integer in_cvv2) {
		this.in_cvv2 = in_cvv2;
	}
	
	@Column(name="IN_IDENTITYCARD")
	public Integer getIn_identityCard() {
		return in_identityCard;
	}
	public void setIn_identityCard(Integer in_identityCard) {
		this.in_identityCard = in_identityCard;
	}
	
	@Column(name="IN_DATEEXPIRY")
	public Integer getIn_dateExpiry() {
		return in_dateExpiry;
	}
	public void setIn_dateExpiry(Integer in_dateExpiry) {
		this.in_dateExpiry = in_dateExpiry;
	}
	
	@Column(name="IN_DATEBIRTH")
	public Integer getIn_dateBirth() {
		return in_dateBirth;
	}
	public void setIn_dateBirth(Integer in_dateBirth) {
		this.in_dateBirth = in_dateBirth;
	}
	
	@Column(name="IN_PHONENUMBER")
	public Integer getIn_phoneNumber() {
		return in_phoneNumber;
	}
	public void setIn_phoneNumber(Integer in_phoneNumber) {
		this.in_phoneNumber = in_phoneNumber;
	}
	
	@Column(name="IN_IDCARDRANGE")
	public Integer getIn_idCardRange() {
		return in_idCardRange;
	}
	public void setIn_idCardRange(Integer in_idCardRange) {
		this.in_idCardRange = in_idCardRange;
	}
	
	@Column(name="IN_VALUE1")
	public Integer getIn_value1() {
		return in_value1;
	}
	public void setIn_value1(Integer in_value1) {
		this.in_value1 = in_value1;
	}
	
	@Column(name="IN_VALUE2")
	public Integer getIn_value2() {
		return in_value2;
	}
	public void setIn_value2(Integer in_value2) {
		this.in_value2 = in_value2;
	}
	
	@Column(name="IN_VALUE3")
	public Integer getIn_value3() {
		return in_value3;
	}
	public void setIn_value3(Integer in_value3) {
		this.in_value3 = in_value3;
	}
	
	@Column(name="IN_VALUE4")
	public Integer getIn_value4() {
		return in_value4;
	}
	public void setIn_value4(Integer in_value4) {
		this.in_value4 = in_value4;
	}
	
	@Column(name="VC_VALUENAME1")
	public String getVc_valueName1() {
		return vc_valueName1;
	}
	public void setVc_valueName1(String vc_valueName1) {
		this.vc_valueName1 = vc_valueName1;
	}
	
	@Column(name="VC_VALUENAME2")
	public String getVc_valueName2() {
		return vc_valueName2;
	}
	public void setVc_valueName2(String vc_valueName2) {
		this.vc_valueName2 = vc_valueName2;
	}
	
	@Column(name="VC_VALUENAME3")
	public String getVc_valueName3() {
		return vc_valueName3;
	}
	public void setVc_valueName3(String vc_valueName3) {
		this.vc_valueName3 = vc_valueName3;
	}
	
	@Column(name="VC_VALUENAME4")
	public String getVc_valueName4() {
		return vc_valueName4;
	}
	public void setVc_valueName4(String vc_valueName4) {
		this.vc_valueName4 = vc_valueName4;
	}
	
	@Column(name="IN_USERANDOM")
	public Integer getIn_UserRandon() {
		return in_userRandon;
	}
	public void setVc_UserRandon(Integer in_userRandon) {
		this.in_userRandon = in_userRandon;
	}
	
	@Column(name="IN_POSTALCODE")
	public Integer getIn_postalCode() {
		return in_postalCode;
	}
	public void setIn_postalCode(Integer in_postalCode) {
		this.in_postalCode = in_postalCode;
	}
	
	@Column(name="IN_CUSTOMERNUMBER")
	public Integer getIn_customerNumber() {
		return in_customerNumber;
	}
	public void setIn_customerNumber(Integer in_customerNumber) {
		this.in_customerNumber = in_customerNumber;
	}
	
	@Column(name="IN_ACCOUNTOPENDATE")
	public Integer getIn_accountOpenDate() {
		return in_accountOpenDate;
	}
	public void setIn_accountOpenDate(Integer in_accountOpenDate) {
		this.in_accountOpenDate = in_accountOpenDate;
	}
	
	@Column(name="IN_PAYMENTLIMITDATE")
	public Integer getIn_paymentLimitDate() {
		return in_paymentLimitDate;
	}
	public void setIn_paymentLimitDate(Integer in_paymentLimitDate) {
		this.in_paymentLimitDate = in_paymentLimitDate;
	}
	
	@Column(name="IN_MINIMUMPAYMENT")
	public Integer getIn_minimunPayment() {
		return in_minimunPayment;
	}
	public void setIn_minimunPayment(Integer in_minimunPayment) {
		this.in_minimunPayment = in_minimunPayment;
	}
	
	@Column(name="IN_PREVIOUSBALANCE")
	public Integer getIn_previousBalance() {
		return in_previousBalance;
	}
	public void setIn_previousBalance(Integer in_previousBalance) {
		this.in_previousBalance = in_previousBalance;
	}
	
	@Column(name="IN_CREDITLIMIT")
	public Integer getIn_creditLimit() {
		return in_creditLimit;
	}
	public void setIn_creditLimit(Integer in_creditLimit) {
		this.in_creditLimit = in_creditLimit;
	}
	
	@Column(name="IN_CUTDATE")
	public Integer getIn_cutDate() {
		return in_cutDate;
	}
	public void setIn_cutDate(Integer in_cutDate) {
		this.in_cutDate = in_cutDate;
	}
	
	@Column(name="IN_FULLNAME")
	public Integer getIn_fullName() {
		return in_fullName;
	}
	public void setIn_fullName(Integer in_fullName) {
		this.in_fullName = in_fullName;
	}
	
	@Override
	public String toString() {
		
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("IN_IDPOPUPADCE= "+ in_idPopupADCE+", ");
		sb.append("IN_IDISSUER= "+in_idIssuer +", ");
		sb.append("IN_CASHIERPASSWORD= "+in_cashierPassword+", ");
		sb.append("IN_CARDPASSWORD= "+ in_cardPassword+", ");
		sb.append("IN_BANKINGPASSWORD= "+ in_bankingPassword +", ");
		sb.append("IN_CVV2= "+in_cvv2 +", ");
		sb.append("IN_IDENTITYCARD= "+in_identityCard +", ");
		sb.append("IN_DATEEXPIRY= "+in_dateExpiry +", ");
		sb.append("IN_DATEBIRTH= "+in_dateBirth +", ");
		sb.append("IN_PHONENUMBER= "+in_phoneNumber +", ");
		sb.append("IN_IDCARDRANGE= "+in_idCardRange +", ");
		sb.append("IN_VALUE1= "+in_value1 +", ");
		sb.append("IN_VALUE2= "+in_value2 +", ");
		sb.append("IN_VALUE3= "+in_value3);
		sb.append("IN_VALUE4= "+in_value4 +", ");	
		sb.append("VC_VALUENAME1= "+vc_valueName1 +", ");
		sb.append("VC_VALUENAME2= "+vc_valueName2 +", ");
		sb.append("VC_VALUENAME3= "+vc_valueName3 +", ");
		sb.append("VC_VALUENAME4= "+ vc_valueName4+", ");
		sb.append("IN_USERANDOM= "+ in_userRandon +", ");
		sb.append("IN_POSTALCODE= "+ in_postalCode +", ");
		sb.append("IN_CUSTOMERNUMBER= "+ in_customerNumber +", ");
		sb.append("IN_ACCOUNTOPENDATE= "+ in_accountOpenDate + ", ");
		sb.append("IN_PAYMENTLIMITDATE= "+ in_paymentLimitDate +", ");
		sb.append("IN_MINIMUMPAYMENT= "+ in_minimunPayment +", ");
		sb.append("IN_PREVIOUSBALANCE= "+in_previousBalance +", ");
		sb.append("IN_CREDITLIMIT= "+in_creditLimit +", ");
		sb.append("IN_CUTDATE= "+in_cutDate +", ");
		sb.append("IN_FULLNAME= "+in_fullName +", ");
				
		return super.toString();
	}
	
}

