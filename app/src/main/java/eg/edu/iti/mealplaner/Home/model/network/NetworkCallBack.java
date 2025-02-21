package eg.edu.iti.mealplaner.Home.model.network;

import java.util.List;

import eg.edu.iti.mealplaner.Home.model.models.Meal;

public interface NetworkCallBack {
    void onSuccessResult(List<Meal> products);
    void onFailure(String message);
}
