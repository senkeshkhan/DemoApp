package com.task.demoapp.view;

import android.content.Context;
import android.widget.ImageView;

import com.task.demoapp.R;


/**
 * Nothing but an ImageView with a preset image resource
 * @author yildizkabaran
 *
 */
public class ContentView extends ImageView {
  
  public ContentView(Context context){
    super(context);
    initialize();
  }
  
  private void initialize(){
    // set the dummy content image here
    setImageResource(R.drawable.ic_done_white_48dp);
  }
}
