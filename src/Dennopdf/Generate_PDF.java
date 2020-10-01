package Dennopdf;



import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Generate_PDF {

	public static void main(String[] args) {
		try{
		    String file_name="C:\\Users\\simon\\Desktop\\generation\\test.pdf";
		    Document document=new Document();
		    PdfWriter.getInstance(document, new FileOutputStream(file_name));
	        document.open();

	        Paragraph para=new Paragraph ("this is testing"); 
	        document.add(para);
	        document.add(new Paragraph(""));
	        document.add(new Paragraph(""));
	        document.add(new Paragraph(""));
	        document.add(new Paragraph(""));
	        
	           
			//add table
	        PdfPTable table= new PdfPTable(3);//three columns
	        PdfPCell c1 =new PdfPCell (new Phrase("Heading 1"));
	        table.addCell(c1);
	        PdfPCell c11 = new PdfPCell(new Phrase("heading 2"));
	        table.addCell(c11);
	        c11=new PdfPCell(new Phrase("heading 3"));
	        table.addCell(c11);
	        table.setHeaderRows(1);
	        table.addCell("1.0");
	        table.addCell("1.1");
	        table.addCell("1.2");
	        table.addCell("2.1");
	        table.addCell("2.2");
	        table.addCell("2.3");

	        document.add(table);

	     

	        document.close();
	        System.out.println("finished");
		}

		catch (Exception e)
	    {
	        e.printStackTrace();
	    }

	}

}
