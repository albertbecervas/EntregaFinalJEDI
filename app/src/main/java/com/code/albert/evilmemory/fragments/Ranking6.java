package com.code.albert.evilmemory.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.code.albert.evilmemory.adapter.MyCustomAdapter;
import com.code.albert.evilmemory.adapter.RankingPlayer;
import com.code.albert.evilmemory.data.LoginHelper;
import com.code.albert.evilmemory.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ranking6 extends Fragment {


    Button delete;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;

    ArrayList<RankingPlayer> rankingPlayers;

    MyCustomAdapter myAdapter;

    LoginHelper loginHelper;

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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginHelper LoginHelper = new LoginHelper(getActivity().getApplicationContext());
                LoginHelper.DeleteRanking6();
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

                myAdapter.setData(rankingPlayers);
                myAdapter.notifyDataSetChanged();

            }

        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);

        mLinearLayout = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLinearLayout);

        myAdapter = new MyCustomAdapter(rankingPlayers);

        mRecyclerView.setAdapter(myAdapter);
        // Inflate the layout for this fragment
        return view;
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



