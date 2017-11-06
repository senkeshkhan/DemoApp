package com.task.demoapp.fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.task.demoapp.NextActivity;
import com.task.demoapp.R;
import com.task.demoapp.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by praveen on 6/9/17.
 */
public class FolderPage extends Fragment {

    String str_createfolder;
   public static ArrayList<String> foldernames;
  public static   ArrayList<Integer> foldercolor;

    public static ArrayList<String> arrPackage;

    int DefaultColor, color2;

  private  RecyclerView recyclerView;
    RecyclerView.Adapter recyclerView_Adapter;

    RecyclerView.LayoutManager recyclerViewLayoutManager;

   public static SharedPreferences prefs;
    SharedPreferences.Editor editor;


    public FolderPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.folder_page, container, false);

        prefs = getActivity().getSharedPreferences("yourPrefsKey", Context.MODE_PRIVATE);
       /* putListInt("array", foldercolor);
        putListString("names", foldernames);*/
        arrPackage = new ArrayList<>();
        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerview);


        recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);



        // Inflate the layout for this fragment
        final FloatingActionButton fab =(FloatingActionButton) view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // showChangeLangDialog();

                    Intent intent = new Intent(getActivity(), NextActivity.class);
                    ActivityOptions options = null;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


                        options = ActivityOptions.makeSceneTransitionAnimation(
                                getActivity(),
                                android.util.Pair.create((View) fab, "bg"));
                    }
                    startActivity(intent, options.toBundle());
                  //  getActivity().finish();
                }
            });
        }

        //create_folder = (Button) view.findViewById(R.id.createfolderbtn);
        foldernames = new ArrayList<>();
        foldercolor = new ArrayList<>();
        DefaultColor = ContextCompat.getColor(getActivity(), R.color.colorAccent);

        //grid = (GridView)view. findViewById(R.id.grid);
       /* create_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog();
            }
        });*/
        return view;
    }
    public boolean saveArrayData(ArrayList<String> array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        editor = prefs.edit();
        editor.putInt(arrayName +" _size ", array.size());

        for(int i=0 ; i<array.size(); i++){
            editor.putString(arrayName + "_ " + i, array.get(i));
        }
        return editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        foldercolor = getListInt("array");
        foldernames = getListString("names");
        recyclerView_Adapter = new RecyclerViewAdapter(getActivity(),foldernames,foldercolor);

        recyclerView.setAdapter(recyclerView_Adapter);

         System.out.println("the values are");
    }
  /*  private void packagesharedPreferences() {
        SharedPreferences.Editor editor = shared.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(arrPackage);
        editor.putStringSet("DATE_LIST", set);
        editor.apply();
        Log.d("storesharedPreferences",""+set);
    }
*/
    /*private void retriveSharedValue() {
        Set<String> set = shared.getStringSet("DATE_LIST", null);
        arrPackage.addAll(set);
        Log.d("retrivesharedPrefere",""+set);
    }*/


    public static void putListInt(String key, ArrayList<Integer> intList) {
        // checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        prefs.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    public static void putListString(String key, ArrayList<String> intList) {
        // checkForNullKey(key);
        String[] myIntList = intList.toArray(new String[intList.size()]);
        prefs.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(prefs.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    public ArrayList<String> getListString(String key) {
        String[] myList = TextUtils.split(prefs.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));


        return arrayToList;
    }





    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.customalert, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        final Button colorpicker_btn = (Button) dialogView.findViewById(R.id.colorpicker);
        colorpicker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPickerDialog(false);

            }
        });


        dialogBuilder.setTitle("Create Folder");
      //  dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                str_createfolder = edt.getText().toString().trim();
                foldernames.add(str_createfolder);
                Toast.makeText(getActivity(), "folder" + str_createfolder + color2, Toast.LENGTH_LONG).show();
                if (color2 == 0) {
                    color2 = ContextCompat.getColor(getActivity(), R.color.colorAccent);
                    foldercolor.add(color2);
                    arrPackage.add(""+color2);


                } else {

                    arrPackage.add(""+color2);
                    foldercolor.add(color2);
                    color2=0;

                    Toast.makeText(getActivity(), "tes" + str_createfolder + color2, Toast.LENGTH_LONG).show();
                }

                recyclerView_Adapter = new RecyclerViewAdapter(getActivity(),foldernames,foldercolor);

                recyclerView.setAdapter(recyclerView_Adapter);

                putListInt("array", foldercolor);
                putListString("names", foldernames);
                /*CustomGrid adapter = new CustomGrid(getActivity(), foldernames, foldercolor);
                grid.setAdapter(adapter);*/
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void OpenColorPickerDialog(boolean AlphaSupport) {

        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getActivity(), DefaultColor, AlphaSupport, new
                AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {

                DefaultColor = color;
                color2 = color;
                //  foldercolor.add(DefaultColor);

                // foldercolor.add(color);
                //  relativeLayout.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {

                Toast.makeText(getActivity(), "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();

    }
}
