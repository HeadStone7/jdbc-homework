package DBUtil;

import java.sql.*;

public class StoredProcedure {

    public  static void StoredProcedureOutputParameterSample(String classNo){
        String query = "{call Total_numberOf_Student(?, ?)}";

        try(Connection conn = DBUtil.getConnection();
            CallableStatement stmt = conn.prepareCall(query)) {

            //Output parameter integer called sum.
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, classNo);
            stmt.execute();
            System.out.println(classNo+" has:"+stmt.getInt(1)+" Students");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void SumOfNumbers(int a, int b){
        String sumProcedure = "{call addTwoNumbers(?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
            CallableStatement stmt = conn.prepareCall(sumProcedure)){
            stmt.setInt(1, a);
            stmt.setInt(2, b);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.execute();
            System.out.println("Sum of "+a+" and "+b+" is equal to: "+stmt.getInt(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void customizeStoredProcedure(String sex){
        String query = "{call genderDifference(?)}";

        ResultSet rs;
        try(Connection conn = DBUtil.getConnection();
            CallableStatement stmt = conn.prepareCall(query)){

            stmt.setString(1,sex);
            rs = stmt.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("Sno")+" - "+rs.getString("Sname")+" - "+rs.getString("Sex")+" - "+rs.getString("Birth")+" - "+rs.getString("ClassNo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
