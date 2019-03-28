package com.sun.music61.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.sun.music61.R;
import com.sun.music61.screen.home.HomeFragment;
import com.sun.music61.util.ActivityUtils;
import java.util.Objects;

public class MainFragment extends Fragment {

    private BottomNavigationView mMenuBottom;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        initView(rootView);
        // Default Fragment
        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), HomeFragment.newInstance(),
                R.id.contentFrame);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onEventListener();
    }

    private void initView(View rootView) {
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(R.string.sub_title_slogan);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        mMenuBottom = rootView.findViewById(R.id.menuBottom);
        mMenuBottom.setSelectedItemId(R.id.actionHome);
    }

    private void onEventListener() {
        mMenuBottom.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actionHome:
                ActivityUtils.replaceFragmentToActivity(getChildFragmentManager(),
                        HomeFragment.newInstance(), R.id.contentFrame);
                break;
            case R.id.actionMySong:
            case R.id.actionFavorite:
            case R.id.actionSetting:
                Toast.makeText(getContext(), getString(R.string.text_coming_soon),
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
