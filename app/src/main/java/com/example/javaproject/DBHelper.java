package com.example.javaproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_MOBILE = "user_mobile";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_BALANCE = "user_balance";
    private static final String COLUMN_USER_SAVINGS = "user_savings";
    SQLiteDatabase db;
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " TEXT," + COLUMN_LAST_NAME + " TEXT,"
            + COLUMN_USER_MOBILE + " TEXT," + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_SAVINGS + " DOUBLE," + COLUMN_USER_BALANCE + " DOUBLE" + ")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(UserClass user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getFirstname());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_USER_MOBILE, user.getMobile());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        user.setBalance(12600.00);
        user.setSavings(22500.00);
        values.put(COLUMN_USER_BALANCE, 12600.00);
        values.put(COLUMN_USER_SAVINGS, 22500.00);
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();

    }

    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        UserClass c = new UserClass();
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {

            return true;
        }
        return false;
    }

    public void getDetails(String email){
        UserClass c = new UserClass();
        db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM user WHERE user_email = '" + email + "' LIMIT 1", null);
        int emailIndex = cursor.getColumnIndex("user_email");
        int firstIndex = cursor.getColumnIndex("first_name");
        int lastIndex = cursor.getColumnIndex("last_name");
        int savingsIndex = cursor.getColumnIndex("user_savings");
        int balanceIndex = cursor.getColumnIndex("user_balance");

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            c.setFirstname(cursor.getString(firstIndex));
            c.setLastName(cursor.getString(lastIndex));
            c.setBalance(cursor.getDouble(balanceIndex));
            c.setSavings(cursor.getDouble(savingsIndex));
            c.setEmail(cursor.getString(emailIndex));
            Log.d("TAG", "getDetails: " + cursor.getString(firstIndex));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void updateDetails(double balance, double savings, String email){
        UserClass c = new UserClass();
        db = this.getWritableDatabase();
        db.execSQL("UPDATE user SET user_balance= '"+ balance +"',user_savings= '"+ savings + "' WHERE user_email= '"+ email + "' ");
        db.close();
        c.setBalance(balance);
        c.setSavings(savings);
    }
}
