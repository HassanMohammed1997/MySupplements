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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.adapter.CategoryAdapter;
import com.project.semicolon.mysupplements.databinding.CategoriesFragmentBinding;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.utils.Constants;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements Constants {
    private CategoriesFragmentBinding binding;
    private CategoryViewModel categoryViewModel;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryAdapter adapter;


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = CategoriesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getContext());

        binding.categoriesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.categoriesRecycler.setHasFixedSize(true);
        binding.categoriesRecycler.setAdapter(adapter);

        categoryViewModel = ViewModelProviders.of(this)
                .get(CategoryViewModel.class);
        categoryViewModel.getCategoryMutableLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (categories != null) {
                    adapter.setCategories(categories);
                }

                if (binding.progress.getVisibility() == View.VISIBLE) {
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Category category) {
                SharedPrefUtil.save(getContext(), CATEGORY_ID, category.getId());
                Navigation.findNavController(v).navigate(R.id.articlesFragment);
            }
        });


    }
}
