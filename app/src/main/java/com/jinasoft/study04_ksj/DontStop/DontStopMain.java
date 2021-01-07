package com.jinasoft.study04_ksj.DontStop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jinasoft.study04_ksj.R;

public class DontStopMain extends AppCompatActivity {

    //쓰레드 객체
    private Thread mThread;
    // 카운팅
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dont_stop_main);


//        Toast.makeText(this, "쓰레드 동작 확인 : "+ mCount, Toast.LENGTH_SHORT).show();
    }

    public void startThread(View view) {

        if (mThread == null){
            // 스레드 초기화 및 시작
            mThread = new Thread("My Thread"){
                @Override
                public void run() {
                    for (int i=0 ; i < 1000; i++){
                        try{
                            mCount++;
                            // 1초마다 쉬기
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            break;
                        } //초당 로그 남기기
                        Log.d("My Thread", "스레드 동작 중"+ mCount);
                    }
                }
            };
            mThread.start();
            Toast.makeText(this, "쓰레드 동작 확인 : "+ mCount, Toast.LENGTH_SHORT).show();

        }
    }
    //스레드 종료

    public void stopThread(View view) {
        if (mThread != null){
            mThread.interrupt();
            mThread = null;
            mCount = 0;
        }
    }

    public void playThread(View view) {
        if (mThread != null) {
            Toast.makeText(this, "쓰레드 동작 확인 : " + mCount, Toast.LENGTH_SHORT).show();
        }else if (mThread == null){
            Toast.makeText(this, "스레드 정지상태 : " + mThread, Toast.LENGTH_SHORT).show();
        }
    }
}