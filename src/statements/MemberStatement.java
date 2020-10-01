package statements;

public class MemberStatement {
	
	private String date;
	private String activity;
	private String documentNo;
	private String paidIn;
	private String runningBalance;
	private String reference_code; 
	
	public String getReference_code() {
		return reference_code;
	}
	public void setReference_code(String reference_code) {
		this.reference_code = reference_code;
	} 
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getPaidIn() {
		return paidIn;
	}
	public void setPaidIn(String paidIn) {
		this.paidIn = paidIn;
	}
	public String getRunningBalance() {
		return runningBalance;
	}
	public void setRunningBalance(String runningBalance) {
		this.runningBalance = runningBalance;
	}
	@Override
	public String toString() {
		return "MemberStatement [date=" + date + ", activity=" + activity + ", documentNo=" + documentNo + ", paidIn="
				+ paidIn + ", runningBalance=" + runningBalance + ", reference_code=" + reference_code + "]";
	}
	
	
	

}
