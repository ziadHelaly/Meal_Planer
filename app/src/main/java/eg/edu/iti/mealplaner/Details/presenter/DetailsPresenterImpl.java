package eg.edu.iti.mealplaner.Details.presenter;

import android.annotation.SuppressLint;
import android.util.Log;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.Plan;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenterImpl implements DetailsPresenter {
    Repository repo;
    DetailsPresenter.View view;

    public DetailsPresenterImpl(Repository repo, View view) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData(String id) {
        view.showLoading();
        Log.d("``TAG``", "getData: "+isFav);
        if (isFav){
            repo.getFavMealDetails(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        view.hideLoading();
                        view.showData(item);
                    }, error -> {
                        view.showSnackBar("Error");
                    });
        }else{
            repo.getMealById(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        view.hideLoading();
                        view.showData(item.getMeals().get(0));
                    }, error -> {
                        view.showSnackBar("Error");
                    });
        }
    }
    boolean isFav=false;
    @SuppressLint("CheckResult")
    @Override
    public void isFavourite(String id) {
        if (Const.isLogged){
            repo.isFavourite(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        if (item > 0) {
                            view.onAddToFav();
                            isFav=true;
                        }
                        getData(id);
                    });
        }else{
            getData(id);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void addMealToFav(Meal meal) {
        if (Const.isLogged){
            repo.addMealToFav(meal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        view.showSnackBar("Added Successfully to favourites");
                        view.onAddToFav();
                    });
        }else{
            view.showSnackBar("SignIn to use this Feature");
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void removeFromFav(Meal meal) {
        repo.removeMealFromFav(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    view.showSnackBar("removed from favourites");
                    view.onRemoveFromFav();
                });
    }

    @Override
    public String extractYouTubeVideoId(String videoUrl) {
        if (videoUrl == null) return null;

        String pattern = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([^\"&?/ ]{11})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        return matcher.find() ? matcher.group(1) : null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void addToPlans(Meal meal, String date) {
        if (Const.isLogged){
            repo.addPlan(new Plan(
                            Const.USER_ID,
                            meal.getIdMeal(),
                            meal.getStrMealThumb(),
                            meal.getStrMeal(),
                            date
                    )).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        view.showSnackBar("Added to plans " + date);
                    });
        }else {
            view.showSnackBar("SignIn to use this Feature");
        }
    }


}
