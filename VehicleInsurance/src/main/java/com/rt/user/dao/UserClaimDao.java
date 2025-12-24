package com.rt.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.UserClaim;

@Repository
public class UserClaimDao {

	@Autowired
	private JdbcTemplate template;

	public List<UserClaim> getUserClaim(Integer userId) {
		String sql = "SELECT c.claimId, c.policyId, v.vehicleName, " + "c.accidentDate, c.claimAmount, c.status "
				+ "FROM claims c " + "JOIN policyes p ON c.policyId = p.policyId "
				+ "JOIN vehicles v ON p.vehicleId = v.vehicleId " + "WHERE c.userId = ? AND c.is_deleted = 1 "
				+ "ORDER BY c.claimId DESC";

		return template.query(sql, new RowMapper<UserClaim>() {

			@Override
			public UserClaim mapRow(ResultSet rs, int rowNum) throws SQLException {

				UserClaim uc = new UserClaim();
				uc.setClaimId(rs.getInt("claimId"));
				uc.setPolicyId(rs.getInt("policyId"));
				uc.setVehicleName(rs.getString("vehicleName"));
				uc.setAccidentDate(rs.getString("accidentDate"));
				uc.setClaimAmount(rs.getDouble("claimAmount"));
				uc.setStatus(rs.getString("status"));

				return uc;
			}
		}, userId);
	}

}
