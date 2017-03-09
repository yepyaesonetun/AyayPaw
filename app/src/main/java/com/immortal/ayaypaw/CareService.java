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
 * Created by ACER on 13/12/2015.
 */
public class CareService extends AppCompatActivity {
    final static String[] mCare_title = {"ေသြး", "နာေရး", "ေအာက္စီဂ်င္", "က်န္းမာေရး ႀကိဳ / ပို႔ယာဥ္"};

    static String[] vName;
    public static String tStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_serv_main);

        tStr = "";

        final ListView lst_mcare = (ListView) findViewById(R.id.lst_care_serv_main);
        lst_mcare.setAdapter(new CustomVillageNameArrauAdapter(CareService.this, mCare_title));
        lst_mcare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                // ListView Clicked item value
                String type="";
                String itemValue = (String) lst_mcare.getItemAtPosition(position);
if(itemValue.equals("ေသြး")){
    type="Blood";
}else if(itemValue.equals("ေအာက္စီဂ်င္")){
    type="Oxygen";
}else if(itemValue.equals("က်န္းမာေရး ႀကိဳ / ပို႔ယာဥ္")){
    type="Ambulance";
}else if(itemValue.equals("နာေရး")){
    type="Naryay";
}
                SocietySupport(type.trim());
                ServiceType st=new ServiceType();
                Log.i("lenget of Vname",vName.length+"");
                st.SetLength(vName.length);
                for(int i=0;i<vName.length;i++){
                   st.vName[i]=vName[i];
                }

                tStr = itemValue;

//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue,
//                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(CareService.this, ServiceType.class));
            }
        });
    }


    public void SocietySupport(String type){


        DatabaseHandler dbhandler = new DatabaseHandler(CareService.this);
        Cursor cursor = dbhandler.getSupport(type.trim());
        //	 Cursor cursor = dbhandler.getVillageAdmin();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            int i = 0;
            //	vName=new String[cursor.getCount()];
            // fetch all data one by one
            //VillageName[0] =cursor.getString(cursor.getColumnIndex("VillageName"));
            Log.i("type ",type+" Length="+cursor.getCount());
            vName=new String[cursor.getCount()];
            do {
              vName[i] = cursor.getString(cursor.getColumnIndex("Name"));
                Log.i("Support  ", vName[i]);

                i++;
            } while (cursor.moveToNext());
        }


    }




}
