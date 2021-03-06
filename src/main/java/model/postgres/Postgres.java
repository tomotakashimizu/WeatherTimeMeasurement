package model.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Postgres {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    private String userName;
    private String password;
    private String url;

    public Postgres(String dbName, String userName, String password) {
        this.userName = userName;
        this.password = password;
        // 接続文字列
        this.url = "jdbc:postgresql://127.0.0.1:5432/" + dbName;
    }

    public void createValues(String tableName, String values) {

        try {
            // PostgreSQLへ接続
            conn = DriverManager.getConnection(this.url, this.userName, this.password);
            // 自動コミットOFF
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            // INSERT文の実行
            String sql = "INSERT INTO " + tableName + " VALUES (" + values + ")";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void createTable(String tableName, String valuesType) {

        try {
            // PostgreSQLへ接続
            conn = DriverManager.getConnection(this.url, this.userName, this.password);
            // 自動コミットOFF
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            // CREATE文の実行
            String sql = "CREATE TABLE " + tableName + " (" + valuesType + ")";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void selectValues(String sql) {

        try {
            // PostgreSQLへ接続
            conn = DriverManager.getConnection(this.url, this.userName, this.password);
            // 自動コミットOFF
            conn.setAutoCommit(false);

            // SELECT文の実行
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);

            // SELECT結果の受け取り
            while (rset.next()) {
                String col = rset.getString("current_weather");
                System.out.println(col);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null)
                    rset.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
