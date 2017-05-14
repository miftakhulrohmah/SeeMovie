package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/top_rated?api_key=3eb2b17488a258a4fe1cb35fdc901b55";
    public TextView textViewHeading1;
    public TextView textViewDesc1;
    public TextView textViewReview1;
    public ImageView imageViewDetail1;
    //public String url;
    public  String url;
    private Integer mPostkey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadData();


        textViewHeading1 = (TextView) findViewById(R.id.textViewHeading1);
        textViewDesc1 = (TextView) findViewById(R.id.textViewDesc1);
        textViewReview1 = (TextView) findViewById(R.id.textViewReview1);
        imageViewDetail1 = (ImageView) findViewById(R.id.imageViewDetail);
/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);

                            setTitle("Detail of Your Movie");

                            textViewHeading1.setText(o.getString("original_title"));
                            textViewDesc1.setText(o.getString("popularity"));
                            textViewReview1.setText(o.getString("overview"));
                            url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(MovieDetActivity.this)
                                    .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                                    .into(imageViewDetail1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
