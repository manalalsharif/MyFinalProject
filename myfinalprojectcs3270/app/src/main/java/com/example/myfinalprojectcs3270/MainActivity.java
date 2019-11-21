package com.example.myfinalprojectcs3270;

import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;

import com.example.myfinalprojectcs3270.Adapters.SectionPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private SectionPagerAdapter sectionPagerAdapter;
    private MenuItem previousMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up the toolbar for the activity.
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }

        //States for the bottom navigation menu
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{-android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_window_focused}
        };

        //Defining colors for the bottom navigation menu.
        int[] homeColors = new int[]{
                ContextCompat.getColor(this, R.color.primary_now_playing),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.primary_now_playing)
        };

        int[] movieColors = new int[]{
                ContextCompat.getColor(this, R.color.primary_popular),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.primary_popular)
        };

        int[] cartColors = new int[]{
                ContextCompat.getColor(this, R.color.primary_top),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.primary_top)
        };

        int[] accountColors = new int[]{
                ContextCompat.getColor(this, R.color.primary_favorites),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.materialBlack),
                ContextCompat.getColor(this, R.color.primary_favorites)
        };

        final ColorStateList homeColorList = new ColorStateList(states, homeColors);
        final ColorStateList movieColorList = new ColorStateList(states, movieColors);
        final ColorStateList cartColorList = new ColorStateList(states, cartColors);
        final ColorStateList accountColorList = new ColorStateList(states, accountColors);

        //Initialising the major containers
        bottomNavigationView = findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setItemIconTintList(homeColorList);

        viewPager = findViewById(R.id.view_pager_main);
        sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionPagerAdapter);

        //Handling the click listeners for the bottom navigation.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu_item:
                        viewPager.setCurrentItem(0);
                        bottomNavigationView.setItemIconTintList(homeColorList);
                        bottomNavigationView.setItemTextColor(homeColorList);
                        break;
                    case R.id.movies_menu_item:
                        viewPager.setCurrentItem(1);
                        bottomNavigationView.setItemIconTintList(movieColorList);
                        bottomNavigationView.setItemTextColor(movieColorList);
                        break;
                    case R.id.cart_menu_item:
                        viewPager.setCurrentItem(2);
                        bottomNavigationView.setItemIconTintList(cartColorList);
                        bottomNavigationView.setItemTextColor(cartColorList);
                        break;
                    case R.id.account_menu_item:
                        viewPager.setCurrentItem(3);
                        bottomNavigationView.setItemIconTintList(accountColorList);
                        bottomNavigationView.setItemTextColor(accountColorList);
                        break;
                }
                return false;
            }
        });

        //Adding the page scroll listener.
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setItemIconTintList(homeColorList);
                        bottomNavigationView.setItemTextColor(homeColorList);
                        break;
                    case 1:
                        bottomNavigationView.setItemIconTintList(movieColorList);
                        bottomNavigationView.setItemTextColor(movieColorList);
                        break;
                    case 2:
                        bottomNavigationView.setItemIconTintList(cartColorList);
                        bottomNavigationView.setItemTextColor(cartColorList);
                        break;
                    case 3:
                        bottomNavigationView.setItemIconTintList(accountColorList);
                        bottomNavigationView.setItemTextColor(accountColorList);
                        break;
                    default:
                        bottomNavigationView.setItemIconTintList(homeColorList);
                        bottomNavigationView.setItemTextColor(homeColorList);
                        break;
                }

                if (previousMenuItem != null) {
                    previousMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                previousMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
