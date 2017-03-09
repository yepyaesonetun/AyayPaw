package com.immortal.ayaypaw.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.immortal.ayaypaw.R;

/**
 * Created by ACER on 15/12/2015.
 */
public class CustomVillageInfoArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] name;
    private final String[] ph;
    private Typeface tf;


    public CustomVillageInfoArrayAdapter(Context context, String[] name, String[] ph) {
        super(context, R.layout.format_txt_tab_1_to_3, name);
        this.context = context;
        this.name = name;
        this.ph = ph;
        this.tf = Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.format_txt_tab_1_to_3, parent, false);
        TextView txt_name = (TextView) rowView.findViewById(R.id.textView_data);
        TextView txt_phno = (TextView) rowView.findViewById(R.id.textView_ph_no);

        Button btn_ph_call = (Button) rowView.findViewById(R.id.button_ph_call);
        btn_ph_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Call..............", "S");
                Toast.makeText(getContext(), "I'm Calling to " + ph[position], Toast.LENGTH_SHORT).show();

                if (v.getContext().checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntet = new Intent(Intent.ACTION_CALL);
                    callIntet.setData(Uri.parse("tel: " + ph[position]));
                    callIntet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(callIntet);
                }
            }
        });


        txt_name.setTypeface(tf);
        // txt_phno.setTypeface(tf);
        Log.i("Ph  ", ph[position]);
if(ph[position].equals("0")){
    btn_ph_call.setVisibility(View.GONE);
    txt_phno.setText("");
}else{
    btn_ph_call.setEnabled(true);
    txt_phno.setText(ph[position]);
}

        txt_name.setText(name[position]);


        return rowView;
    }


}
