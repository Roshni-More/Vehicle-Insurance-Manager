package com.rt.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Vehicle;

@Repository

public class VehicleDao {

	@Autowired
	private JdbcTemplate template;

	public boolean vehicleInsert(Vehicle vehicle) {
		String sql = "INSERT INTO vehicletable "
				+ "(vehicleName, model, vehicleNumber, vehicleType, purchaseYear, engineNo, customerId, userId,isactive) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,1)";

		int result = template.update(sql, vehicle.getVehicleName(), vehicle.getModel(), vehicle.getVehicleNumber(),
				vehicle.getVehicleType(), vehicle.getPurchaseYear(), vehicle.getEngineNo(), vehicle.getCustomerId(),
				vehicle.getUserId());

		return result > 0;
	}

	public List<Vehicle> getAllVehicle(Integer userId) {

		String sql = "SELECT * FROM vehicletable WHERE userId = ? AND isactive = 1";

		return template.query(sql, new RowMapper<Vehicle>() {

			@Override
			public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {

				Vehicle v = new Vehicle();

				v.setVehicleId(rs.getInt("vehicleId"));
				v.setVehicleName(rs.getString("vehicleName"));
				v.setModel(rs.getString("model"));
				v.setVehicleNumber(rs.getString("vehicleNumber"));
				v.setVehicleType(rs.getString("vehicleType"));
				v.setPurchaseYear(rs.getInt("purchaseYear"));
				v.setEngineNo(rs.getString("engineNo"));
				v.setCustomerId(rs.getInt("customerId"));
				v.setUserId(rs.getInt("userId"));

				return v;
			}
		}, userId);
	}

	public Vehicle getVehicleById(int vehicleId) {
		String sql = "SELECT * FROM vehicletable WHERE vehicleId = ? AND isactive = 1";

		try {

			return template.queryForObject(sql, new Object[] { vehicleId }, new RowMapper<Vehicle>() {

				@Override
				public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {

					Vehicle v = new Vehicle();
					v.setVehicleId(rs.getInt("vehicleId"));
					v.setVehicleName(rs.getString("vehicleName"));
					v.setModel(rs.getString("model"));
					v.setVehicleNumber(rs.getString("vehicleNumber"));
					v.setVehicleType(rs.getString("vehicleType"));
					v.setPurchaseYear(rs.getInt("purchaseYear"));
					v.setEngineNo(rs.getString("engineNo"));
					v.setCustomerId(rs.getInt("customerId"));
					v.setUserId(rs.getInt("userId"));

					return v;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateVehicle(Vehicle vehicle) {
		String sql = "UPDATE vehicletable SET " + "vehicleName = ?, " + "model = ?, " + "vehicleNumber = ?, "
				+ "vehicleType = ?, " + "purchaseYear = ?, " + "engineNo = ?, " + "customerId = ? "
				+ "WHERE vehicleId = ? AND userId = ?";

		int result = template.update(sql, vehicle.getVehicleName(), vehicle.getModel(), vehicle.getVehicleNumber(),
				vehicle.getVehicleType(), vehicle.getPurchaseYear(), vehicle.getEngineNo(), vehicle.getCustomerId(),
				vehicle.getVehicleId(), vehicle.getUserId());

		return result > 0;
	}

	public Object deleteVehicledata(int vehicleId) {
		String sql = "UPDATE vehicletable SET isactive = 0 WHERE vehicleId = ?";

		int result = template.update(sql, vehicleId);

		return result > 0;
	}

	public List<Vehicle> getVehiclesByUser(int userId) {
		String sql = "SELECT * FROM vehicletable WHERE userId = ? AND isactive = 1";

		return template.query(sql, new RowMapper<Vehicle>() {

			@Override
			public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {

				Vehicle v = new Vehicle();

				v.setVehicleId(rs.getInt("vehicleId"));
				v.setVehicleName(rs.getString("vehicleName"));
				v.setModel(rs.getString("model"));
				v.setVehicleNumber(rs.getString("vehicleNumber"));
				v.setVehicleType(rs.getString("vehicleType"));
				v.setPurchaseYear(rs.getInt("purchaseYear"));
				v.setEngineNo(rs.getString("engineNo"));
				v.setCustomerId(rs.getInt("customerId"));
				v.setUserId(rs.getInt("userId"));

				return v;
			}
		}, userId);
	}

}
