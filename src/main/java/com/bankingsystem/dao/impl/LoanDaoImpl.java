package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanDao;
import com.bankingsystem.entity.LoanEntity;
import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanDaoImpl implements LoanDao {

    @Override
    public String addLoan(LoanEntity entity) throws SQLException {

            String query = "Insert into Loan(customer_id,loan_type_id,branch_id,status_id,principal_amount,interest_rate) VALUES (?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, entity.getCustomerId());
            statement.setInt(2,entity.getLoanTypeId());
            statement.setInt(3,entity.getBranchId());
            statement.setInt(4,entity.getStatusId());
            statement.setDouble(5,entity.getPrincipalAmount());
            statement.setDouble(6,entity.getInterestRate());

            int isRowInserted = statement.executeUpdate();
            if(isRowInserted==1)
            {
                return "Inserted";
            }
            return "Not inserted";
    }

    @Override
    public boolean updateLoan(LoanEntity entity) {
        String query = "UPDATE Loan SET loan_type_id = ?, branch_id = ?, status_id = ?, principal_amount = ?,interest_rate = ? WHERE loan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
//            System.out.println("I am here");
            statement.setInt(1, entity.getLoanTypeId());
            statement.setInt(2, entity.getBranchId());
            statement.setInt(3, entity.getStatusId());
            statement.setDouble(4, entity.getPrincipalAmount());
            statement.setDouble(5, entity.getInterestRate());
            statement.setInt(6,entity.getLoanId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeLoan(int loanId)
    {
        String query = "DELETE FROM Loan WHERE loan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, loanId);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
            return false;
        }
    }
}
