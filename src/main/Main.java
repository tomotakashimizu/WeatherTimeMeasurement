package main;

import java.util.Timer;

public class Main {
    
    public static void main(String[] args) {

        Postgres postgresTest = new Postgres("testdb", "testuser", "testpass");
        // postgresTest.createTable("testtable6", "id integer, city varchar(100), time varchar(100), description varchar(100)");
        postgresTest.selectValues("SELECT * from testtable6");

        Timer time = new Timer();
        System.out.println("実行開始");
        // TimerTaskCallを、3秒後に、5秒間隔で実行する
        time.scheduleAtFixedRate(new TimerTaskCall(), 3000, 5000);
    }
    
}
