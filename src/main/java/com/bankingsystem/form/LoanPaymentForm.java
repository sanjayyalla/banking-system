package com.bankingsystem.form;

public class LoanPaymentForm {

    String loanId;
    String paymentDate;
    String amountPaid;
    String principalComponent;
    String interestComponent;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPricipalComponent() {
        return principalComponent;
    }

    public void setPricipalComponent(String pricipalComponent) {
        this.principalComponent = pricipalComponent;
    }

    public String getInterestComponent() {
        return interestComponent;
    }

    public void setInterestComponent(String interestComponent) {
        this.interestComponent = interestComponent;
    }
}
