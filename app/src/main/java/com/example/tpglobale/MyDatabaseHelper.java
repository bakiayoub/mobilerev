package com.example.tpglobale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Car_Manager";

    // Table name: Note.
    private static final String TABLE_CAR = "Car";

    private static final String COLUMN_CAR_ID ="Car_Id";
    private static final String COLUMN_CAR_TITLE ="Car_Title";
    private static final String COLUMN_CAR_CONTENT = "Car_Content";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_CAR  + "("
                + COLUMN_CAR_ID + " INTEGER PRIMARY KEY," + COLUMN_CAR_TITLE + " TEXT,"
                + COLUMN_CAR_CONTENT + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);

        // Create tables again
        onCreate(db);

    }

    public void addNote(car newcar) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + newcar.getTitre());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_TITLE, newcar.getTitre());
        values.put(COLUMN_CAR_CONTENT, newcar.getDesc());

        // Inserting Row
        db.insert(TABLE_CAR, null, values);

        // Closing database connection
        db.close();
    }

    public int getCarsCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void createDefaultCars()  {
        int count = this.getCarsCount();
        if(count ==0 ) {
            car car1 = new car("Merced AMG",
                    "Lorem ipsum cardistet elargist");
            car car2 = new car("",
                    "Lorem ipsum cardistet elargist");
            this.addNote(car1);
            this.addNote(car2);
        }
    }

    public car getcar(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CAR, new String[] { COLUMN_CAR_ID,
                        COLUMN_CAR_TITLE, COLUMN_CAR_CONTENT }, COLUMN_CAR_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        car car = new car(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return note
        return car;
    }

    public int updatecar(car car) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + car.getTitre());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_TITLE, car.getTitre());
        values.put(COLUMN_CAR_CONTENT, car.getDesc());

        // updating row
        return db.update(TABLE_CAR, values, COLUMN_CAR_ID + " = ?",
                new String[]{String.valueOf(car.getImage())});
    }

    public void deleteCar(car car) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + car.getTitre() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAR, COLUMN_CAR_ID + " = ?",
                new String[] { String.valueOf(car.getImage()) });
        db.close();
    }

    public List<car> getAllCars() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<car> carList = new ArrayList<car>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                car car = new car();
                car.setImage(Integer.parseInt(cursor.getString(0)));
                car.setTitre(cursor.getString(1));
                car.setDesc(cursor.getString(2));
                // Adding note to list
                carList.add(car);
            } while (cursor.moveToNext());
        }

        // return note list
        return carList;
    }






}
