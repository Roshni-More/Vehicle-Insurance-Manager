package com.rt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.PolicyDao;
import com.rt.Dao.VehicleDao;
import com.rt.Entity.Policy;

@Service

public class PolicyService {

	@Autowired
	PolicyDao policydao;
	@Autowired
	VehicleDao vehicledao;

	public boolean insertPolicy(Policy policy) {

		// 1️⃣ vehicleId वापरून userId काढ
		Integer vehicleUserId = vehicledao.getUserIdByVehicleId(policy.getVehicleId());

		// 2️⃣ policy मध्ये set कर
		policy.setUserId(vehicleUserId);

		return policydao.insertPolicy(policy);
	}

	public List<Policy> getPoliciesByUser(Integer userId) {
		return policydao.getPoliciesByUser(userId);
	}

	public Policy getPolicyById(int policyId) {
		return policydao.getPolicyById(policyId);
	}

	public Object updatePolicy(Policy policy) {
		return policydao.updatePolicy(policy);
	}

	public Object deletePolicy(int policyId) {
		return policydao.deletePolicy(policyId);

	}

	public List<Policy> getAllPolicies() {
		return policydao.getAllPolicies();
	}

}
