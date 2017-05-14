package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MovieFragment extends Fragment {
    private List<HomeListItem> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private static final String URL_DATA = "https://api.themoviedb.org/3/movie/top_rated?api_key=3eb2b17488a258a4fe1cb35fdc901b55";
    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();
        loadData();


        /*for(int i =0; i <= 5; i++){
            HomeListItem listItem = new HomeListItem(
                    "",
                    "Title" + (i+1),
                    "Lorem ipsum");
                    listItems.add(listItem);

        }*/

        adapter = new HomeAdapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                HomeListItem item = new HomeListItem(
                                        o.getString("poster_path"),
                                        o.getString("title"),
                                        o.getString("release_date")
                                );

                                listItems.add(item);
                            }
                            adapter = new HomeAdapter(listItems, getActivity().getApplication());
                            recyclerView.setAdapter(adapter);
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}