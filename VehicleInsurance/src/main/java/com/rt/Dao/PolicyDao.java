package com.rt.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Policy;
import com.rt.Entity.Vehicle;

@Repository

public class PolicyDao {

	@Autowired
	private JdbcTemplate template;

	public boolean insertPolicy(Policy policy) {

		String sql = "INSERT INTO policytable " + "(vehicleId, policyType, startDate, endDate, premium, userId) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		int result = template.update(sql, policy.getVehicleId(), policy.getPolicyType(), policy.getStartDate(),
				policy.getEndDate(), policy.getPremium(), policy.getUserId());

		return result > 0;
	}

	public List<Policy> getPoliciesByUser(Integer userId) {

		String sql = "SELECT p.*, v.vehicleName, v.model, v.vehicleNumber " + "FROM policytable p "
				+ "JOIN vehicletable v ON p.vehicleId = v.vehicleId " + "WHERE p.userId = ? AND p.isActive = 1";

		return template.query(sql, new RowMapper<Policy>() {

			@Override
			public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {

				Policy p = new Policy();

				p.setPolicyId(rs.getInt("policyId"));
				p.setVehicleId(rs.getInt("vehicleId"));
				p.setPolicyType(rs.getString("policyType"));
				p.setStartDate(rs.getString("startDate"));
				p.setEndDate(rs.getString("endDate"));
				p.setPremium(rs.getDouble("premium"));
				p.setUserId(rs.getInt("userId"));

				Vehicle v = new Vehicle();
				v.setVehicleId(rs.getInt("vehicleId"));
				v.setVehicleName(rs.getString("vehicleName"));
				v.setModel(rs.getString("model"));
				v.setVehicleNumber(rs.getString("vehicleNumber"));

				p.setVehicle(v);

				return p;
			}
		}, userId);
	}

	public Policy getPolicyById(int policyId) {
		String sql = "SELECT * FROM policytable WHERE policyId = ? AND isActive = 1";

		try {
			return template.queryForObject(sql, new Object[] { policyId }, new RowMapper<Policy>() {
				@Override
				public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {
					Policy p = new Policy();
					p.setPolicyId(rs.getInt("policyId"));
					p.setVehicleId(rs.getInt("vehicleId"));
					p.setPolicyType(rs.getString("policyType"));
					p.setStartDate(rs.getString("startDate"));
					p.setEndDate(rs.getString("endDate"));
					p.setPremium(rs.getDouble("premium"));
					p.setUserId(rs.getInt("userId"));
					return p;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public Object updatePolicy(Policy policy) {
		String sql = "UPDATE policytable SET vehicleId=?, policyType=?, startDate=?, endDate=?, premium=? "
				+ "WHERE policyId=? AND userId=? AND isActive=1";

		return template.update(sql, policy.getVehicleId(), policy.getPolicyType(), policy.getStartDate(),
				policy.getEndDate(), policy.getPremium(), policy.getPolicyId(), policy.getUserId()) > 0;
	}

	public Object deletePolicy(int policyId) {
		String sql = "UPDATE policytable SET isActive = 0 WHERE policyId = ?";

		return template.update(sql, policyId) > 0;
	}
}
