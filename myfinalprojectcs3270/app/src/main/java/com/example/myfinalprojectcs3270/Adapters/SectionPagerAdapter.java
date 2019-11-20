package com.example.myfinalprojectcs3270.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myfinalprojectcs3270.Fragments.Account;
import com.example.myfinalprojectcs3270.Fragments.Home;
import com.example.myfinalprojectcs3270.Fragments.MyCart;
import com.example.myfinalprojectcs3270.Fragments.Popular;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Popular();
            case 2:
                return new MyCart();
            case 3:
                return new Account();
            default:
                return new Home();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Popular";
            case 2:
                return "My Cart";
            case 3:
                return "Account";
        }
        return null;
    }
}
