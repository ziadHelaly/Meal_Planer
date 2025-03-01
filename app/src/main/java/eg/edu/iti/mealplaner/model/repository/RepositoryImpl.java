package eg.edu.iti.mealplaner.model.repository;

import android.content.Context;

import java.util.List;

import eg.edu.iti.mealplaner.model.local.MealLocalDataSource;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class RepositoryImpl implements Repository {
    MealsRemoteDataSource remote;
    MealLocalDataSource local;
    SharedPreference sharedPreference;

    private static Repository repo = null;
    private RepositoryImpl(Context context){
        remote= new MealsRemoteDataSource(context);
        sharedPreference= new SharedPreference(context);
        local= new MealLocalDataSource(context);
    }
    public static Repository getRepository(Context context) {
        if (repo ==null){
            repo =new RepositoryImpl(context);
        }
        return repo;
    }


    @Override
    public void saveUserId(String id) {
        sharedPreference.saveString(id, Const.USER_ID_TAG);
        sharedPreference.setBoolean(true,Const.isLogged_TAG);
        Const.isLogged=true;
        Const.USER_ID=id;
    }

    @Override
    public void saveUserName(String username) {
        sharedPreference.saveString(username,Const.USER_NAME_TAG);
        Const.USER_NAME=username;
    }
    @Override
    public void getUserName() {
        sharedPreference.getString(Const.USER_NAME_TAG);
    }

    @Override
    public void onSignOut(){
        sharedPreference.setBoolean(false,Const.isLogged_TAG);
        sharedPreference.removeString(Const.USER_ID_TAG);
        Const.isLogged=false;
        Const.USER_NAME="Null";
    }
    @Override
    public boolean isLogged() {
        return sharedPreference.getBoolean(Const.isLogged_TAG);
    }

    @Override
    public String getUserID() {
        return sharedPreference.getString(Const.USER_ID_TAG);
    }
    @Override
    public Single<Integer> isFavourite(String id){
        return local.isFavourite(id);
    }
    @Override
    public Completable addMealToFav(Meal meal) {
        meal.setUserId(Const.USER_ID);
        return local.insert(meal);
    }

    @Override
    public Completable removeMealFromFav(Meal meal) {
        return local.delete(meal);
    }

    @Override
    public Flowable<List<Meal>> getFavsMeals() {
        return local.getMeals(Const.USER_ID);
    }

    //TODO when we add network check we will use to load data in offline mode
    @Override
    public Single<Meal> getFavMealDetails(String id) {
        return local.getMeal(id);
    }

    @Override
    public Single<MealsResponse>  getRandomMeal(){
        return remote.getRandomMeal();
    }
    @Override
    public Single<CategoryResponse> getCategories(){
        return remote.getCategories();
    }

    @Override
    public Single<MealsResponse> getMealById(String id) {
        return remote.getMealById(id);
    }

    @Override
    public Single<MealsResponse>  getFilteredDataByArea(String a){
        return remote.getFilteredDataByArea(a);
    }

    @Override
    public Single<MealsResponse>  getFilteredDataByCategory(String c) {
        return remote.getFilteredDataByCategory(c);
    }

    @Override
    public Single<MealsResponse>  getFilteredDataByIngradiants(String i) {
        return remote.getFilteredDataByIngredients(i);
    }

    @Override
    public Single<FilterList<Ingredient>> getIngredientsFilterList() {
        return remote.getIngredientsList();
    }

    @Override
    public Single<FilterList<Country>> getCountriesFilterList() {
        return remote.getCountriesList();
    }

}
