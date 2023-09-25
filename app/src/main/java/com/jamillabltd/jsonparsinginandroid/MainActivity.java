package com.jamillabltd.jsonparsinginandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadDataButton = findViewById(R.id.loadDataButtonId);
        ProgressBar progressBar = findViewById(R.id.progressBarId);
        LinearLayout infoArea = findViewById(R.id.infoAreaId);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvMobile = findViewById(R.id.tvMobile);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvAddress = findViewById(R.id.tvAddress);

        //onClick to load data
        loadDataButton.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);

            // Instantiate the RequestQueue.
            String url = "https://lrjamil.000webhostapp.com/apps/info.json";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        progressBar.setVisibility(View.GONE);
                        infoArea.setVisibility(View.VISIBLE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String name = jsonObject.getString("name");
                            String mobile = jsonObject.getString("mobile");
                            String email = jsonObject.getString("email");
                            String address = jsonObject.getString("address");

                            tvName.setText(name);
                            tvMobile.setText(mobile);
                            tvEmail.setText(email);
                            tvAddress.setText(address);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }, error -> {
                        loadDataButton.setText("Error to data load");
                        progressBar.setVisibility(View.GONE);

                    });

            // Add the request to the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(stringRequest);



        });

    }
}