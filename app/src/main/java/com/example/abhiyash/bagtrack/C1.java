package com.example.abhiyash.bagtrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.firebase.client.Firebase;

import java.sql.Connection;

public class C1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    EditText e1,e2,e3;
    Button b1;
    RadioGroup rg;
    Firebase fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(this);
        rg=(RadioGroup)findViewById(R.id.radiogroup);

        Firebase.setAndroidContext(this);
        fb=new Firebase("https://baggage-tracking-fe250.firebaseio.com/");
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
        getMenuInflater().inflate(R.menu.c1, menu);
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

        if (id == R.id.track_baggage) {
            Intent it=new Intent(this,Tb1.class);
            startActivity(it);
        } else if (id == R.id.complain) {
            Intent it1=new Intent(this,C1.class);
            startActivity(it1);
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
String s="",s1="",s2="",s3="";
        s=e1.getText().toString();
        s1=e2.getText().toString();
        s3=e3.getText().toString();
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.radioButton7:
                s2="Baggage Lost";
                break;
            case R.id.radioButton6:
                s2="Baggage Mishandled";
                break;
            case R.id.radioButton4:
                s2="Other";
                break;
        }
        ComplainSave cs=new ComplainSave();
        cs.setTicket_id(s1);
        cs.setName(s);
        cs.setComplain(s2);
        cs.setSuggestion(s3);
        fb.child("Complaints").child(s1).setValue(cs);
    }



}
