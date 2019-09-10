package com.project.semicolon.mysupplements.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.semicolon.mysupplements.model.Article;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.repository.CategoryRepo;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private MutableLiveData<List<Category>> categoryMutableLiveData;
    private MutableLiveData<List<Article>> articleLiveData;
    private CategoryRepo categoryRepo;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepo = new CategoryRepo(application);
        categoryMutableLiveData = categoryRepo.getCategoryFromServer();
        articleLiveData = categoryRepo.getArticleFromServer();

    }

    public MutableLiveData<List<Category>> getCategoryMutableLiveData() {
        return categoryMutableLiveData;
    }

    public MutableLiveData<List<Article>> getArticleLiveData() {
        return articleLiveData;
    }

    public MutableLiveData<Article> getArticle(int id) {
        return categoryRepo.getArticle(id);
    }

    public LiveData<List<Article>> getArticles(int categoryId) {
        return categoryRepo.getArticles(categoryId);
    }

    public LiveData<List<Article>> getArticleBasedOnGroup(int group){
        return categoryRepo.getArticlesBasedOnGroup(group);
    }
}
