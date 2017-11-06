package com.task.demoapp.fragment;

/**
 * Created by vivid on 6/9/17.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.demoapp.R;


/**
     * A simple {@link Fragment} subclass.
     */
    public class ProfilePage extends Fragment {


        public ProfilePage() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.profilepage, container, false);
        }

    }