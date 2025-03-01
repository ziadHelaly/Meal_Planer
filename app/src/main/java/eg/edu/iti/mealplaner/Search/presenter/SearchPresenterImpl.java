package eg.edu.iti.mealplaner.Search.presenter;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;
import eg.edu.iti.mealplaner.utilies.FilterType;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImpl implements SearchPresenter {

    SearchPresenter.View view;
    Repository repo;

    FilterType type = FilterType.Name;


    public SearchPresenterImpl(View view, Repository repo) {
        this.view = view;
        this.repo = repo;
    }

    List<Category> categories = new ArrayList<>();
    List<Country> countries = new ArrayList<>();
    List<Ingredient> ingredients = new ArrayList<>();
    List<Category> categoriesFilteredResult = new ArrayList<>();
    List<Country> countriesFilteredResult = new ArrayList<>();
    List<Ingredient> ingredientsFilteredResult = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        getCategoriesList();
        getIngredientsList();
        getCountriesList();
        view.showCategories(categories);

    }

    @SuppressLint("CheckResult")
    private void getCategoriesList() {
        repo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            categories = item.getCategories();
                        },error->{
                            view.showErrorMsg("Can't get data");
                        });
    }

    @SuppressLint("CheckResult")
    private void getCountriesList() {
        repo.getCountriesFilterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            countries = item.getMeals();
                        },error->{
                            view.showErrorMsg("Can't get data");
                        });
    }

    @SuppressLint("CheckResult")
    private void getIngredientsList() {
        repo.getIngredientsFilterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            ingredients = item.getMeals();
                        },error->{
                            view.showErrorMsg("Can't get data");
                        });
    }

    @Override
    public void getData(String search) {
        switch (type){
            case Category:{
                categoriesFilteredResult= categories.stream()
                        .filter(string->(string.getStrCategory().toLowerCase().contains(search)))
                        .collect(Collectors.toList());
                view.showCategories(categoriesFilteredResult);
                break;
            }
            case Ingredient:{
                ingredientsFilteredResult= ingredients.stream()
                        .filter(string->(string.getStrIngredient().toLowerCase().contains(search)))
                        .collect(Collectors.toList());
                view.showIngredients(ingredientsFilteredResult);
                break;
            }
            case Area:{
                countriesFilteredResult=countries.stream()
                        .filter(string->(string.getStrArea().toLowerCase().contains(search)))
                        .collect(Collectors.toList());
                view.showCountries(countriesFilteredResult);
                break;
            }
        }
    }




    @Override
    public void setType(String type) {
        switch (type) {
            case "Name": {
                this.type = FilterType.Name;
                break;
            }
            case "Category": {
                this.type = FilterType.Category;
                if (categoriesFilteredResult.isEmpty()){
                    view.showCategories(categories);
                }else{
                    view.showCategories(categoriesFilteredResult);
                }
                break;
            }
            case "Ingredients": {
                this.type = FilterType.Ingredient;
                if (ingredientsFilteredResult.isEmpty()){
                    view.showIngredients(ingredients);
                }else {
                    view.showIngredients(ingredientsFilteredResult);
                }
                break;
            }
            case "Country": {
                this.type = FilterType.Area;
                if (countriesFilteredResult.isEmpty()){
                    view.showCountries(countries);
                }else {
                    view.showCountries(countriesFilteredResult);
                }
                break;
            }
        }
    }
}
