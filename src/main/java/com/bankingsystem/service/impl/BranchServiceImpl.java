package com.bankingsystem.service.impl;

import com.bankingsystem.dao.BranchDao;
import com.bankingsystem.dao.impl.BranchDaoImpl;
import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.form.BranchForm;
import com.bankingsystem.service.BranchService;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<BranchForm> getAllBranches() throws SQLException {
        List<BranchEntity> entities = dao.getAllBranches(); // Fetch from DAO
        List<BranchForm> forms = new ArrayList<>();

        for (BranchEntity entity : entities) {
            BranchForm form = new BranchForm();
            form.setBranchId(String.valueOf(entity.getBranchId())); // Assuming branchId is int in entity
            form.setName(entity.getName());
            form.setAddress(entity.getAddress());
            form.setCity(entity.getCity());
            form.setState(entity.getState());
            forms.add(form);
        }

        return forms;
    }

    @Override
    public boolean deleteBranch(String branchId) {
        return dao.removeBranch(Integer.parseInt(branchId));
    }
}
