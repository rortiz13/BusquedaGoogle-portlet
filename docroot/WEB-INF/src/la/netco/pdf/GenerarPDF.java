package la.netco.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.netco.entities.DatosBusq;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class GenerarPDF {
	//tablas
	PdfPTable encabezado=new PdfPTable(5);
	PdfPTable general=null,firma=null,obs=null,footer=null,solic=null,datosObra=null,desc=null,obrasFonograma=null,fonogramas=null,clase=null,objact=null,valor=null,lugar=null;
	PdfPTable datosPersonas=new PdfPTable(5);
	//fuentes
	Font fuente=new Font(Font.getFamily("ARIAL"),10);
	Font fuente1n=new Font(Font.getFamily("ARIAL"),9,Font.BOLD);
	Font fuentecer=new Font(Font.getFamily("ARIAL"),10,Font.BOLD | Font.UNDERLINE ,new BaseColor(0, 0, 139));
	Font fuentecert=new Font(Font.getFamily("ARIAL"),10,Font.BOLD ,new BaseColor(0, 0, 139));
    Font fuente1=new Font(Font.getFamily("ARIAL"),7,Font.BOLD);
    Font fuente11=new Font(Font.getFamily("ARIAL"),7,Font.BOLD | Font.UNDERLINE,new BaseColor(14, 48, 200));
    Font fuente2=new Font(Font.getFamily("ARIAL"),7);
    Font fuenteV=new Font(Font.getFamily("ARIAL"),8,Font.BOLD | Font.ITALIC ,new BaseColor(39,139, 34));
    Font fuenteR=new Font(Font.getFamily("ARIAL"),8,Font.BOLD,new BaseColor(5, 39, 191));
    int cont;
    
    //
 
	
	public void ITextHelloWorld(ResultSet result, DatosBusq a,ResultSet result2,ResultSet result3,ResultSet result4) throws Exception{
		try{
	        Document document = new Document(PageSize.A4, 36, 36, 140, 36); 
	        cont=1;
	        general=new PdfPTable(1);
	    	general.setWidthPercentage(100);
	        
	       llenarEncabezado(a);         
	       llenarDatosPersonas(result); 
	       llenarDatosObra(a,result2,result3);
	       llenarActosContratos(a, result3);
	       llenarFonogramas(result4, result3, a);
	       llenarDescObra(a);
	       llenarObse(a);       
	       llenarSolicitante(result3,a);
	       llenarFirma(result3);       
	       llenarFooter();
	      
	       
	       
	        //Armar encabezado general
	//       	general.addCell(new PdfPCell(new Paragraph("\n\n",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	//        general.addCell(encabezado);    // Encabezado
	        general.addCell(datosPersonas); 
	        general.addCell(datosObra);
	        if(a.getTipoObra().equals("REGISTRO DE SOPORTE LOGICO") || a.getTipoObra().equals("REGISTRO DE OBRA LITERARIA INEDITA") || a.getTipoObra().equals("REGISTRO DE OBRA AUDIOVISUAL") || a.getTipoObra().trim().equals("REGISTRO DE OBRA ARTISTICA")){
	        	general.addCell(desc);
	    	}
	        if(a.getTipoObra().equals("REGISTRO DE FONOGRAMAS") ){
	        	general.addCell(fonogramas);
	        	general.addCell(clase);
	        	general.addCell(obrasFonograma);
	    	}	
	        if(a.getTipoObra().equals("REGISTRO DE CONTRATO Y DEMAS ACTOS")){
	        	general.addCell(objact);
	        	general.addCell(clase);
	        	general.addCell(valor);
	        	general.addCell(lugar);
	        }
	        general.addCell(obs);         //firma
	        general.addCell(solic);
	        general.addCell(firma);
	        
	        
	        //Montar PDF final
	        PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("PDF/"+a.getnRegistro().trim()+".pdf"));
	        Cabecera encabezado = new Cabecera();  
	        encabezado.setA(a);
	        writer.setPageEvent(encabezado);
	        document.open();
	        document.addCreator("DNDA");
	        document.addAuthor("Direccion Nacional de Derecho de Autor");
	        document.addTitle("Certificado de Registro");
	        document.add(general);  
	        document.add(footer);//
	        document.close();
		}catch(Exception e){
			System.out.println(" Error al PDF "+e);
		}
       
    }
    
	
	@SuppressWarnings("static-access")
		public void llenarEncabezado(DatosBusq a){
		try{
			 Image image=null;
			try {
				 image= Image.getInstance("logoDNDA.png");			
		        
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        image.scaleToFit(130,90);
		        image.setAlignment(image.LEFT | image.TEXTWRAP |Element.ALIGN_CENTER);
		      
		        
		        Paragraph cab=new Paragraph("                   MINISTERIO DEL INTERIOR \n",fuente);
		        cab.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell te1=new PdfPCell(cab);
		        te1.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  
		        Paragraph l1=new Paragraph("     DIRECCION NACIONAL DE DERECHO DE AUTOR   \n",fuente);
		        l1.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell te2=new PdfPCell(l1);
		        te2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        Paragraph l2=new Paragraph("             UNIDAD ADMINISTRATIVA ESPECIAL     \n",fuente);
		        l2.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell te3=new PdfPCell(l2);
		        te3.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        Paragraph l3=new Paragraph("                             OFICINA DE REGISTRO    \n",fuente);
		        l3.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell te4=new PdfPCell(l3);
		        te4.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        Paragraph l4=new Paragraph(" CERTIFICADO DE "+a.getTipoObra()+"      \n \n \n",fuentecert);
		        l4.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell te5=new PdfPCell(l4);
		        te5.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		       
		        PdfPTable tabla=null;
		        tabla=new PdfPTable(1);
	//	        tabla.setWidthPercentage(80);
		        tabla.addCell(te1);
		        tabla.addCell(te2);
		        tabla.addCell(te3);
		        tabla.addCell(te4);
		        tabla.addCell(te5);
		        
		        encabezado.setWidthPercentage(100);
		        
	//	        String dereTit="\n\n\nLibro Tomo Partida: \n"+a.getnRegistro()+"\nFecha de Registro: \n"+a.getFechaReg().substring(0,10);
	//	        Paragraph regi = new Paragraph(dereTit,fuente1);
		        
		        PdfPTable tab=null;
		        tab=new PdfPTable(1);
	//	        tabla.setWidthPercentage(80);
		        tab.addCell(new PdfPCell(new Paragraph("\n\nLibro - Tomo - Partida: ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        tab.addCell(new PdfPCell(new Paragraph(a.getnRegistro(),fuente1n))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        tab.addCell(new PdfPCell(new Paragraph("\nFecha de Registro: ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        tab.addCell(new PdfPCell(new Paragraph(a.getFechaReg(),fuente1n))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        PdfPCell tit1=new PdfPCell(image);
		        PdfPCell tit2=new PdfPCell(tabla);
		        PdfPCell tit3=new PdfPCell(tab);        
		        tit1.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  
		        tit2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  
		        encabezado.addCell(tit1);
		        tit2.setColspan(3);
		        encabezado.addCell(tit2);
		        encabezado.addCell(tit3);
		}catch(Exception e){
			System.out.println("Error encabezado "+e);
		}
	}
	@SuppressWarnings("static-access")
	public void llenarFirma(ResultSet result3) throws Exception{
//		result3.next();
		Image image2= Image.getInstance("firma.png");
	
        image2.scaleToFit(150,150);
        image2.setAlignment(image2.LEFT | image2.TEXTWRAP |Element.ALIGN_CENTER);
        
        firma=new PdfPTable(1);
        firma.setWidthPercentage(100);
        PdfPCell cel2 =new PdfPCell(image2);
        cel2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER); 
        firma.addCell(new PdfPCell(new Paragraph("\n"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        firma.addCell(cel2);  
        PdfPCell cel =new PdfPCell(new Paragraph("CARLOS ANDRES CORREDOR BLANCO",fuente1n));
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setBorder(com.itextpdf.text.Rectangle.NO_BORDER); 
        firma.addCell(cel);        
        PdfPCell cel1 =new PdfPCell(new Paragraph("JEFE OFICINA DE REGISTRO",fuente1));
        cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel1.setBorder(com.itextpdf.text.Rectangle.NO_BORDER); 
        firma.addCell(cel1);
        firma.addCell(new PdfPCell(new Paragraph("\n"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        PdfPCell cela=new PdfPCell(new Paragraph(result3.getString(26),fuente2));
        cela.setHorizontalAlignment(Element.ALIGN_LEFT);
        cela.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        firma.addCell(cela);
	}
	public void llenarFooter(){
		 footer=new PdfPTable(1);
	        footer.setWidthPercentage(100);
	        Font fuente3=new Font(Font.getFamily("ARIAL"),7);
	        footer.addCell(new PdfPCell(new Paragraph("Nota: El derecho de autor protege exclusivamente la forma mediante la cual las ideas del autor son descritas, explicadas, ilustradas o incorporadas a las" +
	        		"obras . No son objeto de protección las ideas contenidas en las obras literarias y artísticas, o el contenido ideológico o técnico de las obras científicas, ni su" +
	        		"aprovechamiento industrial o comercial (artículo 7o. de la Decisión 351 de 1993).",fuente3)));
	        footer.addCell(new PdfPCell(new Paragraph("\n"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        
	        
	}
	public void llenarObse(DatosBusq a){

        obs=new PdfPTable(1);
    	obs.setWidthPercentage(100);
    	PdfPCell cel1 =new PdfPCell(new Paragraph("\n"+cont++ +".OBSERVACIONES GENERALES DE LA OBRA\n",fuentecer));
        cel1.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	obs.addCell(cel1);
    	obs.addCell(new PdfPCell(new Paragraph("\n"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	if(!a.getObs().trim().equals("")){
	    	PdfPCell cel2 =new PdfPCell(new Paragraph(a.getObs()+"\n",fuente2));
	        cel2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	obs.addCell(cel2);
    	}else{
    		PdfPCell cel2 =new PdfPCell(new Paragraph("--\n",fuente2));
	        cel2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	obs.addCell(cel2);
    	}
	        
	        
	}
	public void llenarDatosPersonas(ResultSet result){
//		String parrafo2="";        
        datosPersonas.setWidthPercentage(100);
        PdfPCell datos_personas=new PdfPCell(new Paragraph("\n"+cont++ +".DATOS DE LAS PERSONAS\n",fuentecer));
        datos_personas.setColspan(5);
        datos_personas.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        PdfPCell linea=new PdfPCell(new Paragraph("\n",fuentecer));
        linea.setColspan(5);
        linea.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        datosPersonas.addCell(datos_personas);
        datosPersonas.addCell(linea);
       try {
			if(result!=null){				      
			       while(result.next()){
			    	   PdfPCell tipoInterviniente=null,tipoInterviniente2=null,nomBD=null,nacBD=null,dirBD=null,ciuBD=null;
			    	if(result.getString(9)!=null){   
			    		tipoInterviniente=new PdfPCell(new Paragraph(result.getString(9),fuenteV));  
			    	}else{
			    		 tipoInterviniente=new PdfPCell(new Paragraph("",fuenteV));
			    	}	
			    	tipoInterviniente.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	if(result.getString(14)!=null){
			    		tipoInterviniente2=new PdfPCell(new Paragraph(result.getString(14),fuenteR));  
			    	}else{
			    		tipoInterviniente2=new PdfPCell(new Paragraph("",fuenteR));
			    	}
			    	tipoInterviniente2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell nom=new PdfPCell(new Paragraph("Nombres y Apellidos ",fuente1)); 
			    	nom.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  	
			    	nomBD=new PdfPCell(new Paragraph(result.getString(5).trim()+" "+result.getString(6).trim()+" "+result.getString(7).trim(),fuente2));			    	
			    	nomBD.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell id=new PdfPCell(new Paragraph("Numero de Identificacion ",fuente1));
			    	id.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell idBD;			    	
			    	if(result.getString(10)!=null){
			    		 idBD=new PdfPCell(new Paragraph(result.getString(10).trim(),fuente2));
			    	}else{
			    		 idBD=new PdfPCell(new Paragraph("",fuente2));			    		
			    	}	
			    	idBD.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell vacio=new PdfPCell(new Paragraph("",fuente2));
			    	vacio.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell vacio2=new PdfPCell(new Paragraph("",fuente2));
			    	vacio2.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell nac=new PdfPCell(new Paragraph("Nacional de ",fuente1));
			    	nac.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	if(result.getString(11)!=null){
			    		nacBD=new PdfPCell(new Paragraph(result.getString(11).trim(),fuente2));
			    	}else{
			    		nacBD=new PdfPCell(new Paragraph("",fuente2));
			    	}
			    	nacBD.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell dir=new PdfPCell(new Paragraph("Direccion ",fuente1));
			    	dir.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	if(result.getString(12)!=null){
			    		dirBD=new PdfPCell(new Paragraph(result.getString(12).trim(),fuente2));
			    	}else{
			    		dirBD=new PdfPCell(new Paragraph("",fuente2));
			    	}
			    	dirBD.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	PdfPCell ciu=new PdfPCell(new Paragraph("Ciudad ",fuente1));
			    	ciu.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	if(result.getString(12)!=null){
			    		ciuBD=new PdfPCell(new Paragraph(result.getString(13).trim(),fuente2));
			    	}else{
			    		ciuBD=new PdfPCell(new Paragraph("",fuente2));
			    	}	
			    	ciuBD.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
			    	tipoInterviniente.setColspan(2);
			    	tipoInterviniente2.setColspan(3);
			    	datosPersonas.addCell(tipoInterviniente);
			    	datosPersonas.addCell(tipoInterviniente2);
			    	datosPersonas.addCell(nom);
			    	datosPersonas.addCell(nomBD);
			    	datosPersonas.addCell(vacio);
			    	datosPersonas.addCell(id);			    	
			    	datosPersonas.addCell(idBD);			    	
			    	datosPersonas.addCell(nac);
			    	datosPersonas.addCell(nacBD);
			    	vacio2.setColspan(3);
			    	datosPersonas.addCell(vacio2);
			    	datosPersonas.addCell(dir);
			    	dirBD.setColspan(2);
			    	datosPersonas.addCell(dirBD);			    	
			    	datosPersonas.addCell(ciu);
			    	datosPersonas.addCell(ciuBD);
			    	
			       }
				
			}else{					
				System.out.println("no ahy coincidencias de busqueda");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void llenarSolicitante(ResultSet result3,DatosBusq a) throws Exception {		
		if(result3!=null){ 
				solic=new PdfPTable(5);
//		        result3.next();
		        solic.setWidthPercentage(100);
		        PdfPCell titsolic =new PdfPCell(new Paragraph("\n"+cont++ +".DATOS DEL SOLICITANTE\n",fuentecer));
		        titsolic.setColspan(5);
		        titsolic.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        PdfPCell linea =new PdfPCell(new Paragraph("\n",fuentecer));
		        linea.setColspan(5);
		        linea.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(titsolic);
		        solic.addCell(linea);
		        solic.addCell(new PdfPCell(new Paragraph("Nombres y apellidos",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);           
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(1).trim()+" "+result3.getString(2).trim()+" "+result3.getString(3).trim(),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph("No. de Identificacion",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(4),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph("Nacional de",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(5),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph("Medio de Radicacion",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(10),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);		        
		        solic.addCell(new PdfPCell(new Paragraph("Ciudad",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(9),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph("En representacion de ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(a.getTipoObra().equals("REGISTRO DE CONTRATO Y DEMAS ACTOS")){
		        	solic.addCell(new PdfPCell(new Paragraph(result3.getString(22),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        }else{
		        	solic.addCell(new PdfPCell(new Paragraph(result3.getString(11),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        }
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph("Radicación de entrada ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        solic.addCell(new PdfPCell(new Paragraph(result3.getString(24),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        
		} 
		
	}
	public void llenarDatosObra(DatosBusq a,ResultSet result2,ResultSet result3)throws Exception{
		result3.next();
		if(!a.getTipoObra().equals("REGISTRO DE FONOGRAMAS")){
        	if(!a.getTipoObra().equals("REGISTRO DE CONTRATO Y DEMAS ACTOS")){
	        	datosObra=new PdfPTable(2);
	        	datosObra.setWidthPercentage(100);
		        PdfPCell datObr=new PdfPCell(new Paragraph("\n"+cont++ +".DATOS DE LA OBRA\n",fuentecer));
		        datObr.setColspan(2);
		        datosObra.addCell(datObr).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  
		        PdfPCell linea=new PdfPCell(new Paragraph("\n",fuentecer));
		        linea.setColspan(2);
		        datosObra.addCell(linea).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);  
		        PdfPCell title=new PdfPCell(new Paragraph("Título Original",fuente1));
		        datosObra.addCell(title).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        PdfPCell autor=new PdfPCell(new Paragraph(a.getTituloObra(),fuente1));
		        datosObra.addCell(autor).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        datosObra.addCell(new PdfPCell(new Paragraph("Año de Creacion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        datosObra.addCell(new PdfPCell(new Paragraph(a.getFechaCre(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	}else{
        		datosObra=new PdfPTable(2);
	        	datosObra.setWidthPercentage(100);
		        PdfPCell datObr=new PdfPCell(new Paragraph("\n"+cont++ +".CLASE DE CONTRATO O ACTO\n",fuentecer));
		        datObr.setColspan(2);
		        datosObra.addCell(datObr).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);   
		        datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(23),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        datosObra.addCell(new PdfPCell(new Paragraph(""))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		              
        		
        	}
	        if(a.getTipoObra().equals("REGISTRO DE OBRA AUDIOVISUAL")){
	        	datosObra.addCell(new PdfPCell(new Paragraph("Duracion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	if(result3.getString(18)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(18).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}
	        	datosObra.addCell(new PdfPCell(new Paragraph("País Origen",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	if(result3.getString(25)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(25).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}
	        }
	        if(a.getTipoObra().equals("REGISTRO DE OBRA LITERARIA EDITADA")){
	        	datosObra.addCell(new PdfPCell(new Paragraph("ISBN",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	if(result3.getString(12)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(12).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
		        datosObra.addCell(new PdfPCell(new Paragraph("No paginas",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(result3.getString(13)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(13).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
		        datosObra.addCell(new PdfPCell(new Paragraph("No edicion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(result3.getString(14)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(14).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
		        datosObra.addCell(new PdfPCell(new Paragraph("Fecha primera publicacion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(result3.getString(15)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(15).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
		        datosObra.addCell(new PdfPCell(new Paragraph("Tiraje",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(result3.getString(16)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(16).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
		        datosObra.addCell(new PdfPCell(new Paragraph("Pais primera publicacion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
		        if(result3.getString(17)==null){
	        		datosObra.addCell(new PdfPCell(new Paragraph("",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}else{
	        		datosObra.addCell(new PdfPCell(new Paragraph(result3.getString(17).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        	}	
	        	
	        }
	        

	        try {
				if(result2!=null){				      
				       while(result2.next()){
				    	datosObra.addCell(new PdfPCell(new Paragraph(result2.getString(1),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				        datosObra.addCell(new PdfPCell(new Paragraph(result2.getString(2),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				    		
				       }
					
				}else{					
					System.out.println("no ahy coincidencias de busqueda");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
        	fonogramas=new PdfPTable(1);
        	fonogramas.setWidthPercentage(100);
        	fonogramas.addCell(new PdfPCell(new Paragraph("\nTÍTULO\n",fuentecer))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	fonogramas.addCell(new PdfPCell(new Paragraph(a.getTituloObra(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	clase=new PdfPTable(2);
        	clase.setWidthPercentage(100);
        	PdfPCell celi=new PdfPCell(new Paragraph("\nCLASE E IDENTIFICACIÓN\n",fuentecer));
        	celi.setColspan(2);
        	clase.addCell(celi).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	clase.addCell(new PdfPCell(new Paragraph("Año 1ra fijacion",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	clase.addCell(new PdfPCell(new Paragraph(result3.getString(19),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	clase.addCell(new PdfPCell(new Paragraph("Numero",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        clase.addCell(new PdfPCell(new Paragraph(result3.getString(20),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
        	try {
				if(result2!=null){				      
				       while(result2.next()){
				    	clase.addCell(new PdfPCell(new Paragraph(result2.getString(1),fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				        clase.addCell(new PdfPCell(new Paragraph(result2.getString(2),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				    		
				       }
					
				}else{					
					System.out.println("no ahy coincidencias de busqueda");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void llenarDescObra(DatosBusq a){
		desc=new PdfPTable(1);
    	desc.setWidthPercentage(100);
    	desc.addCell(new PdfPCell(new Paragraph("\n"+cont++ +".DESCRIPCION DE LA OBRA\n",fuentecer))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	desc.addCell(new PdfPCell(new Paragraph("\n"))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	if(!a.getDescObra().trim().equals("")){
    		desc.addCell(new PdfPCell(new Paragraph(a.getDescObra()+"\n",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	}else{
    		desc.addCell(new PdfPCell(new Paragraph("--\n",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    	}	
	}
	public void llenarFonogramas(ResultSet result4,ResultSet result3, DatosBusq a){
		if(a.getTipoObra().equals("REGISTRO DE FONOGRAMAS") ){ 
			obrasFonograma=new PdfPTable(2);
	         obrasFonograma.setWidthPercentage(100);
	         PdfPCell obras=new PdfPCell(new Paragraph("\n"+cont++ +".OBRAS FIJADAS EN EL FONOGRAMA\n",fuentecer));
	         obras.setColspan(2);
	         obrasFonograma.addCell(obras).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	         PdfPCell linea=new PdfPCell(new Paragraph("\n"));
	         linea.setColspan(2);
	         obrasFonograma.addCell(linea).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	         PdfPCell title=new PdfPCell(new Paragraph("Título",fuente11));
	         obrasFonograma.addCell(title).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	         PdfPCell autor=new PdfPCell(new Paragraph("Autor",fuente11));
	         obrasFonograma.addCell(autor).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	
	        try {
				if(result3!=null){				      
				       while(result4.next()){
				    	PdfPCell titletq=new PdfPCell(new Paragraph(result4.getString(1),fuente2));  				    	
				    	PdfPCell autoretq=new PdfPCell(new Paragraph(result4.getString(2).trim(),fuente2));
				    	
				    	obrasFonograma.addCell(titletq).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				    	obrasFonograma.addCell(autoretq).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
				    		
				       }
					
				}else{					
					System.out.println("no ahy coincidencias de busqueda");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void llenarActosContratos(DatosBusq a,ResultSet result3) throws Exception{
		if(a.getTipoObra().equals("REGISTRO DE CONTRATO Y DEMAS ACTOS")){
			objact=new PdfPTable(1);
	    	objact.setWidthPercentage(100);
	    	objact.addCell(new PdfPCell(new Paragraph("\n"+cont++ +".OBJETO Y AÑO DE CREACIÓN\n",fuentecer))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	objact.addCell(new PdfPCell(new Paragraph("Objeto",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	objact.addCell(new PdfPCell(new Paragraph(a.getTituloObra(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);   
	    	
	    	valor=new PdfPTable(1);
	    	valor.setWidthPercentage(100);
	    	valor.addCell(new PdfPCell(new Paragraph("\n"+cont++ +".VALOR",fuentecer))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	valor.addCell(new PdfPCell(new Paragraph("Oneroso",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	valor.addCell(new PdfPCell(new Paragraph("   ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	valor.addCell(new PdfPCell(new Paragraph("Cuantia",fuente1))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	valor.addCell(new PdfPCell(new Paragraph(result3.getString(21).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	
	    	lugar=new PdfPTable(1);
	    	lugar.setWidthPercentage(100);
	    	lugar.addCell(new PdfPCell(new Paragraph("\n"+cont++ +".LUGAR Y FECHA DE LA FIRMA\n",fuentecer))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	    	lugar.addCell(new PdfPCell(new Paragraph(result3.getString(9).trim(),fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	
		}
	}


}
