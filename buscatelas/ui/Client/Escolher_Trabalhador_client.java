package com.example.buscatelas.ui.Client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.buscatelas.R;
import com.example.buscatelas.ui.dashboard.DashboardFragment;
import com.example.buscatelas.ui.worker.Aceitar_Pedido_worker;
import com.example.buscatelas.ui.worker.lista_servicos_worker;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Escolher_Trabalhador_client#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Escolher_Trabalhador_client extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    public Escolher_Trabalhador_client() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Escolher_Trabalhador_client.
     */
    // TODO: Rename and change types and number of parameters
    public static Escolher_Trabalhador_client newInstance(String param1, String param2) {
        Escolher_Trabalhador_client fragment = new Escolher_Trabalhador_client();
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
        View view = inflater.inflate(R.layout.fragment_escolher__trabalhador_client, container, false);

        if(container!= null){
            container.removeAllViews();
        }

        lv = (ListView) view.findViewById(R.id.listaTrabalhadores);

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,al);
        lv.setAdapter(aa);
        al.add("Rui Mario - Eletrecista- 25km");
        al.add("Rui Mario - Eletrecista- 25km");


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_client, new DashboardFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;

    }
}