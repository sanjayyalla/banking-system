package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanStatusEntity;

import java.sql.SQLException;
import java.util.List;

public interface LoanStatusDao {
    public boolean saveStatus(LoanStatusEntity loanStatusEntity) throws SQLException;
    public boolean updateStatus(LoanStatusEntity loanStatusEntity) throws SQLException;
    public String getStatus(int id) throws SQLException;
    public List<LoanStatusEntity> getAllStatus() throws SQLException;
    public boolean removeStatus(int id) throws SQLException;

}
