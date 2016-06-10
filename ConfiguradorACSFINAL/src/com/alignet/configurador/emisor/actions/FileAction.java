package com.alignet.configurador.emisor.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.alignet.configurador.emisor.bean.FileBean;
import com.alignet.configurador.emisor.hibernate.bean.BrandBean;
import com.alignet.configurador.emisor.hibernate.bean.UserBean;
import com.alignet.configurador.emisor.servicio.implementacion.FileImplementation;
import com.alignet.configurador.emisor.util.Parameters;
import com.alignet.configurador.emisor.util.Utilitario;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="ConfiguradorACS")
public class FileAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<BrandBean>  listBrandHabilitadasEmisor = new ArrayList<BrandBean>();

	public InputStream  fileInputStream ;
	private BrandBean  brandBean;
	private FileBean fileBean;

	private InputStream imagenLogoIssuer;
	private Integer brandSelected;
	private String operacionValidate;
	private SessionMap<String, Object> sessionMapLogo;
	
	
	
	public void validate() {
		
		try {
			Utilitario.getLOG_APP().info("<Inicio> Validate");
			
			if(operacionValidate.equals("validateCargaLogo")){
				
				Utilitario.getLOG_APP().info("Metodo: validateCargaLogo ");
				
				 if(validateCambiarMarca( brandBean.getIntIdBrand()))  
					 {
					 	validateCargaLogo();
					 	brandSelected = brandBean.getIntIdBrand();
					 }
			}
			
			Utilitario.getLOG_APP().info("<Fin> Validate");
			
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error en el validate ",e);
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
		}
	}
	
	
	public boolean validateCambiarMarca(Integer idBrand){
		boolean isValido= false;
		try {
			String regBrand = "[1-2]";
			
			if( Utilitario.isVacioOrNull(idBrand)){
				
				Utilitario.getLOG_APP().warn("Marca Null");
				addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}
			else if ( !idBrand.toString().matches(regBrand) ) {
					Utilitario.getLOG_APP().warn("Marca Incorrecta");
					addFieldError("mensajeERROR", " Seleccione una marca VALIDA! ");
			}else{
				String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+idBrand).trim();
				Utilitario.getLOG_APP().info("El usuario selecciono la marca: " + nombreMarca);
				isValido = true;
			}
			
		} catch (Exception e) {
			addFieldError("mensajeERROR", "Favor de verificar los valores ingresados");
			Utilitario.getLOG_APP().error("ERROR : PantallaTarjetaBloqueadaAction - validateCambiarMarca ",e);
		}
		return isValido;
	}
	
	
	
	public void validateCargaLogo(){
		try {
			
			if(fileBean==null){
				Utilitario.getLOG_APP().warn("fileBean es null");
				addFieldError("mensajeERROR", "Seleccione una imagen");
			}
			else if(fileBean.getFileLogoIssuer()==null){
				Utilitario.getLOG_APP().warn("fileBean.getFileLogoIssuer es null");
				addFieldError("mensajeERROR", "Seleccione una imagen");
			}
			else if ( !fileBean.getFileLogoIssuerContentType().contains("gif")){
				Utilitario.getLOG_APP().warn("El tipo de imagen es invalido "+ fileBean.getFileLogoIssuerContentType());
				addFieldError("mensajeERROR", "Seleccione una imagen tipo gif");
			}else{
				
				byte[] imagenCargado = Utilitario.getBytesFromFile(fileBean.getFileLogoIssuer() );
				ImageIcon image= new ImageIcon( imagenCargado ) ; 
				
				Integer anchoLogoPermitido= Integer.parseInt(Utilitario.getStringResourceBundle("LOGO.ANCHO"));
				Integer altoLogoPermitido= Integer.parseInt(Utilitario.getStringResourceBundle("LOGO.ALTO"));
				
				
				if( image.getIconHeight()!= altoLogoPermitido.intValue() || image.getIconWidth()!= anchoLogoPermitido.intValue() ){
					Utilitario.getLOG_APP().warn("El tamanio del logo es invalido");
					addFieldError("mensajeERROR", "El tamanioo del logo debe ser "+ anchoLogoPermitido +"x"+altoLogoPermitido);
				}else
					Utilitario.getLOG_APP().info("El logo seleccionado es correcto, se procede a registrar");
			}
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error en el validateCargaLogo  ",e);
			addFieldError("mensajeERROR", "Ocurrio un error, Intente mas tarde");
		}
	}
	
	
	@SkipValidation
	@Action(value = "/irConfigLogo", results = { 
			@Result (location = "t_configLogo", name = "success", type="tiles")
	})	
	public String irConfigLogo(){
		
		try {
			Utilitario.getLOG_APP().info("========== [opcion seleccionada] : CONFIGURACION LOGO");
			
			if(brandBean!=null && !Utilitario.isVacioOrNull(brandBean.getIntIdBrand()))
				brandSelected=  brandBean.getIntIdBrand();
			else
				brandSelected = Integer.parseInt(Utilitario.getStringResourceBundle("MARCA_DEFAULT"));
			
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info("Se obtiene Configuracion marca: " + nombreMarca);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error al ir a la pantalla Configuracion  Logo ",e);
		}
		return SUCCESS;
	}
	
	
	
	
	@SkipValidation
	@Action(value = "/logoIssuer", results = { @Result(params={"inputName","imagenLogoIssuer"}, name = "success", type="stream") })
	public String imagenDistrito() throws Exception {
		try {
			
			brandSelected = Utilitario.isVacioOrNull(brandSelected)? Integer.parseInt(Utilitario.getStringResourceBundle("MARCA_DEFAULT")): brandSelected;
			
			String nombreMarca= Utilitario.getStringResourceBundle("MARCA."+brandSelected).trim();
			Utilitario.getLOG_APP().info("Se obtiene Configuracion Logo de la marca: " + nombreMarca);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			UserBean usuario =(UserBean)sessionMapLogo.get("ObjetoUsuario");
			
			FileImplementation fileimpl =new FileImplementation(datasource);
			fileBean =  new FileBean();
			fileBean = fileimpl.getFilexIdEmisor(usuario.getIntIdIssuer());
			
			imagenLogoIssuer = null;
			
			if(fileBean!=null && fileBean.getBlFile()!=null){
				Utilitario.getLOG_APP().info("Se muestra el logo de la marca "+ nombreMarca);
				imagenLogoIssuer =new ByteArrayInputStream(fileBean.getBlFile()) ;
			}else
				Utilitario.getLOG_APP().info("Emisor no tiene registrado Logo para la marca "+ nombreMarca);
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error al Obtener Logo ",e);
		}
		return SUCCESS;
	}	

	
	@Action(value = "/cargarLogoIssuer", results = { @Result(location = "t_configLogo", name = "success", type="tiles"),
			@Result(location = "t_configLogo", name = "input", type="tiles") })
	public String cargarLogoIssuer(){
		
		try {
			Utilitario.getLOG_APP().info("<INICIO>  METODO: ------ cargarLogoIssuer ----");
			
			
			com.alignet.configurador.emisor.hibernate.bean.FileBean filehibernate= new com.alignet.configurador.emisor.hibernate.bean.FileBean();
			
			UserBean usuario =(UserBean)sessionMapLogo.get("ObjetoUsuario");
			Integer idEmisor=  usuario.getIntIdIssuer();
			
			Utilitario.getLOG_APP().info("ID MARCA "+ brandSelected);
			
			Integer datasource = Integer.parseInt( Utilitario.getStringResourceBundle("MARCA.DATASOURCE."+brandSelected)  ) ;
			FileImplementation fileimpl =new FileImplementation(datasource);
			
			FileBean fileBeanObtenido= fileimpl.getFilexIdEmisor(usuario.getIntIdIssuer());
			
			if(fileBeanObtenido!=null)
				filehibernate.setIntIdFile(fileBeanObtenido.getIntIdFile());
			
			filehibernate.setIntType(Parameters.IN_TYPELOGOEMISOR);
			filehibernate.setIntIdReference(Parameters.IN_IDREFERENCE);
			filehibernate.setIntIdIssuer(idEmisor);
			filehibernate.setBlFile( Utilitario.getBytesFromFile(fileBean.getFileLogoIssuer() ) );
			filehibernate.setDtRegistry(new Date());

			if(fileimpl.SaveUpdateLogoEmisor(filehibernate)){
				Utilitario.getLOG_APP().info("La imagen se cargo correctamente");
				addFieldError("mensajeSUCCESS", " La imagen se cargo correctamente");
			}else{
				Utilitario.getLOG_APP().info("Error al cargar Logo, verificar log de BD ");
				addFieldError("mensajeERROR", " Error al cargar imagen, Intente mas tarde ");
			}
			
			Utilitario.getLOG_APP().info("<FIN>     METODO: ------ cargarLogoIssuer ----");

			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error al cargar Logo ",e);
			return INPUT;
		}

		return SUCCESS;
	}
	

	@SkipValidation
    @Action(value="/irDescargarManual", 
    results={
        @Result(name="success", type="stream", 
        params = {
                "contentType", "application/zip",
                "inputName", "fileInputStream",
                "contentDisposition", "filename=GPRY_ACS_Pantallas_Configuracion-VMA.zip",
                "bufferSize", "1024"
        })
    }           
	)    
	public String downloadManual(){
	    try {
	    	Utilitario.getLOG_APP().info("========== [opcion seleccionada] : DESCARGAR MANUAL");
			
	    	String NombreArchivoZIP = Utilitario.getStringResourceBundle("fileNameManual").trim() ;
	    	String DirectorioServidorArchivoZIP = Utilitario.getStringResourceBundle("pathZIPManual").trim() ;
	    	
	    	Utilitario.getLOG_APP().info("Directorio a descargar archivo: "+ DirectorioServidorArchivoZIP);
	    	Utilitario.getLOG_APP().info("Nombre archivo a descargar : "+ NombreArchivoZIP);

			fileInputStream = new FileInputStream(new File(DirectorioServidorArchivoZIP, NombreArchivoZIP));
			 
			Utilitario.getLOG_APP().info("El archivo se descargo correctamente");
	        
		} catch (FileNotFoundException e) {
			
			Utilitario.getLOG_APP().error("Archivo no encontrado " , e );
			
		} catch (Exception e) {
			Utilitario.getLOG_APP().error("Error al descargar archivos ",e);
		}

	    return SUCCESS;
	}
	
	
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMapLogo((SessionMap<String, Object>) sessionMap);
	}


	public SessionMap<String, Object> getSessionMapLogo() {
		return sessionMapLogo;
	}


	public void setSessionMapLogo(SessionMap<String, Object> sessionMapLogo) {
		this.sessionMapLogo = sessionMapLogo;
	}


	public FileBean getFileBean() {
		return fileBean;
	}


	public void setFileBean(FileBean fileBean) {
		this.fileBean = fileBean;
	}

	public InputStream getImagenLogoIssuer() {
		return imagenLogoIssuer;
	}

	public void setImagenLogoIssuer(InputStream imagenLogoIssuer) {
		this.imagenLogoIssuer = imagenLogoIssuer;
	}

	public ArrayList<BrandBean> getListBrandHabilitadasEmisor() {
		listBrandHabilitadasEmisor =( ArrayList<BrandBean>)sessionMapLogo.get("listBrandHabilitadasEmisor");
		return listBrandHabilitadasEmisor;
	}

	public void setListBrandHabilitadasEmisor(ArrayList<BrandBean> listBrandHabilitadasEmisor) {
		this.listBrandHabilitadasEmisor = listBrandHabilitadasEmisor;
	}

	public BrandBean getBrandBean() {
		return brandBean;
	}

	public void setBrandBean(BrandBean brandBean) {
		this.brandBean = brandBean;
	}

	public Integer getBrandSelected() {
		return brandSelected;
	}

	public void setBrandSelected(Integer brandSelected) {
		this.brandSelected = brandSelected;
	}


	public String getOperacionValidate() {
		return operacionValidate;
	}


	public void setOperacionValidate(String operacionValidate) {
		this.operacionValidate = operacionValidate;
	}
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}


}
