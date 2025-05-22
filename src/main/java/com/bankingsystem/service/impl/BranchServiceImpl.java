package com.bankingsystem.service.impl;

import com.bankingsystem.dao.BranchDao;
import com.bankingsystem.dao.impl.BranchDaoImpl;
import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.form.BranchForm;
import com.bankingsystem.service.BranchService;

import java.sql.SQLException;
import java.util.List;

public class BranchServiceImpl implements BranchService {

    BranchDao dao = new BranchDaoImpl();
    @Override
    public boolean addBranch(BranchForm form) throws SQLException {
        BranchEntity entity = new BranchEntity();
        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setCity(form.getCity());
        entity.setState(form.getState());
        return dao.saveBranch(entity);
    }

    @Override
    public boolean updateBranch(BranchForm form) {
        BranchEntity entity = new BranchEntity();
        entity.setBranchId(Integer.parseInt(form.getBranchId()));
        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setCity(form.getCity());
        entity.setState(form.getState());
        return dao.updateBranch(entity);
    }

    @Override
    public String getBranch(String branchId) {
        return dao.getBranch(Integer.parseInt(branchId));
    }

    @Override
    public List<BranchEntity> getAllBranches() throws SQLException {
        return dao.getAllBranches();
    }

    @Override
    public boolean deleteBranch(String branchId) {
        return dao.removeBranch(Integer.parseInt(branchId));
    }
}
