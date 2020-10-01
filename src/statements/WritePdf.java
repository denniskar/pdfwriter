package statements;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class WritePdf {

	public static boolean writePdf(List<MemberStatement> memberSts, Member member, List<MemberLoan> memberLoans) {
		 //public static final PdfNumber LANDSCAPE = new PdfNumber(90);
		
		
		

		try{
		    String file_name="C:\\Users\\simon\\Desktop\\generation\\test.pdf";
		    Document document=new Document();
		    PdfWriter.getInstance(document, new FileOutputStream(file_name));
	        document.open();
	        Paragraph linebreak =new Paragraph ("-------------------------------------------------------------------");
	        Paragraph space =new Paragraph ("   ");
	        
	        Paragraph Surname=new Paragraph ("Name:"  +member.getSurname()+member.getOtherNames());
	        Paragraph PhoneNo=new Paragraph ("Phone Number:"   +member.getPhoneNo());
	        Paragraph idNo=new Paragraph ("ID Number:"    +member.getIdNo());
	        Paragraph memberNo=new Paragraph ("Member Number:"     +member.getMemberNo());
	        Paragraph payrollno= new Paragraph("Payroll Number:"   +member.getPayrollNo());
	        document.add(Surname);
	        document.add(payrollno);
	        document.add(PhoneNo);
	        document.add(idNo);
	        document.add(memberNo);
	        document.add(space);
	        document.add(space);
	        document.add(space);
	        
	       
			//add table
	        
	        
	        PdfPTable table= new PdfPTable(5);//three columns
	        PdfPCell c1 =new PdfPCell (new Phrase("Date"));
	        c1.setColspan(1);
	        c1.setBackgroundColor(BaseColor.MAGENTA);
	        c1.setFixedHeight((float) 0.1);
	        table.addCell(c1);
	        PdfPCell c11 = new PdfPCell(new Phrase("Activity"));
	        c11.setBackgroundColor(BaseColor.BLUE);
	        c11.setColspan(1);
	        table.addCell(c11);
	        PdfPCell c12=new PdfPCell(new Phrase("DocumentNo"));
	        c12.setBackgroundColor(BaseColor.BLUE);
	        c12.setColspan(1);
	        table.addCell(c12);
	        
//	        PdfPCell c14=new PdfPCell(new Phrase("Description"));
//	        c14.setBackgroundColor(BaseColor.BLUE);
//          c14.setColspan(1);
//	        table.addCell(c14);
	        PdfPCell c13=new PdfPCell(new Phrase("PaidIn"));
	        c13.setBackgroundColor(BaseColor.BLUE);
	        c13.setColspan(1);
	        table.addCell(c13);
	        PdfPCell  c15=new PdfPCell(new Phrase("RunningBalance"));
	        c15.setBackgroundColor(BaseColor.BLUE);
	        c15.setColspan(1);
	        table.addCell(c15);
	        
	        table.setHeaderRows(1);
	        table.setWidthPercentage(102);
	        
	        for (int i = 0; i < memberSts.size(); i++) {
	        	MemberStatement statement = memberSts.get(i);
	        	table.addCell(statement.getDate());
	 	        table.addCell(statement.getActivity());
	 	        table.addCell(statement.getDocumentNo());
	 	       //table.addCell(statement.getReference_code());
	 	        table.addCell(statement.getPaidIn());
	 	        table.addCell(statement.getRunningBalance());	
			}
	        
	        document.add(table);
	        document.add(space);
	        
	        document.add(linebreak);
	        document.add(space);
	        
	        //Section  2
	      //add table
	        PdfPTable table2= new PdfPTable(6);//three columns
	        PdfPCell c21 =new PdfPCell (new Phrase("Date"));
	        table.addCell(c21);
	       
	        
	        table.setHeaderRows(1);
	        
	        for (int i = 0; i < memberLoans.size(); i++) { 
	        	MemberLoan statement = memberLoans.get(i);
	        	table2.addCell(statement.getDate());
	 	        	
			}
	        
	        document.add(table2);
 
	     

	        document.close();
	        System.out.println("finished");
	        return true;
		}

		catch (Exception e)
	    {
	        e.printStackTrace();
	        return false;
	    }

	}

}
