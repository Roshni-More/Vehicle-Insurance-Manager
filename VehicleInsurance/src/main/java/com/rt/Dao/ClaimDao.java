package com.rt.Dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Claim;

@Repository
public class ClaimDao {

	@Autowired
	private JdbcTemplate template;

	// ------------------- INSERT CLAIM -------------------

	public int insertClaim(Claim claim) {
		String sql = "INSERT INTO claimtable (policyId, accidentDate, claimAmount, details, imageName, status, is_deleted) "
				+ "VALUES (?, ?, ?, ?, ?, ?, 1)";

		return template.update(sql, claim.getPolicyId(), claim.getAccidentDate(), claim.getClaimAmount(),
				claim.getDetails(), claim.getImageName(), "Pending");
	}

	// ------------------- GET ALL ACTIVE CLAIMS -------------------
	public List<Claim> getAllClaims() {
		String sql = "SELECT * FROM claimtable WHERE is_deleted = 1";
		return template.query(sql, (ResultSet rs, int rowNum) -> {
			Claim c = new Claim();
			c.setClaimId(rs.getInt("claimId"));
			c.setPolicyId(rs.getInt("policyId"));
			c.setAccidentDate(rs.getDate("accidentDate"));
			c.setClaimAmount(rs.getDouble("claimAmount"));
			c.setDetails(rs.getString("details"));
			c.setStatus(rs.getString("status"));
			c.setIsDeleted(rs.getInt("is_deleted"));
			return c;
		});
	}

	// ------------------- GET CLAIM BY ID -------------------
	public Claim getClaimById(int claimId) {
		String sql = "SELECT * FROM claimtable WHERE claimId = ? AND is_deleted = 1"; // only active
		return template.queryForObject(sql, new Object[] { claimId }, (ResultSet rs, int rowNum) -> {
			Claim c = new Claim();
			c.setClaimId(rs.getInt("claimId"));
			c.setPolicyId(rs.getInt("policyId"));
			c.setAccidentDate(rs.getDate("accidentDate"));
			c.setClaimAmount(rs.getDouble("claimAmount"));
			c.setDetails(rs.getString("details"));
			c.setStatus(rs.getString("status"));
			c.setIsDeleted(rs.getInt("is_deleted"));
			return c;
		});
	}

	// ------------------- UPDATE CLAIM -------------------
	public int updateClaim(Claim claim) {
		String sql = "UPDATE claimtable SET policyId=?, accidentDate=?, claimAmount=?, details=?, status=? "
				+ "WHERE claimId=? AND is_deleted = 1";
		return template.update(sql, claim.getPolicyId(), claim.getAccidentDate(), claim.getClaimAmount(),
				claim.getDetails(), claim.getStatus(), claim.getClaimId());
	}

	// ------------------- SOFT DELETE CLAIM -------------------

	public int deleteClaim(int claimId) {
		String sql = "UPDATE claimtable SET is_deleted = 0 WHERE claimId = ?";
		return template.update(sql, claimId);
	}
}
