package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;

import java.sql.SQLException;
import java.util.List;

public interface LoanDao {
    String addLoan(LoanEntity entity) throws SQLException;

    boolean updateLoan(LoanEntity entity) throws SQLException;

    boolean removeLoan(int loanId);

    LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception;
    List<LoanResponseForm> getAllLoanDetails();
    boolean processLoan(int loanId);
}
