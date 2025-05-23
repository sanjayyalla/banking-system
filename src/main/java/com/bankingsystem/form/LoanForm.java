package com.bankingsystem.form;

public class LoanForm {
    private String loanId;
    private String customerId;
    private String loanTypeId;
    private String branchId;
    private String statusId;
    private String pricipalAmount;
    private String interestRate;
    private String termMonths;
    private String startDate;
    private String endDate;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getPricipalAmount() {
        return pricipalAmount;
    }

    public void setPricipalAmount(String pricipalAmount) {
        this.pricipalAmount = pricipalAmount;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(String termMonths) {
        this.termMonths = termMonths;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
