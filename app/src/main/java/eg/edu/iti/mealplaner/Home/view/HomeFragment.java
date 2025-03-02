package eg.edu.iti.mealplaner.Home.view;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenter;
import eg.edu.iti.mealplaner.Home.presenter.HomePresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentHomeBinding;
import eg.edu.iti.mealplaner.Home.view.HomeFragmentDirections.ActionHomeFragmentToDeatilsFragment;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.utilies.NetworkStatusManager;
import eg.edu.iti.mealplaner.view.ItemsAdapter;
import eg.edu.iti.mealplaner.view.OnItemClicked;


public class HomeFragment extends Fragment implements HomePresenter.View, OnItemClicked {
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    View view;
    boolean network;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NetworkStatusManager networkStatusManager = NetworkStatusManager.getInstance();
        networkStatusManager.getNetworkStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isConnected) {
                if (isConnected) {
                    network=true;
                    onConnection();
                } else {
                    // Handle network unavailable
                    network=false;
                    onNoConnection();

                }
            }
        });
        presenter = new HomePresenterImpl(RepositoryImpl.getRepository(getContext()), this);
//        presenter.getData();
        this.view = view;
        binding.tvSeeMoreEgypt.setOnClickListener(v -> {
            navToFilteredScreen(FilterType.Area, "Egyptian");
        });
        binding.tvSeeMoreBeef.setOnClickListener(v -> {
            navToFilteredScreen(FilterType.Category, "Beef");
        });
        binding.tvSeeMoreVegan.setOnClickListener(v -> {
            navToFilteredScreen(FilterType.Category, "Vegan");
        });
    }

    public void onNoConnection() {
        binding.noNetworkLayer.setVisibility(VISIBLE);
        binding.cardView.setVisibility(INVISIBLE);
    }

    boolean isLoading = false;

    public void onConnection() {
        binding.noNetworkLayer.setVisibility(GONE);
        if (!isLoading) {
            binding.cardView.setVisibility(VISIBLE);

        }
        presenter.getData();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!network){
            binding.cardView.setVisibility(GONE);
        }else {
            presenter.getData();
        }
    }

    @Override
    public void showLoadingScreen() {
        isLoading = true;
        binding.loadingScreen.setVisibility(VISIBLE);
        binding.cardView.setVisibility(INVISIBLE);
    }

    @Override
    public void hideLoadingScreen() {
        isLoading = false;
        binding.loadingScreen.setVisibility(GONE);
        binding.cardView.setVisibility(VISIBLE);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void displayMealOfToday(Meal mealOfToday) {
        binding.tvMealName.setText(mealOfToday.getStrMeal());
        binding.tvCategory.setText(mealOfToday.getStrCategory());
        binding.tvCountry.setText(mealOfToday.getStrArea());
        binding.tvDes.setText(mealOfToday.getStrInstructions());
        binding.btnMore.setOnClickListener(v -> {
            ActionHomeFragmentToDeatilsFragment action;
            action = HomeFragmentDirections.actionHomeFragmentToDeatilsFragment(mealOfToday.getIdMeal());
            Navigation.findNavController(view).navigate(action);
        });
        binding.btnAdd.setOnClickListener(v -> {
            presenter.addMealToFav(mealOfToday);
            binding.btnAdd.setCompoundDrawableTintList(ColorStateList.valueOf(R.color.primary));
        });
        Glide.with(getContext()).load(mealOfToday.getStrMealThumb())
                .into(binding.ivMealOfToday);
    }

    @Override
    public void displayCategories(List<Category> categories) {
        binding.rvCategories.setAdapter(new CategoryAdapter(categories, getContext(), this));
    }

    @Override
    public void displayEgyptSection(List<Meal> meals) {
        Log.d("``TAG``", "displayEgyptSection: ");
        binding.rvEgypt.setAdapter(new ItemsAdapter(meals, getContext(), this));
    }

    @Override
    public void displayBeefSection(List<Meal> meals) {
        binding.rvBeef.setAdapter(new ItemsAdapter(meals, getContext(), this));
    }

    @Override
    public void displayVeganSection(List<Meal> meals) {
        binding.rvVegan.setAdapter(new ItemsAdapter(meals, getContext(), this));
    }

    @Override
    public void showMsg(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void navToDetailsScreen(String id) {
        HomeFragmentDirections.ActionHomeFragmentToDeatilsFragment action;
        action = HomeFragmentDirections.actionHomeFragmentToDeatilsFragment(id);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void navToFilteredScreen(FilterType type, String name) {
        HomeFragmentDirections.ActionHomeFragmentToFilteredFragment action;
        action = HomeFragmentDirections.actionHomeFragmentToFilteredFragment(type, name);
        Navigation.findNavController(view).navigate(action);
    }
}