package com.bankingsystem.service;

import com.bankingsystem.form.LoanPaymentForm;

import java.util.List;

public interface LoanPaymentService {
    public String addLoanPayment(int loanId) throws Exception;

    boolean saveAllLoanPaymentDetails();

    List<LoanPaymentForm> getAllLoanPayments();

    boolean deleteLoanPayment(String paymentId);

    boolean saveLoanPaymentTransactionHistory(String loanId);
}
