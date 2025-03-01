package eg.edu.iti.mealplaner.Favourites.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import eg.edu.iti.mealplaner.Favourites.presenter.FavPresenter;
import eg.edu.iti.mealplaner.Favourites.presenter.FavPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentFavBinding;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;


public class FavFragment extends Fragment implements FavPresenter.View,OnFavRemoveItemClick {

    FragmentFavBinding binding;
    FavPresenter presenter;

    public FavFragment() {
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
        binding = FragmentFavBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavPresenterImpl(this, RepositoryImpl.getRepository(getContext()));
        presenter.getData();
    }

    @Override
    public void showData(List<Meal> meals) {
        if (meals.isEmpty()) {
            binding.tvNoItems.setVisibility(VISIBLE);
        }else {
            binding.tvNoItems.setVisibility(GONE);
        }
        binding.rvFavs.setAdapter(new FavAdapter(meals, getContext(), this));

    }

    @Override
    public void showErrorMsg(String msg) {
        Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showLoadingScreen() {

    }

    @Override
    public void hideLoadingScreen() {

    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT)
                .setAction("UNDO", view -> {
                    presenter.redoMeal();
                })
                .show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void removeItem(Meal meal) {
        presenter.removeFav(meal);
    }
}