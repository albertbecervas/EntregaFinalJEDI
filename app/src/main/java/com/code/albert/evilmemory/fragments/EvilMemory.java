package com.code.albert.evilmemory.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.activities.Memory4;
import com.code.albert.evilmemory.activities.Memory6;
import com.code.albert.evilmemory.activities.Memory8;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvilMemory extends Fragment {



    ButterKnife butterKnife;
    private Unbinder unbinder;

    public EvilMemory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_evil_memory, container, false);

        unbinder = butterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.easy,R.id.medium,R.id.hard})
    public void on_click(View view){
        switch (view.getId()){
            case R.id.easy:
                startActivity(new Intent(getActivity().getApplicationContext(), Memory4.class));
                break;
            case R.id.medium:
                startActivity(new Intent(getActivity().getApplicationContext(), Memory6.class));
                break;
            case R.id.hard:
                startActivity(new Intent(getActivity().getApplicationContext(), Memory8.class));
                break;
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
