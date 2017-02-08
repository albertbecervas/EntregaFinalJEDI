package com.code.albert.evilmemory.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.code.albert.evilmemory.R;
import com.code.albert.evilmemory.activities.other.ImgActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Calculator extends NavigationDrawer {

    TextView operands, result;

    int x,a,b,res;

    String symbol;

    Boolean secondNumber=false;
    Boolean ans=false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ButterKnife butterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        butterKnife.bind(this);

        operands = (TextView) findViewById(R.id.operands);
        result = (TextView) findViewById(R.id.result);

        sharedPreferences = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operand", operands.getText().toString());
        outState.putString("result", result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        operands.setText(savedInstanceState.getString("operand"));
        result.setText(savedInstanceState.getString("result"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:671980912"));
                startActivity(intent);
                return true;
            case R.id.Explorer:
                Intent internet = new Intent(Intent.ACTION_VIEW);
                internet.setData(Uri.parse("https://sites.google.com/jediupc.com/andqt2017tardes"));
                startActivity(internet);
                return true;
            case R.id.toast:
                editor.putBoolean("Toast", true);
                Toast.makeText(getApplicationContext(), "Toast Notification", Toast.LENGTH_SHORT).show();
                editor.apply();
                return true;
            case R.id.state:
                editor.putBoolean("Toast", false);
                editor.apply();
                Toast.makeText(getApplicationContext(), "State Notification", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.button_0, R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5
            ,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_equal,R.id.button_coma
            ,R.id.button_CE,R.id.button_suma,R.id.button_resta,R.id.button_prod,R.id.button_div,})
    public void on_click(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                x = x * 10 + 0;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }
                operands.setText(String.valueOf(x));

                break;
            case R.id.button_1:
                x = x * 10 + 1;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_2:
                x = x * 10 + 2;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_3:
                x = x * 10 + 3;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_4:
                x = x * 10 + 4;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_5:
                x = x * 10 + 5;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_6:
                x = x * 10 + 6;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_7:
                x = x * 10 + 7;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_8:
                x = x * 10 + 8;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_9:
                x = x * 10 + 9;
                if(secondNumber){
                    String operacio= operands.getText().toString();
                    operands.setText(String.valueOf(operacio + x));
                }else {
                    operands.setText(String.valueOf(x));
                }
                break;
            case R.id.button_equal:
                b = x;
                switch (symbol) {
                    case "+":
                        res = a + b;
                        break;
                    case "-":
                        res = a - b;
                        break;
                    case "*":
                        res = a * b;
                        break;
                    case "/":
                        if(b!=0) {
                            res = a / b;
                        }else{
                            res=0;
                            if(sharedPreferences.getBoolean("Toast",true)){
                                Toast.makeText(getApplicationContext(),"You've got ZERO friends",Toast.LENGTH_SHORT).show();
                            }else{
                                int mId=1;

                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                                builder.setContentTitle("Multiplicate por cero!");
                                builder.setContentText("Math error");
                                builder.setSmallIcon(R.mipmap.ic_launcher);
                                notificationManager.notify(1,builder.build());


                                Intent resultIntent = new Intent(getApplicationContext(), ImgActivity.class);

                                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                                // Añade la pila para el Intent,pero no el intent en sí
                                stackBuilder.addParentStack(ImgActivity.class);
                                // Añadimos el intent que empieza la activity que está en el top de la pila
                                stackBuilder.addNextIntent(resultIntent);

                                //El pending intent será el que se ejecute cuando la notificación sea pulsada
                                PendingIntent resultPendingIntent =
                                        stackBuilder.getPendingIntent(
                                                0,
                                                PendingIntent.FLAG_UPDATE_CURRENT
                                        );
                                builder.setContentIntent(resultPendingIntent);

                                // mId nos permite actualizar las notificaciones en un futuro
                                // Notificamos
                                notificationManager.notify(mId, builder.build());

                                Notification noti = builder.build();
                                noti.flags |= Notification.FLAG_INSISTENT;
                                //noti.flags |= Notification.FLAG_NO_CLEAR;
                                noti.flags |= Notification.FLAG_SHOW_LIGHTS;
                                notificationManager.notify(mId, noti);
                            }
                        }
                        break;
                }
                operands.setText(a + symbol + b);
                result.setText(String.valueOf(res));
                break;
            case R.id.button_coma:
                ans=true;
                x=res;
                secondNumber=true;
                operands.setText(String.valueOf(x));
                break;
            case R.id.button_CE:

                x = 0;
                a = 0;
                b = 0;
                res = 0;
                result.setText(null);
                operands.setText(null);
                break;
            case R.id.button_suma:

                a = x;
                x = 0;
                symbol = "+";
                secondNumber=true;
                operands.setText(String.valueOf(a + symbol));
                break;
            case R.id.button_resta:

                a = x;
                x = 0;
                symbol = "-";
                secondNumber=true;
                operands.setText(String.valueOf(a + symbol));
                break;
            case R.id.button_prod:

                a = x;
                x = 0;
                symbol = "*";
                secondNumber=true;
                operands.setText(String.valueOf(a + symbol));
                break;
            case R.id.button_div:
                a = x;
                x = 0;
                symbol = "/";
                secondNumber=true;
                operands.setText(String.valueOf(a + symbol));
                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
