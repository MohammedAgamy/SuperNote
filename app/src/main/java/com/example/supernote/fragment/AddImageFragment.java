package com.example.supernote.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.supernote.R;
import com.example.supernote.pojo.ConvertImage;
import com.example.supernote.pojo.LiveDataNote;
import com.example.supernote.pojo.RoomDataBase.Entity;
import com.example.supernote.ui.HomeActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AddImageFragment extends Fragment implements View.OnClickListener {

    final static int REQUEST_CODE = 23;
    final static int RequestCodePhoto = 1;

    CardView mCardView;
    EditText mTitle, mDecs;
    Button mSavePhoto, mTrayAgain;
    ImageView mShowImage;


    Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savediInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniView(view);

    }

    private void iniView(View view) {


        mCardView = view.findViewById(R.id.cardView);
        mCardView.setOnClickListener(this);
        mTitle = view.findViewById(R.id.title_edit);
        mDecs = view.findViewById(R.id.dec_edit);
        mSavePhoto = view.findViewById(R.id.save);
        mSavePhoto.setOnClickListener(this);
        mTrayAgain = view.findViewById(R.id.tackphot);
        mTrayAgain.setOnClickListener(this);
        mShowImage = view.findViewById(R.id.imageViewNote);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardView:
                getPermissionCamera();
                break;

            case R.id.save:
                savedImage();
                break;

            case R.id.tackphot:
                tackAthhorPhoto();
                break;
        }
    }


    public boolean getPermissionCamera() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permission, REQUEST_CODE);
            return true;
        } else {
            getPhoto();

        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPhoto();
                }
                break;
        }
    }

    private void getPhoto() {
        Intent tackPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(tackPhoto, RequestCodePhoto);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCodePhoto && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            mShowImage.setImageBitmap(bitmap);
            //Picasso.get().load(String.valueOf(url)).into(binding.imageViewNote);
            // Glide.with(this).load(url).into(binding.imageViewNote);
            mCardView.setVisibility(View.INVISIBLE);
            mShowImage.setVisibility(View.VISIBLE);
            mSavePhoto.setVisibility(View.VISIBLE);
            mTrayAgain.setVisibility(View.VISIBLE);

        }

    }


    private void tackAthhorPhoto() {
        mCardView.setVisibility(View.VISIBLE);
        mShowImage.setVisibility(View.INVISIBLE);
        mSavePhoto.setVisibility(View.INVISIBLE);
        mTrayAgain.setVisibility(View.INVISIBLE);
    }


    private void savedImage() {

        String title = mTitle.getText().toString();
        String des = mDecs.getText().toString();
        if (title.isEmpty() && des.isEmpty()) {
            Toast.makeText(getActivity(), "Enter Your data", Toast.LENGTH_SHORT).show();
        } else {

            LiveDataNote liveDataNote = ViewModelProviders.of(this).get(LiveDataNote.class);
            liveDataNote.insert(new Entity(title, des, ConvertImage.convertImage2ByteArray(bitmap)));

            getActivity().finish();
            Log.e("getData", title);
            Log.e("getData", des);
            Log.e("getData", String.valueOf(bitmap));


            // startActivity(new Intent(getActivity() , HomeActivity.clsas));

            // Intent intent =new Intent(getActivity()  , HomeActivity.class);
            // startActivity(intent);


        }

    }

}