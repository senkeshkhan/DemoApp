package com.task.demoapp.fragment;

/**
 * Created by vivid on 12/9/17.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;
import com.task.demoapp.R;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanPage extends Fragment {


    public ScanPage() {
        // Required empty public constructor
    }

    boolean _areLecturesLoaded = false;

    private static final int REQUEST_CODE = 99;
    private Button scanButton;
     Button cameraButton;
    private Button mediaButton;
    private ImageView scannedImageView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.scan_page, container, false);


        if (!checkPermission()) {

            requestPermission();


        } else {

            //Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();

        }
        scanButton = (Button) view. findViewById(R.id.scanButton);
        cameraButton = (Button)view.findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));

        scannedImageView = (ImageView)view. findViewById(R.id.scannedImage);
        mediaButton = (Button)view.findViewById(R.id.mediaButton);
        init();


        return view;

}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !_areLecturesLoaded ) {
            startScan(ScanConstants.OPEN_CAMERA);
            _areLecturesLoaded = false;
        }
    }


    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA);
        int result1 = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.GET_TASKS);


//<uses-permission android:name="android.permission.MANAGE_DOCUMENTS"/>
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED ;
    }

    public void requestPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{ android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.GET_TASKS}, PERMISSION_REQUEST_CODE);

    }


    private void init() {

        scanButton.setOnClickListener(new ScanButtonClickListener());

        cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));

        if (!checkPermission()) {

            requestPermission();


        } else {

            //Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();

        }
        mediaButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_MEDIA));

    }

    private class ScanButtonClickListener implements View.OnClickListener {

        private int preference;

        public ScanButtonClickListener(int preference) {
            this.preference = preference;
        }

        public ScanButtonClickListener() {



        }

        @Override
        public void onClick(View v) {
            startScan(preference);
        }
    }

    protected void startScan(int preference) {
        Intent intent = new Intent(getActivity(), ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                getActivity(). getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap convertByteArrayToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}