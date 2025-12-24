package com.rt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Entity.UserPolicy;
import com.rt.user.dao.UserPolicyDao;

@Service
public class UserPolicyService {

	@Autowired
	UserPolicyDao userpolicydao;

	public List<UserPolicy> getUserPolicies(int userId) {
		return userpolicydao.getPoliciesByUserId(userId);
	}

}
