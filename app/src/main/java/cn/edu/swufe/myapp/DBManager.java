package cn.edu.swufe.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DBHelper dbHelper;
    private String TBNAME;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }
    public void add(Choices cho){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", cho.getQuestion());
        values.put("result", cho.getResult());
        values.put("time", cho.getTime());
        db.insert(TBNAME, null, values);
        db.close();
    }
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public List<Choices> listAll(){
        List<Choices> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            rateList = new ArrayList<Choices>();
            while(cursor.moveToNext()){
                Choices item = new Choices();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setQuestion(cursor.getString(cursor.getColumnIndex("QUESTION")));
                item.setResult(cursor.getString(cursor.getColumnIndex("RESULT")));
                item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));

                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;

    }

    public Choices findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        Choices rateItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            rateItem = new Choices();
            rateItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            rateItem.setQuestion(cursor.getString(cursor.getColumnIndex("QUESTION")));
            rateItem.setResult(cursor.getString(cursor.getColumnIndex("RESULT")));
            rateItem.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            cursor.close();
        }
        db.close();
        return rateItem;
    }
}