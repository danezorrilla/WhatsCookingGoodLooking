package com.bb.whatscookinggoodlooking.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bb.whatscookinggoodlooking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.login_username)
    EditText loginUsername;

    @BindView(R.id.login_password)
    EditText loginPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.login_user)
    public void loginUser(){
        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();
    }

    @OnClick(R.id.register_user)
    public void registerUser(){
        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();
    }

}
