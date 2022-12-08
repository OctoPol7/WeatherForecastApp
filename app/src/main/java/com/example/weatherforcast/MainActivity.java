package com.example.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String city;
    private String degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
    }

    public void buttonPressed(View view) {
        EditText cityInput = findViewById(R.id.cityInput);
        city = cityInput.getText().toString();

        fetchWeather();
    }

    private void fetchWeather() {
        Switch switchSystem = findViewById(R.id.switchSystem);
        String url;

        TextView cityName = findViewById(R.id.cityName);
        TextView temp = findViewById(R.id.temp);
        TextView tempH = findViewById(R.id.tempH);
        TextView tempFL = findViewById(R.id.tempFL);
        TextView tempL = findViewById(R.id.tempL);
        TextView weather = findViewById(R.id.weather);


        if (!city.equals("") && !city.equals("Enter a city")) {
            if (switchSystem.isChecked()) {
                url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0d6ea1c728b59d0bd10630142fa767a0&units=imperial";
                degree = "°F";
            } else {
                url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0d6ea1c728b59d0bd10630142fa767a0&units=metric";
                degree = "°C";
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, response -> {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            JSONObject weatherObj = response.getJSONArray("weather").getJSONObject(0);

                            cityName.setText(response.getString("name"));
                            temp.setText(Integer.toString((int)main.getDouble("temp")) + degree);
                            tempH.setText("High " + Integer.toString((int)main.getDouble("temp_max")) + degree);
                            tempFL.setText("Feels like " + Integer.toString((int)main.getDouble("feels_like")) + degree);
                            tempL.setText("Low " + Integer.toString((int)main.getDouble("temp_min")) + degree);
                            weather.setText(weatherObj.getString("main"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show());

            requestQueue.add(jsonObjectRequest);

        } else {
            Toast.makeText(this, "Enter a city", Toast.LENGTH_SHORT).show();
        }

    }

}