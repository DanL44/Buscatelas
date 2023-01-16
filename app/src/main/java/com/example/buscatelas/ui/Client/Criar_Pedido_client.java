package com.example.buscatelas.ui.Client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buscatelas.R;
import com.example.buscatelas.ui.worker.lista_servicos_worker;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Criar_Pedido_client#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Criar_Pedido_client extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Criar_Pedido_client() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Criar_Pedido_client.
     */
    // TODO: Rename and change types and number of parameters
    public static Criar_Pedido_client newInstance(String param1, String param2) {
        Criar_Pedido_client fragment = new Criar_Pedido_client();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_criar__pedido_client, container, false);
        if(container!= null){
            container.removeAllViews();
        }


        Button continuarPedido = view.findViewById(R.id.continuarPedido);

        continuarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new Escolher_Trabalhador_client());
                fragmentTransaction.commit();
            }
        });
        return view ;
    }
}