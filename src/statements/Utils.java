package statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connector.DBConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Statement;

import statements.Member;
import statements.Utils;

import java.sql.DriverManager;




public class Utils {
	
	static DBConnection obJBDCConnection =new DBConnection();
	
	public static Member fetchMember(String payrollNo) throws SQLException {
		try {
			Connection connMain=obJBDCConnection.getConnection("main");
			
			  Member member = new Member();
			 PreparedStatement ps=null;
	           ResultSet rs=null;

	           String query="select * from membersmasterfile where PayrollNo = "+payrollNo;
	           ps=connMain.prepareStatement(query);
	           rs=ps.executeQuery();
	           while (rs.next()){
	               
	        	 
	        	   member.setIdNo(rs.getString("IDNumber"));
	        	   member.setMemberNo(rs.getString("MemberNo"));
	        	   member.setPayrollNo(rs.getString("PayrollNo"));
	        	   member.setPhoneNo(rs.getString("PhoneNo"));
	        	   member.setSurname(rs.getString("Surname"));
	        	   member.setOtherNames(rs.getString("OtherNames"));
	        	   
	        	   
	           }
	           rs.close();
	           ps.close();
	           connMain.close();
	  
			
			System.out.println(member);
			
			
			
	        
			return member;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public static boolean generatePdf(Member member) {
		  String file_name="C:\\\\Users\\\\simon\\\\Desktop\\\\generation\\\\test.pdf";
		 Document document =new Document();
         try {
			PdfWriter.getInstance(document,new FileOutputStream(file_name));
		
           document.open();
           
	      // connectionbosa000010=obJBDCConnection.getConnection("bosa000010");
         Connection connectionbosa000010=obJBDCConnection.getConnection("bosa000010");
         PreparedStatement ps=null;
         ResultSet rs=null;

         String query="select * from mem_statement01 where Payroll_No ="+member.getPayrollNo();
         ps=connectionbosa000010.prepareStatement(query);
         rs=ps.executeQuery();
         List<MemberStatement>  memberStatements = new ArrayList<MemberStatement>();
         while (rs.next()){
        	 MemberStatement memberStatement = new MemberStatement();
        	 memberStatement.setActivity(rs.getString("ActivityRef")); 
        	 memberStatement.setDate(rs.getString("Document_Date"));
        	 memberStatement.setDocumentNo(rs.getString("Document_number"));
        	 memberStatement.setPaidIn(rs.getString("Share_Cr"));
        	 memberStatement.setRunningBalance(rs.getString("Loan_Bal"));
        	 memberStatement.setReference_code(rs.getString("Reference_Name"));
        	 
//		       Paragraph para=new Paragraph(rs.getString("Employer_Code")+""+rs.getString("Payroll_No")+rs.getString("Scheme")+rs.getString("Reference_code")+rs.getString("Document_number")
//		      // +rs.getString("Document_Date")+rs.getString("ActivityRef")+rs.getString("Reference_name")+rs.getString("Period")+rs.getString("Trans_Date")+rs.getString("Share_Dr")
//		      // +rs.getString("Share_Cr")+rs.getString("Share_Bal")+rs.getString("Loan_Dr")+rs.getString("Loan_Cr")+rs.getString("Loan_Bal")+rs.getString("Interest_Dr")+rs.getString("Interest_Cr")
//		       //+rs.getString("Insurance_Dr")+rs.getString("Insurance_cr")+rs.getString("Penalty_Dr")+rs.getString("Penalty_Cr")+rs.getString("EntryTime")
//		       );
//		       document.add(para);
//		       document.add(new Paragraph(""));
        	 memberStatements.add(memberStatement);

         }
         
         rs.close();
         ps.close();
         connectionbosa000010.close();
         //Another List
         List<MemberLoan> memberLoans = fetchMemberLoans(member);
         
         //Query Source of Data
         
       
    	 
    	 if(WritePdf.writePdf(memberStatements , member, memberLoans)) {
    		 System.out.println("Statement Generated Successfully");
    	 };
    	   
         
         return true;
         
           } catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			return false;
   		}
	}
//	  public  static  List<Scheme> fetchSchemes(){
//		  
//		    Connection mainConn = null;
//			try {
//				mainConn = obJBDCConnection.getConnection("main");
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			 
//	        try{
//	            
//	            java.sql.Statement statement = mainConn.createStatement();
//	            // Result set get the result of the SQL query
//	            ResultSet resultSet = statement
//	                    .executeQuery("select * from savingschemes");
//
//	            List<Scheme> schemes = new ArrayList<>();
//	            while (resultSet.next()) {
//	                Scheme scheme = new Scheme();
//	                scheme.setSchemeCode(resultSet.getString("SchemeCode"));
//	                scheme.setSchemeName(resultSet.getString("SchemeName"));
//	                schemes.add(scheme);
//	            }
//	            resultSet.close();
//	            statement.close();
//	            return   schemes ;
//
//	        }catch (Exception e){
//	            e.printStackTrace();
//	            return  new ArrayList<>();
//	        }
//
//	    }
	
	
	
	public static  List<MemberLoan> fetchMemberLoans(Member member){
		
         try {
        	 Connection connectionbosa000010=obJBDCConnection.getConnection("bosa000010");
             PreparedStatement ps=null;
             ResultSet rs=null;

             String query="select * from mem_statement01 where Payroll_No ="+member.getPayrollNo();
			ps=connectionbosa000010.prepareStatement(query);
			
			rs=ps.executeQuery();
	         List<MemberLoan>   loans = new ArrayList<MemberLoan>();
	         while (rs.next()){
	        	 MemberLoan memberLoan = new MemberLoan();
	        	 memberLoan.setDate(rs.getString("Document_Date"));
	        	
	        	 
//			       Paragraph para=new Paragraph(rs.getString("Employer_Code")+""+rs.getString("Payroll_No")+rs.getString("Scheme")+rs.getString("Reference_code")+rs.getString("Document_number")
//			      // +rs.getString("Document_Date")+rs.getString("ActivityRef")+rs.getString("Reference_name")+rs.getString("Period")+rs.getString("Trans_Date")+rs.getString("Share_Dr")
//			      // +rs.getString("Share_Cr")+rs.getString("Share_Bal")+rs.getString("Loan_Dr")+rs.getString("Loan_Cr")+rs.getString("Loan_Bal")+rs.getString("Interest_Dr")+rs.getString("Interest_Cr")
//			       //+rs.getString("Insurance_Dr")+rs.getString("Insurance_cr")+rs.getString("Penalty_Dr")+rs.getString("Penalty_Cr")+rs.getString("EntryTime")
//			       );
//			       document.add(para);
//			       document.add(new Paragraph(""));
	        	 loans.add(memberLoan);

	         }
	         
	         rs.close();
	         ps.close();
	         connectionbosa000010.close();
			return loans;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<MemberLoan>();
		}
         
         //Another List
	}
	
}
