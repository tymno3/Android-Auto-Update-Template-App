package com.aware.androidupdatetemplateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    public void checkForUpdates(View view) {
        final TextView textView = (TextView) findViewById(R.id.text);
        // todo(tding): Supply real username and real current version
        String localhost = "http://10.0.2.2:5000/version/?user=Tim&current_version=1";
        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, localhost, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                textView.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!"+ error.toString());

                    }
                });
        queue.add(stringRequest);
    }
}