package com.example.supernote.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.supernote.R;

import org.jetbrains.annotations.NotNull;

public class VoiceFragment extends Fragment implements View.OnClickListener {
ImageView mImageMenu ;
NavController navController ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageMenu=view.findViewById(R.id.imgMenu);
        mImageMenu.setOnClickListener(this);

        navController = Navigation.findNavController(view);
    }



    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.imgMenu:
                 navController. navigate(R.id.action_voiceFragment_to_playListFragment);

                break;
        }

    }
}