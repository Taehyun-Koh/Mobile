package edu.skku.map.finalproject;
import static android.os.SystemClock.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

class img{
    public int size;
    public boolean answer = false;
    public boolean start;
    public DisplayMetrics metrics;


    public img(DisplayMetrics metrics, int size,boolean start,boolean answer){
        this.metrics = metrics;
        this.size = size;
        this.start = start;
        this.answer = answer;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
public class GridViewAdapter extends BaseAdapter{

    private ArrayList<img> items;
    private Context mContext;
    private Integer[] mThumIds = {
            R.drawable.lightgray,
            R.drawable.blackimg
    };

    public GridViewAdapter(Context mContext, ArrayList<img> items){
        this.mContext = mContext;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.cell, viewGroup, false);
        }

        final int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                350 / items.get(i).size, items.get(i).metrics));
//        Log.v("width", String.valueOf(width));
        final int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                350 / items.get(i).size, items.get(i).metrics));
//        Log.v("height", String.valueOf(height));
        ImageView iv = view.findViewById(R.id.imageView);

        if(items.get(i).answer == false){
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
            iv.setLayoutParams(params);
            iv.setPadding(10,10,10,10);
            iv.setImageResource(mThumIds[0]);
        }
        if(items.get(i).answer == true){
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
            iv.setLayoutParams(params);
            iv.setPadding(10,10,10,10);
            iv.setImageResource(mThumIds[1]);
        }
        return view;
    }
}
