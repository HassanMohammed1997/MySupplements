package com.project.semicolon.mysupplements.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.semicolon.mysupplements.databinding.MainFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private MainFragmentBinding binding;

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

    }


}