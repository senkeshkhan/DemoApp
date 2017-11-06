package com.task.demoapp.adapter;

/**
 * Created by vivid on 6/9/17.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.task.demoapp.fragment.FolderPage;
import com.task.demoapp.fragment.ProfilePage;
import com.task.demoapp.fragment.ScanPage;
import com.task.demoapp.fragment.SettingPage;


public class ViewPagerAdapter extends FragmentPagerAdapter {

        int mNumOfTabs;
        Activity activity;

        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    FolderPage tab1 = new FolderPage();


                    return tab1;
                case 1:
                    ScanPage tab2 = new ScanPage();
                    return tab2;
                case 2:
                    ProfilePage tab3 = new ProfilePage();
                    return tab3;


                case 3:
                    SettingPage tab4 = new SettingPage();
                    return tab4;
                default:
                    return null;
            }
        }




        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }