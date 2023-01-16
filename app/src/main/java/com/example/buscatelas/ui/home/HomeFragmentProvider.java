package com.example.buscatelas.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.buscatelas.R;
import com.example.buscatelas.Utils.Database;
import com.example.buscatelas.databinding.FragmentHomeBinding;
import com.example.buscatelas.models.Client;
import com.example.buscatelas.ui.worker.Aceitar_Pedido_worker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragmentProvider extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Database databs;

    private Client currentUser;
    private FirebaseUser currentFBUser;

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(container!= null){
            container.removeAllViews();
        }

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        databs = new Database();

        currentFBUser = mAuth.getCurrentUser();
        System.out.println("Firebase User ID:" + currentFBUser.getUid());
        getUser(root);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getUser(View root){
        DatabaseReference uidRef = mDatabase.child("users").child(currentFBUser.getUid());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = databs.getClientById(currentFBUser.getUid());
                System.out.println("Current User" + currentUser);
                setupUI(root);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

    private void saveUser(String hourlyRate, String description){
        mDatabase.child("users").child(currentFBUser.getUid()).setValue(currentUser);
    }

    private void setupUI(View root){

        lv = (ListView) root.findViewById(R.id.lv);

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,al);
        lv.setAdapter(aa);
        al.add("pedidos");
        al.add("pedidos");
        al.add("pedidos");


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_client, new Aceitar_Pedido_worker());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}