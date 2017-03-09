package com.immortal.ayaypaw;

/**
 * Created by ACER on 20/12/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hp1 on 21-01-2015.
 */
public class AboutViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when AboutViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the AboutViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public AboutViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            At1 tab1 = new At1();
            return tab1;
        }
        else         // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            At2 tab2 = new At2();
            return tab2;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}

