package eg.edu.iti.mealplaner.Home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import eg.edu.iti.mealplaner.Home.model.models.Meal;
import eg.edu.iti.mealplaner.Home.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenter;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenterImpl;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements HomePresenter.View {
    HomePresenter presenter;
    FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new HomePresenterImpl(RepositoryImpl.getRepository(),this);
        presenter.getMealOfToday();


    }

    @Override
    public void showLoadingScreen() {

    }

    @Override
    public void hideLoadingScreen() {

    }

    @Override
    public void displayMealOfToday(Meal mealOfToday) {
        binding.tvMealName.setText(mealOfToday.getStrMeal());
        binding.tvCategory.setText(mealOfToday.getStrCategory());
        binding.tvCountry.setText(mealOfToday.getStrArea());
        binding.tvDes.setText(mealOfToday.getStrInstructions());
        Glide.with(getContext()).load(mealOfToday.getStrMealThumb())
                .into(binding.ivMealOfToday);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}