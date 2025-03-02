package eg.edu.iti.mealplaner.Auth.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import eg.edu.iti.mealplaner.databinding.FragmentSignInBinding;
import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSource;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSourceImpl;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSourceImpl;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.utilies.Const;
import eg.edu.iti.mealplaner.view.HomeActivity;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.Auth.presenter.AuthPresenterImpl;
import eg.edu.iti.mealplaner.Auth.presenter.AuthPresenter;

public class SignInFragment extends Fragment implements AuthPresenter.view {
    AuthPresenter presenter;
    EditText etEmail, etPassword;
    Button btnGoogle;
    ConstraintLayout btnSingIn;
    TextView tvSingUp, tvSignIn;
    ProgressBar progressBar;
    FragmentSignInBinding binding;
    private GoogleSignInClient googleSignInClient;


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
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupPresenter();
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnSingIn = view.findViewById(R.id.btnSingUp);
        tvSignIn = view.findViewById(R.id.tvSingUpBtn);
        progressBar = view.findViewById(R.id.pbLoad);
        btnGoogle = view.findViewById(R.id.btGoogle);
        tvSingUp = view.findViewById(R.id.tvSingIn);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    btnSingIn.setBackgroundResource(R.drawable.btn_shape);
                    btnSingIn.setClickable(false);
                } else {
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
                } else {
                    btnSingIn.setBackgroundResource(R.drawable.btn_active);
                    btnSingIn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSingIn.setOnClickListener(v -> {
            if (!etEmail.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                presenter.signIn(etEmail.getText().toString().trim(), etPassword.getText().toString());
            }

        });
        tvSingUp.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_singUpFragment);
        });
        binding.btnGuest.setOnClickListener(v -> {
            presenter.guestMode();

        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        binding.btGoogle.setOnClickListener(v->{
            signInWithGoogle();
        });
    }
    private void setupPresenter(){
        MealLocalDataSource local= MealLocalDataSourceImpl.getMealLocalDataSource(getContext());
        MealsRemoteDataSource remote= MealsRemoteDataSourceImpl.getInstance(getContext());
        SharedPreference sharedPreference=SharedPreference.getInstance(getContext());
        FireBaseAuthModel fireBaseAuthModel= new FireBaseAuthModel(FirebaseAuth.getInstance());
        presenter = new AuthPresenterImpl(this, RepositoryImpl.getRepository(remote,local,sharedPreference,fireBaseAuthModel));
    }

    public void setAllButtonsClickable(boolean clickable) {
        btnGoogle.setClickable(clickable);
        btnSingIn.setClickable(clickable);
        tvSingUp.setClickable(clickable);
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Const.RC_GOOGLE_SIGN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.RC_GOOGLE_SIGN) {
            presenter.signInWithGoogle(data);
        }
    }
    @Override
    public void onSuccess() {
        Snackbar.make(binding.getRoot(), "Welcome", Snackbar.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onFailure() {
        Snackbar.make(binding.getRoot(), "Failed", Snackbar.LENGTH_SHORT).show();
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