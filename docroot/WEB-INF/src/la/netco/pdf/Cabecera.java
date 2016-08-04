package la.netco.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import la.netco.entities.DatosBusq;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Clase que maneja los eventos de pagina necesarios para agregar un encabezado y  conteo de paginas a un documento.
 * El encabezado, definido en onEndPage, consiste en una tabla con 3 celdas que contienen:
 * Frase del encabezado | pagina <numero de pagina> de | total de paginas, con una linea horizontal separando el
 * encabezado del texto
 * 
 * Referencia: http://itextpdf.com/examples/iia.php?id=104
 * 
 * @author David
 */

public class Cabecera extends PdfPageEventHelper {
   
    PdfTemplate total;
    private DatosBusq a;
    Font fuente=new Font(Font.getFamily("ARIAL"),10);
    Font fuentecert=new Font(Font.getFamily("ARIAL"),10,Font.BOLD ,new BaseColor(0, 0, 139));
    Font fuente1n=new Font(Font.getFamily("ARIAL"),9,Font.BOLD);
    Font fuente2=new Font(Font.getFamily("ARIAL"),7);
    
    public DatosBusq getA() {
		return a;
	}

	public void setA(DatosBusq a) {
		this.a = a;
	}

	/**
     * Crea el objecto PdfTemplate el cual contiene el numero total de
     * paginas en el documento
     */
	public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    
    /**
     * Esta es el metodo a llamar cuando ocurra el evento <b>onEndPage</b>, es en este evento
     * donde crearemos el encabeazado de la pagina con los elementos indicados.
     */
    public void onEndPage(PdfWriter writer, Document document) {
    	PdfPTable table = new PdfPTable(3);
        try {
            // Se determina el ancho y altura de la tabla 
            table.setWidths(new int[]{12, 30,8});
            table.setTotalWidth(523);
            table.setLockedWidth(true);
//            table.getDefaultCell().setFixedHeight(20);
            
            // Borde de la celda
//            table.getDefaultCell().setBorder(Rectangle.OUT_BOTTOM | Rectangle.OUT_RIGHT);
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
    	        image.setAlignment(Image.LEFT | Image.TEXTWRAP |Element.ALIGN_CENTER);
    	        PdfPCell tit1=new PdfPCell(image);
    	        tit1.setBorder(Rectangle.OUT_RIGHT | Rectangle.OUT_TOP | Rectangle.OUT_LEFT); 
            table.addCell(tit1);
            
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
	       
	        PdfPTable centro=null;
	        centro=new PdfPTable(1);
//	        centro.setWidthPercentage(80);
	        centro.addCell(te1);
	        centro.addCell(te2);
	        centro.addCell(te3);
	        centro.addCell(te4);
	        centro.addCell(te5);
	        PdfPCell tit2=new PdfPCell(centro);
	        tit2.setBorder(Rectangle.OUT_TOP | Rectangle.OUT_LEFT); 
//	        tit2.setColspan(3);
	        table.addCell(tit2);
//            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(String.format("Pagina "+writer.getPageNumber()));
	        PdfPTable tab=null,otra;
	        tab=new PdfPTable(1); otra=new PdfPTable(2);
//	        tabla.setWidthPercentage(80);
	        tab.addCell(new PdfPCell(new Paragraph("\nLibro - Tomo - Partida: ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        tab.addCell(new PdfPCell(new Paragraph(a.getnRegistro(),fuente1n))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        tab.addCell(new PdfPCell(new Paragraph("\nFecha de Registro: ",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
	        tab.addCell(new PdfPCell(new Paragraph(a.getFechaReg()+"\n",fuente1n))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);	            
	       	
	        Image tot=Image.getInstance(total);
	        tot.scaleToFit(20,20);
	        PdfPCell cell = new PdfPCell(tot);
            cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
//            tab.addCell(cell);
            otra.addCell(new PdfPCell(new Paragraph("Pagina "+writer.getPageNumber()+" de",fuente2))).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            otra.addCell(cell).setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
            tab.addCell(new PdfPCell(otra));
	        PdfPCell tit3=new PdfPCell(tab);
	        table.addCell(tit3);
            // Esta linea escribe la tabla como encabezado
            table.writeSelectedRows(0, -1, 36, 792, writer.getDirectContent());
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    
    
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    } 
  
}
