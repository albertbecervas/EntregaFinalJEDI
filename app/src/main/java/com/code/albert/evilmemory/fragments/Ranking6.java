package com.code.albert.evilmemory.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.code.albert.evilmemory.activities.Ranking;
import com.code.albert.evilmemory.adapter.MyCustomAdapter;
import com.code.albert.evilmemory.adapter.RankingPlayer;
import com.code.albert.evilmemory.data.LoginHelper;
import com.code.albert.evilmemory.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ranking6 extends Fragment implements  View.OnClickListener{


    Button delete;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;

    ArrayList<RankingPlayer> rankingPlayers;

    MyCustomAdapter myAdapter;

    LoginHelper loginHelper;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Ranking6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ranking6, container, false);

        loginHelper = new LoginHelper(getActivity().getApplicationContext());

        rankingPlayers = new ArrayList<>(0);

        Cursor cursor = loginHelper.getRanking6();
        RankingPlayer pos;
        if (cursor.moveToFirst()) {
            do {
                String u = cursor.getString(cursor.getColumnIndex("name"));
                int p = cursor.getInt(cursor.getColumnIndex("score6"));
                pos = new RankingPlayer(0, u, p);
                rankingPlayers.add(pos);
            } while (cursor.moveToNext());
        }

        delete=(Button) view.findViewById(R.id.button);
        delete.setOnClickListener(this);

        sharedPreferences = getActivity().getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("activity",6);
        editor.apply();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);

        mLinearLayout = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLinearLayout);

        myAdapter = new MyCustomAdapter(rankingPlayers);

        mRecyclerView.setAdapter(myAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            LoginHelper LoginHelper = new LoginHelper(getActivity().getApplicationContext());
            LoginHelper.DeleteRanking6();
            Cursor cursor = loginHelper.getRanking6();
            RankingPlayer pos;
            if (cursor.moveToFirst()) {
                do {
                    String u = cursor.getString(cursor.getColumnIndex("name"));
                    int p = cursor.getInt(cursor.getColumnIndex("score4"));
                    pos = new RankingPlayer(0, u, p);
                    rankingPlayers.add(pos);
                } while (cursor.moveToNext());
            }

            myAdapter.setData(rankingPlayers);
            myAdapter.notifyDataSetChanged();

            /*FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.detach(this);
            ft.attach(this);
            ft.commit();*/

            startActivity(new Intent(getActivity().getApplicationContext(), Ranking.class));
            getActivity().finish();


        } else {
            Toast.makeText(getActivity().getApplicationContext(), "nbgfxn", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}



