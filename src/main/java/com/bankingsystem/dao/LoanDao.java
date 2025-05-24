package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.form.LoanRequestForm;
import com.bankingsystem.form.LoanResponseForm;

import java.sql.SQLException;

public interface LoanDao {
    String addLoan(LoanEntity entity) throws SQLException;

    boolean updateLoan(LoanEntity entity);

    boolean removeLoan(int loanId);

    LoanResponseForm getLoanDetailById(LoanRequestForm request) throws Exception;
}
