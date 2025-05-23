package com.bankingsystem.dao.impl;
import com.bankingsystem.dao.CustomerDao;
import com.bankingsystem.entity.CustomerEntity;
import com.bankingsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean saveCustomer(CustomerEntity entity) throws SQLException {
        String query = "Insert into Customer(name,email,phone,address,dob) VALUES (?,?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getEmail());
        statement.setString(3, entity.getPhone());
        statement.setString(4, entity.getAddress());
        statement.setDate(5,entity.getDob());
        int isRowInserted = statement.executeUpdate();
        return isRowInserted == 1;
    }

    @Override
    public boolean updateCustomer(CustomerEntity entity) {
        String query = "UPDATE Customer SET name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE cust_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            System.out.println("I am here");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPhone());
            statement.setString(4, entity.getAddress());
            statement.setDate(5, entity.getDob());
            statement.setInt(6,entity.getCustId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getCustomer(int custID) {
        String query = "SELECT * FROM Customer WHERE cust_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, custID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("cust_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String dob = String.valueOf(rs.getDate("dob"));


                return String.format("Branch{cust_id=%d, name='%s', email='%s', phone='%s', address='%s', dob='%s'}",
                        id, name, email, phone, address,dob);
            } else {
                return "Branch not found with ID: " + custID;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In production, use a logger
            return "Error retrieving branch: " + e.getMessage();
        }
    }

    @Override
    public List<CustomerEntity> getCustomers() throws SQLException {
        List<CustomerEntity> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while(rs.next())
        {
            CustomerEntity entity = new CustomerEntity();
            entity.setCustId(rs.getInt("cust_id"));
            entity.setName(rs.getString("name"));
            entity.setEmail(rs.getString("email"));
            entity.setPhone(rs.getString("phone"));
            entity.setAddress(rs.getString("address"));
            entity.setDob(rs.getDate("dob"));
            customers.add(entity);

        }
        return customers;
    }

    @Override
    public boolean removeCustomer(int custID) {
        String query = "DELETE FROM Customer WHERE cust_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, custID);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted == 1;

        } catch (SQLException e) {
            System.err.println("Error deleting loan status: " + e.getMessage());
            return false;
        }
    }
}
