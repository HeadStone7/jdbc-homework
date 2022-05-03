package DBUtil;

import java.sql.*;

public class InsertSql {
    /**
     * Insert data
     * @param Sno
     * @param Sname
     * @param sex
     * @param birth
     * @return
     */
    public static int insertData(String Sno, String Sname, String sex, Date birth, String classNo){

        ResultSet rs = null;
        int dataId = 0;
        String insertSql = "INSERT INTO student(Sno, Sname, sex, birth, classNo) VALUES(?, ?, ?, ?, ?)";

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            //set parameters for statement

            pstmt.setString(1, Sno);
            pstmt.setString(2, Sname);
            pstmt.setString(3, sex);
            pstmt.setDate(4, birth);
            pstmt.setString(5, classNo);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1){

                // get user id
                rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    dataId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return dataId;
    }
}
