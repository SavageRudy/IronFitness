package com.example.ironfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Member;

public class Dbhelper  extends SQLiteOpenHelper {

    public static final String DBNAME = "Iron.db";

    public Dbhelper(Context context) {
        super(context, "Iron.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table members(id1 INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT ,email TEXT, subs TEXT, pass TEXT)");
        Mydb.execSQL("create Table workers(id1 INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,email TEXT, desg TEXT , pass TEXT, salary INT)");

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

    //Member table coloumns
    private static final String mid = "id1";
    private static final String mname = "name";
    private static final String memail = "email";
    private static final String msubs = "subs";
    private static final String mpass = "pass";

    private static final String[] Coloumns = { mid,mname,memail,msubs,mpass};

    String selection1 = mname + " = ?" + mpass + " = ?";


    public Boolean insertMembers(  String name,String email, String subs , String pass ){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("subs",subs);
        cv.put("pass",pass);
        long result = Mydb.insert("members",null,cv);
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
        Cursor cursor = Mydb.query(Members,Coloumns,selection1,new String[]{String.valueOf(name), String.valueOf(pass)},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
            member.setId(Integer.parseInt(cursor.getString(0)));
            member.setName(cursor.getString(1));
            member.setEmail(cursor.getString(2));
            member.setsubs(cursor.getString(3));
            member.setPassword(cursor.getString(4));
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

    private static final String wid = "id1";
    private static final String wname = "name";
    private static final String wemail = "email";
    private static final String wdesg = "desg";
    private static final String wpass = "pass";
    private static final String wsal = "salary";

    private static final String[] Coloumns1 = { wid,wname,wemail,wdesg,wpass,wsal};
    String selection2 = wname + " = ?" + wpass + " = ?";


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
        long result = Mydb.insert("workers",null,cv);
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
        Cursor cursor = Mydb.query(Workers,Coloumns1,selection2,new String[]{String.valueOf(name), String.valueOf(pass)},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
            worker.setId(Integer.parseInt(cursor.getString(0)));
            worker.setName(cursor.getString(1));
            worker.setEmail(cursor.getString(2));
            worker.setdesg(cursor.getString(3));
            worker.setPassword(cursor.getString(4));
            worker.setsal(Integer.parseInt(cursor.getString(5)));
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
