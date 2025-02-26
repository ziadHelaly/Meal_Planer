package eg.edu.iti.mealplaner.Favourites.presenter;

import android.annotation.SuppressLint;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenterImpl implements FavPresenter {
    FavPresenter.View view;
    Repository repo;
    Meal holdingMeal;

    public FavPresenterImpl(View view, Repository repo) {
        this.view = view;
        this.repo = repo;
    }

    @SuppressLint("CheckResult")
    @Override
    public void removeFav(Meal meal) {
        holdingMeal=meal;
        repo.removeMealFromFav(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    view.showSnackBar("Removed from Favourites");
                });
    }

    @Override
    public void redoMeal() {
        repo.addMealToFav(holdingMeal).subscribeOn(Schedulers.io()).subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        repo.getFavsMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.showData(meals);
                        },
                        error -> {
                            view.showErrorMsg(error.getMessage());
                        });
    }
}
