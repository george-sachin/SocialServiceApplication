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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String category;
    private SQLiteHandler db;
    private SessionManager session;
    CheckBox adults,kids,blankets,sports,firstaid,utensils,stationery,toys,magazines,novels,storybooks,notebooks,veg,nonveg,grains,pulses,chocolate;
    EditText descriptionclothes,descriptionamenities,descriptionbooks,descriptionfood,descriptiongroceries;
    DatePicker datePickerclothes,datePickeramenities,datePickerbooks,datePickerfood,datePickergroceries;
    String description;
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

            case R.id.about:
                about();
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



    private void about() {

        Intent i = new Intent(MainActivity.this, AboutActivity.class);
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
        description="Clothes: ";
        adults=(CheckBox)findViewById(R.id.adultscheckBox);
        kids=(CheckBox)findViewById(R.id.kidscheckBox);
        blankets=(CheckBox)findViewById(R.id.blanketscheckBox);
        descriptionclothes=(EditText)findViewById(R.id.descriptionclothes);
        datePickerclothes=(DatePicker) findViewById(R.id.datePickerclothes);
        String date=Integer.toString(datePickerclothes.getDayOfMonth())+"/"+Integer.toString(datePickerclothes.getMonth()+1)+"/"
                +Integer.toString(datePickerclothes.getYear());


        if (adults.isChecked())
            description=description+"Adults ";
        if (kids.isChecked())
            description=description+"Kids ";
        if (blankets.isChecked())
            description=description+"Blankets ";

       description=description+" Description:"+descriptionclothes.getText().toString()+" Date:"+date;



        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key", category);
        i.putExtra("description",description);
        startActivity(i);
    }
    public  void onAmenitiesSubmit(View v){
        category="amenities.php";
        description="Amenities: ";

        sports=(CheckBox)findViewById(R.id.sportscheckBox);

        stationery=(CheckBox)findViewById(R.id.stationerycheckBox);
        utensils=(CheckBox)findViewById(R.id.utensilscheckBox);
        toys=(CheckBox)findViewById(R.id.toyscheckBox);
        firstaid=(CheckBox)findViewById(R.id.firstaidcheckBox);

        descriptionamenities=(EditText)findViewById(R.id.descriptionamenities);
        datePickeramenities=(DatePicker) findViewById(R.id.datePickeramenities);

        String date=Integer.toString(datePickeramenities.getDayOfMonth())+"/"+Integer.toString(datePickeramenities.getMonth()+1)+"/"
                +Integer.toString(datePickeramenities.getYear());

        if (firstaid.isChecked())
            description=description+"First Aid ";
        if (toys.isChecked())
            description=description+"Toys ";
        if (utensils.isChecked())
            description=description+"Utensils ";
        if (sports.isChecked())
            description=description+"Sports ";
        if (stationery.isChecked())
            description=description+"Stationery ";


        description=description+" Description: "+descriptionamenities.getText().toString()+" Date:"+date;

        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key", category);
        i.putExtra("description",description);
        startActivity(i);
    }
    public  void onGrocerySubmit(View v){
        category="amenities.php";
        description="Groceries: ";

        grains=(CheckBox)findViewById(R.id.grainscheckBox);
        chocolate=(CheckBox)findViewById(R.id.chocolatecheckBox);
        pulses=(CheckBox)findViewById(R.id.pulsescheckBox);

        descriptiongroceries=(EditText)findViewById(R.id.descriptiongroceries);
        datePickergroceries=(DatePicker) findViewById(R.id.datePickergroceries);

        String date=Integer.toString(datePickergroceries.getDayOfMonth())+"/"+Integer.toString(datePickergroceries.getMonth()+1)+"/"
                +Integer.toString(datePickergroceries.getYear());

        if (pulses.isChecked())
            description=description+"Pulses ";
        if (grains.isChecked())
            description=description+"Grains ";
        if (chocolate.isChecked())
            description=description+"Chocolates ";
        description=description+" Description: "+descriptiongroceries.getText().toString()+" Date:"+date;


        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        i.putExtra("description",description);
        startActivity(i);
    }
    public  void onStationarySubmit(View v){
        category="books.php";
        description="Books: ";
        notebooks=(CheckBox)findViewById(R.id.notebookcheckBox);
        magazines=(CheckBox)findViewById(R.id.magazinescheckBox);
        storybooks=(CheckBox)findViewById(R.id.storybookcheckBox);
        novels=(CheckBox)findViewById(R.id.novelcheckBox);
        descriptionbooks=(EditText)findViewById(R.id.descriptionbooks);
        datePickerbooks=(DatePicker) findViewById(R.id.datePickerbooks);
        String date=Integer.toString(datePickerbooks.getDayOfMonth())+"/"+Integer.toString(datePickerbooks.getMonth()+1)+"/"
                +Integer.toString(datePickerbooks.getYear());


        if (notebooks.isChecked())
            description=description+"Notebooks ";
        if (magazines.isChecked())
            description=description+"Magazines ";
        if (storybooks.isChecked())
            description=description+"Story Books ";
        if (novels.isChecked())
            description=description+"Novels ";
        description=description+" Description: "+descriptionbooks.getText().toString()+" Date:"+date;




        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        i.putExtra("description",description);
        startActivity(i);
    }
    public  void onFoodSubmit(View v){
        category="food.php";
        description="Food: ";
        veg=(CheckBox)findViewById(R.id.vegcheckBox);
        nonveg=(CheckBox)findViewById(R.id.nonvegcheckBox);

        descriptionfood=(EditText)findViewById(R.id.descriptionfood);
        datePickerfood=(DatePicker) findViewById(R.id.datePickerfood);
        String date=Integer.toString(datePickerfood.getDayOfMonth())+"/"+Integer.toString(datePickerfood.getMonth()+1)+"/"
                +Integer.toString(datePickerfood.getYear());


        if (veg.isChecked())
            description=description+"Vegitarian ";
        if (nonveg.isChecked())
            description=description+"Non-vegetarian ";

        description=description+" Description: "+descriptionfood.getText().toString()+" Date:"+date;


        Intent i=new Intent(this, ListActivity.class);
        i.putExtra("key",category);
        i.putExtra("description",description);
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










