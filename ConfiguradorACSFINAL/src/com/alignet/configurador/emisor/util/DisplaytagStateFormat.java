package com.alignet.configurador.emisor.util;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DisplaytagStateFormat implements DisplaytagColumnDecorator{

	// @Override
	public Object decorate(Object columnValue, PageContext arg1, MediaTypeEnum arg2) throws DecoratorException {
		return columnValue != null ? showState(columnValue) : "-";
		
	}
	
	
	
	private static String showState(Object objeto) {
		String nombre = "";

		String valor = "0";

		if (objeto instanceof Integer) {
			valor = objeto != null ? ((Integer) objeto).toString() : "0";
		} else if (objeto instanceof String) {
			valor = objeto != null ? (String) objeto : "0";
		}

		
		if ( valor.equals(Parameters.REGISTER_ACTIVE.toString() ))
			nombre="Habilitado";
		else if (valor.equals(Parameters.REGISTER_NONACTIVE.toString() ))
			nombre="No Habilitado";

		return nombre;
	}

}
