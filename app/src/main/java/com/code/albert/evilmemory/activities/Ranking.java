package com.code.albert.evilmemory.activities;

import android.location.Address;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.fragments.Ranking4;
import com.code.albert.evilmemory.fragments.Ranking6;
import com.code.albert.evilmemory.fragments.Ranking8;

import java.util.List;

public class Ranking extends NavigationDrawer{

    BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        bottomNavigationItemView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationItemView.getMenu().getItem(0).setChecked(true);

        //Load fragment for first time
        Fragment f = new Ranking4();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ranking_container, f, "FRAGMENt")
                .commit();



        bottomNavigationItemView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.rank4:
                                Fragment f = new Ranking4();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f, "ranking4")
                                        .commit();
                                break;
                            case R.id.rank6:
                                Fragment f2 = new Ranking6();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f2, "ranking6")
                                        .commit();
                                break;
                            case R.id.rank8:
                                Fragment f3 = new Ranking8();
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.ranking_container, f3, "ranking8")
                                        .commit();
                                break;

                        }
                        return true;
                    }
                });
    }
}
