package com.example.dell.sac;
import com.example.dell.sac.helper.SQLiteHandler;
import com.example.dell.sac.helper.SessionManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String category;
    private SQLiteHandler db;
    private SessionManager session;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Main frags = new Main();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();*/

        frameanima();
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

      /*  if (!session.isLoggedIn()) {
            logoutUser();
        }
*/
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.past:
                past();
                return true;

            case R.id.logout:
               logoutUser();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void past() {

        Intent i = new Intent(MainActivity.this, PastActivity.class);
        startActivity(i);
        finish();
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Launching new activity
     * */

    public void myCloth(View v) {
        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setVisibility(View.GONE);
        Fragment2 frag2=new Fragment2();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction2=manager.beginTransaction();
        transaction2.replace(R.id.frag, frag2, "fragclothkey");
        transaction2.commitAllowingStateLoss();
    }
    public void myBook(View v){
        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setVisibility(View.GONE);

        FragmentBook fragb=new FragmentBook();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transactionb=manager.beginTransaction();
        transactionb.replace(R.id.frag,fragb,"fragbookkey");
        transactionb.commitAllowingStateLoss();
    }
    public void myGrocery(View v){
        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setVisibility(View.GONE);

        FragmentGrocery fragg=new FragmentGrocery();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transactiong=manager.beginTransaction();
        transactiong.replace(R.id.frag,fragg,"fraggrocerykey");
        transactiong.commitAllowingStateLoss();
    }
    public void myBlanket(View v){
        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setVisibility(View.GONE);

        FragmentBlanket fragbl=new FragmentBlanket();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transactionbl=manager.beginTransaction();
        transactionbl.replace(R.id.frag, fragbl, "fragblanketkey");
        transactionbl.commitAllowingStateLoss();
    }
    public void myFood(View v){

        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setVisibility(View.GONE);
        FragmentFood fragf=new FragmentFood();
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transactionf=manager.beginTransaction();
        transactionf.replace(R.id.frag, fragf, "fragfoodkey");
        transactionf.commitAllowingStateLoss();
    }
    public  void onClothesSubmit(View v){
        category="clothes.php";

        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key", category);
        startActivity(i);
    }
    public  void onUtilitiesSubmit(View v){
        category="utilities.php";
        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        startActivity(i);
    }
    public  void onGrocerySubmit(View v){
        category="grocery.php";

        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        startActivity(i);
    }
    public  void onStationarySubmit(View v){
        category="utilities.php";

        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        startActivity(i);
    }
    public  void onFoodSubmit(View v){
        category="food.php";

        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        startActivity(i);
    }
    public void frameanima()
    {

        AnimationDrawable frameanim;
        ImageView iv=(ImageView)findViewById(R.id.imageV);
        iv.setImageResource(R.drawable.frame_anim);

        // Typecasting the Animation Drawable
        frameanim = (AnimationDrawable)iv.getDrawable();
        frameanim.start();
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


}










