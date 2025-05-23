package com.bankingsystem.service;

import com.bankingsystem.form.LoanForm;

import java.sql.SQLException;
import java.text.ParseException;

public interface LoanService {

    String addLoan(LoanForm form) throws ParseException, SQLException;
    boolean updateLoan(LoanForm form);
    boolean deleteLoan(String loanId);
}


