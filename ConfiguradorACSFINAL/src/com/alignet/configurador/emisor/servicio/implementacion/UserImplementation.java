package com.alignet.configurador.emisor.servicio.implementacion;

import java.io.Serializable;

import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.hibernate.dao.UserDAO;
import com.alignet.configurador.emisor.servicio.interfaz.UserInterface;
import com.alignet.configurador.emisor.util.Utilitario;

public class UserImplementation implements UserInterface,Serializable{

	private static final long serialVersionUID = 1L;
	UserDAO userDao= new UserDAO();
	
	//@Override
	public UserBean selectUserBeanbyUsuario(String usuario) throws Exception {
		UserBean user= null;
		try {
			user=userDao.selectUserBeanbyUsuario(usuario);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. UserImplementation - selectUserBeanbyUsuario  error al obtener usuario", e);
		}
		return user;
	}

	//@Override
	public boolean updateIntentUser(UserBean usuario) throws Exception {
		boolean operacionExitosa =false;
		try {
			operacionExitosa=userDao.UpdateIntentUser(usuario);
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("ERROR. UserImplementation - updateIntentUser error al obtener usuario", e);
		}
		
		return operacionExitosa;
	}
	
	
	
	
}
