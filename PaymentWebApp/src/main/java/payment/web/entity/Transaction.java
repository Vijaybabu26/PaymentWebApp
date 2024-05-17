package payment.web.entity;

import java.sql.Date;

public class Transaction {
	String TxnId;
	Date TxnDate;
	String SourceType;
	String DestType;
	String TxnStatus;
	int UserId;
	double TxnAmount;
	public double getTxnAmount() {
		return TxnAmount;
	}
	public void setTxnAmount(double txnAmount) {
		TxnAmount = txnAmount;
	}
	public String getTxnId() {
		return TxnId;
	}
	public void setTxnId(String txnId) {
		TxnId = txnId;
	}
	public Date getTxnDate() {
		return TxnDate;
	}
	public void setTxnDate(Date txnDate) {
		TxnDate = txnDate;
	}
	public String getSourceType() {
		return SourceType;
	}
	public void setSourceType(String sourceType) {
		SourceType = sourceType;
	}
	public String getDestType() {
		return DestType;
	}
	public void setDestType(String destType) {
		DestType = destType;
	}
	public String getTxnStatus() {
		return TxnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		TxnStatus = txnStatus;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	
}
