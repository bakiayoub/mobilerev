package com.example.tpglobale;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

    private Context mycontext;
    private Integer[] myimages = {R.drawable.g63,R.drawable.audirs3,R.drawable.gt63s};

    public GalleryAdapter(Context mycontext) {
        this.mycontext = mycontext;
    }

    @Override
    public int getCount() {
        return myimages.length;
    }

    @Override
    public Object getItem(int position) {
        return myimages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(mycontext);
        image.setImageResource((Integer) getItem(position));
        image.setLayoutParams(new Gallery.LayoutParams(200, 200));
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        return image;
    }
}
