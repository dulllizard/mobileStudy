package com.example.mobilestudy.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.mobilestudy.R;
import com.example.mobilestudy.adapter.EventAdapter;
import com.example.mobilestudy.data.DatabaseHelper;
import com.example.mobilestudy.data.DummyDatabaseSettings;
import com.example.mobilestudy.databinding.FragmentHomeBinding;
import com.example.mobilestudy.dto.Event;
import com.example.mobilestudy.ui.detail.DetailFragment;
import com.example.mobilestudy.ui.edit.EditFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент, отображающий домашнюю страницу.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    /**
     * Адаптер записей
     */
    private EventAdapter adapter;

    /**
     * Список записей
     */
    private List<Event> eventList;

    /**
     * База данных
     */
    private DatabaseHelper dbHelper;

    private DummyDatabaseSettings settingsDatabase;

    /**
     * Поле для привязки макета фрагмента
     */
    private FragmentHomeBinding binding;

    /**
     * Поле для слушателя нажатия кнопки настроек
     */
    private OnSettingsButtonClickListener settingsButtonClickListener;

    /**
     * Метод, вызываемый при присоединении фрагмента к его контексту.
     *
     * @param context Контекст, к которому присоединяется фрагмент.
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnSettingsButtonClickListener) {
            settingsButtonClickListener = (OnSettingsButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnSettingsButtonClickListener");
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHelper = new DatabaseHelper(getContext());
        settingsDatabase = DummyDatabaseSettings.getInstance();

        String homeTitle = getHomeTitle();
        binding.homeTitle.setText(homeTitle);
        eventList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);


        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        adapter.setOnButtonGoingClickListener(new EventAdapter.OnButtonGoingClickListener() {
            @Override
            public void onGoingClick(int position) {
                Event selectedEvent = eventList.get(position);
                dbHelper.updateIsFavoriteById(selectedEvent.getId(), !selectedEvent.getIsFavorite());
                updateEventList();
            }
        });

        adapter.setOnButtonShowMoreClickListener(new EventAdapter.OnButtonShowMoreClickListener() {
            @Override
            public void onShowMoreClick(int position) {
                Event selectedEvent = eventList.get(position);

                DetailFragment fragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putString("eventName", selectedEvent.getEventName());
                bundle.putString("eventDescription", selectedEvent.getDescription());
                bundle.putString("eventPreview", selectedEvent.getImagePreview());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        adapter.setOnButtonEditClickListener(new EventAdapter.onButtonEditListener() {
            @Override
            public void onEditClick(int position) {
                Event selectedEvent = eventList.get(position);

                EditFragment fragment = new EditFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("eventId", selectedEvent.getId());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        adapter.setOnButtonDeleteClickListener(new EventAdapter.onButtonDeleteListener() {
            @Override
            public void onDeleteClick(int position) {
                Event selectedEvent = eventList.get(position);
                int id = selectedEvent.getId();
                dbHelper.deleteEventById(id);
                updateEventList();
            }
        });

        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsButtonClickListener.onSettingsButtonClick();
            }
        });

        updateEventList();

        return view;
    }

    /**
     * Получение заголовка домашней страницы
     */
    private String getHomeTitle() {
        return settingsDatabase.getCity() + "\n" + settingsDatabase.getEventType();
    }

    /**
     * Фильтрация списка записей по текстовому запросу
     */
    private void filterList(String newText) {
        List<Event> filteredList = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getEventName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(event);
            }
        }

        if (!filteredList.isEmpty()) {
            adapter.setFilteredList(filteredList);
        }
    }
    /**
     * Обновление списка записей
     */
    public void updateEventList() {
        String city = settingsDatabase.getCity();
        String eventType = settingsDatabase.getEventType();
        List<Event> events = dbHelper.getEventsByCityAndEventType(city, eventType);
        eventList.clear();
        eventList.addAll(events);
        adapter.notifyDataSetChanged();
    }

    /**
     * Интерфейс для обработки нажатия кнопки настроек.
     */
    public interface OnSettingsButtonClickListener {

        /**
         * Вызывается при нажатии кнопки настроек.
         */
        void onSettingsButtonClick();
    }
}
