package com.example.vanessa.temp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vanessa.temp.app.AppConfig;
import com.example.vanessa.temp.helper.SQLiteHandler;

import java.util.HashMap;

import android.app.Activity;
import android.widget.Toast;


import com.example.vanessa.temp.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView txtname;
    private TextView txtaddress;
    private TextView txtphone;
    private TextView txtemail,notify;
    ListView listView;

    ImageView profileimage;

    private Button btnLogout;
    public String uid,orgname,orgemail,orgaddress,orgphone,photo;

    private SQLiteHandler db;

    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new SQLiteHandler(getApplicationContext());
txtname=(TextView)findViewById(R.id.orgname);
        txtaddress=(TextView)findViewById(R.id.orgaddress);
        txtphone=(TextView)findViewById(R.id.orgphone);
        txtemail=(TextView)findViewById(R.id.orgemail);
        profileimage=(ImageView)findViewById(R.id.profileimage);

        // session manager

        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logout();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

         uid = user.get("uid");
        orgemail=user.get("email");
        orgname=user.get("name");


        Intent i=new Intent(this,UpdateActivity.class);
        i.putExtra("uid",uid);
        i.putExtra("orgname",orgname);

        getData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

                intent.putExtra("orgaddress",orgaddress);
                intent.putExtra("orgphone",orgphone);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.action_check_updates:
                Requests();
                return true;

            case R.id.past:
                pastActivity();
                return true;

            case R.id.about:
                about();
                return true;



            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Launching new activity
     * */
    private void about() {

        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
        finish();
    }
    private void Requests() {
        Intent i = new Intent(this, Requests.class);
        startActivity(i);
    }
    public void logout() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void pastActivity(){
        Intent intentNew=new Intent(MainActivity.this,PastActivity.class);
        intentNew.putExtra("uid",uid);
        startActivity(intentNew);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void getData() {

        String url = AppConfig.URL+"getOrgProfile.php?orgid=" + uid;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void showJSON(String response) {

        try {
            JSONObject movieObj = new JSONObject(response);

            JSONArray result = movieObj.getJSONArray(AppConfig.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            orgaddress = collegeData.getString("orgaddress");
            orgphone = collegeData.getString("orgphone");
            photo=collegeData.getString("photo");

            Glide.with(getApplicationContext()).load(photo)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileimage);
            txtname.setText(orgname);
            txtemail.setText(orgemail);
            txtaddress.setText(orgaddress);
            txtphone.setText(orgphone);





        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



}

