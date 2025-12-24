package com.rt.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.UserPolicy;

@Repository
public class UserPolicyDao {

	@Autowired
	private JdbcTemplate template;

	public List<UserPolicy> getPoliciesByUserId(int userId) {

		String sql = "SELECT policyId, userId, vehicleId, policyType, startDate, expiryDate, premium,status "
				+ "FROM policyes WHERE userId = ? AND isActive = 1";

		return template.query(sql, new RowMapper<UserPolicy>() {

			@Override
			public UserPolicy mapRow(ResultSet rs, int rowNum) throws SQLException {

				UserPolicy p = new UserPolicy();
				p.setPolicyId(rs.getInt("policyId"));
				p.setUserId(rs.getInt("userId"));
				p.setVehicleId(rs.getInt("vehicleId"));
				p.setPolicyType(rs.getString("policyType"));
				p.setStartDate(rs.getDate("startDate")); // ✅ Date → Date
				p.setExpiryDate(rs.getDate("expiryDate")); // ✅ Date → Date
				p.setPremium(rs.getDouble("premium"));
				p.setStatus(rs.getString("status"));

				return p;
			}
		}, userId);
	}
}
