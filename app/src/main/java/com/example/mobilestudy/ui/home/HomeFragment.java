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
import com.example.mobilestudy.adapter.CardAdapter;
import com.example.mobilestudy.data.DummyDatabaseCard;
import com.example.mobilestudy.data.DummyDatabaseSettings;
import com.example.mobilestudy.databinding.FragmentHomeBinding;
import com.example.mobilestudy.dto.Card;
import com.example.mobilestudy.ui.detail.DetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент, отображающий домашнюю страницу.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Card> cardList;

    private DummyDatabaseCard cardDatabase;

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

        cardDatabase = DummyDatabaseCard.getInstance();
        settingsDatabase = DummyDatabaseSettings.getInstance();

        String homeTitle = getHomeTitle();
        binding.homeTitle.setText(homeTitle);
        cardList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CardAdapter(cardList);
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
        adapter.setOnButtonGoingClickListener(new CardAdapter.OnButtonGoingClickListener() {
            @Override
            public void onGoingClick(int position) {
                Card selectedCard = cardList.get(position);
                cardDatabase.updateIsFavoriteById(selectedCard.getId(), !selectedCard.getIsFavorite());
                updateNoteList();
            }
        });

        adapter.setOnButtonShowMoreClickListener(new CardAdapter.OnButtonShowMoreClickListener() {
            @Override
            public void onShowMoreClick(int position) {
                Card selectedCard = cardList.get(position);

                DetailFragment fragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putString("eventName", selectedCard.getEventName());
                bundle.putString("eventDescription", selectedCard.getDescription());
                bundle.putString("eventPreview", selectedCard.getImagePreview());
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsButtonClickListener.onSettingsButtonClick();
            }
        });

        updateNoteList();

        return view;
    }

    private String getHomeTitle() {
        return settingsDatabase.getCity() + "\n" + settingsDatabase.getEventType();
    }

    private void filterList(String newText) {
        List<Card> filteredList = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getEventName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(card);
            }
        }

        if (!filteredList.isEmpty()) {
            adapter.setFilteredList(filteredList);
        }
    }

    public void updateNoteList() {
        String city = settingsDatabase.getCity();
        String eventType = settingsDatabase.getEventType();
        List<Card> cards = cardDatabase.getCardsByCityAndEventType(city, eventType);

        cardList.clear();
        cardList.addAll(cards);
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
