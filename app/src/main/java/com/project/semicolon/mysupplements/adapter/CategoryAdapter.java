package com.project.semicolon.mysupplements.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.semicolon.mysupplements.databinding.CategoryListBinding;
import com.project.semicolon.mysupplements.model.Category;
import com.project.semicolon.mysupplements.utils.AppUtil;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> categories;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryListBinding binding = CategoryListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindTo(categories.get(position));

    }

    @Override
    public int getItemCount() {
        if (categories == null) {
            return 0;

        }
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private CategoryListBinding binding;

        public CategoryViewHolder(CategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(final Category category) {
            AppUtil.loadImage(context, category.getImage(), binding.categoryImage);
            binding.categoryName.setText(category.getName());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(view, category);

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, Category category);
    }
}
