package com.example.dell.sac;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.sac.app.MyApplication;
import com.example.dell.sac.helper.Movie;
import com.example.dell.sac.helper.SQLiteHandler;
import com.example.dell.sac.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SingleTrackActivity extends AppCompatActivity {
    String orgid, urlpart1;
    private ProgressDialog pDialog;
    String url1 = "http://192.168.0.106/u206583551_swipe/request.php";

    private String URL_TOP_250 = "http://192.168.0.106/u206583551_swipe/getData1.php?orgid=";
    String data;
    TextView textView,textView1;
  SQLiteDatabase db;
    String uid;
    String id;
    String name,email;
    private Cursor c;

    public static final String TAG = MyApplication.class
            .getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_track);
        db=openOrCreateDatabase("android_api", Context.MODE_PRIVATE,null);
        c= db.rawQuery("SELECT * FROM user", null);
        c.moveToFirst();
        showrecords();

        Intent i = getIntent();
        orgid = i.getStringExtra("orgid");

        textView = (TextView) findViewById(R.id.textView3);

       // user=db.getUserID();
        getData();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading.....");
        pDialog.setCancelable(false);



        //textView.setText(data);
        // urlpart1=i.getStringExtra("urlpart");
    }

    private void getData() {

        String url = URL_TOP_250 + orgid;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingleTrackActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void showrecords()
    {uid=c.getString(3);
        textView1=(TextView)findViewById(R.id.textView5);
        textView1.setText(uid);}

    private void showJSON(String response) {

        try {
            JSONObject movieObj = new JSONObject(response);

            JSONArray result = movieObj.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            int rank = collegeData.getInt("orgid");
            String title = collegeData.getString("orgname");
            String address = collegeData.getString("orgaddress");
            int phone = collegeData.getInt("orgphone");
            String rankString = Integer.toString(rank);
            String phoneString = Integer.toString(phone);

            data = rankString + title + address + phoneString;

            textView.setText(data);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void onDonate(View v) {



        pDialog.show();
     //   item_name = item_et.getText().toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                       // item_et.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("orgid", orgid);
                params.put("uid",uid);
                return params;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(postRequest);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
