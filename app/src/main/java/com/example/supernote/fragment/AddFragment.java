package com.example.supernote.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.ActivityAddNoteBinding;
import com.example.supernote.databinding.FragmentAddBinding;
import com.example.supernote.pojo.ConvertImage;
import com.example.supernote.pojo.LiveDataNote;
import com.example.supernote.pojo.RoomDataBase.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;

public class AddFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "TAGnOTE";
    ImageView mSaveDataBtn ;
    EditText mTitle ,mNote ;
    ImageView imageView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSaveDataBtn=view.findViewById(R.id.addNote);
        mSaveDataBtn.setOnClickListener(this);
        mTitle=view.findViewById(R.id.titleNote);
        mNote=view.findViewById(R.id.note_edit);


        imageView=view.findViewById(R.id.imageNote_item);

    }
    private void saveData() {
        String title = mTitle.getText().toString();
        String note = mNote.getText().toString();
        if (title.isEmpty() && note.isEmpty()) {
            Toast.makeText(getActivity(), "Enter Your Note", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.fuk);

            LiveDataNote liveDataNote = ViewModelProviders.of(this).get(LiveDataNote.class);
            liveDataNote.insert(new Entity(title, note, ConvertImage.convertImage2ByteArray(largeIcon)));
            getActivity().finish();
            Log.d(TAG, title);
            Log.d(TAG, note);

        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNote:
                saveData();
                break;
        }
    }
}