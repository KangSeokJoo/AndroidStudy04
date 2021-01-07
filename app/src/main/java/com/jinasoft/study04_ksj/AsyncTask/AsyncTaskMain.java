package com.jinasoft.study04_ksj.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jinasoft.study04_ksj.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskMain extends AppCompatActivity {
    private  static final String TAG = AsyncTaskMain.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_main);
        //소스를 확인하고 싶은 사이트 주소
        new HttpAsycTask().execute("https://www.naver.com");
    }

    private static class HttpAsycTask extends AsyncTask<String, Void, String> {
        // OkHttp 클라이언트
        OkHttpClient client = new OkHttpClient();
        @Override
        protected String doInBackground(@NotNull String... strings) {
            String result = null;
            String strUrl = strings[0];

            try{
                // 요청
                Request request = new Request.Builder()
                // okhttp 의 메소드
                      .url(strUrl).build();
                try{
                    //응답          트라이 씌운이유는 오류가 나서 그럼 , 리스폰부분 에러 try bulid(); 밑에 리스폰 , 로그 넣으면 됌
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "oncreate: " + response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch(RuntimeException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null){
                Log.d("HttpAsyncTask", s);

                //로그캣에 날씨 정보가 표시됌
            }
        }
    }
}