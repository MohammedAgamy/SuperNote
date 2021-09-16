package com.example.supernote.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding ;
    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mAuth=FirebaseAuth.getInstance();


        binding.animationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    finish();
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                }
                else
                {
                    finish();
                    startActivity(new Intent(SplashActivity.this,AuthActivity.class));
                }



            }
        },3000);

    }

    
}