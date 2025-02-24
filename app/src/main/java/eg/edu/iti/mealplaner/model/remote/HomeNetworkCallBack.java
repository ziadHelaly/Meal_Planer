package eg.edu.iti.mealplaner.model.remote;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public interface HomeNetworkCallBack {
    void onSuccessResultMeals(List<Meal> meals,NetworkCalls type);
    void onSuccessResultCategories(List<Category> categories);
    void onFailure(String message);
}
