package com.bankingsystem.dao.impl;

import com.bankingsystem.dao.LoanStatusDao;
import com.bankingsystem.entity.LoanStatusEntity;
import com.bankingsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanStatusDaoImpl implements LoanStatusDao {

    @Override
    public boolean saveStatus(LoanStatusEntity loanStatusEntity) throws SQLException {

        String query = "Insert into Loan_Status(status_name) VALUES (?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, loanStatusEntity.getStatusName());
        int isRowInserted = statement.executeUpdate();
        return isRowInserted == 1;
    }
    public boolean updateStatus(LoanStatusEntity entity) throws SQLException {

        String query = "UPDATE Loan_Status SET status_name = ? WHERE status_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, entity.getStatusName());
        statement.setInt(2, entity.getStatusId());
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated == 1;
    }
    public String getStatus(int id) throws SQLException {

        String query = "Select status_name from Loan_Status where status_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getString("status_name");
        } else {
            return null;
        }

    }
//    @Override
//    public List<String> getAllStatus() throws SQLException{
//        List<String> statuses = new ArrayList<>();
//        String query = "SELECT status_name FROM Loan_Status";
//        Connection conn = DBConnection.getConnection();
//        PreparedStatement statement = conn.prepareStatement(query);
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            statuses.add(rs.getString("status_name"));
//        }
//        return statuses;
//    }
    @Override
    public List<LoanStatusEntity> getAllStatus() throws SQLException {
        List<LoanStatusEntity> statuses = new ArrayList<>();
        String query = "SELECT status_id, status_name FROM Loan_Status";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                LoanStatusEntity entity = new LoanStatusEntity();
                entity.setStatusId(rs.getInt("status_id"));
                entity.setStatusName(rs.getString("status_name"));
                statuses.add(entity);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all statuses: " + e.getMessage());
            throw e;
        }

        return statuses;
    }

    public boolean removeStatus(int id)
    {
        String query = "DELETE FROM Loan_Status WHERE status_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan status: " + e.getMessage());
            return false;
        }
    }
}
