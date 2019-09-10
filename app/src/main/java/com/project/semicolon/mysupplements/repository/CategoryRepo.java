package com.project.semicolon.mysupplements.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.semicolon.mysupplements.model.Article;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.utils.Constants;
import com.project.semicolon.mysupplements.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepo implements Constants {
    private DatabaseReference categoryRef, articleRef;
    private MutableLiveData<List<Category>> categoryLiveData;
    private MutableLiveData<List<Article>> articleLiveData;
    private Application application;

    public CategoryRepo(Application application) {
        this.application = application;
        categoryRef = FirebaseDatabase.getInstance().getReference().child(CATEGORY_PATH);
        articleRef = FirebaseDatabase.getInstance().getReference().child(ARTICLE_PATH);
        categoryLiveData = new MutableLiveData<>();
        articleLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoryFromServer() {
        final List<Category> categories = new ArrayList<>();

        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (categories != null) {
                        categories.clear();
                    }
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        Category value = snapshot.getValue(Category.class);
                        if (value != null) {
                            categories.add(value);
                            Logger.verbose("onDataChange: " + snapshot);

                        } else {
                            categoryLiveData.postValue(null);
                        }
                    }

                    categoryLiveData.postValue(categories);


                } else {
                    categoryLiveData.postValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Logger.error("onCancelled: database error: " + databaseError.getMessage());
                categoryLiveData.postValue(null);


            }
        });

        return categoryLiveData;

    }

    public MutableLiveData<List<Article>> getArticleFromServer() {
        final List<Article> articles = new ArrayList<>();
        articleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (articles != null) {
                        articles.clear();
                    }

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        articles.add(child.getValue(Article.class));
                        Logger.verbose("onDataChanged: " + child);

                    }

                    articleLiveData.postValue(articles);


                } else {
                    articleLiveData.postValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Logger.error("onCancelled: database error: " + databaseError.getMessage());
                articleLiveData.postValue(null);


            }
        });

        return articleLiveData;

    }

    public MutableLiveData<Article> getArticle(int id) {
        final MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();
        articleRef.orderByKey().equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Logger.verbose("onDataChanged: article" + dataSnapshot.toString());
                            Article value = dataSnapshot.getValue(Article.class);
                            articleMutableLiveData.postValue(value);
                        } else {
                            articleMutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Logger.error("onCancelled: database error: " + databaseError.toException());
                        articleMutableLiveData.postValue(null);

                    }
                });

        return articleMutableLiveData;
    }

    public LiveData<List<Article>> getArticles(int categoryId) {
        final MutableLiveData<List<Article>> articlesLiveData = new MutableLiveData<>();
        final List<Article> articles = new ArrayList<>();
        articleRef.orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (articles != null) {
                                articles.clear();
                            }
                            Article value = dataSnapshot.getValue(Article.class);
                            if (value != null) {
                                articles.add(value);
                                articlesLiveData.postValue(articles);
                            } else {
                                articlesLiveData.postValue(null);
                            }
                        } else {
                            articlesLiveData.postValue(null);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Logger.verbose("onCancelled: database Error: " + databaseError.getMessage());

                    }
                });

        return articlesLiveData;

    }

    public LiveData<List<Article>> getArticlesBasedOnGroup(int group) {
        final MutableLiveData<List<Article>> articleLiveData = new MutableLiveData<>();
        final List<Article> articles = new ArrayList<>();

        articleRef.orderByChild("group")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (articles != null) {
                                articles.clear();
                            }

                            Article value = dataSnapshot.getValue(Article.class);
                            if (value != null) {
                                articles.add(value);
                                articleLiveData.postValue(articles);
                            } else {
                                articleLiveData.postValue(null);
                            }
                        } else {
                            articleLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Logger.verbose("onCancelled: database error: " + databaseError.getMessage());
                        articleLiveData.postValue(null);

                    }
                });

        return articleLiveData;
    }


}
