package com.example.myfinalprojectcs3270;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.myfinalprojectcs3270.Fragments.Account;
import com.example.myfinalprojectcs3270.Fragments.Home;
import com.example.myfinalprojectcs3270.Fragments.MyCart;
import com.example.myfinalprojectcs3270.Fragments.Popular;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Home())
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch(menuItem.getItemId()){
                case R.id.menu_home:
                    selectedFragment = new Home();
                    break;
                case R.id.menu_products:
                    selectedFragment = new Popular();
                    break;
                case R.id.menu_cart:
                    selectedFragment = new MyCart();
                    break;
                case R.id.menu_account:
                    selectedFragment = new Account();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                    .commit();

            return true;
        }
    };
}
