package com.example.supernote.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.supernote.R;
import com.example.supernote.databinding.FragmentSignUpBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;



public class SignUpFragment extends Fragment implements View.OnClickListener {
    FragmentSignUpBinding binding;
    private FirebaseAuth mAuth;
    NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_sign_up);
        navController = Navigation.findNavController(view);
        binding.btnSignup.setOnClickListener(this);

        iniView();
    }
    private void iniView()
    {
        mAuth = FirebaseAuth.getInstance();

    }
    private void register()
    {

                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                if (name.isEmpty() && email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getContext(), "Enter Your Data", Toast.LENGTH_SHORT).show();
                }

                else if(password.length() <= 6)
                {
                    Toast.makeText(getContext(), "The password must be 6 characters long", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    mAuth.createUserWithEmailAndPassword(email ,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            Log.e("tlog", String.valueOf(task.getResult()));
                            Log.e("tlog", String.valueOf(task.getException()));
                            if(task.isSuccessful())
                            {

                                navController.navigate(R.id.action_signUpFragment_to_loginFragment);
                                Toast.makeText(getContext(), "You Can Login Now", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Error Register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_signup:
                register();

                break;
        }
    }
}