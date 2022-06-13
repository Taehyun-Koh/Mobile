package edu.skku.map.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game extends AppCompatActivity {
    TextView crr_level;
    TextView crr_lives;
    GridView gv;
    ConstraintLayout constraintLayout;
    Button start_btn;
    TextView cnt;
    ImageView heart_img;
    private ArrayList<img> items;
    Random ran = new Random();
    final GridViewAdapter[] gridViewAdapter = new GridViewAdapter[1];
    public static final String USER_RECORD = "LEVEL 20";
    public static final String USER_NAME = "id";
    public static final String KAKAO_NAME = "kakao";

    static Integer[] random2(int size, int answer_num) {
        Integer[] level2 = new Integer[answer_num];
        // 번호 생성
        for (int i = 0; i < answer_num; i++) {
            level2[i] = (int) (Math.random() * size);
            // 중복 번호 제거
            for (int j = 0; j < i; j++) {
                if (level2[i] == level2[j]) {
                    i--;
                    break;
                }
            }
        }
        return level2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent intent_get = getIntent();
        String user = intent_get.getStringExtra(GameMainActivity.USER_NAME);
        String kakao_user = intent_get.getStringExtra(GameMainActivity.KAKAO_NAME);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        start_btn = findViewById(R.id.start_btn);
        crr_level = findViewById(R.id.tv_level);
        crr_lives = findViewById(R.id.tv_live);
        cnt = findViewById(R.id.tv_count);
        gv = findViewById(R.id.gridView);
        constraintLayout = findViewById(R.id.mainlayout);
        heart_img = findViewById(R.id.img_heart);

        items = new ArrayList<img>();
        final int[] level = {1};
        final int[] lives = {3};
//        final int[] size = {0};
//        final int[] count_ans = {0};
        final boolean[] pass_1 = {false};
        final boolean[] pass_2 = {false};
        final boolean[] pass_3 = {false};
        final boolean[] pass_4 = {false};
        final boolean[] pass_5 = {false};
        final boolean[] pass_6 = {false};
        final boolean[] pass_7 = {false};
        final boolean[] pass_8 = {false};
        final boolean[] pass_9 = {false};
        final boolean[] pass_10 = {false};
        final boolean[] pass_11 = {false};
        final boolean[] pass_12 = {false};
        final boolean[] pass_13 = {false};
        final boolean[] pass_14 = {false};
        final boolean[] pass_15 = {false};
        final boolean[] pass_16 = {false};



        start_btn.setOnClickListener(view -> {
            heart_img.setVisibility(View.VISIBLE);
            if(lives[0] == 0){
                Log.v("마지막","가자");
                Intent intent = new Intent(this,End.class);
                intent.putExtra(USER_RECORD,"Level"+level[0]);
                if(user == null){
                    intent.putExtra(KAKAO_NAME,kakao_user);
                }
                else if(kakao_user == null){
                    intent.putExtra(USER_NAME,user);
                }
                startActivity(intent);
            }

            if(pass_15[0] == true){
                Intent intent = new Intent(this,End.class);
                intent.putExtra(USER_RECORD,"Level"+level[0]);
                if(user == null){
                    intent.putExtra(KAKAO_NAME,kakao_user);
                }
                else if(kakao_user == null){
                    intent.putExtra(USER_NAME,user);
                }
                startActivity(intent);

            }

            if (level[0] == 1) {
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean pass_0 = false;
                final boolean[] start = {false};
                Integer[] level1_anss = new Integer[3];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {3};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(3);
                //정답 인덱스 생성
                level1_anss = random2(9, 3);
                Arrays.sort(level1_anss);

                for (int i = 0; i < 9; i++) {
                    items.add(new img(metrics, 3, false, false));
                }

                pass_0 = true;
                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finalLevel1_anss = level1_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 9; i++) {
                            int finalI = i;
                            if (Arrays.stream(finalLevel1_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 1000);
                boolean finalPass_ = pass_0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 9; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 1 && finalPass_ == true) {

                            final int[] chance = {3};
                            List<Integer> user_ans = new ArrayList<Integer>(3);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {


                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }

                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);//클릭못하게
                                        Log.v("여기", "들어");
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);

                                        if (Arrays.equals(array, finalLevel1_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            Log.v("level", String.valueOf(level[0]));
                                            level[0] += 1;
                                            pass_1[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            lives[0] -= 1;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            crr_lives.setText("X  " + lives[0]);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 1500);
            }
            else if (level[0] == 2){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level2_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {4};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(3);
                //정답 인덱스 생성
                level2_anss = random2(9, 4);
                Arrays.sort(level2_anss);

                for (int i = 0; i < 9; i++) {
                    items.add(new img(metrics, 3, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel2_anss = level2_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 9; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel2_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 9; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 2 && pass_1[0] == true) {
                            final int[] chance = {4};
                            List<Integer> user_ans = new ArrayList<Integer>(4);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Log.v("여기", "들어");
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        for (int i = 0; i < 3; i++) {
                                            Log.v("hissssssssssssssssssssssss", String.valueOf(finallevel2_anss[i]));
                                            Log.v("hisssssssssssssssss", String.valueOf(array[i]));
                                        }
                                        if (Arrays.equals(array, finallevel2_anss)) {
                                            Log.v("level", String.valueOf(level[0]));
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_2[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 2700);
            }
            else if (level[0] == 3){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level3_anss = new Integer[5];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {5};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(4);
                //정답 인덱스 생성
                level3_anss = random2(16, 5);
                Arrays.sort(level3_anss);

                for (int i = 0; i < 16; i++) {
                    items.add(new img(metrics, 4, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel3_anss = level3_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel3_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {

                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();

                        }
                        if (level[0] == 3 && pass_2[0] == true) {
                            final int[] chance = {5};
                            List<Integer> user_ans = new ArrayList<Integer>(5);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Log.v("여기", "들어");
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        for (int i = 0; i < 5; i++) {
                                            Log.v("hissssssssssssssssssssssss", String.valueOf(finallevel3_anss[i]));
                                            Log.v("hisssssssssssssssss", String.valueOf(array[i]));
                                        }
                                        if (Arrays.equals(array, finallevel3_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            Log.v("level", String.valueOf(level[0]));
                                            level[0] += 1;
                                            pass_3[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }

                    }
                }, 2800);


            }
            else if (level[0] == 4){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level4_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {6};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(4);
                //정답 인덱스 생성
                level4_anss = random2(16, 6);
                Arrays.sort(level4_anss);

                for (int i = 0; i < 16; i++) {
                    items.add(new img(metrics, 4, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel4_anss = level4_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel4_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 4 && pass_3[0] == true) {
                            final int[] chance = {6};
                            List<Integer> user_ans = new ArrayList<Integer>(6);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel4_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_4[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 2800);

            }//else if
            else if (level[0] == 5){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level5_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {7};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(4);
                //정답 인덱스 생성
                level5_anss = random2(16, 7);
                Arrays.sort(level5_anss);

                for (int i = 0; i < 16; i++) {
                    items.add(new img(metrics, 4, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel5_anss = level5_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel5_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 16; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 5 && pass_4[0] == true) {
                            final int[] chance = {7};
                            List<Integer> user_ans = new ArrayList<Integer>(7);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel5_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_5[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 2800);
            }//else if
            else if (level[0] == 6){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level6_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {8};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(5);
                //정답 인덱스 생성
                level6_anss = random2(25, 8);
                Arrays.sort(level6_anss);

                for (int i = 0; i < 25; i++) {
                    items.add(new img(metrics, 5, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel6_anss = level6_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel6_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 6 && pass_5[0] == true) {
                            final int[] chance = {8};
                            List<Integer> user_ans = new ArrayList<Integer>(8);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel6_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_6[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 2800);

            }//else if
            else if (level[0] == 7){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level7_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {9};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(5);
                //정답 인덱스 생성
                level7_anss = random2(25, 9);
                Arrays.sort(level7_anss);

                for (int i = 0; i < 25; i++) {
                    items.add(new img(metrics, 5, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel7_anss = level7_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel7_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 7 && pass_6[0] == true) {
                            final int[] chance = {9};
                            List<Integer> user_ans = new ArrayList<Integer>(9);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel7_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_7[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 2900);
            }//else if
            else if (level[0] == 8){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level8_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {10};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(5);
                //정답 인덱스 생성
                level8_anss = random2(25, 10);
                Arrays.sort(level8_anss);

                for (int i = 0; i < 25; i++) {
                    items.add(new img(metrics, 5, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel8_anss = level8_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel8_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 8 && pass_7[0] == true) {
                            final int[] chance = {10};
                            List<Integer> user_ans = new ArrayList<Integer>(10);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel8_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_8[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3200);
            }//else if
            else if (level[0] == 9){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level9_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {11};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(6);
                //정답 인덱스 생성
                level9_anss = random2(36, 11);
                Arrays.sort(level9_anss);

                for (int i = 0; i < 36; i++) {
                    items.add(new img(metrics, 6, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel9_anss = level9_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel9_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 9 && pass_8[0] == true) {
                            final int[] chance = {11};
                            List<Integer> user_ans = new ArrayList<Integer>(11);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel9_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            level[0] += 1;
                                            pass_9[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3200);

            }//else if
            else if (level[0] == 10){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level10_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {12};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(6);
                //정답 인덱스 생성
                level10_anss = random2(36, 12);
                Arrays.sort(level10_anss);

                for (int i = 0; i < 36; i++) {
                    items.add(new img(metrics, 6, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel10_anss = level10_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel10_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 10 && pass_9[0] == true) {
                            final int[] chance = {12};
                            List<Integer> user_ans = new ArrayList<Integer>(12);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel10_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            level[0] += 1;
                                            pass_10[0] = true;
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3400);
            }//else if
            //else if
            else if (level[0] == 11){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level11_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {13};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(6);
                //정답 인덱스 생성
                level11_anss = random2(36, 13);
                Arrays.sort(level11_anss);

                for (int i = 0; i < 36; i++) {
                    items.add(new img(metrics, 6, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel11_anss = level11_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel11_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 11 && pass_10[0] == true) {
                            final int[] chance = {13};
                            List<Integer> user_ans = new ArrayList<Integer>(13);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel11_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            level[0] += 1;
                                            pass_11[0] = true;
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3400);

            }//else if
            //else if
            else if (level[0] == 12){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level12_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {14};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(6);
                //정답 인덱스 생성
                level12_anss = random2(36, 14);
                Arrays.sort(level12_anss);

                for (int i = 0; i < 36; i++) {
                    items.add(new img(metrics, 6, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel12_anss = level12_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel12_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 12 && pass_11[0] == true) {
                            final int[] chance = {14};
                            List<Integer> user_ans = new ArrayList<Integer>(14);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel12_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            level[0] += 1;
                                            pass_12[0] = true;
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3600);

            }
            else if (level[0] == 13){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level13_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {15};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(6);
                //정답 인덱스 생성
                level13_anss = random2(36, 15);
                Arrays.sort(level13_anss);

                for (int i = 0; i < 36; i++) {
                    items.add(new img(metrics, 6, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel13_anss = level13_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel13_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 36; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 13 && pass_12[0] == true) {
                            final int[] chance = {15};
                            List<Integer> user_ans = new ArrayList<Integer>(15);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel13_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            level[0] += 1;
                                            pass_13[0] = true;
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3600);

            }//elfi
            else if (level[0] == 14){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level14_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {16};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(7);
                //정답 인덱스 생성
                level14_anss = random2(49, 16);
                Arrays.sort(level14_anss);

                for (int i = 0; i < 49; i++) {
                    items.add(new img(metrics, 7, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel14_anss = level14_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 49; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel14_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 49; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 14 && pass_13[0] == true) {
                            final int[] chance = {16};
                            List<Integer> user_ans = new ArrayList<Integer>(16);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel14_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            level[0] += 1;
                                            pass_14[0] = true;
                                            start_btn.setText("정답! 다음레벨");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 3700);
            }//elfi
            else if (level[0] == 15){
                items.clear();
                start_btn.setVisibility(View.INVISIBLE);
                boolean start = false;
                Integer[] level15_anss = new Integer[4];

                constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                crr_level.setText("Level " + level[0]);
                crr_lives.setText("X  " + lives[0]);
                start_btn.setText("...");
                final int[] count = {17};
                cnt.setText("남은 타일 수 : "+ count[0]);

                gv.setNumColumns(7);
                //정답 인덱스 생성
                level15_anss = random2(49, 17);
                Arrays.sort(level15_anss);

                for (int i = 0; i < 49; i++) {
                    items.add(new img(metrics, 7, false, false));
                }

                gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);
                gv.setAdapter(gridViewAdapter[0]);
                Integer[] finallevel15_anss = level15_anss;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 49; i++) {
                            int finalI = i;
                            if (Arrays.stream(finallevel15_anss).anyMatch(x -> x == finalI)) {
                                img crr_cell = items.get(i);
                                crr_cell.setAnswer(true);
                                gridViewAdapter[0].notifyDataSetChanged();
                            }
                        }
                    }
                }, 2000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 49; i++) {
                            img all_cell = items.get(i);
                            all_cell.setAnswer(false);
                            gridViewAdapter[0].notifyDataSetChanged();
                        }
                        if (level[0] == 15 && pass_14[0] == true) {
                            final int[] chance = {17};
                            List<Integer> user_ans = new ArrayList<Integer>(17);
                            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {

                                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                                    if(select_img.isAnswer() == false){
                                        int n = (int) gridViewAdapter[0].getItemId(a_position);
                                        Log.v("TAG",n+"번째 눌림");
                                        user_ans.add(n);
                                        select_img.setAnswer(true);
                                        gridViewAdapter[0].notifyDataSetChanged();
                                        chance[0] -= 1;
                                        count[0] -= 1;
                                        cnt.setText("남은 타일 수 : "+ count[0]);
                                    }
                                    if (chance[0] == 0) {
                                        gv.setOnItemClickListener(null);
                                        Integer[] array = user_ans.toArray(new Integer[user_ans.size()]);
                                        Arrays.sort(array);
                                        if (Arrays.equals(array, finallevel15_anss)) {
                                            start_btn.setBackgroundColor(Color.parseColor("#238946"));

                                            level[0] += 1;
                                            pass_15[0] = true;
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("CLEAR");
                                        } else {
                                            start_btn.setBackgroundColor(Color.parseColor("#ff6f6e"));
                                            start_btn.setVisibility(View.VISIBLE);
                                            start_btn.setText("Retry");
                                            lives[0] -= 1;
                                            crr_lives.setText("X  " + lives[0]);
                                            if(lives[0] == 0){
                                                start_btn.setText("나가기");
                                                Toast.makeText(getApplicationContext(),"GameOver!!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }, 4000);

            }//elfi
        });
    }
}



