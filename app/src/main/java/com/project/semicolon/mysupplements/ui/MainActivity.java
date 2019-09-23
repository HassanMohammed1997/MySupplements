package com.project.semicolon.mysupplements.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;
import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.utils.AppUtil;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_share:
                AppUtil.share(this, "Share this app with your friends", "Share");
                break;
            case R.id.nav_bmr:
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.BMRFragment);
                break;
            case R.id.nav_protein:
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.proteinFragment);
                break;
            case R.id.nav_fat_burner:
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.fatBurnerFragment);
                break;
            case R.id.nav_category:
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.categoriesFragment);
                break;
            case R.id.nav_home:
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.mainFragment);
            default:
                break;


        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideToolbar(boolean hide){
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
