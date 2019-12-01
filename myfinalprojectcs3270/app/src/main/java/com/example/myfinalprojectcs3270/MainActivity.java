package com.example.myfinalprojectcs3270;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.myfinalprojectcs3270.Fragments.Account;
import com.example.myfinalprojectcs3270.Fragments.CartDialog;
import com.example.myfinalprojectcs3270.Fragments.Home;
import com.example.myfinalprojectcs3270.Fragments.MyCart;
import com.example.myfinalprojectcs3270.Fragments.Popular;
import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements CartDialog.sendCartList {
    //Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_shopping_cart_white));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, new Home())
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

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_main, selectedFragment)
                    .commit();

            return true;
        }
    };

    @Override
    public void addStuffToCart(MyCartItem newItem) {
        MyCart mc = (MyCart)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container_main);
        mc.addStuffToCart(newItem);
    }
}
