package com.project.semicolon.mysupplements.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.project.semicolon.mysupplements.R;

public class AppUtil {
    private static Dialog mDialog;

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

    public static String getText(EditText editText) {
        return editText.getText().toString();
    }

    public static void showDialog(Context context) {
        mDialog = new Dialog(context, R.style.NewDialog);
        mDialog.addContentView(
                new ProgressBar(context),
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        );
        mDialog.setCancelable(false);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public static void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public static void share(Context context, String message, String subject) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(shareIntent);

    }
}
