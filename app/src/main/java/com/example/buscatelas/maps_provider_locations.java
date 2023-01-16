package com.example.buscatelas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.buscatelas.Utils.Authentication;
import com.example.buscatelas.Utils.Database;
import com.example.buscatelas.models.Client;
import com.example.buscatelas.models.Request;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link maps_provider_locations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class maps_provider_locations extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button closeButton;
    private AlertDialog.Builder builder;
    private Request request;
    private Database databs;
    private String espc;


    public maps_provider_locations() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment maps_provider_locations.
     */
    // TODO: Rename and change types and number of parameters
    public static maps_provider_locations newInstance(String param1, String param2) {
        maps_provider_locations fragment = new maps_provider_locations();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public maps_provider_locations (String i) {
        this.espc = i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps_provider_locations, container, false);

        if(container!= null){
            container.removeAllViews();
        }

        databs = new Database();

        Button passwordBtn = view.findViewById(R.id.button4);

        passwordBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new maps_percurso_client());
                fragmentTransaction.commit();
            }
        });


        closeButton = view.findViewById(R.id.buttonpopup);
        closeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                builder = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                builder.setView(edittext);

                builder.setMessage("Descriçao")
                        .setCancelable(false)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String descript = edittext.getText().toString();
                                Authentication firebaseAuth = new Authentication(getActivity());
                                FirebaseUser client = firebaseAuth.getCurrentUser();
                                Client cli = databs.getClientById(client.getUid());
                                request = new Request(cli,descript);
                                databs.pushRequest(request, cli.getId());

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        return view;
    }
}