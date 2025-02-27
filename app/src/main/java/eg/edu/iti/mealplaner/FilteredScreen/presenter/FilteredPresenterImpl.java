package eg.edu.iti.mealplaner.FilteredScreen.presenter;

import android.annotation.SuppressLint;

import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilteredPresenterImpl implements FilteredPresenter {
    PresenterInterface.View view;
    Repository repo;

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

    @SuppressLint("CheckResult")
    private void getByCategory(String c) {
        repo.getFilteredDataByCategory(c)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.hideLoadingScreen();
                    view.showData(item.getMeals());
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
                });
    }
}
