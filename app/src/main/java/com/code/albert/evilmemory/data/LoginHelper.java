package com.code.albert.evilmemory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Albert on 28/01/2017.
 */

public class LoginHelper extends SQLiteOpenHelper{
    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "Login";

    //Declaracion del nombre de la tabla
    public static final String LOGIN_TABLE ="Users";

    //sentencia global de cracion de la base de datos
    public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " + LOGIN_TABLE + " (name TEXT PRIMARY KEY UNIQUE, password INTEGER, completename TEXT, address TEXT,score4 INTEGER, score6 INTEGER, score8 INTEGER);";
    //public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " + LOGIN_TABLE + " (name TEXT PRIMARY KEY UNIQUE, password INTEGER,
    //          completename TEXT, profileimage ??,score4 INTEGER, score6 INTEGER, socre8 INTEGER);";



    public LoginHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOGIN_TABLE_CREATE);

    }


    public Cursor getUserPassName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"password"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    public Cursor getUserName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"name"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }

    public Cursor getUserData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"completename","address","score4","score6","score8"};
        String[] where = {name};
        Cursor c = db.query(
                LOGIN_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }





    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    public void modifyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET completename='"+name+"'");
    }
    public void modifyPassword(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET password='"+password+"'");
    }

    public void modifyAddress(String address){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET address='"+address+"'");
    }


    public Cursor getRanking4() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name", "score4"};
        Cursor c=db.query(
                LOGIN_TABLE,
                columns,
                "score4>1",
                null,
                null,
                null,
                "score4 ASC"
        );
        return c;
    }

    public Cursor getRanking6() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name", "score6"};
        Cursor c=db.query(
                LOGIN_TABLE,
                columns,
                "score6>1",
                null,
                null,
                null,
                "score6 ASC"
        );
        return c;
    }

    public Cursor getRanking8() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name", "score8"};
        Cursor c=db.query(
                LOGIN_TABLE,
                columns,
                "score8>1",
                null,
                null,
                null,
                "score8 ASC"
        );
        return c;
    }

    public int getBetter4(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"score4"};
        String[] where = {username};
        Cursor cursor = db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("score4"));
        }
        else {return 0;}
    }

    public int getBetter6(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"score6"};
        String[] where = {username};
        Cursor cursor = db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("score6"));
        }
        else {return 0;}
    }

    public int getBetter8(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"score8"};
        String[] where = {username};
        Cursor cursor = db.query(
                LOGIN_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("score8"));
        }
        else {return 0;}
    }

    public void setScore4(String username, int intents) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+LOGIN_TABLE+" SET score4="+intents+" WHERE name='"+username+"';");
    }

    public void setScore6(String username, int intents) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+LOGIN_TABLE+" SET score6="+intents+" WHERE name='"+username+"';");
    }

    public void setScore8(String username, int intents) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+LOGIN_TABLE+" SET score8="+intents+" WHERE name='"+username+"';");
    }


    public boolean clearRankingByLevel(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] where = {level};
        return db.delete(
                LOGIN_TABLE,
                "level=?",
                where) > 0;
    }

    public void DeleteRanking4(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + LOGIN_TABLE +" SET score4=" + 0);

        }

    public void DeleteRanking6(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET score6=" + 0);
    }

    public void DeleteRanking8(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " +LOGIN_TABLE +" SET score8=" + 0);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL(LOGIN_TABLE_CREATE);
    }
}
