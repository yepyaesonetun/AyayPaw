package com.immortal.ayaypaw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.immortal.ayaypaw.Adapter.CustomBltArrayAdapter;

/**
 * Created by ACER on 15/12/2015.
 */
public class BLTeamDetail extends AppCompatActivity {
    public static String detail;
   public static  String title;
  public static String[] phno;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_team_detail);


        getSupportActionBar().setTitle(title);

//Button btn=(Button)findViewById(R.id.button_blt_ph_call);
        TextView tvdetail=(TextView)findViewById(R.id.textView_bl_team_data);
        Log.i("Now det", detail + "");

        tvdetail.setText(detail);
        ListView lst=(ListView)findViewById(R.id.lst_blt_phno);
        lst.setAdapter(new CustomBltArrayAdapter(this, phno));
    }
    public void setLength(int size){
        phno=new String[size];
    }
}
