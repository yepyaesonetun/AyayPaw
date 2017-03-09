package com.immortal.ayaypaw;

/**
 * Created by ACER on 20/12/2015.
 */
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



public class Am extends AppCompatActivity{

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    AboutViewPagerAdapter adapter;
    Al tabs;
    CharSequence Titles[]={"ရည္႐ြယ္ခ်က္","အားထုတ္ႀကိဳးပမ္းခဲ့သူမ်ား"};
    int Numboftabs =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // setSupportActionBar(toolbar);


        // Creating The AboutViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new AboutViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (Al) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new Al.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.TSC);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);



    }



}