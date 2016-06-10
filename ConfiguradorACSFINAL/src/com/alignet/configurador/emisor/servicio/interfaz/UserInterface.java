package com.alignet.configurador.emisor.servicio.interfaz;

import com.alignet.configurador.emisor.hibernate.bean.UserBean;

public interface UserInterface {
	
	UserBean selectUserBeanbyUsuario(String usuario) throws Exception;
	boolean updateIntentUser(UserBean usuario) throws Exception;
}
