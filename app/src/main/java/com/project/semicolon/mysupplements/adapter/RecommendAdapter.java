package com.project.semicolon.mysupplements.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.semicolon.mysupplements.databinding.RecommendListBinding;
import com.project.semicolon.mysupplements.model.Article;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private Context context;
    private List<Article> articles;
    private OnSuggestClickListener onSuggestClickListener;

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public void setOnSuggestClickListener(OnSuggestClickListener onSuggestClickListener) {
        this.onSuggestClickListener = onSuggestClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecommendListBinding binding = RecommendListBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(articles.get(position));


    }

    @Override
    public int getItemCount() {
        if (articles == null)
            return 0;
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecommendListBinding binding;

        public ViewHolder(RecommendListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindTo(Article article) {
            Glide.with(context)
                    .load(article.getImageUrl())
                    .into(binding.articleImage);

            performOnClickListener(binding, article);


        }

    }

    private void performOnClickListener(RecommendListBinding binding, Article article) {

        binding.getRoot().setOnClickListener(view -> {
            if (onSuggestClickListener != null)
                onSuggestClickListener.suggestClicked(view, article);
        });
    }

    public interface OnSuggestClickListener{
        void suggestClicked(View view, Article article);
    }
}
