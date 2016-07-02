package com.sewa.app.appsewa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class PostDetails extends AppCompatActivity {

    //Declaration
    private static final String REPORT_URL=Config.getServerIp()+"report_post.php";
    TextView tvOrgName, tvNumofVols, tvCause, tvEmergencyLvl, tvDisasterLocation, tvOrgAddress;
    Button btnBack, btnCall, btnSend, btnReport;
    Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //setting data passed from the previous activity to the post instance
        post=new Post();
        post.setPost_id(getIntent().getExtras().getInt("post_id"));
        post.setOrg_name(getIntent().getExtras().getString("org_name"));
        post.setNum_of_vols(getIntent().getExtras().getInt("num_of_vols"));
        post.setCause(getIntent().getExtras().getString("cause"));
        post.setEmergency_lvl(getIntent().getExtras().getString("emergency_lvl"));
        post.setDisaster_location(getIntent().getExtras().getString("disaster_location"));
        post.setOrg_address(getIntent().getExtras().getString("org_address"));
        post.setOrg_contact(getIntent().getExtras().getString("org_contact"));
        post.setOrg_email(getIntent().getExtras().getString("org_email"));

        //Assigining the items of the layout to the variables

        //Text Views
        tvOrgName=(TextView)findViewById(R.id.tvOrgName);
        tvNumofVols=(TextView)findViewById(R.id.tvNumOfVols);
        tvCause=(TextView)findViewById(R.id.tvCause);
        tvEmergencyLvl=(TextView)findViewById(R.id.tvEmergencyLvl);
        tvDisasterLocation=(TextView)findViewById(R.id.tvDisasterLocation);
        tvOrgAddress=(TextView)findViewById(R.id.tvOrgAddress);

        //Buttons
        btnBack=(Button)findViewById(R.id.btnBack);
        btnCall=(Button)findViewById(R.id.btnCall);
        btnSend=(Button)findViewById(R.id.btnSend);
        btnReport=(Button)findViewById(R.id.btnReport);

        //Calling a method to populate the text views
        populateAll();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostDetails.this, ListPosts.class));
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact=post.getOrg_contact().toString().trim();
                Intent callIntent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+contact));
                try{
                    startActivity(callIntent);
                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportFake();
            }
        });
    }

    private void reportFake() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Reporting","Please wait ...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REPORT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Dismissing the progress dialog
                        loading.dismiss();
                        if (s.equals("done")) {
                            startActivity(new Intent(PostDetails.this, ListPosts.class));
                            Toast.makeText(PostDetails.this, "The post has been reported successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(PostDetails.this, s, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();
                        //Showing toast
                        Toast.makeText(PostDetails.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();
                String post_id=String.valueOf(post.getPost_id());
                //Adding parameters
                params.put("post_id", post_id);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void populateAll() {
        tvOrgName.setText(post.getOrg_name());
        tvOrgAddress.setText(post.getOrg_address());
        if (post.getNum_of_vols()==1){
            tvNumofVols.setText(post.getNum_of_vols()+" volunteer");
        }else {
            tvNumofVols.setText(post.getNum_of_vols() + " volunteers");
        }
        tvCause.setText(post.getCause()+" Victims");
        tvDisasterLocation.setText(post.getDisaster_location());
        tvEmergencyLvl.setText("Emergency Level: "+post.getEmergency_lvl());
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {post.getOrg_email()};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "In response to your post in the SwayamSewak app");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your complete message to "+tvOrgName.getText().toString().trim());
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose you email client"));
            //finish();
            Log.i("Finished sending email.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(PostDetails.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
