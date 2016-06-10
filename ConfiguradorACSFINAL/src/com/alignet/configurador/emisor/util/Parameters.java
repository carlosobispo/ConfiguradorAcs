package com.alignet.configurador.emisor.util;




public final class Parameters {
	
	//public static final ResourceBundle RESOURCE_BUNDLE =  ResourceBundle.getBundle("configuracionACS");
	
	public  final static int SIN_MARCA 					=  0;
	public  final static int ID_MARCA_AMEX 				=  3;
	
	
	//NUMEROS MAGICOS
	public  final static int CIEN						=  100;
	public  final static int MIL						=  1000;
	public  final static int DIEZ						=  10;

	public static final Integer PERFIL_ADMINISTRADORALIGNET  = 1;
	public static final Integer PERFIL_ADMINISTRADOEMISOR 	 = 20;
	
	
	public static final Integer HORA_DESBLOQUEDO		=	2;
	public static final Integer MAX_INTENTOS			=   3;
	
	// Type de Usario 4 para el modulo configurador ALignet
	public static final Integer REGISTER_USER_TYPE		= 4;	 
	
	public static final Integer REGISTER_NONACTIVE 		= 0;
	public static final Integer REGISTER_ACTIVE 		= 1;
	public static final Integer REGISTER_BLOQUEADO 		= 2;
	public static final Integer REGISTER_ELIMINADO      = 3;
	
	public static final Integer IN_IDREFERENCE          = 0 ;
	public static final Integer IN_TYPELOGOEMISOR		= 2;	
	
}
