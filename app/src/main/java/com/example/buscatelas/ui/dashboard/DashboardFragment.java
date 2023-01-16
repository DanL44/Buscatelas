package com.example.buscatelas.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.buscatelas.R;
import com.example.buscatelas.databinding.FragmentDashboardBinding;
import com.example.buscatelas.ui.Client.Criar_Pedido_client;
import com.example.buscatelas.ui.Client.Lista_Completos_Servicos_client;
import com.example.buscatelas.ui.settings.ChangeEmailFragment;
import com.example.buscatelas.ui.worker.Lista_Completos_Servicos_worker;
import com.example.buscatelas.ui.worker.Lista_pedidosPendentes_worker;
import com.example.buscatelas.ui.worker.Servico_worker;
import com.example.buscatelas.ui.worker.lista_servicos_worker;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(container!= null){
            container.removeAllViews();
        }

        Button listaPedidos = root.findViewById(R.id.ListaPedidos);

        listaPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new Lista_pedidosPendentes_worker());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button ListaServico = root.findViewById(R.id.ListaServico);

        ListaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new lista_servicos_worker());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button historico = root.findViewById(R.id.historico);

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new Lista_Completos_Servicos_worker());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button criarPedido = root.findViewById(R.id.criarPedido);

        criarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new Criar_Pedido_client());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button historicoclient = root.findViewById(R.id.historicoclient);

        historicoclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new Lista_Completos_Servicos_client());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}