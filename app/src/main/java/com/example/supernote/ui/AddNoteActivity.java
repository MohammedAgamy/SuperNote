package com.example.supernote.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.ActivityAddNoteBinding;
import com.example.supernote.fragment.AddFragment;
import com.example.supernote.fragment.AddImageFragment;
import com.example.supernote.fragment.PlayListFragment;
import com.example.supernote.fragment.ToDoListFragment;
import com.example.supernote.fragment.VoiceFragment;
import com.example.supernote.pojo.LiveDataNote;
import com.example.supernote.pojo.RoomDataBase.Entity;
import com.google.android.material.snackbar.Snackbar;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class AddNoteActivity extends AppCompatActivity {
    ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        onClickChipNavigationBar();

    }

    private void onClickChipNavigationBar() {
        binding.bottomNavBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.add:
                        fragment = new AddFragment();
                        break;


                    case R.id.img:
                        fragment =new AddImageFragment();
                        break;

                    case R.id.Voice:
                        fragment=new  VoiceFragment();
                        break;

                    case R.id.check:
                        fragment=new ToDoListFragment() ;
                        break;

                    case R.id.imgMenu:
                        fragment=new PlayListFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_not,fragment)
                        .commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chap_nav, menu);
        return true;
    }


}