package com.project.semicolon.mysupplements.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.adapter.RecommendAdapter;
import com.project.semicolon.mysupplements.databinding.ProteinBinding;
import com.project.semicolon.mysupplements.ui.ArticleActivity;
import com.project.semicolon.mysupplements.utils.AppUtil;
import com.project.semicolon.mysupplements.utils.Logger;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProteinFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ProteinBinding binding;
    private String selectedTarget;
    private int position;
    private CategoryViewModel viewModel;
    private int group;
    private RecommendAdapter adapter;


    public ProteinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ProteinBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this)
                .get(CategoryViewModel.class);


        initViews();
        initRecyclerView();

    }

    private void initRecyclerView() {
        binding.suggestedSupplementsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.suggestedSupplementsRecycler.setHasFixedSize(true);
        adapter = new RecommendAdapter(getContext());

        binding.suggestedSupplementsRecycler.setAdapter(adapter);

        adapter.setOnSuggestClickListener((view, article) -> {
            SharedPrefUtil.save(getContext(), "desc", article.getDescription());
            SharedPrefUtil.save(getContext(), "image_url", article.getImageUrl());
            SharedPrefUtil.save(getContext(), "title", article.getTitle());
            Intent in = new Intent(getContext(), ArticleActivity.class);
            startActivity(in);
        });

    }

    private void initViews() {
        binding.targetSpinner.setOnItemSelectedListener(this);


        buttonAction();

    }

    private void buttonAction() {

        binding.btnSuggest.setOnClickListener(view -> {

            String fatPer = AppUtil.getText(binding.etFatPer);
            String height = AppUtil.getText(binding.etHeight);
            String weight = AppUtil.getText(binding.etWeight);

            if (!validation(fatPer, height, weight))
                return;

            suggestSupplements(fatPer);

        });

    }

    private void suggestSupplements(String fatPer) {
        int intFatPer = Integer.parseInt(fatPer);
        switch (position) {
            case 0:
                if (intFatPer >= 0 && intFatPer < 10) {
                    group = 1;
                } else if (intFatPer >= 10 && intFatPer < 15) {
                    group = 2;
                } else {
                    AppUtil.snack(binding.container, "نسبة الدهون عالية جدا");
                    return;
                }

                break;

            case 1:
                if (intFatPer >= 0 && intFatPer < 10) {
                    group = 2;
                } else if (intFatPer >= 10 && intFatPer < 15) {
                    group = 3;
                } else {
                    group = 3;
                }

                break;

            case 2:
                if (intFatPer >= 0 && intFatPer < 10) {
                    group = 3;
                } else if (intFatPer >= 10 && intFatPer < 15) {
                    group = 3;
                } else {
                    //TODO Group 2 & Group 3 = Group 4
                    Toast.makeText(getContext(), "G2 & G3", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;


        }

        viewModel.getArticleBasedOnGroup(group)
                .observe(this, articles -> {
                    if (articles != null) {
                        Logger.verbose("onChanged: articles: " + articles.toString());
                        adapter.setArticles(articles);

                    }
                });

    }

    private boolean validation(String fatPer, String height, String weight) {
        if (TextUtils.isEmpty(fatPer)) {
            binding.etFatPer.setError(getString(R.string.required_error));
            return false;
        }

        if (TextUtils.isEmpty(height)) {
            binding.etHeight.setError(getString(R.string.required_error));
            return false;
        }

        if (TextUtils.isEmpty(weight)) {
            binding.etWeight.setError(getString(R.string.required_error));
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position != -1) {
            selectedTarget = String.valueOf(adapterView.getItemAtPosition(position));
            this.position = position;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
