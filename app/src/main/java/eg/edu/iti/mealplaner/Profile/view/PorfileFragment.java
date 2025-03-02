package eg.edu.iti.mealplaner.Profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import eg.edu.iti.mealplaner.Auth.view.AuthActivity;
import eg.edu.iti.mealplaner.Profile.presenter.ProfilePresenter;
import eg.edu.iti.mealplaner.Profile.presenter.ProfilePresenterImpl;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FragmentPorfileBinding;
import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSource;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSourceImpl;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSourceImpl;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.utilies.Const;


public class PorfileFragment extends Fragment implements ProfilePresenter.View {

    FragmentPorfileBinding binding;
    ProfilePresenter presenter;

    public PorfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        binding = FragmentPorfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupPresenter();
        binding.textView12.setText((Const.USER_NAME == null || Const.USER_NAME.equals("Null")) ? "Guest" : Const.USER_NAME);
        binding.btnSignOut.setText((Const.isLogged) ? "SignOut" : "SignIn");
        binding.btnFavourites.setOnClickListener(v -> {
            if (Const.isLogged) {
                Navigation.findNavController(view).navigate(R.id.action_porfileFragment_to_favFragment);
            } else {
                Snackbar.make(view, "Sign in to access this feature", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.btnPlans.setOnClickListener(v -> {
            if (Const.isLogged) {
                Navigation.findNavController(container).navigate(R.id.action_porfileFragment_to_calenderFragment);
            } else {
                Snackbar.make(view, "Sign in to access this feature", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.btnSignOut.setOnClickListener(v -> {
            if (Const.isLogged) {
                presenter.signOut();
            } else {
                onSignOut();
            }
        });
    }

    private void setupPresenter() {
        MealLocalDataSource local = MealLocalDataSourceImpl.getMealLocalDataSource(getContext());
        MealsRemoteDataSource remote = MealsRemoteDataSourceImpl.getInstance(getContext());
        SharedPreference sharedPreference = SharedPreference.getInstance(getContext());
        FireBaseAuthModel fireBaseAuthModel = new FireBaseAuthModel(FirebaseAuth.getInstance());
        presenter = new ProfilePresenterImpl(RepositoryImpl.getRepository(remote, local, sharedPreference, fireBaseAuthModel), this);
    }

    @Override
    public void onSignOut() {
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}