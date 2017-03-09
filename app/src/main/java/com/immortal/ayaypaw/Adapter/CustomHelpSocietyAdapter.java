package com.immortal.ayaypaw.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.immortal.ayaypaw.R;

/**
 * Created by ACER on 15/12/2015.
 */
public class CustomHelpSocietyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] name;
    private Typeface tf;


    public CustomHelpSocietyAdapter(Context context, String[] name) {
        super(context, R.layout.format_txt_for_t4, name);
        this.context = context;
        this.name = name;
        this.tf = Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.format_txt_for_t4, parent, false);
        TextView txt_name = (TextView) rowView.findViewById(R.id.textView_data_for_tb_4);
        txt_name.setTypeface(tf);


        txt_name.setText(name[position]);

        return rowView;
    }


}
