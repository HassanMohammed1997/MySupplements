package com.project.semicolon.mysupplements.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.semicolon.mysupplements.databinding.MainListBinding;
import com.project.semicolon.mysupplements.model.MainModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainModel> list;
    private Context context;
    private OnItemClickListener itemClickListener;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<MainModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MainListBinding binding = MainListBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(list.get(position));


    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MainListBinding binding;

        public ViewHolder(MainListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(MainModel mainModel) {
            binding.mainTitle.setText(mainModel.getMain());

            binding.mainTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, getAdapterPosition());

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
