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

        //tirar da base de dados os dados
        TextView myTextView = (TextView) root.findViewById(R.id.workerName);
        myTextView.setText("client name");
        TextView myTextView2 = (TextView) root.findViewById(R.id.workerEmail);
        myTextView.setText("client email");
        TextView myTextView3 = (TextView) root.findViewById(R.id.textView24);
        myTextView.setText("client phone");



        Button about = root.findViewById(R.id.button10);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_client, new about());
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