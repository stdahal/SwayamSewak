package com.sewa.app.appsewa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAsk, btnFind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        btnAsk=(Button)findViewById(R.id.btnAsk);
        btnFind=(Button)findViewById(R.id.btnFind);

        btnAsk.setOnClickListener(this);
        btnFind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAsk:
                startActivity(new Intent(this,WritePost.class));
                break;
            case R.id.btnFind:
                startActivity(new Intent(this,ListPosts.class));
                break;
        }
    }
}
