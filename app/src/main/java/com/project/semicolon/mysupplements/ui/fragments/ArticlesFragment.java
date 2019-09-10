package com.project.semicolon.mysupplements.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.adapter.ArticleAdapter;
import com.project.semicolon.mysupplements.databinding.ArticlesFragmentBinding;
import com.project.semicolon.mysupplements.model.Article;
import com.project.semicolon.mysupplements.utils.Constants;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment implements Constants {
    private ArticlesFragmentBinding binding;
    private CategoryViewModel viewModel;

    private int id;

    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ArticlesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this)
                .get(CategoryViewModel.class);
        initRecyclerView();

    }

    private void initRecyclerView() {
        binding.articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.articlesRecycler.setHasFixedSize(true);

        id = Integer.parseInt(String.valueOf(SharedPrefUtil.getData(getContext(),
                CATEGORY_ID, 0)));

        initAdapter();


    }

    private void initAdapter() {
        final ArticleAdapter adapter = new ArticleAdapter(getContext());

        viewModel.getArticles(id)
                .observe(this, new Observer<List<Article>>() {
                    @Override
                    public void onChanged(List<Article> articles) {
                        if (articles != null) {
                            adapter.setArticles(articles);
                            binding.progress.setVisibility(View.GONE);
                        } else {
                            binding.progress.setVisibility(View.GONE);
                        }

                    }
                });

        binding.articlesRecycler.setAdapter(adapter);
    }
}
