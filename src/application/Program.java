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

	public static void main(String[] args) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
/*
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?, ?, ?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Barack Obama");
			st.setString(2, "barack@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("09/08/1980").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);
*/
			st = conn.prepareStatement("INSERT INTO department (name) values ('Fisiocultura'), ('Estética')",
					Statement.RETURN_GENERATED_KEYS);
			int countLines = st.executeUpdate();
			
//			System.out.println("Perfect: " + countLines + " lines affected");
			if (countLines > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Perfect: new " + id);
				}
			}
			else {
				System.out.println("No lines affected");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		catch (ParseException e) {
//			e.printStackTrace();
//		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
