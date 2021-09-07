package run;

import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        // Postgres postgresTest = new Postgres("testdb", "testuser", "testpass");
        // postgresTest.createTable("testtable7", "id integer, city varchar(100), time
        // varchar(100), measuring_time integer, target_weather varchar(100),
        // current_weather varchar(100), current_weather_time integer,
        // total_target_weather_time integer, total_target_weather_count integer");
        // postgresTest.selectValues("SELECT * from testtable7");

        Timer time = new Timer();
        System.out.println("実行開始");
        // TimerTaskCallを、3秒後に、5秒間隔で実行する
        time.scheduleAtFixedRate(new TimerTaskCall(), 3000, 5000);
    }

}
