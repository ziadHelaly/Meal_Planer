package eg.edu.iti.mealplaner.Home.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.remote.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public class HomePresenterImpl implements HomePresenter, HomeNetworkCallBack {
    Repository myRepo;
    HomePresenter.View view;

    public HomePresenterImpl(Repository myRepo, View view) {
        this.myRepo = myRepo;
        this.view = view;
    }

    @Override
    public void getCategories() {
        myRepo.getCategories(this);
    }

    @Override
    public void getEgyptianSection() {
        myRepo.getFilteredDataByArea("Egyptian", this,NetworkCalls.EgyptianCategory);
    }

    @Override
    public void getBeefSection() {
        myRepo.getFilteredDataByCategory("Beef",this,NetworkCalls.BeefCategory);
    }

    @Override
    public void getVeganSection() {
        myRepo.getFilteredDataByCategory("Vegan",this,NetworkCalls.VeganCategory);

    }

    @Override
    public void getMealOfToday() {
        view.showLoadingScreen();
        myRepo.getRandomMeal(this);
    }

    @Override
    public void onSuccessResultMeals(List<Meal> meals, NetworkCalls type) {
        view.hideLoadingScreen();
        if (type == NetworkCalls.MealOfToday) {
            view.displayMealOfToday(meals.get(0));
        } else if (type == NetworkCalls.EgyptianCategory) {
            view.displayEgyptSection(meals);
        } else if (type == NetworkCalls.BeefCategory) {
            view.displayBeefSection(meals);
        } else if (type == NetworkCalls.VeganCategory) {
            view.displayVeganSection(meals);
        }
    }

    @Override
    public void onSuccessResultCategories(List<Category> categories) {
        view.displayCategories(categories);
    }

    @Override
    public void onFailure(String message) {

    }
}
