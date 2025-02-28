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

import eg.edu.iti.mealplaner.Auth.view.AuthActivity;
import eg.edu.iti.mealplaner.Profile.presenter.ProfilePresenter;
import eg.edu.iti.mealplaner.Profile.presenter.ProfilePresenterImpl;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FragmentPorfileBinding;
import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.utilies.Const;


public class PorfileFragment extends Fragment implements ProfilePresenter.View{

    FragmentPorfileBinding binding;
    ProfilePresenter presenter;

    public PorfileFragment() {
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
        binding=FragmentPorfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new ProfilePresenterImpl(new FireBaseAuthModel(), RepositoryImpl.getRepository(getContext()),this);
        binding.textView12.setText("User"+ Const.USER_ID);
        binding.btnFavourites.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_porfileFragment_to_favFragment);
        });
        binding.btnPlans.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_porfileFragment_to_calenderFragment);
        });
        binding.btnSignOut.setOnClickListener(v -> {
            presenter.signOut();
        });
    }

    @Override
    public void onSignOut() {
        Intent intent=new Intent(getActivity(), AuthActivity.class);
        getActivity().startActivity(intent);
    }
}