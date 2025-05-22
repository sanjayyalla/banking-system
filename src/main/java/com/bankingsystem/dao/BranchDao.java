package com.bankingsystem.dao;

import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.form.BranchForm;

import java.sql.SQLException;
import java.util.List;

public interface BranchDao {
    public boolean saveBranch(BranchEntity entity) throws SQLException;
    public boolean updateBranch(BranchEntity entity);
    public String getBranch(int branchId);
    public List<BranchEntity> getAllBranches() throws SQLException;
    public boolean removeBranch(int branchId);
}
