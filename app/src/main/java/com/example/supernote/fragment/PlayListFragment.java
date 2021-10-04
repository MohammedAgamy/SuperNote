package com.example.supernote.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.supernote.R;
import com.example.supernote.databinding.FragmentPlayListBinding;
import com.example.supernote.pojo.AdapterPlayList;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class PlayListFragment extends Fragment implements AdapterPlayList.OnItemClick {

    FragmentPlayListBinding binding;
    ConstraintLayout const_rent;
    BottomSheetBehavior bottomSheetBehavior;
    File[] mFileList;
    AdapterPlayList mAdapterPlayList;
    MediaPlayer mMediaPlayer = null;
    boolean isPlaying = false;
    File fileToPlay;
    RecyclerView mRecyclerPlay;


    // uiPlayAudio
    TextView fileName, play_text;
    ImageView imgPlay, imgNext, imgBack;
    SeekBar mSeekBar;
    Handler mHandler;
    Runnable mRunnable;

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

        //findView ui bottomSheet
        mRecyclerPlay = view.findViewById(R.id.recycler_play);
        fileName = view.findViewById(R.id.fileName);
        play_text = view.findViewById(R.id.playtext);
        imgPlay = view.findViewById(R.id.paly);
        imgNext = view.findViewById(R.id.img_back);
        imgBack = view.findViewById(R.id.img_next);

        mSeekBar = view.findViewById(R.id.seekBer);


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

        mAdapterPlayList = new AdapterPlayList(mFileList, this);
        mRecyclerPlay.setHasFixedSize(true);
        mRecyclerPlay.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerPlay.setAdapter(mAdapterPlayList);
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


        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    pauseAudio();
                } else {

                    if (fileName != null) {

                    }
                    resaumAudio();
                }
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                resaumAudio();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = mSeekBar.getProgress();
                mMediaPlayer.seekTo(progress);

                pauseAudio();
            }
        });

    }

    @Override
    public void onClickLisiner(File allFile, int position) {

        fileToPlay = allFile;

        if (isPlaying) {
            stopPlayAudio();
            playAudio(fileToPlay);

        } else {

            playAudio(fileToPlay);

        }
    }
    //stopAudio
    private void stopPlayAudio() {
        isPlaying = false;
        mMediaPlayer.stop();
        mSeekBar.removeCallbacks(mRunnable);

    }


    //startAudio
    private void playAudio(File fileToPlay) {
        mMediaPlayer = new MediaPlayer();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        try {
            mMediaPlayer.setDataSource(fileToPlay.getAbsolutePath());
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        imgPlay.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_pause_24));
        fileName.setText(fileToPlay.getName());
        play_text.setText("Playing..");

        isPlaying = true;

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayAudio();
                play_text.setText("MediaPlayer");
                imgPlay.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_play_arrow_24));

            }
        });

        mSeekBar.setMax(mMediaPlayer.getDuration());
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
                mHandler.postDelayed(this, 500);
            }
        };

        mHandler.postDelayed(mRunnable, 0);
    }



    //pauseAudio
    public void pauseAudio() {
        mMediaPlayer.pause();
        imgPlay.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_play_arrow_24));
        mSeekBar.removeCallbacks(mRunnable);
        isPlaying = false;
    }

    //resumeAudio
    public void resaumAudio() {
        mMediaPlayer.start();
        imgPlay.setImageDrawable(getActivity().getDrawable(R.drawable.ic_baseline_pause_24));
        isPlaying = true;
    }


    @Override
    public void onStop() {
        super.onStop();
        stopPlayAudio();
    }
}