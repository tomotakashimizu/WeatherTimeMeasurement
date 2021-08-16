package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.util.Timer;

public class Main {
    
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        //接続文字列
        String url = "jdbc:postgresql://127.0.0.1:5432/testdb";
        String user = "testuser";
        String password = "testpass";

        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String sql = "SELECT 1";
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while(rset.next()){
                String col = rset.getString(1);
                System.out.println(col);
            }

            //INSERT文の実行
            sql = "INSERT INTO testtable VALUES (1, 'AAA')";
            stmt.executeUpdate(sql);
            conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(rset != null)rset.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }


        // Timer time = new Timer();
        // System.out.println("実行開始");
        // // TimerTaskCallを、3秒後に、5秒間隔で実行する
        // time.scheduleAtFixedRate(new TimerTaskCall(), 3000, 5000);
    }
    
}
