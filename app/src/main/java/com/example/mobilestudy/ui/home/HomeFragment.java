package com.example.mobilestudy.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudy.R;
import com.example.mobilestudy.adapter.CardAdapter;
import com.example.mobilestudy.databinding.FragmentHomeBinding;
import com.example.mobilestudy.dto.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент, отображающий домашнюю страницу.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Card> cardList;

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
        cardList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new CardAdapter(cardList);
        recyclerView.setAdapter(adapter);

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
        List<Card> cards = new ArrayList<>();

        cards.add(new Card("Event 1", "Place 1"));
        cards.add(new Card("Event 2", "Place 2"));

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
