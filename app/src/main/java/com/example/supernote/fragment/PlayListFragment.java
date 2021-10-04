package com.example.supernote.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.supernote.R;
import com.example.supernote.databinding.FragmentPlayListBinding;
import com.example.supernote.pojo.AdapterPlayList;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PlayListFragment extends Fragment {

    FragmentPlayListBinding binding;
    ConstraintLayout const_rent;
    BottomSheetBehavior bottomSheetBehavior;
    File[] mFileList;
    AdapterPlayList mAdapterPlayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_play_list);
        iniView(view);
        getDataPlayList();

    }


    private void iniView(View view) {
        //StateBottomSheet
        const_rent = view.findViewById(R.id.const_rent);
        bottomSheetBehavior = BottomSheetBehavior.from(const_rent);
        onStateChangedBottomSheet();
        //
    }

    private void getDataPlayList() {
        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File file = new File(path);
        mFileList = file.listFiles();

        mAdapterPlayList = new AdapterPlayList(mFileList);
        binding.recyclerPlay.setHasFixedSize(true);
        binding.recyclerPlay.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerPlay.setAdapter(mAdapterPlayList);
    }

    //StateBottomSheet
    private void onStateChangedBottomSheet() {
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @NotNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull @NotNull View bottomSheet, float slideOffset) {
                //wE DON`T NEED THIS
            }
        });
    }
}