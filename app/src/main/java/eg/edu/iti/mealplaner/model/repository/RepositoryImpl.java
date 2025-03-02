package eg.edu.iti.mealplaner.model.repository;

import com.google.android.gms.tasks.Task;

import java.util.List;

import eg.edu.iti.mealplaner.Auth.presenter.FirebaseCallBack;
import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSource;
import eg.edu.iti.mealplaner.model.local.MealLocalDataSourceImpl;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import eg.edu.iti.mealplaner.model.models.Plan;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.model.remote.MealsRemoteDataSourceImpl;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class RepositoryImpl implements Repository {
    MealsRemoteDataSource remote;
    MealLocalDataSource local;
    SharedPreference sharedPreference;
    FireBaseAuthModel fireBaseAuthModel;
    private static Repository repo = null;


    public RepositoryImpl(MealsRemoteDataSource remote, MealLocalDataSource local, SharedPreference sharedPreference, FireBaseAuthModel fireBaseAuthModel) {
        this.remote = remote;
        this.local = local;
        this.sharedPreference = sharedPreference;
        this.fireBaseAuthModel = fireBaseAuthModel;
    }

    public static Repository getRepository(
            MealsRemoteDataSource remote,
            MealLocalDataSource local,
            SharedPreference sharedPreference,
            FireBaseAuthModel fireBaseAuthModel
    ) {
        if (repo == null) {
            repo = new RepositoryImpl(remote, local, sharedPreference, fireBaseAuthModel);
        }
        return repo;
    }


    @Override
    public void saveUserId(String id) {
        sharedPreference.saveString(id, Const.USER_ID_TAG);
        sharedPreference.setBoolean(true, Const.isLogged_TAG);
        Const.isLogged = true;
        Const.USER_ID = id;
    }

    @Override
    public void saveUserName(String username) {
        sharedPreference.saveString(username, Const.USER_NAME_TAG);
        Const.USER_NAME = username;
    }

    @Override
    public void getUserName() {
        sharedPreference.getString(Const.USER_NAME_TAG);
    }

    @Override
    public void onSignOut() {
        sharedPreference.setBoolean(false, Const.isLogged_TAG);
        sharedPreference.removeString(Const.USER_ID_TAG);
        Const.isLogged = false;
        Const.USER_NAME = "Null";
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
    public Single<Integer> isFavourite(String id) {
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
    public Single<MealsResponse> getRandomMeal() {
        return remote.getRandomMeal();
    }

    @Override
    public Single<CategoryResponse> getCategories() {
        return remote.getCategories();
    }

    @Override
    public Single<MealsResponse> getMealById(String id) {
        return remote.getMealById(id);
    }

    @Override
    public Single<MealsResponse> getFilteredDataByArea(String a) {
        return remote.getFilteredDataByArea(a);
    }

    @Override
    public Single<MealsResponse> getFilteredDataByCategory(String c) {
        return remote.getFilteredDataByCategory(c);
    }

    @Override
    public Single<MealsResponse> getFilteredDataByIngradiants(String i) {
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

    @Override
    public Flowable<List<Plan>> getPlansByDay(String userId, String day) {
        return local.getPlans(userId, day);
    }

    @Override
    public Completable addPlan(Plan plan) {
        return local.addPlan(plan);
    }

    @Override
    public Completable removePlan(Plan plan) {
        return local.removePlan(plan);
    }

    @Override
    public void singIn(String email, String password, FirebaseCallBack callBack) {
           fireBaseAuthModel.singIn(email, password, callBack);
    }

    @Override
    public void singUp(String email, String password, FirebaseCallBack callBack) {
        fireBaseAuthModel.singUp(email, password, callBack);
    }

    @Override
    public void signInWithGoogleCredential(String idToken, FirebaseCallBack callBack) {
        fireBaseAuthModel.signInWithGoogleCredential(idToken, callBack);
    }

    @Override
    public void signOut() {
        fireBaseAuthModel.signOut();
    }
}
