package com.rt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.ClaimDAO;
import com.rt.Dao.PolicyDao;
import com.rt.Entity.Claim;

@Service
public class ClaimService {

	@Autowired
	private ClaimDAO dao;
	@Autowired
	private PolicyDao policyDao;

	public void saveClaim(Claim c) {

		int userId = policyDao.getUserIdByPolicyId(c.getPolicyId());

		c.setUserId(userId);

		c.setStatus("Pending");

		dao.saveClaim(c);
	}

	// 2️⃣ Get All Claims
	public List<Claim> getAllClaims() {
		return dao.getAllClaims();
	}

	// 3️⃣ Get Claim by ID
	public Claim getClaimById(int id) {
		return dao.getClaimById(id);
	}

	// 4️⃣ Update Claim
	public int updateClaim(Claim c) {
		return dao.updateClaim(c);
	}

	// 5️⃣ Delete Claim
	public int deleteClaim(int id) {
		return dao.deleteClaim(id);
	}

	public Claim getClaimById1(int claimId) {
		return dao.getClaimById(claimId);
	}
}
