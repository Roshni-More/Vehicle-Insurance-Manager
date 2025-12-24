package com.rt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Entity.UserClaim;
import com.rt.user.dao.UserClaimDao;

@Service
public class UserClaimService {

	@Autowired
	UserClaimDao claimdao;

	public List<UserClaim> getUserClaim(Integer userId) {
		return claimdao.getUserClaim(userId);
	}

}
