package com.example.mobilestudy.ui.settings;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mobilestudy.R;


public class SettingsFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ImageButton backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
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