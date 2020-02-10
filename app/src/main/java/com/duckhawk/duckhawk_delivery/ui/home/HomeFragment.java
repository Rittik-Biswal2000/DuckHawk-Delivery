package com.duckhawk.duckhawk_delivery.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.duckhawk.duckhawk_delivery.LoginActivity;
import com.duckhawk.duckhawk_delivery.MainActivity;
import com.duckhawk.duckhawk_delivery.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {

    FirebaseUser currentUser;
    int time = 500;
    private FirebaseAuth mAuth;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView txtView = ((MainActivity)getContext()).findViewById(R.id.dispEmail);
                txtView.setText(currentUser.getEmail());
                TextView dispname = ((MainActivity)getContext()).findViewById(R.id.dispName);
                dispname.setText(currentUser.getDisplayName());
            }
        },time);

        Button logout = root.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),LoginActivity.class));
                getActivity().finish();
            }
        });

        Button b = root.findViewById(R.id.home_button);
        b.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                currentUser = mAuth.getCurrentUser();
                Log.w("InHome ",currentUser.getEmail());
                Log.w("InHome ",currentUser.getUid());
                Log.w("InHome ",currentUser.getProviderId());
                Log.w("InHome ",currentUser.getDisplayName());
                Log.w("InHome ",currentUser.getPhoneNumber());
                Toast.makeText(HomeFragment.super.getContext(), "Hello There", Toast.LENGTH_SHORT).show();
            }
        });



        return root;




    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
//        Log.w("In Home ",currentUser.getEmail());

    }
}