package model;

import java.sql.Date;

public class Transaction {
    public int transactionid;
    public int bookid;
    public int patronid;
    public Date issuedate;
    public Date duedate;
    public Date returndate;

    public Transaction(int transactionid, int bookid, int patronid, Date issudedate, Date duedate, Date returndate) {
        this.transactionid = transactionid;
        this.bookid = bookid;
        this.patronid = patronid;
        this.issuedate = issudedate;
        this.duedate = duedate;
        this.returndate = returndate;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getPatronid() {
        return patronid;
    }

    public void setPatronid(int patronid) {
        this.patronid = patronid;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issudedate) {
        this.issuedate = issudedate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }
}
