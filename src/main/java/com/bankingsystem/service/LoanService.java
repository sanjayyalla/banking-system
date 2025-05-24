package com.bankingsystem.service;

import com.bankingsystem.form.LoanForm;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;

import java.sql.SQLException;
import java.text.ParseException;

public interface LoanService {

    String addLoan(LoanForm form) throws ParseException, SQLException;
    boolean updateLoan(LoanForm form);
    boolean deleteLoan(String loanId);
    LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception;
}


