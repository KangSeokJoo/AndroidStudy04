package com.jinasoft.study04_ksj.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.jinasoft.study04_ksj.R;

public class NotificationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
    }

    private void show(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        //필수항목
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("알림 제목");
        builder.setContentText("알림 세부 텍스트");
        //액션 정의

        Intent intent = new Intent(this, NotificationMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        // 클릭 이벤트 설정
        builder.setContentIntent(pendingIntent);
        //큰 아이콘 설정
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setColor(Color.RED);
        //기본 알림음 사운드 설정
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);
        //진동설정 : 대기시간 , 진동시간, 대기시간, 진동시간 ... 반복패턴
        long[] vibrate = {0, 100, 200, 300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);
        // 알림매니저
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 오레오에서는 알림채널을 매니저에 생성해야 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(new NotificationChannel("default", "기본채널", NotificationManager.IMPORTANCE_DEFAULT));
        }// 알림 통지
        manager.notify(1, builder.build());
    }
    private void hide(){
        // 알림 해제
        NotificationManagerCompat.from(this).cancel(1);
    }

    public void createNotification(View view) {
        show();
    }

    public void removeNotification(View view) {
        hide();
    } // 앱을 실행 -> 알림 생성 버튼을 누르면 상단에 알림이 표시, 사운드와 진동 설정을 햇으므로 소리와 진동이 함꼐 동작, 아래로 밀면 빨간색 , 큰아이콘 ,
    // 클릭하면 팬딩인텐트 등록한 인텐트 동작해 메인액티비티가 실행
    //알림 채널을 생성하는 순서는 패키지 내에 고유 ID 알림채널 객체생성 , 초기 설정으로 채널객체 설정, 알림채널 객체를 알림 매니저에게 제출
}