package com.task.demoapp.adapter;

/**
 * Created by vivid on 6/9/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.demoapp.R;

import java.util.ArrayList;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> names=new ArrayList<>();
      ArrayList<Integer> foldercolors=new ArrayList<>();
    private static final int[] FROM_COLOR = new int[]{249, 279, 210};
    private static final int THRESHOLD = 3;
    String[] values;
    Context context1;

    public RecyclerViewAdapter(Context context2, ArrayList<String> folder_names, ArrayList<Integer> foldercolors){

        //  this.Imageid = Imageid;
        this.names =folder_names;
        this.foldercolors=foldercolors;

        context1 = context2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        ImageView imageView;
        public ViewHolder(View v){

            super(v);
             textView = (TextView) v.findViewById(R.id.grid_text);
             imageView = (ImageView)v.findViewById(R.id.grid_image);

        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context1).inflate(R.layout.grid_single,parent,false);

        ViewHolder viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){


        Vholder.textView.setText(names.get(position));
        Vholder.  textView.setTextColor(foldercolors.get(position));


        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(foldercolors.get(position), PorterDuff.Mode.MULTIPLY);


       // Vholder.  imageView.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
       // Vholder.imageView.setBackgroundResource(R.drawable.folder);
      // Vholder. imageView.setColorFilter(ContextCompat.getColor(context1, foldercolors.get(position)), android.graphics.PorterDuff.Mode.MULTIPLY);
       Vholder. imageView.setColorFilter(porterDuffColorFilter);
        //Vholder. imageView.setBackgroundColor(Color.TRANSPARENT);



    }

    @Override
    public int getItemCount(){

        return names.size();
    }

    private Drawable adjust(Drawable d)
    {
        int to = Color.RED;

        //Need to copy to ensure that the bitmap is mutable.
        Bitmap src = ((BitmapDrawable) d).getBitmap();
        Bitmap bitmap = src.copy(Bitmap.Config.ARGB_8888, true);
        for(int x = 0;x < bitmap.getWidth();x++)
            for(int y = 0;y < bitmap.getHeight();y++)
                if(match(bitmap.getPixel(x, y)))
                    bitmap.setPixel(x, y, to);

        return new BitmapDrawable(bitmap);
    }

    private boolean match(int pixel)
    {
        //There may be a better way to match, but I wanted to do a comparison ignoring
        //transparency, so I couldn't just do a direct integer compare.
        return Math.abs(Color.red(pixel) - FROM_COLOR[0]) < THRESHOLD &&
                Math.abs(Color.green(pixel) - FROM_COLOR[1]) < THRESHOLD &&
                Math.abs(Color.blue(pixel) - FROM_COLOR[2]) < THRESHOLD;
    }


}