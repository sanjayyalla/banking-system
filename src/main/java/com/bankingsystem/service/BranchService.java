package com.bankingsystem.service;
import com.bankingsystem.form.BranchForm;
import java.sql.SQLException;
import java.util.List;

public interface BranchService {
     boolean addBranch(BranchForm form) throws SQLException;
     boolean updateBranch(BranchForm form);
     String getBranch(String branchId);
     List<BranchForm> getAllBranches() throws SQLException;
     boolean deleteBranch(String branchId);
}
