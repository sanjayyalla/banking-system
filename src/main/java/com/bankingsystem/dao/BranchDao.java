package com.bankingsystem.dao;
import com.bankingsystem.entity.BranchEntity;
import java.sql.SQLException;
import java.util.List;

public interface BranchDao {
     boolean saveBranch(BranchEntity entity) throws SQLException;

     boolean updateBranch(BranchEntity entity);

     String getBranch(int branchId);

     List<BranchEntity> getAllBranches() throws SQLException;

     boolean removeBranch(int branchId);
}
