package com.bankingsystem.service;

import com.bankingsystem.entity.LoanTypeEntity;
import com.bankingsystem.form.LoanTypeForm;

import java.sql.SQLException;
import java.util.List;

public interface LoanTypeService {
    boolean addLoanType(LoanTypeForm form) throws SQLException;

    boolean updateLoanType(LoanTypeForm form) throws SQLException;

    String getLoanType(String loanId);

    List<LoanTypeForm> getAllLoneTypes() throws SQLException;

    boolean deleteLoanType(String loanId);
}
