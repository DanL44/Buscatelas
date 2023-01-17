package com.example.buscatelas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buscatelas.Utils.Authentication;
import com.example.buscatelas.Utils.Database;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private Authentication mAuth;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = new Authentication(this);
        Database database = new Database();

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );


        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

        askNotificationPermission();
        Button loginButton = findViewById(R.id.loginButton);
        EditText emailText = findViewById(R.id.emailAdress);
        String email = emailText.getText().toString();
        EditText passwordT = findViewById(R.id.password);
        String password = passwordT.getText().toString().trim();



        loginButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                mAuth.loginWithEmailAndPassword("ola3@gmail.com", "olaola");
                MyApplication app = (MyApplication) getApplication();
                app.setAuth(mAuth);
                app.setDatabase(database);

                Button loginButton = findViewById(R.id.loginButton);
                EditText emailText = findViewById(R.id.emailAdress);

                String email = emailText.getText().toString().trim();
                EditText passwordT = findViewById(R.id.password);

                String password = passwordT.getText().toString().trim();
                if (isFieldEmpty(email) || isFieldEmpty(password)) {
                    Toast.makeText(Login.this, "Fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.loginWithEmailAndPassword("ola3@gmail.com", "olaola");
                    while(mAuth.getCurrentUser() == null);
                    startActivity(new Intent(Login.this, ProviderActivity.class));

                }

            }
        });

        Button client = findViewById(R.id.button);
        client.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mAuth.loginWithEmailAndPassword("ola3@gmail.com", "olaola");
                startActivity(new Intent(Login.this, ClientActivity.class));
                Button loginButton = findViewById(R.id.loginButton);
                EditText emailText = findViewById(R.id.emailAdress);

                String email = emailText.getText().toString().trim();
                EditText passwordT = findViewById(R.id.password);

                String password = passwordT.getText().toString().trim();
                if (isFieldEmpty(email) || isFieldEmpty(password)) {
                    Toast.makeText(Login.this, "Fields are empty",
                            Toast.LENGTH_SHORT).show();
                }
                else{

                    mAuth.loginWithEmailAndPassword("ola3@gmail.com", "olaola");
                    startActivity(new Intent(Login.this, ClientActivity.class));
                }

            }
        });












        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }


    private boolean isFieldEmpty(String text){
        return text.length() == 0;

    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }



}