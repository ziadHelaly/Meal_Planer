package eg.edu.iti.mealplaner.Home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import eg.edu.iti.mealplaner.Home.model.models.Category;
import eg.edu.iti.mealplaner.Home.model.models.Meal;
import eg.edu.iti.mealplaner.Home.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenter;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenterImpl;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FragmentHomeBinding;
import eg.edu.iti.mealplaner.Home.view.HomeFragmentDirections.ActionHomeFragmentToDeatilsFragment;


public class HomeFragment extends Fragment implements HomePresenter.View {
    HomePresenter presenter;
    FragmentHomeBinding binding;
    CategoryAdapter categoryAdapter;
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

    View view;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new HomePresenterImpl(RepositoryImpl.getRepository(),this);
        presenter.getMealOfToday();
        presenter.getCategories();
        presenter.getEgyptianSection();
        presenter.getBeefSection();
        presenter.getVeganSection();
        this.view=view;


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
        binding.btnMore.setOnClickListener(v->{
            ActionHomeFragmentToDeatilsFragment action;
            action = HomeFragmentDirections.actionHomeFragmentToDeatilsFragment(mealOfToday.getIdMeal());
            Navigation.findNavController(view).navigate(action);
        });
        Glide.with(getContext()).load(mealOfToday.getStrMealThumb())
                .into(binding.ivMealOfToday);
    }

    @Override
    public void displayCategories(List<Category> categories) {
        binding.rvCategories.setAdapter(new CategoryAdapter(categories,getContext()));
    }

    @Override
    public void displayEgyptSection(List<Meal> meals) {
        Log.d("``TAG``", "displayEgyptSection: ");
        binding.rvEgypt.setAdapter(new ItemsAdapter(meals,getContext()));
    }

    @Override
    public void displayBeefSection(List<Meal> meals) {
        binding.rvBeef.setAdapter(new ItemsAdapter(meals,getContext()));
    }

    @Override
    public void displayVeganSection(List<Meal> meals) {
        binding.rvVegan.setAdapter(new ItemsAdapter(meals,getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}