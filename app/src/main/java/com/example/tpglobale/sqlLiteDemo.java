package com.example.tpglobale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class sqlLiteDemo extends AppCompatActivity {

    private ListView listView;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;

    private static final int MY_REQUEST_CODE = 1000;

    private final List<car> carList = new ArrayList<car>();
    private ArrayAdapter<car> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_demo);

        // Get ListView object from xml
        this.listView = (ListView) findViewById(R.id.carLists);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultCars();

        List<car> list=  db.getAllCars();
        this.carList.addAll(list);

        // Define a new Adapter
        // 1 - Context
        // 2 - Layout for the row
        // 3 - ID of the TextView to which the data is written
        // 4 - the List of data

        this.listViewAdapter = new ArrayAdapter<car>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.carList);

        // Assign adapter to ListView
        this.listView.setAdapter(this.listViewAdapter);

        // Register the ListView for Context menu
        registerForContextMenu(this.listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_VIEW , 0, "View Note");
        menu.add(0, MENU_ITEM_CREATE , 1, "Create Note");
        menu.add(0, MENU_ITEM_EDIT , 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final car selectedCar= (car) this.listView.getItemAtPosition(info.position);

        if(item.getItemId() == MENU_ITEM_VIEW){
            Toast.makeText(getApplicationContext(),selectedCar.getTitre(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId() == MENU_ITEM_CREATE){
            Intent intent = new Intent(this, addEditNote.class);

            // Start AddEditNoteActivity, (with feedback).
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        }
        else if(item.getItemId() == MENU_ITEM_EDIT ){
            Intent intent = new Intent(this, addEditNote.class);
            intent.putExtra("car", selectedCar);

            // Start AddEditNoteActivity, (with feedback).
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if(item.getItemId() == MENU_ITEM_DELETE){
            // Ask before deleting.
            new AlertDialog.Builder(this)
                    .setMessage(selectedCar.getTitre() +". Are you sure you want to delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteCar(selectedCar);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else {
            return false;
        }
        return true;
    }

    private void deleteCar(car selectedCar) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteCar(selectedCar);
        this.carList.remove(selectedCar);
        // Refresh ListView.
        this.listViewAdapter.notifyDataSetChanged();
    }

    // When AddEditNoteActivity completed, it sends feedback.
    // (If you start it using startActivityForResult ())
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            // Refresh ListView
            if (needRefresh) {
                this.carList.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                List<car> list = db.getAllCars();
                this.carList.addAll(list);


                // Notify the data change (To refresh the ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }

}