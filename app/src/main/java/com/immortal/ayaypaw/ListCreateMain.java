package com.immortal.ayaypaw;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by ACER on 15/12/2015.
 */
public class ListCreateMain extends AppCompatActivity {

public static String title;
    static String detail="";
    static String[] datat = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    static String[] PhoneNumbert;
    static int doing;
    int healthlength = 1;
    public static String[] vName;
    public static String Str;
    ArrayAdapter<String> dataAdapter = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subtitle_list_activity);

        getSupportActionBar().setTitle(title);

        Str ="";

        final ListView lst = (ListView) findViewById(R.id.listView_sub_title_list);
        ListAdapter adapter = null;
        final DatabaseHandler dbhandler = new DatabaseHandler(ListCreateMain.this);

        Cursor cursor = dbhandler.HelpSociety();
        Log.i("Length ", cursor.getCount() + "");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int i = 0;
            vName = new String[cursor.getCount()];
            // fetch all data one by one
            //VillageName[0] =cursor.getString(cursor.getColumnIndex("VillageName"));

            do {
                vName[i] = cursor.getString(cursor.getColumnIndex("Name"));

               /* try {
                        old2 = NumberFormat.getInstance()
								.parse(price1[i].toString()).doubleValue();

					} catch (Exception e) {
					}*/

                i++;
            } while (cursor.moveToNext());
            Log.i("Length ", vName[0] + "");
        }
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, vName);
        // final EditText det = (EditText) findViewById(R.id.editText);

        lst.setAdapter(dataAdapter);

    //    lst.setAdapter(new CustomVillageNameArrauAdapter(this, vName));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) lst.getItemAtPosition(position);
                dbhandler.itemvalue = itemValue;
                BLTeamDetail blt=new BLTeamDetail();
                blt.title = itemValue;
                // Show Alert

                String n = (String) lst.getItemAtPosition(position);
                n.trim();

                SocietyDetail(n);
                if (doing == 1) {
                    BLTeamDetail bi = new BLTeamDetail();

                    bi.detail = detail;
                    Log.i("Detail;", detail);
                    //   Log.i("Detail;", PhoneNumbert.length + "");

                    bi.setLength(PhoneNumbert.length);
                    for (int i = 0; i < PhoneNumbert.length; i++) {
                        bi.phno[i] = PhoneNumbert[i];
                    }
                    startActivity(new Intent(ListCreateMain.this, BLTeamDetail.class));
                }

                Str = itemValue;

//                Toast.makeText(getApplicationContext(),
//                        "Position :" + itemPosition + "  ListItem : " + itemValue,
//                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                // perform query here

                return true;

            }


            @Override

            public boolean onQueryTextChange(String newText) {
                ListCreateMain.this.dataAdapter.getFilter().filter(searchView.getQuery().toString());
                return false;

            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
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
