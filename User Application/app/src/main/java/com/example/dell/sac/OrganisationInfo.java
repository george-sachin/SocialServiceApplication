package com.example.dell.sac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dell.sac.app.AppConfig;
import com.example.dell.sac.app.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrganisationInfo extends AppCompatActivity {
    public static final String TAG = MyApplication.class
            .getSimpleName();
    private ProgressDialog pDialog;
    TextView txtname,txtaddress,txtphone,txtemail;
    ImageView profileimage;
    private String URL_TOP_250 = AppConfig.URL+"getOrgDetails.php?orgname=";
    String orgname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_info);

        Intent i = getIntent();
        orgname = i.getStringExtra("orgname");

        getData();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading.....");
        pDialog.setCancelable(false);

    }


    private void getData() {

        String url = URL_TOP_250 + orgname;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OrganisationInfo.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void showJSON(String response) {

        try {
            JSONObject movieObj = new JSONObject(response);

            JSONArray result = movieObj.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            String rank = collegeData.getString("email");
            String title = collegeData.getString("orgname");
            String address = collegeData.getString("orgaddress");
            String phone = collegeData.getString("orgphone");
            String photo=collegeData.getString("photo");

            txtname=(TextView) findViewById(R.id.orgname);
            txtaddress=(TextView)findViewById(R.id.orgaddress);
            txtemail=(TextView)findViewById(R.id.orgemail);
            txtphone=(TextView)findViewById(R.id.orgphone);
            profileimage=(ImageView)findViewById(R.id.profileimage);
            Glide.with(OrganisationInfo.this).load(photo)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileimage);

            txtname.setText(title);
            txtaddress.setText(address);
            txtphone.setText(phone);
            txtemail.setText(rank);




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void onCall(View v)
    {String p = "tel:" + txtphone.getText().toString();

      Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(p));
        startActivity(callIntent);

    }

}
