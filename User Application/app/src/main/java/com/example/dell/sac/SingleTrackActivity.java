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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dell.sac.app.AppConfig;
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
    String organisation, urlpart1,orgid;
    String description;
    private ProgressDialog pDialog;
    String url1 = AppConfig.URL+"request.php";

    private String URL_TOP_250 = AppConfig.URL+"getData1.php?orgid=";
    String data;
TextView orgname,orgphone,orgemail,orgaddress;
    ImageView profileimage;
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
        Intent i = getIntent();
        organisation = i.getStringExtra("orgname");
        description=i.getStringExtra("description");
        orgid=i.getStringExtra("orgid");
       showrecords();

        profileimage = (ImageView) findViewById(R.id.profileimage);
       orgname = (TextView) findViewById(R.id.orgname);
        orgemail = (TextView) findViewById(R.id.orgemail);
        orgphone = (TextView) findViewById(R.id.orgphone);
        orgaddress = (TextView) findViewById(R.id.orgaddress);


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
     //   textView1=(TextView)findViewById(R.id.textView5);
     //   textView1.setText(description);
       }

    private void showJSON(String response) {

        try {
            JSONObject movieObj = new JSONObject(response);

            JSONArray result = movieObj.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            String email = collegeData.getString("email");
            String title = collegeData.getString("orgname");
            String address = collegeData.getString("orgaddress");
            String phone = collegeData.getString("orgphone");
            String photo= collegeData.getString("photo");

            orgname.setText(title);
            orgemail.setText(email);
            orgphone.setText(phone);
            orgaddress.setText(address);


            Glide.with(SingleTrackActivity.this).load(photo)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileimage);



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
                params.put("info",description);
                return params;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(postRequest);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
