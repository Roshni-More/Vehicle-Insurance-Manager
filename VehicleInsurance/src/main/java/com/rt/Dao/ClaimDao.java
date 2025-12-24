package com.rt.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Claim;

@Repository
public class ClaimDAO {

	@Autowired
	private JdbcTemplate template;

	// 1️⃣ Save Claim
	public int saveClaim(Claim c) {
		String sql = "INSERT INTO claims (policyId,userId,accidentDate, claimAmount, details, imageName, status, is_deleted,adminId) "
				+ "VALUES (?, ?, ?, ?, ?, ?,?,?,1)";
		return template.update(sql, c.getPolicyId(), c.getUserId(), c.getAccidentDate(), c.getClaimAmount(),
				c.getDetails(), c.getImageName(), c.getStatus(), c.getAdminId());
	}

	// 2️⃣ Get All Claims
	public List<Claim> getAllClaims() {
		String sql = "SELECT * FROM claims WHERE is_deleted=1";
		return template.query(sql, new RowMapper<Claim>() {
			@Override
			public Claim mapRow(ResultSet rs, int rowNum) throws SQLException {
				Claim c = new Claim();
				c.setClaimId(rs.getInt("claimId"));
				c.setPolicyId(rs.getInt("policyId"));
				c.setAccidentDate(rs.getString("accidentDate"));
				c.setClaimAmount(rs.getDouble("claimAmount"));
				c.setDetails(rs.getString("details"));
				c.setImageName(rs.getString("imageName"));
				c.setStatus(rs.getString("status"));
				c.setIs_deleted(rs.getInt("is_deleted"));
				return c;
			}
		});
	}

	// 3️⃣ Get Claim by ID
	public Claim getClaimById(int id) {
		String sql = "SELECT * FROM claims WHERE claimId=? AND is_deleted=1";
		return template.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
			Claim c = new Claim();
			c.setClaimId(rs.getInt("claimId"));
			c.setPolicyId(rs.getInt("policyId"));
			c.setAccidentDate(rs.getString("accidentDate"));
			c.setClaimAmount(rs.getDouble("claimAmount"));
			c.setDetails(rs.getString("details"));
			c.setImageName(rs.getString("imageName"));
			c.setStatus(rs.getString("status"));
			c.setIs_deleted(rs.getInt("is_deleted"));
			return c;
		});
	}

	// 4️⃣ Update Claim
	public int updateClaim(Claim c) {
		String sql = "UPDATE claims SET policyId=?, accidentDate=?, claimAmount=?, details=?, imageName=?, status=? WHERE claimId=?";
		return template.update(sql, c.getPolicyId(), c.getAccidentDate(), c.getClaimAmount(), c.getDetails(),
				c.getImageName(), c.getStatus(), c.getClaimId());
	}

	// 5️⃣ Delete Claim (Soft Delete)
	public int deleteClaim(int id) {
		String sql = "UPDATE claims SET is_deleted=0 WHERE claimId=?";
		return template.update(sql, id);
	}

	public int getClaimById1(Claim c) {
		String sql = "UPDATE claims SET " + "policyId = ?, " + "accidentDate = ?, " + "claimAmount = ?, "
				+ "details = ? " + "WHERE claimId = ?";

		return template.update(sql, c.getPolicyId(), c.getAccidentDate(), c.getClaimAmount(), c.getDetails(),
				c.getClaimId());
	}
}
