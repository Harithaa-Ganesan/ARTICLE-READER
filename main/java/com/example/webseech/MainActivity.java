package com.example.webseech;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    Button login;
    TextView user, pass;
    String uss, pss;
    ImageButton img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (TextView) findViewById(R.id.userid);
        pass = (TextView) findViewById(R.id.passid);
        login = (Button) findViewById(R.id.loginid);

        img1 = (ImageButton) findViewById(R.id.img1id);
        img2 = (ImageButton) findViewById(R.id.img2id);
        login.setOnClickListener(this);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.loginid:
                uss= user.getText().toString();
                pss= pass.getText().toString();
                Toast.makeText(this, uss+" "+pss, Toast.LENGTH_SHORT).show();
                if(uss.equals("admin") && pss.equals("12345")) {
                    Intent intent = new Intent(MainActivity.this,EntryPage.class);
                    startActivity(intent);
                }

            case R.id.img1id:
                if(user.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    user.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    user.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img2id:
                if(pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;


        }

    }
}