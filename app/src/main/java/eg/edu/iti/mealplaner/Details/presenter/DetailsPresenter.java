package eg.edu.iti.mealplaner.Details.presenter;

import android.annotation.SuppressLint;

import eg.edu.iti.mealplaner.model.models.Meal;

public interface DetailsPresenter {
    void getData(String id);

    @SuppressLint("CheckResult")
    void isFavourite(String id);

    void addMealToFav(Meal meal);
    void removeFromFav(Meal meal);

    String extractYouTubeVideoId(String videoUrl);

    @SuppressLint("CheckResult")
    void addToPlans(Meal meal, String date);

    interface View{
        void showData(Meal meal);
        void showSnackBar(String msg);
        void onAddToFav();
        void onRemoveFromFav();
        void showLoading();
        void hideLoading();

    }
}
