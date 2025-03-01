package eg.edu.iti.mealplaner.FilteredScreen.presenter;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilteredPresenterImpl implements FilteredPresenter {
    PresenterInterface.View view;
    Repository repo;
    List<Meal> meals=new ArrayList<>();
    Observable<Meal> observable=Observable.fromIterable(meals);

    public FilteredPresenterImpl(View view, Repository repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getData(FilterType filterType, String c) {
        view.showLoadingScreen();
        switch (filterType) {
            case Area: {
                getByArea(c);
                break;
            }
            case Category: {
                getByCategory(c);
                break;
            }
            case Ingredient: {
                getByIngredient(c);
                break;
            }
        }
    }
    List<Meal> result=new ArrayList<>();
    @Override
    public void searchByName(String search){
        result=meals.stream()
                .filter(string->(string.getStrMeal().toLowerCase().contains(search)))
                .collect(Collectors.toList());
        view.showData(result);

    }
    @SuppressLint("CheckResult")
    private void getByCategory(String c) {
        repo.getFilteredDataByCategory(c)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.hideLoadingScreen();
                    view.showData(item.getMeals());
                    meals=item.getMeals();
                    observable=Observable.fromIterable(meals);
                });
    }

    @SuppressLint("CheckResult")
    private void getByArea(String a) {
        repo.getFilteredDataByArea(a)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.hideLoadingScreen();
                    view.showData(item.getMeals());
                    meals=item.getMeals();
                    observable=Observable.fromIterable(meals);
                });
    }

    @SuppressLint("CheckResult")
    private void getByIngredient(String i) {
        repo.getFilteredDataByIngradiants(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.hideLoadingScreen();
                    view.showData(item.getMeals());
                    meals=item.getMeals();
                    observable=Observable.fromIterable(meals);
                });
    }
}
