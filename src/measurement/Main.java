package measurement;

import java.util.Timer;

public class Main {
    
    public static void main(String[] args) {
        Timer time = new Timer();
        System.out.println("実行開始");
        // TimerTaskCallを、3秒後に、5秒間隔で実行する
        time.scheduleAtFixedRate(new TimerTaskCall(), 3000, 5000);
    }
    
}
