package com.example.buscatelas;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.buscatelas.Utils.Authentication;
import com.example.buscatelas.Utils.Database;
import com.example.buscatelas.databinding.ActivityClientBinding;
import com.example.buscatelas.databinding.ActivityProviderBinding;
import com.example.buscatelas.models.Client;
import com.example.buscatelas.models.ServiceProvider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProviderActivity extends AppCompatActivity {

    private ActivityProviderBinding binding;
    private Database database;
    private Authentication auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        this.getWindow().setFlags(WindowManager. LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams. FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        binding = ActivityProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyApplication app = (MyApplication) getApplication();
        auth = app.getAuth();
        database = app.getDatabase();
        System.out.println(auth.getCurrentUser());
        ServiceProvider provider = database.getServiceProviderById(auth.getCurrentUser().getUid());
        provider.setLocation(getUserLocation());
        database.pushServiceProvider(provider, auth.getCurrentUser().getUid());


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

    public LatLng getUserLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = null;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            task = fusedLocationClient.getLastLocation();
        }

        final LatLng[] loca = {null};
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    loca[0] = latLng;
                }
            }
        });
        return loca[0];

    }

}