package eg.edu.iti.mealplaner.Home.model.network;

import java.util.List;

import eg.edu.iti.mealplaner.Home.model.models.Category;
import eg.edu.iti.mealplaner.Home.model.models.Meal;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public interface HomeNetworkCallBack {
    void onSuccessResultMeals(List<Meal> meals,NetworkCalls type);
    void onSuccessResultCategories(List<Category> categories);
    void onFailure(String message);
}
