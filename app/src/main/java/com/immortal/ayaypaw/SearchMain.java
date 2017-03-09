package com.immortal.ayaypaw;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.immortal.ayaypaw.NearestLocation.LocationMainActivity;

/**
 * Created by ACER on 13/12/2015.
 */
public class SearchMain extends AppCompatActivity {

    private Typeface font;

    private Button btn_s_vill, btn_s_hellper, btn_s_serv, btn_s_nearest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main_activity);
/////////////////////Title Text Work Done...
//        getSupportActionBar().setTitle("Search.....");
///////////////////////////////////////////////////

        font = Typeface.createFromAsset(getAssets(), "zawgyi.ttf");

        btn_s_vill = (Button) findViewById(R.id.button_village_search);
        btn_s_hellper = (Button) findViewById(R.id.button_helper_search);
        btn_s_serv = (Button) findViewById(R.id.button_care_search);
        btn_s_nearest = (Button) findViewById(R.id.button_nearest_search);
        btn_s_vill.setTypeface(font);
        btn_s_hellper.setTypeface(font);
        btn_s_serv.setTypeface(font);
        btn_s_nearest.setTypeface(font);

        btn_s_vill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = new MainActivity();
                ma.index = 0;
                ListCreate lct=new ListCreate();
                lct.title =btn_s_vill.getText().toString();
                Intent intentVillageName = new Intent(SearchMain.this,
                        ListCreate.class);
                startActivity(intentVillageName);


            }
        });

        btn_s_hellper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = new MainActivity();
                ma.index = 1;
               ListCreateMain lctm=new ListCreateMain();
                lctm.title=btn_s_hellper.getText().toString();
                Intent intentHelper = new Intent(SearchMain.this, ListCreateMain.class);
                startActivity(intentHelper);

            }
        });

        btn_s_serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchMain.this, CareService.class));

            }
        });

        btn_s_nearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchMain.this, LocationMainActivity.class));
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button_village_search:
//                Toast.makeText(getApplicationContext(),"Village",Toast.LENGTH_LONG).show();
//                SnackBarShow(btn_s_vill.getText().toString());
//                break;
//            case R.id.button_helper_search:
//                SnackBarShow(btn_s_hellper.getText().toString());
//                break;
//            case R.id.button_care_search:
//                SnackBarShow(btn_s_serv.getText().toString());
//                break;
//            case R.id.button_nearest_search:
//                SnackBarShow(btn_s_nearest.getText().toString());
//                break;
//        }
//    }

    public void SnackBarShow(String c) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), c, Snackbar.LENGTH_LONG).setActionTextColor(Color.BLACK);
        View viewa = snack.getView();
        TextView tv = (TextView) viewa.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }

}
