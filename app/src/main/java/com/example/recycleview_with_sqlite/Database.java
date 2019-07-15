package com.example.recycleview_with_sqlite;
import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import android.widget.ArrayAdapter;



import java.util.ArrayList;
import java.util.List;


/**

 * Created by vivek on 10/29/13.

 */

public class Database extends SQLiteOpenHelper {

String Table="demo";

    public Database(Context context)

    {

        super(context, "RecycleView_Demo_DB.db", null, 1);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String tableEmp="create table "+Table+"(prod_main_img text,prod_name text,id integer primary key)";

        db.execSQL(tableEmp);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table+" ;");
    }

    public void insertData(String prod_main_img,String prod_name, String id)

    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("prod_main_img",prod_main_img);

        values.put("prod_name",prod_name);

        values.put("id",id);

        sqLiteDatabase.insertWithOnConflict(Table, null, values,SQLiteDatabase.CONFLICT_REPLACE);

        //sqLiteDatabase.insert("info1",null,values);
        System.out.println("values : "+values);

    }
//
//    public ArrayList fetchData()
//
//    {
//
//        ArrayList<String>stringArrayList=new ArrayList<String>();
//
//        String fetchdata="select * from emp";
//
//        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
//
//        Cursor cursor=sqLiteDatabase.rawQuery(fetchdata, null);
//
//        if(cursor.moveToFirst()){
//
//            do
//
//            {
//
//                stringArrayList.add(cursor.getString(0));
//
//                stringArrayList.add(cursor.getString(1));
//
//                stringArrayList.add(cursor.getString(2));
//
//            } while (cursor.moveToNext());
//
//        }
//        System.out.println("stringArrayList"+stringArrayList);
//        return stringArrayList;
//
//
//    }

    public List<ProductList> getdata(){
        // DataModel dataModel = new DataModel();
        List<ProductList> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        ProductList productList = null;
        while (cursor.moveToNext()) {
            productList= new ProductList();
            String prod_main_img = cursor.getString(cursor.getColumnIndexOrThrow("prod_main_img"));
            String prod_name = cursor.getString(cursor.getColumnIndexOrThrow("prod_name"));
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));


            productList.setProduct_Image_URL(prod_main_img);
            productList.setProduct_Name(prod_name);
            productList.setProduct_Id(id);
            stringBuffer.append(productList);
            // stringBuffer.append(dataModel);

            data.add(productList);
        }

        for (ProductList mo:data ) {

            Log.i("Get Product Name :  ",""+mo.getProduct_Name());
        }

        //

        return data;
    }

}

