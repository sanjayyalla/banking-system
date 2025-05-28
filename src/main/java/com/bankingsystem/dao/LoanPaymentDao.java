package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanPaymentEntity;

import java.sql.SQLException;
import java.util.List;

public interface LoanPaymentDao {
    String addLoanPayment(LoanPaymentEntity entity);

    int getLoanPaymentCount(int loanId) throws SQLException;

    boolean saveAllLoanPaymentDetails();

    List<LoanPaymentEntity> getAllLoanPayments();

    boolean deleteLoanPayment(int i);

    boolean saveLoanPaymentTransactionHistory(int i);
}
