package com.rt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.VehicleDao;
import com.rt.Entity.Vehicle;

@Service

public class VehicleService {

	@Autowired
	VehicleDao vehicledao;

	public boolean vehicleInsert(Vehicle vehicle) {
		return vehicledao.vehicleInsert(vehicle);
	}

	public List<Vehicle> getAllVehicle(Integer userId) {
		return vehicledao.getAllVehicle(userId);
	}

	public Vehicle getVehicleById(int vehicleId) {

		return vehicledao.getVehicleById(vehicleId);
	}

	public boolean updateVehicle(Vehicle vehicle) {
		return vehicledao.updateVehicle(vehicle);

	}

	public Object deleteVehicledata(int vehicleId) {
		return vehicledao.deleteVehicledata(vehicleId);
	}

	public List<Vehicle> getVehiclesByUser(int userId) {

		return vehicledao.getVehiclesByUser(userId);
	}

}
