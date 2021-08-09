package measurement;

import java.util.Timer;

public class Main {
    
    public static void main(String[] args) {
        // Timerクラスのオブジェクトを作成
        Timer time = new Timer();

        System.out.println("実行開始");

        // 一定間隔で処理を開始する
        // TimerTaskCallを、3秒後に、5秒間隔で実行する
        time.scheduleAtFixedRate(new TimerTaskCall(), 3000, 5000);
    }
    
}
