package com.immortal.ayaypaw;

/**
 * Created by ACER on 12/12/2015.
 */

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.immortal.ayaypaw.Adapter.CustomHelpSocietyAdapter;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab4 extends Fragment {

    static String[] vName;
    static String detail="";



    static String[] data = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    static String[] datat = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    int healthlength = 1;
    public static String itemValue,getStr;
    static String[] PhoneNumbert;
  static int doing;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_4, container, false);
doing=0;
        getStr="";
//        TextView t = (TextView)v.findViewById(R.id.textView_t4);
//        t.setTypeface(f);

        HelpSociety();

        Typeface f = Typeface.createFromAsset(getActivity().getAssets(), "zawgyi.ttf");
//        TextView t = (TextView)v.findViewById(R.id.textView_t3);
//        t.setTypeface(f);
        final ListView lv = (ListView) v.findViewById(R.id.lst_tab_4);
        //  Log.i("Ph no 0  ",PhoneNumber[0]);
        lv.setAdapter(new CustomHelpSocietyAdapter(getActivity(), vName));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemValue = (String) lv.getItemAtPosition(position);
              BLTeamDetail blt=new BLTeamDetail();
                blt.title = itemValue;
              //  blt.setTitle(itemValue);

                Toast.makeText(getActivity(),
                        itemValue,
                        Toast.LENGTH_LONG).show();
                Log.i("Doing ",doing+"");

                    String n = (String) lv.getItemAtPosition(position);
                    n.trim();

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
                    startActivity(new Intent(getActivity(), BLTeamDetail.class));
                }
//                startActivity(new Intent(getActivity(),ListCreate.class));
            }

        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        "Long Click",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return v;
    }

    public void HelpSociety() {
        DatabaseHandler dbhandler = new DatabaseHandler(getActivity());
        Cursor cursor = dbhandler.VillageInfomation();
        //	 Cursor cursor = dbhandler.getVillageAdmin();

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            int i = 0;
            //	vName=new String[cursor.getCount()];
            // fetch all data one by one
            //VillageName[0] =cursor.getString(cursor.getColumnIndex("VillageName"));
            String ss = cursor.getString(cursor.getColumnIndex("Society"));
            try {
                SplitListString(ss);
            } catch (Exception e) {
                data[0] = ss;
                Log.i(" HEalth 0 ", ss);
            }


            for (int II = 0; II < data.length; II++) {
                String s = data[II];
                if (!(s.equals("0"))) {
                    healthlength = II + 1;
                    Log.i("  Heakth Lrngth", healthlength + "");
                }
            }

            vName = new String[healthlength];

            for (int l = 0; l < healthlength; l++) {
                vName[l] = data[l].trim();
                // PhoneNumber[l]="";

            }
        }

    }

    public void SplitListString(String ss) {
        data = ss.split(";");
        Log.i("  ", data[0]);
    }
    public void SplitListPString(String ss) {
        datat = ss.split(";");
        Log.i("  ", datat[0]);
    }


    public void SocietyDetail(String name){

        DatabaseHandler dbhandler = new DatabaseHandler(getContext());
        Cursor cursor = dbhandler.getDetail(name);
        Log.i("name",name);

        Log.i("ltable",cursor.getCount()+"");
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
            Log.i( "PhoneLe;",PhoneNumbert.length+"");
            for(int l=0;l<healthlength;l++){
               PhoneNumbert[l]=datat[l];
                Log.i( "PhoneNubm;",PhoneNumbert[l]);

            }
        }else doing=0;


    }


}