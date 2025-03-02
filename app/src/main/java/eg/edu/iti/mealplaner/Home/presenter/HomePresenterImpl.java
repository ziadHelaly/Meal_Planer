package eg.edu.iti.mealplaner.Home.presenter;

import android.annotation.SuppressLint;

import eg.edu.iti.mealplaner.Home.view.HomeFragment;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {
    Repository myRepo;
    HomePresenter.View view;


    public HomePresenterImpl(View view, Repository repository) {
        this.myRepo = repository;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void addMealToFav(Meal meal) {
        if (Const.isLogged){
            myRepo.addMealToFav(meal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        view.showMsg("Added to Fav");
                    },throwable -> view.showMsg(throwable.getMessage()));
        }else {
            view.showMsg("SignIn to use favourites");
        }
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
