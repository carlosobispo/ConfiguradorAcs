package com.alignet.configurador.emisor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class Utilitario {
	
	private static Logger LOG_APP = Logger.getLogger("APP");
	private static Logger LOG_SQL = Logger.getLogger("SQL");
	
	private static final String BUNDLE_NAME = "configuracionACS";
	private static final String BUNDLE_NAME2 = "messages_es";
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static final ResourceBundle RESOURCE_BUNDLE2 = ResourceBundle.getBundle(BUNDLE_NAME2);
	
	private static String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	public static boolean isvalidoEmail(String email) {
		// Compiles the given regular expression into a pattern.
		final Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		// Match the given input against this pattern
		final Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	public static boolean existeValorProperties(String valor,String CampoProperties){
		String campoProperties= getStringResourceBundle(CampoProperties);
		
		String[] valoresCampoProperties= campoProperties.split(",");
		
		if(valoresCampoProperties.length > 0){
			for (int i = 0; i < valoresCampoProperties.length; i++) {
				if(valoresCampoProperties[i].equals(valor))
					return true;
			}
		}
		return false;
	}
	
	
	public static boolean isValidoTamnioCampo(Object objeto, Integer tamanio){
		
		boolean isvalido=true;
		String valor = "";
		
		if (objeto instanceof Integer) {
			valor = objeto != null ? ((Integer) objeto).toString() : "";
		} else if (objeto instanceof String) {
			valor = objeto != null ? (String) objeto : "";
		}
		
		if(valor==null || valor.length()> tamanio){
			isvalido = false;
		}
		
		return isvalido;
	}
	

	public static boolean isVacioOrNull(Object objeto){
		boolean isvacio=true;
		String valor = "";

		if (objeto instanceof Integer) {
			valor = objeto != null ? ((Integer) objeto).toString() : "";
		} else if (objeto instanceof String) {
			valor = objeto != null ? (String) objeto : "";
		}
		if(valor!=null && !valor.trim().equals("") ){
			isvacio=false;
		}
		return isvacio;
	}
	
	
	
	public final static String getFechaSistema(){
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,new Locale("es","MX"));
		Date fechaActual=new Date();
		String fechaSesion = df.format(fechaActual);
		return fechaSesion;
	}

	
	public static long calcularHorasEntreFechas(Date fecha1, Date fecha2) {
		long diff = fecha2.getTime() - fecha1.getTime();
		long horas = diff / (1000 * 60 * 60);
		 Utilitario.getLOG_APP().info("Horas transcurridas : " + horas);
		return horas;
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
		if(file!= null){
			InputStream is = new FileInputStream(file);

		    byte[] bytes = new byte[(int)file.length()];


		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length  && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }
		  
		    is.close();
		    return bytes;
		}else{
			return null;
		}
	    
	   
	}
	
	public static String setearTildeCadena(String cadena, boolean mostrarTildeEnCajaTexto){
		try {
			 String seteoTildes[][]={  {"&aacute;","á"}, {"&eacute;","é"},{"&iacute;","í"},{"&oacute;","ó"}, {"&uacute;","ú"} ,
					 				   {"&Aacute;","Á"}, {"&Eacute;","É"},{"&Iacute;","Í"},{"&Oacute;","Ó"}, {"&Uacute;","Ú"} ,{"&ntilde;","ñ"},{"&Ntilde;","Ñ"}};
			
			for (int i = 0; i < seteoTildes.length; i++) {
				if(mostrarTildeEnCajaTexto)
					cadena = cadena.replaceAll(seteoTildes[i][0], seteoTildes[i][1]);
				else 
					cadena = cadena.replaceAll(seteoTildes[i][1], seteoTildes[i][0]);
			}
			return cadena;
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. Utilitario - getValueforKeyName", e);
		}
		return "";
	}
	
	
	public static boolean isValidoCheck(String cadena){
		
		boolean isvalido=false;
		try {
			if(cadena.equals("true") || cadena.equals("false"))
				return isvalido = true;
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. isValidoCheck ", e);
		}
		return isvalido;
	}
	

	public static String getStringResourceBundle(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}
	

	
	
	public static String getStringMessage(String key) {
		try {
			return RESOURCE_BUNDLE2.getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}
	
	
	
	public static Logger getLOG_APP() {
		return LOG_APP;
	}
	public static void setLOG_APP(Logger lOG_APP) {
		LOG_APP = lOG_APP;
	}
	
	
	public static Logger getLOG_SQL() {
		return LOG_SQL;
	}
	public static void setLOG_SQL(Logger lOG_SQL) {
		LOG_SQL = lOG_SQL;
	}


	
}
