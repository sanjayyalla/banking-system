package com.bankingsystem.service;

import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.form.LoanStatusForm;

import java.sql.SQLException;
import java.util.List;

public interface LoanStatusService {
    public boolean addStatus(LoanStatusForm loanStatusForm) throws SQLException;
    public boolean updateStatus(LoanStatusForm loanStatusForm) throws SQLException;
    public String getStatus(String id) throws SQLException;
    public List<LoanStatusForm> getAllStatus() throws SQLException;
    public boolean deleteStatus(String id) throws SQLException;
}
