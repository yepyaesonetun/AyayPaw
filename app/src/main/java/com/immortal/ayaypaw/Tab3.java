package com.immortal.ayaypaw;

/**
 * Created by ACER on 12/12/2015.
 */

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.immortal.ayaypaw.Adapter.CustomVillageInfoArrayAdapter;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab3 extends Fragment {
    static String engnumber = "";
    static String realnumber;
    static String[] vName;
    static String[] Splitphno = {"0", "0"};
    static String[] PhoneNumber;
    static String[] convertphone = {"0", "0"};
    static String[] data = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    int healthlength = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3, container, false);


        VillageAdmin();

        Typeface f = Typeface.createFromAsset(getActivity().getAssets(), "zawgyi.ttf");
//        TextView t = (TextView)v.findViewById(R.id.textView_t3);
//        t.setTypeface(f);
        ListView lv = (ListView) v.findViewById(R.id.lst_tab_3);
        //  Log.i("Ph no 0  ",PhoneNumber[0]);
        lv.setAdapter(new CustomVillageInfoArrayAdapter(getActivity(), vName, PhoneNumber));
        //lv.setAdapter(new CustomArrayAdapter(getActivity(),vName));


//        t.setTypeface(f);
        return v;
    }

    public void SplitListString(String ss) {
        data = ss.split(";");
        Log.i("  ", data[0]);
    }

    public void GetPhoneNo(String ss) {
        Splitphno = ss.split(":");

        Log.i("  ", data[0]);
    }

    public void VillageAdmin() {
        DatabaseHandler dbhandler = new DatabaseHandler(getActivity());
        Cursor cursor = dbhandler.VillageInfomation();
        //	 Cursor cursor = dbhandler.getVillageAdmin();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int i = 0;
            //	vName=new String[cursor.getCount()];
            // fetch all data one by one
            //VillageName[0] =cursor.getString(cursor.getColumnIndex("VillageName"));
            String ss = cursor.getString(cursor.getColumnIndex("VillageAdmin"));
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
            String[] getname={"",""};
            vName = new String[healthlength];
            PhoneNumber = new String[healthlength];
            for (int l = 0; l < healthlength; l++) {
                try{
                    getname=data[l].split(":");
                    vName[l]=getname[0].trim();
                }catch(Exception e){
                    vName[l] = data[l];
                }

                PhoneNumber[l] = "0";
                try {
                    GetPhoneNo(data[0]);
                    ConvertPhoneNumber(Splitphno[1]);
                    PhoneNumber[l] = engnumber;
                    Log.i("Ph no  ", engnumber);
                } catch (Exception e) {
                    PhoneNumber[l] = "0";
                }

            }
        }


    }


    public void ConvertPhoneNumber(String number) {
        engnumber = "";
        char[] c;
        convertphone = number.split("-");
        realnumber = convertphone[0] + convertphone[1];
        realnumber.trim();
        Log.i("PH NO :", realnumber);
        c = new char[realnumber.length()];
        for (int i = 0; i < realnumber.length(); i++) {
            if (realnumber.charAt(i) == '၁') {

                c[i] = '1';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၂') {
                c[i] = '2';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၃') {
                c[i] = '3';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၄') {
                c[i] = '4';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၅') {
                c[i] = '5';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၆') {
                c[i] = '6';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၇') {
                c[i] = '7';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၈') {
                c[i] = '8';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else if (realnumber.charAt(i) == '၉') {
                c[i] = '9';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            } else {
                c[i] = '0';
                Log.i(" ", realnumber.charAt(i) + "  " + c[i]);
            }
            engnumber += c[i];
        }
        // engnumber=c.toString();

    }


}