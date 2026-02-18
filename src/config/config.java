package config;

import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;

public class config {

    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:Clothing_db.db");
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

    // Simplified addRecord using setObject (just like your friend probably does)
    public void addRecord(String sql, Object... values) {
        try (Connection conn = connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public void displayData(String sql, javax.swing.JTable table, Object... values) {
   try (Connection conn = connectDB(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // This loop binds values ONLY if you provided them (like in a search)
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
        }
        
        try (ResultSet rs = pstmt.executeQuery()) {
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
    } catch (SQLException e) {
        System.out.println("Display Error: " + e.getMessage());
        }
    }

    public void deleteRecord(int id, String table, String column) {
       String sql = "DELETE FROM " + table + " WHERE " + column + " = ?";
        try (Connection conn = connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Successfully deleted!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete Error: " + e.getMessage());
        }
    }
    public int insertData(String sql){
  // Try-with-resources ensures the connection and statement close automatically
        try (Connection conn = connectDB(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            return 1;
        } catch (SQLException ex) {
            System.out.println("Insert Error: " + ex.getMessage());
            return 0;
        }
    }
    public ResultSet getData(String sql) throws SQLException {
    Connection conn = connectDB();
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }
}