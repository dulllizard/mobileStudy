package com.example.mobilestudy.ui.edit;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilestudy.R;
import com.example.mobilestudy.data.DatabaseHelper;
import com.example.mobilestudy.databinding.FragmentCreateBinding;
import com.example.mobilestudy.databinding.FragmentEditBinding;
import com.example.mobilestudy.dto.Event;
import com.example.mobilestudy.ui.create.CreateFragment;
import com.example.mobilestudy.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;


public class EditFragment extends Fragment {

    private FragmentEditBinding binding;

    private OnBackButtonClickListener backButtonClickListener;

    private DatabaseHelper dbHelper;

    private Event event;

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

        binding = FragmentEditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHelper = new DatabaseHelper(getContext());

        Bundle args = getArguments();

        if (args != null) {
            event = dbHelper.getEventById(args.getInt("eventId"));
            binding.nameInput.getEditText().setText(event.getEventName());
            binding.descriptionInput.getEditText().setText(event.getDescription());
            binding.eventPlaceInput.getEditText().setText(event.getEventPlace());
            binding.eventCityInput.getEditText().setText(event.getCity());
            binding.eventTypeInput.getEditText().setText(event.getEventType());
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClickListener.onBackButtonClick();
            }
        });

        binding.changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = binding.nameInput.getEditText().getText().toString();
                String description = binding.descriptionInput.getEditText().getText().toString();
                String eventPlace = binding.eventPlaceInput.getEditText().getText().toString();
                String city = binding.eventCityInput.getEditText().getText().toString();
                String eventType = binding.eventTypeInput.getEditText().getText().toString();

                event.setEventName(eventName);
                event.setDescription(description);
                event.setEventPlace(eventPlace);
                event.setCity(city);
                event.setEventType(eventType);

                dbHelper.updateEventById(event.getId(), event);

                HomeFragment fragment = new HomeFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .addToBackStack(null)
                        .commit();

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
}