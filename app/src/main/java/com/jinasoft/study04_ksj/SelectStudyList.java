package com.jinasoft.study04_ksj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jinasoft.study04_ksj.AlarmManager.AlarmMain;
import com.jinasoft.study04_ksj.AsyncTask.AsyncTaskMain;
import com.jinasoft.study04_ksj.DontStop.DontStopMain;
import com.jinasoft.study04_ksj.Notification.NotificationMain;
import com.jinasoft.study04_ksj.Service.ServiceMain;


public class SelectStudyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_study_list);

        Button btn1 = (Button) findViewById(R.id.Selcet_Step1BTN);
        Button btn2 = (Button) findViewById(R.id.Selcet_Step2BTN);
        Button btn3 = (Button) findViewById(R.id.Selcet_Step3BTN);
        Button btn4 = (Button) findViewById(R.id.Selcet_Step4BTN);
        Button btn5 = (Button) findViewById(R.id.Selcet_Step5BTN);
//        Button btn6 = (Button) findViewById(R.id.Selcet_CountBTN);
//        Button btn7 = (Button) findViewById(R.id.Selcet_DataBaseBTN);
//
        btn1.setOnClickListener(view -> {
            Log.d("btn1_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, AsyncTaskMain.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            Log.d("btn2_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, DontStopMain.class);
            startActivity(intent);
        });
        btn3.setOnClickListener(view -> {
            Log.d("btn3_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, ServiceMain.class);
            startActivity(intent);
        });
        btn4.setOnClickListener(view -> {
            Log.d("btn4_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, NotificationMain.class);
            startActivity(intent);
        });
        btn5.setOnClickListener(view -> {
            Log.d("btn5_btn", "활성");
            Intent intent = new Intent(SelectStudyList.this, AlarmMain.class);
            startActivity(intent);
        });
//        btn6.setOnClickListener(view -> {
//            Log.d("btn6_btn", "활성");
//            Intent intent = new Intent(com.jinasoft.study03_ksj.SelectStudyList.this, CountMain.class);
//            startActivity(intent);
//        });
//        btn7.setOnClickListener(view -> {
//            Log.d("btn7_btn", "활성");
//            Intent intent = new Intent(com.jinasoft.study03_ksj.SelectStudyList.this, DataBaseMain.class);
//            startActivity(intent);
//        });

//        btn.setOnClickListener(new View.OnClickListener() {  원래 쓰던 Onclick 식
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SelectStudyList.this, EmailSave.class);
//                startActivity(intent);
//            }
//        });

//      public void GO_Act(view view){ // 다른 버튼을 눌러도 그 버튼이 한바퀴 스위치문 한바퀴를 도는게 느리지 않나 생각
//      swich(v.getId()){
//          case R.id. ~~~ ;
//              Intent in1 = new Intent ~~~~
//              startActivity(1);
//              break;
//      }


//    public void Go_Act7(View view){                         메소드로 온크릭을 기능 구현했었으나 위 방법으로 작성할 예정이였으나,
//        Intent intent = new Intent(this, ScrollView.class);
//        startActivity(intent);
//    }
//    public void Go_Act6(View view){
//        Intent intent = new Intent(this, Browser.class);
//        startActivity(intent);
//    }
//    public void Go_Act5(View view){
//        Intent intent = new Intent(this, OptionMenu.class);
//        startActivity(intent);
//    }
//    public void Go_Act4(View view){
//        Intent intent = new Intent(this, AppInQuest.class);
//        startActivity(intent);
//    }
//    public void Go_Act3(View view){
//        Intent intent = new Intent(this, KakaoMessage.class);
//        startActivity(intent);
//    }
//    public void Go_Act2(View view){
//        Intent intent = new Intent(this, ActionCall.class);
//        startActivity(intent);
//    }
//    public void Go_Act1(View view){
//        Intent intent = new Intent(this, ActivityDataSend.class);
//        startActivity(intent);
//    }

    }
}