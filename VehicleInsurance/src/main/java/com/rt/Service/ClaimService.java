package com.rt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.ClaimDao;
import com.rt.Entity.Claim;

@Service
public class ClaimService {

	@Autowired
	ClaimDao claimdao;

	public int insertClaim(Claim claim) {
		return claimdao.insertClaim(claim);
	}

	// GET ALL CLAIMS
	public List<Claim> getAllClaims() {
		return claimdao.getAllClaims();
	}

	// GET CLAIM BY ID
	public Claim getClaimById(int claimId) {
		return claimdao.getClaimById(claimId);
	}

	// UPDATE CLAIM
	public int updateClaim(Claim claim) {
		return claimdao.updateClaim(claim);
	}

	// SOFT DELETE CLAIM
	public int deleteClaim(int claimId) {
		return claimdao.deleteClaim(claimId);
	}
}
