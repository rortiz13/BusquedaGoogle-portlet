package la.netco.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletResponse;

import la.netco.controllerBusqueda.ControllerBusqueda;
import la.netco.entities.DatosBusq;
import la.netco.entities.DatosPersonas;
import la.netco.entities.Solicitante;
import la.netco.entities.selecItem;
import la.netco.pdf.GenerarPDF;

import org.primefaces.model.StreamedContent;

import com.liferay.portal.util.PortalUtil;

@ManagedBean(name="BeanBusqueda")
@ViewScoped
public class BusquedaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private  String buscar;
	    private static DatosBusq datosBusq;
	    private static Solicitante solic;
	    private static List<DatosBusq> datosBusqs;
	    private String clsReg;
	    private List<selecItem> clsRegs;
	    private List<selecItem> tipoPers;
	    private static List<selecItem> datosObra;
	    private static List<selecItem> datosFono;
		private String tipoPer;
//	    private Date fechaDesde;
//	    private Date fechaHasta;
	    private String titulo;
	    private String cedula;
	    private String entidad;
	    private String nombre;
	    private String apellido1;
	    private String apellido2;
	    private String cdoReg;
	    private String noReg;
	    private String fechaDesde;
	    private String fechaHasta;
	    
	    
	    public String getFechaDesde() {
			return fechaDesde;
		}


		public void setFechaDesde(String fechaDesde) {
			this.fechaDesde = fechaDesde;
		}


		public String getFechaHasta() {
			return fechaHasta;
		}


		public void setFechaHasta(String fechaHasta) {
			this.fechaHasta = fechaHasta;
		}


		public String getCdoReg() {
			return cdoReg;
		}


		public void setCdoReg(String cdoReg) {
			this.cdoReg = cdoReg;
		}


		public String getNoReg() {
			return noReg;
		}


		public void setNoReg(String noReg) {
			this.noReg = noReg;
		}


		private static String tipoObra;
	    private static List<DatosPersonas> datosPer;
	    
	    public BusquedaBean()
	    {
	        clsRegs = new ArrayList<selecItem>();
	        tipoPers = new ArrayList<selecItem>();
	        ResultSet rs = ControllerBusqueda.selectClsReg();
	        selectClsReg(rs);
	        ResultSet rs1 = ControllerBusqueda.selectTipoPer();
	        selectTipoPer(rs1);
	    }
	    
	    
		public String getBuscar() {
			return buscar;
		}
		public void setBuscar(String buscar) {
			this.buscar = buscar;
		}
		public  DatosBusq getDatosBusq() {
			return datosBusq;
		}
		public  void setDatosBusq(DatosBusq datosBusq) {
			BusquedaBean.datosBusq = datosBusq;
		}
		public  Solicitante getSolic() {
			return solic;
		}
		public  void setSolic(Solicitante solic) {
			BusquedaBean.solic = solic;
		}
		public  List<DatosBusq> getDatosBusqs() {
			return datosBusqs;
		}
		public  void setDatosBusqs(List<DatosBusq> datosBusqs) {
			BusquedaBean.datosBusqs = datosBusqs;
		}
		public String getClsReg() {
			return clsReg;
		}
		public void setClsReg(String clsReg) {
			this.clsReg = clsReg;
		}
		public List<selecItem> getClsRegs() {
			return clsRegs;
		}
		public void setClsRegs(List<selecItem> clsRegs) {
			this.clsRegs = clsRegs;
		}
		public List<selecItem> getTipoPers() {
			return tipoPers;
		}
		public void setTipoPers(List<selecItem> tipoPers) {
			this.tipoPers = tipoPers;
		}
		public String getTipoPer() {
			return tipoPer;
		}
		public void setTipoPer(String tipoPer) {
			this.tipoPer = tipoPer;
		}
//		public Date getFechaDesde() {
//			return fechaDesde;
//		}
//		public void setFechaDesde(Date fechaDesde) {
//			this.fechaDesde = fechaDesde;
//		}
//		public Date getFechaHasta() {
//			return fechaHasta;
//		}
//		public void setFechaHasta(Date fechaHasta) {
//			this.fechaHasta = fechaHasta;
//		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getCedula() {
			return cedula;
		}
		public void setCedula(String cedula) {
			this.cedula = cedula;
		}
		public String getEntidad() {
			return entidad;
		}
		public void setEntidad(String entidad) {
			this.entidad = entidad;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido1() {
			return apellido1;
		}
		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}
		public String getApellido2() {
			return apellido2;
		}
		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}
		public  String getTipoObra() {
			return tipoObra;
		}
		public  void setTipoObra(String tipoObra) {
			BusquedaBean.tipoObra = tipoObra;
		}
		public  List<DatosPersonas> getDatosPer() {
			return datosPer;
		}
		public  void setDatosPer(List<DatosPersonas> datosPer) {
			BusquedaBean.datosPer = datosPer;
		}
	     public  List<selecItem> getDatosFono() {
				return datosFono;
		}
		public  void setDatosFono(List<selecItem> datosFono) {
				BusquedaBean.datosFono = datosFono;
		}
		
		public String Busq() throws ParseException 
	    {	
			String filtroRPE="";
	        if(tipoPer != null)
	        {          
	            filtroRPE +=" AND RPE_TRP='"+tipoPer+"' ";
	        }
			String filtro = consAvanzada();				
		    ResultSet rs = ControllerBusqueda.BusquedaSimple(buscar, filtro.toUpperCase(),filtroRPE);
		    LlenardatosBusqs(rs);
		    return "listBusq";
		}
	
		private String consAvanzada()
	    {
//	        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);			
	        String filtro = "";
	        
	        if(clsReg != null)
	        {
	            filtro+=" AND CRE_ID='"+clsReg+"' ";
	        }
	        if(fechaDesde != "")
	        {
//	            filtro+=" AND REG_FEC>'"+df1.format(fechaDesde)+"' ";
	        	filtro+=" AND cast(REG_FEC as date)>='"+fechaDesde+"' ";
	        }
	        if(fechaHasta != "")
	        {
//	            filtro+=" AND REG_FEC<'"+df1.format(fechaHasta)+"' ";
	        	filtro+=" AND cast(REG_FEC as date)<='"+fechaHasta+"' ";
	        }
	        if(titulo != null && !"".equals(titulo))
	        {
	            filtro+=" AND REG_TOR='"+" AND REG_TOR='"+"' ";
	        }
	        if(cedula != null && !"".equals(cedula))
	        {
	            filtro+=" AND PER_DOC='"+cedula+"' ";
	        }
	        if(entidad != null && !"".equals(entidad))
	        {
	            String vec[] = entidad.split(" ");
	            for(int i = 0; i < vec.length; i++)
	            {
	                filtro+=" AND contains(ETD_NOM,'"+vec[i]+"') ";
	            }

	        }
	        if(nombre != null && !"".equals(nombre))
	        {
	            String vec[] = nombre.split(" ");
	            for(int i = 0; i < vec.length; i++)
	            {
	                filtro+=" AND contains(PER_NOM,'"+vec[i]+"') ";
	            }

	        }
	        if(apellido1 != null && !"".equals(apellido1))
	        {
	            String vec[] = apellido1.split(" ");
	            for(int i = 0; i < vec.length; i++)
	            {
	                filtro+=" AND contains(PER_PAP,'"+vec[i]+"') ";
	            }

	        }
	        if(apellido2 != null && !"".equals(apellido2))
	        {
	            String vec[] = apellido2.split(" ");
	            for(int i = 0; i < vec.length; i++)
	            {
	                filtro+=" AND contains(PER_SAP,'"+vec[i]+"') ";
	            }

	        }
	        return filtro;
	    }
		private void LlenardatosBusqs(ResultSet result) throws ParseException
	    {
	        datosBusqs = new ArrayList<DatosBusq>();
	        try
	        {
	            if(result != null)
	            {
	            	
	            	
	               while( result.next()){
	                		String fec= result.getString(4).substring(0,10);
	    	            	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	            	DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
	    	            	java.util.Date startDate = df.parse(fec);
	                		datosBusqs.add(new DatosBusq(result.getString(3), result.getString(1), df1.format(startDate), result.getString(2), result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getString(11))); 
	                	}
	            } else
	            {
	                System.out.println("no ahy coincidencias de busqueda");
	            }
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
		private void selectClsReg(ResultSet result)
	    {
	        try
	        {
	            if(result != null)
	            {
	                for(; result.next(); clsRegs.add(new selecItem(result.getString(1), result.getString(2)))) { }
	            } else
	            {
	                System.out.println("no ahy coincidencias de busqueda");
	            }
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
		private void selectTipoPer(ResultSet result)
	    {
	        try
	        {
	            if(result != null)
	            {
	                for(; result.next(); tipoPers.add(new selecItem(result.getString(1), result.getString(2)))) { }
	            } else
	            {
	                System.out.println("no ahy coincidencias de busqueda");
	            }
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	    }
//		public String imprimirRegistro(DatosBusq a)
//		        throws Exception
//		    {
//		        datosBusq = a;
//		        System.out.println("entrtht");
//		        ResultSet certificado = ControllerBusqueda.Certificado(a.getnRegistro());
//		        CargarPersonas(certificado);		        
//		        tipoObra = a.getTipoObra();
//		        ResultSet perSol = ControllerBusqueda.solicitanteqry(a.getnRegistro());
//		        CargarSolic(perSol);
//		        ResultSet datobr=ControllerBusqueda.datosObras(a.getnRegistro());
//		        llenardaObr(datobr);		        
//		        if(a.getTipoObra().equals("REGISTRO DE FONOGRAMAS")){
//			        ResultSet obrAut = ControllerBusqueda.fonogramasObrasAutor(a.getnRegistro());
//			        llenardaFono(obrAut);
//			        return "regFonogramas";
//		        }else{
//		        	return "registroInfo";
//		        }
//		    }
		public void CargarPersonas(ResultSet result)
		        throws SQLException
		    {
		        datosPer = new ArrayList<DatosPersonas>();
		        if(result != null)
		        {
		            while(result.next()) 
		            {
		            	String cedula="";
		            	if(result.getString(10)!=null){
		            		cedula=result.getString(10).trim();		            		
		            	}
		            	datosPer.add(new DatosPersonas(cedula, result.getString(5).trim()+" "+result.getString(6).trim()+" "+result.getString(7).trim(), result.getString(11).trim(), result.getString(13).trim(), result.getString(9).trim()));
		            }
		        }
		    }
		public void CargarSolic(ResultSet result)
		        throws SQLException
		    {
		        if(result != null)
		        {
		            while(result.next()) 
		            {
		                solic = new Solicitante((new StringBuilder(String.valueOf(result.getString(1).trim()))).append(" ").append(result.getString(2).trim()).append(" ").append(result.getString(3).trim()).toString(), result.getString(4).trim(), result.getString(5).trim(), result.getString(6).trim(), result.getString(7).trim(), result.getString(8).trim(), result.getString(9).trim());
		            }
		        }
		    }
		
//		private void llenardaObr(ResultSet result) {
//			
//			datosObra= new ArrayList<selecItem>();
//				try {
//					if(result!=null){				      
//					       while(result.next()){			    	  
//					    	   datosObra.add(new selecItem( result.getString(1), result.getString(2)));   
//					       }
//						
//					}else{					
//						System.out.println("no ahy coincidencias de busqueda");
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//		private void llenardaFono(ResultSet result) {
//			
//			datosFono= new ArrayList<selecItem>();
//				try {
//					if(result!=null){				      
//					       while(result.next()){			    	  
//					    	   datosFono.add(new selecItem( result.getString(1), result.getString(2)));   
//					       }
//						
//					}else{					
//						System.out.println("no ahy coincidencias de busqueda");
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}

		public List<selecItem> getDatosObra() {
			return datosObra;
		}


		public void setDatosObra(List<selecItem> datosObra) {
			BusquedaBean.datosObra = datosObra;
		}
//		private StreamedContent file;  
	      
		  
	    public StreamedContent getFile(DatosBusq a) throws Exception {  
	    	System.out.println("Inicio Getfile");
	    	
		    ResultSet certificado = ControllerBusqueda.Certificado(a.getnRegistro());
		    tipoObra = a.getTipoObra();
		    ResultSet perSol = ControllerBusqueda.solicitanteqry(a.getnRegistro());
		    ResultSet datobr=ControllerBusqueda.datosObras(a.getnRegistro());	
		    ResultSet obrAut = ControllerBusqueda.fonogramasObrasAutor(a.getnRegistro());
		    
	    	GenerarPDF obj= new GenerarPDF();
	    	obj.ITextHelloWorld(certificado,a,datobr, perSol,obrAut);
	    	 
	    	
	    	 // 1. initialize the fileInputStream
	    	 File localfile = new File("PDF/"+a.getnRegistro().trim()+".pdf");
	    	 FileInputStream fis = new FileInputStream(localfile);
	    	 System.out.println(fis);
	    	 // 2. get Liferay's ServletResponse
	    	 PortletResponse portletResponse = (PortletResponse) FacesContext
	    	   .getCurrentInstance().getExternalContext().getResponse();
	    	 HttpServletResponse res = PortalUtil
	    	   .getHttpServletResponse(portletResponse);
	    	 res.setHeader("Content-Disposition", "attachment; filename=\""
	    	   +"RegDNDA"+a.getnRegistro().trim()+".pdf" + "\"");//
	    	 res.setHeader("Content-Transfer-Encoding", "binary");
	    	 res.setContentType("application/octet-stream");
	    	 res.flushBuffer();

	    	 // 3. write the file into the outputStream
	    	 OutputStream out = res.getOutputStream();
	    	 byte[] buffer = new byte[4096];
	    	 int bytesRead;
	    	 while ((bytesRead = fis.read(buffer)) != -1) {
	    	  out.write(buffer, 0, bytesRead);
	    	  buffer = new byte[4096];
	    	 }
	    	 System.out.println("fin Getfile");
	    	 // 4. return null to this method
	    	 return null;
	    }  

}
