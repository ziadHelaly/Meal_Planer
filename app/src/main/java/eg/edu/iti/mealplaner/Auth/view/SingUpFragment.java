package eg.edu.iti.mealplaner.Auth.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
import eg.edu.iti.mealplaner.Auth.presenter.classes.Auth;
import eg.edu.iti.mealplaner.Auth.presenter.interfaces.AuthPresenter;


public class SingUpFragment extends Fragment implements AuthPresenter.view {
    AuthPresenter presenter;
    EditText etEmail,etPassword,etConfirm;

    ConstraintLayout btnSingUp;
    TextView tvSignUp,tvSignIn;
    ProgressBar progressBar;

    public SingUpFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter= new Auth(this);
        etEmail=view.findViewById(R.id.etEmail);
        etPassword=view.findViewById(R.id.etPassword);
        btnSingUp=view.findViewById(R.id.btnSingUp);
        tvSignUp=view.findViewById(R.id.tvSingUpBtn);
        progressBar=view.findViewById(R.id.pbLoad);
        tvSignIn=view.findViewById(R.id.tvSingIn);
        etConfirm=view.findViewById(R.id.etConfirmPassword);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEdText();
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
                checkEdText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEdText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSingUp.setOnClickListener(v->{
            Log.d("``TAG``", "onViewCreated: click");
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
                if(etConfirm.getText().toString().equals(etPassword.getText().toString())){
                    presenter.signUp(etEmail.getText().toString().trim(),etPassword.getText().toString());
                }else {
                    Log.d("```TAG```", "password don't match");
                }
            }
        });
        tvSignIn.setOnClickListener(v->{
            Navigation.findNavController(view).popBackStack();
        });
    }
    private void checkEdText(){
        if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etConfirm.getText().toString().isEmpty()) {
            if(etConfirm.getText().toString().equals(etPassword.getText().toString())){
                btnSingUp.setBackgroundResource(R.drawable.btn_shape);
                btnSingUp.setClickable(false);
            }
        }else {
            btnSingUp.setBackgroundResource(R.drawable.btn_active);
            btnSingUp.setClickable(true);
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
    }
    public void setAllButtonsClickable(boolean clickable){
        btnSingUp.setClickable(clickable);
        tvSignIn.setClickable(clickable);
    }
    @Override
    public void showProgressBar() {
        setAllButtonsClickable(false);
        progressBar.setVisibility(VISIBLE);
        tvSignUp.setVisibility(INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setAllButtonsClickable(true);
        btnSingUp.setBackgroundResource(R.drawable.btn_active);
        progressBar.setVisibility(INVISIBLE);
        tvSignUp.setVisibility(VISIBLE);
    }
}