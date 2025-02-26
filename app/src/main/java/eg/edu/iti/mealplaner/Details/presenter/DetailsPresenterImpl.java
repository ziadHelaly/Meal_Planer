package eg.edu.iti.mealplaner.Details.presenter;

import android.annotation.SuppressLint;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.remote.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

    @SuppressLint("CheckResult")
    @Override
    public void addMealToFav(Meal meal) {
        repo.addMealToFav(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    view.showSnackBar("Added Successfully to favourites");
                    view.onAddToFav();
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void removeFromFav(Meal meal) {
        repo.removeMealFromFav(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    view.showSnackBar("removed from favourites");
                    view.onRemoveFromFav();
                });
    }

    @Override
    public void onSuccessResultMeals(List<Meal> meals, NetworkCalls type) {
        if (type==NetworkCalls.MealById){
            view.showData(meals.get(0));
        }
    }
    @Override
    public String extractYouTubeVideoId(String videoUrl) {
        if (videoUrl == null) return null;

        String pattern = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([^\"&?/ ]{11})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Override
    public void onSuccessResultCategories(List<Category> categories) {

    }

    @Override
    public void onFailure(String message) {

    }
}
