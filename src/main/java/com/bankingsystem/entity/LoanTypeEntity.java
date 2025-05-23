package com.bankingsystem.entity;

public class LoanTypeEntity {

    private int loanTypeID;
    private String typeName;
    private double interestRate;

    public int getLoanTypeID() {
        return loanTypeID;
    }

    public void setLoanTypeID(int loanTypeID) {
        this.loanTypeID = loanTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
