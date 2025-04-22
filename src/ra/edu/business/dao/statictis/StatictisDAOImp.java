package ra.edu.business.dao.statictis;

import ra.edu.business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class StatictisDAOImp implements StatictisDAO{
    @Override
    public Map<String, Integer> statisticCourseByStudent() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String, Integer> result = new HashMap<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL statisticCourseByStudent()}");
            boolean hasResults = callSt.execute();
            if (hasResults){
                rs = callSt.getResultSet();
                while (rs.next()){
                    String courseName = rs.getString("name");
                    int studentCount = rs.getInt("total_students");
                    result.put(courseName, studentCount);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }

    @Override
    public Map<String, Integer> statisticCourseTop5HighestRegisted() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String, Integer> result = new HashMap<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL statisticCourseTop5HighestRegisted()}");
            boolean hasResults = callSt.execute();
            if (hasResults){
                rs = callSt.getResultSet();
                while (rs.next()){
                    String courseName = rs.getString("name");
                    int studentCount = rs.getInt("total_students");
                    result.put(courseName, studentCount);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }

    @Override
    public Map<String, Integer> statisticCourseWith10StudentsOrHigher() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String, Integer> result = new HashMap<>();
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL statisticCourseWith10StudentsOrHigher()}");
            boolean hasResults = callSt.execute();
            if (hasResults){
                rs = callSt.getResultSet();
                while (rs.next()){
                    String courseName = rs.getString("name");
                    int studentCount = rs.getInt("total_students");
                    result.put(courseName, studentCount);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }

}
