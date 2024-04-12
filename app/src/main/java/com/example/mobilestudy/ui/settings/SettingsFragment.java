package com.example.mobilestudy.ui.settings;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.mobilestudy.R;
import com.example.mobilestudy.data.DummyDatabaseCard;
import com.example.mobilestudy.data.DummyDatabaseSettings;
import com.example.mobilestudy.databinding.FragmentSettingsBinding;


/**
 * Фрагмент настроек, позволяющий пользователю выбирать город и тип событий.
 */
public class SettingsFragment extends Fragment {

    /**
     * Привязка к макету фрагмента
     */
    private FragmentSettingsBinding binding;

    /**
     * Слушатель нажатия кнопки "назад"
     */
    private OnBackButtonClickListener backButtonClickListener;

    /**
     * Настройки, храниящиеся во внутренней памяти
     */
    private DummyDatabaseSettings settingsDatabase;

    /**
     * Метод, вызываемый при присоединении фрагмента к его контексту.
     *
     * @param context Контекст, к которому присоединяется фрагмент.
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnBackButtonClickListener) {
            backButtonClickListener = (OnBackButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnBackButtonClickListener");
        }
    }

    /**
     * Метод, вызываемый при создании представления фрагмента.
     *
     * @param inflater           Инфлейтер для раздувания макета.
     * @param container          Контейнер для раздутого макета.
     * @param savedInstanceState Сохраненное состояние фрагмента, если оно есть.
     * @return Возвращает представление фрагмента.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        settingsDatabase = DummyDatabaseSettings.getInstance();

        String[] cities = settingsDatabase.getAvailableCities();
        String[] events = settingsDatabase.getAvailableEventTypes();
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, cities);
        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, events);
        binding.dropdownCities.setAdapter(citiesAdapter);
        binding.dropdownEvents.setAdapter(eventsAdapter);

        updateSettingsChosenText();

        binding.dropdownCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = (String) parent.getItemAtPosition(position);
                settingsDatabase.setCity(selectedCity);
                updateSettingsChosenText();
            }
        });

        binding.dropdownEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedEventType = (String) parent.getItemAtPosition(position);
                settingsDatabase.setEventType(selectedEventType);
                updateSettingsChosenText();
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

    private void updateSettingsChosenText() {
        String chosenText = settingsDatabase.getCity() + " " + settingsDatabase.getEventType();
        binding.settingsChosen.setText(chosenText);
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
