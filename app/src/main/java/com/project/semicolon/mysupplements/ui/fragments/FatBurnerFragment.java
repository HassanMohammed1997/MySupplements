package com.project.semicolon.mysupplements.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.adapter.RecommendAdapter;
import com.project.semicolon.mysupplements.databinding.FatBurnerBinding;
import com.project.semicolon.mysupplements.ui.ArticleActivity;
import com.project.semicolon.mysupplements.utils.AppUtil;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FatBurnerFragment extends Fragment {
    private static final String TAG = FatBurnerFragment.class.getSimpleName();
    private FatBurnerBinding binding;
    private String currentWeight, currentFat, targetWeight, targetFat;
    private CategoryViewModel viewModel;
    private RecommendAdapter adapter;


    public FatBurnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FatBurnerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initClasses();
        initRecycler();
        initButtonsAction();


    }

    private void initClasses() {
        viewModel = ViewModelProviders.of(this)
                .get(CategoryViewModel.class);

        adapter = new RecommendAdapter(getContext());


    }

    private void getInputValues() {
        currentWeight = AppUtil.getText(binding.etCurrentWeight);
        currentFat = AppUtil.getText(binding.etCurrentFatPer);
        targetFat = AppUtil.getText(binding.etTargetFatPer);
        targetWeight = AppUtil.getText(binding.etTargetWeight);
    }

    private void fetchRecommendedArticle(int diff) {
        if (diff >= 0 && diff <= 3) {
            viewModel.getArticleBasedOnGroup(1)
                    .observe(FatBurnerFragment.this, articles -> {
                        if (articles != null) {
                            adapter.setArticles(articles);
                        }

                        AppUtil.dismissDialog();


                    });
        } else {
            viewModel.getArticleBasedOnGroup(2)
                    .observe(FatBurnerFragment.this, articles -> {
                        if (articles != null) {
                            adapter.setArticles(articles);
                        }

                        AppUtil.dismissDialog();


                    });
        }
    }

    private void initButtonsAction() {

        binding.btnCalculate.setOnClickListener(view -> {

            AppUtil.showDialog(getContext());

            getInputValues();
            if (!validationValues()) {
                AppUtil.dismissDialog();
                return;


            }


            if (Integer.valueOf(currentWeight) < Integer.valueOf(targetWeight)) {
                AppUtil.snack(binding.getRoot(), getString(R.string.current_weight_error));
                AppUtil.dismissDialog();
                return;
            }

            int diff = Integer.valueOf(currentFat) - Integer.valueOf(targetFat);

            fetchRecommendedArticle(diff);


        });
    }

    private void backToMain(View v) {
    }

    private void initRecycler() {
        binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.articlesRecycler.setAdapter(adapter);

        adapter.setOnSuggestClickListener((view, article) -> {
            SharedPrefUtil.save(getContext(), "desc", article.getDescription());
            SharedPrefUtil.save(getContext(), "image_url", article.getImageUrl());
            SharedPrefUtil.save(getContext(), "title", article.getTitle());
            Intent in = new Intent(getContext(), ArticleActivity.class);
            startActivity(in);
        });
    }

    private boolean validationValues() {

        if (TextUtils.isEmpty(currentFat) || TextUtils.isEmpty(currentWeight)
                || TextUtils.isEmpty(targetFat) || TextUtils.isEmpty(targetWeight)) {
            AppUtil.snack(binding.getRoot(), getString(R.string.fields_empty));
            return false;
        }

        return true;

    }

    @Override
    public void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }
}
