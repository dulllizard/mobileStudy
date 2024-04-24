package com.example.mobilestudy.ui.map;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudy.R;
import com.example.mobilestudy.data.DatabaseHelper;
import com.example.mobilestudy.databinding.FragmentMapBinding;
import com.example.mobilestudy.dto.Event;
import com.example.mobilestudy.ui.detail.DetailFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    private DatabaseHelper dbHelper;

    private Activity activity;

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

        dbHelper = new DatabaseHelper(getContext());

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

        // Получаем геокодер
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        // Получаем список всех событий
        List<Event> events = dbHelper.getEventsByCityAndEventType("Красноярск", "Выставки");

        activity = getActivity();

        // Создаем ExecutorService
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Выполняем задачу загрузки и отображения меток на карте
        executorService.execute(() -> {
            for (Event event : events) {
                addMarkerForEvent(geocoder, event);
            }

            // Перемещаем камеру на город Красноярск
            moveCameraToCity(geocoder, "Красноярск");
        });

        // Останавливаем ExecutorService после выполнения задачи
        executorService.shutdown();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                // Получаем название события из метки
                String eventName = marker.getTitle();

                // Находим событие по названию
                Event event = null;
                for (Event e : events) {
                    if (e.getEventName().equals(eventName)) {
                        event = e;
                        break;
                    }
                }

                // Если событие найдено, создаем и отображаем DetailFragment
                if (event != null) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle args = new Bundle();
                    args.putString("eventName", event.getEventName());
                    args.putString("eventDescription", event.getDescription());
                    args.putString("eventPreview", event.getImagePreview());
                    detailFragment.setArguments(args);

                    // Заменяем текущий фрагмент на DetailFragment
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout, detailFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                // Возвращаем true, чтобы не вызывать стандартное действие при клике на метку
                return true;
            }
        });
    }

    /**
     * Метод для добавления метки на карту для заданного события.
     */
    private void addMarkerForEvent(Geocoder geocoder, Event event) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(event.getEventPlace() + ", " + event.getCity(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                // Устанавливаем метку на карту
                LatLng latLng = new LatLng(latitude, longitude);
                activity.runOnUiThread(() -> mMap.addMarker(new MarkerOptions().position(latLng).title(event.getEventName())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для перемещения камеры на указанный город.
     */
    private void moveCameraToCity(Geocoder geocoder, String cityName) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(cityName, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);
                activity.runOnUiThread(() -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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