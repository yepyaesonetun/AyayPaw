package com.immortal.ayaypaw;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.immortal.ayaypaw.Adapter.CustomVillageNameArrauAdapter;

/**
 * Created by ACER on 16/12/2015.
 */
public class ServiceType extends AppCompatActivity {
    static String detail="";
    static String[] datat = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    static String[] PhoneNumbert;
    static int doing;
    int healthlength = 1;


    public static String[] vName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofsociety);

        CareService cs = new CareService();
        getSupportActionBar().setTitle(cs.tStr);

        final ListView lst_mcare = (ListView) findViewById(R.id.lst_of_society);
        lst_mcare.setAdapter(new CustomVillageNameArrauAdapter(ServiceType.this,vName));
        lst_mcare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String n = (String) lst_mcare.getItemAtPosition(position);
                n.trim();
                BLTeamDetail blt=new BLTeamDetail();
                blt.title = n;

                SocietyDetail(n);
                if(doing==1) {
                    BLTeamDetail bi = new BLTeamDetail();

                    bi.detail = detail;
                    Log.i("Detail;", detail);
                    //   Log.i("Detail;", PhoneNumbert.length + "");

                    bi.setLength(PhoneNumbert.length);
                    for (int i = 0; i < PhoneNumbert.length; i++) {
                        bi.phno[i] = PhoneNumbert[i];
                    }
                    startActivity(new Intent(ServiceType.this, BLTeamDetail.class));
                }

            }
        });
    }
    public void SetLength(int Size){
        vName=new String[Size];
    }
    public void SplitListPString(String ss) {
        datat = ss.split(";");
        Log.i("  ", datat[0]);
    }


    public void SocietyDetail(String name){

        DatabaseHandler dbhandler = new DatabaseHandler(getApplicationContext());
        Cursor cursor = dbhandler.getDetailfirst(name);
        Log.i("name", name);

        Log.i("ltable", cursor.getCount() + "");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            doing=1;

            detail=cursor.getString(cursor.getColumnIndex("Detail"));
            String pno=cursor.getString(cursor.getColumnIndex("Phoneno"));
            Log.i("Detail",detail);
            Log.i("Pnone",pno);
            try{
                SplitListPString(pno);
            }catch(Exception e){
                datat[0]=pno;
                Log.i(" HEalth 0 ",pno);
            }

            for(int II=0;II<datat.length;II++){
                String s=datat[II];
                if(!(s.equals("0"))){
                    healthlength=II+1;
                    Log.i("  Heakth Lrngth",healthlength+"");
                }
            }


            PhoneNumbert=new String[healthlength];
            Log.i("PhoneLe;", PhoneNumbert.length + "");
            for(int l=0;l<healthlength;l++){
                PhoneNumbert[l]=datat[l];
                Log.i( "PhoneNubm;",PhoneNumbert[l]);

            }
        }else doing=0;


    }

}
