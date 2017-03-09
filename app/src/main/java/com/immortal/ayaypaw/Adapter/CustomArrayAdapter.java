package com.immortal.ayaypaw.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.immortal.ayaypaw.R;

/**
 * Created by ACER on 10/12/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    private Typeface tf;

    public CustomArrayAdapter(Context context, String[] values) {
        super(context, R.layout.custom_list_main_activity, values);

        this.context = context;
        this.values = values;
        this.tf = Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context

                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.custom_list_main_activity, parent, false);


        TextView textView = (TextView) rowView.findViewById(R.id.textView_main_list);
        textView.setTypeface(tf);

        ImageView imgv = (ImageView) rowView.findViewById(R.id.imageView_main_list);


        if (position == 0) {
            imgv.setBackgroundResource(R.drawable.villages_png);
        } else if (position == 1) {
            imgv.setBackgroundResource(R.drawable.helping_society);

        } else if (position == 2) {
            imgv.setBackgroundResource(R.drawable.health_care_services);
        } else if (position == 3) {
            imgv.setBackgroundResource(R.drawable.nearest_health_center_location);
        } else {
            imgv.setBackgroundResource(android.R.drawable.ic_menu_search);
        }

        textView.setText(values[position]);


        return rowView;
    }
}
