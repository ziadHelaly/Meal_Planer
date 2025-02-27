package eg.edu.iti.mealplaner.Home.presenter;

import android.annotation.SuppressLint;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {
    Repository myRepo;
    HomePresenter.View view;

    public HomePresenterImpl(Repository myRepo, View view) {
        this.myRepo = myRepo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void addMealToFav(Meal meal) {
        myRepo.addMealToFav(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.showMsg("Added to Fav");
                });
    }

    @Override
    public void getData() {
        view.showLoadingScreen();
        getMealOfToday();
        getCategories();
        getEgyptianSection();
        getBeefSection();
        getVeganSection();
    }

    @SuppressLint("CheckResult")
    public void getCategories() {
        myRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> view.displayCategories(item.getCategories()),
                        error -> {
                            //TODO
                        });
    }

    @SuppressLint("CheckResult")
    public void getEgyptianSection() {
        myRepo.getFilteredDataByArea("Egyptian")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.displayEgyptSection(item.getMeals());
                }, error -> {
                    //TODO
                });
    }

    @SuppressLint("CheckResult")
    public void getBeefSection() {
        myRepo.getFilteredDataByCategory("Beef")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.displayBeefSection(item.getMeals());
                }, error -> {
                    //TODO
                });
    }

    @SuppressLint("CheckResult")
    public void getVeganSection() {
        myRepo.getFilteredDataByCategory("Vegan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.displayVeganSection(item.getMeals());
                }, error -> {
                    //TODO
                });

    }


    @SuppressLint("CheckResult")
    public void getMealOfToday() {
        view.showLoadingScreen();
        myRepo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.displayMealOfToday(item.getMeals().get(0));
                    view.hideLoadingScreen();
                }, error -> {
                    //TODO
                });
    }

}
