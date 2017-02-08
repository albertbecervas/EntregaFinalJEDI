package com.code.albert.evilmemory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class SaveWeather extends Fragment implements View.OnClickListener {


    EditText lat;
    EditText lon;

    String l;
    String lo;

    double latitud;
    double longitud;

    Button button;

    TextView celcius;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    WeatherService service = retrofit.create(WeatherService.class);

    String result;
    float res = 0, press = 0;

    Boolean entered=false;

    String appid;

    public SaveWeather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save_weather, container, false);

        lat = (EditText) view.findViewById(R.id.edit_text1);
        lon = (EditText) view.findViewById(R.id.edit_text2);

        button = (Button) view.findViewById(R.id.button_enter);
        button.setOnClickListener(this);

        appid="31b6b748c4c0db46e501ca60f2df064c";


        return view;
    }

    public void get_temp(){
        if(entered) {
            service.getWeather(latitud, longitud, appid).enqueue(new retrofit2.Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    if (response.isSuccessful()) {
                        TextView name = (TextView) getActivity().findViewById(R.id.Location1);
                        TextView min = (TextView) getActivity().findViewById(R.id.mintemp1);
                        TextView max = (TextView) getActivity().findViewById(R.id.maxtemp1);
                        TextView t = (TextView) getActivity().findViewById(R.id.temp1);
                        ImageView w = (ImageView) getActivity().findViewById(R.id.weather1);

                        WeatherResponse weatherResponse = response.body();

                        result = weatherResponse.getWeather();
                        String icon = "ic_" + result;
                        int id = getResources().getIdentifier(icon, "drawable", getActivity().getPackageName());
                        w.setImageResource(id);

                        result = weatherResponse.getNam();
                        name.setText(result);

                        result = weatherResponse.getWeathermain();
                        res = Float.parseFloat(result);
                        int rs = ((int) res);
                        t.setText("" + (rs - 272) + "ºC");

                        result = weatherResponse.getTempMax();
                        res = Float.parseFloat(result);
                        int maxtmp = ((int) res);
                        min.setText("Minimum temperature: " + (maxtmp - 272) + "ºC");

                        result = weatherResponse.getTempMin();
                        res = Float.parseFloat(result);
                        int mintmp = ((int) res);
                        max.setText("Maximum temperature: " + (mintmp - 272) + "ºC");

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "OH NO", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        l=lat.getText().toString();
        lo=lon.getText().toString();


        entered=true;
        if(!l.equals("") && !lo.equals("")) {
            latitud=Double.parseDouble(l);
            longitud = Double.parseDouble(lo);
            get_temp();
        }else{
            if (l.equals(""))lat.setError("Empty field!");
            if(lo.equals("")) lon.setError("Empty field!");
        }

    }
}
