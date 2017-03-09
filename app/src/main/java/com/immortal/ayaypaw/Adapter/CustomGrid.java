package com.immortal.ayaypaw.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.immortal.ayaypaw.R;

/**
 * Created by ACER on 22/12/2015.
 */
public class CustomGrid extends BaseAdapter {

    private Context mContext;
    private final String[] name;
    private final int[] Imageid;

    public CustomGrid(Context mContext, String[] name, int[] imageid) {
        this.mContext = mContext;
        this.name = name;
        Imageid = imageid;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View grid;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single,null);
            TextView textView = (TextView)grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);

            textView.setText(name[position]);
            imageView.setImageResource(Imageid[position]);
        }else {
            grid = (View) convertView;
        }
        return grid;
    }
}
