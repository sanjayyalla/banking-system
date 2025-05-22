package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanTypeDao;
import com.bankingsystem.entity.CustomerEntity;
import com.bankingsystem.entity.LoanTypeEntity;
import com.bankingsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanTypeDaoImpl implements LoanTypeDao {

    @Override
    public boolean addLoanType(LoanTypeEntity entity) throws SQLException {
        String query = "Insert into Loan_Type(type_name,interest_rate) VALUES (?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, entity.getTypeName());
        statement.setDouble(2,entity.getInterestRate());
        int isRowInserted = statement.executeUpdate();
        return isRowInserted == 1;
    }

    @Override
    public boolean updateLoanType(LoanTypeEntity entity) throws SQLException {
        String query = "UPDATE Loan_Type SET type_name = ?,interest_rate = ? WHERE loan_type_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, entity.getTypeName());
        statement.setDouble(2, entity.getInterestRate());
        statement.setInt(3,entity.getLoanTypeID());
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated == 1;
    }

    @Override
    public String getLoanType(int loanId) {
        String query = "SELECT * FROM Loan_Type WHERE loan_type_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, loanId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int loanTypeId = rs.getInt("loan_type_id");
                String typeName = rs.getString("type_name");
                double interestRate = rs.getDouble("interest_rate");
                return String.format("Loan Type{Loan type id =%d, Type name='%s', interest rate='%.2f'}",
                        loanTypeId,typeName,interestRate);
            } else {
                return "Loan Type not found with ID: " + loanId;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In production, use a logger
            return "Error retrieving branch: " + e.getMessage();
        }
    }

    @Override
    public List<LoanTypeEntity> getAllLoneTypes() throws SQLException {
        List<LoanTypeEntity> loanTypes = new ArrayList<>();
        String query = "SELECT * FROM Loan_Type";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while(rs.next())
        {
            LoanTypeEntity entity = new LoanTypeEntity();
            entity.setLoanTypeID(rs.getInt("loan_type_id"));
            entity.setTypeName(rs.getString("type_name"));
            entity.setInterestRate(rs.getDouble("interest_rate"));

            loanTypes.add(entity);

        }
        return loanTypes;
    }

    @Override
    public boolean removeLoanType(int loanId) {
        String query = "DELETE FROM Customer WHERE loan_type_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, loanId);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan type: " + e.getMessage());
            return false;
        }
    }
}
