package com.jinasoft.study04_ksj.AlarmManager;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private AlarmManager mAlarmManager;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //알람 매니저 인스턴스 얻기
        mAlarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        // 현재 시간으로 타임 피커를 설정
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // 타임 피커 다이얼로그를 현재 시간 설정으로 생성하고 반환


        return new TimePickerDialog(getContext(), this, hour, minute, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) { // i = hourday minute
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, i);
        calendar.set(Calendar.MINUTE, i1);
        //알람이 동작되면 MainActivity를 실행하도록 동작 정의
        // 여기서 브로드캐스트나 서비스를 실행할 수도 있음
        Intent intent = new Intent(getContext(), AlarmMain.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0); // 설정된 시간에 기기가 슬립상태에서도 알람이 동작설정
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
}
