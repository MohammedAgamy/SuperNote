package com.example.supernote.pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ConvertImage {

    public static byte[] convertImage2ByteArray(Bitmap bitmap) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public static Bitmap bitmapByteArray(byte[] array)
    {

        return BitmapFactory.decodeByteArray(array ,0,array.length);
    }
}
