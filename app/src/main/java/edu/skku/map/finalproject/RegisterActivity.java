package edu.skku.map.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText register_pw;
    EditText register_id;
    Button register_btn;
    String check;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register_id = findViewById(R.id.register_id);
        register_pw = findViewById(R.id.register_password);
        register_btn = findViewById(R.id.register_button);


        register_btn.setOnClickListener(view -> {
            OkHttpClient client = new OkHttpClient();
            DataModel data = new DataModel();
            String name = register_id.getText().toString();
            String pw = register_pw.getText().toString();
            data.setName(name);
            data.setPasswd(pw);
            Gson gson = new Gson();
            String json = gson.toJson(data, DataModel.class);
            Log.v("json",json);
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://c01n0nuxg9.execute-api.ap-northeast-2.amazonaws.com/dev/adduser").newBuilder();
            String url = urlBuilder.build().toString();
            Request req = new Request.Builder().url(url)
                    .post(RequestBody.create(MediaType.parse("application/json"),json)).build();
            Log.v("req", String.valueOf(req));
            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String myResponse = response.body().string();
                    Log.v("respose",myResponse);
                    Gson gson = new GsonBuilder().create();

                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(myResponse.contains("false")){
                                Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Register success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
                }
            });
        });
    }
}

