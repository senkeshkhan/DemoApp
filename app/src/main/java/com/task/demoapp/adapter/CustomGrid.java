package com.task.demoapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.demoapp.R;

import java.util.ArrayList;

/**
 * Created by praveen on 6/9/17.
 */
public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private  ArrayList<String> names=new ArrayList<>();
    private  ArrayList<Integer> foldercolors=new ArrayList<>();
    private static final int[] FROM_COLOR = new int[]{249, 279, 210};
    private static final int THRESHOLD = 3;
   // private final int[] Imageid;

    public CustomGrid(Context c, ArrayList<String> folder_names , ArrayList<Integer> foldercolors) {
        mContext = c;
      //  this.Imageid = Imageid;
        this.names =folder_names;
        this.foldercolors=foldercolors;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);


            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
           ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);

            textView.setText(names.get(position));
            textView.setTextColor(foldercolors.get(position));
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(foldercolors.get(position),PorterDuff.Mode.MULTIPLY);

            imageView.setColorFilter(porterDuffColorFilter);
            imageView.setBackgroundColor(Color.TRANSPARENT);
           //imageView.setImageResource(Imageid[position]);
           // imageView.setBackgroundColor(foldercolors.get(position));
           // Drawable d =mContext. getResources().getDrawable(R.drawable.folderimage);
          //  imageView.setImageDrawable(adjust(d));
           // imageView.setColorFilter(foldercolors.get(position));
        } else {
            grid = (View) convertView;
        }

        return grid;
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