package com.example.mobilestudy.ui.map;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudy.R;
import com.example.mobilestudy.databinding.FragmentMapBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Фрагмент, отображающий карту событий.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    /**
     * Поле для привязки макета фрагмента
     */
    private FragmentMapBinding binding;

    /**
     * Поле для слушателя нажатия кнопки настроек
     */
    private OnSettingsButtonClickListener settingsButtonClickListener;

    private GoogleMap mMap;

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
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsButtonClickListener.onSettingsButtonClick();
            }
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Устанавливаем координаты перекрёстка
        LatLng crossroads = new LatLng(47.249912481336494, 39.69719647988453);

        // Добавляем маркер на карту
        mMap.addMarker(new MarkerOptions().position(crossroads).title("Перекресток"));

        // Включаем или выключаем отображение зданий
        mMap.setBuildingsEnabled(true);

        // Включаем или выключаем внутреннюю картографию
        mMap.setIndoorEnabled(true);
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