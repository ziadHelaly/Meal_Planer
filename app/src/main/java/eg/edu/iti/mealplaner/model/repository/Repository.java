package eg.edu.iti.mealplaner.model.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.remote.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public interface Repository {
    void saveUserId(String id);
    boolean isLogged();
    String getUserID();
    void addMealToFav(Meal meal);
    void removeMealFromFav(Meal meal);
    LiveData<List<Meal>> getFavsMeals(String id);

    void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack);
    void getCategories(HomeNetworkCallBack homeNetworkCallBack);
    void getMealById(String id,HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type);
    void getFilteredDataByArea(String a,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
    void getFilteredDataByCategory(String c,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
    void getFilteredDataByIngradiants(String i,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
}
