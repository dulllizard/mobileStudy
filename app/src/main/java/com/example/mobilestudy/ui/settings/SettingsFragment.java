package com.example.mobilestudy.ui.settings;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.example.mobilestudy.R;
import com.example.mobilestudy.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private OnBackButtonClickListener backButtonClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnBackButtonClickListener) {
            backButtonClickListener = (OnBackButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnBackButtonClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String[] cities = getResources().getStringArray(R.array.cities);
        String[] events = getResources().getStringArray(R.array.events);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, cities);
        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, events);
        binding.dropdownCities.setAdapter(citiesAdapter);
        binding.dropdownEvents.setAdapter(eventsAdapter);


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClickListener.onBackButtonClick();
            }
        });

        return view;
    }

    public interface OnBackButtonClickListener {
        void onBackButtonClick();
    }
}
