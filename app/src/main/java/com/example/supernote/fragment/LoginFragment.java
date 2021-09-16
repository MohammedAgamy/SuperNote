package com.example.supernote.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.FragmentLoginBinding;
import com.example.supernote.ui.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;


public class LoginFragment extends Fragment implements View.OnClickListener {
    FragmentLoginBinding binding;
    NavController navController;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_login);
        iniView(view);

    }


    private void iniView(View view) {
        navController = Navigation.findNavController(view);
        binding.loginS.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void logIn() {

        String email = binding.emailL.getText().toString();
        String password = binding.passwordL.getText().toString();
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(getContext(), "Enter Your Data", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                        Toast.makeText(getContext(), "Welcome In Your App", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_s:
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
                break;
            case R.id.btn_login:
                logIn();
                break;
        }
    }
}