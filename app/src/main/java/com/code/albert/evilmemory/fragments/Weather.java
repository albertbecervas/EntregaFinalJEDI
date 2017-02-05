package com.code.albert.evilmemory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.adapter.WeatherResponse;
import com.code.albert.evilmemory.interfaces.WeatherService;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weather extends Fragment {


    ButterKnife butterKnife;
    private Unbinder unbinder;

    TextView celcius;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    WeatherService service = retrofit.create(WeatherService.class);

    String result;

    public Weather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_weather, container, false);

        unbinder = butterKnife.bind(this, view);

        String appid="31b6b748c4c0db46e501ca60f2df064c";


        service.getWeather(41.3818,2.1685,appid).enqueue(new retrofit2.Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful()){
                    WeatherResponse weatherResponse = response.body();
                    result = weatherResponse.getWeathermain();
                    TextView t= (TextView) getActivity().findViewById(R.id.temp);
                    t.setText(result);
                    Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"OH NO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        celcius = (TextView) getActivity().findViewById(R.id.temp);
        return view;
    }



}
