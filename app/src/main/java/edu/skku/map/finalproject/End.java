package edu.skku.map.finalproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class End extends AppCompatActivity {
    TextView rank_tv;
    TextView user_tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endpage);

        rank_tv = findViewById(R.id.rank_tv);
        user_tv = findViewById(R.id.user_tv);
        Intent intent = getIntent();
        String record = intent.getStringExtra(Game.USER_RECORD);
        String user = intent.getStringExtra(Game.USER_NAME);
        String kakao_user = intent.getStringExtra(Game.KAKAO_NAME);

        if(user == null){
            user_tv.setText(kakao_user + "님의 Best Record");
        }
        else if(kakao_user == null){
            user_tv.setText(user + "님의 Best Record");
        }

        rank_tv.setText(record);


    }
}
