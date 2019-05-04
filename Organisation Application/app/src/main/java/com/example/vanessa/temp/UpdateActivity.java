package com.example.vanessa.temp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.example.vanessa.temp.MainActivity;
import com.example.vanessa.temp.app.AppConfig;
import com.example.vanessa.temp.app.MyApplication;
import com.example.vanessa.temp.helper.SQLiteHandler;
import com.example.vanessa.temp.helper.SessionManager;

/**
 * Created by vanessa on 22-06-2016.
 */
public class UpdateActivity extends AppCompatActivity {
    EditText phone, address;
    CheckBox clothes, food, amenities, books;
    TextView txtname,txtemail;
    public String phoneStr, addressStr, category = "", orgname, email,usid;

    private SQLiteHandler db;
    private SessionManager session;

    private String url1 = AppConfig.URL + "update.php";
    private ProgressDialog pDialog;
    private String TAG = UpdateActivity.class.getSimpleName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        Intent i=getIntent();
       String orgaddress= i.getStringExtra("orgaddress");
        String orgphone=i.getStringExtra("orgphone");

        txtname=(TextView)findViewById(R.id.name);
        txtemail=(TextView)findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        address.setText(orgaddress);
        phone.setText(orgphone);
        clothes = (CheckBox) findViewById(R.id.clothes);
        food = (CheckBox) findViewById(R.id.food);
        amenities = (CheckBox) findViewById(R.id.amenities);
        books = (CheckBox) findViewById(R.id.books);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading.....");
        pDialog.setCancelable(false);

        HashMap<String, String> user = db.getUserDetails();

        usid=user.get("uid");
        orgname = user.get("name");
        email = user.get("email");
        txtname.setText(orgname);
        txtemail.setText(email);


    }

    public void onSubmit(View view) {
        phoneStr = phone.getText().toString();
        addressStr = address.getText().toString();
        if (clothes.isChecked())
            category = category + "1";
        if (books.isChecked())
            category = category + "2";
        if (food.isChecked())
            category = category + "3";
        if (amenities.isChecked())
            category = category + "4";

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
                params.put("orgid", usid);
                params.put("orgname", orgname);
                params.put("orgphone", phoneStr);
                params.put("orgaddress", addressStr);
                params.put("category", category);

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(postRequest);
        session.setLogin(true);
        Intent i = new Intent(UpdateActivity.this, ImageUpload.class);
        i.putExtra("orgid",usid);
        startActivity(i);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            return super.onKeyDown(keyCode, event);
        }

    }
}
