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
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game2 extends AppCompatActivity {
    TextView crr_level;
    TextView crr_lives;
    GridView gv;
    ConstraintLayout constraintLayout;
    private ArrayList<img> items;
    Random ran = new Random();
    final GridViewAdapter[] gridViewAdapter = new GridViewAdapter[1];

    static List<Integer> random(int size, int answer_num){
        List<Integer> level1_ans2 = new ArrayList<Integer>();

        // 번호 생성
        for(int i=0; i<answer_num; i++) {
            level1_ans2.add((int) (Math.random() * size));
            // 중복 번호 제거
            for(int j=0; j<i; j++) {
                if(level1_ans2.get(i) == level1_ans2.get(j)) {
                    i--;
                    break;
                }
            }
        }
        return level1_ans2;
    }

    boolean sum(int a, int b) {

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        crr_level = findViewById(R.id.tv_level);
        crr_lives = findViewById(R.id.tv_live);
        gv = findViewById(R.id.gridView);
        constraintLayout = findViewById(R.id.mainlayout);
        items = new ArrayList<img>();
        int[] level1 = new int[9]; //3*3
        List<Integer> level1_ans = new ArrayList<Integer>();
        int[] level2 = new int[9]; //3*3
        int[] level3 = new int[16]; //4*4
        int[] level4 = new int[16]; //4*4
        int[] level5 = new int[16]; //4*4
        int[] level6 = new int[25]; //5*5
        int[] level7 = new int[25]; //5*5
        int[] level8 = new int[25]; //5*5

        boolean pass_0 = false;
        final boolean[] pass_1 = {false};
        boolean start = false;
        final int[] level = {2};
        Intent intent = getIntent();
//        String lives = intent.getStringExtra(String.valueOf(Game.LIVES));
        if (level[0] == 1) {
            crr_level.setText("Level "+ level[0]);
            crr_lives.setText("Lives "+ 2);
            gv.setNumColumns(4);
            //정답 인덱스 생성
            level1_ans = random(9, 4);
            for (int i = 0; i < 4; i++) {
                Log.v("hi", String.valueOf(level1_ans.get(i)));
            }
            for (int i = 0; i < 9; i++) {
                items.add(new img(metrics, 4, false, false));
            }

            pass_0 = true;

        }
        gridViewAdapter[0] = new GridViewAdapter(getApplicationContext(), items);

        gv.setAdapter(gridViewAdapter[0]);


        if (level[0] == 2 && pass_0 == true && start == false) {
            constraintLayout.setBackgroundColor(Color.parseColor("#2375c2"));
            List<Integer> finalLevel1_ans = level1_ans;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 9; i++) {
                        int finalI = i;
                        if (finalLevel1_ans.contains(i)) {
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
                        Log.v("why", "sl;dkfsldkf");
                        img all_cell = items.get(i);
                        all_cell.setAnswer(false);
                        gridViewAdapter[0].notifyDataSetChanged();
                    }
                }
            }, 3000);
            start = true;
        }

        if (level[0] == 2 && pass_0 == true && start == true) {
            final int[] chance = {4};
            List<Integer> user_ans = new ArrayList<Integer>();
            List<Integer> finalLevel1_ans1 = level1_ans;
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                    img select_img = (img) gridViewAdapter[0].getItem(a_position);
                    int n = (int) gridViewAdapter[0].getItemId(a_position);
                    user_ans.add(n);
//                        Toast.makeText(getApplicationContext(), "Selected cell : "+n, Toast.LENGTH_SHORT).show();
                    select_img.setAnswer(true);
                    gridViewAdapter[0].notifyDataSetChanged();

                    chance[0] -= 1;

                    if (chance[0] == 0) {
                        if (finalLevel1_ans1.containsAll(user_ans)) {
                            pass_1[0] = true;
                            level[0] += 1;
                        } else {
                            constraintLayout.setBackgroundColor(Color.RED);
                            pass_1[0] = true;
                            level[0] += 1;
                        }
                    }

                }
            });
        }



    }
}
