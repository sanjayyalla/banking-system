package com.bankingsystem.service;

import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.form.BranchForm;

import java.sql.SQLException;
import java.util.List;

public interface BranchService {
    public boolean addBranch(BranchForm form) throws SQLException;
    public boolean updateBranch(BranchForm form);
    public String getBranch(String branchId);
    public List<BranchForm> getAllBranches() throws SQLException;
    public boolean deleteBranch(String branchId);
}
