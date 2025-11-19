package com.rt.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Customer;
import com.rt.Entity.Signup;

@Repository
public class CustomerDao {

	@Autowired
	private JdbcTemplate template;

	public boolean addcustomer(Customer customer) {
		try {
			Object[] args = { customer.getCustomerName(), customer.getPhoneNo(), customer.getEmailAddress(),
					customer.getAddress(), customer.getUserId().getSignupid() };

			int result = template.update(
					"INSERT INTO customertable(customerName, phoneNo, emailAddress, address,userId) VALUES ( ?,?, ?, ?, ?)",
					args);

			return result == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Customer> getAllCustomers() {
		try {
			String sql = "SELECT * FROM customertable WHERE isactive = 1";

			return template.query(sql, new RowMapper<Customer>() {
				@Override
				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer c = new Customer();
					c.setCustomerId(rs.getString("customerId"));
					c.setCustomerName(rs.getString("customerName"));
					c.setPhoneNo(rs.getString("phoneNo"));
					c.setEmailAddress(rs.getString("emailAddress"));
					c.setAddress(rs.getString("address"));
					c.setisactive(rs.getInt("isactive"));

					Signup s = new Signup();
					s.setSignupid(rs.getInt("userId"));
					c.setUserId(s);
					return c;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Customer getCustomerById(int customerId) {
		String sql = "SELECT * FROM customertable  WHERE customerId = ?";
		try {
			return template.queryForObject(sql, new Object[] { customerId }, new RowMapper<Customer>() {
				@Override
				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					Customer c = new Customer();
					c.setCustomerId(rs.getString("customerId"));
					c.setCustomerName(rs.getString("customerName"));
					c.setPhoneNo(rs.getString("phoneNo"));
					c.setEmailAddress(rs.getString("emailAddress"));
					c.setAddress(rs.getString("address"));
					return c;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modifyCustomer(Customer customer) {
		String sql = "UPDATE customertable  SET customerName=?, phoneNo=?, emailAddress=?, address=? WHERE customerId=?";
		int result = template.update(sql, customer.getCustomerName(), customer.getPhoneNo(), customer.getEmailAddress(),
				customer.getAddress(), customer.getCustomerId());
		return result > 0;
	}

	public boolean softDeleteCustomer(int customerId) {
		try {
			String sql = "UPDATE customertable SET isactive = 0 WHERE customerId = ?";
			int result = template.update(sql, customerId);
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Customer> getAllCustomer(Integer userId) {
		String sql = "SELECT * FROM customertable WHERE isactive = 1 AND userId = ?";

		return template.query(sql, new Object[] { userId }, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Customer c = new Customer();
				c.setCustomerId(rs.getString("customerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setPhoneNo(rs.getString("phoneNo"));
				c.setEmailAddress(rs.getString("emailAddress"));
				c.setAddress(rs.getString("address"));
				c.setisactive(rs.getInt("isactive"));

				Signup s = new Signup();
				s.setSignupid(rs.getInt("userId"));
				c.setUserId(s);

				return c;
			}
		});
	}
}
