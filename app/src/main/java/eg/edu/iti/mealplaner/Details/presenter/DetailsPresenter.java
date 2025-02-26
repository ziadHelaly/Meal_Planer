package eg.edu.iti.mealplaner.Details.presenter;

import eg.edu.iti.mealplaner.model.models.Meal;

public interface DetailsPresenter {
    void getData(String id);
    void addMealToFav(Meal meal);
    void removeFromFav(Meal meal);

    String extractYouTubeVideoId(String videoUrl);

    interface View{
        void showData(Meal meal);
        void showSnackBar(String msg);
        void onAddToFav();
        void onRemoveFromFav();
        void showLoading();
        void hideLoading();
    }
}
