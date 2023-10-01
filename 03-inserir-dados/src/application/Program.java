package application;


import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Everton Martins");
            st.setString(2, "everton@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("26/01/1979").getTime()));
            st.setDouble(4, 3300.0);
            st.setInt(5, 2);

            int rowAffected = st.executeUpdate();

            if (rowAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1); // primeira coluna
                    System.out.println("Done! Id = " + id);
                }
            }else{
                System.out.println("No rows affected!");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }catch (ParseException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
