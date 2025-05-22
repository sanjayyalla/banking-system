package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanTypeEntity;

import java.sql.SQLException;
import java.util.List;

public interface LoanTypeDao {
    boolean addLoanType(LoanTypeEntity entity) throws SQLException;

    boolean updateLoanType(LoanTypeEntity entit) throws SQLException;

    String getLoanType(int loanId);

    List<LoanTypeEntity> getAllLoneTypes() throws SQLException;

    boolean removeLoanType(int loanId);
}
