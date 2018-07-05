package com.androidgits.quotes.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidgits.quotes.R;
import com.androidgits.quotes.adapter.RvAdapter;
import com.androidgits.quotes.model.Quotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private RecyclerView myrv;
    private String URL_JSON = "https://talaikis.com/api/quotes/";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Quotes> lstQuotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Quotes ...");
        dialog.show();

        myrv = (RecyclerView) findViewById(R.id.rv);
        jsoncall();
    }

    public void jsoncall() {
        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0;i<response.length();i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Quotes quotes = new Quotes();
                        quotes.setAuthor(jsonObject.getString("author"));
                        quotes.setCat(jsonObject.getString("cat"));
                        quotes.setQuote(jsonObject.getString("quote"));
                        lstQuotes.add(quotes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                setRvAdapter(lstQuotes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Network Connection", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ArrayRequest);
    }

    public void setRvAdapter(List<Quotes> lstQuotes) {
        RvAdapter myAdapter = new RvAdapter(this,lstQuotes);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}