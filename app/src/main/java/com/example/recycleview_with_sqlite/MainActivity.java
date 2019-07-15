package com.example.recycleview_with_sqlite;

import android.os.Bundle;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;



import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    String ParsingDta = "http://tl.devmantech.com/ws_products/allcategories";

    TextView textView1;

    ArrayList arrayList;

    String str = "",id,prod_name,prod_main_img;

   // ListView listView;

    Database database;
    ProductList productList;
    String urlforlogout = "http://tl.devmantech.com/ws_users/set_logout";
    String url11 = "http://tl.devmantech.com/ws_products/allcategories";
    RecyclerView recyclerView;
    //ArrayList<ProductList> feedsList = new ArrayList<ProductList>();
    ProductAdapter adapter;
    List<ProductList> datamodel;
    CircularProgressView progressView;
    private RecyclerView.LayoutManager layoutManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        database = new Database(this);
        progressView = (CircularProgressView) findViewById(R.id.progress_viewProduct);
        // this.deleteDatabase("EmployeeDatabase.db");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        database.getWritableDatabase();

        textView1 = (TextView) findViewById(R.id.textView1);

        //listView = (ListView) findViewById(R.id.listView);
    progressView.startAnimation();

        GetMethod_For_Display_HomeProducts();




        datamodel=  database.getdata();
        adapter =new ProductAdapter(MainActivity.this, (ArrayList<ProductList>) datamodel);


        Log.i("HIteshdata",""+datamodel);
        RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);




//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        // textView_no_search=(TextView)findViewById(R.id.textView_no_search);
//        recyclerView.setAdapter(adapter);
//
//        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());





//        try {
//
//            JSONObject jsonObject = new JSONObject(ParsingDta);
//
//            JSONArray jsonArray = jsonObject.getJSONArray("Employee");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                String id = jsonObject1.getString("id").toString();
//
//                String name = jsonObject1.getString("name").toString();
//
//                String salary = jsonObject1.getString("salary").toString();
//
//                database.insertData(id, name, salary);
//
//                str += "\n Employee" + i + "\n name:" + name + "\n id:" + id + "\n salary:" + salary + "\n";
//
//                //textView1.setText(str);
//
//            }
//
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//
//        }
//
//        arrayList = database.fetchData();
//
//        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.activity_list_item, android.R.id.text1, arrayList);
//
//        //listView.setAdapter(adapter);
//
    }


    public void GetMethod_For_Display_HomeProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url11, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    System.out.println("Response is : " + jsonObject.getString("status"));
//                    if (jsonObject.getInt("status_code") == 1) {

                    progressView.stopAnimation();
                    progressView.setVisibility(View.GONE);
                    JSONArray jsonArray2 = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        System.out.println("obj length" + jsonArray2.length());

                        JSONObject obj = jsonArray2.getJSONObject(i);

                        System.out.println("obj" + obj);


                         id = obj.getString("c_id");
                         prod_name = obj.getString("c_name");
                         prod_main_img = obj.getString("c_img");




                        database.insertData(prod_main_img,prod_name,id);
//                        System.out.println("prod_name" + prod_name + "\n" + "prod_main_img" + prod_main_img);
//                        // adding movie to movies array
//                        feeds = new ProductList(prod_main_img, id, prod_name);
//                        feedsList.add(feeds);
//                        adapter.notifyItemChanged(i);
                    }

//                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    //Notify adapter about data changes
                    //
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



//    @Override
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return true;
//
//    }}

}