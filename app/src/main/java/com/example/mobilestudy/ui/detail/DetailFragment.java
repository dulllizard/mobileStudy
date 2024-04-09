package com.example.mobilestudy.ui.detail;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilestudy.R;
import com.example.mobilestudy.databinding.FragmentDetailBinding;


/**
 * Фрагмент, отображающий детальную информацию.
 */
public class DetailFragment extends Fragment {

    /**
     * Поле для привязки макета фрагмента
     */
    private FragmentDetailBinding binding;

    /**
     * Поле для слушателя нажатия кнопки "назад"
     */
    private OnBackButtonClickListener backButtonClickListener;

    /**
     * Вызывается, когда фрагмент прикрепляется к контексту (активности).
     * Устанавливает слушатель нажатия на кнопку "Назад".
     *
     * @param context Контекст, к которому прикреплен фрагмент
     * @throws ClassCastException Если контекст не реализует интерфейс OnBackButtonClickListener
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
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClickListener.onBackButtonClick();
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            String eventName = args.getString("eventName");
            TextView eventNameView = view.findViewById(R.id.eventName);
            eventNameView.setText(eventName);

            TextView eventDescription = view.findViewById(R.id.eventDescription);
            eventDescription.setText("Example");

        }

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
