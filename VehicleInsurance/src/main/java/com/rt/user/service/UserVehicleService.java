package com.rt.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Entity.Vehicle;
import com.rt.user.dao.UserVehicaleDao;

@Service
public class UserVehicleService {
	@Autowired
	UserVehicaleDao userVehicaleDao;

	public List<Vehicle> getVehiclesByUserId(Integer userId) {
		return userVehicaleDao.getVehiclesByUserId(userId);
	}

}
