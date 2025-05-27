package com.bankingsystem.controller;

import com.bankingsystem.form.LoanPaymentForm;
import com.bankingsystem.service.LoanPaymentService;
import com.bankingsystem.service.impl.LoanPaymentServiceImpl;

import java.util.List;

public class LoanPaymentController {
    LoanPaymentService service = new LoanPaymentServiceImpl();
    public String addLoanPayment(int loanId) throws Exception {
        if(loanId!=0)
        {
            return service.addLoanPayment(loanId);
        }
        return "False Loan ID";
    }

    public boolean saveAllLoanPaymentDetails()
    {
        return service.saveAllLoanPaymentDetails();
    }

    public List<LoanPaymentForm> getAllLoanPayments()
    {
        return service.getAllLoanPayments();
    }
}
