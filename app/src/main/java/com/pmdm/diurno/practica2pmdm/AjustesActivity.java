package com.pmdm.diurno.practica2pmdm;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class AjustesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DineroFragment dineroFragment;
    private CombinacionFragment combinacionFragment;
    private AjustesFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.contenedor);

        dineroFragment = new DineroFragment();
        combinacionFragment = new CombinacionFragment();

        adapter = new AjustesFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class AjustesFragmentPagerAdapter extends FragmentPagerAdapter{

        public AjustesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return dineroFragment;
                case 1:
                    return combinacionFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Dinero";
                case 1:
                    return "Combinaciones";
            }
            return null;
        }
    }

}
