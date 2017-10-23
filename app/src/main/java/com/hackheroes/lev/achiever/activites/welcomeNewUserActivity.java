package com.hackheroes.lev.achiever.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hackheroes.lev.achiever.R;

public class welcomeNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_new_user);



        Button closeActivity = (Button)findViewById(R.id.welcome_new_user_button);
        closeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // TODO : create tutorial in some form that would familiarize user with using app
                // let's go back to previous activity
                finish();
            }
        });

    }


}
