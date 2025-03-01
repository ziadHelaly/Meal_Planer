package eg.edu.iti.mealplaner.Favourites.presenter;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;

public interface FavPresenter extends PresenterInterface {
    void removeFav(Meal meal);
    void redoMeal();
    interface View extends PresenterInterface.View{
        void showOnGuestMode();

        void showSnackBar(String msg);

        void showLoading();
    }
}
