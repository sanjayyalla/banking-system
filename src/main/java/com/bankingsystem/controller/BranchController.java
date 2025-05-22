package com.bankingsystem.controller;

import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.form.BranchForm;
import com.bankingsystem.service.BranchService;
import com.bankingsystem.service.impl.BranchServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class BranchController {

    BranchService service = new BranchServiceImpl();
    public boolean addBranch(BranchForm form) throws SQLException {
        if(form.getBranchId()!=null && form.getName()!=null && form.getCity()!=null && form.getAddress()!=null && form.getState()!=null)
        {
            return service.addBranch(form);
        }
        return false;
    }
    public boolean updateBranch(BranchForm form)
    {
        if(form.getBranchId()!=null && form.getName()!=null && form.getCity()!=null && form.getAddress()!=null && form.getState()!=null)
        {
            return service.updateBranch(form);
        }
        return false;

    }
    public String getBranch(String branchId)
    {
        return service.getBranch(branchId);
    }
    public List<BranchEntity> getAllBranches() throws SQLException {
        return service.getAllBranches();
    }
    public boolean deleteBranch(String branchId)
    {
        if(branchId!=null && !branchId.isEmpty())
        {
            return service.deleteBranch(branchId);
        }
        return false;
    }
}
