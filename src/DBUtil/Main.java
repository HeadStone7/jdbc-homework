package DBUtil;


import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String Sname;

        Date date = new Date(2022, 05, 05);
        System.out.println("1.Display table\n2.Delete data\n3.Insert Data\n4.Update data\n5.Get number of students by classNo\n" +
                "6.Calculate sum of two numbers\n7.Get number of Student by sex\n0. To Exit");

        while(true){
            System.out.print("--->Your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    displayTable();
                    break;
                case 2:
                    System.out.println("Insert Sname:");
                    Sname = scanner.next();
                    deleleBySname(Sname);
                    break;
                case 3:
                    //Insert data
                    System.out.print("Sno: ");
                    String sno = scanner.next();
                    System.out.print("Sname: ");
                    String sname = scanner.next();
                    System.out.print("Sex: ");
                    String sex = scanner.next();
                    System.out.print("Birth YYYY-MM-DD: ");
                    String date1= scanner.next();
                    Date birth = Date.valueOf(date1);
                    System.out.print("ClassNo: ");
                    String classNo = scanner.next();
                    InsertSql.insertData(sno, sname, sex, birth, classNo);
                    System.out.println("data successfully added !");
                    break;
                case 4:
                    //Update data
                    System.out.println("update username, where Sno...");
                    System.out.println("Insert username: ");
                    String sname1 = scanner.next();
                    System.out.println("Insert Sno: ");
                    String Sno = scanner.next();
                    updateByUsername(sname1, Sno);
                    System.out.println("Successfully updated");
                    break;
                case 5:
                    System.out.println("ClassNo: ");
                    String classNo1 = scanner.next();
                    StoredProcedure.StoredProcedureOutputParameterSample(classNo1);
                    break;
                case 6:
                    System.out.print("type first integer: ");
                    int a = scanner.nextInt();
                    System.out.print("Type the second integer: ");
                    int b = scanner.nextInt();
                    StoredProcedure.SumOfNumbers(a, b);
                    break;
                case 7:
                    System.out.print("type gender 男, 女 or M: ");
                    String gender = scanner.next();
                    StoredProcedure.customizeStoredProcedure(gender);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Index out of range, try again.");
            }
        }

//


    }
    private static void updateByUsername(String sname, String sno) throws SQLException {
        String sql ="UPDATE Student SET sname=? WHERE Sno=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement prpStmt = conn.prepareCall(sql);
        prpStmt.setString(1, sname);
        prpStmt.setString(2,sno);

        prpStmt.executeUpdate();
    }

    private static void displayTable() throws SQLException {
        String sql = "SELECT * FROM student";
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("Sno")+"\t"+rs.getString("Sname")+"\t\t"+rs.getString("Sex")+"\t"+rs.getDate("Birth")+"\t"+rs.getString("classNo"));
        }
    }

    private static void deleleBySname(String sname){

        String delete = "DELETE FROM student WHERE Sname= ?";

        try (Connection conn = DBUtil.getConnection();
        ){
            PreparedStatement prstmt = conn.prepareCall(delete);
            prstmt.setString(1, sname);
            prstmt.executeUpdate();
            System.out.println("Row deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
