package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = DB.getConnection();
			/*
			 * ps = conn.prepareStatement(
			 * 
			 * "INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
			 * +"VALUES " + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			 * 
			 * ps.setString(1, "Leonardo"); ps.setString(2, "leonardo@company.com");
			 * ps.setDate(3, new java.sql.Date(sdf.parse("12/08/1998").getTime()));
			 * ps.setDouble(4, 8500.0); ps.setInt(5, 4);
			 * 
			 */

			ps = conn.prepareStatement("INSERT INTO department " + "(name) " + "VALUES " + ("(?), (?)"),
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, "Travels");
			ps.setString(2, "Shoes");

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Result: " + id);
				}
			} else {
				System.out.println("NO ROW AFFECTED");
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			DB.closeConnection();
			DB.closeStatement(ps);
			DB.closeResultSet(rs);

		}

	}

}
