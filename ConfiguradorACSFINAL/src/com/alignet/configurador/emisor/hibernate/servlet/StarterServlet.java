package com.alignet.configurador.emisor.hibernate.servlet;

import java.io.Serializable;

import javax.servlet.http.HttpServlet;

public class StarterServlet extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;

	
	public void destroy() {
		HibernateSessionFactory.closeSessionFactory();
	}
	
	
	public void init() {
		HibernateSessionFactory.getConfiguration();
	}
}
