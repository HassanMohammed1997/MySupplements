package com.project.semicolon.mysupplements.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class AppUtil {
    public static void loadImage(Context ctx, String image, ImageView target) {
        Glide.with(ctx)
                .load(image)
                .into(target);
    }

    public static void navigate(Activity activity, int containerId, int fragmentRes) {
        Navigation.findNavController(activity, containerId)
                .navigate(fragmentRes);
    }


    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void snack(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();

    }

    public static boolean isEmpty(int value) {
        String v = String.valueOf(value);
        return TextUtils.isEmpty(v);
    }
}
