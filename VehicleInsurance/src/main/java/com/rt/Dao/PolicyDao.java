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

	// INSERT
	public boolean insertPolicy(Policy policy) {

		if (policy.getStatus() == null || policy.getStatus().isEmpty()) {
			policy.setStatus("PENDING");
		}

		String sql = "INSERT INTO policyes "
				+ "(vehicleId, userId, policyType, startDate, premium, adminId, status, expiryDate, renewCount, renewalDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int result = template.update(sql, policy.getVehicleId(), policy.getUserId(), // 👈 IMPORTANT
				policy.getPolicyType(), policy.getStartDate(), policy.getPremium(), policy.getAdminId(),
				policy.getStatus(), policy.getExpiryDate(), policy.getRenewCount(), policy.getRenewalDate());

		return result > 0;
	}

	public int getUserIdByPolicyId(int policyId) {

		String sql = "SELECT userId FROM policyes WHERE policyId = ?";

		return template.queryForObject(sql, Integer.class, policyId);
	}

	// SHOW LIST
	public List<Policy> getPoliciesByUser(Integer userId) {

		String sql = "SELECT p.*, v.vehicleName, v.model, v.vehicleNumber " + "FROM policyes p "
				+ "JOIN vehicles v ON p.vehicleId = v.vehicleId " + "WHERE p.adminId = ? AND p.isActive = 1";

		return template.query(sql, new RowMapper<Policy>() {
			@Override
			public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {

				Policy p = new Policy();

				p.setPolicyId(rs.getInt("policyId"));
				p.setVehicleId(rs.getInt("vehicleId"));
				p.setPolicyType(rs.getString("policyType"));
				p.setStartDate(rs.getString("startDate"));
				p.setPremium(rs.getDouble("premium"));
				p.setAdminId(rs.getInt("adminId"));
				p.setStatus(rs.getString("status"));
				p.setExpiryDate(rs.getDate("expiryDate").toLocalDate());
				p.setRenewCount(rs.getInt("renewCount"));
				java.sql.Date renewDate = rs.getDate("renewalDate");
				if (renewDate != null) {
					p.setRenewalDate(renewDate.toLocalDate());
				}
				// vehicle
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

	// GET SINGLE
	public Policy getPolicyById(int policyId) {
		String sql = "SELECT * FROM policyes WHERE policyId = ? AND isActive = 1";

		try {
			return template.queryForObject(sql, new Object[] { policyId }, new RowMapper<Policy>() {
				@Override
				public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {
					Policy p = new Policy();
					p.setPolicyId(rs.getInt("policyId"));
					p.setVehicleId(rs.getInt("vehicleId"));
					p.setPolicyType(rs.getString("policyType"));
					p.setStartDate(rs.getString("startDate"));

					p.setPremium(rs.getDouble("premium"));
					p.setAdminId(rs.getInt("adminId"));
					p.setStatus(rs.getString("status"));
					p.setExpiryDate(rs.getDate("expiryDate").toLocalDate());
					p.setRenewCount(rs.getInt("renewCount"));
					java.sql.Date renewal = rs.getDate("renewalDate");
					if (renewal != null) {
						p.setRenewalDate(renewal.toLocalDate());
					}

					return p;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	// UPDATE POLICY
	public boolean updatePolicy(Policy policy) {

		String sql = "UPDATE policyes "
				+ "SET vehicleId=?, policyType=?, startDate=?, premium=?, status=?, expiryDate=?, renewCount=?, renewalDate=? "
				+ "WHERE policyId=? AND adminId=? AND isActive=1";

		return template.update(sql, policy.getVehicleId(), policy.getPolicyType(), policy.getStartDate(),
				policy.getPremium(), policy.getStatus(), policy.getExpiryDate(), policy.getRenewCount(),
				policy.getRenewalDate(), policy.getPolicyId(), policy.getAdminId()) > 0;

	}

	// DELETE (SOFT)
	public boolean deletePolicy(int policyId) {
		String sql = "UPDATE policyes SET isActive = 0 WHERE policyId = ?";
		return template.update(sql, policyId) > 0;
	}

	public List<Policy> getAllPolicies() {
		String sql = "SELECT p.*, v.vehicleName, v.model, v.vehicleNumber " + "FROM policyes p "
				+ "JOIN vehicles v ON p.vehicleId = v.vehicleId " + "WHERE p.isActive = 1";

		return template.query(sql, new RowMapper<Policy>() {
			@Override
			public Policy mapRow(ResultSet rs, int rowNum) throws SQLException {

				Policy p = new Policy();

				p.setPolicyId(rs.getInt("policyId"));
				p.setVehicleId(rs.getInt("vehicleId"));
				p.setPolicyType(rs.getString("policyType"));
				p.setStartDate(rs.getString("startDate"));
				p.setPremium(rs.getDouble("premium"));
				p.setAdminId(rs.getInt("adminId"));
				p.setStatus(rs.getString("status"));
				p.setExpiryDate(rs.getDate("expiryDate").toLocalDate());
				p.setRenewCount(rs.getInt("renewCount"));

				java.sql.Date renewDate = rs.getDate("renewalDate");
				if (renewDate != null) {
					p.setRenewalDate(renewDate.toLocalDate());
				}

				// VEHICLE object
				Vehicle v = new Vehicle();
				v.setVehicleId(rs.getInt("vehicleId"));
				v.setVehicleName(rs.getString("vehicleName"));
				v.setModel(rs.getString("model"));
				v.setVehicleNumber(rs.getString("vehicleNumber"));

				p.setVehicle(v);

				return p;
			}
		});
	}
}
