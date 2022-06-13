package edu.skku.map.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameMainActivity extends AppCompatActivity {
    Button start_btn;
    public static final String USER_NAME = "visual";
    public static final String KAKAO_NAME = "kakao";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent_get = getIntent();
        String user = intent_get.getStringExtra(MainActivity.USER_NAME);
        String kako_user = intent_get.getStringExtra(MainActivity.KAKAO_NAME);
//        Log.v("GameMainActivity",user);
//        Log.v("GameMainActivity2",kako_user);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemain);
        start_btn = findViewById(R.id.buttonStart);
        start_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this,Game.class);
            if(user == null){
                intent.putExtra(KAKAO_NAME,kako_user);
            }
            else if(kako_user ==null){
                intent.putExtra(USER_NAME,user);
            }

            startActivity(intent);
        });
    }
}
