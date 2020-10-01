package Connector;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;
import java.util.Scanner; 


import statements.Member;
import statements.MemberStatement;
import statements.Scheme;
import statements.SchemeTransactions;
import statements.Utils;
import java.sql.DriverManager;


public class GeneratePdf_dynamic{

    public static void main (String[]args){
       try{
       
           DBConnection obJBDCConnection =new DBConnection();
           Connection connectionbosa000010=obJBDCConnection.getConnection("bosa000010");
       	  
           
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object 
        System.out.println("Enter payroll number ");

        String Payroll = myObj.nextLine();  // Read user input
       
  
           if(Utils.generatePdf( Utils.fetchMember(Payroll))) {
        	   System.out.println("Document Generated !!!! ");
           }
           
           
    
        System.out.println("finished");


       }
             catch (Exception e){
           System.err.println(e);

             }}

}