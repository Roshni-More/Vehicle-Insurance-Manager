package com.rt.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Customer;

@Repository
public class CustomerDao {

	@Autowired
	private JdbcTemplate template;

	public boolean addcustomer(Customer customer) {
		try {
			String sql = "INSERT INTO users(userName, phoneNo, emailAddress, address, adminId) "
					+ "VALUES (?, ?, ?, ?, ?)";

			int result = template.update(sql, customer.getUserName(), customer.getPhoneNo(), customer.getEmailAddress(),
					customer.getAddress(), customer.getAdminId());

			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Customer> getAllCustomers() {
		String sql = "SELECT * FROM users WHERE isactive = 1";

		return template.query(sql, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer c = new Customer();
				c.setUserId(rs.getInt("userId"));
				c.setUserName(rs.getString("userName"));
				c.setPhoneNo(rs.getString("phoneNo"));
				c.setEmailAddress(rs.getString("emailAddress"));
				c.setAddress(rs.getString("address"));
				c.setIsactive(rs.getInt("isactive"));
				c.setAdminId(rs.getInt("adminId"));
				return c;
			}
		});
	}

	public List<Customer> getAllCustomer(Integer userId) {
		String sql = "SELECT * FROM users WHERE isactive = 1 AND adminId = ?";

		return template.query(sql, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer c = new Customer();
				c.setUserId(rs.getInt("userId"));
				c.setUserName(rs.getString("userName"));
				c.setPhoneNo(rs.getString("phoneNo"));
				c.setEmailAddress(rs.getString("emailAddress"));
				c.setAddress(rs.getString("address"));
				c.setIsactive(rs.getInt("isactive"));
				c.setAdminId(rs.getInt("adminId"));
				return c;
			}
		}, userId);
	}

	public Customer getCustomerById(int userId) {
		String sql = "SELECT * FROM users WHERE userId = ?";

		return template.queryForObject(sql, new Object[] { userId }, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer c = new Customer();
				c.setUserId(rs.getInt("userId"));
				c.setUserName(rs.getString("userName"));
				c.setPhoneNo(rs.getString("phoneNo"));
				c.setEmailAddress(rs.getString("emailAddress"));
				c.setAddress(rs.getString("address"));
				c.setIsactive(rs.getInt("isactive"));
				c.setAdminId(rs.getInt("adminId"));
				return c;
			}
		});
	}

	public boolean modifyCustomer(Customer customer) {
		String sql = "UPDATE users SET userName=?, phoneNo=?, emailAddress=?, address=? WHERE userId=?";
		int result = template.update(sql, customer.getUserName(), customer.getPhoneNo(), customer.getEmailAddress(),
				customer.getAddress(), customer.getUserId());
		return result > 0;
	}

	public boolean softDeleteCustomer(int customerId) {
		String sql = "UPDATE users SET isactive = 0 WHERE userId = ?";
		return template.update(sql, customerId) > 0;
	}

	public List<Integer> getCustomerIdsByUserId(Integer userId) {
		String sql = "SELECT userId FROM users WHERE userId = ?";
		return template.queryForList(sql, Integer.class, userId);
	}
}
