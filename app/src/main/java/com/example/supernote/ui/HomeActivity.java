package com.example.supernote.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.ActivityHomeBinding;
import com.example.supernote.pojo.AdpterNote;
import com.example.supernote.pojo.LiveDataNote;
import com.example.supernote.pojo.RoomDataBase.Entity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityHomeBinding binding;
    LiveDataNote mLiveData;
    AdpterNote mAdapterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.floatingActionButton.setOnClickListener(this);
        binding.logOut.setOnClickListener(this);

       // binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
       // binding.recyclerView.setAdapter(mAdapterNote);


        mLiveData = ViewModelProviders.of(this).get(LiveDataNote.class);
        mLiveData.getAllData().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(List<Entity> entities) {


                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                mAdapterNote = new AdpterNote(entities, getApplication());
                binding.recyclerView.setHasFixedSize(true);
                binding.recyclerView.scrollToPosition(0);
                binding.recyclerView.setAdapter(mAdapterNote);


            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floating_action_button:
                startActivity(new Intent(HomeActivity.this, AddNoteActivity.class));
                break;

            case R.id.logOut:
                logOut();
                break;


        }
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, AuthActivity.class));
        finish();

    }
}