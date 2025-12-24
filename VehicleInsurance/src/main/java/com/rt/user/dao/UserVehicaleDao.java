package com.rt.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Vehicle;

@Repository
public class UserVehicaleDao {

	@Autowired
	private JdbcTemplate template;

	public List<Vehicle> getVehiclesByUserId(Integer userId) {

		String sql = "SELECT vehicleId, vehicleName, model, vehicleNumber, " + "vehicleType, purchaseYear, engineNo "
				+ "FROM vehicles WHERE userId = ? AND isactive = 1";

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

				return v;
			}

		}, userId);
	}

}
