package com.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chatapp.adapters.CDRAdapter;
import com.chatapp.adapters.CDRModel;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import im.vector.R;

import static com.chatapp.Settings.asHex;
import static com.chatapp.Settings.encrypt;

public class CDRReprt extends AppCompatActivity {
    LinearLayout pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_d_r_reprt);
        pg = findViewById(R.id.pg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Call Details Report");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCDR();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getCDR() {
        try {
            SharedPreferences settings = android.preference.PreferenceManager.getDefaultSharedPreferences(CDRReprt.this);

            final String cust_id = asHex(encrypt(settings.getString("Username", ""), Settings.ENC_KEY).getBytes());
            final String cust_pass = asHex(encrypt(settings.getString("Password", ""), Settings.ENC_KEY).getBytes());


            String url = Settings.CDR;
            pg.setVisibility(View.VISIBLE);
            RequestQueue queue = Volley.newRequestQueue(CDRReprt.this);
            StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        response = response.trim();
                        JSONObject json = new JSONObject(response);
                        if (json.optString("result").equalsIgnoreCase("success")) {
                            setList(json.optJSONArray("msg"));
                            if (CDRReprt.this != null) {
                                CDRReprt.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pg.setVisibility(View.GONE);

                                    }
                                });
                            }
                        } else {
                            if (CDRReprt.this != null) {
                                CDRReprt.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CDRReprt.this, "An error, please try again later.", Toast.LENGTH_LONG).show();
                                        pg.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                        if (CDRReprt.this != null) {
                            CDRReprt.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(CDRReprt.this, "An Internal error, please try again later.", Toast.LENGTH_LONG).show();
                                    pg.setVisibility(View.GONE);
                                }
                            });
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    final VolleyError error1 = error;
                    if (CDRReprt.this != null) {
                        CDRReprt.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CDRReprt.this, error1.getMessage(), Toast.LENGTH_LONG).show();
                                pg.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("cust_id", cust_id);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }

            };
            queue.add(sr);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    private void setList(JSONArray msg) {
        RecyclerView rv = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        ArrayList<CDRModel> list = new ArrayList<>();
        list.addAll(CDRModel.getCDRList(msg));
        if (list.size() > 0) {
            CDRAdapter adapter = new CDRAdapter(CDRModel.getCDRList(msg), this);
            rv.setAdapter(adapter);
        } else {
            TextView tv = findViewById(R.id.errText);
            tv.setText("No Call Details Found");
        }
    }
}
