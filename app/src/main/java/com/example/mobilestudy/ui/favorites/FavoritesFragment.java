package com.example.mobilestudy.ui.favorites;

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

import com.example.mobilestudy.R;
import com.example.mobilestudy.adapter.CardAdapter;
import com.example.mobilestudy.data.DummyDatabaseCard;
import com.example.mobilestudy.databinding.FragmentFavoritesBinding;
import com.example.mobilestudy.dto.Card;
import com.example.mobilestudy.ui.detail.DetailFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Фрагмент, отображающий список избранных элементов.
 */
public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Card> cardList;

    private DummyDatabaseCard database;

    /**
     * Поле для привязки макета фрагмента
     */
    private FragmentFavoritesBinding binding;

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
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        cardList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.fragment_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CardAdapter(cardList);
        recyclerView.setAdapter(adapter);

        database = DummyDatabaseCard.getInstance();

        adapter.setOnButtonGoingClickListener(new CardAdapter.OnButtonGoingClickListener() {
            @Override
            public void onGoingClick(int position) {
                Card selectedCard = cardList.get(position);
                database.updateIsFavoriteById(selectedCard.getId(), !selectedCard.getIsFavorite());
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

    public void updateNoteList() {
        List<Card> cards = database.getFavoriteCards();

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
