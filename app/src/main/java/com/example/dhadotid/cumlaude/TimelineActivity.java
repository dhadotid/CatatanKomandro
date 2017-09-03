package com.example.dhadotid.cumlaude;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dhadotid.cumlaude.Adapter.AdapterListCatatan;
import com.example.dhadotid.cumlaude.Model.CatatanModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {
    @BindView (R.id.timeline_listview_recycleview)
    RecyclerView listCatatan;
    ArrayList<CatatanModel> data = new ArrayList<>();
    AdapterListCatatan adapterListCatatan;

    private static final String URL = "http://catatanapi.anak-it.web.id/api/catatan/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        requestData();
    }

    private void setAdapter(){
        adapterListCatatan = new AdapterListCatatan(this, data);

        listCatatan.setLayoutManager(new LinearLayoutManager(this));
        listCatatan.setItemAnimator(new DefaultItemAnimator());
        listCatatan.setAdapter(adapterListCatatan);
    }

    private void requestData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getInt("result_code") == 200){
                        JSONArray arrayValue = object.getJSONArray("value");

                        for(int a = 0; a < arrayValue.length(); a++){
                            JSONObject object1 = arrayValue.getJSONObject(a);
                            data.add(new CatatanModel(
                                    object1.getString("id"),
                            object1.getString("id_user"),
                                    object1.getString("title"),
                                    object1.getString("notes"),
                                    object1.getString("created_at")
                            ));
                        }
                        setAdapter();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TimelineActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }


}
