package com.alignet.configurador.emisor.util;

import java.text.MessageFormat;

import com.alignet.configurador.emisor.bean.NotificacionEnrolamientoBean;

public class HtmlFormatter {

	
	public static void setearConfiguracionEmail(String htmlEmail, String BrandName , Object objeto){
		
		try {
			if (objeto instanceof NotificacionEnrolamientoBean) {
				Utilitario.getLOG_APP().info("Objeto Tipo : NotificacionEnrolamientoBean");
				setearConfiguracionEmail_NotificacionEnrolamiento( htmlEmail , BrandName , (NotificacionEnrolamientoBean)objeto);
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR setearConfiguracionEmail ",e);
		}
	}
	
	public static void setearConfiguracionEmail_NotificacionEnrolamiento(String htmlEmail, String BrandName,NotificacionEnrolamientoBean notificacionBean){
		try {
			
			
			Integer indicadorHtmlTable = htmlEmail.indexOf("<table id='notificacion_enrolamiento'>");
			Integer indicadorHtmlBody = htmlEmail.indexOf("</body>");
			
			String sFormatItem0 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM0."+BrandName); 
			String sFormatItem1 = Utilitario.getStringResourceBundle("NOTIFICACION.ITEM1."+BrandName);
			
			
			if(indicadorHtmlTable<0 || indicadorHtmlBody<0) {
				Utilitario.getLOG_APP().warn("No se pudo obtener configuracion, Se mostrara la configuracion por defecto");
				return;
			}

			Utilitario.getLOG_APP().info("Se obtiene la siguiente Configuracion: ");
			notificacionBean.setSflagTieneConfiguracionEmail("true");
			
			String htmlEmailView =  htmlEmail.substring(indicadorHtmlTable, indicadorHtmlBody);
			
			
			String defaulthtmlEmailView = MessageFormat.format(htmlEmailView, sFormatItem0,sFormatItem1);
			
			notificacionBean.setStextConfiguracionEmail(defaulthtmlEmailView);
			
			if(htmlEmail.contains("saludo_inicial"))
			{
				
				String sSaluoinicial = obtenerCadenaOfTable(htmlEmailView, "<div id='saludo_inicial'>", "</div>");
				
				notificacionBean.setSflagSaludoInicial("true");
				
				Utilitario.getLOG_APP().info("*Etiqueta saludo_inicial: "+sSaluoinicial);
				
				notificacionBean.setStextSaludoInicial( Utilitario.setearTildeCadena(sSaluoinicial, true));	
			}
			
			if(htmlEmail.contains("parrafo1"))
			{
				String sParrafo1 = obtenerCadenaOfTable(htmlEmailView, "<div id='parrafo1'>", "</div>");
				Utilitario.getLOG_APP().info("*Etiqueta parrafo1: "+sParrafo1);
				
				String defaultTextsParrafo1 =  MessageFormat.format(sParrafo1, "TARJETA_CREDITO","ULTIMOS_4_DIGITOS_TARJETA");
				
				notificacionBean.setStextParrafo1( Utilitario.setearTildeCadena(defaultTextsParrafo1, true) );
				
			}
			
			if(htmlEmail.contains("parrafo2"))
			{
				String sParrafo2 = obtenerCadenaOfTable(htmlEmailView, "<div id='parrafo2'>", "</div>") ;
				
				Utilitario.getLOG_APP().info("*Etiqueta parrafo2: "+sParrafo2);
				String defaultTextsParrafo2 =  MessageFormat.format(sParrafo2, "TARJETA_CREDITO","ULTIMOS_4_DIGITOS_TARJETA");
				notificacionBean.setSflagParrafo2("true");
				notificacionBean.setStextParrafo2( Utilitario.setearTildeCadena(defaultTextsParrafo2, true) );
				
			}
			
			if(htmlEmail.contains("parrafo3"))
			{
				String sParrafo3 = obtenerCadenaOfTable(htmlEmailView, "<div id='parrafo3'>", "</div>");
				
				Utilitario.getLOG_APP().info("*Etiqueta parrafo3: "+sParrafo3);
				String defaultTextsParrafo3 =  MessageFormat.format(sParrafo3, "TARJETA_CREDITO","ULTIMOS_4_DIGITOS_TARJETA");
				
				notificacionBean.setSflagParrafo3("true");
				notificacionBean.setStextParrafo3( Utilitario.setearTildeCadena(defaultTextsParrafo3, true) );
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Utilitario.getLOG_APP().error("ERROR setearConfiguracionEmail_NotificacionEnrolamiento ",e);
		}
	}
	
	
	public static String obtenerCadenaOfTable(String htmlEmail ,String sCadenaLimiteInicial, String sCadenaLimiteFinal){
		
		String cadenaResult="";
		try {
		
			int longcadena= sCadenaLimiteInicial.length();
			
			int posIcadena = htmlEmail.indexOf(sCadenaLimiteInicial) + longcadena; 
			int posFcadena= htmlEmail.indexOf(sCadenaLimiteFinal, posIcadena);
			
			cadenaResult = htmlEmail.substring(posIcadena, posFcadena);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR obtenerCadenaOfTable ",e);
		}
		
		return cadenaResult;
	}
	
	
	
}
