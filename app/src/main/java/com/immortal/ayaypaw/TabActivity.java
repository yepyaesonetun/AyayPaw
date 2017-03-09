package com.immortal.ayaypaw;

/**
 * Created by ACER on 12/12/2015.
 */

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TabActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5;
    // Declaring Your View and Variables

    //  Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    String[] Titles = new String[4];
    int Numboftabs = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity_main);

        String[] strarr = getResources().getStringArray(R.array.title_arr);

        Typeface f = Typeface.createFromAsset(getAssets(), "zawgyi.ttf");


        Titles[0] = "ေက်းလက္က်န္းမာေရးဌာန";
        Titles[1] = "ေစတနာ့၀န္ထမ္းမ်ား";
        Titles[2] = "အုပ္ခ်ဳပ္ေရး";
        Titles[3] = "ဆက္သြယ္ရန္လူမႈကူညီေရးအသင္းမ်ား";

        DatabaseHandler dbhandler = new DatabaseHandler(getApplicationContext());
        Cursor cursor = dbhandler.getGroupName();
        cursor.moveToFirst();
        TextView tgroup = (TextView) findViewById(R.id.textView_VGroup);

        ListCreate lsc = new ListCreate();

        getSupportActionBar().setTitle(lsc.villageName);
        tgroup.setText("(" + cursor.getString(cursor.getColumnIndex("Group")) + ")");
        tgroup.setTypeface(Typeface.createFromAsset(getAssets(), "zawgyi.ttf"));


//        ListView lsttab=(ListView)findViewById(R.id.lst_tab_1);
//        lsttab.setAdapter(new CustomArrayAdapter(getApplicationContext(),Titles));

        //  Toolbar toolbarTop=(Toolbar)findViewById(R.id.toolbar_top);
        //TextView mTitle=(TextView)toolbarTop.findViewById(R.id.toolbar_title);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);


        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabdid);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }


}