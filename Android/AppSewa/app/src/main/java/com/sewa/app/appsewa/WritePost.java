package com.sewa.app.appsewa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class WritePost extends AppCompatActivity{
    private static final String POST_URL=Config.getServerIp()+"write_post.php";
    EditText etOrgName, etNumOfVols, etDisasterLocation, etOrgAddress, etOrgContact,  etOrgEmail;
    Spinner spinnerCause, spinnerEmergencyLvl;
    Button btnBack, btnPost;
    private String cause="";
    private String emergency_level="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        etOrgName=(EditText)findViewById(R.id.etOrgName);
        etNumOfVols=(EditText)findViewById(R.id.etNumOfVols);
        etDisasterLocation=(EditText)findViewById(R.id.etDisasterLocation);
        etOrgAddress=(EditText)findViewById(R.id.etOrgAddress);
        etOrgContact=(EditText)findViewById(R.id.etOrgContact);
        etOrgEmail=(EditText)findViewById(R.id.etOrgEmail);

        spinnerCause = (Spinner) findViewById(R.id.spinnerCause);
        spinnerEmergencyLvl = (Spinner) findViewById(R.id.spinnerEmergencyLvl);
        btnBack=(Button)findViewById(R.id.btnBack);
        btnPost=(Button)findViewById(R.id.btnPost);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> causeAdapter = ArrayAdapter.createFromResource(this,
                R.array.cause_values, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> emergencyLvlAdapter = ArrayAdapter.createFromResource(this,
                R.array.emergency_lvl_values, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        causeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emergencyLvlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
//        spinnerEmergencyLevel.setAdapter(adapterOne);
        spinnerCause.setAdapter(causeAdapter);
        spinnerEmergencyLvl.setAdapter(emergencyLvlAdapter);

        //Code for the buttons and their onclick handlers
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WritePost.this, SelectActivity.class);
                startActivity(intent);
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=validateFields(etOrgName.getText().toString().trim(),
                        etNumOfVols.getText().toString().trim(),etDisasterLocation.getText().toString().trim(),
                        etOrgAddress.getText().toString().trim(), etOrgContact.getText().toString().trim(),
                        etOrgEmail.getText().toString().trim());
                if (msg=="done"){
                    if (validateEmail(etOrgEmail.getText().toString().trim())==true) {
                        postDetails();
                    }else{
                        Toast.makeText(WritePost.this, "You have entered a wrong email address. Ex: abc@gmail.com", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(WritePost.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        spinnerCause.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cause = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEmergencyLvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emergency_level = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private String validateFields(String etOrgName, String etNumOfVols, String  etDisasterLocation, String etOrgAddress, String etOrgContact, String etOrgEmail) {
        String msg="done";
            if (etOrgName.equals("") || etNumOfVols.equals("") || etDisasterLocation.equals("") || etOrgAddress.equals("") || etOrgContact.equals("") || etOrgEmail.equals("")){
                msg="You have not filled in one or more number of fields.";
            }
        return msg;
    }

    private void postDetails() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Posting...","Please wait ...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        if (s.equals("done")) {
                            startActivity(new Intent(WritePost.this, ListPosts.class));
                            Toast.makeText(WritePost.this, "Your post has been added successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(WritePost.this, s, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();
                        //Showing toast
                        Toast.makeText(WritePost.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Getting values of the fields
                String org_name = etOrgName.getText().toString().trim();
                String num_of_volunteers = etNumOfVols.getText().toString().trim();
                String disaster_location = etDisasterLocation.getText().toString().trim();
                String org_address = etOrgAddress.getText().toString().trim();
                String org_contact = etOrgContact.getText().toString().trim();
                String org_email= etOrgEmail.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("org_name", org_name);
                params.put("num_of_volunteers", num_of_volunteers);
                params.put("emergency_level", emergency_level);
                params.put("cause", cause);
                params.put("org_address", org_address);
                params.put("org_contact", org_contact);
                params.put("disaster_location", disaster_location);
                params.put("org_email", org_email);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
