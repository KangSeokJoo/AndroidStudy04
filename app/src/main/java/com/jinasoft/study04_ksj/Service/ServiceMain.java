package com.jinasoft.study04_ksj.Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.jinasoft.study04_ksj.R;

public class ServiceMain extends AppCompatActivity {

    private int mCount;
    private MyService myService;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);


    }

    public void OnStartService(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    } // 서비스가 시작되면 제일먼저 onStartConmmand 메서드가 호출 재정의하여 처리를 해야함

    public void OnStopService(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void OnStartIntentService(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
    } // 인텐트 서비스는 순서대로 일을 처리하고 스스로 stopSelf를 하기에 정지를 할 필요없음음

    public void OnStartForegroundService(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction("startForeground");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(intent);
        }else {
            startService(intent); // 포그라운드는 죽지않는 서비스를 사용할수 있고 항상 알림을 표시해야 한다 알림을 제거하면 서비스도 중지될 수 있따.
        }
    }

    //바인더는 액티비티가 다른 컴포넌트와 연결하여 서로 데이터를 주고 받을 수 있다. 별도 프로세스로 ㄷㅇ작하기 때문에 서비스끼리 데이터를 주고받으려면 바인더를 사용
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // MyBinder와 연결될 것이며 IBinder 타입으로 넘오는 것을 캐스팅하여 사용
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //예기치 않는 오류
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        //서비스에 바인딩
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 서비스와 연결 해제
        if (mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void getCountValue(View view) {
        if (mBound){
            Toast.makeText(this, "카운팅 : "+ myService.getCount(), Toast.LENGTH_SHORT).show();
        }
    }
}