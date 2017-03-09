package com.immortal.ayaypaw;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ACER on 12/12/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static String itemvalue;
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    public static String[] VillageAdmim;
    public static String[] VillageName;

    public static int length = 0;
    // Database Name
    private static final String DATABASE_NAME = "AyayPawData";

    // Contacts table name
    private static final String TABLE_MSINFORMATION = "MSInformation";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "VillageName";
    private static final String KEY_VAdmin = "VillageAdmin";
    private static final String KEY_VHealth = "VillageHealth";
    private static final String KEY_Volunteer = "Volunteer";
    private static final String KEY_Society = "Society";
    private static final String KEY_Group = "Group";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFORMATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MSINFORMATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_VAdmin + " TEXT," + KEY_VHealth + " TEXT," + KEY_Volunteer + " TEXT," + KEY_Society + " TEXT,'Group' TEXT)";
        db.execSQL(CREATE_INFORMATION_TABLE);
        String helpsociety = "CREATE TABLE IF NOT EXISTS HelpSociety('_id' INTEGER PRIMARY KEY,'Name' TEXT,'Blood' BOOL,'Oxygen' BOOL,'Ambulance' BOOL,'Naryay' BOOL,'Detail' TEXT,'Phoneno' TEXT,'AviName' TEXT)";
        db.execSQL(helpsociety);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSINFORMATION);
        db.execSQL("DROP TABLE IF EXISTS HelpSociety");

        // Create tables again
        onCreate(db);
    }

    // Getting All Contacts
    public Cursor getAllContacts() {

        // Select All Query
        String selectQuery = "SELECT * FROM MSInformation";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor HelpSociety() {

        // Select All Query
        String selectQuery = "SELECT * FROM HelpSociety";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor VillageInfomation() {
        // String VNAME="မံုေ႐ြး";
        String selectQuery = "SELECT * FROM " + TABLE_MSINFORMATION + " WHERE VillageName='" + itemvalue + "'";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }

    public Cursor getGroupName() {
        // String VNAME="မံုေ႐ြး";
        String selectQuery = "SELECT * FROM " + TABLE_MSINFORMATION + " WHERE VillageName='" + itemvalue + "'";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }
    public Cursor getSupport(String type){

        String selectQuery = "SELECT * FROM HelpSociety WHERE "+type+"='true'";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }
    public Cursor getDetail(String names){

        String selectQuery = "SELECT * FROM HelpSociety WHERE AviName='"+ names+"'";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }
    public Cursor getDetailfirst(String names){

        String selectQuery = "SELECT * FROM HelpSociety WHERE Name='"+ names+"'";
        Log.i("SelectQuery", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }
    public void updateTable(int[] Id,String[] vname,String[] vadmin,String[] vhealth,String[] volunteer,String[] society,String[] group){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from MSInformation");
        for(int i=0;i<Id.length;i++){

            db.execSQL("insert into MSInformation values('" + Id[i] + "','" + vname[i]
                    + "','" + vadmin[i] + "','" + vhealth[i] + "','" + volunteer[i] + "','" + society[i]+ "','" + group[i]
                    + "')");
            Log.i("insert", " " + vname[i] + " successful");

        }
    }
    public void updateHTable(int[] Id,String[] name,boolean[] blood,boolean[] oxygen,boolean[] ambulance,boolean[] naryay,String[] detail,String[] phno,String[] avi){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from HelpSociety");
        for(int i=0;i<Id.length;i++){

            db.execSQL("insert into HelpSociety values('" + Id[i] + "','" + name[i]
                    + "','" + blood[i] + "','" + oxygen[i] + "','" + ambulance[i] + "','" + naryay[i]+ "','" + detail[i]+ "','" + phno[i]+ "','" + avi[i]
                    + "')");
            Log.i("insert"," "+name[i]+" successful");

        }
    }

}
