package com.example.mobilestudy.ui;

import androidx.fragment.app.Fragment;

import com.example.mobilestudy.ui.detail.DetailFragment;
import com.example.mobilestudy.ui.favorites.FavoritesFragment;
import com.example.mobilestudy.ui.home.HomeFragment;
import com.example.mobilestudy.ui.map.MapFragment;
import com.example.mobilestudy.ui.settings.SettingsFragment;

public class FragmentFactory {
    private Fragment detailFragment = null;

    private Fragment favoritesFragment = null;

    private Fragment homeFragment = null;

    private Fragment mapFragment = null;

    private Fragment settingsFragment = null;

    public Fragment getDetailFragment() {
        if (this.detailFragment == null) {
            setDetailFragment();
        }
        return this.detailFragment;
    }

    private void setDetailFragment() {
        this.detailFragment = new DetailFragment();
    }

    public Fragment getFavoritesFragment() {
        if (this.favoritesFragment == null) {
            setFavoritesFragment();
        }
        return this.favoritesFragment;
    }

    private void setFavoritesFragment() {
        this.favoritesFragment = new FavoritesFragment();
    }


    public Fragment getHomeFragment() {
        if (this.homeFragment == null) {
            setHomeFragment();
        }
        return this.homeFragment;
    }

    private void setHomeFragment() {
        this.homeFragment = new HomeFragment();
    }

    public Fragment getMapFragment() {
        if (this.mapFragment == null) {
            setMapFragment();
        }
        return this.mapFragment;
    }

    private void setMapFragment() {
        this.mapFragment = new MapFragment();
    }

    public Fragment getSettingsFragment() {
        if (this.settingsFragment == null) {
            setSettingsFragment();
        }
        return this.settingsFragment;
    }

    private void setSettingsFragment() {
        this.settingsFragment = new SettingsFragment();
    }
}
