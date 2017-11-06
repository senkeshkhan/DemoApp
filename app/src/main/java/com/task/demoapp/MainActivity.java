package com.task.demoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.task.demoapp.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {




    //This is our viewPager
    private ViewPager viewPager;

    TabLayout tabLayout;
    TextView title;
    int max = 0;
    int maxfirst = 0;
    ArrayList<Integer> arrayList = new ArrayList<>();
    int [] valArr = {9,7,33,6,8,11,21,66};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.bottombarcolor));
        tToast("onCreate");
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyReceiver.class));


        largestAndSmallest(valArr);


        arrayList.add(89);
        arrayList.add(100);
        arrayList.add(1);
        arrayList.add(12);
        arrayList.add(1000);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(60);
        arrayList.add(99);
            maxFirsts(valArr);
        Collections.sort(arrayList);


         //palindrome & reverse strings
        String kk = new StringBuffer("vava").reverse().toString();

        System.out.println("vallllllllllll"+arrayList.get(arrayList.size()-1)+" "+arrayList.get(arrayList.size()-2));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarlay);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottombarcolor)));
         title=(TextView)findViewById(getResources().getIdentifier("action_bar_title", "id", getPackageName()));

        title.setText("My Folder");

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);


        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabiconselector).setText("Folder"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabiconselector1).setText("Scan"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabiconselector2).setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabiconselector3).setText("Settings"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(getResources().getColor(R.color.tabdividercolor));
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(20);
        linearLayout.setDividerDrawable(drawable);


        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab.getIcon().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                viewPager.setCurrentItem(tab.getPosition());


                switch (tab.getPosition()) {
                    case 0:

                        title.setText("My Folder");

                        viewPager.setCurrentItem(tab.getPosition());
                        // showToast("One");
                        break;
                    case 1:
                        //  showToast("Two");
                        title.setText("Scan");

                        break;
                    case 2:
                        // showToast("Three");
                        title.setText("My Profile");

                        break;

                    case 3:
                        // showToast("Three");
                        title.setText("Settings");

                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


    }
    public static void largestAndSmallest(int[] numbers) {

        int largest = Integer.MIN_VALUE;
        int largest1 = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int smallest1 = Integer.MAX_VALUE;

        for (int number : numbers) {

            if (number > largest) {

                largest1 =largest;

                largest = number;




            } else if (number > largest1){

                largest1=number;

            } else if (number < smallest) {

                smallest1 = smallest;
                smallest = number;

            }else if(number< smallest1){

                smallest1=number;

            }
        }

        System.out.println("Given integer array : " + Arrays.toString(numbers));

        System.out.println("Largest number in array is : " + largest+largest1);

        System.out.println("Smallest number in array is : " + smallest+" : "+smallest1);
    }

    public  void maxFirsts(int [] val){

      for(int n : val){
          if(n > max){

             // System.out.println("yyyyyyyyyyyyyyyy"+max);
              maxfirst = max;
            //  System.out.println("zzzzzzzzzzzzzzzzz"+maxfirst);
              max=n;
             // System.out.println("aaaaaaaaaaaaaaaaa"+max+n);
          }else if (n > maxfirst){

              maxfirst= n;
          }
      }
        System.out.println("First Max Number: "+max);
        System.out.println("Second Max Number: "+maxfirst);
    }
    public void onStart() {
        super.onStart();
        tToast("onStart");
    }

    public void onRestart() {
        super.onRestart();
        tToast("onRestart");
    }

    public void onResume() {
        super.onResume();
        tToast("onResume");
    }

    public void onPause() {
        super.onPause();
        tToast("onPause: bye bye!");
    }

    public void onStop() {
        super.onStop();
        tToast("onStop.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tToast("onDestroy.");
    }

    private void tToast(String s) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, s, duration);
        toast.show();
    }
}




