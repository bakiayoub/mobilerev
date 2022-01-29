package com.example.tpglobale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class list_view extends AppCompatActivity {
    ListView listView;
    List<car> list_car = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listview);
        listViewAdapter adapter = new listViewAdapter(list_car,this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addCar();
    }

    private void addCar() {
        list_car.clear();
        list_car.add(new car(R.drawable.audirs3,"Audi RS3"," LoremIpsumLoremIpsumLoremIpsum"));
        list_car.add(new car(R.drawable.g63,"Mercedes G63","LoremIpsumLoremIpsumLoremIpsum"));
        list_car.add(new car(R.drawable.gt63s,"Mercedes GT63s","LoremIpsumLoremIpsumLoremIpsum"));



    }
}