package com.code.albert.evilmemory.activities.other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.code.albert.evilmemory.activities.NavigationDrawer;
import com.code.albert.evilmemory.fragments.Ranking4;
import com.code.albert.evilmemory.fragments.SaveWeather;
import com.code.albert.evilmemory.fragments.Weather;


public class PagerAdapter extends FragmentPagerAdapter {


    private String tabTitles[] = new String[] { "Current Location", "Set" };
    private Fragment tab = null;

    //creadora
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //crea las tabas, siempre tiene que retornar con el numero de tabs que queremos mostrar
    @Override
    public int getCount() {
        return tabTitles.length;
    }

    //Lanza el fragment asociado con el número de tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                tab = new Weather();
                break;
            case 1:
                tab = new SaveWeather();
                break;
        }
        return tab;
    }

    //pone el nombre en cada tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Genera le título en función de la posición
        return tabTitles[position];
    }
}