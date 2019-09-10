package com.project.semicolon.mysupplements.ui;

import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.databinding.ArticleActivityBinding;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;

public class ArticleActivity extends AppCompatActivity {
    private ArticleActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_article);

        String desc = String.valueOf(SharedPrefUtil.getData(this, "desc", ""));
        String imageUrl = String.valueOf(SharedPrefUtil.getData(this, "image_url", ""));
        String title = String.valueOf(SharedPrefUtil.getData(this, "title", ""));

        binding.articleDesc.setText(Html.fromHtml(desc));
        binding.articleTitle.setText(title);
        Glide.with(this).load(imageUrl).into(binding.articleImage);
    }
}
