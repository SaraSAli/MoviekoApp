package com.example.movieapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.movieapplication.databinding.ActivityMainBinding;
import com.example.movieapplication.fragments.MovieListFragment;
import com.example.movieapplication.fragments.SearchFragment;
import com.example.movieapplication.fragments.TVShowsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    Map<Integer, Fragment> fragments = new HashMap<>();

    MovieListFragment movieListFragment = new MovieListFragment();
    SearchFragment searchFragment = new SearchFragment();
    TVShowsFragment tvShowsFragment = new TVShowsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragments.put(R.id.action_movies, movieListFragment);
        fragments.put(R.id.action_search, searchFragment);
        fragments.put(R.id.action_tv_shows, tvShowsFragment);

        binding.bottomNavigation.setOnItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.action_movies);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragments.get(item.getItemId());
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}