package com.bankingsystem.entity;

import java.sql.Date;

public class LoanPaymentEntity {
    int loanId;
    Date paymentDate;
    double amountPaid;
    double principalComponent;
    double interestComponent;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getPrincipalComponent() {
        return principalComponent;
    }

    public void setPrincipalComponent(double pricipalComponent) {
        this.principalComponent = pricipalComponent;
    }

    public double getInterestComponent() {
        return interestComponent;
    }

    public void setInterestComponent(double interestComponent) {
        this.interestComponent = interestComponent;
    }
}
