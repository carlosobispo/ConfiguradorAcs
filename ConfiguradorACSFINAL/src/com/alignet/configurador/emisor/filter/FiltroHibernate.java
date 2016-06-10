package com.alignet.configurador.emisor.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.hibernate.servlet.HibernateSessionFactory;
import com.alignet.configurador.emisor.util.Utilitario;

public class FiltroHibernate implements Filter {


    public FiltroHibernate() {
    }
    
	public void destroy() {	
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = ( HttpServletRequest ) request;
		HttpServletResponse res = ( HttpServletResponse ) response;		
		
		HttpSession  session = req.getSession(false);
		
		if( !req.getRequestURI().contains("resources")){
			
			if(req.getRequestURI().equals(Utilitario.getStringResourceBundle("reqGetServletPath1")) || 
					req.getRequestURI().equals(Utilitario.getStringResourceBundle("reqGetServletPath2")) || 
					req.getRequestURI().equals(Utilitario.getStringResourceBundle("reqGetServletPath3")) ||  
					req.getRequestURI().equals(Utilitario.getStringResourceBundle("reqGetServletPath4"))
					
					){
				//MDC.put("idsession", getIdMetodo());
				chain.doFilter(request, response);
				return;
				
			}else{
				
				if(session != null){

					if(session.getAttribute("ObjetoUsuario") == null ){
						res.sendRedirect(Utilitario.getStringResourceBundle("reqGetServletPath1"));
					}
					else{
						
						
						try {
							
							UserBean usuario =(UserBean)session.getAttribute("ObjetoUsuario");
							String identificador = usuario.getIntIdIssuer() != null ? usuario.getIntIdIssuer().toString() :  usuario.getSvcLogin() ;
							
							MDC.put("idsession", identificador);
							chain.doFilter(request, response);
							
						} catch (Exception e) {
							Utilitario.getLOG_APP().error("ERROR : ", e);
						}finally{
							MDC.remove("idsession");
						}
						
					}
						
					
				}else{
					res.sendRedirect(Utilitario.getStringResourceBundle("reqGetServletPath1"));
				}
			}
			
			
			
			//HIBERNATE
			try {
				UserTransaction transaction = HibernateSessionFactory.getUserTransaction();
				int status = transaction.getStatus();
				//Utilitario.getLOG_APP().info(" Status.STATUS_ACTIVE "+Status.STATUS_ACTIVE);
				//Utilitario.getLOG_APP().info(" status  "+status);
				
				if(status == Status.STATUS_ACTIVE){
					//Utilitario.getLOG_APP().info("ANTES DE EJECUTAR COMMIT ");
					transaction.commit();
				//	Utilitario.getLOG_APP().info("DESPUES DE EJECUTAR COMMIT ");
					
				}
			} catch (Exception e) {
				Utilitario.getLOG_APP().error("ERROR EJECUTAR COMMIT  ", e);
				//throw new ServletException("Error al obtener transacción (Hibernate)", e);
			}
			
			
			
		}
		else{
			chain.doFilter(request, response);
		}

	}
	
	
	
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public static String getIdMetodo() {
		return generateAleatorio(4);  
	}     	

	public static String generateAleatorio(final int length) {
		final Random r = new Random();
		final String nrand = String.valueOf((Math.abs(r.nextInt())));
		return StringUtils.substring(nrand, 0, length);
	}
	
}
