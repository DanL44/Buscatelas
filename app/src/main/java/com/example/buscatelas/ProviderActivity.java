package com.example.buscatelas;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.buscatelas.databinding.ActivityClientBinding;
import com.example.buscatelas.databinding.ActivityProviderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProviderActivity extends AppCompatActivity {

    private ActivityProviderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        this.getWindow().setFlags(WindowManager. LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams. FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        binding = ActivityProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragmentProvider, R.id.navigation_dashboard, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_provider);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

}