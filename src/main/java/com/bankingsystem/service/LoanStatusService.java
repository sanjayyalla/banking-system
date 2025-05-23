package com.bankingsystem.service;
import com.bankingsystem.form.LoanStatusForm;

import java.sql.SQLException;
import java.util.List;

public interface LoanStatusService {

     boolean addStatus(LoanStatusForm loanStatusForm) throws SQLException;

     boolean updateStatus(LoanStatusForm loanStatusForm) throws SQLException;

     String getStatus(String id) throws SQLException;

     List<LoanStatusForm> getAllStatus() throws SQLException;

     boolean deleteStatus(String id) throws SQLException;
}
