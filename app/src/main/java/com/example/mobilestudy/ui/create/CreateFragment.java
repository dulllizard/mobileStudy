package com.example.mobilestudy.ui.create;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mobilestudy.R;
import com.example.mobilestudy.data.DatabaseHelper;
import com.example.mobilestudy.databinding.FragmentCreateBinding;
import com.example.mobilestudy.dto.Event;
import com.example.mobilestudy.ui.home.HomeFragment;


public class CreateFragment extends Fragment {

    private FragmentCreateBinding binding;

    private OnBackButtonClickListener backButtonClickListener;

    private DatabaseHelper dbHelper;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHelper = new DatabaseHelper(getContext());


        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = binding.nameInput.getEditText().getText().toString();
                String description = binding.descriptionInput.getEditText().getText().toString();

                String eventPlace = binding.eventPlaceInput.getEditText().getText().toString();
                String imagePreview = getResources().getString(R.string.baseImageURL);
                String city = binding.eventCityInput.getEditText().getText().toString();
                String eventType = binding.eventTypeInput.getEditText().getText().toString();
                Boolean isFavorite = false;

                Event event = new Event(eventName, eventPlace, description, imagePreview, city, eventType, isFavorite);
                dbHelper.addEvent(event);

                HomeFragment fragment = new HomeFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClickListener.onBackButtonClick();
            }
        });

        return view;
    }

    /**
     * Интерфейс для обработки нажатия кнопки "назад".
     */
    public interface OnBackButtonClickListener {
        /**
         * Вызывается при нажатии кнопки "назад".
         */
        void onBackButtonClick();
    }

    public interface onCreateButtonClickListener {
        void onCreateButtonClick();
    }

    private onCreateButtonClickListener buttonCreateListener;

    public void setOnButtonCreateClickListener(onCreateButtonClickListener listener) {
        buttonCreateListener = listener;
    }
}