package eg.edu.iti.mealplaner.Search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;

import java.util.List;

import eg.edu.iti.mealplaner.Home.view.CategoryAdapter;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.Search.presenter.SearchPresenter;
import eg.edu.iti.mealplaner.Search.presenter.SearchPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentSearchBinding;
import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.view.OnItemClicked;


public class SearchFragment extends Fragment implements SearchPresenter.View , OnItemClicked {

    FragmentSearchBinding binding;
    SearchPresenter presenter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    CategoryAdapter categoryAdapter;
    CountriesAdapter countriesAdapter;
    IngredientsFilterAdapter ingredientsAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryAdapter=new CategoryAdapter(getContext(),this);
        countriesAdapter=new CountriesAdapter(getContext(),this);
        ingredientsAdapter=new IngredientsFilterAdapter(getContext(),this);
        presenter=new SearchPresenterImpl(this, RepositoryImpl.getRepository(getContext()));
        presenter.getData();
        //each chip will change filter type
        for (int i = 0; i < binding.chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipGroup.getChildAt(i);
            if (chip.isChecked()) presenter.setType(chip.getText().toString());
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        presenter.setType(chip.getText().toString());
                    } else {
                        presenter.setType("Name");
                    }
                }
            });
        }
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < binding.chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipGroup.getChildAt(i);
            if (chip.isChecked()) presenter.setType(chip.getText().toString());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void showData(List<Meal> meals) {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showLoadingScreen() {

    }

    @Override
    public void hideLoadingScreen() {

    }

    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter.setCategories(categories);
        binding.rvItems.setAdapter(categoryAdapter);
    }
    @Override
    public void showCountries(List<Country> countries) {
        countriesAdapter.setCountries(countries);
        binding.rvItems.setAdapter(countriesAdapter);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        ingredientsAdapter.setIngredients(ingredients);
        binding.rvItems.setAdapter(ingredientsAdapter);
    }

    @Override
    public void navToFilteredScreen(FilterType type, String name) {
        eg.edu.iti.mealplaner.Search.view.SearchFragmentDirections.ActionSearchFragmentToFilteredFragment action;
        action= SearchFragmentDirections.actionSearchFragmentToFilteredFragment(type,name);
        Navigation.findNavController(getView()).navigate(action);
    }
}