package com.bankingsystem.controller;

import com.bankingsystem.form.LoanPaymentForm;
import com.bankingsystem.service.LoanPaymentService;
import com.bankingsystem.service.impl.LoanPaymentServiceImpl;

import java.util.List;

public class LoanPaymentController {
    LoanPaymentService service = new LoanPaymentServiceImpl();

    public String addLoanPayment(int loanId) throws Exception {
        if (loanId != 0) {
            return service.addLoanPayment(loanId);
        }
        return "False Loan ID";
    }

    public boolean saveAllLoanPaymentDetails() {
        return service.saveAllLoanPaymentDetails();
    }

    public List<LoanPaymentForm> getAllLoanPayments() {
        return service.getAllLoanPayments();
    }

    public boolean deleteLoanPayment(String paymentId) {
        if (paymentId != null && !paymentId.isEmpty()) {
            return service.deleteLoanPayment(paymentId);
        }
        return false;
    }

    public boolean saveLoanPaymentTransactionHistory(String loanId) {
        if (loanId != null && !loanId.isEmpty()) {
            return service.saveLoanPaymentTransactionHistory(loanId);
        }
        return false;
    }
}
