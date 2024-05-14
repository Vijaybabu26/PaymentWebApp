package payment.web.entity;

public class BankAccount {
	int UserId;
	String BankAcctNo;
	String BankAcctName;
	int BankAcctTypeId;
	String BankIFSCCode;
	String BankPin;
	double CurrBankBal;
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getBankAcctNo() {
		return BankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		BankAcctNo = bankAcctNo;
	}
	public String getBankAcctName() {
		return BankAcctName;
	}
	public void setBankAcctName(String bankAcctName) {
		BankAcctName = bankAcctName;
	}
	public int getBankAcctTypeId() {
		return BankAcctTypeId;
	}
	public void setBankAcctTypeId(int bankAcctTypeId) {
		BankAcctTypeId = bankAcctTypeId;
	}
	public String getBankIFSCCode() {
		return BankIFSCCode;
	}
	public void setBankIFSCCode(String bankIFSCCode) {
		BankIFSCCode = bankIFSCCode;
	}
	public String getBankPin() {
		return BankPin;
	}
	public void setBankPin(String i) {
		BankPin = i;
	}
	public double getCurrBankBal() {
		return CurrBankBal;
	}
	public void setCurrBankBal(double currBankBal) {
		CurrBankBal = currBankBal;
	}
	
}
