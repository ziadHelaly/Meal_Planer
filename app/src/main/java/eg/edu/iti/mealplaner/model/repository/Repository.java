package eg.edu.iti.mealplaner.model.repository;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;

import java.util.List;

import eg.edu.iti.mealplaner.Auth.presenter.FirebaseCallBack;
import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import eg.edu.iti.mealplaner.model.models.Plan;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface Repository {
    void saveUserId(String id);
    void saveUserName(String username);

    void getUserName();

    void onSignOut();

    boolean isLogged();
    String getUserID();

    Single<Integer> isFavourite(String id);

    Completable addMealToFav(Meal meal);
    Completable removeMealFromFav(Meal meal);
    Flowable<List<Meal>> getFavsMeals( );
    Single<Meal> getFavMealDetails(String id);
    Single<MealsResponse>  getRandomMeal();
    Single<CategoryResponse> getCategories();
    Single<MealsResponse> getMealById(String id);
    Single<MealsResponse>  getFilteredDataByArea(String a);
    Single<MealsResponse>  getFilteredDataByCategory(String c);
    Single<MealsResponse>  getFilteredDataByIngradiants(String i);
    Single<FilterList<Ingredient>> getIngredientsFilterList();
    Single<FilterList<Country>> getCountriesFilterList();

    Flowable<List<Plan>> getPlansByDay(String userId, String day);

    Completable addPlan(Plan plan);

    Completable removePlan(Plan plan);

    void singIn(String email, String password, FirebaseCallBack callBack);
    void singUp(String email, String password, FirebaseCallBack callBack);
    void signInWithGoogleCredential(String idToken,FirebaseCallBack callBack);
    void signOut();
}
