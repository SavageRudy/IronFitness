package com.example.ironfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Member;

public class Dbhelper  extends SQLiteOpenHelper {

    public static final String DBNAME = "Iron.db";

    public Dbhelper(Context context) {
        super(context, "Iron.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table members(id1 INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT ,email TEXT, subs TEXT, pass TEXT , vald TEXT , stat TEXT)");
        Mydb.execSQL("create Table workers(id1 INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,email TEXT, desg TEXT , pass TEXT, salary INT , sstat TEXT , whours TEXT , sid INT, FOREIGN KEY(sid) REFERENCES members(id1))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop Table if exists members");
        Mydb.execSQL("drop Table if exists workers");
        onCreate(Mydb);
    }

    //Member table functions

    //table name
    private static final String Members = "members";





    public Boolean insertMembers(  String name,String email, String subs , String pass ){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("subs",subs);
        cv.put("pass",pass);
        cv.put("vald"," 15th October 2020");
        cv.put("stat","Fees Pending");
        long result = Mydb.insert(Members,null,cv);
        Mydb.close();
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkMember(String name ){
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor= Mydb.rawQuery("Select * from  members where name = ?", new String[] {name});
        if (cursor.getCount()>0) {
            cursor.close();
            return true;
        }
        else
            return false;
    }

    public Boolean checkUser1( String name , String pass) {
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from  members where name = ? and pass = ?", new String[]{name, pass});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else
            return false;
    }


    public Member1 getdata(String name , String pass){
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Member1 member = new Member1();
        Cursor cursor = Mydb.rawQuery("Select * from  members where name = ? and pass = ?", new String[]{name, pass});

        if(cursor != null) {
            cursor.moveToFirst();
            member.setId(Integer.parseInt(cursor.getString(0)));
            member.setName(cursor.getString(1));
            member.setEmail(cursor.getString(2));
            member.setsubs(cursor.getString(3));
            member.setPassword(cursor.getString(4));
            member.setvald(cursor.getString(5));
            member.setstat(cursor.getString(6));
            cursor.close();
        }else{
            member = null;
        }

        return member;
    }

    public void deleteUser(String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by nme
        db.delete(Members, "mname = ? , mpass=?",
                new String[]{String.valueOf(name), String.valueOf(pass)});
        db.close();
    }

    //workers table

    //table name
    private static final String Workers = "workers";



    public Boolean insertWorker(  String name,String email, String desg , String pass ){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("desg",desg);
        cv.put("pass",pass);
        if (desg.equals("Trainer")) {
            cv.put("salary", 800);
        }else{
            if(desg.equals("Employee")){
                cv.put("salary",500);
            }
        }
        cv.put("sstat","pending");
        if (desg.equals("Trainer")) {
            cv.put("whours", "9am to 8pm");
        }else{
            if(desg.equals("Employee")){
                cv.put("whours","5am to 10 pm");
            }
        }
        cv.put("sid",0);
        long result = Mydb.insert(Workers,null,cv);
        Mydb.close();
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkWorker(String name ){
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor= Mydb.rawQuery("Select * from  workers where name = ?", new String[] {name});
        if (cursor.getCount()>0) {
            cursor.close();
            return true;
        }
        else
            return false;
    }

    public Boolean checkUser2( String name , String pass) {
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from  workers where name = ? and pass = ?", new String[]{name, pass});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        else
            return false;
    }

    public Worker1 getdata2(String name , String pass){
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Worker1 worker = new Worker1();
        Cursor cursor = Mydb.rawQuery("Select * from  workers where name = ? and pass = ?", new String[]{name, pass});
        if(cursor != null) {
            cursor.moveToFirst();
            worker.setId(Integer.parseInt(cursor.getString(0)));
            worker.setName(cursor.getString(1));
            worker.setEmail(cursor.getString(2));
            worker.setdesg(cursor.getString(3));
            worker.setPassword(cursor.getString(4));
            worker.setsal(Integer.parseInt(cursor.getString(5)));
            worker.setsstat(cursor.getString(6));
            worker.setwhours(cursor.getString(7));
            worker.setsid(Integer.parseInt(cursor.getString(8)));
            cursor.close();
        }else{
            worker = null;
        }

        return worker;
    }

    public void deleteUser2(String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by nme
        db.delete(Workers, "mname = ?  and  wpass=?",
                new String[]{String.valueOf(name), String.valueOf(pass)});
        db.close();
    }
}
