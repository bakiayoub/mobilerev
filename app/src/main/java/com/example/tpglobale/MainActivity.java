package com.example.tpglobale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gallery gallery = findViewById(R.id.gallery);
        gallery.setAdapter(new GalleryAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent i = new Intent(MainActivity.this,list_view.class);
                    startActivity(i);
                }
                if (position==1){
                    Intent i = new Intent(MainActivity.this,sqlLiteDemo.class);
                    startActivity(i);

                }

            }
        });

    }
}