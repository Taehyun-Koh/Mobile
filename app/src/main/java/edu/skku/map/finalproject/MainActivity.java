package edu.skku.map.finalproject;

import android.app.Presentation;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ResourceCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements Contract.LoginView {

    private Button login_btn;
    private Button register_btn;
    private LoginPresenter presenter;

    public static final String USER_NAME = "id";
    public static final String KAKAO_NAME = "kakao";
    public String usr;
    public String kakao_usr;
    EditText login_id;
    EditText login_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new LoginPresenter(this);


        login_btn = findViewById(R.id.login_button);
        register_btn = findViewById(R.id.register_button);
        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_password);
        login_btn.setOnClickListener(view -> {
            String id = login_id.getText().toString();
            String pw = login_pw.getText().toString();
            usr = id;
            OkHttpClient client = new OkHttpClient();
            DataModel data = new DataModel();
            data.setName(id);
            data.setPasswd(pw);
            Gson gson = new Gson();
            String json = gson.toJson(data, DataModel.class);
            Log.v("json", json);
            HttpUrl.Builder urlBuilder = HttpUrl.parse("서버주소").newBuilder();
            String url = urlBuilder.build().toString();
            Request req = new Request.Builder().url(url)
                    .post(RequestBody.create(MediaType.parse("application/json"), json)).build();
            Log.v("req", String.valueOf(req));
            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String myResponse = response.body().string();
                    Log.v("respose", myResponse);
                    Gson gson = new GsonBuilder().create();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LoginModel loginModel = new LoginModel(id, pw, myResponse);
                            presenter.start(loginModel);
                        }
                    });
                }
            });

        });
        register_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        ImageButton kakao_login_button = (ImageButton)findViewById(R.id.kakao_login_button);
        kakao_login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(MainActivity.this)){
                    login();

                }
                else{
                    accountLogin();
                }
            }
        });

    }
    @Override
    public void onSuccess() {
        Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameMainActivity.class);
        intent.putExtra(USER_NAME, usr);
        startActivity(intent);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void login(){
        String TAG = "login()";
        UserApiClient.getInstance().loginWithKakaoTalk(MainActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                getUserInfo();
            }
            return null;
        });
    }

    public void accountLogin(){
        String TAG = "accountLogin()";
        UserApiClient.getInstance().loginWithKakaoAccount(MainActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                getUserInfo();

            }
            return null;
        });
    }
    public void getUserInfo(){
        String TAG = "getUserInfo()";
        UserApiClient.getInstance().me((user, meError) -> {
            if (meError != null) {
                Log.e(TAG, "사용자 정보 요청 실패", meError);
            } else {
                System.out.println("로그인 완료");
                Log.i(TAG, user.toString());
                {
                    Log.i(TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: "+user.getId() +
                            "\n이메일: "+user.getKakaoAccount().getEmail());
                }
                String user_tmp;
                user_tmp = user.toString();
                String name_arr[] = user_tmp.split("nickname=");
                String name_arr2[] = name_arr[1].split(", profile_image");
                kakao_usr = name_arr2[0];
                Log.v("kakaouser",kakao_usr);
                Account user1 = user.getKakaoAccount();
                System.out.println("사용자 계정" + user1);
                Toast.makeText(this, "Login success with kakao!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GameMainActivity.class);
                intent.putExtra(KAKAO_NAME, kakao_usr);
                startActivity(intent);
            }
            return null;
        });
    }
}