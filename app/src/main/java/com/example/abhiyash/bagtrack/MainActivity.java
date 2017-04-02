package com.example.abhiyash.bagtrack;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    EditText e1;
    Button b1;
    Firebase fb;
    String s="";
    private final int REQ_CODE_SPEECH_INPUT =100;
    TextView tv;
  Button iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        e1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(this);
        tv=(TextView)findViewById(R.id.textView3);
        iv=(Button) findViewById(R.id.button2);
        iv.setOnClickListener(this);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Firebase.setAndroidContext(this);
        fb=new Firebase("https://baggage-tracking-fe250.firebaseio.com/T&B");
        startService(new Intent(MainActivity.this,NewService.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.track_baggage)
        {
            Intent it=new Intent(this,Tb1.class);
            startActivity(it);
        }
        else if (id == R.id.complain)
        {
            Intent it1=new Intent(MainActivity.this,C1.class);
            startActivity(it1);
        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        s = e1.getText().toString();
        switch(v.getId())
        {
            case R.id.button:
                check();
                break;
            case R.id.button2:
                e1.setText("");

                promptinputsearch();
                s=e1.getText().toString();
              //  check();
                break;
        }




    }
       public void check()
       { try
       {
            com.firebase.client.Query q=fb.orderByKey();
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int flag=0;
                    System.out.println("1");
                    for(DataSnapshot sn:dataSnapshot.getChildren())
                    {

                       // CheckerBagId fp=sn.getValue(CheckerBagId.class);
                        WorkArey wa=sn.getValue(WorkArey.class);
                        String s2=wa.ticket_id;
                        System.out.println(s2);
                            if (flag==0)
                            {

                                if(s2.equals(s))
                                {
                                    Intent it=new Intent(MainActivity.this,Tb1.class);
                                    startActivity(it);
                                    flag++;
                                    SharedPreferences msh=getSharedPreferences("My", Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor edit=msh.edit();
                                    edit.putString("Ticket_id",s2);
                                    edit.putString("Baggage_id",wa.bag_id);
                                    edit.commit();

                                    break;


                                }
                            }
                    }
                    if(flag==0)
                    {
                        Toast.makeText(MainActivity.this, "Invalid PNR number", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
private void promptinputsearch(){
    Intent it=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    it.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    it.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speech_prompt));
    try{
      startActivityForResult(it, REQ_CODE_SPEECH_INPUT);
    }
    catch (Exception e)
    {
        Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
    }
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data)
                {
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   tv.setText(result.get(0).toString());
                    Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}

