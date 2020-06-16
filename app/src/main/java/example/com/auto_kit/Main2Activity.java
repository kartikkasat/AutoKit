package example.com.auto_kit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView user_name,vehicle_name1,vehicle_no1,fuel_capacity1,average1;
    SharedPreferences sharedPreferences;
    String user_info;
    String info[];
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);
        user_name=headerView.findViewById(R.id.name);
        vehicle_name1=headerView.findViewById(R.id.vehicle_name);
        vehicle_no1=headerView.findViewById(R.id.vehicle_no);
        fuel_capacity1=headerView.findViewById(R.id.fuel_capacity);
        average1=headerView.findViewById(R.id.average);
        sharedPreferences=getSharedPreferences("flagdata", Context.MODE_PRIVATE);
        user_info=sharedPreferences.getString("user_info","");
        flag=sharedPreferences.getInt("flag",1);
        if (flag==2) {
            info = user_info.split(",");
            user_name.setText(info[0]);
            vehicle_name1.setText(info[1]);
            vehicle_no1.setText(info[2]);
            fuel_capacity1.setText(info[3]);
            average1.setText(info[4]);
        }
        HomeFragment homeFragment=new HomeFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.imp_screen,homeFragment);
        ft.commit();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment=null;
        int id = item.getItemId();
        if (id == R.id.Home) {
            fragment=new HomeFragment();
        } else if (id == R.id.summary) {
            fragment=new SummaryFragment();
        } else if (id == R.id.cost) {
            fragment=new CostFragment();
        } else if (id == R.id.graph) {
            fragment=new GraphFragment();
        }
        if (fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.imp_screen,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
