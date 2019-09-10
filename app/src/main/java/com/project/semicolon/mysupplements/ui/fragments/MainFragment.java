package com.project.semicolon.mysupplements.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.adapter.MainAdapter;
import com.project.semicolon.mysupplements.databinding.MainFragmentBinding;
import com.project.semicolon.mysupplements.model.MainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private MainFragmentBinding binding;
    private List<MainModel> mainModels = new ArrayList<>();
    private MainModel mainModel;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = MainFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainModel = new MainModel(getString(R.string.bmr_calculator), R.drawable.ic_calculator);
        mainModels.add(mainModel);

        mainModel = new MainModel(getString(R.string.fat_burner), R.drawable.ic_list);
        mainModels.add(mainModel);

        mainModel = new MainModel(getString(R.string.category), R.drawable.ic_list);
        mainModels.add(mainModel);


        mainModel = new MainModel(getString(R.string.share), R.drawable.ic_share);
        mainModels.add(mainModel);

        mainModel = new MainModel(getString(R.string.about_app), R.drawable.ic_smartphone);
        mainModels.add(mainModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mainRecycler.setHasFixedSize(true);
        setAdapter();
    }

    private void setAdapter() {


        MainAdapter adapter = new MainAdapter(getContext());
        adapter.setList(mainModels);

        binding.mainRecycler.setAdapter(adapter);

        adapter.setItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                switch (position) {
                    case 0:
                        Navigation.findNavController(v).navigate(R.id.BMRFragment);
                        break;
                    case 1:
                        Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_fatBurnerFragment);
                        break;
                    case 2:
                        Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_categoriesFragment);
                        break;

                }
            }
        });
    }
}