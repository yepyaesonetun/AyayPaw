package com.immortal.ayaypaw;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.immortal.ayaypaw.Adapter.CustomGrid;
import com.immortal.ayaypaw.NearestLocation.DataProvider;
import com.immortal.ayaypaw.NearestLocation.LocationMainActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GridView grid;
    public static int index;
    static final String[] MAIN_TITLE = new String[]{"ေက်း႐ြာမ်ား", "လူမႈကူညီေရးအသင္းမ်ား", "က်န္းမာေရး ၀န္ေဆာင္မႈမ်ား", "အနီးဆံုး၀န္ေဆာင္မႈမ်ား"};
    int[] imageId = {
            R.drawable.village_green_256,
            R.mipmap.helper_yellow,
            R.mipmap.health_care,
            R.mipmap.map_pointer
    };
    private Toolbar toolbar;
    public static String itemValue, getStr;
    ShowcaseView.Builder showCaseView;
    final int SHOWCASEVIEW_ID = 28;


    public static String doss = "NotOK";
    public static String doss1 = "NotOK";
    public static String doss2 = "NotOK";
    public static int[] Tid;
    public static String[] VillageName;
    public static String[] VillageAdmin;
    public static String[] VillageHealth;
    public static String[] Volunteer;
    public static String[] Society;
    public static String[] Group;


    public static int[] HTid;
    public static String[] hName;
    public static boolean[] Blood;
    public static boolean[] Oxygen;
    public static boolean[] Ambulunce;
    public static boolean[] Naryay;
    public static String[] AviName;
    public static String[] Phoneno;
    public static String[] Detail;

    public static int[] lid;
    public static String[] lname;
    public static String[] laddress;
    public static double[] latt;
    public static double[] longi;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//        }





        //  Parse.initialize(this, "7ORiDnFbGsNrGUn3wdtt6EqsM3tfsnhCR6rnPStL", "BpBc3xOCDIMSLBKe5jIIYl8hiA5raodtj2AQlKGK");

        ParseInstallation.getCurrentInstallation().saveInBackground();


        showShowCaseView();

        getStr = "";

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////////////////////////////////////////////////////////////////////////

        Log.i("DATABASE EXIST : ", "" + checkDataBase());
        if (!checkDataBase())
            copyDataBase();
        Log.i("CCCCCC", "dsafdsa");
        index = 0;
        //DatabaseHandler dbhandler = new DatabaseHandler(MainActivity.this);

        /////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "zawgyi.ttf");

//        final ListView lst = (ListView) findViewById(R.id.list_item);

        CustomGrid adapter = new CustomGrid(MainActivity.this, MAIN_TITLE, imageId);
        grid = (GridView) findViewById(R.id.gridView);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                itemValue = MAIN_TITLE[+position];
                //Toast.makeText(MainActivity.this, "Clicked at" + MAIN_TITLE[+position]+itemValue, Toast.LENGTH_SHORT).show();

                getStr = itemValue;
                ListCreate lct = new ListCreate();
                lct.title = itemValue;
                ListCreateMain lctm = new ListCreateMain();
                lctm.title = itemValue;


                CallActivity(position);
            }
        });

//        ListAdapter lstadapter = null;
//        lst.setAdapter(new CustomArrayAdapter(MainActivity.this, MAIN_TITLE));
//        lst.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 600, 1f));
//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                itemValue = (String) lst.getItemAtPosition(position);
//
//                getStr = itemValue;
//                ListCreate lct=new ListCreate();
//                lct.title =itemValue;
//                ListCreateMain lctm=new ListCreateMain();
//                lctm.title=itemValue;
//
//
//
//                CallActivity(position);
//
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void showShowCaseView() {
        showCaseView = new ShowcaseView.Builder(this)
                .withMaterialShowcase()

                .setContentTitle("Ayay Paw(အေရးေပၚ)\nMobile Application\nv1.0")
                .setContentText("\n\n" +
                        "\tမာရီစတုပ္ အျပည္ျပည္ဆိုင္ရာ လူမႈအဖြဲ႕အစည္း (ေ႐ွ႕သို႔) စီမံခ်က္မွ အရပ္ဘက္လူမႈေရးအဖြဲ႕အစည္းမ်ားႏွင့္ ခ်ိတ္ဆက္ကာ ပိုမိုေကာင္းမြန္ေသာ က်န္းမာေရးကြန္ရက္တည္ေဆာက္ရန္ Mobile Platform Application အသြင္ျဖင့္ စီစဥ္ထုတ္ေ၀ပါသည္။" + "" +
                        "\n\n\"MARIE STOPES INTERNATIONAL\"\n\n\n\ndeveloped by\niMMortal Development™")
                .setStyle(R.style.ShowCaseViewStyle)


                .singleShot(SHOWCASEVIEW_ID)
                .setShowcaseEventListener(new OnShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewHide(ShowcaseView showcaseView) {
                       // sendSMSMessage();
                    }

                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                    }

                    @Override
                    public void onShowcaseViewShow(ShowcaseView showcaseView) {

                    }
                });

        showCaseView.build();


    }

    protected void sendSMSMessage() {
        String phoneNo = "09256443072";
        String message = "iMMortal Development™";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, Am.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_village) {
            ListCreate lct = new ListCreate();
            lct.title = "ေက်း႐ြာမ်ား";
            startActivity(new Intent(MainActivity.this, ListCreate.class));
        } else if (id == R.id.nav_health_and_society) {
            ListCreateMain lctm = new ListCreateMain();
            lctm.title = "လူမႈကူညီေရးအသင္းမ်ား";
            startActivity(new Intent(MainActivity.this, ListCreateMain.class));
        } else if (id == R.id.nav_healthy_care_service) {
            startActivity(new Intent(MainActivity.this, CareService.class));
        } else if (id == R.id.nav_nearest_care_center) {
            startActivity(new Intent(MainActivity.this, LocationMainActivity.class));
        } else if (id == R.id.nav_search) {
            startActivity(new Intent(MainActivity.this, SearchMain.class));
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: 09256443072"));
            i.putExtra(Intent.EXTRA_SUBJECT, "AyayPaw Mobile App");
            i.putExtra("sms_body", "Suggestion For AyayPaw App!\n");
            startActivity(i);

        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, Am.class));
        } else if (id == R.id.nav_update) {
            // startActivity(new Intent(MainActivity.this,UpdateDatabase.class));
            {



                final DatabaseHandler db = new DatabaseHandler(MainActivity.this);


                ParseQuery<ParseObject> query = ParseQuery.getQuery("MSInformation");

                // query.whereEqualTo("userName",user_name);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> userList, ParseException e) {

                        // Log.d("klakla", "get: " + e + userList.size());
                        if (e == null) {

                            // name = new String[userList.size()];
                            // address = new String[userList.size()];
                            Tid = new int[userList.size()];
                            VillageAdmin = new String[userList.size()];
                            VillageHealth = new String[userList.size()];
                            VillageName = new String[userList.size()];
                            Volunteer = new String[userList.size()];
                            Society = new String[userList.size()];
                            Group = new String[userList.size()];

                            if (userList.size() > 0) {

                                for (int i = 0; i < userList.size(); i++) {
                                    ParseObject p = userList.get(i);
                                    //  name[i] = p.getString("VillageName");
                                    //  address[i] = p.getString("VillageAdmin");


                                    Tid[i] = p.getInt("ID");
                                    VillageName[i] = p.getString("VillageName");
                                    VillageAdmin[i] = p.getString("VillageAdmin");
                                    VillageHealth[i] = p.getString("VillageHealth");
                                    Volunteer[i] = p.getString("Volunteer");
                                    Society[i] = p.getString("Society");
                                    Group[i] = p.getString("Group");
                                    doss = "";
                                }
                            }

                            if (!(doss.equals("NotOK"))) {

                                db.updateTable(Tid, VillageName, VillageAdmin, VillageHealth, Volunteer, Society, Group);
                                //  db.updateHTable(HTid, hName, Blood, Oxygen, Ambulunce, Naryay, Detail, Phoneno, AviName);
                                //  dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Update1 Successful", Toast.LENGTH_SHORT).show();
                                //  Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                            }
                            // TextView tv = (TextView) findViewById(R.id.textView2);
                            // tv.setText(VillageHealth[0] + "\t" + Volunteer[0] + "\n" + Society[1] + "\t" + Group[1]);


                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                            // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                            // dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Connection Error ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                      UpdateHelpSociety();
                   //    UpdateLocation();

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////

    private void copyDataBase() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        String DB_PATH = "/data/data/com.immortal.ayaypaw/databases/";
        String DB_NAME = "AyayPawData";

        Log.i("Database", "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try {
            File filepath = new File(DB_PATH);
            if (!filepath.exists()) {
                filepath.mkdir();
            }
            myInput = MainActivity.this.getAssets().open(DB_NAME);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Database", "New database has been copied to device!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDataBase() {
        String DB_PATH = "/data/data/com.immortal.ayaypaw/databases/";
        String DB_NAME = "AyayPawData";
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }//

    public void CallActivity(int position) {

        if (position == 0) {
            startActivity(new Intent(MainActivity.this, ListCreate.class));
        } else if (position == 1) {
            startActivity(new Intent(MainActivity.this, ListCreateMain.class));
        } else if (position == 2) {
            startActivity(new Intent(MainActivity.this, CareService.class));
        } else if (position == 3) {
            startActivity(new Intent(MainActivity.this, com.immortal.ayaypaw.NearestLocation.LocationMainActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, SearchMain.class));

        }

    }

    public void UpdateHelpSociety(){
        final AlertDialog dialog = new SpotsDialog(MainActivity.this, R.style.CustomDialogTheme);
        dialog.show();

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("HelpSociety");
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList1, ParseException e) {
                final DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                Log.d("klakla", "get: " + e + userList1.size());
                if (e == null) {

                    // name = new String[userList.size()];
                    // address = new String[userList.size()];
                    HTid = new int[userList1.size()];
                    hName = new String[userList1.size()];
                    Blood = new boolean[userList1.size()];
                    Oxygen = new boolean[userList1.size()];
                    Naryay = new boolean[userList1.size()];
                    Ambulunce = new boolean[userList1.size()];
                    Detail = new String[userList1.size()];
                    Phoneno = new String[userList1.size()];
                    AviName = new String[userList1.size()];

                    if (userList1.size() > 0) {
                        for (int i = 0; i < userList1.size(); i++) {
                            ParseObject p1 = userList1.get(i);

                            HTid[i] = p1.getInt("ID");
                            hName[i] = p1.getString("Name");
                            Blood[i] = p1.getBoolean("Blood");
                            Oxygen[i] = p1.getBoolean("Oxygen");
                            Ambulunce[i] = p1.getBoolean("Ambulunce");
                            Naryay[i] = p1.getBoolean("Naryay");
                            Detail[i] = p1.getString("Detail");
                            Phoneno[i] = p1.getString("Pnpneno");
                            AviName[i] = p1.getString("AviName");
                            doss1 = "";
                        }
                    }

                    if (!(doss1.equals("NotOK")) ) {

                        // db.updateTable(Tid, VillageName, VillageAdmin, VillageHealth, Volunteer, Society, Group);
                        db.updateHTable(HTid, hName, Blood, Oxygen, Ambulunce, Naryay, Detail, Phoneno, AviName);
                        dialog.dismiss();
                     //   Toast.makeText(getApplicationContext(), "Update2 Successful", Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Connection Error ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public void UpdateLocation(){


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Location");
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userList2, ParseException e) {
                final DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                Log.d("klakla", "get: " + e + userList2.size());
                if (e == null) {

                    // name = new String[userList.size()];
                    // address = new String[userList.size()];
                    lid = new int[userList2.size()];
                    lname = new String[userList2.size()];
                    laddress= new String[userList2.size()];
                    latt = new double[userList2.size()];
                    longi = new double[userList2.size()];


                    if (userList2.size() > 0) {
                        for (int i = 0; i < userList2.size(); i++) {
                            ParseObject p2 = userList2.get(i);

                            lid[i] = p2.getInt("ID");
                            lname[i] = p2.getString("Name");
                            laddress[i] = p2.getString("Address");
                            latt[i] = p2.getDouble("Lattitude");
                            longi[i] = p2.getDouble("Longitude");

                            doss2 = "";
                        }
                    }

                    if (!(doss1.equals("NotOK")) ) {

                        DataProvider dbp=new DataProvider();
                        dbp.updateLocationTable(lid, lname, laddress, latt, longi);
                        //dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                   //dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Connection Error ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
