package com.example.buscatelas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.buscatelas.Utils.Database;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link maps_percurso_client#newInstance} factory method to
 * create an instance of this fragment.
 */
public class maps_percurso_client extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Database databs;
    private Button closeButton;
    private AlertDialog.Builder builder;


    public maps_percurso_client() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment maps_percurso.
     */
    // TODO: Rename and change types and number of parameters
    public static maps_percurso_client newInstance(String param1, String param2) {
        maps_percurso_client fragment = new maps_percurso_client();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        View view = inflater.inflate(R.layout.fragment_maps_percurso_client, container, false);

        if(container!= null){
            container.removeAllViews();
        }

        databs = new Database();

        closeButton = view.findViewById(R.id.button3);
        closeButton.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v){
                builder = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                builder.setView(edittext);

                builder.setMessage("Detalhes Provider")
                        .setCancelable(false)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("voltar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Perform some action on click of NO button
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        return view;
    }
}