package com.project.semicolon.mysupplements.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.databinding.MainActivityBinding;

public class HomeActivity extends AppCompatActivity {
    private MainActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

    }
}
