package com.bankingsystem.dao;

import com.bankingsystem.entity.LoanStatusEntity;

import java.sql.SQLException;
import java.util.List;

public interface LoanStatusDao {
     boolean saveStatus(LoanStatusEntity loanStatusEntity) throws SQLException;

     boolean updateStatus(LoanStatusEntity loanStatusEntity) throws SQLException;

     String getStatus(int id) throws SQLException;

     List<LoanStatusEntity> getAllStatus() throws SQLException;

     boolean removeStatus(int id) throws SQLException;

}
