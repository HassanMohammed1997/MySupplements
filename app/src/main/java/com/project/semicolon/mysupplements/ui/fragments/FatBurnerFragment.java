package com.project.semicolon.mysupplements.ui.fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.databinding.FatBurnerBinding;
import com.project.semicolon.mysupplements.model.Article;
import com.project.semicolon.mysupplements.utils.AppUtil;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FatBurnerFragment extends Fragment {
    private static final String TAG = FatBurnerFragment.class.getSimpleName();
    private FatBurnerBinding binding;
    private String currentWeight, currentFat, targetWeight, targetFat;
    private CategoryViewModel viewModel;


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

        viewModel = ViewModelProviders.of(this)
                .get(CategoryViewModel.class);

        initRecycler();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.mainFragment);
            }
        });


        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentWeight = binding.etCurrentWeight.getText().toString();
                currentFat = binding.etCurrentFatPer.getText().toString();
                targetFat = binding.etTargetFatPer.getText().toString();
                targetWeight = binding.etTargetWeight.getText().toString();

                if (!validate())
                    return;


                if (Integer.valueOf(currentWeight) < Integer.valueOf(targetWeight)) {
                    AppUtil.snack(binding.getRoot(), getString(R.string.current_weight_error));
                    return;
                }

                int diff = Integer.valueOf(currentFat) - Integer.valueOf(targetFat);

                if (diff >= 0 && diff <= 3) {
                    viewModel.getArticleBasedOnGroup(1)
                            .observe(FatBurnerFragment.this, new Observer<List<Article>>() {
                                @Override
                                public void onChanged(List<Article> articles) {
                                    if (articles != null) {
                                        //implement
                                    }


                                }
                            });
                } else {
                    viewModel.getArticleBasedOnGroup(2)
                            .observe(FatBurnerFragment.this, new Observer<List<Article>>() {
                                @Override
                                public void onChanged(List<Article> articles) {
                                    if (articles != null) {
                                        //implement
                                    }


                                }
                            });
                }

            }
        });


    }

    private void initRecycler() {
        binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private boolean validate() {

        if (TextUtils.isEmpty(currentFat) || TextUtils.isEmpty(currentWeight)
                || TextUtils.isEmpty(targetFat) || TextUtils.isEmpty(targetWeight)) {
            AppUtil.snack(binding.getRoot(), getString(R.string.fields_empty));
            return false;
        }

        return true;

    }
}
