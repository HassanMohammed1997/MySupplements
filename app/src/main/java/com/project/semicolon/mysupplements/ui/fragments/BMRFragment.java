package com.project.semicolon.mysupplements.ui.fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.project.semicolon.mysupplements.R;
import com.project.semicolon.mysupplements.databinding.BMRFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BMRFragment extends Fragment {
    private BMRFragmentBinding binding;
    private double activityLevel;
    private String gender;


    public BMRFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = BMRFragmentBinding.inflate(inflater, container, false);
        binding.activityLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        activityLevel = 1.2;
                        break;
                    case 1:
                        activityLevel = 1.6;
                        break;
                    case 2:
                        activityLevel = 1.8;
                        break;
                    default:
                        activityLevel = 2.0;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != -1) {
                    gender = String.valueOf(adapterView.getSelectedItem());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(binding.inputAge.getText().toString())) {
                    binding.inputAge.setError(getString(R.string.required_error));
                    return;
                } else if (TextUtils.isEmpty(binding.inputWeight.getText().toString())) {
                    binding.inputWeight.setError(getString(R.string.required_error));
                    return;
                } else if (TextUtils.isEmpty(binding.inputHeight.getText().toString())) {
                    binding.inputHeight.setError(getString(R.string.required_error));
                    return;
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(getActivity(), R.string.gender_text, Toast.LENGTH_SHORT).show();
                    return;
                } else if (activityLevel == 0) {
                    Toast.makeText(getActivity(), R.string.activity_level_string, Toast.LENGTH_SHORT).show();
                    return;
                }

                int weight = Integer.parseInt(binding.inputWeight.getText().toString());
                int height = Integer.parseInt(binding.inputHeight.getText().toString());
                int age = Integer.parseInt(binding.inputAge.getText().toString());

                double result = calculateBMR(weight, height, age, gender, activityLevel);
                Toast.makeText(getActivity(), "Your BMR result: " + result, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();


    }

    public double calculateBMR(int weight, int height, int age, String gender, double levelActivity) {
        double result;
        if (gender.equalsIgnoreCase("male")) {
            result = (10 * weight + 6.25 * height - 5 * age + 5) * levelActivity;
        } else {
            result = (10 * weight + 6.25 * height - 5 * age - 161) * levelActivity;
        }

        return result;
    }

}
