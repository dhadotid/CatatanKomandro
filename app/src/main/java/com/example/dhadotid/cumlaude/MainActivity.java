package com.example.dhadotid.cumlaude;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText et_user;
    @BindView(R.id.et_passowrd)
    EditText et_pass;

    private String username;
    private String password;

    private final String URL = "http://catatanapi.anak-it.web.id/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.Login)
    public void onLoginClicked(){
        getDataLogin();
        doLogin();
    }

    private void getDataLogin(){
        username = et_user.getText().toString();
        password = et_pass.getText().toString();
    }

    private void doLogin(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObject = new JSONObject(response);
                    if (responseObject.getInt("result_code") == 200){
                        JSONObject responseValue = responseObject.getJSONObject("value");

                        Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Login error!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                error.getStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new ArrayMap<>();
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
