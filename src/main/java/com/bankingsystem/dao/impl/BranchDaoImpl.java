package com.bankingsystem.dao.impl;
import com.bankingsystem.dao.BranchDao;
import com.bankingsystem.entity.BranchEntity;
import com.bankingsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements BranchDao {
    @Override
    public boolean saveBranch(BranchEntity entity) throws SQLException {

        String query = "Insert into Branch(name,address,city,state) VALUES (?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getAddress());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getState());
        int isRowInserted = statement.executeUpdate();
        return isRowInserted == 1;
    }

    @Override
    public boolean updateBranch(BranchEntity entity) {
        String query = "UPDATE Branch SET name = ?, address = ?, city = ?, state = ? WHERE branch_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getCity());
            statement.setString(4, entity.getState());
            statement.setInt(5, entity.getBranchId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public String getBranch(int branchId) {
        String query = "SELECT * FROM Branch WHERE branch_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, branchId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("branch_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");

                return String.format("Branch{\n\tid=%d, \n\tname='%s', \n\taddress='%s', \n\tcity='%s', \n\tstate='%s'\n}",
                        id, name, address, city, state);
            } else {
                return "Branch not found with ID: " + branchId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error retrieving branch: " + e.getMessage();
        }
    }

    @Override
    public List<BranchEntity> getAllBranches() throws SQLException {
        List<BranchEntity> branches = new ArrayList<>();
        String query = "SELECT * FROM branch";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            BranchEntity entity = new BranchEntity();
            entity.setBranchId(rs.getInt("branch_id"));
            entity.setName(rs.getString("name"));
            entity.setCity(rs.getString("city"));
            entity.setAddress(rs.getString("address"));
            entity.setState(rs.getString("state"));
            branches.add(entity);

        }
        return branches;
    }

    @Override
    public boolean removeBranch(int branchId) {
        String query = "DELETE FROM Branch WHERE branch_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, branchId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan status: " + e.getMessage());
            return false;
        }
    }
}
