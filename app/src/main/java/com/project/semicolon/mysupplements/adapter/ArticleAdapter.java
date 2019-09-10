package com.project.semicolon.mysupplements.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.databinding.CategoryListBinding;
import com.project.semicolon.mysupplements.model.Article;
import com.project.semicolon.mysupplements.ui.ArticleActivity;
import com.project.semicolon.mysupplements.utils.SharedPrefUtil;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryListBinding binding = CategoryListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        holder.bindTo(articles.get(position));


    }

    @Override
    public int getItemCount() {
        if (articles == null)
            return 0;

        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CategoryListBinding binding;

        public ViewHolder(CategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindTo(final Article article) {
            binding.categoryName.setText(article.getTitle());
            Glide.with(context)
                    .load(article.getImageUrl())
                    .placeholder(R.drawable.article_place_holder)
                    .into(binding.categoryImage);

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO save description in Shared pref...
                    SharedPrefUtil.save(context, "desc", article.getDescription());
                    SharedPrefUtil.save(context, "image_url", article.getImageUrl());
                    SharedPrefUtil.save(context, "title", article.getTitle());
                    Intent in = new Intent(context, ArticleActivity.class);
                    context.startActivity(in);
                }
            });
        }
    }
}
