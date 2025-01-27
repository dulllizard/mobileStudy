package com.example.mobilestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.mobilestudy.databinding.ActivityMainBinding;
import com.example.mobilestudy.ui.detail.DetailFragment;
import com.example.mobilestudy.ui.favorites.FavoritesFragment;
import com.example.mobilestudy.ui.home.HomeFragment;
import com.example.mobilestudy.ui.map.MapFragment;
import com.example.mobilestudy.ui.settings.SettingsFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnSettingsButtonClickListener,
        SettingsFragment.OnBackButtonClickListener,
        DetailFragment.OnBackButtonClickListener,
        FavoritesFragment.OnSettingsButtonClickListener,
        MapFragment.OnSettingsButtonClickListener {

    /**
     * Привязка к макету активности
     */
    private ActivityMainBinding binding;

    /**
     * Нижняя навигационная панель
     */
    private MeowBottomNavigation bottomNavigation;

    /**
     * Метод, вызываемый при создании активности.
     *
     * @param savedInstanceState Сохраненное состояние активности, если оно есть.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigation = binding.bottomNavigation;

        replace(new HomeFragment());
        bottomNavigation.show(2, true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_favorite_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_map_24));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {
                    case 1:
                        replace(new FavoritesFragment());
                        break;

                    case 2:
                        replace(new HomeFragment());
                        break;

                    case 3:
                        replace(new MapFragment());
                        break;
                }
                return null;
            }
        });
    }

    /**
     * Обработчик нажатия кнопки "назад".
     */
    @Override
    public void onBackButtonClick() {
        replace(new HomeFragment());
        bottomNavigation.show(2, true);
    }

    /**
     * Обработчик нажатия кнопки "настройки".
     */
    @Override
    public void onSettingsButtonClick() {
        replace(new SettingsFragment());
    }

    /**
     * Метод для замены текущего фрагмента.
     *
     * @param fragment Фрагмент, который нужно отобразить.
     */
    private void replace(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.framelayout.getId(), fragment);
        transaction.commit();
    }
}