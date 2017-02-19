package com.app.kisan.network.demo.Utils;

/**
 * Created by adyro on 18-02-2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.kisan.network.demo.Model.SentMessageDetails;

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sentMessages";

    // Contacts table name
    private static final String TABLE_SMS_DETAILS = "sent_messages_details";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CONTACT_NAME = "contact_name";
    private static final String KEY_TIME_STAMP = "time_stamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SMS_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CONTACT_NAME + " TEXT,"
                + KEY_TIME_STAMP + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS_DETAILS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new sentMessageDetails
    public void addContact(SentMessageDetails sentMessageDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONTACT_NAME, sentMessageDetails.getContactName());
        values.put(KEY_TIME_STAMP, sentMessageDetails.getCurrentDateAndTime());

        // Inserting Row
        db.insert(TABLE_SMS_DETAILS, null, values);
        db.close(); // Closing database connection
    }

    public List<SentMessageDetails> getAllMessageDetails() {
        List<SentMessageDetails> messageDetailsList = new ArrayList<SentMessageDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SMS_DETAILS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SentMessageDetails messageDetails = new SentMessageDetails();
                messageDetails.setContactName(cursor.getString(1));
                messageDetails.setCurrentDateAndTime(cursor.getString(2));
                // Adding contact to list
                messageDetailsList.add(messageDetails);
            } while (cursor.moveToNext());
        }
        return messageDetailsList;
    }



    // Getting contacts Count
    public int getAllMessageCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SMS_DETAILS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
