package eg.edu.iti.mealplaner.model.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.edu.iti.mealplaner.model.local.MealLocalDataSource;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.model.remote.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.utilies.Const;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;
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

    @Override
    public Single<Meal> getFavMealDetails(String id) {
        return local.getMeal(id);
    }

    @Override
    public void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack){
        remote.getRandomMeal(homeNetworkCallBack);
    }
    @Override
    public void getCategories(HomeNetworkCallBack homeNetworkCallBack){
        remote.getCategories(homeNetworkCallBack);
    }

    @Override
    public void getMealById(String id, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        remote.getMealById(id,homeNetworkCallBack,type);
    }

    @Override
    public void getFilteredDataByArea(String a, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type){
        remote.getFilteredDataByArea(a,homeNetworkCallBack,type);
    }

    @Override
    public void getFilteredDataByCategory(String c, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type) {
        remote.getFilteredDataByCategory(c,homeNetworkCallBack, type);
    }

    @Override
    public void getFilteredDataByIngradiants(String i, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type) {
        remote.getFilteredDataByIngredients(i,homeNetworkCallBack,type);
    }

}
