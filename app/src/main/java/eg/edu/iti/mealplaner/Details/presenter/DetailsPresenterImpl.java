package eg.edu.iti.mealplaner.Details.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.remote.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public class DetailsPresenterImpl implements DetailsPresenter, HomeNetworkCallBack {
    Repository repo;
    DetailsPresenter.View view;

    public DetailsPresenterImpl(Repository repo, View view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getData(String id) {
        repo.getMealById(id,this,NetworkCalls.MealById);
    }

    @Override
    public void addMealToFav(Meal meal) {
        repo.addMealToFav(meal);
    }

    @Override
    public void onSuccessResultMeals(List<Meal> meals, NetworkCalls type) {
        if (type==NetworkCalls.MealById){
            view.showData(meals.get(0));
        }
    }

    @Override
    public void onSuccessResultCategories(List<Category> categories) {

    }

    @Override
    public void onFailure(String message) {

    }
}
