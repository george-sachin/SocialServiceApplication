package com.example.dell.sac;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dell.sac.app.AppConfig;
import com.example.dell.sac.app.MyApplication;
import com.example.dell.sac.helper.Movie;
import com.example.dell.sac.helper.SwipeListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ListActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = ListActivity.class.getSimpleName();
    String rank,title;
    private String URL_TOP_250 =  AppConfig.URL;
    private String urlpart,description;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListAdapter adapter;
    private List<Movie> movieList;

    // initially offset will be 0, later will be updated while parsing the json
    private int offSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);
        Intent i=getIntent();
        urlpart=i.getStringExtra("key");
        description=i.getStringExtra("description");
        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        movieList = new ArrayList<Movie>();
        adapter = new SwipeListAdapter(this, movieList);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchMovies();
                                    }
                                }
        );


   //     ListView lv = getListView();

        /**
         * Listview on item click listener
         * SingleTrackActivity will be lauched by passing album id, song id
         * */
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                // On selecting single track get song information
                Intent i = new Intent(getApplicationContext(), SingleTrackActivity.class);

                // to get song information
                // both album id and song is needed
              // String album_id = ((TextView) view.findViewById(R.id.album_id)).getText().toString();
                String orgid = ((TextView) view.findViewById(R.id.rating)).getText().toString();
/*
                Toast.makeText(getApplicationContext(), "Album Id: " + album_id + ", Song Id: " + song_id, Toast.LENGTH_SHORT).show();
*/
               // String orgid=Integer.toString(rank);
                i.putExtra("orgname", title);
                i.putExtra("description",description);
                i.putExtra("orgid",orgid);
             //   i.putExtra("urlpart", urlpart);

                startActivity(i);
            }
        });

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        fetchMovies();
    }

    /**
     * Fetching movies json by making http call
     */
    private void fetchMovies() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        // appending offset to url
        String url = URL_TOP_250+urlpart;

        // Volley's json array request object
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {
                            movieList.clear();
                            // looping through json and adding to movies list
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject movieObj = response.getJSONObject(i);

                                  /*  int*/ rank = movieObj.getString("orgid");
                                     title = movieObj.getString("orgname");
                                    String address = movieObj.getString("orgaddress");
                                    String phone = movieObj.getString("orgphone");
                                    String thumbnailUrl=movieObj.getString("photo");

                                    Movie m = new Movie(rank,title,address,phone,thumbnailUrl);

                                    movieList.add(0, m);

                                    // updating offset value to highest value
                               /*    if (rank >= offSet)
                                        offSet = rank;
*/
                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }

                        // stopping swipe refresh
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);
    }

}
