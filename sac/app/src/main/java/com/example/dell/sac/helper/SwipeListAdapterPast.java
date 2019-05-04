package com.example.dell.sac.helper;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.dell.sac.R;
import com.example.dell.sac.app.MyApplication;

/**
 * Created by Ravi on 13/05/15.
 */
public class SwipeListAdapterPast extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MoviePast> movieList;
    ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

   private String[] bgColors;

    public SwipeListAdapterPast(Activity activity, List<MoviePast> movieList) {
        this.activity = activity;
        this.movieList = movieList;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int location) {
        return movieList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_rowpast, null);

        TextView serial = (TextView) convertView.findViewById(R.id.serial);
        TextView title = (TextView) convertView.findViewById(R.id.titlepast);

        serial.setText(String.valueOf(movieList.get(position).id));
        title.setText(movieList.get(position).title);

        String color = bgColors[position % bgColors.length];
        serial.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }

}
