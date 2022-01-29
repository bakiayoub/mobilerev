package com.example.tpglobale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listViewAdapter extends BaseAdapter {

    List<car> list_car;
    //INFLATER =  TRANSFORMATEUR
    LayoutInflater inflater;

    public listViewAdapter(List<car> list_car, Context context) {
        this.list_car = list_car;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_car.size();
    }

    @Override
    public Object getItem(int position) {
        return list_car.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class afficherItem{
        ImageView image;
        TextView titre;
        TextView desc;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        afficherItem item;
        if(convertView == null)
        {
            item = new afficherItem();
            convertView = inflater.inflate(R.layout.list_item, null);
            item.image = (ImageView) convertView.findViewById(R.id.image);
            item.titre = (TextView)convertView.findViewById(R.id.titre);
            item.desc = (TextView)convertView.findViewById(R.id.desc);
            convertView.setTag(item);

        }
        else{
            item = (afficherItem) convertView.getTag();
        }
        item.image.setImageResource(list_car.get(position).getImage());
        item.titre.setText(list_car.get(position).getTitre());
        item.desc.setText(list_car.get(position).getDesc());

        return convertView;

    }
}
