package eg.edu.iti.mealplaner.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.presenter.classes.Auth;
import eg.edu.iti.mealplaner.presenter.interfaces.AuthPresenter;

public class SignInFragment extends Fragment implements AuthPresenter.view {
    AuthPresenter presenter;
    EditText etEmail,etPassword;
    Button btnGoogle;
    ConstraintLayout btnSingIn;
    TextView tvSingUp,tvSignIn;
    ProgressBar progressBar;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= new Auth(this);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etPassword);
        btnSingIn=view.findViewById(R.id.btnSingIn);
        tvSignIn=view.findViewById(R.id.tvSingInBtn);
        progressBar=view.findViewById(R.id.pbLoad);
        btnGoogle=view.findViewById(R.id.btGoogle);
        tvSingUp=view.findViewById(R.id.tvSingUp);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    btnSingIn.setBackgroundResource(R.drawable.btn_shape);
                    btnSingIn.setClickable(false);
                }else{
                    btnSingIn.setBackgroundResource(R.drawable.btn_active);
                    btnSingIn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    btnSingIn.setBackgroundResource(R.drawable.btn_shape);
                    btnSingIn.setClickable(false);
                }else{
                    btnSingIn.setBackgroundResource(R.drawable.btn_active);
                    btnSingIn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSingIn.setOnClickListener(v->{
            Log.d("``TAG``", "onViewCreated: click");
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
                presenter.signIn(etEmail.getText().toString().trim(),etPassword.getText().toString());
            }

        });
    }
    public void setAllButtonsClickable(boolean clickable){
        btnGoogle.setClickable(clickable);
        btnSingIn.setClickable(clickable);
    }
    @Override
    public void onSuccess() {
        Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        setAllButtonsClickable(false);
        progressBar.setVisibility(VISIBLE);
        tvSignIn.setVisibility(INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setAllButtonsClickable(true);
        btnSingIn.setBackgroundResource(R.drawable.btn_active);
        progressBar.setVisibility(INVISIBLE);
        tvSignIn.setVisibility(VISIBLE);
    }
}