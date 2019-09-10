package com.project.semicolon.mysupplements.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.adapter.CategoryAdapter;
import com.project.semicolon.mysupplements.databinding.CategoriesActivityBinding;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private CategoriesActivityBinding binding;
    private CategoryViewModel categoryViewModel;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(this);

        binding.categoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRecycler.setHasFixedSize(true);
        binding.categoriesRecycler.setAdapter(adapter);

        categoryViewModel = new CategoryViewModel(getApplication());
        categoryViewModel.getCategoryMutableLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setCategories(categories);
            }
        });


    }
}
