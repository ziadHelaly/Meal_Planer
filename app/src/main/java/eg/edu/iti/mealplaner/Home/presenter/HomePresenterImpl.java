package eg.edu.iti.mealplaner.Home.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.Home.model.models.Meal;
import eg.edu.iti.mealplaner.Home.model.network.NetworkCallBack;
import eg.edu.iti.mealplaner.Home.model.repository.Repository;

public class HomePresenterImpl implements HomePresenter, NetworkCallBack {
    Repository myRepo;
    HomePresenter.View view;

    public HomePresenterImpl(Repository myRepo, View view) {
        this.myRepo = myRepo;
        this.view = view;
    }

    @Override
    public void getMealOfToday() {
        view.showLoadingScreen();
        myRepo.getRandomMeal(this);
    }
//TODO افتكر تظبط بقيت الانترفييس 
    @Override
    public void onSuccessResult(List<Meal> meals) {
        view.hideLoadingScreen();
        view.displayMealOfToday(meals.get(0));
    }

    @Override
    public void onFailure(String message) {

    }
}
