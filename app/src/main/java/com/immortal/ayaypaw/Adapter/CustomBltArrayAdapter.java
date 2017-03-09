package com.immortal.ayaypaw.Adapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
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
 * Created by ACER on 16/12/2015.
 */
public class CustomBltArrayAdapter extends ArrayAdapter<String> {


    private final Context context;

    private final String[] ph;
    private Typeface tf;


    public CustomBltArrayAdapter(Context context, String[] ph) {
        super(context, R.layout.format_for_blt_phno, ph);
        this.context = context;
        this.ph = ph;
        this.tf = Typeface.createFromAsset(context.getAssets(), "zawgyi.ttf");

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.format_for_blt_phno, parent, false);

        TextView txt_phno = (TextView) rowView.findViewById(R.id.textView_blt_phno);

        Button btn_ph_call = (Button) rowView.findViewById(R.id.button_blt_ph_call);
        if (ph[position].equals("0")) {

            btn_ph_call.setVisibility(View.GONE);
            txt_phno.setText("");
        } else {
            txt_phno.setText(ph[position]);
        }
        btn_ph_call.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Log.i("Call..............", "S");
                Toast.makeText(getContext(), "I'm Calling to " + ph[position], Toast.LENGTH_SHORT).show();


                if (v.getContext().checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntet = new Intent(Intent.ACTION_CALL);
                    callIntet.setData(Uri.parse("tel: "+ph[position]));
                    callIntet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(callIntet);
                }



            }


        });




        return rowView;
    }


}
