package eg.edu.iti.mealplaner.Details.presenter;

import eg.edu.iti.mealplaner.model.models.Meal;

public interface DetailsPresenter {
    void getData(String id);
    interface View{
        void showData(Meal meal);
        void showLoading();
        void hideLoading();
    }
}
