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
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ACER on 12/12/2015.
 */
public class ListCreate extends AppCompatActivity {
    static final String[] MAIN_TITLE = new String[]{"ေက်း႐ြာမ်ား", "က်န္းမာေရးႏွင့္လူမႈကူညီေရးအသင္းမ်ား", "က်န္းမာေရး ၀န္ေဆာင္မႈမ်ား", "အနီးဆံုးက်န္းမာေရး ၀န္ေဆာင္မႈမ်ား", "႐ွာေဖြရန္"};
    ArrayAdapter<String> dataAdapter = null;
  public static String title;
    public static String[] vName;
    public static String villageName;
     ListView lst;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subtitle_list_activity);

        MainActivity ma = new MainActivity();

//        getSupportActionBar().setTitle(title);

        villageName = "";


         lst = (ListView) findViewById(R.id.listView_sub_title_list);

        final DatabaseHandler dbhandler = new DatabaseHandler(ListCreate.this);

        if (ma.index == 0) {


            Cursor cursor = dbhandler.getAllContacts();
            Log.i("Length ", cursor.getCount() + "");
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                int i = 0;
                vName = new String[cursor.getCount()];
                // fetch all data one by one
                //VillageName[0] =cursor.getString(cursor.getColumnIndex("VillageName"));

                do {
                    vName[i] = cursor.getString(cursor.getColumnIndex("VillageName"));
                    vName[i].trim();
               /* try {
                        old2 = NumberFormat.getInstance()
								.parse(price1[i].toString()).doubleValue();

					} catch (Exception e) {
					}*/

                    i++;
                } while (cursor.moveToNext());
                Log.i("Length ", vName[0] + "");
            }

        }



        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, vName);
       // final EditText det = (EditText) findViewById(R.id.editText);

        lst.setAdapter(dataAdapter);

//        det.addTextChangedListener(new TextWatcher() {
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                BeanConvertor.this.dataAdapter.getFilter().filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });



     //   lst.setAdapter(new CustomVillageNameArrauAdapter(this, vName));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) lst.getItemAtPosition(position);
                dbhandler.itemvalue = itemValue;
                // Show Alert

//                ListView lst_list = (ListView)findViewById(R.id.lst_tab_1);
//                ListAdapter lstadapter = null;
//                lst_list.setAdapter(new CustomArrayAdapter(ListCreate.this, MAIN_TITLE));


                Intent i = new Intent(ListCreate.this, TabActivity.class);
                startActivity(i);

                villageName = itemValue;
//
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

                return true;

            }


            @Override

            public boolean onQueryTextChange(String newText) {
                Toast.makeText(ListCreate.this, searchView.getQuery(), Toast.LENGTH_SHORT).show();
                if(searchView.getQuery()=="​​"){
                    Log.i("akljf;al",searchView.getQuery().toString());
                    String[] arr=new String[vName.length];
                    String[] name;
                    int j=0;
                    for(int i=0;i<vName.length;i++){
                        if(vName[i].startsWith("ေ")){
                            arr[j++]=vName[i];
                        }

                    }
                    name=new String[j];
                    for(int a=0;a<j;a++){
                        name[a]=arr[a];
                        Log.i("akljf;al",name[a].toString());
                    }
                    dataAdapter = new ArrayAdapter<String>(ListCreate.this,android.R.layout.simple_list_item_1, android.R.id.text1, vName);
                    // final EditText det = (EditText) findViewById(R.id.editText);

                    lst.setAdapter(dataAdapter);
                }else
                ListCreate.this.dataAdapter.getFilter().filter(searchView.getQuery().toString());

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

}
