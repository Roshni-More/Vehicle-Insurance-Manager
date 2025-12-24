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
		String sql = "INSERT INTO vehicles "
				+ "(vehicleName, model, vehicleNumber, vehicleType, purchaseYear, engineNo, userId, adminId, isactive) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";

		try {
			int result = template.update(sql, vehicle.getVehicleName(), vehicle.getModel(), vehicle.getVehicleNumber(),
					vehicle.getVehicleType(), vehicle.getPurchaseYear(), vehicle.getEngineNo(), vehicle.getUserId(),
					vehicle.getAdminId());

			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Vehicle> getAllVehicle(Integer userId) {
		String sql = "SELECT * FROM vehicles WHERE adminId = ? AND isactive = 1";

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
				v.setUserId(rs.getInt("userId"));
				v.setAdminId(rs.getInt("adminId"));

				return v;
			}
		}, userId);
	}

	public Vehicle getVehicleById(int vehicleId) {
		String sql = "SELECT * FROM vehicles WHERE vehicleId = ? AND isactive = 1";

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
					v.setUserId(rs.getInt("userId"));
					v.setAdminId(rs.getInt("adminId"));

					return v;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateVehicle(Vehicle vehicle) {
		String sql = "UPDATE vehicles SET " + "vehicleName=?, model=?, vehicleNumber=?, vehicleType=?, "
				+ "purchaseYear=?, engineNo=? " + "WHERE vehicleId=?";

		int result = template.update(sql, vehicle.getVehicleName(), vehicle.getModel(), vehicle.getVehicleNumber(),
				vehicle.getVehicleType(), vehicle.getPurchaseYear(), vehicle.getEngineNo(), vehicle.getVehicleId());

		return result > 0;
	}

	public Object deleteVehicledata(int vehicleId) {
		String sql = "UPDATE vehicles SET isactive = 0 WHERE vehicleId = ?";

		int result = template.update(sql, vehicleId);

		return result > 0;
	}

	public List<Vehicle> getVehiclesByUser(Integer userId) {
		String sql = "SELECT * FROM vehicles WHERE adminId = ? AND isactive = 1";

		return template.query(sql, (rs, rowNum) -> {
			Vehicle v = new Vehicle();
			v.setVehicleId(rs.getInt("vehicleId"));
			v.setVehicleName(rs.getString("vehicleName"));
			v.setModel(rs.getString("model"));
			v.setVehicleNumber(rs.getString("vehicleNumber"));
			v.setVehicleType(rs.getString("vehicleType"));
			v.setPurchaseYear(rs.getInt("purchaseYear"));
			v.setEngineNo(rs.getString("engineNo"));
			v.setUserId(rs.getInt("userId"));
			v.setAdminId(rs.getInt("adminId"));
			return v;
		}, userId);
	}

	public List<Vehicle> getAllVehicle1(Integer userId) {
		String sql = "SELECT * FROM vehicles WHERE adminId = ? AND isactive = 1";

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
				v.setUserId(rs.getInt("userId"));
				v.setAdminId(rs.getInt("adminId"));
				return v;
			}
		}, userId);
	}

	public Integer getUserIdByVehicleId(int vehicleId) {
		String sql = "SELECT userId FROM vehicles WHERE vehicleId = ?";
		return template.queryForObject(sql, Integer.class, vehicleId);
	}

}
