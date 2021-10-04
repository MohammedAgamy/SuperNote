package com.example.supernote.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Magnifier;

import com.airbnb.lottie.LottieAnimationView;
import com.example.supernote.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VoiceFragment extends Fragment implements View.OnClickListener {
    ImageView mImageMenu;
    LottieAnimationView mIsRecording;
    Chronometer mTimer ;

    boolean isRecoding = false;
    final int requestPermission = 20;
    String recorderFile ;

    MediaRecorder mMediaRecorder;


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
        mImageMenu = view.findViewById(R.id.imgMenu);
        mImageMenu.setOnClickListener(this);
        mIsRecording = view.findViewById(R.id.startRec);
        mIsRecording.setOnClickListener(this);
        mTimer=view.findViewById(R.id.time);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgMenu:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.nav_host_fragment_not, PlayListFragment.class, null)
                        .commit();
                break;

            case R.id.startRec:
                if (isRecoding) {
                    //stop record
                    stopRecording();
                    mIsRecording.setAnimation(R.raw.recordgray);
                    isRecoding = false;
                } else {
                    //Start record
                    if (cheackPermisssionAudie()) {
                        startRecording();
                        mIsRecording.setAnimation(R.raw.voice);
                        mIsRecording.playAnimation();
                        isRecoding = true;
                    }

                }
                break;

        }
    }


    private boolean cheackPermisssionAudie() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, requestPermission);
            return false;
        }
    }

    private void stopRecording()
    {
        mTimer.stop();
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder =null ;
    }

    private void startRecording() {
        mTimer.setBase(SystemClock.elapsedRealtime());
        mTimer.start();

        String recordPath= getActivity().getExternalFilesDir("/").getAbsolutePath() ;
        SimpleDateFormat format =new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date date =new Date();
        recorderFile ="Recording" + format.format(date) + ".mp3";
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setOutputFile(recordPath + "/" + recorderFile);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaRecorder.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        stopRecording();
    }
}