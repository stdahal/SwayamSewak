package com.sewa.app.appsewa;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListPosts extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URL = Config.getServerIp()+"get_posts.php";

    //declaring list of posts
    public List<Post> postList;

    //declaring views
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);
        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        searchView = (SearchView) findViewById(R.id.searchView);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        postList = new ArrayList<Post>();

        //Calling method to get data to fetch data
        getPosts();

        //Initialize adapter
        adapter = new CardAdapter (postList, this);

        //Adding adapter to recyclerView
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getPosts();
                                    }
                                }
        );

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Post post = postList.get(position);
                        //Toast.makeText(getActivity(), "the item clicked is " + position + "and siteId is " + site.getId(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListPosts.this, PostDetails.class);
                        intent.putExtra("post_id",post.getPost_id());
                        intent.putExtra("org_name",post.getOrg_name());
                        intent.putExtra("num_of_vols",post.getNum_of_vols());
                        intent.putExtra("cause",post.getCause());
                        intent.putExtra("emergency_lvl",post.getEmergency_lvl());
                        intent.putExtra("disaster_location",post.getDisaster_location());
                        intent.putExtra("org_address",post.getOrg_address());
                        intent.putExtra("org_contact",post.getOrg_contact());
                        intent.putExtra("org_email",post.getOrg_email());
                        startActivity(intent);
                    }
                })
        );

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                Filter newFilter=new Filter(postList, text);
                List<Post> filteredList=newFilter.filter();
                if (filteredList.size()!=0) {
                    RecyclerView.Adapter afterSearchAdapter = new CardAdapter(filteredList, ListPosts.this);
                    recyclerView.setAdapter(afterSearchAdapter);
                }else{
                    recyclerView.setAdapter(adapter);
                }
                return false;
            }
        });
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }
    private void getPosts() {
        //final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        //setProgressBarIndeterminateVisibility(true);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse (JSONArray response){
                        postList.clear();
                        //Dismissing progress dailog
                        //loading.dismiss();
                        progressBar.setVisibility(View.GONE);
                        //call method to parse json array
                        parseData(response);
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        progressBar.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(ListPosts.this, "No items found", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    //This method parses json data
    private void parseData (JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            Post newPost = new Post();
            JSONObject json = null;

            try {
                json = array.getJSONObject(i);
                String StrPostId = json.getString("post_id");
                String orgName = json.getString("org_name");
                String StrNumOfVols = json.getString("num_of_volunteers");
                String cause = json.getString("cause");
                String emergencyLvl = json.getString("emergency_level");
                String disasterLocation = json.getString("disaster_location");
                String orgAddress = json.getString("org_address");
                String orgContact = json.getString("org_contact");
                String orgEmail = json.getString("org_email");

                int postId = Integer.parseInt(StrPostId);
                int numOfVols = Integer.parseInt(StrNumOfVols);

                newPost.setPost_id(postId);
                newPost.setOrg_name(orgName);
                newPost.setNum_of_vols(numOfVols);
                newPost.setCause(cause);
                newPost.setEmergency_lvl(emergencyLvl);
                newPost.setDisaster_location(disasterLocation);
                newPost.setOrg_address(orgAddress);
                newPost.setOrg_contact(orgContact);
                newPost.setOrg_email(orgEmail);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            postList.add(newPost);
        }
    }

        @Override
        public void onRefresh(){
            getPosts();
        }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
