package com.jinasoft.study04_ksj.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.jinasoft.study04_ksj.R;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();
    private Thread mthread;
    private int mCount = 0;

    public MyService() {
    }
    // 앱실행 -> 서비스 시작 -> 카운트 -> 앱종료 카운트 수행중 -> 앱다시 실행 -> 카운트 수행-> 서비스 종료 -> 카운트 종료
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if ("startForeground".equals(intent.getAction())){
            // 포그라운드 서비스 시작
            startForegroundService();
        }else if (mthread == null){
            if(mthread == null){
                //스레드 초기화 및 시작
                mthread = new Thread("My Thread"){
                    @Override
                    public void run() {
                        for (int i =0; i < 100; i++){
                            try{
                                mCount++;
                                // 1초마다 쉬기
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                //스레드에 인터럽트가 걸리면
                                //오래 걸리는 처리 종료
                                break;
                            }
                            //1초마다 로그 남기기
                            Log.d("My Service", "서비스 동작 중 " + mCount);
                        }
                    }
                };
                mthread.start();
            }
        }return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        //stopService에 의해 호출됨
        // 스레드를 정지시킴
        if (mthread != null){
            mthread.interrupt();
            mthread = null;
        }
    }

    private IBinder binder = new MyBinder();
    public class MyBinder extends Binder{
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: 여기야");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: 여기야");
        return super.onUnbind(intent);
    }

    public int getCount(){
        return mCount;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 여기야");
    }

    private void startForegroundService(){
        // default 채널 ID로 알림 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("포그라운드 서비스");
        builder.setContentText("포그라운드 서비스 실행 중");
        Intent notificationIntent = new Intent(this, ServiceMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        builder.setContentIntent(pendingIntent);
        // 오래오에서는 알림 채널을 매니저에 생성해야 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        //포그라운드로 시작
        startForeground(1, builder.build());
    }
}